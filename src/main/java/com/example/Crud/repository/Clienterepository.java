package com.example.Crud.repository;

import com.example.Crud.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Clienterepository extends JpaRepository<Cliente, Integer> {
    @Override
    Optional<Cliente> findById(Integer cedula);
}
