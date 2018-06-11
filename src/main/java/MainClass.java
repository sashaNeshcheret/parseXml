/**
 * Created by Нещерет on 10.06.2018.
 */

import exceptions.ParseException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigDecimal;

public class MainClass {
    private static final String XML_PATH = "src\\main\\resources\\My.xml";
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, ParseException {
        Person person1 = new Person("sasha", "kyiv", new BigDecimal("1000"), "sa");
        Person person2 = new Person("stepa", "lviv", new BigDecimal("1500"), "sa");
        Person person3 = new Person("valera", "dnipro", new BigDecimal("17000"), "sa");
        createXML(person1, person2, person3);
        System.out.println("dom parser");
        DOMPersonParser domPersonParser = new DOMPersonParser(XML_PATH);
//        domPersonParser.parse();
        System.out.println("sax parser");
        SAXPersonParser saxPersonParser = new SAXPersonParser(XML_PATH);
 //       saxPersonParser.parse();
    }

    public static void createXML(Person ... persons){
        Element root = new Element("catalog");
        Element subRoot = new Element("notebook");
        root.addContent(subRoot);

        for (Person personEntity : persons) {
            Element person = new Element("person");
            person.setAttribute("id", personEntity.getId().toString());
            Element name = new Element("name");
            name.setText(personEntity.getName());
            Element address = new Element("address");
            address.setText(personEntity.getAddress());
            Element cash = new Element("cash");
            cash.setText(personEntity.getCash().toString());
            Element education = new Element("education");
            education.setText(personEntity.getEducation());
            person.addContent(name);
            person.addContent(address);
            person.addContent(cash);
            person.addContent(education);

            subRoot.addContent(person);
        }
        Document doc = new Document(root);
        write(doc);
    }

    public static void write(Document document){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(new File(XML_PATH)));
            XMLOutputter serializer = new XMLOutputter();
            Format f = Format.getPrettyFormat();
            serializer.setFormat(f);
            serializer.output(document, osw);
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

}
