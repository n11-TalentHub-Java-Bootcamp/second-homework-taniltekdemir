package com.taniltekdemir.springboot.controller;


import com.taniltekdemir.springboot.entity.Kategori;
import com.taniltekdemir.springboot.entity.Urun;
import com.taniltekdemir.springboot.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    @GetMapping("")
    public List<Kategori> findAll() {
        List<Kategori> all = kategoriService.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Kategori findById(@PathVariable Long id) {
        Kategori byId = kategoriService.findById(id);
        return byId;
    }

    @PostMapping("/saveCategory")
    public Kategori save(@RequestBody Kategori kategori) {
        Kategori save = kategoriService.save(kategori);
        return save;
    }

    @PostMapping("/updateCategory")
    public Kategori update(@RequestBody Kategori kategoriInput) {
        Kategori save = kategoriService.save(kategoriInput);
        return save;
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id) {
        kategoriService.deleteById(id);
    }

    public void delete (Kategori kategori) {
        kategoriService.delete(kategori);
    }


}
