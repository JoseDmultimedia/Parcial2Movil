package com.example.appadmin;

import java.util.List;

public class Estudiante {

    private String nombreE;
    private String codigoE;
    private  String programaE;
    private int posicion = 0;
    private List listaEstudainte;

    public Estudiante(String nombreE, String codigoE, String programaE) {
        this.nombreE = nombreE;
        this.codigoE = codigoE;
        this.programaE = programaE;
    }

    public Estudiante() {
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
