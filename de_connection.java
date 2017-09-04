/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca_serveur;

/**
 *
 * @author MyComputer
 */
public class de_connection extends Thread{
    
    private connection_avion con_avion; 
    private String num_vol;
    private String host_name;
   
    public de_connection(connection_avion con_avion, String num_vol,String host_name){
        this.con_avion = con_avion;
        this.num_vol = num_vol;
        this.host_name = host_name;
    }
   
    public void run(){
        con_avion.disconnect(num_vol,host_name);
    }
    
    
}
