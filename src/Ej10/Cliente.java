/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej10;
import java.security.SecureRandom;
/**
 *
 * @author NASA
 */
public class Cliente implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer bufferCompartido; // reference to shared object
    // constructor
    public Cliente(Buffer bf){
        this.bufferCompartido = bf;
    }
    public void run() {
        int sum = 0;
        for (int count = 1; count <= 10; count++) {
            try{ // sleep 0 to 3 seconds, then place value in Buffer
                Thread.sleep(generator.nextInt(3000));
                sum += bufferCompartido.blockingGet();
                System.out.printf("\t\t\t%2d%n", sum);
           } catch (InterruptedException exception){
                Thread.currentThread().interrupt();
           }
       }
      System.out.print("-----------------------------------------------------------------------");
      System.out.printf("%n%s %d%n%s%n", "| La suma total en cliente es ", sum, "| Terminando Cliente");
      System.out.println("-----------------------------------------------------------------------");
   }
    
}
