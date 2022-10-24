package com.example.demo.services;

import com.example.demo.models.EmpleadoModel;
import com.example.demo.models.PedidoModel;
import com.example.demo.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    //---------------------------------------busca todos los empleados------------------
    public ArrayList<PedidoModel> obtenerPedidos(){
        return (ArrayList<PedidoModel>)pedidoRepository.findAll();
    }

    //----------------------------crea nuevo empleado--------------- y ---lo actualiza si se manda el ID(ci) de un empleado que ya existe
    public PedidoModel guardarPedido(PedidoModel pedido){
        return pedidoRepository.save(pedido);
    }

    //********************* Busqueda de empleado por ID (ci) **************** NO REQUIERE UN public EN REPOSITORY
    public Optional<PedidoModel> obtenerPorIdpedido(Long idpedido){
        return pedidoRepository.findById(idpedido);
    }

    //********************* Elimina empleado por ID (ci) ****************** NO REQUIERE UN public EN REPOSITORY
    //public boolean eliminarUsuario(Long id) {
    public boolean eliminarpedido(Long idpedido) {
        try{
            pedidoRepository.deleteById(idpedido);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    //-------------------------------------------------------------------------------



}
