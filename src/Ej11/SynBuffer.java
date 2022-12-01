/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej11;
import Ej10.*;
/**
 *
 * @author NASA
 * Acceso sincronizado a datos compartidoo utilizandoo wait y notifyAll
 */
public class SynBuffer implements Buffer{
    // Debemos asegurar que el acceso a estos dos atributos se hace de forma sincronizada. 
    private int buffer = -1;
    private boolean ocupado = false;
    
    //Metodo que coloca valor en el buffer. 
    //Como es synchronized solo un hilo ppuede acceder a la vez a este método para un mismo objeto. 
    public synchronized void blockingPut(int value) throws InterruptedException{
        while(ocupado){
            System.out.println("Servidor intenta escribir");
            displayState("Buffer lleno, toca esperar");
            wait();
        } 
        /**
         * El hilo se queda en el estado de wait hasta que otra hebra le notifica que puede seguir. 
         * En ese momento vuelve a intentar coger el candado para el objeto de la clase y si lo
         * consigue continua ejecutando el código después del while. 
         * */
        buffer = value;
        ocupado = true;
        // iindicamoos que el servidor no puede guardar un nuevo dato hasta que el cliente lo haya leido
        displayState("Servidor escribe "+buffer);
        notifyAll();
        // proopaga a los hilos durmientes que salgan de su estado y vuelvan al estado running. 
        // Cuando llamamos a notifyAll el candado se libera para que otra hebra pueda cogerlo. 
        
    }
    public synchronized int blockingGet() throws InterruptedException{
        while(!ocupado){
            System.out.println("Cliente lee");
            displayState("Buffer vacío, el cliente espera");
            wait();
        }
        ocupado = false; 
        // Para que el servidor pueda volver a meter dato. 
        // Ocuppado false es que el buffer está vacío.
        displayState("Cliente lee "+buffer);
        notifyAll(); // proopaga a los hilos durmientes que salgan de su estado y vuelvan al estado running
        return buffer;
    }
    // Este metodo también es sincronizado porque lee del buffer que son datos que van cambiando
    private synchronized void displayState(String operacion){
        System.out.printf("%n%-40s%d\t\t%b%n%n", operacion, buffer, ocupado);
    }
    
}
