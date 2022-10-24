package com.example.demo.controllers;

import com.example.demo.models.EmpleadoModel;
import com.example.demo.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;
    //---------------------------------------busca todos los empleados------------------
    @GetMapping()
    public ArrayList<EmpleadoModel> obtenerEmpleados(){

      return empleadoService.obtenerEmpleados();
    }

    //----------------------------crea nuevo empleado--------------- y ---lo actualiza si se manda el ID(ci) de un empleado que ya existe
    @PostMapping()
    public EmpleadoModel guardarEmpleado(@RequestBody EmpleadoModel empleado){
        return this.empleadoService.guardarEmpleado(empleado);
    }


    //---------------------------------------------------------------------------------------------
    @GetMapping( path = "/{ci}")
    public Optional<EmpleadoModel> obtenerEmpleadoPorCi(@PathVariable("ci") Long ci) {
        return this.empleadoService.obtenerPorCi(ci);
    }

//--------------------------------------------------------------------------------------------------

    //@DeleteMapping( path = "/{id}")
    @DeleteMapping(path = "/{ci}")
    //public String eliminarPorId(@PathVariable("id") Long id){
    public String eliminarPorCI(@PathVariable("ci") Long ci){
        //boolean ok = this.usuarioService.eliminarUsuario(id);
        boolean ok = this.empleadoService.eliminarEmpleado(ci);
        if (ok){
            return "Se eliminó el empleado con CI " + ci;
        }else{
            return "No pudo eliminar el usuario con CI" + ci;
        }
    }


    //----------------------------------- busca : /query?usuario= evargas -------------------------
    @GetMapping("/query")
    public ArrayList<EmpleadoModel> obtenerEmpleadoPorUsuario
    (@RequestParam("usuario") String usuario){
        //return this.usuarioService.obtenerPorPrioridad(prioridad);
        return  this.empleadoService.obtenerPorUsuario(usuario);
    }

}
