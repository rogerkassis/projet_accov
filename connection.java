/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saca_serveur;


public class connection extends Thread {
    
    private connection_avion con_avion; 
    private String num_vol;
    private String host_name;
    private Integer num_port;
   
    public connection(connection_avion con_avion, String num_vol,String host_name,Integer num_port){
        this.con_avion = con_avion;
        this.num_vol = num_vol;
        this.host_name = host_name;
        this.num_port = num_port;
    }
   
    public void run(){
        con_avion.connect(num_port,num_vol,host_name);
    }
}

