package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.odjectClass.Animal;
import org.example.odjectClass.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import java.io.*;
import javax.xml.stream.*;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;



public class App 
{
    public static void main( String[] args )
    {
        //setTheFirstAndSecondTask();
        //setTheThirdTask();
        setTheStarTask();
    }



    //Task 1 and 2.
    // 1 - Написать программу для парсинга xml документа. Необходимо распарсить xml документ и
    //содержимое тегов line записать в другой документ. Исходный файл - inputTask1
    // 2 - Распарсить xml файл используя StAX парсер
    public static void setTheFirstAndSecondTask(){

        String outputFilePath = " ";

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader
                    (new FileInputStream("src/filesInput/inputTask1.xml"));
            Book book = null;

            while(reader.hasNext()){
                int event = reader.next();
                if (event == XMLStreamReader.START_ELEMENT) {
                    if (reader.getLocalName().equals("sonnet")) {
                        book = new Book("", "", "","",0,0);
                    } else if (reader.getLocalName().equals("title")) {
                        book.title = reader.getElementText();
                    } else if (reader.getLocalName().equals("lastName")) {
                        book.lastName = reader.getElementText();
                    } else if (reader.getLocalName().equals("firstName")) {
                        book.firstName = reader.getElementText();
                    } else if (reader.getLocalName().equals("nationality")) {
                        book.nationality = reader.getElementText();
                    } else if (reader.getLocalName().equals("yearOfBirth")) {
                        book.yearOfBirth = Integer.parseInt(reader.getElementText());
                    } else if (reader.getLocalName().equals("yearOfDeath")) {
                        book.yearOfDeath = Integer.parseInt(reader.getElementText());
                    } else if (reader.getLocalName().equals("line")){
                        reader.next();
                        book.addLine(reader.getText());
                    }

                }
            }

            reader.close();
            System.out.println(book.toString());

            outputFilePath = "src/filesInput/" + book.firstName + "_" + book.lastName + "_" + book.title + ".txt";
            writeToFile(outputFilePath, book.getSonnetLines());

        }catch (Exception e){
            System.out.println("Exception " + e.getMessage());
        }

    }


    private static void writeToFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    // Task 3. Десериализовать JSON-строку в объект Java с использованием
    // библиотеки Jackson и вывести поля объекта в консоль. (Класс Animal)
    public static void setTheThirdTask(){

        String jsonFormat = "{\"type\":\"Cat\", \"name\":\"Murka\", \"age\":5 }";

        ObjectMapper mapper = new ObjectMapper();

        try{

            Animal animal = mapper.readValue(jsonFormat.getBytes(),Animal.class);
            System.out.println(animal.toString());
            System.out.println("-----------------------------------");
            System.out.println("Type: " + animal.getType());
            System.out.println("Name: " + animal.getName());
            System.out.println("Age: " + animal.getAge());


        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }




    }


    //Task *--- если с консоли введено значение 1 распарсить документ с помощью SAX,
    // если с консоли введено значение 2 - распарсить документ с помощью DOM
    public static void setTheStarTask(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 1 for SAX, enter 2 for DOM: ");
        int operation = scanner.nextInt();
        System.out.println("--------------");

        if (operation == 1) {
            setSaxParser();
        } else if (operation == 2) {
            setDomParser();
        } else {
            System.out.println("Error:There is no such operation");
        }

        scanner.close();

    }

    public static void setSaxParser(){

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean name = false;
                boolean species = false;
                boolean habitat = false;
                boolean diet = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    if (qName.equalsIgnoreCase("name")) {
                        name = true;
                    } else if (qName.equalsIgnoreCase("species")) {
                        species = true;
                    } else if (qName.equalsIgnoreCase("habitat")) {
                        habitat = true;
                    } else if (qName.equalsIgnoreCase("diet")) {
                        diet = true;
                    }

                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (name) {
                        System.out.println("Name: " + new String(ch, start, length));
                        name = false;
                    } else if (species) {
                        System.out.println("Species: " + new String(ch, start, length));
                        species = false;
                    } else if (habitat) {
                        System.out.println("Habitat: " + new String(ch, start, length));
                        habitat = false;
                    } else if (diet) {
                        System.out.println("Diet: " + new String(ch, start, length));
                        System.out.println("--------------");
                        diet = false;
                    }
                }

            };


            saxParser.parse("src/filesInput/taskStarAnimal.xml", handler);


        } catch (Exception e) {
            e.printStackTrace();
        }




    }


    public static void setDomParser(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/filesInput/taskStarAnimal.xml");

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("animal");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String species = element.getElementsByTagName("species").item(0).getTextContent();
                String habitat = element.getElementsByTagName("habitat").item(0).getTextContent();
                String diet = element.getElementsByTagName("diet").item(0).getTextContent();

                System.out.println("Animal " + (i + 1) + ":");
                System.out.println("Name: " + name);
                System.out.println("Species: " + species);
                System.out.println("Habitat: " + habitat);
                System.out.println("Diet: " + diet);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
