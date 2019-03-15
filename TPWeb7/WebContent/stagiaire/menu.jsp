<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>TP Web - Menu Stagiaire</title>
<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/theme/basique/style.css" type="text/css"/>
</head>
<body>
<div id="page">

	<div id="entete">
		<h1>TP Web - Menu Stagiaire</h1>
	</div>

	<%@ include file="/stagiaire/menu.jspf" %>

	<div id="contenu">
	<jsp:useBean id="stagiaireConnecte" class="fr.eni_ecole.jee.bean.Stagiaire" scope="session" />
	<p>
	Bonjour
	<jsp:getProperty property="prenom" name="stagiaireConnecte"/>
	<jsp:getProperty property="nom" name="stagiaireConnecte"/>	
	</p>
	</div>
	
	<%@ include file="/pieddepage.jspf" %>
		
</div>

</body>
</html>