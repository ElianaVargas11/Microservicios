package com.example.demo.controllers;

import com.example.demo.models.CategoriaModel;
import com.example.demo.models.PagoModel;
import com.example.demo.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/pago")
public class PagoController {

  @Autowired
  PagoService pagoService;
  //---------------------------------------busca todos los categoria------------------
  @GetMapping()
  public ArrayList<PagoModel> obtenerPagos() {
    return pagoService.obtenerPagos();
  }

  //----------------------------crea nueva categoria----------------------------------
  // y --- actualiza si se manda el ID(id_categoria) de una categoria que ya existe
  @PostMapping()
  public PagoModel guardarPago(@RequestBody PagoModel pago){
    return this.pagoService.guardarPago(pago);
  }

  //----------------------------Busqueda de categoria por ID (id_categoria)-----------------------
  @GetMapping( path = "/{idpago}")
  public Optional<PagoModel> obtenerPorId_pago(@PathVariable("idpago") Integer idpago) {
    return this.pagoService.obtenerPorId_pago(idpago);
  }

  @GetMapping( path = "/factura")
  public PagoModel parafactura(){
    return this.pagoService.parafactura();
  }

}
