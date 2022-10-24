package com.example.demo.controllers;

import com.example.demo.models.ProductoModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    //---------------------------------------busca todos los categoria------------------
    @GetMapping()
    public ArrayList<ProductoModel> obtenerProductos(){return productoService.obtenerProductos();}

    //----------------------------crea nueva categoria----------------------------------
    // y --- actualiza si se manda el ID(id_categoria) de una categoria que ya existe
    @PostMapping()
    public ProductoModel guardarProducto(@RequestBody ProductoModel producto){
        return this.productoService.guardarProducto(producto);
    }

    //----------------------------Busqueda de categoria por ID (id_categoria)-----------------------
    @GetMapping( path = "/{id_producto}")
    public Optional<ProductoModel> obtenerProductoPorId_producto(@PathVariable("id_producto") Long id_producto) {
        return this.productoService.obtenerPorId_producto(id_producto);
    }

    //----------------------------- Elimina categoria por ID (id_categoria)-----------------------
    @DeleteMapping(path = "/{id_producto}")
    public String eliminarPorId_producto(@PathVariable("id_producto") Long id_producto){
        boolean ok = this.productoService.eliminarProducto(id_producto);
        if (ok){
            return "Se eliminó producto: " + id_producto;
        }else{
            return "No pudo eliminar producto: " + id_producto;
        }
    }
    //----------------------------------- busca : /query?usuario= evargas -------------------------
    //************************* BUSCA por id_categoria ****************** y devuelve los productos ordenados por nombre
    @GetMapping("/query")
    public ArrayList<ProductoModel> obtenerProductosPorIdcategoria(@RequestParam("idcategoria") Long idcategoria){
        return this.productoService.obtenerProductosPorIdcategoria(idcategoria);
    }

    //************************* BUSCA tosdos los productos  ****************** y devuelve los productos ordenados por categoria
    @GetMapping("/order")
    public ArrayList<ProductoModel> obtenerProductosOrdenadosPorCategoria(){
        return this.productoService.obtenerProductosOrdenadosPorCategoria();
    }

    //************************* BUSCA por categoria y estado activo SI ****************** y devuelve los productos ordenados por nombre
    @GetMapping("/activos/{idcategoria}")
    public ArrayList<ProductoModel> obtenerProductosActivosPorIdcategoria(@PathVariable("idcategoria") Long idcategoria){
        return this.productoService.obtenerProductosActivosPorIdcategoria(idcategoria,"si");
    }


}
