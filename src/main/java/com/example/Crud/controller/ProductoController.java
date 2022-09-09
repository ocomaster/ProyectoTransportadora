package com.example.Crud.controller;


import com.example.Crud.dto.Mensaje;
import com.example.Crud.dto.ProductoDTO;
import com.example.Crud.entity.Producto;
import com.example.Crud.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/listaProductos")
    public ResponseEntity<List<Producto>> listaProductos(){
        List<Producto> productos = productoService.listaProducto();
        return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
    }

    @GetMapping("/detalleProducto/{id}")
            public ResponseEntity<Producto> productoById(@PathVariable("id")int id){

        if (!productoService.existsByIdProducto(id))
            return new ResponseEntity(new Mensaje("No existe Producto con ese Id"), HttpStatus.NOT_FOUND);

        Producto producto = productoService.getProducto(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }


    @RequestMapping("/crearProducto")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoDTO productoDTO){
        if (productoDTO.getNombre() ==null)
            return new ResponseEntity(new Mensaje("El campo nombre es obligatorio"),HttpStatus.BAD_REQUEST);

        Producto producto = new Producto(productoDTO.getId(), productoDTO.getNombre(), productoDTO.getPrecio());
        productoService.saveProducto(producto);
        return new ResponseEntity<>(new Mensaje("Producto creado correctamente"), HttpStatus.OK);
    }

    @PutMapping("/actualizarProducto/{id}")
    public ResponseEntity<?>  actualizarCliente(@PathVariable("id") int id, @RequestBody ProductoDTO productoDTO){
        if ( !productoService.existsByIdProducto(id))
        return new ResponseEntity(new Mensaje("No existe ese ID"), HttpStatus.NOT_FOUND);

        Producto producto = productoService.getProducto(id).get();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        productoService.saveProducto(producto);
        return new ResponseEntity(new Mensaje("Producto Actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/borrarProducto/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable("id") int id){
        if (!productoService.existsByIdProducto(id))
        return new ResponseEntity(new Mensaje("No existe ese Id"), HttpStatus.NOT_FOUND);

        productoService.deleteProducto(id);
        return new ResponseEntity(new Mensaje("Producto Eliminado"), HttpStatus.OK);
    }

}


