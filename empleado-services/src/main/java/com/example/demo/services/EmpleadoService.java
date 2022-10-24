package com.example.demo.services;
import com.example.demo.models.EmpleadoModel;
import com.example.demo.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    //UsuarioRepository usuarioRepository;
    EmpleadoRepository empleadoRepository;
    //---------------------------------------busca todos los empleados------------------
    //public ArrayList<UsuarioModel> obtenerUsuarios(){
    public ArrayList<EmpleadoModel>obtenerEmpleados(){
        //return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
        return (ArrayList<EmpleadoModel>) empleadoRepository.findAll();
    }


    //----------------------------crea nuevo empleado--------------- y ---lo actualiza si se manda el ID(ci) de un empleado que ya existe
    //public UsuarioModel guardarUsuario(UsuarioModel usuario){
    public EmpleadoModel guardarEmpleado(EmpleadoModel empleado){
        //return usuarioRepository.save(usuario);
        return empleadoRepository.save(empleado);
    }

    //********************* Busqueda de empleado por ID (ci) **************** NO REQUIERE UN public EN REPOSITORY
    //public Optional<UsuarioModel> obtenerPorId(Long id){
    public Optional<EmpleadoModel>obtenerPorCi(Long ci){
        //return usuarioRepository.findById(id);
        return empleadoRepository.findById(ci);
    }

    //********************* Elimina empleado por ID (ci) ****************** NO REQUIERE UN public EN REPOSITORY
    //public boolean eliminarUsuario(Long id) {
    public boolean eliminarEmpleado(Long ci) {
        try{
            //usuarioRepository.deleteById(id);
            empleadoRepository.deleteById(ci);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    //-------------------------
    //public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad) {
    public ArrayList<EmpleadoModel>obtenerPorUsuario(String usuario) {
        //return usuarioRepository.findByPrioridad(prioridad);
        return empleadoRepository.findByUsuario(usuario);
    }
        //--public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad) {
//    public ArrayList<EmpleadoModel> obtenerPorUsuarioYContraseña(List<String> usuario_var) {
        //--return usuarioRepository.findByPrioridad(prioridad);
//        return empleadoRepository.findByUsuarioAndUsuario_contraseña(usuario_var);
//    }
//*************************************************************************************************************


}

