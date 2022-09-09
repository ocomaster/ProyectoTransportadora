package com.example.Crud.service;

import com.example.Crud.entity.TipoVehiculo;
import com.example.Crud.repository.TipoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipoVehiculoService {

    @Autowired
    TipoVehiculoRepository tipoVehiculoRepository;

    public List<TipoVehiculo> ListarTipoVehiculo(){return tipoVehiculoRepository.findAll();}

    public Optional<TipoVehiculo> getTipoVehiculo(int id){ return tipoVehiculoRepository.findById(id);}

    public void saveTipoVehiculo(TipoVehiculo tipoVehiculo){  tipoVehiculoRepository.save(tipoVehiculo);}

    public void deleteTipoVehiculo(int id){ tipoVehiculoRepository.deleteById(id);}

    public boolean existsById(int id){ return tipoVehiculoRepository.existsById(id); }
}
