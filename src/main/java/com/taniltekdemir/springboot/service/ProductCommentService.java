package com.taniltekdemir.springboot.service;

import com.taniltekdemir.springboot.dto.ProductCommentDto;
import com.taniltekdemir.springboot.entity.ProductComment;
import com.taniltekdemir.springboot.entity.Urun;
import com.taniltekdemir.springboot.entity.User;
import com.taniltekdemir.springboot.exception.CommentNotFoundException;
import com.taniltekdemir.springboot.exception.UserNotFoundException;
import com.taniltekdemir.springboot.repository.ProductCommentRepository;
import com.taniltekdemir.springboot.repository.UrunRepository;
import com.taniltekdemir.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if (productCommentList.size() == 0) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                throw new CommentNotFoundException(user.get().getUsername() + " kullanıcısı henüz yorum yazmamıştır");
            } else {
                throw new UserNotFoundException("Kullanıcı bulunamadı");
            }
        }
        List<ProductCommentDto> dtoList = convertCommentDtoListToCommentList(productCommentList);
        return dtoList;
    }

    public List<ProductCommentDto> fillAllCommentByProductId(Long productId) {
        List<ProductComment> productCommentList = commentRepository.findAllByUrun_IdOrderByComment_dateDesc(productId);

        if (productCommentList.isEmpty()) {
            Urun urun = urunRepository.getById(productId);
            throw new CommentNotFoundException(urun.getAdi() + "ürününe henüz bir yorum yazılmamıştır.");
        }
        List<ProductCommentDto> dtoList = convertCommentDtoListToCommentList(productCommentList);
        return dtoList;
    }

    public ProductComment save (ProductCommentDto dto) {
        ProductComment comment = convertCommentDtoToComment(dto);
        return commentRepository.save(comment);
    }

    public ProductComment update (ProductCommentDto dto) {
        ProductComment comment = convertCommentDtoToComment(dto);
        return commentRepository.save(comment);
    }

    public void delete (Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private List<ProductCommentDto> convertCommentDtoListToCommentList(List<ProductComment> productCommentList) {
        List<ProductCommentDto> dtoList = new ArrayList<>();

        for (ProductComment comment : productCommentList) {
            ProductCommentDto dto = new ProductCommentDto();
            dto.setComment(comment.getComment());
            dto.setUsername(comment.getUser().getUsername());
            dto.setUrunname(comment.getUrun().getAdi());
            dto.setComment_date(comment.getComment_date());

            dtoList.add(dto);
        }
        return dtoList;
    }

    private ProductCommentDto convertCommentDtoToComment(ProductComment comment) {

            ProductCommentDto dto = new ProductCommentDto();
            dto.setComment(comment.getComment());
            dto.setUsername(comment.getUser().getUsername());
            dto.setUrunname(comment.getUrun().getAdi());
            dto.setComment_date(comment.getComment_date());

        return dto;
    }

    private ProductComment convertCommentDtoToComment(ProductCommentDto commentDto) {
        Urun urun = urunRepository.findAllByAdi(commentDto.getUrunname());
        User user = userRepository.findFirstByUsername(commentDto.getUsername());
        ProductComment comment = new ProductComment();
        comment.setComment(commentDto.getComment());
        comment.setUser(user);
        comment.setUrun(urun);
        comment.setComment_date(commentDto.getComment_date());

        return comment;
    }
}
