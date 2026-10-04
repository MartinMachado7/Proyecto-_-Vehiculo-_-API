package com.proyecto.v.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(name = "identificacion", nullable = false, unique = true)
    private String identificacion;

    @Column(name = "tipo_identificacion", nullable = false, length = 10)
    private String tipoIdentificacion; // CC, CE, PASAPORTE

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "tipo_persona", nullable = false, length = 1)
    private String tipoPersona; // C: Conductor, A: Administrativo

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;

    @PrePersist
    @PreUpdate
    private void validarCampos() {
        if (!"CC".equals(tipoIdentificacion) && !"CE".equals(tipoIdentificacion)) {
            throw new IllegalArgumentException("Tipo de identificación no válido.");
        }
        if (!"C".equals(tipoPersona) && !"A".equals(tipoPersona)) {
            throw new IllegalArgumentException("Tipo de persona debe ser 'C' o 'A'.");
        }
    }

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}