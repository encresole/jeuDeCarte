package Model;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Classe Combat — logique complete d'un tour de jeu
 * Auteur : Raphael
 *
 * Ce qui a ete fait :
 * - estEtourdi()      : verifie si "ETOURDI" est dans la HashMap
 * - estMort()         : verifie si les pv <= 0 ET le banc est vide
 * - promouvoirBanc()  : met le 1er Personnage du banc en actif
 * - jouerTour()       : gere l'etourdissement, ATTAQUER, JOUER_SORT et fin de partie
 * - resoudreAttaque() : applique BUFF_ATQ, ESQUIVE, et K.O.
 * - jouerSort()       : joue le 1er Sort de la main, applique son Effet sur la bonne cible
 *
 * Structures utilisees :
 * - HashMap<String, Integer> : effets actifs (nom effet -> tours restants)
 * - LinkedList<String>       : historique des actions du combat
 */
public class Combat {

    public Partie partie;
    public HashMap<String, Integer> effetsJoueur1;
    public HashMap<String, Integer> effetsJoueur2;
    public LinkedList<String> historique;

    public Combat(Partie partie) {
        this.partie = partie;
        this.effetsJoueur1 = new HashMap<>();
        this.effetsJoueur2 = new HashMap<>();
        this.historique = new LinkedList<>();
    }

    public boolean jouerTour(Model.ActionJoueur action) {
        Joueur attaquant;
        Joueur defenseur;
        HashMap<String, Integer> effetsAtt;
        HashMap<String, Integer> effetsDef;

        if (partie.tourDe == 0) {
            attaquant = partie.joueur1;
            defenseur = partie.joueur2;
            effetsAtt = effetsJoueur1;
            effetsDef = effetsJoueur2;
        } else {
            attaquant = partie.joueur2;
            defenseur = partie.joueur1;
            effetsAtt = effetsJoueur2;
            effetsDef = effetsJoueur1;
        }

        if (estEtourdi(effetsAtt)) {
            historique.add("Tour " + partie.tour + " : " + attaquant.name + " est etourdi et passe son tour !");
            decrementerEffets(effetsAtt);
            passerAuTourSuivant();
            return false;
        }

        if (action == Model.ActionJoueur.ATTAQUER) {
            resoudreAttaque(attaquant, defenseur, effetsAtt, effetsDef);
        } else if (action == Model.ActionJoueur.JOUER_SORT) {
            jouerSort(attaquant, defenseur);
        } else {
            historique.add("Tour " + partie.tour + " : " + attaquant.name + " passe son tour.");
        }

        decrementerEffets(effetsAtt);
        decrementerEffets(effetsDef);

        if (estMort(defenseur)) {
            partie.finPartie = true;
            historique.add("=== " + attaquant.name + " gagne la partie ! ===");
            return true;
        }

        passerAuTourSuivant();
        return false;
    }

    private void resoudreAttaque(Joueur attaquant, Joueur defenseur,
                                  HashMap<String, Integer> effetsAtt,
                                  HashMap<String, Integer> effetsDef) {
        if (!(attaquant.actif instanceof Personnage)) return;
        if (!(defenseur.actif instanceof Personnage)) return;

        Personnage pAtt = (Personnage) attaquant.actif;
        Personnage pDef = (Personnage) defenseur.actif;

        if (pAtt.energie < pAtt.coutEnergie) {
            historique.add(pAtt.nom + " n'a pas assez d'energie ! ("
                    + pAtt.energie + "/" + pAtt.coutEnergie + " requis)");
            return;
        }
        pAtt.energie -= pAtt.coutEnergie;

        int degats = pAtt.attaque;

        if (effetsAtt.containsKey("BUFF_ATQ")) {
            degats += 20;
        }

        if (effetsDef.containsKey("ESQUIVE")) {
            int hasard = (int)(Math.random() * 3);
            if (hasard == 0) {
                historique.add("Tour " + partie.tour + " : " + pDef.nom + " esquive l'attaque !");
                return;
            }
        }

        pDef.pv -= degats;
        historique.add("Tour " + partie.tour + " : " + pAtt.nom + " inflige " + degats + " degats a " + pDef.nom + " (PV : " + pDef.pv + ")");

        if (pDef.pv <= 0) {
            historique.add(pDef.nom + " est K.O. !");
            promouvoirBanc(defenseur);
        }
    }

