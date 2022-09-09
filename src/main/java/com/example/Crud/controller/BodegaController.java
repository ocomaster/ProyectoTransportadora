package com.example.Crud.controller;

import com.example.Crud.dto.BodegaDTO;
import com.example.Crud.dto.ClienteDTO;
import com.example.Crud.dto.Mensaje;
import com.example.Crud.entity.Bodega;
import com.example.Crud.entity.Cliente;
import com.example.Crud.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodegas")
@CrossOrigin("*")
public class BodegaController {

    @Autowired
    BodegaService bodegaService;

    @GetMapping("/listaBodegas")
    public ResponseEntity<List<Bodega>> listaBodegas() {
        List<Bodega> bodegas = bodegaService.listaBodega();
        return new ResponseEntity<List<Bodega>>(bodegas, HttpStatus.OK);
    }

    @GetMapping("/detalleBodega/{id}")
    public ResponseEntity<Bodega> bodegaByid(@PathVariable("id") int id) {

        if (!bodegaService.existsBodegaById (id))
            return new ResponseEntity(new Mensaje("no existe la bodega"), HttpStatus.NOT_FOUND);

        Bodega bodega = bodegaService.getBodega(id).get();
        return new ResponseEntity(bodega, HttpStatus.OK);
    }

    @PostMapping("/crearBodega")
    public ResponseEntity<?> crearBodega(@RequestBody BodegaDTO bodegaDTO) {
        if (bodegaDTO.getNombre() == null)
            return new ResponseEntity(new Mensaje("El campo nombre es Obligatorio"), HttpStatus.BAD_REQUEST);

        Bodega bodega = new Bodega(bodegaDTO.getId(), bodegaDTO.getNombre(),bodegaDTO.getDireccion(),bodegaDTO.getTelefono());
        bodegaService.saveBodega(bodega);
        return new ResponseEntity<>(new Mensaje("Bodega creada Correctamente"), HttpStatus.OK);
    }

    @PutMapping("/actualizarBodega/{id}")
    public ResponseEntity<?> actualizarBodega(@PathVariable("id") int id, @RequestBody BodegaDTO bodegaDTO) {
        if (!bodegaService.existsBodegaById(id))
            return new ResponseEntity(new Mensaje("No existe la bodega"), HttpStatus.NOT_FOUND);

        Bodega bodega = bodegaService.getBodega(id).get();
        bodega.setNombre(bodegaDTO.getNombre());
        bodega.setDireccion(bodegaDTO.getDireccion());
        bodega.setTelefono(bodegaDTO.getTelefono());
        bodegaService.saveBodega(bodega);

        return new ResponseEntity(new Mensaje("Bodega Actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrarBodega/{id}")
    public ResponseEntity<?> borrarBodega(@PathVariable("id") int id) {
        if (!bodegaService.existsBodegaById(id))
            return new ResponseEntity(new Mensaje("No existe la bodega"), HttpStatus.NOT_FOUND);

        bodegaService.deleteBodega(id);
        return new ResponseEntity(new Mensaje("Bodega Eliminado"), HttpStatus.OK);
    }

}
