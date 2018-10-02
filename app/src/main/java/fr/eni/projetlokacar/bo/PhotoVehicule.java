package fr.eni.projetlokacar.bo;

public class PhotoVehicule extends Photo{

    public PhotoVehicule() {
    }

    public PhotoVehicule(String url) {
        super(url);
    }

    public PhotoVehicule(int id, String url) {
        super(id, url);
    }

    @Override
    public String toString() {
        return "PhotoVehicule{}";
    }



}

