package com.example.Crud.repository;

import com.example.Crud.entity.Torre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Notacion para indicar que es un repositorio
@Repository
public interface TorreRepository extends JpaRepository<Torre, Integer> {
    //Con @Respository le indico los metodos principales como Select, create, update, delete

    //Convención sobre convicción
    //CrudRepository permite realizar busquedas por campo según la entidad
    Optional<Torre> findByNombreTorre(String nombreTorre);

    boolean existsByNombreTorre(String nombreTorre);
}
