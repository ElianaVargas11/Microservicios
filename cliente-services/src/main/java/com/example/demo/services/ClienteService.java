package com.example.demo.services;

import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClienteService {
  @Autowired
  ClienteRepository clienteRepository;
  //---------------------------------------busca todas las categorias-----------------
  public ArrayList<ClienteModel> obtenerClientes(){
    return (ArrayList<ClienteModel>)clienteRepository.findAll();
  }
  //----------------------------crea nueva categoria----------------------------------
  // y --- actualiza si se manda el ID(id_categoria) de una categoria que ya existe
  public ClienteModel guardarCliente(ClienteModel cliente){
    return clienteRepository.save(cliente);
  }

  //----------------------------Busqueda de empleado por ID (ci)-----------------------
  public Optional<ClienteModel> obtenerPorCi(Integer ci){
    return clienteRepository.findById(ci);
  }
}
