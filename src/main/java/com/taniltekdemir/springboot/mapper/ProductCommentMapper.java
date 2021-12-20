package com.taniltekdemir.springboot.mapper;

import com.taniltekdemir.springboot.dto.ProductCommentDto;
import com.taniltekdemir.springboot.dto.ProductCommentSaveDto;
import com.taniltekdemir.springboot.entity.ProductComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCommentMapper {

    ProductCommentMapper INSTANCE = Mappers.getMapper(ProductCommentMapper.class);

    @Mapping(target = "urunname", source = "urun.adi")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "comment_date", source = "comment_date")
    ProductCommentDto convertCommentToCommentDto(ProductComment productComment);

    @Mapping(target = "urun.id", source = "urunId")
    @Mapping(target = "user.id", source = "userId")
    ProductComment convertCommentSaveDtoToComment(ProductCommentSaveDto dto);
}
