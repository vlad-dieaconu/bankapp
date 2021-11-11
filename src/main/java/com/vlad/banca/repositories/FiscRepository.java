package com.vlad.banca.repositories;


import com.vlad.banca.models.Fisc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiscRepository extends JpaRepository<Fisc, Long> {
    Fisc findByidClient(Long clientMonitorizat);
    Boolean existsByidClient(Long clientMonitorizat);
    Fisc deleteByidClient(Long clientMonitorizat);
}
