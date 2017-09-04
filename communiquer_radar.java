/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avion;

import java.net.InetAddress;

/**
 *
 * @author MyComputer
 */
public class communiquer_radar extends Thread{
    
    class_avion inst_avion;
    Avion avion;
    communiquer_controleur controleur ;

    
    public communiquer_radar(class_avion inst_avion, Avion avion){
       this.inst_avion  = inst_avion; 
       this.avion = avion;
       controleur = new communiquer_controleur(inst_avion,avion.num_port);
    }
    
    public void run(){
  
        while (true)
        {
                 
            try{
             sleep(2000);

             //ouvrir connection.
            if (inst_avion.ouvrir_communication(InetAddress.getLocalHost().getHostName(),6789) ){
             avion.jLabel1.setText("Connecteé avec le gestionnaire de vol. " ); 
            }
            else{
             avion.jLabel1.setText("Impossible de contacter le gestionnaire de vol.");
            }
                 
            inst_avion.calcul_deplacement();
            inst_avion.envoyer_caracteristiques(avion.jTextField1.getText(),avion.num_port);
            //Affichage des donnees.
            String str = " Localisation: " + inst_avion.param[1] + " , " + inst_avion.param[2] + " , Altitude: " + inst_avion.param[3] + " , Vitesse: " + inst_avion.param[4] + " , Cap: " + inst_avion.param[5];
            avion.jTextArea1.append(str + "\n");
            
            //fermer connection.
            inst_avion.fermer_communication();
            
            //commuuniquer avec controleur.
            controleur.run();
            
              }
        catch(Exception e){
           
        
        }
    
    }
    
    }
}
    
    

