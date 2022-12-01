
package Ej12;

import Ej10.Buffer;
import java.security.SecureRandom;

/**
 *
 * @author NASA
 */
public class Clientte implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer bufferCompartido; 
    // constructor
    public Clientte(Buffer bf){
        this.bufferCompartido = bf;
    }
    public void run() {
        int sum = 0;
        for (int count = 1; count <= 10; count++) {
            try{ 
                Thread.sleep(generator.nextInt(3000));
                sum += bufferCompartido.blockingGet();
           } catch (InterruptedException exception){
                Thread.currentThread().interrupt();
           }
       }
      System.out.printf("%n%s %d%n%s%n", "| La suma total en cliente es ", sum, "| Terminando Cliente");
   }   
}
