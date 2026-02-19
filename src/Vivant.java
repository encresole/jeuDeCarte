import java.util.ArrayList;

public class Vivant extends Carte {
	private String nom;
	private int pv;
	private ArrayList<Capacite> capacites= new ArrayList<Capacite>();
	
	public Vivant(String nom, int pv, Capacite capacite1) {
		this.nom=nom;
		this.pv=pv;
	}
	
	public Vivant(String nom, int pv, Capacite capacite1,Capacite capacite2) {
		this.nom=nom;
		this.pv=pv;
	}
	
	private void utiliserCapacite(Capacite capacite,Vivant cible) {
		capacite.utiliserCapacite(cible);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public ArrayList<Capacite> getCapacites() {
		return capacites;
	}

	public void setCapacites(ArrayList<Capacite> capacites) {
		this.capacites = capacites;
	}
	
	
}
