package fr.eni.projetlokacar.bo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Date;

import fr.eni.projetlokacar.dao.converters.DateConverter;

@Entity(
        tableName = "locations",
        primaryKeys = {"clientId", "vehiculeId", "dateDepart", "dateRetour"},
        foreignKeys = {
                @ForeignKey(entity = Client.class,
                        parentColumns = "id",
                        childColumns = "clientId",
                        onDelete = ForeignKey.RESTRICT),
                @ForeignKey(entity = Vehicule.class,
                        parentColumns = "id",
                        childColumns = "vehiculeId",
                        onDelete = ForeignKey.RESTRICT)
        }
)
public class Location implements Parcelable {

    private int clientId;
    private int vehiculeId;
    @TypeConverters(DateConverter.class)
    @NonNull
    private Date dateDepart;
    @TypeConverters(DateConverter.class)
    @NonNull
    private Date dateRetour;
    @TypeConverters(DateConverter.class)
    private Date dateCloture;
    private String commentaire;

    public Location() {
    }

    public Location(int clientId, int vehiculeId, @NonNull Date dateDepart, @NonNull Date dateRetour) {
        this.clientId = clientId;
        this.vehiculeId = vehiculeId;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
    }

    public Location(int clientId, int vehiculeId, @NonNull Date dateDepart, @NonNull Date dateRetour, Date dateCloture, String commentaire) {
        this.clientId = clientId;
        this.vehiculeId = vehiculeId;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.dateCloture = dateCloture;
        this.commentaire = commentaire;
    }

    protected Location(Parcel in) {
        commentaire = in.readString();
        clientId = in.readInt();
        vehiculeId = in.readInt();
        dateDepart = new Date(in.readLong());
        dateRetour = new Date(in.readLong());

        if(in.readInt() == 1) {
            dateCloture = new Date(in.readLong());
        } else {
            in.readLong(); // ignore stored value
            dateCloture = null;
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(commentaire);
        dest.writeInt(clientId);
        dest.writeInt(vehiculeId);
        dest.writeLong(dateDepart.getTime());
        dest.writeLong(dateRetour.getTime());

        dest.writeInt(dateCloture != null ? 1 : 0);
        dest.writeLong(dateCloture != null ? dateCloture.getTime() : 0);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(int vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "clientId=" + clientId +
                ", vehiculeId=" + vehiculeId +
                ", dateDepart=" + dateDepart +
                ", dateRetour=" + dateRetour +
                ", dateCloture=" + dateCloture +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
