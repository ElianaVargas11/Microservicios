package com.example.demo.controllers;

import com.example.demo.models.EmpleadoModel;
import com.example.demo.models.PedidoModel;
import com.example.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;
    //---------------------------------------busca todos los empleados------------------
    @GetMapping()
    public ArrayList<PedidoModel> obtenerPedidos(){

        return pedidoService.obtenerPedidos();
    }

    //----------------------------crea nuevo empleado--------------- y ---lo actualiza si se manda el ID(ci) de un empleado que ya existe
    @PostMapping()
    public PedidoModel guardarPedido(@RequestBody PedidoModel pedido){

        return this.pedidoService.guardarPedido(pedido);
    }

    //---------------------------------------------------------------------------------------------
    @GetMapping( path = "/{idpedido}")
    public Optional<PedidoModel> obtenerPedidoPorIdpedido(@PathVariable("idpedido") Long idpedido) {
return this.pedidoService.obtenerPorIdpedido(idpedido);
    }

//--------------------------------------------------------------------------------------------------
    @DeleteMapping(path = "/{idpedido}")
    public String eliminarPorIdpedido(@PathVariable("idpedido") Long idpedido){
        boolean ok = this.pedidoService.eliminarpedido(idpedido);
        if (ok){
            return "Se eliminó el empleado con CI " + idpedido;
        }else{
            return "No pudo eliminar el usuario con CI" + idpedido;
        }
    }

}
