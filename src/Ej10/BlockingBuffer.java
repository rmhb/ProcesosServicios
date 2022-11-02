/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej10;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author NASA
 */
public class BlockingBuffer implements Buffer {
    private final ArrayBlockingQueue<Integer> bf;

    public BlockingBuffer() {
        bf = new ArrayBlockingQueue<Integer>(1);
    }
    // colocar valor en el buffer
    public void blockingPut(int value) throws InterruptedException{
        bf.put(value);
        System.out.printf("%s%2d\t%s%d%n","Proveedor --> ",value, "Buffer cells occupied: ", bf.size());
    }
    public int blockingGet()throws InterruptedException{
        int readValue = bf.take(); // remove value from buffer
        System.out.printf("%s%2d\t%s%d%n","Cliente lee --> ",readValue, "Buffer cells occupied: ", bf.size());
        return readValue;
    }
    
    
}
