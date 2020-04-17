package com.sda.botaonavergar.turno;

public class Turno {

    private int id;
    private String numero;
    private String nome;

    public Turno() {
    }

    public Turno(int id, String numero, String nome) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}