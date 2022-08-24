package com.example.demo.services;

import com.example.demo.models.Usuario.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {
  @Autowired
  UsuarioRepository usuarioRepository;
  public List<UsuarioModel> obtenerUsuarios(){
    return (List<UsuarioModel>) usuarioRepository.findAll();
  }
  public UsuarioModel guardarUsuario(UsuarioModel usuario){
    return usuarioRepository.save(usuario);
  }
  public Boolean verificar(UsuarioModel usuario){
   return this.obtenerUsuarios().stream().anyMatch(usuarios-> Objects.equals(usuarios.getUser(), usuario.getUser()));
  }

}
