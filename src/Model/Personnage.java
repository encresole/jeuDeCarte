package Model;

public class Personnage extends Carte {
	public int pv;
	public int attaque;
	public int energie;
	public Boolean estActif;
	
	public Personnage(String nom, int pv, int attaque, int energie, Boolean estActif) {
		super(nom);
		this.pv = pv;
		this.attaque = attaque;
		this.energie = energie;
		this.estActif = estActif;
	}
}
