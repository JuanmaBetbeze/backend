/* package com.example.demo.controllers;

import com.example.demo.models.Usuario.CategoriaUsuario;
import com.example.demo.models.Usuario.UsuarioModel;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
  @Autowired
  UsuarioService usuarioService;
  @GetMapping()
  public List<UsuarioModel> obtenerUsuarios(){
    return usuarioService.obtenerUsuarios();
  }
  /*
  @PostMapping()
  public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
    return this.usuarioService.guardarUsuario(usuario);
  }



  @PostMapping("/login")
  public ResponseEntity<?> verificarUsuario(@RequestBody UsuarioModel usuario){
    System.out.println(usuario);
    return usuarioService.verificar(usuario);
  }

}

   */