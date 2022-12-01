/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej10;
import java.security.SecureRandom;
/**
 *
 * En este ejemplo el proveedor va a ir escribiendo datos en un buffer
 * que es compartido también por el cliente o consumidor. El objetivo es comprobar 
 * que lee el cliente del buffer, para ello, vamos a hacer un sumador de numeros
 * El cliente va a ir anotando en la variable suma, la suma de todos los valores
 * que ha leído del buffer. Para hacer la comprobacion tabmien haremos que el
 * proveedor escriba la suma de los elementos que ha ido escribiendo. 
 */
public class Cliente implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer bufferCompartido; // Referencia al buffer compartido
    
    public Cliente(Buffer bf){
        this.bufferCompartido = bf;
    }
    public void run() {
        int sum = 0;
        for (int count = 1; count <= 10; count++) {
            try{ 
                Thread.sleep(generator.nextInt(3000));
                sum += bufferCompartido.blockingGet();
                System.out.printf("suma cliente \t\t\t%2d%n", sum);
           } catch (InterruptedException exception){
                Thread.currentThread().interrupt();
           }
       }
      System.out.print("-----------------------------------------------------------------------");
      System.out.printf("%n%s %d%n%s%n", "| La suma total en cliente es ", sum, "| Terminando Cliente");
      System.out.println("-----------------------------------------------------------------------");
   }  
}
