package com.sda.botaonavergar.maq;

public class Maquina {

    private int id;
    private String grupo;
    private int numero;

    public Maquina() {
    }

    public Maquina(int id, String grupo, int numero) {
        this.id = id;
        this.grupo = grupo;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Maquina{" +
                "id=" + id +
                ", grupo='" + grupo + '\'' +
                ", numero=" + numero +
                '}';
    }
}