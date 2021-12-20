package com.taniltekdemir.springboot.repository;

import com.taniltekdemir.springboot.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    List<ProductComment> findAllByUser_IdOrderByComment_date(Long userId);

    List<ProductComment> findAllByUrun_IdOrderByComment_dateDesc(Long productId);
}
