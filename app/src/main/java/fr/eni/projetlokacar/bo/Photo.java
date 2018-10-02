package fr.eni.projetlokacar.bo;

import android.arch.persistence.room.PrimaryKey;

public abstract class Photo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String url;

    public Photo() {
    }

    public Photo(String url) {
        this.url = url;
    }

    public Photo(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
