import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ClasePrincipal {
public static void main(String[] args) {
	ArrayList<Contacto> lista_contactos=new ArrayList<>();
	Contacto c1=new Contacto("Pepe", "Perez", "pepe@mail.com");
	Contacto c2=new Contacto("Ana", "Gil", "anita@mail.com");
	lista_contactos.add(c1);
	lista_contactos.add(c2);
	Document d=crearXML(lista_contactos);
	try {
		imprimir(d);
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
private static void imprimir(Document doc) throws TransformerException {
	TransformerFactory tf = TransformerFactory.newInstance();
	Transformer transformer = tf.newTransformer();
	transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	StringWriter writer = new StringWriter();
	transformer.transform(new DOMSource(doc), new StreamResult(writer));
	String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
	System.out.println(output);
}
private static Document crearXML(ArrayList<Contacto> lista_contactos) {
	DocumentBuilderFactory fabricante=DocumentBuilderFactory.newInstance();
	Document doc=null;
	try {
		DocumentBuilder builder=fabricante.newDocumentBuilder();
		doc=builder.newDocument();
		Element elemento_agenda=doc.createElement("agenda");
		for (Contacto contacto : lista_contactos) {
			Element elemento_contacto=doc.createElement("contacto");
			
			Element elemento_nombre=doc.createElement("nombre");
			elemento_nombre.setTextContent(contacto.getNombre());
			Element elemento_apellido=doc.createElement("apellido");
			elemento_apellido.setTextContent(contacto.getApellido());
			Element elemento_email=doc.createElement("email");
			elemento_email.setTextContent(contacto.getEmail());
			elemento_contacto.appendChild(elemento_nombre);
			elemento_contacto.appendChild(elemento_apellido);
			elemento_contacto.appendChild(elemento_email);
			elemento_agenda.appendChild(elemento_contacto);
		}
		doc.appendChild(elemento_agenda);
		
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return doc;
}

}
