package Model;

public interface Effet {
    void appliquer(Joueur cible);
    String getDescription();
}