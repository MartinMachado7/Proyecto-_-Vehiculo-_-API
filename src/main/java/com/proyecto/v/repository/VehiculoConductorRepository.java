package com.proyecto.v.repository;

import com.proyecto.v.entity.VehiculoConductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculoConductorRepository extends JpaRepository<VehiculoConductor, Long> {

    // Cambiado 'ConductorIdPersona' a 'PersonaIdPersona' para hacer match con el atributo 'persona'
    Optional<VehiculoConductor> findByPersonaIdPersonaAndVehiculoId(Long idPersona, Long vehiculoId);
}