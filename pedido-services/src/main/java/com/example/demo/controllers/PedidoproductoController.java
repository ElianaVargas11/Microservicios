package com.example.demo.controllers;

//import com.example.demo.models.EmpleadoModel;
import com.example.demo.models.PedidoproductoModel;
import com.example.demo.services.PedidoproductoService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/pedidoproducto")
public class PedidoproductoController {

    @Autowired
    PedidoproductoService pedidoproductoService;

    //---------------------------------------busca todos los empleados------------------
    @GetMapping()
    public ArrayList<PedidoproductoModel> obtenerPedidoproductos(){
        return pedidoproductoService.obtenerPedidoproductos();
    }


    //----------------------------crea nuevo empleado--------------- y ---lo actualiza si se manda el ID(ci) de un empleado que ya existe
    @PostMapping()
    //public EmpleadoModel guardarEmpleado(@RequestBody EmpleadoModel empleado){
    public PedidoproductoModel guardarPedidoproducto(@RequestBody PedidoproductoModel pedidoproducto){
        //return this.empleadoService.guardarEmpleado(empleado);
        return this.pedidoproductoService.guardarPedidoproducto(pedidoproducto);
    }


    //----------------------------Busca por Id ---------------------------------------------------------------
    @GetMapping( path = "/{id}")
    //public Optional<EmpleadoModel> obtenerEmpleadoPorCi(@PathVariable("ci") Long ci) {
    public Optional<PedidoproductoModel> obtenerPedidoproductoPorId(@PathVariable("id") Long id) {
        //return this.empleadoService.obtenerPorCi(ci);
        return  this.pedidoproductoService.obtenerPorId(id);
    }

//---------------------------------Elimina por ID -----------------------------------------------------------------

    //@DeleteMapping( path = "/{id}")
    @DeleteMapping(path = "/{id}")
    //public String eliminarPorId(@PathVariable("id") Long id){
    public String eliminarPorId(@PathVariable("id") Long id){
        //boolean ok = this.usuarioService.eliminarUsuario(id);
        boolean ok = this.pedidoproductoService.eliminarPedidoproducto(id);
        if (ok){
          return null;
          //return "Se eliminó el registro con id " + id;
        }else{
          return null;
            //return "No pudo eliminar el registro id" + id;

        }
    }

    //*********************
    //********************* BUSCA si existe en pedidoproducto por idpedido e idproducto
    @GetMapping("/busca/{idpedido}/{idproducto}")
    public PedidoproductoModel obtienePorIdpedidoAndIdproducto(@PathVariable("idpedido") Integer idpedido, @PathVariable("idproducto") Integer idproducto ){
        return this.pedidoproductoService.obtienePorIdpedidoAndIdproducto(idpedido,idproducto);
    }
    //********************* BUSCA todos los productos en el pedido
    @GetMapping("/carrito/{idpedido}")
    public ArrayList<PedidoproductoModel> obtienePedidoproductosPorIdpedido(@PathVariable("idpedido") Integer idpedido){
      return this.pedidoproductoService.obtienePedidoproductosPorIdpedido(idpedido);
    }
//--------------------------
    @GetMapping("/carrito/total/{idpedido}")
    public PedidoproductoModel totalCantidadPrecioPedido  (@PathVariable("idpedido") Integer idpedido){
      return this.pedidoproductoService.totalCantidadPrecioPedido(idpedido);
    }

//--------------------------
  @GetMapping("/reporteproductos/{a}/{b}")
  public ArrayList<PedidoproductoModel> productosMasVendidosEntreFechas (@PathVariable("a") String a, @PathVariable("b") String b){
    return this.pedidoproductoService.productosMasVendidosEntreFechas(a, b);
  }




}
