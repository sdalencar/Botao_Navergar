package com.sda.sobcontrole.model;


import java.io.Serializable;

public class Leitura implements Serializable {
    private int id;
    private String identificador;
    private String grupo;
    private int numero;
    private int entrada;
    private int saida;

    public Leitura() {
    }

    public Leitura(int id, String identificador, String grupo, int numero, int entrada, int saida) {
        this.id = id;
        this.identificador = identificador;
        this.grupo = grupo;
        this.numero = numero;
        this.entrada = entrada;
        this.saida = saida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSaida() {
        return saida;
    }

    public void setSaida(int saida) {
        this.saida = saida;
    }

    @Override
    public String toString() {
        return "Leitura{" +
                "id=" + id +
                ", identificador='" + identificador + '\'' +
                ", grupo='" + grupo + '\'' +
                ", numero=" + numero +
                ", entrada=" + entrada +
                ", saida=" + saida +
                '}';
    }
}
