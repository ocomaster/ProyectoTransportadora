package com.example.Crud.controller;

import com.example.Crud.dto.ClienteDTO;
import com.example.Crud.dto.Mensaje;
import com.example.Crud.entity.Cliente;
import com.example.Crud.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //Se indica el tipo de peticion asi como el nombre del servicio
    @GetMapping("/listaClientes")
    public ResponseEntity<List<Cliente>> listaClientes() {
        List<Cliente> clientes = clienteService.listaCliente();
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    @GetMapping("/detalleCliente/{cedula}")
    public ResponseEntity<Cliente> clienteBycedula(@PathVariable("cedula") int cedula) {

        if (!clienteService.existsBycedula(cedula))
            return new ResponseEntity(new Mensaje("no existe la cédula"), HttpStatus.NOT_FOUND);

        Cliente cliente = clienteService.getCliente(cedula).get();
        return new ResponseEntity(cliente, HttpStatus.OK);
    }

    @PostMapping("/crearCliente")
    public ResponseEntity<?> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        if (clienteDTO.getNombre() == null)
            return new ResponseEntity(new Mensaje("El campo nombre es Obligatorio"), HttpStatus.BAD_REQUEST);

        Cliente cliente = new Cliente(clienteDTO.getCedula(), clienteDTO.getNombre(), clienteDTO.getApellido(), clienteDTO.getDireccion(), clienteDTO.getTelefono());
        clienteService.saveCliente(cliente);
        return new ResponseEntity<>(new Mensaje("Cliente creado"), HttpStatus.OK);
    }

    @PutMapping("/actualizarCliente/{cedula}")
    public ResponseEntity<?> actualizarCliente(@PathVariable("cedula") int cedula, @RequestBody ClienteDTO clienteDTO) {
        if (!clienteService.existsBycedula(cedula))
            return new ResponseEntity(new Mensaje("No existe la Cédula"), HttpStatus.NOT_FOUND);

        Cliente cliente = clienteService.getCliente(cedula).get();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        clienteService.saveCliente(cliente);
        return new ResponseEntity(new Mensaje("Cliente Actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrarCliente/{cedula}")
    public ResponseEntity<?> borrarCliente(@PathVariable("cedula") int cedula) {
        if (!clienteService.existsBycedula(cedula))
            return new ResponseEntity(new Mensaje("No existe la cedula"), HttpStatus.NOT_FOUND);

        clienteService.deleteCliente(cedula);
        return new ResponseEntity(new Mensaje("Cliente Eliminado"), HttpStatus.OK);
    }

}
