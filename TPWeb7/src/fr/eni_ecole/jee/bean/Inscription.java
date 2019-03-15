package fr.eni_ecole.jee.bean;

import java.io.Serializable;
import java.util.Date;

public class Inscription implements Serializable
{

	private int stagiaire;
	private int formation;
	private Date dateInscription;
	

	public Inscription() 
	{
		
	}


	/**
	 * @return the stagiaire
	 */
	public final int getStagiaire() {
		return stagiaire;
	}


	/**
	 * @param stagiaire the stagiaire to set
	 */
	public final void setStagiaire(int stagiaire) {
		this.stagiaire = stagiaire;
	}


	/**
	 * @return the formation
	 */
	public final int getFormation() {
		return formation;
	}


	/**
	 * @param formation the formation to set
	 */
	public final void setFormation(int formation) {
		this.formation = formation;
	}


	/**
	 * @return the dateInscription
	 */
	public final Date getDateInscription() {
		return dateInscription;
	}


	/**
	 * @param dateInscription the dateInscription to set
	 */
	public final void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	
	

}
