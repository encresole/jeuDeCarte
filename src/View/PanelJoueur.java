package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Model.Joueur;
import Model.Palette;
import Model.Personnage;

public class PanelJoueur extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Joueur joueur;
	JPanel panelBanc = new JPanel();
	JPanel panelMain = new JPanel();
	
	
	public PanelJoueur(Boolean reverse,Joueur joueur) {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		
		panelBanc.setPreferredSize(new Dimension((int) (400*0.3),600));
		panelBanc.setLayout(new BoxLayout(panelBanc, BoxLayout.Y_AXIS));
		
		
		panelBanc.setBackground(Palette.BUTTON_TEXT);
		if (reverse) {
			add(panelBanc,BorderLayout.LINE_END);
		} else {
			add(panelBanc,BorderLayout.LINE_START);
		}
		
		
		panelBanc.add(new Personnage("","Aldric","Sire Aldric l'Indomptable","/images/personnages/Sire Aldric l'indomptable.jpg",null,150,100));
		panelBanc.add(new Personnage("","Kenshi","Kenshi la Lame Silencieuse","/images/personnages/Kenshi la lame silencieuse.jpg",null,110,80));
		panelBanc.add(new Personnage("","AgentFantome","Kenshi la Lame Silencieuse","/images/personnages/Agent Fantome.jpg",null,110,80));
	}
	

}
