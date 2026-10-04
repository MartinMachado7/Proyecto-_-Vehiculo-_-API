package com.proyecto.v.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vehiculo_conductores")
public class VehiculoConductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    // Importante: la regla de negocio "solo personas tipo CONDUCTOR" se valida
    // en el Service (igual que otras reglas de negocio del proyecto), porque
    // un CHECK de SQL no puede validar un valor de OTRA tabla.
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @Column(name = "fecha_asociacion", nullable = false)
    private LocalDate fechaAsociacion;

    // "PO" (Puede Operar), "EA" (Espera de Aprobación), "RO" (Restringido para Operar)
    @Column(nullable = false, length = 2,
            check = @CheckConstraint(name = "chk_estado_conductor", constraint = "estado IN ('PO','EA','RO')"))
    private String estado;
 // --- GETTERS Y SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }

    public LocalDate getFechaAsociacion() { return fechaAsociacion; }
    public void setFechaAsociacion(LocalDate fechaAsociacion) { this.fechaAsociacion = fechaAsociacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}