package com.example.demo.services;

import com.example.demo.models.Usuario.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<?> verificar(UsuarioModel usuario){
      UsuarioModel user=usuarioRepository.findByuser(usuario.getUser());
   if(user.getPassword().equals(usuario.getPassword())){
     return ResponseEntity.ok(usuario);
   }
   return ((ResponseEntity<?>) ResponseEntity.internalServerError());
  }

}
