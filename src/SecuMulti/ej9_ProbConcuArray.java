/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecuMulti;
// Class that manages an integer array to be shared by multiple threads.
import java.security.SecureRandom;
import java.util.Arrays;

public class ej9_ProbConcuArray {
    private static final SecureRandom generador = new SecureRandom();
    private final int[] array; // Creamos un array de enteros que va a ser compartido por varios hilos
    private int writeIndex = 0; // Definimos un índicee para controlar la escritura,
    // indica la siguiente posición a escribir. Este índice también será compartido por varios hilos
    
    // Construir un array con el tamaño indicado
    public ej9_ProbConcuArray(int tam){
        array = new int[tam];
    }

    // Añadir un valor al array compartido
    public void add(int valor){
        int posicion = writeIndex; // Guardamos el indice
        try {
            // Mandamos al hilo a dormir durante 0-499 ms.
            // Hacemos esto para que el problema de hebras no sincronizadas sea aún más obvio. 
            Thread.sleep(generador.nextInt(500));
        }
        catch (InterruptedException ex){
            Thread.currentThread().interrupt(); // re-interrupt the thread
        }
        // Añadimos el valor en la posición del array
        array[posicion] = valor;    
        // Thread.currentThread().getName() -> Obtiene una refferencia a la hebra que se está ejecutando y se obtiene su nombre.
        System.out.printf("%s Ha escrito %2d en la posicion %d.%n",Thread.currentThread().getName(), valor, posicion);
        ++writeIndex; 
        System.out.printf("Siguiente indice de escritura: %d%n", writeIndex);
    }

    // Finalmente mostramos el contenido del array compartido
    public String toString(){
        return Arrays.toString(array);
    }

}
