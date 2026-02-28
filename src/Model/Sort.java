package Model;

public class Sort extends Carte  {
	/*
	 * On fera une classe effet avec un dictionnaire de tout les effets avec comme clé le nom de
	 * l'effet et comme valeur une fonction ou sinon plein de if avec ce qui ce passe si
	 * effet == celui qu'on veut ou sinon on si c'est juste un truc qui donne des pv ou qui rajoute
	 * des dégats on le met dans la variable effet
	 */
	public Effet effet;

	public Sort(String nom, Effet effet) {
		super(nom);
		this.effet = effet;
	}
}
