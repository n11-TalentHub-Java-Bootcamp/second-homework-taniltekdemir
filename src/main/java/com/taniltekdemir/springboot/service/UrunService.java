package com.taniltekdemir.springboot.service;

import com.taniltekdemir.springboot.entity.Urun;
import com.taniltekdemir.springboot.repository.UrunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrunService {

    @Autowired
    private UrunRepository urunRepository;


    public List<Urun> findAll() {
        return (List<Urun>) urunRepository.findAll();
    }

    public Urun findById(Long id) {
        Optional<Urun> optionalUrun = urunRepository.findById(id);

        Urun urun = null;
        if (optionalUrun.isPresent()) {
            urun = optionalUrun.get();
        }
        return urun;
    }

    public Urun save (Urun urun) {
        urun = urunRepository.save(urun);
        return urun;
    }

    public void delete (Urun urun) {
        urunRepository.delete(urun);
    }

    public void deleteById (Long id) {
        urunRepository.deleteById(id);
    }

    public long count() {
        return urunRepository.count();
    }

    public List<Urun> findAllByCategoryId(Long id) { return urunRepository.findAllByKategoriOrderByIdDesc(id);}
}
