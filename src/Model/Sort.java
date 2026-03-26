package Model;


import Controller.MenuController;

public class Sort extends Carte  {
	private static final long serialVersionUID = 1L;
	/*
	 * On fera une classe effet avec un dictionnaire de tout les effets avec comme clé le nom de
	 * l'effet et comme valeur une fonction ou sinon plein de if avec ce qui ce passe si
	 * effet == celui qu'on veut ou sinon on si c'est juste un truc qui donne des pv ou qui rajoute
	 * des dégats on le met dans la variable effet
	 */
	public Effet effet;
	public Model.Faction faction;
	public String nomEffet;
	public String description;

	public Sort(String id, String nom, String image, MenuController mc, Effet effet) {
		super(id, nom, nom, image, mc);
		this.effet = effet;
	}

	@Override
	public Carte copy() {
		return new Sort(id, nom, cheminImage, mc, effet);
	}
}