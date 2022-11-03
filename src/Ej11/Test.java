
package Ej11;


import Ej10.Buffer;
import Ej10.Cliente;
import Ej10.Proveedor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author NASA
 */
public class Test {
    // Este método es para el 11
    public static void runTestSynBuffer ()  {
        // create new thread pool with two threads
        ExecutorService exService = Executors.newCachedThreadPool();
        // create UnsynchronizedBuffer to store ints
        Buffer bufferCompartido = new SynBuffer();
        System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", "Buffer", "Occupied", "---------", "------\t\t--------");
        // execute the Producer and Consumer, giving each access to the sharedLocation
        exService.execute(new Proveedor(bufferCompartido));
        exService.execute(new Cliente(bufferCompartido));
        exService.shutdown();
        try{
            exService.awaitTermination(1, TimeUnit.MINUTES);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
        // Este método es para el 11-B
    public static void runTestBufferCiclicoArrayBlockingQueue ()  {
        // create new thread pool with two threads
        ExecutorService exService = Executors.newCachedThreadPool();
        // create UnsynchronizedBuffer to store ints
        Buffer bufferCompartido = new BufferArrayBlockingVariosElementos();
        System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", "Buffer", "Occupied", "---------", "------\t\t--------");
        // execute the Producer and Consumer, giving each access to the sharedLocation
        exService.execute(new Proveedor(bufferCompartido));
        exService.execute(new Cliente(bufferCompartido));
        exService.shutdown();
        try{
            exService.awaitTermination(1, TimeUnit.MINUTES);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
        
        // Este método es para el 11-C
    public static void testBufferCircular ()  {
        // create new thread pool with two threads
        ExecutorService exService = Executors.newCachedThreadPool();
        // create UnsynchronizedBuffer to store ints
        CircularBuffer bufferCompartido = new CircularBuffer();
        bufferCompartido.displayState("Estado Inicial");
        // execute the Producer and Consumer, giving each access to the sharedLocation
        exService.execute(new Proveedor(bufferCompartido));
        exService.execute(new Cliente(bufferCompartido));
        exService.shutdown();
        try{
            exService.awaitTermination(1, TimeUnit.MINUTES);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
}
