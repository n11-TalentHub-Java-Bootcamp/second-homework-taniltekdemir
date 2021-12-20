package com.taniltekdemir.springboot.repository;

import com.taniltekdemir.springboot.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Long> {

//    @Query("select kategori from Kategori kategori")

    List<Kategori> findAllByUstKategoriIsNullOrderByAdiDesc();
}
