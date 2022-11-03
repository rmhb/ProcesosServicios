/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej12;

// Synchronizing access to a shared integer using the Lock and Condition
// interfaces
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import Ej10.Buffer;
/**
 *
 * @author NASA
 */
public class SynBufferLockCondi implements Buffer{
    // Candado para controlar el acceso al buffer
    private final Lock candado = new ReentrantLock();
    // Condiciones que voy a declarar
    private final Condition puedoEscribir = candado.newCondition();
    private final Condition puedoLeer = candado.newCondition();
    private int buffer = -1;
    private boolean ocupado = false; // Si el buffer está lleno o vacío
    public void blockingPut(int value) throws InterruptedException{
        candado.lock(); // Coge candado
        try{
            while(ocupado){
                System.out.println("Servidor intenta escribir");
                displayState("Buffer lleno. Servidor espera");
                puedoEscribir.await();
            }
            buffer = value;
            ocupado = true;
            displayState("Servidor esttá escribiendo "+buffer);
            puedoLeer.signalAll(); // Como he escrito un dato notifico al resto de hebras que estaban esperand sobre esta condición para que pueda leer
        }finally{
            candado.unlock();
        }
    }
     public int blockingGet() throws InterruptedException{
        int readValue = 0;
        candado.lock(); // Coge candado
        try{
            while(!ocupado){
                System.out.println("Cliente intenta leer");
                displayState("Buffer vacío. Cliente espera");
                puedoLeer.await();
            }
            ocupado = false;
            readValue = buffer;
            displayState("Cliente esttá leyendo "+readValue);
            puedoEscribir.signalAll(); // Como he escrito un dato notifico al resto de hebras que estaban esperand sobre esta condición para que pueda escribir
        }finally{
            candado.unlock();
        }
        return readValue;
    }
    
    
    
   private void displayState(String operation){
        // En este método vamos a poder ver qué tipos de acción está realizando el programa, lecutra o escritura del buffer y el estado del buffer
            try{
                candado.lock();
                System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, ocupado);
            }
            finally{
                candado.unlock();
            }
       
    }
    
}
