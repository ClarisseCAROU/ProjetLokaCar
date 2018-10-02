package fr.eni.projetlokacar.bo;

public class Modele {

    private int id;
    private String nom;
    private Marque marque;


    public Modele() {
    }

    public Modele(String nom, Marque marque) {
        this.nom = nom;
        this.marque = marque;
    }

    public Modele(int id, String nom, Marque marque) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Modele{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", marque=" + marque +
                '}';
    }
}
