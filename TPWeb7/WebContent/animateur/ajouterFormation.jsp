<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java"
		 contentType="text/html; charset=ISO-8859-1"
    	 pageEncoding="ISO-8859-1"
    	 import ="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type"/>
  <title>TP Web - Acces Animateur</title>
  <link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/theme/basique/style.css" type="text/css"/>
</head>
<body>
<div id="page">

	<div id="entete">
		<h1>TP Web - Acces Animateur</h1>
	</div>

<%@ include file="/animateur/menu.jspf" %>

	<div id="contenu">
	

	<%@ include file="/animateur/gestionFormation.jspf" %>
	
	<br /> 
	
	<jsp:useBean id="formationCourante" class="fr.eni_ecole.jee.bean.Formation" scope="session" />
	<%
	// Formatage des dates
	DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
	String dateDebut = (formationCourante.getDateDebut()!=null)?df2.format(formationCourante.getDateDebut()):"";
	String dateFin = (formationCourante.getDateFin()!=null)?df2.format(formationCourante.getDateFin()):"";
	%>

	<form action="<%=request.getContextPath() %>/animateur/gestionFormations" name="formAjoutFormation" method="post" >
	
	<div>
	<label class="formLabel" >Libelle :</label>
	<input type="text" name="tLibelle" size="30" value="<jsp:getProperty property="libelle" name="formationCourante"/>"/>
	</div>
	
	<div>
	<label class="formLabel">Debut de formation :</label>
	<input type="text" name="tDebut" size="10" value="<%=dateDebut%>"/>
	</div>	
	
	<div>
	<label class="formLabel">Fin de formation :</label>
	<input type="text" name="tFin" size="10" value="<%= dateFin%>"/>
	</div>
	
	<div>
	<label class="formLabel">Description :</label>
	<input type="text" name="tDescription" size="100" value="<jsp:getProperty property="description" name="formationCourante"/>"/>
	</div>
	
	<input type="submit" name="bEnregistrer" value="Enregistrer"/>
	</form>
 	</div>
	
	<%@ include file="/pieddepage.jspf" %>
	
	</div>
</body>
</html>