package fr.eni_ecole.jee.bean;

public class Animateur extends Utilisateur {

	private static final long serialVersionUID = 1L;

	public Animateur() {
		super();
	}

	public Animateur(int id, String nom, String prenom, String motDePasse,	String email) {
		super(id, nom, prenom, motDePasse, email);
	}

	public Animateur(int id) {
		super(id);
	}

}
