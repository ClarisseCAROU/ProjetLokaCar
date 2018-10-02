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
    private int clientId;
    private int vehiculeId;

    public Location() {
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

    @Override
    public String toString() {
        return "Location{" +
                "dateDepart=" + dateDepart +
                ", dateRetour=" + dateRetour +
                ", dateCloture=" + dateCloture +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
