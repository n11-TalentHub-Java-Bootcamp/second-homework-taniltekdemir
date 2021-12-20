package com.taniltekdemir.springboot.repository;

import com.taniltekdemir.springboot.entity.Urun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrunRepository extends JpaRepository<Urun, Long> {

    List<Urun> findAllByKategoriOrderByIdDesc(Long id);

    Urun findAllByAdi(String name);
}
