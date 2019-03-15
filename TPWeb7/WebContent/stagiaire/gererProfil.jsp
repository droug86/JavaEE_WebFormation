<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java"
		 contentType="text/html; charset=ISO-8859-1"
    	 pageEncoding="ISO-8859-1"
    	 import ="fr.eni_ecole.jee.bean.*, java.util.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type"/>
  <title>TP Web - Gestion de mon profil</title>
  <link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/theme/basique/style.css" type="text/css"/>
</head>
<body>
<div id="page">
 
	<div id="entete">
		<h1>TP Web - Gestion de mon profil</h1>
	</div>

<%@ include file="/stagiaire/menu.jspf" %>

	<div id="contenu">
	
	<%@ include file="/stagiaire/gererProfil.jspf" %>
	</div>
	
	<%@ include file="/pieddepage.jspf" %>
	
	</div>
</body>
</html>