package fr.eni_ecole.jee.bean;

import java.io.*;
import java.util.*;

public class Formation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String libelle;
	private Date dateDebut;
	private Date dateFin;
	private String description;
	
	public Formation(){
		super();
	}
	public Formation(int id, String libelle, Date dateDebut, Date dateFin, String description){
		super();
		setId(id);
		setLibelle(libelle);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setDescription(description);
	}

	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getLibelle() {
		return libelle;
	}
 
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
