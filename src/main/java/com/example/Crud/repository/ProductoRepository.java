package com.example.Crud.repository;

import com.example.Crud.entity.Cliente;
import com.example.Crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Override
    Optional<Producto> findById(Integer id);

   // Optional<Producto> findByNombreProducto(String nombre);

    //Boolean existsByNombreProducto(String nombre);

}
