package com.taniltekdemir.springboot.service;

import com.taniltekdemir.springboot.dto.ProductCommentDto;
import com.taniltekdemir.springboot.dto.ProductCommentSaveDto;
import com.taniltekdemir.springboot.entity.ProductComment;
import com.taniltekdemir.springboot.entity.Urun;
import com.taniltekdemir.springboot.entity.User;
import com.taniltekdemir.springboot.exception.CommentNotFoundException;
import com.taniltekdemir.springboot.exception.UserNotFoundException;
import com.taniltekdemir.springboot.mapper.ProductCommentMapper;
import com.taniltekdemir.springboot.repository.ProductCommentRepository;
import com.taniltekdemir.springboot.repository.UrunRepository;
import com.taniltekdemir.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCommentService {

    @Autowired
    private ProductCommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UrunRepository urunRepository;

    public List<ProductCommentDto> findAllComment(Long userId) {
        List<ProductComment> productCommentList = commentRepository.findAllByUser_IdOrderByComment_date(userId);

        if (productCommentList.isEmpty()) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                throw new CommentNotFoundException(user.get().getUsername() + " kullanıcısı henüz yorum yazmamıştır");
            } else {
                throw new UserNotFoundException("Kullanıcı bulunamadı");
            }
        }
        return productCommentList.stream().map(ProductCommentMapper.INSTANCE::convertCommentToCommentDto).collect(Collectors.toList());
    }

    public List<ProductCommentDto> fillAllCommentByProductId(Long productId) {
        List<ProductComment> productCommentList = commentRepository.findAllByUrun_IdOrderByComment_dateDesc(productId);

        if (productCommentList.isEmpty()) {
            Urun urun = urunRepository.getById(productId);
            throw new CommentNotFoundException(urun.getAdi() + "ürününe henüz bir yorum yazılmamıştır.");
        }
        return productCommentList.stream().map(ProductCommentMapper.INSTANCE::convertCommentToCommentDto).collect(Collectors.toList());
    }

    public ProductComment save (ProductCommentSaveDto dto) {
        ProductComment comment = ProductCommentMapper.INSTANCE.convertCommentSaveDtoToComment(dto);
        return commentRepository.save(comment);
    }

    public ProductComment update (ProductCommentSaveDto dto) {
        ProductComment comment = ProductCommentMapper.INSTANCE.convertCommentSaveDtoToComment(dto);
        return commentRepository.save(comment);
    }

    public void delete (Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
