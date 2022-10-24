package com.example.demo.repositories;

import java.util.*;
import java.util.Optional;
import com.example.demo.models.ProductoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long> {
    public abstract ArrayList<ProductoModel> findByIdcategoriaOrderByNombreproducto(Long idcategoria);
    public abstract ArrayList<ProductoModel> findAllByIdproductoIsNotNullOrderByIdcategoria ();

    public abstract ArrayList<ProductoModel> findByIdcategoriaAndActivoOrderByNombreproducto(Long idcategoria,String activo);
   //public Optional<ProductoModel> findByIdcategoriaAndActivoOrderByNombreproducto(Long idcategoria,String activo);
    //public ProductoModel findByIdcategoriaAndActivoOrderByNombreproducto(Long idcategoria,String activo);
}
