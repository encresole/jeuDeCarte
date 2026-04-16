package Model;

import java.util.ArrayList;

public class Deck extends ArrayList<Carte>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean ajouter(Carte e) {
		// TODO Auto-generated method stub
		if (this.size()<Model.TAILLEDECK) {
			add(e);
			return true;
		} else {
			System.err.println("Essai d'ajout alors que deck plein");
		}
		return false;
	}
	
	public Deck copy() {
		Deck copy= new Deck();
		for (Carte carte : this) {
			copy.ajouter(carte.copy());
		}
		return copy;
	}
}
