package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import Controller.GameController;
import Controller.MenuController;
import Model.Carte;
import Model.Joueur;
import Model.Personnage;

public class PanelJoueur extends PanelAgesOfClash {
	private static final long serialVersionUID = 1L;

	Joueur joueur;
	PanelAgesOfClash panelBanc = new PanelAgesOfClash();
	LabelTitle labelBanc = new LabelTitle("Banc");
	PanelAgesOfClash panelMain = new PanelAgesOfClash();
	LabelTitle labelMain = new LabelTitle("Main");
	MenuController mc;
	GameController gc;
	Carte carteActive;

	Boolean reverse;

	public PanelJoueur(Boolean reverse, Joueur joueur, MenuController mc, GameController gc) {
		setLayout(new BorderLayout());

		this.joueur = joueur;
		this.reverse = reverse;

		panelBanc.setPreferredSize(new Dimension((int) (400 * 0.3), 600));
		panelBanc.setLayout(new BoxLayout(panelBanc, BoxLayout.Y_AXIS));
		
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.X_AXIS));

		if (reverse) {
			add(panelBanc, BorderLayout.LINE_END);
		} else {
			add(panelBanc, BorderLayout.LINE_START);
		}

		add(panelMain, BorderLayout.SOUTH);

		panelBanc.add(labelBanc);
		panelMain.add(labelMain);

	}

	public void actualiserMain() {
		panelMain.removeAll();
		panelMain.add(labelMain);
		for (Carte c : joueur.main) {
			panelMain.add(c);
		}
		panelMain.repaint();
		panelMain.revalidate();
	}

	public void actualiserBanc() {
		panelBanc.removeAll();
		panelBanc.add(labelBanc);
		for (Carte c : joueur.banc) {
			panelBanc.add(c);
		}
		panelBanc.repaint();
		panelBanc.revalidate();
	}

	public void actualiserActif() {
		for (Component comp : this.getComponents()) {
			if (comp instanceof Personnage) {
				Personnage lacarte=(Personnage) comp;
				if (lacarte.pv<=0) {
					this.remove(lacarte);
				}
			}
		}
		
		if (joueur.actif == null) {
			for (Component comp : this.getComponents()) {
				if (comp instanceof Carte) {
					this.remove((Carte) comp);
				}
			}
		} else {
			carteActive = joueur.actif;
			if (reverse) {
				add(carteActive, BorderLayout.LINE_START);
			} else {
				add(carteActive, BorderLayout.LINE_END);
			}
			repaint();
			revalidate();
		}
	}

	public void actualiserAll() {
		actualiserBanc();
		actualiserMain();
		actualiserActif();
	}

}
