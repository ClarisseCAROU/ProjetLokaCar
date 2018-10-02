package fr.eni.projetlokacar.bo;

public class PhotoEtatDesLieux extends Photo{
    private TypeEtatLieux typeEtatLieux;


    public PhotoEtatDesLieux() {
    }

    public PhotoEtatDesLieux(TypeEtatLieux typeEtatLieux) {
        this.typeEtatLieux = TypeEtatLieux.Depart;
    }

    public TypeEtatLieux getTypeEtatLieux() {
        return typeEtatLieux;
    }

    public void setTypeEtatLieux(TypeEtatLieux typeEtatLieux) {
        this.typeEtatLieux = typeEtatLieux;
    }

    @Override
    public String toString() {
        return "PhotoEtatDesLieux{" +
                "typeEtatLieux=" + typeEtatLieux +
                '}';
    }
}
