/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecuMulti;
// Class that manages an integer array to be shared by multiple threads.
import java.security.SecureRandom;
import java.util.Arrays;
/**
 *
 * @author NASA
 */
public class ej9_SynConcuArray1 {
    private static final SecureRandom generador = new SecureRandom();
    private final int[] array; 
    private int writeIndex = 0; 
    
    public ej9_SynConcuArray1(int tam){
        array = new int[tam];
    }

    // Ponemos synchronized para tratar el método como algo a ejecutar por una sola hebra a la vez. Vamos a hacer el método atomico.
    public synchronized void add(int valor){
        int posicion = writeIndex; 
        try {
            Thread.sleep(generador.nextInt(500));
        }
        catch (InterruptedException ex){
            Thread.currentThread().interrupt(); 
        }
        // put valor in the appropriate element
        array[posicion] = valor;    
         System.out.printf("%s Ha escrito %2d en la posicion %d.%n",Thread.currentThread().getName(), valor, posicion);
        ++writeIndex; 
        System.out.printf("Siguiente indice de escritura: %d%n", writeIndex);
    }

    // used for outputting the contents of the shared integer array
    public synchronized String toString(){
        return Arrays.toString(array);
    }

}
