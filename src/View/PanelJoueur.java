package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Controller.GameController;
import Controller.MenuController;
import Model.Carte;
import Model.Joueur;
import Model.Palette;
import Model.Personnage;

public class PanelJoueur extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Joueur joueur;
	PanelAgesOfClash panelBanc = new PanelAgesOfClash();
	LabelTitle labelBanc= new LabelTitle("Banc");
	PanelAgesOfClash panelMain = new PanelAgesOfClash();
	LabelTitle labelMain= new LabelTitle("Main");
	MenuController mc;
	GameController gc;
	
	public PanelJoueur(Boolean reverse,Joueur joueur, MenuController mc, GameController gc) {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		
		this.joueur=joueur;
		
		panelBanc.setPreferredSize(new Dimension((int) (400*0.3),600));
		panelBanc.setLayout(new BoxLayout(panelBanc, BoxLayout.Y_AXIS));
		
		Carte carteActive=new Personnage("","AgentFantome","Kenshi la Lame Silencieuse","/images/personnages/Agent Fantome.jpg",mc,110,80);
		if (reverse) {
			add(panelBanc,BorderLayout.LINE_END);
			add(
					carteActive,
					BorderLayout.LINE_START
				);
		} else {
			add(panelBanc,BorderLayout.LINE_START);
			add(
					carteActive,
					BorderLayout.LINE_END
				);
		}
		
		
		add(panelMain,BorderLayout.SOUTH);
		
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

}
