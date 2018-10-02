package fr.eni.projetlokacar.bo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "clients")
public class Client implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private String prenom;
    private int numPermis;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private String email;


    public Client() {
    }

    public Client(String nom, String prenom, int numPermis, String adresse, String codePostal, String ville, String telephone, String email) {

        this.nom = nom;
        this.prenom = prenom;
        this.numPermis = numPermis;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
    }

    public Client(int id, String nom, String prenom, int numPermis, String adresse, String codePostal, String ville, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numPermis = numPermis;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
    }


    protected Client(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        prenom = in.readString();
        numPermis = in.readInt();
        adresse = in.readString();
        codePostal = in.readString();
        ville = in.readString();
        telephone = in.readString();
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeInt(numPermis);
        dest.writeString(adresse);
        dest.writeString(codePostal);
        dest.writeString(ville);
        dest.writeString(telephone);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumPermis() {
        return numPermis;
    }

    public void setNumPermis(int numPermis) {
        this.numPermis = numPermis;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numPermis=" + numPermis +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

