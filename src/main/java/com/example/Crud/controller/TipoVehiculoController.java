package com.example.Crud.controller;

import com.example.Crud.dto.Mensaje;
import com.example.Crud.dto.TipoVehiculoDTO;
import com.example.Crud.entity.TipoVehiculo;
import com.example.Crud.service.TipoVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/tipoVehiculo")
@CrossOrigin("*")
public class TipoVehiculoController {

    @Autowired
    TipoVehiculoService tipoVehiculoService;

    @GetMapping("/listaTipoVehiculos")
    public ResponseEntity<List<TipoVehiculo>> listaTipoVehiculos(){
        List<TipoVehiculo> tipoVehiculos = tipoVehiculoService.ListarTipoVehiculo();
        return new ResponseEntity<List<TipoVehiculo>>(tipoVehiculos, HttpStatus.OK);
    }

    @GetMapping("/detalleTipoVehiculo/{id}")
    public ResponseEntity<TipoVehiculo> tipoVehiculoById(@PathVariable("id") int id){
        if ( !tipoVehiculoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);

        TipoVehiculo tipoVehiculo = tipoVehiculoService.getTipoVehiculo(id).get();
        return  new ResponseEntity(tipoVehiculo, HttpStatus.OK);
    }

    @PostMapping("/crearTipoVehiculo")
    public  ResponseEntity<?> crearTipoVehiculo(@RequestBody TipoVehiculoDTO tipoVehiculoDTO){
        if (tipoVehiculoDTO.getNombre()==null)
            return new ResponseEntity(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);

        TipoVehiculo tipoVehiculo = new TipoVehiculo(tipoVehiculoDTO.getId(), tipoVehiculoDTO.getNombre());
        tipoVehiculoService.saveTipoVehiculo(tipoVehiculo);
        return new  ResponseEntity<>(new Mensaje("Tipo de Vehiculo Creado correctamente"),HttpStatus.OK);
    }

    @PutMapping("/actualizarTipoVehiculo/{id}")
    public ResponseEntity<?> actualizarTipoVehiculo(@PathVariable("id")int id, @RequestBody TipoVehiculoDTO tipoVehiculoDTO){
        if (!tipoVehiculoService.existsById(id))
            return new ResponseEntity(new Mensaje("El Tipo Vehiculo no existe"), HttpStatus.NOT_FOUND);

        TipoVehiculo tipoVehiculo = tipoVehiculoService.getTipoVehiculo(id).get();
        tipoVehiculo.setNombre(tipoVehiculoDTO.getNombre());
        tipoVehiculoService.saveTipoVehiculo(tipoVehiculo);
        return new ResponseEntity(new Mensaje("Tipo de Vehiculo Actualizado"), HttpStatus.OK);
     }

     @DeleteMapping("/borrarTipoVehiculo/{id}")
    public ResponseEntity<?> borrarTipoVehiculo(@PathVariable("id")int id){
        if (!tipoVehiculoService.existsById(id))
            return new ResponseEntity(new Mensaje("El Tipo de Vehiculo no existe"), HttpStatus.NOT_FOUND);

        tipoVehiculoService.deleteTipoVehiculo(id);
        return new ResponseEntity(new Mensaje("Tipo de Vehiculo Eliminado"),HttpStatus.OK);


     }



}
