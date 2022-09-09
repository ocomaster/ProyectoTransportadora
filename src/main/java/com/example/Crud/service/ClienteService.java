package com.example.Crud.service;

import com.example.Crud.entity.Cliente;
import com.example.Crud.repository.Clienterepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    Clienterepository clienterepository;

    //Al extender de JPA, trae el metodo por defecto:
    public List<Cliente> listaCliente(){ return  clienterepository.findAll(); }

    public Optional<Cliente> getCliente(int cedula){ return clienterepository.findById(cedula); }


    public void  saveCliente(Cliente cliente){
        clienterepository.save(cliente);
    }

    public void deleteCliente(int cedula){
        clienterepository.deleteById(cedula);
    }

    public Boolean existsBycedula(int cedula){
        return clienterepository.existsById(cedula);
    }




}
