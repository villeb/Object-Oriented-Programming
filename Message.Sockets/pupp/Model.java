/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pupp;

import java.awt.Color;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author hugo
 */
public class Model {
    
    private int userType;
    private Client client;
    private Server server;
    private PrintWriter outputStream;
    private File messageFile;
    private SAXParserFactory saxFactory = SAXParserFactory.newInstance();
    private SAXParser parser;
    
    public Model(String address,int port, int u){
        userType = u;
        //om man är server
        if (userType == 0){
            server = new Server(address,port);
            
        }else{
            client = new Client(address,port);
        }
        try{
            this.parser = saxFactory.newSAXParser();
        }
        catch(Exception ex){
            System.out.println("Couldn't create Model");
        }

    }
    
    public void sendMessage(String s){
        if (userType == 0){
             try {
                server.send(s);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Couldn't send message.");
            }
        }else {
            try {
                client.send(s);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Couldn't send message.");
            }
        }
       
    }
    public Message receiveMessage(){
        System.out.println("4. received");
        String s;
        if (userType == 0){
            s = server.receive();
        }else{
        s =  client.receive();
        
        
        }
        try{
        return handleInputMessage(s);
        }catch(Exception ex){
            Message err =new Message("något problem, disconnecto","MAX");
            err.setDisconnect();
            return err;
        }
    }
    
    public Message handleInputMessage(String messageString) throws Exception {
        /*
        Parsear ett inkommet string och gör om de till ett message helt enkelt.
        För att kunna göra detta så måste vi
        använda en parser, här används SAXParser. För att använda
        en SAXParser så måste man ha en Handler åt parsern, denna handler är en 
        inre klass. Denna parser är inspirerad av Sven Sandfeldt.
         */

        MyHandler handler = new MyHandler();
        InputStream stream
                = new ByteArrayInputStream(
                        messageString.getBytes(StandardCharsets.UTF_8.name()));

        try {
            this.useParser(stream, handler);
            /*För att använda parsern krävs
            en handler och en inputström.*/
        } catch (Exception e) {

            return null;
        }

        return handler.getMessage();
    }

    private void useParser(InputStream stream, DefaultHandler handler) {
        try {
            parser.parse(stream, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class MyHandler extends DefaultHandler {

    /*
        Denna klass är en klass som vi måste ha för att kunna använda en
        SAXParser. Klassen har ett jobb, vilket är att
        läsa igenom en sträng och spara den information som vi måste ha för
        att skapa ett medelande. Sedan har denna klass
        också en metod som kan hämta medelandet som vi parsat fram.
     */
    private String messageSender;
    private Color color = Color.BLACK;
    private String text;
    private StringBuilder textBuilder = new StringBuilder();
    private boolean txtRdy = false;

    private boolean messageIsDisconnect = false;

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) {
        /*
            Denna metod bestämmer vad vi ska göra när vi stöter på en starttag.
         */

        this.handleStartTag(qName, attributes);

    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        /*
            Denna metod bestämmer vad som ska hända när vi stöter på en sluttag.
         */
        if (txtRdy) {
            text = textBuilder.toString();
        }
    }

    @Override
    public void characters(
            char ch[], int start, int length) throws SAXException {
        /* Denna metod sköter texten mellan text taggarna*/

        textBuilder.append(new String(ch, start, length));
        txtRdy = true;

    }

    public Message getMessage() {
        if (messageIsDisconnect) {
            Message m = new Message(text, messageSender);
            m.setDisconnect();
            return m;
        } else {
            return new Message(text, messageSender, color);
        }
    }

    private void handleStartTag(String tagName, Attributes attributes) {
        /*
            Denna metod känner igen alla taggar som vi kan hantera, och kan
            skicka en tag till rätt hanterare. 
         */

        if (tagName.equalsIgnoreCase("message")) {

            this.handleMessageTag(attributes);
        }

        if (tagName.equalsIgnoreCase("text")) {
            this.handleTextTag(attributes);
        }

        if (tagName.equalsIgnoreCase("disconnect")) {
            System.out.print("Someone disconnected");
            this.messageIsDisconnect = true;
        }

    }

    private void handleTextTag(Attributes attributes) {
        
        color = Color.decode(attributes.getValue("color"));

    }

    private void handleMessageTag(Attributes attributes) {
        
        messageSender = attributes.getValue("sender");

    }

}
