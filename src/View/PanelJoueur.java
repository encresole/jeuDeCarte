package View;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import Model.Personnage;

public class PanelJoueur extends JPanel{
	private static final long serialVersionUID = 1L;
	
	PanelAgesOfClash panelBanc = new PanelAgesOfClash();
	PanelAgesOfClash panelMain = new PanelAgesOfClash();
	
	
	public PanelJoueur() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		add(panelBanc,BorderLayout.LINE_START);
		add(panelMain,BorderLayout.SOUTH);
		panelBanc.add(new Personnage("","Aldric","Sire Aldric l'Indomptable","/images/personnages/Sire Aldric l'indomptable.jpg",null,150,100));
		panelBanc.add(new Personnage("","Kenshi","Kenshi la Lame Silencieuse","/images/personnages/Kenshi la lame silencieuse.jpg",null,110,80));
		panelBanc.add(new Personnage("","AgentFantome","Kenshi la Lame Silencieuse","/images/personnages/Agent Fantome.jpg",null,110,80));
	}
	

}
