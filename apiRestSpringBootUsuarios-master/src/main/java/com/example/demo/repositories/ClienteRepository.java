package com.example.demo.repositories;

import com.example.demo.models.CategoriaModel;
import com.example.demo.models.ClienteModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, Integer> {
}
