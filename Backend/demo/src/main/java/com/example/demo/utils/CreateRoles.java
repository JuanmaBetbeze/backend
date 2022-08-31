/*
package com.example.demo.utils;

import com.example.demo.models.Usuario.Rol;
import com.example.demo.security.enums.RolNombre;
import com.example.demo.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;


    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin=new Rol(RolNombre.ADMIN);
        Rol rolEditor=new Rol(RolNombre.EDITOR);
        Rol rolObserver=new Rol(RolNombre.OBSERVER);
        rolService.save(rolAdmin);
        rolService.save(rolEditor);
        rolService.save(rolObserver);
    }
}
*/