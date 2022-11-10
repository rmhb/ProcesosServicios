package Ej10;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author NASA
 */
public class Test {
    public static void runTest_unsyn () {
        // Importar java.util.concurrent.*
        // Creamos una nueva Pool de hilos
        ExecutorService exService = Executors.newCachedThreadPool();
        // Creamos un buffer, que almacenara enteros. Este buffer no esta sincronizado (clase unSynBuffer)
        Buffer bufferCompartido = new unSyncBuffer();

        System.out.println( "Accion\t\tValor\tSuma Proveedor\tSuma Cliente");
        System.out.printf(  "------\t\t-----\t--------------\t------------%n%n");

        // Ejecutamos el Proveedor y el Cliente. 
        // Asignando un hilo a cada uno, que además accederan ambos al buffer compartido
        exService.execute(new Proveedor(bufferCompartido));
        exService.execute(new Cliente(bufferCompartido));
        exService.shutdown();
        try{
            exService.awaitTermination(1, TimeUnit.MINUTES);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    // Este método es para el 10B
    public static void runTest_syn () {
        // create new thread pool with two threads
        ExecutorService exService = Executors.newCachedThreadPool();
        // create UnsynchronizedBuffer to store ints
        Buffer bufferCompartido = new BlockingBuffer();

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
