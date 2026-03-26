package Controller;

import java.util.Random;
import Model.*;

public class GameController {

    public Model m;
    Random random = new Random();

    public GameController(Model m) {
        this.m = m;
    }

    public void commencerCombat(Joueur joueur1, Joueur joueur2) {
        m.partieEnCours = new Partie(joueur1, joueur2, Model.TypeDePartie.JcJ);

        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
            joueur1.main.add(joueur1.deck.get(i));
        }

        for (int i = 0; i < m.TAILLEMAINDEBUT; i++) {
            joueur2.main.add(joueur2.deck.get(i));
        }

        m.partieEnCours.tourDe = tirageJoueur();
    }

    public int tirageJoueur() {
        return random.nextInt(2);
    }
}