package com.example.demo.repositories;
import com.example.demo.models.CategoriaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaModel, Long>{

    public abstract ArrayList<CategoriaModel> findAllByActivoOrderByCategoriaprodDesc(String activo);
}
