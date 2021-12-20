package com.taniltekdemir.springboot.controller;

import com.taniltekdemir.springboot.dto.ProductCommentDto;
import com.taniltekdemir.springboot.entity.ProductComment;
import com.taniltekdemir.springboot.service.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productComment")
public class ProductCommentController {
    @Autowired
    private ProductCommentService commentService;

    @GetMapping("/findAllByUserId/{userId}")
    public List<ProductCommentDto> findAllComment(@PathVariable Long userId) {
        return commentService.findAllComment(userId);
    }

    @GetMapping("/findAllByProductId/{productId}")
    public List<ProductCommentDto> findAllCommentByProductId(@PathVariable Long productId) {
        return commentService.fillAllCommentByProductId(productId);
    }

    @PostMapping("")
    public ResponseEntity<ProductComment> saveProductComment(ProductCommentDto commentDto) {
        ProductComment productComment = commentService.save(commentDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(productComment.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/deleteProductComment/{commentId}")
    public void deleteProductComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }
}
