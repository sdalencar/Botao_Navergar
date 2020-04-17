package com.sda.botaonavergar.pes;

public class Pessoa {

    private int id;
    private String nome;
    private String alias;
    private String celular;
    private String relacionamento;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String alias, String celular, String relacionamento) {
        this.id = id;
        this.nome = nome;
        this.alias = alias;
        this.celular = celular;
        this.relacionamento = relacionamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(String relacionamento) {
        this.relacionamento = relacionamento;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", alias='" + alias + '\'' +
                ", celular='" + celular + '\'' +
                ", relacionamento=" + relacionamento +
                '}';
    }
}