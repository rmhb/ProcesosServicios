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
public class ej9_ProbConcuArray {
    private static final SecureRandom generator = new SecureRandom();
    private final int[] array; // the shared integer array
    private int writeIndex = 0; // shared index of next element to write
    
    // construct a SimpleArray of a given size
    public ej9_ProbConcuArray(int size){
        array = new int[size];
    }

    // add a value to the shared array
    public void add(int value){
        int position = writeIndex; // store teh write index
        try {
            // put thread to sleep for 0-499 milliseconds. Hacemos esto para que el problema de hebras no sincronizadas sea aún más obvio. 
            Thread.sleep(generator.nextInt(500));
        }
        catch (InterruptedException ex){
            Thread.currentThread().interrupt(); // re-interrupt the thread
        }
        // put value in the appropriate element
        array[position] = value;    
        // Thread.currentThread().getName() primero se obtiene una refferencia a la hebra que se está ejecutando y se obtiene su nombre.
        System.out.printf("%s wrote %2d to element %d.%n",Thread.currentThread().getName(), value, position);
        ++writeIndex; //Increment index of elemento to be written next.
        System.out.printf("Next write index: %d%n", writeIndex);
    }

    // used for outputting the contents of the shared integer array
    public String toString(){
        return Arrays.toString(array);
    }

}
