package com.example.demo.services;

import com.example.demo.models.EmpleadoModel;
import com.example.demo.models.PedidoproductoModel;
import com.example.demo.repositories.EmpleadoRepository;
import com.example.demo.repositories.PedidoproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.*;

@Service
public class PedidoproductoService {
        @Autowired
        //UsuarioRepository usuarioRepository;
        PedidoproductoRepository pedidoproductoRepository;
        //---------------------------------------busca todos los empleados------------------
        //public ArrayList<UsuarioModel> obtenerUsuarios(){
        public ArrayList<PedidoproductoModel>obtenerPedidoproductos(){
            //return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
            return (ArrayList<PedidoproductoModel>) pedidoproductoRepository.findAll();
        }



        //----------------------------crea nuevo empleado--------------- y ---lo actualiza si se manda el ID(ci) de un empleado que ya existe
        //public UsuarioModel guardarUsuario(UsuarioModel usuario){
        public PedidoproductoModel guardarPedidoproducto(PedidoproductoModel pedidoproducto){
                //return usuarioRepository.save(usuario);
                return pedidoproductoRepository.save(pedidoproducto);
        }

        //********************* Busqueda de empleado por ID (ci) **************** NO REQUIERE UN public EN REPOSITORY
        //public Optional<UsuarioModel> obtenerPorId(Long id){
        public Optional<PedidoproductoModel>obtenerPorId(Long id){
                //return usuarioRepository.findById(id);
                return pedidoproductoRepository.findById(id);
        }

        //********************* Elimina empleado por ID (ci) ****************** NO REQUIERE UN public EN REPOSITORY
        //public boolean eliminarUsuario(Long id) {
        public boolean eliminarPedidoproducto(Long id) {
                try{
                        //usuarioRepository.deleteById(id);
                        pedidoproductoRepository.deleteById(id);
                        return true;
                }catch(Exception err){
                        return false;
                }
        }
        //*********************
        //********************* BUSCA si existe en pedidoproducto por idpedido e idproducto
        public PedidoproductoModel obtienePorIdpedidoAndIdproducto(Integer idpedido, Integer idproducto){
                return pedidoproductoRepository.findByIdpedidoAndIdproducto(idpedido,idproducto);
        }

        public ArrayList<PedidoproductoModel> obtienePedidoproductosPorIdpedido(Integer idpedido){
          return pedidoproductoRepository.findAllByIdpedidoOrderByNombreproducto(idpedido);
        }
//-------------------------------
        public PedidoproductoModel totalCantidadPrecioPedido(Integer idpedido){
          return pedidoproductoRepository.totalCantidadPrecioPedido(idpedido);
        }

//-------------------------------
        public ArrayList<PedidoproductoModel> productosMasVendidosEntreFechas (String a, String b){
          return  pedidoproductoRepository.productosMasVendidosEntreFechas(a , b);
        }


}

