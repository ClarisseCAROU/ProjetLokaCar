package fr.eni.projetlokacar.bo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "utilisateurs")
public class Utilisateur {

    @PrimaryKey
    private String login;
    private String MotDePasse;

    public Utilisateur(String login, String motDePasse) {
        this.login = login;
        MotDePasse = motDePasse;
    }

    public Utilisateur() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        MotDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "login='" + login + '\'' +
                ", MotDePasse='" + MotDePasse + '\'' +
                '}';
    }
}

