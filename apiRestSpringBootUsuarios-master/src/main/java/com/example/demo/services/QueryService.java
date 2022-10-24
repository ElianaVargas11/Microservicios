package com.example.demo.services;

import com.example.demo.models.PedidoModel;
import com.example.demo.repositories.EmpleadoRepository;
import com.example.demo.repositories.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryService {
    @Autowired
    QueryRepository queryRepository;

    public Optional<PedidoModel> obtenerMax(){
        return queryRepository.maxNativo();
    }
}
