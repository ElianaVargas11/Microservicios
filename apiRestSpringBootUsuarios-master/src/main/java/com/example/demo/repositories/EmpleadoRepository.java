package com.example.demo.repositories;
import com.example.demo.models.EmpleadoModel;
import com.example.demo.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmpleadoRepository extends CrudRepository<EmpleadoModel, Long>{
    //public abstract ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);

    public abstract ArrayList<EmpleadoModel> findByUsuario (String usuario);
    //public abstract ArrayList<EmpleadoModel> findByUsuarioAndUsuario_contraseña(List<String> usuario_var);
    //public abstract ArrayList<EmpleadoModel> findByCi(Long ci);
    //public abstract ArrayList<EmpleadoModel> findByUsuarioAndUsuario_contraseña(String usuario, String usuario_contraseña);
}

