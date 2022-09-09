package com.example.Crud.repository;

import com.example.Crud.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Integer> {
    @Override
    Optional<Bodega> findById(Integer integer);
}
