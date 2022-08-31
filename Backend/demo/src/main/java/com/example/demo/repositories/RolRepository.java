package com.example.demo.repositories;

import com.example.demo.models.Usuario.Rol;
import com.example.demo.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
