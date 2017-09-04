/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca_serveur;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import static saca_serveur.radar_serveur.avions;

/**
 *
 * @author MyComputer
 */
public class communiquer_avion extends Thread{
   
        ServerSocket welcomeSocket;
        radar_serveur radar;
     
        public communiquer_avion(radar_serveur radar){
            try{
                welcomeSocket = new ServerSocket(6789);
                welcomeSocket.setSoTimeout(2000);
                this.radar = radar;
                }
            catch(Exception e){}
           
        }

public void run(){    

        while (true)
        {
            try{
                        
                  sleep(2000);
                  // Create the Client Socket
                  Socket clientSocket = welcomeSocket.accept();
                  ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());        
                  String parameters[] = new String[7];
                  parameters = (String[])inFromClient.readObject();
            
               
                  if (Double.parseDouble(parameters[4]) < 200){
                  Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                  radar.jLabel3.setText("Vitesse Trop Faible: Crash de l'avion Numero: " + parameters[0]);
                  }
                  
                  if (Double.parseDouble(parameters[3]) == 0){
                  Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                  radar.jLabel3.setText("L'avion Numero: " + parameters[0] + " s'est écrasé au sol: " );
                  }
                  
                  //Put: replaces existing value, if key exists or creates new key with value if key doesn't exists.
                  radar.avions.put(parameters[0], parameters);
                  
                       
                  }catch (Exception e) {
                      
                  }
    
}
}
}
