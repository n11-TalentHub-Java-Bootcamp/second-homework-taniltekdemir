package com.taniltekdemir.springboot.repository;

import com.taniltekdemir.springboot.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    @Query("Select productComment from ProductComment productComment where productComment.user.id = :userId")
    List<ProductComment> findAllByUser_IdOrderByComment_date(@Param("userId")Long userId);


    @Query("Select productComment from ProductComment productComment where productComment.urun.id = :productId")
    List<ProductComment> findAllByUrun_IdOrderByComment_dateDesc(@Param("productId") Long productId);

}
