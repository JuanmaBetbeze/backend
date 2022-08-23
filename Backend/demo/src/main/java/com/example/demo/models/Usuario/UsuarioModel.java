package com.example.demo.models.Usuario;

import com.example.demo.models.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioModel extends EntidadPersistente {

  public String user;
  public String password;
  @Enumerated
  public CategoriaUsuario categoria;

  public String getUser () {
    return user;
  }

  public void setUser (String user) {
    this.user = user;
  }

  public String getPassword () {
    return password;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  public CategoriaUsuario getCategoria () {
    return categoria;
  }

  public void setCategoria (CategoriaUsuario categoria) {
    this.categoria = categoria;
  }
}
