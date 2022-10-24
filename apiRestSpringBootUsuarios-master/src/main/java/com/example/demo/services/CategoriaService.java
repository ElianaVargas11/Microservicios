package com.example.demo.services;

import com.example.demo.models.CategoriaModel;
import com.example.demo.models.EmpleadoModel;
import com.example.demo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    //---------------------------------------busca todas las categorias-----------------
    public ArrayList<CategoriaModel> obtenerCategorias(){
        return (ArrayList<CategoriaModel>)categoriaRepository .findAll();
    }
    //----------------------------crea nueva categoria----------------------------------
    // y --- actualiza si se manda el ID(id_categoria) de una categoria que ya existe
    public CategoriaModel guardarCategoria(CategoriaModel categoria){
            return categoriaRepository.save(categoria);
    }

    //----------------------------Busqueda de empleado por ID (ci)-----------------------
    public Optional<CategoriaModel> obtenerPorId_categoria(Long id_categoria){
        return categoriaRepository.findById(id_categoria);
    }

    //********************* Elimina empleado por ID (ci) ****************** NO REQUIERE UN public EN REPOSITORY
    public boolean eliminarcategoria(Long id_categoria) {
        try{
            categoriaRepository.deleteById(id_categoria);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    //---------------------------------------
    //---------------------------------------busca todas las categorias activas--------------
    public ArrayList<CategoriaModel> categoriasActivas(String activo){
        return categoriaRepository.findAllByActivoOrderByCategoriaprodDesc(activo);
    }

}
