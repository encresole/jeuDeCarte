
public class Capacite {
	
	private String nom;
	private int degats;
	
	public Capacite(String nom, int degats) {
		this.nom=nom;
		this.degats=degats;
	}
	
	public void utiliserCapacite(Vivant cible) {
		cible.setPv(degats);
	}
}
