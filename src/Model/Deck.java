package Model;

import java.util.ArrayList;

public class Deck extends ArrayList<Carte>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean add(Carte e) {
		// TODO Auto-generated method stub
		if (this.size()<Model.TAILLEDECK) {
			return super.add(e);
		} else {
			System.err.println("Essai d'ajout alors que deck plein");
		}
		return false;
	}
}
