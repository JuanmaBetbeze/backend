package com.example.demo.security.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class NuevoUsuario {
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;
    private Set<String> roles=new HashSet<>();

    public NuevoUsuario(String nombreUsuario, String password, Set<String> roles) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.roles = roles;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
