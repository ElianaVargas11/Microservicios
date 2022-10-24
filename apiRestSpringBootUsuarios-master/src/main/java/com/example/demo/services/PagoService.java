package com.example.demo.services;

import com.example.demo.models.CategoriaModel;
import com.example.demo.models.PagoModel;
import com.example.demo.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PagoService {
  @Autowired
  PagoRepository pagoRepository;

  //---------------------------------------busca todas las categorias-----------------
  public ArrayList<PagoModel> obtenerPagos(){
    return (ArrayList<PagoModel>)pagoRepository.findAll();
  }
  //----------------------------crea nueva categoria----------------------------------
  // y --- actualiza si se manda el ID(id_categoria) de una categoria que ya existe
  public PagoModel guardarPago(PagoModel pago){
    return pagoRepository.save(pago);
  }

  //----------------------------Busqueda de empleado por ID (ci)-----------------------
  public Optional<PagoModel> obtenerPorId_pago(Integer id){
    return pagoRepository.findById(id);
  }

  public PagoModel parafactura(){
    return  pagoRepository.parafactura();
  }



}
