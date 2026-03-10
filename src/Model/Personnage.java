package Model;

public class Personnage extends Carte {
	private static final long serialVersionUID = 1L;
	
	public int pv;
	public int attaque;
	public int energie;
	public Boolean estActif;
	
	public Personnage(String nom, int pv, int attaque) {
		super(nom);
		this.pv = pv;
		this.attaque = attaque;
		this.energie = 100;
		this.estActif = false;
	}
}
