package com.example.Crud.service;

import com.example.Crud.entity.Bodega;
import com.example.Crud.entity.Cliente;
import com.example.Crud.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BodegaService {

    @Autowired
    BodegaRepository bodegaRepository;

    public List<Bodega> listaBodega(){ return  bodegaRepository.findAll(); }

    public Optional<Bodega> getBodega(int id){ return bodegaRepository.findById(id); }


    public void  saveBodega(Bodega bodega){
        bodegaRepository.save(bodega);
    }

    public void deleteBodega(int id){
        bodegaRepository.deleteById(id);
    }

    public Boolean existsBodegaById(int id){
        return bodegaRepository.existsById(id);
    }


}
