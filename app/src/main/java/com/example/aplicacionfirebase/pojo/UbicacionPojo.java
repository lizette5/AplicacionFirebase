package com.example.aplicacionfirebase.pojo;

public class UbicacionPojo {
    Double latitud;
    Double longitud;

    public UbicacionPojo() {
    }

    public UbicacionPojo(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
