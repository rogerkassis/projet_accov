/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avion;

/**
 *
 * @author MyComputer
 */

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

public class class_avion  {
    Socket clientSocket;
    String param[] = new String[8];
    int vt_max    = 1000;
    int vt_min    = 200;
    long pause     = 2000;
    int alt_min   = 0;
    int alt_max   = 20000;
    static int i = 0;
       
       boolean ouvrir_communication(String server_name,int port_number)
    {
           try
        {
            clientSocket = new Socket(server_name, port_number);
            return true;
         }catch (Exception e ) {return false;}
                     
    }
       
        void fermer_communication() {
            try{
              clientSocket.close();
            }
            catch (Exception e ) {}
        
    }
        void envoyer_caracteristiques(String num_vol,Integer num_port)
        {
            try
            {
                ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
    
                param[0] = num_vol;
                param[6]= InetAddress.getLocalHost().getHostName();
                param[7] = String.valueOf(num_port);
                outToServer.writeObject(param);
                        
           }catch (Exception e ) {System.out.println(e.getMessage());}
        }
        
    public void initialiser_avion(){
    try{
        // intialisation des paramétres de l'avion
        double x,y,altitude,cap,vitesse;
        DecimalFormat df = new  DecimalFormat ("0.##");
        String num_vol;

        x = 1000 + Math.random() % 1000;
        x = Math.floor(x*100)/100;
        y = 1000 + Math.random() % 1000;
        y = Math.floor(y*100)/100;
        altitude = 900 + Math.random() % 100;
        altitude = Math.floor(altitude*100)/100;
        cap = Math.random() % 360;
        cap = Math.floor(cap*100)/100;
        vitesse = 600 + Math.random() % 200;
        vitesse = Math.floor(vitesse*100)/100;
        
        // initialisation du numero de l'avion : chaine de 5 caract�res 
        // formée de 2 lettres puis 3 chiffres
        Random randomer = new Random();
        char [] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int indice = randomer.nextInt( alphabet.length );
        int indice1 = randomer.nextInt( alphabet.length );
        num_vol  =  String.valueOf(alphabet[ indice ]) + String.valueOf(alphabet[ indice1 ]) + String.valueOf(randomer.nextInt(9)) + String.valueOf(randomer.nextInt(9)) + String.valueOf(randomer.nextInt(9));
           
        param[1] = String.valueOf(x);
        param[2] = String.valueOf(y);
        param[3] = String.valueOf(altitude);
        param[4] = String.valueOf(vitesse);
        param[5] = String.valueOf(cap);

        }catch(Exception e){System.out.println(e.getMessage());}
    
    }
    
    	void changer_altitude(int alt) {
	    if (alt < 0)
	        param[3] = String.valueOf(0);
	    else if (alt > alt_max)
	        param[3] = String.valueOf(alt_max);
	    else param[3] = String.valueOf(alt);
	}
    	// modifie la valeur de l'avion avec la valeur pass�e en param�tre
	
	void changer_vitesse(int vitesse) {
	    if (vitesse < 0){
	    param[4] = String.valueOf(0);}
	    else if (vitesse > vt_max){
	        param[4] = String.valueOf(vt_max);}
            else{ param[4] = String.valueOf(vitesse);
	}}
	
	// modifie le cap de l'avion avec la valeur passée en paramètre
	
	void changer_cap(int cap) {
	    if ((cap >= 0) && (cap < 360))
	        param[5] = String.valueOf(cap);
	}
	
 
        void calcul_deplacement(){
            
         double cosinus,sinus,dep_x,dep_y;
          
         //vitesse
         if (Double.parseDouble(param[4]) < vt_min){
            System.out.println("Vitesse Trop Faiible: Crash de l'avion\n");
            //fermer_communication();
            return;
            }
         //Altitude.
         if (Double.parseDouble(param[3]) == 0){
            System.out.println("L'avion s'est écrasé au sol.\n");
            //fermer_communication();
            return;
            }   
         
        //Cap.
        cosinus = Math.cos(Double.parseDouble(param[5]) * 2 * Math.PI /360);
        sinus = Math.sin(Double.parseDouble(param[5]) * 2 * Math.PI /360);
         
        //vitesse.
        dep_x = cosinus * Double.parseDouble(param[4]) * 10 / vt_min;
        dep_y = sinus * Double.parseDouble(param[4]) * 10 / vt_min;
         
        // on se d�place d'au moins une case quels que soient le cap et la vitesse
        // sauf si cap est un des angles droit
        if ((dep_x > 0) && (dep_x < 1)) dep_x = 1;
        if ((dep_x < 0) && (dep_x > -1)) dep_x = -1;
        if ((dep_y > 0) && (dep_y < 1)) dep_y = 1;
        if ((dep_y < 0) && (dep_y > -1)) dep_y = -1;
        
        dep_x = Double.parseDouble(param[1]) + dep_x;
        dep_x = Math.floor(dep_x*100)/100;
        
        dep_y = Double.parseDouble(param[2]) + dep_y;
        dep_y = Math.floor(dep_y*100)/100;
        
        param[1] = String.valueOf(dep_x);
        param[2] = String.valueOf(dep_y);
        }
}