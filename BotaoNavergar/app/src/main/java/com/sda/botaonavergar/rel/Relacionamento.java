package com.sda.botaonavergar.rel;

public class Relacionamento {

    private int id;
    private String relacao;

    public Relacionamento() {
    }

    public Relacionamento(int id, String relacao) {
        this.id = id;
        this.relacao = relacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRelacao() {
        return relacao;
    }

    public void setRelacao(String relacao) {
        this.relacao = relacao;
    }

    @Override
    public String toString() {
        return "Relacionamento{" +
                "id=" + id +
                ", relacao='" + relacao + '\'' +
                '}';
    }
}