package Model;

public class Objet extends Carte {
	private static final long serialVersionUID = 1L;
	
	public Effet effet;

	public Objet(String nom, Effet effet) {
		super(nom);
		this.effet = effet;
	}

}
