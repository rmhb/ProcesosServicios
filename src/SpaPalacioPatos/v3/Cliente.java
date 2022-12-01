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
public class Cliente  implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final SPA spa; // Referencia al buffer compartido
    private String nombre;
    
    public Cliente(SPA spa, String nombre){
        this.spa = spa;
        this.nombre = nombre;
    }
    public void run() {
       try{ 
           spa.ocuparCamilla(this.nombre);
       } catch (InterruptedException exception){
            Thread.currentThread().interrupt();
       }
   }  
}
