package com.proyecto.v.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_documento", nullable = false, unique = true, length = 20)
    private String codigoDocumento;

    @Column(name = "nombre_documento", nullable = false, length = 100)
    private String nombreDocumento;

    @Column(name = "aplica_a", nullable = false, length = 2)
    private String aplicaA;

    @Column(nullable = false, length = 2)
    private String obligatorio;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Constructors
    public Documento() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoDocumento() { return codigoDocumento; }
    public void setCodigoDocumento(String codigoDocumento) { this.codigoDocumento = codigoDocumento; }

    public String getNombreDocumento() { return nombreDocumento; }
    public void setNombreDocumento(String nombreDocumento) { this.nombreDocumento = nombreDocumento; }

    public String getAplicaA() { return aplicaA; }
    public void setAplicaA(String aplicaA) { this.aplicaA = aplicaA; }

    public String getObligatorio() { return obligatorio; }
    public void setObligatorio(String obligatorio) { this.obligatorio = obligatorio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}