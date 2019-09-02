/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pupp;

/**
 *
 * @author Vilhelm
 */
import java.io.*;
import java.net.*;

public class Server{

    // StrÃ¶mmar fÃ¶r att lÃssa/skriva
    private PrintWriter out;
    private BufferedReader in;
       
    // Sockets till uppkopplingen
    private ServerSocket serverSocket;
    private Socket clientSocket = null;



    public Server(String address, int port){

	// Koppla upp serverns socket
	try {
	    serverSocket = new ServerSocket(port);
	} catch (IOException e) {
	    System.out.println("Could not listen on port: " + port);
	    System.exit(-1);
	}
	
	// Lyssna efter en klient
	try {
	    clientSocket = serverSocket.accept();
	} catch (IOException e) {
	    System.out.println("Accept failed: " + port);
	    System.exit(-1);
	}

	// skapar ett objekt för att kunna få ut text från client
	try{
	    out = new PrintWriter( clientSocket.getOutputStream(), true);
            out.flush();
	}catch(IOException e){
	    System.out.println("getOutputStream failed: " + e);
	    System.exit(1);
	}

	try{
	    in = new BufferedReader(new InputStreamReader(
	            clientSocket.getInputStream()));
	}catch(IOException e){
	    System.out.println("getInputStream failed: " + e);
	    System.exit(1);
	}

	// Kommer vi hit har det gått bra
	// Vi skriver ut IP-adressen till klienten
	System.out.println("Connection Established: " 
			   + clientSocket.getInetAddress());


    }
    
     public void send(String text) throws UnsupportedEncodingException{
        
         System.out.println("SERVER TEXT "+ text);
         String userInput;
         System.out.println(text);

        BufferedReader textIn = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(text.getBytes("UTF-8"))));
      
                                   
	// Läs in från chatbox och skicka till clienten:
        try{
        
	if ((userInput = textIn.readLine()) != null) {
            System.out.println(userInput);
            System.out.println(text);
            out.flush();
	    out.println(userInput);
            out.flush();
            
            }
        }
        catch(Exception e){ 
            System.out.println("Server couldn't send message");
        }
    }
     
     // Tar emot clientens chatbox 
    public String receive(){
        String s = null;
        System.out.println("5. server receive");
        try{
                System.out.println("6. We in here");
                s = in.readLine();
                System.out.println("7. now we out here");
               

        }catch(Exception e){
            System.out.println(e);}
        
        return s;
        
    }
}