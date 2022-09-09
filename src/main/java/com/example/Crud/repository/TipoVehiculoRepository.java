package com.example.Crud.repository;

import com.example.Crud.entity.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Integer> {

    @Override
    Optional<TipoVehiculo> findById(Integer integer);
}
