function err(status)
{
    alert("erreur Traitement AjaxServlet :" + status);
}
function ok(resultat)
{
	
	   var i = 0;
	   var res=resultat.split("|");
	   
	    document.getElementById("nonInscrit").innerHTML = "";
	    document.getElementById("nonInscrit").innerHTML= res[0];
	    document.getElementById("inscrit").innerHTML = "";
	    document.getElementById("inscrit").innerHTML = res[1];
	    verif();
}
function verif()
{
	{
		if (document.getElementById("nonInscrit").length==0)
		document.getElementById("add").disabled=true;
		else
		document.getElementById("add").disabled=false;
		if (document.getElementById("inscrit").length==0)
			document.getElementById("suppr").disabled=true;
		else
		document.getElementById("suppr").disabled=false;
	}	
}