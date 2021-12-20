package com.taniltekdemir.springboot.controller;

import com.taniltekdemir.springboot.dto.UrunDetayDto;
import com.taniltekdemir.springboot.dto.UrunDto;
import com.taniltekdemir.springboot.entity.Kategori;
import com.taniltekdemir.springboot.entity.Urun;
import com.taniltekdemir.springboot.exception.ProductNotFoundException;
import com.taniltekdemir.springboot.mapper.UrunMapper;
import com.taniltekdemir.springboot.service.KategoriService;
import com.taniltekdemir.springboot.service.UrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class UrunController {

    @Autowired
    private UrunService urunService;
    @Autowired
    private KategoriService kategoriService;

    @GetMapping("")
    public List<Urun> findAllProductsList() {
        return urunService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Urun> findById(@PathVariable Long id) {
        Urun urun = urunService.findById(id);

        if (urun == null) {
            throw new ProductNotFoundException("Product not found. id : " + id);
        }

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllProductsList()
        );
        EntityModel entityModel = EntityModel.of(urun);

        entityModel.add(linkTo.withRel("tum_urunler"));
        return entityModel;
    }

    @GetMapping("/dto/{id}")
    public UrunDetayDto finUrunDtodById(@PathVariable Long id) {

        Urun urun = urunService.findById(id);
        UrunDetayDto dto = convertUrunToUrunDetayDto(urun);

        return dto;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveProduct(@RequestBody UrunDto urunDto) {

//        Urun urun = UrunMapper.INSTANCE.convertUrunDTOToUrun(urunDto);
        Urun urun = convertUrunDtoToUrun(urunDto);

        urun = urunService.save(urun);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(urun.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        urunService.deleteById(id);
    }

    @GetMapping("/{id}/products")
    public List<Urun> findAllProductsByCategoryId(@PathVariable Long id) {
        return urunService.findAllByCategoryId(id);
    }

    private Urun convertUrunDtoToUrun(UrunDto urunDto) {
        Kategori kategori = kategoriService.findById(urunDto.getKategoriId());

        Urun urun = new Urun();
        urun.setAdi(urunDto.getAdi());
        urun.setFiyat(urunDto.getFiyat());
        urun.setKayit_tarihi(urunDto.getKayit_tarihi());
        urun.setKategori(kategori);
        return urun;
    }

    private UrunDetayDto convertUrunToUrunDetayDto(Urun urun) {
        Kategori kategori = kategoriService.findById(urun.getKategori().getId());

        UrunDetayDto dto = new UrunDetayDto();
        dto.setUrunAdi(urun.getAdi());
        dto.setUrunFiyati(urun.getFiyat());
        dto.setKategoriAdi(kategori.getAdi());
        return dto;
    }
}
