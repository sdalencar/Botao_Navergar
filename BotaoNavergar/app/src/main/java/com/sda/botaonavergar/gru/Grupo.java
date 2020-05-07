package com.sda.botaonavergar.gru;

public class Grupo {

    private int id;
    private String proprietario;
    private String nome;
    private double valor;

    public Grupo() {
    }

    public Grupo(int id, String proprietario, String nome, double valor) {
        this.id = id;
        this.proprietario = proprietario;
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", proprietario='" + proprietario + '\'' +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}