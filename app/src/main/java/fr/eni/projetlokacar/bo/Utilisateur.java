package fr.eni.projetlokacar.bo;

public class Utilisateur {
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

