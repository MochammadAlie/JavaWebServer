/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ALIE
 */
public class PingPong {
    
    public PingPong(){
        try {
            //Open socket server
            ServerSocket server = new ServerSocket(2020);
            System.out.println("Server is ready, running on port 2020");
            
            //Try accept client
            Socket clientSocket = server.accept();
            System.out.println("Connection from is accepted");
            
            //Make reader & writer for accept & send data to client
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            String inputLine = null;
            
            //In loop scope would read data that send to the client
            while ((inputLine = in.readLine()) != null){
                System.out.println("Received " + inputLine + " from client");
                
                //If have a data that send to the client, it reply with send Pong to the client early 
                out.write("Pong\n");
                System.out.println("Send pong to the client");
                out.flush();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
    }
}
