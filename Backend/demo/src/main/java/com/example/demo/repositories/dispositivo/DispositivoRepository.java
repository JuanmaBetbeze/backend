package com.example.demo.repositories.dispositivo;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Empleado.Empleado;
import com.example.demo.models.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    boolean existsByModelo(String modelo);
    Dispositivo findById(long id);
    Dispositivo findDispositivoByModelo(String modelo);
    List<Dispositivo>  findDispositivosByAsegurado(boolean asegurado);
    List<Dispositivo> findDispositivosByEmpleadoActual(Empleado empleado);
}
