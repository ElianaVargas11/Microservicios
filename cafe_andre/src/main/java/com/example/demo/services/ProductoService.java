package com.example.demo.services;

import com.example.demo.models.ProductoModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    //---------------------------------------busca todas los productos-----------------
    public ArrayList<ProductoModel> obtenerProductos(){
        return (ArrayList<ProductoModel>)productoRepository.findAll();
    }
    //----------------------------crea nueva categoria----------------------------------
    // y --- actualiza si se manda el ID(id_categoria) de una categoria que ya existe
    public ProductoModel guardarProducto(ProductoModel producto){
        return productoRepository.save(producto);
    }

    //----------------------------Busqueda de empleado por ID (ci)-----------------------
    public Optional<ProductoModel> obtenerPorId_producto(Long idproducto){
        return productoRepository.findById(idproducto);
    }

    //-------------------------- Elimina empleado por ID (ci) ------------------------- NO REQUIERE UN public EN REPOSITORY
    public boolean eliminarProducto(Long idproducto) {
        try{
            productoRepository.deleteById(idproducto);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    //************************* BUSCA por id_categoria ****************** y devuelve los productos ordenados por nombre
    public ArrayList<ProductoModel> obtenerProductosPorIdcategoria(Long idcategoria) {
        return productoRepository.findByIdcategoriaOrderByNombreproducto(idcategoria);
    }
    //************************* BUSCA tosdos los productos  ****************** y devuelve los productos ordenados por categoria
    public ArrayList<ProductoModel> obtenerProductosOrdenadosPorCategoria() {
        return productoRepository.findAllByIdproductoIsNotNullOrderByIdcategoria();
    }

    //************************* BUSCA por id_categoria y estado activo SI ****************** y devuelve los productos ordenados por nombre
    public ArrayList<ProductoModel> obtenerProductosActivosPorIdcategoria (Long idcategoria, String activo){
        return productoRepository.findByIdcategoriaAndActivoOrderByNombreproducto(idcategoria,activo);
    }


}
