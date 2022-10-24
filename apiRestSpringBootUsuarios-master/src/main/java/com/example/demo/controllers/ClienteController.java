package com.example.demo.controllers;


import com.example.demo.models.CategoriaModel;
import com.example.demo.models.ClienteModel;
import com.example.demo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/cliente")
public class ClienteController {
  @Autowired
  ClienteService clienteService;
  //---------------------------------------busca todos los categoria------------------
  @GetMapping()
  public ArrayList<ClienteModel> obtenerClientes(){
    return clienteService.obtenerClientes();
  }

  //----------------------------crea nueva categoria----------------------------------
  // y --- actualiza si se manda el ID(id_categoria) de una categoria que ya existe
  @PostMapping()
  public ClienteModel guardarCliente(@RequestBody ClienteModel cliente){
    return this.clienteService.guardarCliente(cliente);
  }

  //----------------------------Busqueda de categoria por ID (id_categoria)-----------------------
  @GetMapping( path = "/{ci}")
  public Optional<ClienteModel> obtenerCategoriaPorId_categoria(@PathVariable("ci") Integer ci) {
    return this.clienteService.obtenerPorCi(ci);
  }


}
