package com.sda.botaonavergar.lei;

public class Leitura {

    private int id;
    private int numero;
    private int entrada;
    private int saida;

    public Leitura() {
    }

    public Leitura(int id, int numero, int entrada, int saida) {
        this.id = id;
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
                ", numero=" + numero +
                ", entrada=" + entrada +
                ", saida=" + saida +
                '}';
    }
}
