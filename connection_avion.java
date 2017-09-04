/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca_serveur;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class connection_avion {
  
  private static HashMap<String,Boolean> connection = new HashMap<String,Boolean>();
  Socket clientSocket;
         
  public synchronized void connect(Integer num_port,String num_vol,String host_name){
      
      if (connection.containsKey(num_vol)){
                    
          if ((Boolean)connection.get(num_vol)){
          
          try{
              JOptionPane.showMessageDialog(null, "L'avion est connectée à un autre controleur. Vous êtes en état d'attente");
              return;
           }catch (Exception e){}
          }
      }
          
      try
        {
         clientSocket = new Socket(host_name, num_port);
         JOptionPane.showMessageDialog(null, "Vous etes connecté a l'avion.");
          
           }catch (Exception e ) {}
        connection.put(num_vol,true);
  
  }
  
  public synchronized void disconnect(String num_vol,String host_name)
  {
    connection.put(num_vol,false);
    try{
       clientSocket.close();
       JOptionPane.showMessageDialog(null, "Déconnexion réussie");
        }
   catch (Exception e ) {}
   
    notifyAll();
  
  }
  
  public void envoyer_information(Integer num_port,String num_vol,String host_name,String cap,String vitesse,String altitude){
      
      if (connection.containsKey(num_vol)){
      
      if ((Boolean)connection.get(num_vol)){
            try
            {
                clientSocket.close();
                clientSocket = new Socket(host_name, num_port);
                ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
    
                String param[] = new String[3];
                param[0] = cap;
                param[1] = vitesse;
                param[2] = altitude;
                
                outToServer.writeObject(param);
                JOptionPane.showMessageDialog(null, "Informations bien envoyées.");
                 return;   
           }catch (Exception e ) {System.out.println(e.getMessage());}
      
        }
  
      }
      
      JOptionPane.showMessageDialog(null, "Tu n'est pas connecté à l'avion.");
   
}
 
}
