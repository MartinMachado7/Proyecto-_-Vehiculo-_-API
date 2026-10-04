package com.proyecto.v.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
@IdClass(Usuario.UsuarioId.class) // Se referencia como Usuario.UsuarioId
public class Usuario {

    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Id
    @Column(name = "id_persona", nullable = false)
    private Long idPersona;
    @MapsId
    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "idPersona")
    private Persona persona;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "apikey", nullable = false, unique = true)
    private String apikey;

    // ... demás atributos, relaciones y métodos de Usuario ...

    // Clase auxiliar como inner class estática al final del archivo
    public static class UsuarioId implements Serializable {
        private String login;
        private Long idPersona;

        public UsuarioId() {}

        public UsuarioId(String login, Long idPersona) {
            this.login = login;
            this.idPersona = idPersona;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UsuarioId)) return false;
            UsuarioId that = (UsuarioId) o;
            return Objects.equals(login, that.login) && Objects.equals(idPersona, that.idPersona);
        }

        @Override
        public int hashCode() {
            return Objects.hash(login, idPersona);
        }
    }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public Long getIdPersona() { return idPersona; }
    public void setIdPersona(Long idPersona) { this.idPersona = idPersona; }
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
   
}