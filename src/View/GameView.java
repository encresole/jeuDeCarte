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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends PanelAgesOfClash {
    private static final long serialVersionUID = 1L;

    MenuManager menuManager;
    ButtonAgesOfClash boutonRetour;
    PanelJoueur panelJ1;
    PanelJoueur panelJ2;
    PanelAgesOfClash panelDesBoutons = new PanelAgesOfClash();

    ButtonAgesOfClash boutonUtiliser;
    ButtonAgesOfClash boutonRetraite;
    ButtonAgesOfClash boutonFinDuTour;

    public GameView(MenuManager menuManager) {
        this.menuManager = menuManager;
        setLayout(new BorderLayout());

        panelJ1 = new PanelJoueur(false, menuManager.model.joueur1, menuManager.menuController);
        panelJ2 = new PanelJoueur(true, menuManager.model.joueur2, menuManager.menuController);

        panelJ1.setPreferredSize(new Dimension(400, 600));
        panelJ2.setPreferredSize(new Dimension(400, 600));

        add(panelJ1, BorderLayout.LINE_START);
        add(panelJ2, BorderLayout.LINE_END);

        panelDesBoutons.setLayout(new BorderLayout());
        add(panelDesBoutons, BorderLayout.SOUTH);

        boutonUtiliser = new ButtonAgesOfClash("Utiliser");
        boutonUtiliser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuManager.menuController.m.gameController.utiliser();
            }
        });

        boutonRetraite = new ButtonAgesOfClash("Retraite");
        boutonRetraite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuManager.menuController.m.gameController.retraite();
            }
        });

        boutonFinDuTour = new ButtonAgesOfClash("Fin du tour");
        boutonFinDuTour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuManager.menuController.m.gameController.finDuTour();
            }
        });

        panelDesBoutons.add(boutonUtiliser, BorderLayout.LINE_START);
        panelDesBoutons.add(boutonRetraite);
        panelDesBoutons.add(boutonFinDuTour, BorderLayout.LINE_END);

        boutonRetour = new ButtonAgesOfClash("Retour");
        boutonRetour.setActionCommand("SHOWMENU");
        boutonRetour.addActionListener(menuManager.menuController);
        panelDesBoutons.add(boutonRetour, BorderLayout.PAGE_END);
    }
}