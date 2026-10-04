package com.proyecto.v.controller;

import com.proyecto.v.entity.Persona;
import com.proyecto.v.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private PersonaRepository personaRepo;

    @GetMapping("/conductores/pueden-operar")
    public ResponseEntity<List<Persona>> obtenerConductoresHabilitados() {
        return ResponseEntity.ok(personaRepo.obtenerConductoresQuePuedenOperar());
    }

    @GetMapping("/personas/conteo-por-tipo")
    public ResponseEntity<List<Object[]>> obtenerConteoPersonasPorTipo() {
        return ResponseEntity.ok(personaRepo.contarPersonasPorTipo());
    }
}