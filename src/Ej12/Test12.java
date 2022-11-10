/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej12;

import Ej10.Buffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author NASA
 */
public class Test12 {
    
    public void runTestSynBufferLockCondi(){
           // create new thread pool with two threads
        ExecutorService exService = Executors.newCachedThreadPool();
        // create UnsynchronizedBuffer to store ints
        Buffer bufferCompartido = new SynBufferLockCondi();
        System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", "Buffer", "Occupied", "---------", "------\t\t--------");
        // execute the Producer and Consumer, giving each access to the sharedLocation
        exService.execute(new Servidoor(bufferCompartido));
        exService.execute(new Clientte(bufferCompartido));
        exService.shutdown();
        try{
            exService.awaitTermination(1, TimeUnit.MINUTES);
        }catch(InterruptedException e){
            e.printStackTrace();
        } 
    }
    

    
}
