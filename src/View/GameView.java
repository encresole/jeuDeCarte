package View;

/**
 * GameView — Vue principale du combat
 *
 * Ce qui a été fait :
 * - Les 3 boutons du bas (Utiliser, Retraite, Fin du tour) sont branchés
 *   sur GameController via des ActionListener anonymes
 * - Le bouton Retour reste branché sur MenuController comme avant
 * - PanelJoueur reçoit maintenant le MenuController en 3ème paramètre (mis à jour)
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Model.Joueur;

public class GameView extends PanelAgesOfClash {
    private static final long serialVersionUID = 1L;

    MenuManager menuManager;
    ButtonAgesOfClash boutonRetour;
    PanelJoueur panelJ1;
    PanelJoueur panelJ2;
    PanelAgesOfClash panelDesBoutons = new PanelAgesOfClash();
    PanelAgesOfClash panelDesActions = new PanelAgesOfClash();
    PanelAgesOfClash panelLabel= new PanelAgesOfClash();
	LabelTitle indicateurTour;
	LabelTitle indicateurCptTour;
    ButtonAgesOfClash boutonUtiliser;
    ButtonAgesOfClash boutonRetraite;
    ButtonAgesOfClash buttonBanc;
    ButtonAgesOfClash buttonActif;
    ButtonAgesOfClash boutonAttaquer;
    ButtonAgesOfClash boutonFinDuTour;
    PanelAgesOfClash panelHistorique = new PanelAgesOfClash();
    JLabel historiqueLabel= new JLabel("historique");
    ButtonAgesOfClash boutonRetourMenu;

    public GameView(MenuManager menuManager) {
        this.menuManager = menuManager;
        setLayout(new BorderLayout());
        
        
		indicateurTour = new LabelTitle("TOUR DE ");
		indicateurCptTour=new LabelTitle("TOUR N");
		
		panelLabel.add(indicateurTour);
		panelLabel.add(indicateurCptTour);
		
		add(panelLabel, BorderLayout.NORTH);
		
		
        panelJ1 = new PanelJoueur(false, menuManager.model.joueur1, menuManager.menuController, menuManager.gameController);
        panelJ2 = new PanelJoueur(true, menuManager.model.joueur2, menuManager.menuController, menuManager.gameController);

        panelJ1.setPreferredSize(new Dimension(400, 600));
        panelJ2.setPreferredSize(new Dimension(400, 600));

        add(panelJ1, BorderLayout.LINE_START);
        add(panelJ2, BorderLayout.LINE_END);

        panelDesBoutons.setLayout(new BorderLayout());
        add(panelDesBoutons, BorderLayout.SOUTH);

        boutonUtiliser = new ButtonAgesOfClash("Utiliser");
        boutonUtiliser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuManager.gameController.utiliser();
            }
        });

        boutonRetraite = new ButtonAgesOfClash("Retraite");
        boutonRetraite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuManager.gameController.retraite();
            }
        });
        boutonAttaquer= new ButtonAgesOfClash("Attaquer");
        boutonAttaquer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuManager.gameController.attaquer();
            }
        });
        
        buttonBanc = new ButtonAgesOfClash("Placer sur le banc");
        buttonBanc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuManager.gameController.placerSurBanc();
			}
		});
        
        buttonActif= new ButtonAgesOfClash("Placer sur le poste actif");
        buttonActif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuManager.gameController.placerEnActif();
			}
		});
        
        boutonFinDuTour= new ButtonAgesOfClash("Fin du tour");
        boutonFinDuTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuManager.gameController.finDuTour();
			}
		});
        
        panelHistorique.add(historiqueLabel);
        historiqueLabel.setForeground(Color.WHITE);
        add(panelHistorique);
        
        panelDesActions.add(boutonAttaquer);
        panelDesActions.add(boutonUtiliser);
        panelDesActions.add(boutonRetraite);
        panelDesActions.add(buttonBanc);
        panelDesActions.add(buttonActif);
        panelDesActions.add(boutonFinDuTour);

        // Bouton fin de partie — masqué jusqu'à la victoire
        boutonRetourMenu = new ButtonAgesOfClash("↩ RETOUR AU MENU PRINCIPAL");
        boutonRetourMenu.setActionCommand("SHOWMENU");
        boutonRetourMenu.addActionListener(menuManager.menuController);
        boutonRetourMenu.setVisible(false);
        panelDesActions.add(boutonRetourMenu);

        panelDesBoutons.add(panelDesActions);
        
        boutonRetour = new ButtonAgesOfClash("Retour");
        boutonRetour.setActionCommand("SHOWMENU");
        boutonRetour.addActionListener(menuManager.menuController);
        panelDesBoutons.add(boutonRetour, BorderLayout.PAGE_END);
        
        panelJ1.actualiserAll();
    	panelJ2.actualiserAll();
    }
    
    public void refresh() {
    	panelJ1.actualiserAll();
    	panelJ2.actualiserAll();
    	refreshText();
    	
    	String historique=menuManager.model.gameController.getCombat().getHistorique();
    	System.out.println("=========debut=========="+historique+"=======================fin=================");
    	historiqueLabel.setText(historique);
    }
    
    public void onCombatCommence() {
    	reinitialiserBoutons();
    	panelJ1.actualiserAll();
    	panelJ2.actualiserAll();
    	refreshText();
    	indicateurTour.setText("TOUR DE JOUEUR "+((int)menuManager.model.partieEnCours.tourDe+1));
    	if (menuManager.model.partieEnCours.tour<2) {
    		indicateurCptTour.setText("TOUR DE PREPARATION");
    	} else {
    		indicateurCptTour.setText("TOUR N°"+((int)menuManager.model.partieEnCours.tour-1 ));
    	}
    }
    
    public void onTourUpdate() {
    	if (menuManager.model.partieEnCours.tour<2) {
    		boutonUtiliser.setEnabled(false);
    		boutonAttaquer.setEnabled(false);
    		boutonRetraite.setEnabled(false);
    	} else {
			boutonUtiliser.setEnabled(true);
			boutonAttaquer.setEnabled(true);
			boutonRetraite.setEnabled(true);
		}
    }
    
    public void refreshText() {
    	indicateurTour.setText("TOUR DE JOUEUR "+((int)menuManager.model.partieEnCours.tourDe+1));
    	if (menuManager.model.partieEnCours.tour<2) {
    		indicateurCptTour.setText("TOUR DE PREPARATION");
    	} else {
    		indicateurCptTour.setText("TOUR N°"+((int)menuManager.model.partieEnCours.tour-1));
    	}
    }

	public void finPartie(Joueur joueurGagnant) {
		indicateurTour.setText(joueurGagnant + " GAGNE LA PARTIE !");
		indicateurCptTour.setText("Partie terminee !");
		// Desactiver tous les boutons d'action
		boutonAttaquer.setEnabled(false);
		boutonUtiliser.setEnabled(false);
		boutonRetraite.setEnabled(false);
		boutonFinDuTour.setEnabled(false);
		buttonBanc.setEnabled(false);
		buttonActif.setEnabled(false);
		// Afficher le bouton retour menu
		boutonRetourMenu.setVisible(true);
		repaint();
		revalidate();
		panelJ1.actualiserActif();
		panelJ2.actualiserActif();
	}

	/** Remet les boutons dans leur etat initial quand une nouvelle partie commence */
	public void reinitialiserBoutons() {
		boutonAttaquer.setEnabled(true);
		boutonUtiliser.setEnabled(true);
		boutonRetraite.setEnabled(true);
		boutonFinDuTour.setEnabled(true);
		buttonBanc.setEnabled(true);
		buttonActif.setEnabled(true);
		boutonRetourMenu.setVisible(false);
	}
    
}