package com.example.demo.security.controller;

import com.example.demo.security.Mensaje;
import com.example.demo.security.dto.JwtDto;
import com.example.demo.security.dto.LoginUsuario;
import com.example.demo.security.dto.NuevoUsuario;
import com.example.demo.security.entity.Rol;
import com.example.demo.security.entity.Usuario;
import com.example.demo.security.enums.RolNombre;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.service.RolService;
import com.example.demo.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/auth/usuario/nuevo")
    public ResponseEntity<?> nuevo(@Valid
            @RequestBody NuevoUsuario nuevoUsuario,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>( new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"),HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombreUsuario(),passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles =new HashSet<>();
        if (nuevoUsuario.getRoles().contains("Admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.EDITOR).get());
            roles.add(rolService.getByRolNombre(RolNombre.OBSERVER).get());
        }
        else if (nuevoUsuario.getRoles().contains("Editor")) {
            roles.add(rolService.getByRolNombre(RolNombre.EDITOR).get());
            roles.add(rolService.getByRolNombre(RolNombre.OBSERVER).get());
        }

        else if (nuevoUsuario.getRoles().contains("Observer"))
            roles.add(rolService.getByRolNombre(RolNombre.OBSERVER).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario agregado con exito"),HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity( new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto= new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());
        return new ResponseEntity(jwtDto,HttpStatus.OK);
    }
    @GetMapping("/auth/usuario")
    public ResponseEntity<List<NuevoUsuario>> listarUsuarios(){
        List<Usuario> list = usuarioService.listarUsuarios();
        List<NuevoUsuario> lista=new ArrayList<>();
        list.forEach(usuario -> lista.add(new NuevoUsuario(usuario.getNombreUsuario(),usuario.getPassword(),
                this.obtenerRolMayor(usuario.getRoles()))));
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @PostMapping("/auth/usuario/eliminar")
    public ResponseEntity<?> eliminarUsuario(@Valid@RequestBody String usuario){
        if (!usuarioService.existsByNombreUsuario(usuario))
            return new ResponseEntity<>(new Mensaje("Ese usuario no existe"),HttpStatus.BAD_REQUEST);
        usuarioService.eliminarUsuario(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario agregado con exito"),HttpStatus.CREATED);

    }

    public Set<String> obtenerRolMayor(Set<Rol> roles){
       Set<String> rolesSt= roles.stream().map(rol -> rol.getRolNombre().toString()).collect(Collectors.toSet());
       Set<String> nuevo= new HashSet<>();
       if(rolesSt.contains("ADMIN")){
           nuevo.add("Admin");
           return nuevo;
       }
       else
           if (rolesSt.contains("EDITOR")){
               nuevo.add("Editor");
               return nuevo;
           }
           else {
               nuevo.add("Observador");
               return nuevo;
           }
    }

}
