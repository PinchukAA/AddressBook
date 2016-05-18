package application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.soap.Addressing;

import application.model.Person;
import application.model.PersonAddress;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

public class DataParser{

    private static final String ROOT_ELEM_TAG = "root";
    private static Document document;

    private static Document createDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document document = null;
        try {
            document = factory.newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void save(File file, Collection<Person> elements) {
        document = createDocument();

        Element root = document.createElement("root");
        document.appendChild(root);

        Element addressBook = document.createElement("AddressBook");
        root.appendChild(addressBook);

        for (Person person : elements) {
            Element personElement = document.createElement("person");
            personElement.setAttribute("firstName", person.getFirstName());
            personElement.setAttribute("lastName", person.getLastName());
            personElement.setAttribute("surName", person.getSurName());

            Element addressElement = document.createElement("personAddress");
            addressElement.setAttribute("country", person.getAddress().getCountry());
            addressElement.setAttribute("region", person.getAddress().getRegion());
            addressElement.setAttribute("city", person.getAddress().getCity());
            addressElement.setAttribute("street", person.getAddress().getStreet());
            addressElement.setAttribute("houseNumber", String.valueOf(person.getAddress().getHouseNumber()));
            addressElement.setAttribute("pavilionNumber", String.valueOf(person.getAddress().getPavilionNumber()));
            addressElement.setAttribute("flatNumber", String.valueOf(person.getAddress().getFlatNumber()));

            personElement.appendChild(addressElement);
            addressBook.appendChild(personElement);
        }

        try {
            toXML(file, document);
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void toXML(File file, Document doc) throws TransformerException, IOException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(file));
    }

    public static List<Person> loadAddressBook(File fileName) {
        InputStream in = null;
        try {
            in = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XMLGraphLoader loader = new XMLGraphLoader(in);
        try {
            loader.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Person> loadAddressBook = loader.getPersons();

        return loadAddressBook;
    }

    private static class XMLGraphLoader {
        private InputSource source;
        private SAXParser parser;
        private DefaultHandler documentHandler;

        private List<Person> personsList;

        public XMLGraphLoader(InputStream stream) {
            personsList = new LinkedList<>();
            try {
                Reader reader = new InputStreamReader(stream);
                source = new InputSource(reader);
                parser = SAXParserFactory.newInstance().newSAXParser();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            documentHandler = new XMLParser();
        }

        public void parse() throws Exception {
            parser.parse(source, documentHandler);
        }

        public List<Person> getPersons() {
            return personsList;
        }

        class XMLParser extends DefaultHandler {
            public void startElement(String uri, String localName, String qName, Attributes attr) {
                PersonAddress tempPersonAddress;
                Person tempPerson;

                String firstName = null;
                String secondName = null;
                String surName = null;

                List<PersonAddress> personAddressList = new ArrayList<>();
                List<List> personFieldList = new ArrayList<>();
                List<String> fieldList = new ArrayList<>();

                switch (qName){
                    case ("personAddress"):
                        personAddressList.add(parsePersonAddress(attr));
                        break;
                    case ("person"):
                        fieldList = parsePerson(attr);
                        personFieldList.add(fieldList);
                        break;
                    }
                Iterator<PersonAddress> iteratorP = personAddressList.iterator();
                for (Iterator<List> iteratorL = personFieldList.iterator(); iteratorL.hasNext();){
                    iteratorP.hasNext();
                    tempPersonAddress = iteratorP.next();
                    fieldList = iteratorL.next();
                    for (Iterator<String> iteratorS = fieldList.iterator(); iteratorS.hasNext();){
                        firstName = iteratorS.next();
                        System.out.print(firstName);
                        secondName = iteratorS.next();
                        System.out.print(secondName);
                        surName = iteratorS.next();
                        System.out.print(surName);
                    }

                    tempPerson = new Person(firstName, secondName, surName, tempPersonAddress);
                    personsList.add(tempPerson);
                }


            }





                /*
                if(qName.equals("person")){
                        List<String> list = parsePerson(attr);
                        for (Iterator<String> iteratorS = list.iterator(); iteratorS.hasNext();){
                            firstName = iteratorS.next();
                            System.out.print(firstName);
                            secondName = iteratorS.next();
                            System.out.print(secondName);
                            surName = iteratorS.next();
                            System.out.print(surName);
                        }
                        list.removeAll(list);

                        if(qName.equals("personAddress"))
                            tempAddress = parsePersonAddress(attr);

                        Person tempPerson = new Person(firstName, secondName, surName, tempAddress);
                        personsList.add(tempPerson);
                    }
                }*/

            private PersonAddress parsePersonAddress(Attributes attr){
                System.out.print(attr.getValue("houseNumber"));
                PersonAddress tempAddress = new PersonAddress(
                        attr.getValue("country"),
                        attr.getValue("region"),
                        attr.getValue("city"),
                        attr.getValue("street"),
                        Integer.parseInt(attr.getValue("houseNumber")),
                        Integer.parseInt(attr.getValue("pavilionNumber")),
                        Integer.parseInt(attr.getValue("flatNumber"))
                );
                return tempAddress;
            }

            private List<String> parsePerson(Attributes attr) {
                List<String> list = new ArrayList<>();
                list.add(attr.getValue("firstName"));
                list.add(attr.getValue("lastName"));
                list.add(attr.getValue("surName"));
                return list;
            }
        }
    }
}


