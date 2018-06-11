
import exceptions.ParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class DOMPersonParser extends MedicineParser {
    private Document doc;
    static Logger log = Logger.getLogger("DOMPersonParser");

    public DOMPersonParser(String xmlPath) throws ParserConfigurationException, IOException, SAXException {
        this.xmlPath = xmlPath;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        File file = new File(xmlPath);
        doc = db.parse(file);
    }

    @Override
    public void parse() throws ParseException, NumberFormatException {
        Element root = doc.getDocumentElement();
        Node subRoot = root.getChildNodes().item(1);
        if (subRoot.getNodeName().equals("notebook")) {
            NodeList listPersons = subRoot.getChildNodes();

            for (int i = 0; i < listPersons.getLength(); i++) {

                Person person = new Person();
                Element personElement = (Element) listPersons.item(i);

                person.setId(Integer.parseInt(personElement.getAttribute("id")));
                person.setName(personElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
                person.setAddress(personElement.getElementsByTagName("address").item(0).getFirstChild().getNodeValue());
                person.setCash(new BigDecimal(personElement.getElementsByTagName("cash").item(0).getFirstChild().getNodeValue()));
                person.setEducation(personElement.getElementsByTagName("education").item(0).getFirstChild().getNodeValue());
                resultPersons.add(person);
                for(Person person1 : resultPersons){
                    System.out.println(person1.getName());
                }
            }
        }
    }
}
