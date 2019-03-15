<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<%@ include file="/menu.jspf" %>

	<div id="contenu">
	
	<%
		String animateurId = request.getParameter("identifiant");
		if (animateurId==null) animateurId="";
		String animateurMdP = request.getParameter("motdepasse");
		if (animateurMdP==null) animateurMdP="";
		String messageErreur = (String)request.getAttribute("messageErreur");
		if (messageErreur==null) messageErreur="";
	%>
	
		<form class="connexion" action="<%=request.getContextPath()%>/animateur/ValiderAccesAnimateur" method="post">
		<input type="hidden"  name="typeUtilisateur" value="animateur" />
		<div class="bloc_identifiant">
			<label for="identifiant">Identifiant</label>
			<input class="champtexte" type="text" id="identifiant" name="identifiant" value="<%=animateurId%>"/>
		</div>
		<div class="bloc_motdepasse">
			<label for="motdepasse">Mot de passe</label>
			<input class="champtexte" type="text"  id="motdepasse" name="motdepasse" value="<%=animateurMdP%>"/>
		</div>
		<div class="bloc_connexion">
			<input type="submit" id="seconnecter" value="Se connecter" />
		</div>
		</form>
		
		<% if (!"".equals(messageErreur)) { %>
		<div><p><%=messageErreur%></p></div>
		<% } %>

 	</div> 	
	
	<%@ include file="/pieddepage.jspf" %>
	
</div>
</body>
</html>
