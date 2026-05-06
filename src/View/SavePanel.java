package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import data.Serialiseur;

/**
 * SavePanel — panneau de sauvegarde et de chargement de parties.
 *
 * Fonctionnalités :
 * - Sauvegarder la partie en cours (avec saisie d'un nom optionnel)
 * - Lister les parties sauvegardées
 * - Charger une partie sélectionnée dans la liste
 * - Supprimer une sauvegarde (bouton SUPPRIMER)
 * - Revenir à la partie en cours (bouton REPRENDRE)
 */
public class SavePanel extends PanelAgesOfClash {
    private static final long serialVersionUID = 1L;

    MenuManager menuManager;
    Serialiseur serialiseur = new Serialiseur();

    // ─── Composants ──────────────────────────────────────────────────────────
    ButtonAgesOfClash buttonReprendre  = new ButtonAgesOfClash("↩ Reprendre la partie");
    ButtonAgesOfClash buttonSauvegarder = new ButtonAgesOfClash("💾 Sauvegarder");
    ButtonAgesOfClash buttonCharger    = new ButtonAgesOfClash("📂 Charger");
    ButtonAgesOfClash buttonSupprimer  = new ButtonAgesOfClash("🗑 Supprimer");

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> listeSauvegardes     = new JList<>(listModel);

    LabelTitle labelTitre              = new LabelTitle("SAUVEGARDES");
    JLabel     labelStatus             = new JLabel(" ");

