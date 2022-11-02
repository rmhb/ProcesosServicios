/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecuMulti;
import java.security.SecureRandom;

/**
 *
 * @author NASA
 */
public class PrintTask implements Runnable {
    //Corresponde al ejemplo 8.
    private static final SecureRandom generador = new SecureRandom();
    private final int sleepTime;
    private final String taskName;

    public PrintTask(String taskName) {
        // Se genera un sleepTime entre 0 y 5 segundos
        this.sleepTime = generador.nextInt(5000);      
        this.taskName = taskName;
    }
    public void run(){
        try{
            System.out.printf("%s va a dormir durantte %d ms.%n ", taskName, sleepTime);
            Thread.sleep(sleepTime); // Método estatico que podemos usar de la clase Thread.
        }catch(InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Volvemos a reinterrumpir la hebra. Debemos pasarle la hebra actual
        }
        System.out.printf("%s ha regresado de sleep%n", taskName); // Aquí la hebra ya ha vuelto al estado ready y se le asiggna la cpu de nuevo. 
    }
    // La tarea PrintTask se ejecuta cuando se invoca al método run de PrintTask. Aqu´´i es importante recoger la excepción con try catch y no haceer que el método run thorughst exception ya que el original no contempla este throught y puede dar problemas.
    
    
}
