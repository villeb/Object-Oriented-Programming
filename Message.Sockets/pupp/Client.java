/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pupp;

import java.io.*;
import java.net.*;


/**
 *
 * @author Vilhelm
 */
public class Client {
    
    // Strömmar för att läsa från/skriva till servern
    private PrintWriter out = null;
    private BufferedReader in = null;
    public Socket clientSocket = null;
    
    public Client(String address, int port){
        
        try {
            clientSocket = new Socket(address, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.flush();
            in = new BufferedReader(new InputStreamReader(
                                        clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.\n" + e);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("sumtinwong");
            System.err.println("Couldn't get I/O for "
                               + "the connection to host.\n" + e);
            System.exit(1);
        }

	// Kommer vi hit har anslutningen gått bra
	System.out.println("Connection successful!");


    }
    
    public void send(String text) throws UnsupportedEncodingException{
        
        
        System.out.println("CLIENT TEXT "+text);
        String userInput;
        

        BufferedReader textIn = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(text.getBytes("UTF-8"))));
      
            
        // Läs in från chatbox och skicka till servern
        try{
	if ((userInput = textIn.readLine()) != null) {
            out.flush();
	    out.println(userInput);
            System.out.println("CLIENT MESSAGE SENT LMAO");
            out.flush();
            }
        }
        catch(Exception e){
            System.out.println("oh shit son sum tin wong");
        }
    }
    
    // tar emot serverns chatbox
    public String receive(){
        
        String s = null;
        System.out.println("client receive");
        try{
                s = in.readLine();
                

        }catch(Exception e){
            System.out.println(e);}
        
        return s;
        
    }
}