package fr.eni.projetlokacar.bo;

import android.arch.persistence.room.Entity;

import java.time.LocalDateTime;
import java.util.List;

@Entity(tableName = "locations")
public class Location {

    private LocalDateTime dateDepart;
    private LocalDateTime dateRetour;
    private LocalDateTime dateCloture;
    private String commentaire;
    private Vehicule vehicule;
    private Client client;
    private List<PhotoEtatDesLieux> photoEtatDesLieux;

    public Location() {
    }

    public Location(LocalDateTime dateDepart, LocalDateTime dateRetour, LocalDateTime dateCloture, String commentaire, Vehicule vehicule, Client client, List<PhotoEtatDesLieux> photoEtatDesLieux) {
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.dateCloture = dateCloture;
        this.commentaire = commentaire;
        this.vehicule = vehicule;
        this.client = client;
        this.photoEtatDesLieux = photoEtatDesLieux;
    }

    public LocalDateTime getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDateTime dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalDateTime getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDateTime dateRetour) {
        this.dateRetour = dateRetour;
    }

    public LocalDateTime getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDateTime dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<PhotoEtatDesLieux> getPhotoEtatDesLieux() {
        return photoEtatDesLieux;
    }

    public void setPhotoEtatDesLieux(List<PhotoEtatDesLieux> photoEtatDesLieux) {
        this.photoEtatDesLieux = photoEtatDesLieux;
    }

    @Override
    public String toString() {
        return "Location{" +
                "dateDepart=" + dateDepart +
                ", dateRetour=" + dateRetour +
                ", dateCloture=" + dateCloture +
                ", commentaire='" + commentaire + '\'' +
                ", vehicule=" + vehicule +
                ", client=" + client +
                ", photoEtatDesLieux=" + photoEtatDesLieux +
                '}';
    }
}
