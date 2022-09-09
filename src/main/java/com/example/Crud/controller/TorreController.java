package com.example.Crud.controller;


import com.example.Crud.dto.Mensaje;
import com.example.Crud.dto.TorreDTO;
import com.example.Crud.entity.Torre;
import com.example.Crud.service.TorreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Notacion para indicar que es un controlador de tipo Rest
@RestController
//Notacion para indicar el contecto de nuestros endpoint es decir /torre/nombreServicio
@RequestMapping("/torre")
//URL que permitimos que consuman nuestras APIS
//En caso de querer permitir todos los origentes ponemos en lugar de la URL un *
@CrossOrigin("*")
public class TorreController {
     /*El nombre de las torres es unico,
    en la creación y actualizacón se hace la validación*/

    //Inyeccion de dependencias
    @Autowired
    TorreService torreService;

    //Se indica el tipo de peticion asi como el nombre del servicio
    @GetMapping("/listaTorre")
    public ResponseEntity<List<Torre>> listaTorres(){
        List<Torre> torres = torreService.listaTorre();
        return new ResponseEntity<List<Torre>>(torres, HttpStatus.OK);
    }

    @GetMapping("/detalleTorre/{idTorre}")
    public ResponseEntity<Torre> torreById(@PathVariable("idTorre")int idTorre){

        if(!torreService.existsByIdTorre(idTorre))
            return  new ResponseEntity(new Mensaje("No existe la Torre"), HttpStatus.NOT_FOUND);

        Torre torre = torreService.getTorre(idTorre).get();
        return new ResponseEntity(torre, HttpStatus.OK);
    }
    //pendiente @Get nombre
    public ResponseEntity<Torre> torreByNombre(@PathVariable("nombreTorre") String nombreTorre){

        if(!torreService.existsByNombreTorre(nombreTorre))
            return new ResponseEntity(new Mensaje("No existe la torre"), HttpStatus.NOT_FOUND);

        Torre torre = torreService.getByNombreTorre(nombreTorre).get();
        return new ResponseEntity(torre, HttpStatus.OK);
    }
    //Con el ? le decimos que no devulve ningún tipo de dato
    //El body va a ser un JSON
    //Aqui se usa el apache commons lang
    // El import de StringUtils es import org.apache.commons.lang3.StringUtils;
@PostMapping("/crearTorre")
    public ResponseEntity<?> creaTorre(@RequestBody TorreDTO torreDTO) {
       /* if(StringUtils.isBlank(torreDto.getNombreTorre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        */
        if (torreDTO.getCantidadAptos() < 0 || (Integer) torreDTO.getCantidadAptos() == null)
            return new ResponseEntity(new Mensaje("La cantidad de apartamentos debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        if (torreService.existsByNombreTorre(torreDTO.getNombreTorre()))
            return new ResponseEntity<>(new Mensaje("Ya existe una torre con ese nombre"), HttpStatus.BAD_REQUEST);

        Torre torre = new Torre(torreDTO.getNombreTorre(), torreDTO.getCantidadAptos());
        torreService.saveTorre(torre);
        return new ResponseEntity<>(new Mensaje("Torre creada"), HttpStatus.OK);
    }

    @PutMapping("/actualizarTorre/{idTorre}")
    public ResponseEntity<?> actualizarTorre(@PathVariable("idTorre")int idTorre, @RequestBody TorreDTO torreDTO){
        if( !torreService.existsByIdTorre(idTorre))
            return new ResponseEntity(new Mensaje("No existe la torre"), HttpStatus.NOT_FOUND);

        if(torreService.existsByNombreTorre(torreDTO.getNombreTorre())
                && torreService.getByNombreTorre(torreDTO.getNombreTorre()).get().getIdTorre() !=idTorre)
            return new ResponseEntity(new Mensaje("El nombre de la torre ya existe"), HttpStatus.NOT_FOUND);
        /*
        if(StringUtils.isBlank(torreDto.getNombreTorre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
         */
        if(torreDTO.getCantidadAptos()<0 || (Integer) torreDTO.getCantidadAptos() == null)
            return new ResponseEntity(new Mensaje("La cantidad de aptos debe ser mayor a 0"),HttpStatus.BAD_REQUEST);

        Torre torre = torreService.getTorre(idTorre).get();
        torre.setNombreTorre(torreDTO.getNombreTorre());
        torre.setCantidadAptos(torreDTO.getCantidadAptos());
        torreService.saveTorre(torre);
        return new ResponseEntity(new Mensaje("Torre Actualizada"), HttpStatus.OK);
    }

        @DeleteMapping("/borrarTorre/{idTorre}")
        public ResponseEntity<?> borrarTorre(@PathVariable("idTorre") int idTorre){
        if (!torreService.existsByIdTorre(idTorre))
            return new ResponseEntity(new Mensaje("No existe la torre"), HttpStatus.NOT_FOUND);

        torreService.deleteTorre(idTorre);
        return  new ResponseEntity(new Mensaje("Torre eliminada"), HttpStatus.OK);

        }


}
