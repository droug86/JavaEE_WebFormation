package fr.eni_ecole.jee.util;



import java.io.StringWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.eni_ecole.jee.dal.FormationDAO;
import fr.eni_ecole.jee.bean.*;

public class FluxRSSFormations
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  String genereFluxRss() throws Exception{
		StringWriter sw = null;
		//--> construire le document vide
		Document documentXML=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		//--> Ajouter la racine
		Element rss=documentXML.createElement("rss");
		rss.setAttribute("version","2.0");
		documentXML.appendChild(rss);
		//--> Définir le canal
		Element channel=documentXML.createElement("channel");
		rss.appendChild(channel);
		//--> Les propriétés du canal
		Element title, description, link, pubDate;
		title=documentXML.createElement("title");
		title.appendChild(documentXML.createTextNode("TP-JEE"));
		channel.appendChild(title);
		description=documentXML.createElement("description");
		description.appendChild(documentXML.createTextNode("Exemple de flux RSS"));
		channel.appendChild(description);
		link=documentXML.createElement("link");
		link.appendChild(documentXML.createTextNode("http://localhost:8080/TPWeb06"));
		channel.appendChild(link);
		pubDate=documentXML.createElement("pubDate");
		pubDate.appendChild(documentXML.createTextNode(dateJourRFC822()));
		channel.appendChild(pubDate);
		Iterator it=FormationDAO.lister().iterator();
		//.... insérer le détail de toutes les formations
		
		while (it.hasNext()){
			Formation laFormation=(Formation)it.next();
			Element item, ititle, idescription, ipubDate, ilink;
			item=documentXML.createElement("item");
			channel.appendChild(item);
			
			ititle=documentXML.createElement("title");
			item.appendChild(ititle);
			String idEncode=URLEncoder.encode(String.valueOf(laFormation.getId()),"UTF-8");
			ititle.appendChild(documentXML.createTextNode(idEncode));
			
			idescription=documentXML.createElement("description");
			item.appendChild(idescription);
			String libelleEncode=URLEncoder.encode(laFormation.getLibelle(),"UTF-8");
			idescription.appendChild(documentXML.createTextNode(libelleEncode));
			
			ilink=documentXML.createElement("link");
			item.appendChild(ilink);
			ilink.appendChild(documentXML.createTextNode(
					"http://localhost:8080/TPWeb06/index.jsp"));
		
			ipubDate=documentXML.createElement("pubDate");
			item.appendChild(ipubDate);
			Source source=new DOMSource(documentXML);
			sw=new StringWriter();
			Result destination=new StreamResult(sw);
			TransformerFactory.newInstance().newTransformer().transform(source, destination);
			
			
		}

		return sw.toString() ;
	}
	/**
	 * Produire la date du jour au format RFC822
	 * */
	private String dateJourRFC822(){

		return produireRFC822(Calendar.getInstance());
	}
	private String produireRFC822(Calendar leJour){
		DateFormat formatter=
			new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.US);
		String laDateRFC822=formatter.format(leJour.getTime());
		return laDateRFC822;
	}
}










