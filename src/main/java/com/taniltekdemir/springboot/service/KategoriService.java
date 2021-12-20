package com.taniltekdemir.springboot.service;

import com.taniltekdemir.springboot.entity.Kategori;
import com.taniltekdemir.springboot.repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;


    public List<Kategori> findAll() {
        return (List<Kategori>) kategoriRepository.findAll();
    }

    public Kategori findById(Long id) {
        Optional<Kategori> optionalKategori = kategoriRepository.findById(id);

        Kategori kategori = null;
        if (optionalKategori.isPresent()) {
            kategori = optionalKategori.get();
        }
        return kategori;
    }

    public Kategori save(Kategori kategori) {
        kategori = kategoriRepository.save(kategori);
        return kategori;
    }

    public void delete(Kategori kategori) {
        kategoriRepository.delete(kategori);
    }

    public void deleteById(Long id) {
        kategoriRepository.deleteById(id);
    }

}




