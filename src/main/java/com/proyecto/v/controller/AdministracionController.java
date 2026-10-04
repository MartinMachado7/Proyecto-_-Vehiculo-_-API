package com.proyecto.v.controller;

import com.proyecto.v.dto.*;
import com.proyecto.v.entity.Persona;
import com.proyecto.v.service.GestionPersonaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdministracionController {

    @Autowired
    private GestionPersonaUsuarioService personaUsuarioService;

    // --- SERVICIOS DE PERSONA ---
    @PostMapping("/personas")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaUsuarioService.crearPersona(persona));
    }

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> listarPersonas() {
        return ResponseEntity.ok(personaUsuarioService.obtenerTodas());
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) {
        return ResponseEntity.ok(personaUsuarioService.actualizarPersona(id, persona));
    }

    // --- SERVICIOS DE USUARIO ---
    @PutMapping("/usuarios/{login}/password")
    public ResponseEntity<String> cambiarPassword(@PathVariable String login, @RequestBody CambioPasswordDto dto) {
        personaUsuarioService.cambiarPassword(login, dto.getNuevaPassword());
        return ResponseEntity.ok("Contraseña actualizada exitosamente.");
    }

    @GetMapping("/usuarios/{login}/apikey")
    public ResponseEntity<String> regenerarApiKey(@PathVariable String login) {
        return ResponseEntity.ok(personaUsuarioService.regenerarApiKey(login));
    }
}