    public SavePanel(MenuManager m) {
        menuManager = m;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // ── Titre ────────────────────────────────────────────────────────────
        PanelAgesOfClash panelNord = new PanelAgesOfClash();
        panelNord.setLayout(new BoxLayout(panelNord, BoxLayout.Y_AXIS));
        labelTitre.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelNord.add(labelTitre);
        panelNord.add(Box.createVerticalStrut(6));

        labelStatus.setForeground(new java.awt.Color(120, 200, 120));
        labelStatus.setFont(new Font("SansSerif", Font.ITALIC, 14));
        labelStatus.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelNord.add(labelStatus);
        add(panelNord, BorderLayout.NORTH);

        // ── Liste des sauvegardes ────────────────────────────────────────────
        listeSauvegardes.setBackground(new java.awt.Color(35, 35, 35));
        listeSauvegardes.setForeground(java.awt.Color.WHITE);
        listeSauvegardes.setFont(new Font("Monospaced", Font.PLAIN, 14));
        listeSauvegardes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeSauvegardes.setFixedCellHeight(30);

        JScrollPane scroll = new JScrollPane(listeSauvegardes);
        scroll.setPreferredSize(new Dimension(400, 300));
        scroll.setBorder(BorderFactory.createLineBorder(new java.awt.Color(80, 80, 80)));
        add(scroll, BorderLayout.CENTER);

        // ── Boutons ──────────────────────────────────────────────────────────
        PanelAgesOfClash panelBoutons = new PanelAgesOfClash();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        dimensionnerBouton(buttonSauvegarder);
        dimensionnerBouton(buttonCharger);
        dimensionnerBouton(buttonSupprimer);
        dimensionnerBouton(buttonReprendre);

        panelBoutons.add(buttonSauvegarder);
        panelBoutons.add(Box.createVerticalStrut(12));
        panelBoutons.add(buttonCharger);
        panelBoutons.add(Box.createVerticalStrut(12));
        panelBoutons.add(buttonSupprimer);
        panelBoutons.add(Box.createVerticalGlue());
        panelBoutons.add(buttonReprendre);
        add(panelBoutons, BorderLayout.EAST);

        // ── Actions ──────────────────────────────────────────────────────────
        buttonReprendre.setActionCommand("REPRENDRE");
        buttonReprendre.addActionListener(menuManager.menuController);

        buttonSauvegarder.addActionListener(e -> sauvegarder());
        buttonCharger.addActionListener(e -> charger());
        buttonSupprimer.addActionListener(e -> supprimer());
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  Actions
    // ─────────────────────────────────────────────────────────────────────────

    /** Appelé à chaque fois qu'on affiche ce panel — rafraîchit la liste. */
    public void rafraichirListe() {
        listModel.clear();
        List<String> ids = serialiseur.listerIdsParties();
        for (String id : ids) {
            listModel.addElement(id);
        }
        setStatus(ids.isEmpty() ? "Aucune sauvegarde trouvée." : ids.size() + " sauvegarde(s) disponible(s).");
    }

    private void sauvegarder() {
        if (menuManager.model.partieEnCours == null) {
            setStatus("❌ Aucune partie en cours à sauvegarder !");
            return;
        }

        // Proposer un nom par défaut basé sur le numéro de tour
        String defaut = "partie_tour" + menuManager.model.partieEnCours.tour
                + "_" + System.currentTimeMillis() % 10000;

        String id = JOptionPane.showInputDialog(
                this,
                "Nom de la sauvegarde :",
                defaut);

        if (id == null || id.trim().isEmpty()) return; // annulé

        id = id.trim().replaceAll("\\s+", "_"); // pas d'espaces dans l'id

        serialiseur.sauvegarderPartie(menuManager.model.partieEnCours, id);
        setStatus("✅ Partie sauvegardée sous « " + id + " »");
        rafraichirListe();
    }

    private void charger() {
        String id = listeSauvegardes.getSelectedValue();
        if (id == null) {
            setStatus("⚠️ Sélectionnez une sauvegarde dans la liste.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Charger « " + id + " » ? La partie en cours sera remplacée.",
                "Confirmer le chargement",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        Model.Partie partie = serialiseur.chargerPartie(id, menuManager.model.lesCartes);
        if (partie == null) {
            setStatus("❌ Impossible de charger la partie « " + id + " ».");
            return;
        }

        // Injecter la partie chargée dans le modèle
        menuManager.model.partieEnCours = partie;
        menuManager.model.joueur1       = partie.joueur1;
        menuManager.model.joueur2       = partie.joueur2;

        // Recréer le combat pour la partie chargée
        menuManager.gameController.combat = new Model.Combat(partie);

        // Synchroniser joueurEnCours selon tourDe
        if (partie.tourDe == 0) {
            menuManager.model.setJoueurEnCours(partie.joueur1);
        } else {
            menuManager.model.setJoueurEnCours(partie.joueur2);
        }

        // Mettre à jour les decks internes du GameController
        menuManager.gameController.deckJ1 = partie.joueur1.deck.copy();
        menuManager.gameController.deckJ2 = partie.joueur2.deck.copy();

        setStatus("✅ Partie « " + id + " » chargée avec succès !");

        // Reconstruire la vue de jeu et afficher
        menuManager.gamePanel.onCombatCommence();
        menuManager.gamePanel.onTourUpdate();
        menuManager.gamePanel.refresh();
        menuManager.showJeuSansRecommancer();
    }

    private void supprimer() {
        String id = listeSauvegardes.getSelectedValue();
        if (id == null) {
            setStatus("⚠️ Sélectionnez une sauvegarde à supprimer.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Supprimer définitivement « " + id + " » ?",
                "Confirmer la suppression",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        // Le Serialiseur ne propose pas de méthode delete explicite,
        // on réécrit le fichier sans cette entrée en sauvegardant une
        // Partie bidon avec le même id... Une vraie suppression nécessite
        // d'ajouter supprimerPartie() dans Serialiseur (voir note ci-dessous).
        // Pour l'instant on délègue la méthode ajoutée dans Serialiseur.
        serialiseur.supprimerPartie(id);
        setStatus("🗑 Sauvegarde « " + id + " » supprimée.");
        rafraichirListe();
    }

    // ─────────────────────────────────────────────────────────────────────────
    //  Utilitaires
    // ─────────────────────────────────────────────────────────────────────────

    private void setStatus(String msg) {
        labelStatus.setText(msg);
    }

    private void dimensionnerBouton(ButtonAgesOfClash b) {
        b.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(220, 45));
        b.setPreferredSize(new Dimension(220, 45));
    }
}
