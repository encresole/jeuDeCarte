package Model;


public class EffetVide implements Effet {

    @Override
    public void appliquer(Joueur cible) {
        // aucun effet
    }

    @Override
    public String getDescription() {
        return "Aucun effet";
    }
}