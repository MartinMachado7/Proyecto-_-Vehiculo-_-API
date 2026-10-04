package com.proyecto.v.repository;

import com.proyecto.v.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	@Query("SELECT p.tipoPersona, COUNT(p) FROM Persona p GROUP BY p.tipoPersona")
    List<Object[]> contarPersonasPorTipo();

    // Se cambió vc.conductor por vc.persona para coincidir con tu entidad
    @Query("SELECT DISTINCT vc.persona FROM VehiculoConductor vc WHERE vc.estado = 'PO'")
    List<Persona> obtenerConductoresQuePuedenOperar();
}