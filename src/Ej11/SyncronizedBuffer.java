/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej11;
import Ej10.Buffer;
import java.util.concurrent.locks.*;

/**
 *
 * @author Nasa
 */
public class SyncronizedBuffer implements Buffer {
    // Lock para controlar la sincro del buffer
    private final Lock bloqueAcceso = new ReentrantLock();
    // Condiciones para controlar lectura y escritura
    private final Condition puedoEscribir = bloqueAcceso.newCondition();
    private final Condition puedoLeer = bloqueAcceso.newCondition();
    
    private int buffer = -1; // Compartido por el servidor y el cliente
    private boolean lleno = false; // Si el buffer está lleno
   
// Colocamos el valor en el buffer    
    public void blockingPut (int value) throws InterruptedException{
        // while there are no empty locations, place thread in waiting state
        bloqueAcceso.lock();
        try{
            while(lleno){
                System.out.println("Servidor intenta escribir dato");
                displayState("Buffer está lleno, el servidor espera");
                puedoEscribir.await(); // Esperar hasta que el buffer se vacie
            }
            buffer = value;
            lleno = true; // Solo dejo escribir un dato, hasta que el cliente no lo lea no se puede escribir más.
            displayState("Servidor escribe "+buffer);
            puedoLeer.signalAll(); // Aviso a las hebras de lectura que estaban esperando
        }finally{
            bloqueAcceso.unlock(); // Suelto el bloqueo.
        }
    }
  
    public int blockingGet() throws InterruptedException {
        int valorLeido = 0;
        bloqueAcceso.lock();
        try{
            while(!lleno){
                System.out.println("Cliente intenta leer ... ");
                displayState("Buffer vacío. El cliente espera");
                puedoLeer.await();
                // debo esperara a que el buffer esté lleno para leer
            }
            lleno = false;
            valorLeido = buffer;
            displayState("Cliente lee "+ valorLeido);
            puedoEscribir.signalAll(); // Avisamos a las hebras que estaban esperando
        }finally{
            bloqueAcceso.unlock();
        }
        return valorLeido;
    }// end method blockingPut; releases lock on SynchronizedBuffer
    
        // Muestra la opperación actual y cómo está el buffer
    public synchronized void displayState(String operation){
        try{
            bloqueAcceso.lock();
            System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, lleno);
        }
        finally{
            bloqueAcceso.unlock();
        }
    } 
}
