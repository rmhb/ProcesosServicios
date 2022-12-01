/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SpaPalacioPatos.v3;

import java.security.SecureRandom;

/**
 *
 * @author NASA
 */
public class Masajista implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final SPA spa; // Referencia al spa que contiene las camillas
    private final String name;
    
    public Masajista(SPA spa, String name){
        this.spa = spa;
        this.name = name;
    }
    public void run() {
        while(spa.isSpaAbierto()){
        try{ 
            Thread.sleep(generator.nextInt(500)); // Tiempo para desplazarse entre camillas
            spa.atenderCliente(this.name);
        } catch (InterruptedException exception){Thread.currentThread().interrupt();}
        }
      
    }  
    
}
