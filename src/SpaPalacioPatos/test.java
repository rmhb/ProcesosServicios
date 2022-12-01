/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SpaPalacioPatos;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NASA
 */
public class test {
    SecureRandom generator = new SecureRandom(); 
    public void runV1(){
        
        int MAX_MASAJISTA = 2;
        int MAX_CAMILLAS =MAX_MASAJISTA+1;
        int MAX_CLIENTES=MAX_MASAJISTA*10;
        int MAX_ESPERA_SEGS = 3;
        SpaPalacioPatos.SPA spa = new SpaPalacioPatos.SPA(MAX_CAMILLAS); // Objeto compartido         
        ExecutorService exService = Executors.newCachedThreadPool();
         // Ejecucion del proveedor y del cliente dando acceso al buffer compartido
         for (int i = 0; i < MAX_MASAJISTA; i++) {
           exService.execute(new SpaPalacioPatos.Masajista(spa, " Masajista "+i));
        }
        for (int i=0; i<MAX_CLIENTES; i++){
            exService.execute(new SpaPalacioPatos.Cliente(spa));     
            try {
                    Thread.sleep(generator.nextInt(MAX_ESPERA_SEGS)*1000);
            } catch (InterruptedException e) {e.printStackTrace();}
        }               
        exService.shutdown();
        for (int i = 0; i < MAX_MASAJISTA; i++) {
            exService.execute(new SpaPalacioPatos.Masajista(spa, " Masajista "+i));
        }           
    }
      public void runV2(){
        
        int MAX_MASAJISTA = 2;
        int MAX_CAMILLAS =MAX_MASAJISTA+1;
        int MAX_CLIENTES=MAX_MASAJISTA*10;
        int MAX_ESPERA_SEGS = 3;

        SpaPalacioPatos.v2.SPA spa = new SpaPalacioPatos.v2.SPA(MAX_CAMILLAS); // Objeto compartido
       
        ExecutorService exService = Executors.newCachedThreadPool();
        SpaPalacioPatos.v2.Masajista[] masajistas = new  SpaPalacioPatos.v2.Masajista[MAX_MASAJISTA];
        for (int i = 0; i < MAX_MASAJISTA; i++) {
           masajistas[i] = new  SpaPalacioPatos.v2.Masajista(spa, " Masajista "+i);
           exService.execute(masajistas[i]);
        }
        for (int i=0; i<MAX_CLIENTES; i++){   
            exService.execute(new SpaPalacioPatos.v2.Cliente(spa));  
            try {
               Thread.sleep(generator.nextInt(MAX_ESPERA_SEGS)*1000);
            } catch (InterruptedException e) {e.printStackTrace();}
        }
        exService.shutdown();
        for (int i = 0; i < MAX_MASAJISTA; i++) {
           masajistas[i].cerrarSPA();
        }
    }
    public static void testSPAv3 ()  {
        int MAX_MASAJISTA = 2;
        int MAX_CAMILLAS =MAX_MASAJISTA+3;
        int MAX_CLIENTES=MAX_MASAJISTA*10;
        int MAX_ESPERA_SEGS = 3;
        SecureRandom gen = new SecureRandom();
        // Creamos un nuevo thread pool para nuestros hilos
        ExecutorService exService = Executors.newCachedThreadPool();
        // creamos un buffer compartido que en este caso se bloquea para lecturas y escrituras
        SpaPalacioPatos.v3.SPA salon = new SpaPalacioPatos.v3.SPA(MAX_CAMILLAS);
        salon.displayState("Estado Inicial");
         // Generamos los masajistas y los clientes
        for (int i = 0; i < MAX_MASAJISTA; i++) {
            exService.execute(new SpaPalacioPatos.v3.Masajista(salon, "Masajista_"+i));
        }
        
        for (int i = 0; i < MAX_CLIENTES; i++) {
            exService.execute(new SpaPalacioPatos.v3.Cliente(salon, "Cliente_"+i));
            try {
               Thread.sleep(gen.nextInt(1500));
            } catch (InterruptedException e) {e.printStackTrace();}
        }
        exService.shutdown();
        while(salon.getCamillasOcupadas() != 0){
            
        }
        salon.setSpaAbierto(false);
        
        try{
            exService.awaitTermination(1, TimeUnit.MINUTES);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        salon.displayState("Estado Final");
    }
    
}
