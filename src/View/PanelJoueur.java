package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Controller.MenuController;
import Model.Carte;
import Model.Joueur;
import Model.Personnage;

public class PanelJoueur extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Joueur joueur;
	PanelAgesOfClash panelBanc = new PanelAgesOfClash();
	PanelAgesOfClash panelMain = new PanelAgesOfClash();
	MenuController mc;
	
	public PanelJoueur(Boolean reverse,Joueur joueur, MenuController mc) {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		
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
		
		panelMain.add(new Personnage("","Aldric","Sire Aldric l'Indomptable","/images/personnages/Sire Aldric l'indomptable.jpg",mc,150,100));
		panelMain.add(new Personnage("","Kenshi","Kenshi la Lame Silencieuse","/images/personnages/Kenshi la lame silencieuse.jpg",mc,110,80));
		panelMain.add(new Personnage("","AgentFantome","Kenshi la Lame Silencieuse","/images/personnages/Agent Fantome.jpg",mc,110,80));
		
		panelBanc.add(new Personnage("","Aldric","Sire Aldric l'Indomptable","/images/personnages/Sire Aldric l'indomptable.jpg",mc,150,100));
		panelBanc.add(new Personnage("","Kenshi","Kenshi la Lame Silencieuse","/images/personnages/Kenshi la lame silencieuse.jpg",mc,110,80));
		panelBanc.add(new Personnage("","AgentFantome","Kenshi la Lame Silencieuse","/images/personnages/Agent Fantome.jpg",mc,110,80));
	}
	
	

}
