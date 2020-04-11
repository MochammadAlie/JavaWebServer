/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ALIE
 */
public class WebServer {

    //Declaration & Inizialitation variable
    ServerSocket sSocket;
    final int port = 1813;
    
    protected void start(){
        System.out.println("Server is running on port " + port);
        System.out.println("Press ctrl * c to stop");
        
        try {
            //Tahap bind(), create socket
            sSocket =  new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("Server error : " + e);
            return;
        }
        
        System.out.println("Waiting for connection");
        for(;;){
            try {
                //Listen section, waiting connection
                Socket remote = sSocket.accept();
                
                //Accept request()
                System.out.println("Connection, sending data");
                BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                
                PrintWriter out = new PrintWriter(remote.getOutputStream());
                
                //reading request
                String str =  ".";
                
                while (!str.equals(""))
                    str = in.readLine();
                
                //Send response and send HTTP header
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type : text/html");
                out.println("Server.Bot");
                
                //Limit sending header marked with empty row
                out.println("");
                
                //Sending HTML Page
                out.println("<h1>Welcome to the Java Web Server</h1>");
                out.println("<blink>Page succesfully loaded</blink>");
                out.flush();
                
                remote.close();
                
            } catch (Exception e){
                System.out.println("Server error : " + e);
            }
        }
    }
    
    public static void main(String[] args) {
       WebServer webServer = new WebServer();
       webServer.start();
    }
}
