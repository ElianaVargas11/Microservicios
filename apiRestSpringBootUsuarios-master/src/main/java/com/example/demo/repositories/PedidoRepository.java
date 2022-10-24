package com.example.demo.repositories;

import com.example.demo.models.PedidoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoModel, Long>{
}
