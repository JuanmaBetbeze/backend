package com.example.demo.controllers;

import com.example.demo.models.Usuario.UsuarioModel;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
  @Autowired
  UsuarioService usuarioService;
  @GetMapping()
  public ArrayList<UsuarioModel> obtenerUsuarios(){
    return usuarioService.obtenerUsuarios();
  }
  @PostMapping()
  public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
    return this.usuarioService.guardarUsuario(usuario);
  }
}
