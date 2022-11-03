/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej11;
import java.util.concurrent.ArrayBlockingQueue;
import Ej10.*;
/**
 *
 * @author NASA
 */
// Ejerciicio 11-B.      // Ejemplo 11-B NO HACER, NO ME FUNCIONA.
public class BufferArrayBlockingVariosElementos implements Buffer{
    private final ArrayBlockingQueue<Integer> bf;

    public BufferArrayBlockingVariosElementos() {
        // La única diferencia respecto del ejempplo 10 es que aumentamos el número de elementos del array. 
        bf = new ArrayBlockingQueue<Integer>(5);
    }
    // colocar valor en el buffer
    public void blockingPut(int value) throws InterruptedException{
        bf.put(value);
        System.out.printf("%s%2d\t%s%d%\t%s%dn","Proveedor --> ",value, "Buffer cells occupied: ", bf.size(),"Buffer free cells remaining : ",bf.remainingCapacity());
    }
    public int blockingGet()throws InterruptedException{
        int readValue = bf.take(); // remove value from buffer
        System.out.printf("%s%2d\t%s%d%n","Cliente lee --> ",readValue, "Buffer cells occupied: ", bf.size());
        return readValue;
    }
    
}
