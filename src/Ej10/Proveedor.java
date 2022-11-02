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
public class Proveedor implements Runnable{
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer bufferCompartido; // reference to shared object
    // constructor
    public Proveedor(Buffer bf){
        this.bufferCompartido = bf;
    }
    public void run() {
        int sum = 0;
        for (int count = 1; count <= 10; count++) {
            try{ // sleep 0 to 3 seconds, then place value in Buffer
                Thread.sleep(generator.nextInt(3000));
                bufferCompartido.blockingPut(count); // set value in buffer
                sum += count; // increment sum of values. Se suman todos los valores de 1 hasta 10: 1+2+3+4+5+6+7+8+9+10 = 55
                System.out.printf("\t%2d%n", sum);
           } catch (InterruptedException exception){
                Thread.currentThread().interrupt();
           }
       }
       System.out.printf("Proveedor ha acabado%nTerminando Proveedor%n");
   }
}
