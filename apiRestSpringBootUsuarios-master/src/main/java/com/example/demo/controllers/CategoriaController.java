package com.example.demo.controllers;

import com.example.demo.models.CategoriaModel;
import com.example.demo.models.EmpleadoModel;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;
    //---------------------------------------busca todos los categoria------------------
    @GetMapping()
    public ArrayList<CategoriaModel> obtenerCategorias()
    {return categoriaService.obtenerCategorias(); }

    //----------------------------crea nueva categoria----------------------------------
    // y --- actualiza si se manda el ID(id_categoria) de una categoria que ya existe
    @PostMapping()
    public CategoriaModel guardarCategoria(@RequestBody CategoriaModel categoria){
        return this.categoriaService.guardarCategoria(categoria);
    }
  //http://localhost:9090/categoria
    //----------------------------Busqueda de categoria por ID (id_categoria)-----------------------
    @GetMapping( path = "/{id_categoria}")
    public Optional<CategoriaModel> obtenerCategoriaPorId_categoria(@PathVariable("id_categoria") Long id_categoria) {
        return this.categoriaService.obtenerPorId_categoria(id_categoria);
    }

    //----------------------------- Elimina categoria por ID (id_categoria)-----------------------
    @DeleteMapping(path = "/{id_categoria}")
    public String eliminarPorId_categoria(@PathVariable("id_categoria") Long id_categoria){
        boolean ok = this.categoriaService.eliminarcategoria(id_categoria);
        if (ok){
            return "Se eliminó categoria: " + id_categoria;
        }else{
            return "No pudo eliminar categoria: " + id_categoria;
        }
    }
    @GetMapping(path = "/activos/{activo}")
    //---------------------------------------
    //---------------------------------------busca todas las categorias activas--------------
    public ArrayList<CategoriaModel> obtenerActivos(@PathVariable("activo") String activo)
    {
        return categoriaService.categoriasActivas(activo);
    }


}
