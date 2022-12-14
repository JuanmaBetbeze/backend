package com.example.demo.services.usuario;


import com.example.demo.models.Usuario.Usuario;
import com.example.demo.repositories.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }
    public void eliminarUsuario(String usuario){
        usuarioRepository.deleteByNombreUsuario(usuario);
    }
    public boolean existeById(int id){
        return usuarioRepository.existsById(id);
    }
}
