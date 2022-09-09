package com.example.Crud.service;

import com.example.Crud.entity.Torre;
import com.example.Crud.repository.TorreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//Notacion para indicar que es un servicio
@Service
//Asegura que toda la data requerida este segura hasta que la transacción termine
@Transactional
public class TorreService {

    //Inyeccion de dependencias(Crea una instancia cuando lo requiera)
    @Autowired
    TorreRepository torreRepository;

    //Al extender de JPA, trae el metodo por defecto:
    public List<Torre> listaTorre(){
        return torreRepository.findAll();
    }
    public Optional<Torre> getTorre(int idTorre){
        return  torreRepository.findById(idTorre);
    }
    public Optional<Torre> getByNombreTorre(String nombreTorre){
        return  torreRepository.findByNombreTorre(nombreTorre);
    }
    public void saveTorre(Torre torre){
        torreRepository.save(torre);
    }
    public void deleteTorre(int idTorre){
        torreRepository.deleteById(idTorre);
    }
    public boolean existsByIdTorre(int idTorre){
        return torreRepository.existsById(idTorre);
    }
    public boolean existsByNombreTorre(String nombreTorre){
        return  torreRepository.existsByNombreTorre(nombreTorre);
    }






}
