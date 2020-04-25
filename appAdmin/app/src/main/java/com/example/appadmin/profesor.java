package com.example.appadmin;

public class profesor {

    private String nombreP;
    private String codigoP;
    private String facultad;

    public profesor(String nombreP, String codigoP, String facultad) {
        this.nombreP = nombreP;
        this.codigoP = codigoP;
        this.facultad = facultad;
    }

    public profesor() {
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
