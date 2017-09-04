/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avion;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.ServerSocket;

/**
 *
 * @author MyComputer
 */
public class communiquer_controleur extends Thread{
    
        ServerSocket welcomeSocket;
        class_avion inst_avion;
     
        public communiquer_controleur(class_avion inst_avion,Integer num_port){
            try{
                this.inst_avion = inst_avion;
                welcomeSocket = new ServerSocket(num_port);
                welcomeSocket.setSoTimeout(1500);
                }
            catch(Exception e){}
           
        }
    
        public void run(){
              
                    try{
                    // Create the Client Socket
                    Socket clientSocket = welcomeSocket.accept();

                    ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());
                    String parameters[] = new String[3];
                    parameters = (String[])inFromClient.readObject();    

                    inst_avion.changer_cap(Integer.valueOf(parameters[0]));
                    inst_avion.changer_vitesse(Integer.valueOf(parameters[1]));
                    inst_avion.changer_altitude(Integer.valueOf(parameters[2]));  
                    }catch(Exception e) {
                    }
                }
        }
  
