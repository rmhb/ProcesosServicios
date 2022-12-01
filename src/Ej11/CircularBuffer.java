/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej11;
import Ej10.Buffer;

/**
 *
 * @author NASA
 */
public class CircularBuffer implements Buffer{
    
    private final int[] buffer = {-1, -1, -1}; // Buffer compartido
    private int celdasOcupadas = 0; //Num celdas buffer ocupadas
    private int writeIndex = 0; // indice siguiente elemento escritura
    private int readIndex = 0; // indice siguiente elemento lectura
    public synchronized void blockingPut(int value) throws InterruptedException{
        // Espèramos hasta que el buffer tenga espacio para poder escrbir.
        // En este caso cualquiier posición del buffer que se quede libre nos vale. 
        while(celdasOcupadas == buffer.length){
            System.out.println("El buffer está completo");
            wait();
        }
        buffer[writeIndex] = value;
        writeIndex = (writeIndex+1)%buffer.length;
        ++celdasOcupadas;
        displayState("Servidor escribe "+value);
        notifyAll(); //Notifica a las hebras en estado waiting que lean del buffer        
    }
    public synchronized int blockingGet() throws InterruptedException{
        //Esperar hasta que el buffer tenga datos que leer y después leer valor.
        // Mientras que no haya valores por leer ponemos la hebra en estado waiting.
        while(celdasOcupadas == 0){
            System.out.println("El buffer está vacío. El cliente se espera ");
            wait();
        }
        int readValue = buffer[readIndex]; // Lee valor del buffer
        //Actualiza el indice
        readIndex =(readIndex+1)%buffer.length;
        --celdasOcupadas; // una celda queda libre
        displayState("Cliente lee "+readValue);
        notifyAll(); // Se notifica a las hebras en estado waiting para que escriban en el buffer
        return readValue;
    }
    // Muestra la opperación actual y cómo está el buffer
    public synchronized void displayState(String operation){
        // En este método vamos a poder ver qué tipos de acción está realizando el programa,
        // lecutra o escritura del buffer y el estado del buffer
        System.out.printf("%s%s%d)%n%s", operation, " (Celdas del buffer ocupadas: ",
                celdasOcupadas, "Celdas del buffer: ");
        for(int value: buffer)
            System.out.printf(" %2d ", value);// valores buffer
        System.out.printf("%n ");

        for (int i = 0; i < buffer.length; i++)
            System.out.print("---- ");
        System.out.printf("%n ");

        for (int i = 0; i < buffer.length; i++)
        {
            if (i == writeIndex && i == readIndex)
                System.out.print(" WR"); // Indice de lectura y escritura coinciden
            else if (i == writeIndex)
                System.out.print(" W "); // Valor indice escritura
            else if (i == readIndex)
                System.out.print(" R "); // Valor indice lectura
            else
                System.out.print(" "); // ningun indice tiene ese valor actualmente
        }
    }
}
