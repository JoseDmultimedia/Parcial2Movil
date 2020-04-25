package com.example.appadmin;

public class estudiante {

    private String nombreE;
    private String codigoE;
    private  String programaE;

    public estudiante(String nombreE, String codigoE, String programaE) {
        this.nombreE = nombreE;
        this.codigoE = codigoE;
        this.programaE = programaE;
    }

    public estudiante() {
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getCodigoE() {
        return codigoE;
    }

    public void setCodigoE(String codigoE) {
        this.codigoE = codigoE;
    }

    public String getProgramaE() {
        return programaE;
    }

    public void setProgramaE(String programaE) {
        this.programaE = programaE;
    }
}
