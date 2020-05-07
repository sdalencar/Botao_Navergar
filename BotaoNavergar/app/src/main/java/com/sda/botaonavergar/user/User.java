package com.sda.botaonavergar.user;

public class User {

    private int id;
    private String idUser;
    private String nivel;

    public User() {
    }

    public User(int id, String idUser, String nivel) {
        this.id = id;
        this.idUser = idUser;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", nivel=" + nivel +
                '}';
    }
}