package com.proyecto.v.service;

import com.proyecto.v.entity.Persona;
import com.proyecto.v.entity.Usuario;
import com.proyecto.v.repository.PersonaRepository;
import com.proyecto.v.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class GestionPersonaUsuarioService {

    @Autowired
    private PersonaRepository personaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Persona crearPersona(Persona persona) {
        Persona nuevaPersona = personaRepo.save(persona);

        if ("A".equals(persona.getTipoPersona())) {
            String login = generarLoginNemotecnico(persona.getNombres(), persona.getApellidos(), persona.getIdentificacion());
            String rawPassword = UUID.randomUUID().toString().substring(0, 8);
            String apiKey = UUID.randomUUID().toString().replace("-", "");

            Usuario usuario = new Usuario();
            usuario.setLogin(login);
            usuario.setPersona(nuevaPersona);
            usuario.setIdPersona(nuevaPersona.getIdPersona());
            usuario.setPassword(passwordEncoder.encode(rawPassword));
            usuario.setApikey(apiKey);

            usuarioRepo.save(usuario);
        }
        return nuevaPersona;
    }

    public List<Persona> obtenerTodas() {
        return personaRepo.findAll();
    }

    public Persona obtenerPorId(Long id) {
        return personaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }

    @Transactional
    public Persona actualizarPersona(Long id, Persona personaActualizada) {
        Persona existente = obtenerPorId(id);
        existente.setNombres(personaActualizada.getNombres());
        existente.setApellidos(personaActualizada.getApellidos());
        existente.setEmail(personaActualizada.getEmail());
        return personaRepo.save(existente);
    }

    @Transactional
    public void cambiarPassword(String login, String nuevaPassword) {
        Usuario usuario = usuarioRepo.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con login: " + login));
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepo.save(usuario);
    }

    @Transactional
    public String regenerarApiKey(String login) {
        Usuario usuario = usuarioRepo.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con login: " + login));
        String nuevaApiKey = UUID.randomUUID().toString().replace("-", "");
        usuario.setApikey(nuevaApiKey);
        usuarioRepo.save(usuario);
        return nuevaApiKey;
    }

    private String generarLoginNemotecnico(String nombres, String apellidos, String identificacion) {
        String primeraLetraNombre = nombres.trim().substring(0, 1).toLowerCase();
        String primeraLetraApellido = apellidos.trim().substring(0, 1).toLowerCase();
        return primeraLetraNombre + primeraLetraApellido + identificacion;
    }
}