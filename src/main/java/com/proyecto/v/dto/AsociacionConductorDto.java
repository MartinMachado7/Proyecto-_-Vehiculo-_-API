package com.proyecto.v.dto;

import java.util.List;

public class AsociacionConductorDto {
    private Long conductorId;
    private List<Long> vehiculosIds;

    public Long getConductorId() { return conductorId; }
    public void setConductorId(Long conductorId) { this.conductorId = conductorId; }
    public List<Long> getVehiculosIds() { return vehiculosIds; }
    public void setVehiculosIds(List<Long> vehiculosIds) { this.vehiculosIds = vehiculosIds; }
}