import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SAXPersonParser extends DefaultHandler {



    private SAXParserFactory factory;
    private SAXParser parser;
    private String xmlPath;
    private List<Person> resultPerson;
    private String elementName;
    private String name;
    private Person person;



    public SAXPersonParser(String xmlPath) throws ParserConfigurationException, SAXException {
        this.xmlPath = xmlPath;

        factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
    }

    public List<Person> getResultPersons() {
        return resultPerson;
    }

    public void setResultMedicine(List<Person> resultPerson) {
        this.resultPerson = resultPerson;
    }

    public void parse() throws IOException, SAXException {
        parser.parse(new File(xmlPath), this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        Person person = null;
        switch (elementName) {
            case "notebook":
                resultPerson = new ArrayList();
                break;
            case "person":
                person = new Person();
            case "name":
                person.setName(new String(ch, start, length));
                break;
            case "address":
                person.setAddress(new String(ch, start, length));
                break;
            case "cash":
                person.setCash(new BigDecimal(new String(ch, start, length)));
                break;
            case "education":
                person.setEducation(new String(ch, start, length));
                break;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "person":
                resultPerson.add(person);

                break;
        }
        elementName = "";
    }
}