    public boolean estEtourdi(HashMap<String, Integer> effets) {
        return effets.containsKey("ETOURDI");
    }

    public boolean estMort(Joueur joueur) {
        if (joueur.actif instanceof Personnage) {
            Personnage p = (Personnage) joueur.actif;
            if (p.pv <= 0 && joueur.banc.isEmpty()) {
                return true;
            }
        }
        if (joueur.actif == null && joueur.banc.isEmpty()) {
            return true;
        }
        return false;
    }

    public void promouvoirBanc(Joueur joueur) {
        for (int i = 0; i < joueur.banc.size(); i++) {
            if (joueur.banc.get(i) instanceof Personnage) {
                joueur.actif = joueur.banc.get(i);
                joueur.banc.remove(i);
                historique.add(joueur.actif.nom + " entre en combat depuis le banc !");
                return;
            }
        }
        joueur.actif = null;
        historique.add(joueur.name + " n'a plus de Personnage !");
    }

    /**
     * jouerSort — joue le premier Sort trouvé dans la main de l'attaquant.
     * - EffetDebuff cible l'adversaire (defenseur)
     * - EffetSoin et EffetBuff ciblent l'attaquant lui-même
     * Le sort joué est retiré de la main après utilisation.
     */
    public void jouerSort(Joueur attaquant, Joueur defenseur) {
        Sort sortAJouer = null;
        int index = -1;

        for (int i = 0; i < attaquant.main.size(); i++) {
            if (attaquant.main.get(i) instanceof Sort) {
                sortAJouer = (Sort) attaquant.main.get(i);
                index = i;
                break;
            }
        }

        if (sortAJouer == null) {
            historique.add("Tour " + partie.tour + " : " + attaquant.name + " n'a pas de sort en main !");
            return;
        }

        // EffetDebuff et EffetDommage ciblent l'adversaire, les autres ciblent soi-même
        Joueur cible;
        if (sortAJouer.effet instanceof EffetDebuff
                || sortAJouer.effet instanceof EffetDommage) {
            cible = defenseur;
        } else {
            cible = attaquant;
        }

        sortAJouer.effet.appliquer(cible);
        historique.add("Tour " + partie.tour + " : " + attaquant.name + " joue [" + sortAJouer.nom + "] sur " + cible.name + " !");

        // Le sort est consommé : on le retire de la main
        attaquant.main.remove(index);
    }

    public void ajouterEffet(HashMap<String, Integer> effets, String nomEffet, int duree) {
        effets.put(nomEffet, duree);
        historique.add("[Effet] " + nomEffet + " ajoute pour " + duree + " tour(s)");
    }

    public void decrementerEffets(HashMap<String, Integer> effets) {
        LinkedList<String> aSupprimer = new LinkedList<>();
        for (String nomEffet : effets.keySet()) {
            int restant = effets.get(nomEffet) - 1;
            if (restant <= 0) {
                aSupprimer.add(nomEffet);
            } else {
                effets.put(nomEffet, restant);
            }
        }
        for (String nomEffet : aSupprimer) {
            effets.remove(nomEffet);
            historique.add("[Fin effet] " + nomEffet);
        }
    }

    public void passerAuTourSuivant() {
        if (partie.tourDe == 0) {
            partie.tourDe = 1;
        } else {
            partie.tourDe = 0;
        }
    }

    public String getHistorique() {
        String resultat = "";
        for (String ligne : historique) {
            resultat += ligne + "\n";
        }
        return resultat;
    }
}