package com.taniltekdemir.springboot.mapper;


import com.taniltekdemir.springboot.dto.UrunDto;
import com.taniltekdemir.springboot.entity.Urun;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UrunMapper {

    UrunMapper INSTANCE = Mappers.getMapper(UrunMapper.class);

    @Mapping(target="kategori.id", source = "kategoriId")
    Urun convertUrunDTOToUrun(UrunDto dto);
}
