package com.example.demo.repositories;

import com.example.demo.models.Usuario.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel,String> {
    UsuarioModel findByuser(String user);

}
