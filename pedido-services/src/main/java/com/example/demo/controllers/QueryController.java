package com.example.demo.controllers;

import com.example.demo.models.PedidoModel;
import com.example.demo.services.PedidoService;
import com.example.demo.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/pedido/qmax")
public class QueryController {

    @Autowired
    QueryService queryService;

    @GetMapping()
    public Optional<PedidoModel> obtenerMaxi(){
        return this.queryService.obtenerMax();
    }

}
