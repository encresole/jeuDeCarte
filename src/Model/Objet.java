package Model;

public class Objet extends Carte {
	public Effet effet;

	public Objet(String nom, Effet effet) {
		super(nom);
		this.effet = effet;
	}

}
