function createXHR() {
    if (window.XMLHttpRequest)    //  Objet standard
    {
        xhr = new XMLHttpRequest();     //  Firefox, Safari, ...
    }
    else if (window.ActiveXObject)      //  Internet Explorer
    {
        xhr = new ActiveXObject("Msxml2.XMLHTTP");
    }
    return xhr;
}

function getSelectValues(id,name)
{
	var liste=document.getElementById(id);
	var values="";
	for(var i=0;i<liste.options.length;i++)
	{
		if(liste.options[i].selected)
		{
			values=values+name+"="+liste.options[i].value+ "&";
		}
	}
	return values;
}

function executerRequeteXML(url, querystring, okFunction, errorFunction)
{
    var xhr = createXHR();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4)
        {
            if (xhr.status == 200)
            {
                okFunction(xhr.responseXML);
            }
            else
            {
                errorFunction(xhr.status);
            }
        }
    };
    
    
    xhr.open("GET", url + "?" + querystring, true);
    xhr.send(null);
}

function executerRequete(url, querystring, okFunction, errorFunction)
{
    var xhr = createXHR();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4)
        {
            if (xhr.status == 200)
            {
                okFunction(xhr.responseText);
            }
            else
            {
                errorFunction(xhr.status);
            }
        }
    };

    xhr.open("GET", url + "?" + querystring, true);
    xhr.send(null);
}