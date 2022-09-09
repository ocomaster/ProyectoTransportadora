package com.example.Crud.service;

import com.example.Crud.entity.Producto;
import com.example.Crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    //Al extender de JPA, trae el metodo por defecto:

    public List<Producto> listaProducto(){return productoRepository.findAll();}

    public Optional<Producto> getProducto(int id){return productoRepository.findById(id);}

    //public Optional<Producto> getByNombreProducto(String nombre){ return  productoRepository.findByNombreProducto(nombre);}

    public void saveProducto(Producto producto){productoRepository.save(producto);}

    public  void deleteProducto(int id){ productoRepository.deleteById(id);}

    public boolean existsByIdProducto(int id){ return productoRepository.existsById(id);}
    //public boolean existsByNombreProducto(String nombre){return  productoRepository.existsByNombreProducto(nombre);}

}
