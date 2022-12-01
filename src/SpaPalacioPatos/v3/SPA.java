/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SpaPalacioPatos.v3;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 *
 * @author NASA
 */
public class SPA {
    private boolean[] arrayCamillas;
    private int camillasOcupadas; //Num celdas buffer ocupadas
    private int writeIndex; // indice para ocupar la siguiente camilla
    private int readIndex; // indice para liberar la siguiente camilla
    SecureRandom generador = new SecureRandom();
    private final int TMP_MASAJE = 2;
    private final int TMP_MASAJISTA_BREAK = 5;
    private boolean spaAbierto ;

    public boolean isSpaAbierto() {
        return spaAbierto;
    }

    public void setSpaAbierto(boolean spaAbierto) {
        this.spaAbierto = spaAbierto;
    }

    public int getCamillasOcupadas() {
        return camillasOcupadas;
    }

    public SPA(int numMaxCamillas) {
        this.arrayCamillas = new boolean[numMaxCamillas];
        this.camillasOcupadas = 0;
        this.writeIndex = 0;
        this.readIndex = 0;
        spaAbierto = true;
        Arrays.fill(arrayCamillas, true); // Esto implica que al inicio todas las camillas están libres
    }
    
    public synchronized void ocuparCamilla(String cliente) throws InterruptedException{
        // Espèramos hasta que el buffer tenga espacio para poder escrbir.
        // En este caso cualquiier posición del buffer que se quede libre nos vale. 
        if(camillasOcupadas == arrayCamillas.length){
            System.out.println("El salon esta completo");
            //wait();
            displayState("El cliente se larga "+cliente);
        }else{
            arrayCamillas[writeIndex] = false; // Se ocupa la Camilla
            writeIndex = (writeIndex+1)%arrayCamillas.length;
            ++camillasOcupadas;
            displayState("El cliente "+cliente+" ocupa la camilla "+writeIndex);
            //notifyAll(); //Notifica a las hebras en estado waiting que lean del buffer        
        }
    }
    public synchronized void atenderCliente(String fisio) throws InterruptedException{
        while(camillasOcupadas == 0){
            System.out.println("El SPA esta vacio. Los masajistas descansan.");
            esperarTiempoAzar(TMP_MASAJISTA_BREAK); // Hacemos que descanses entre 1 y 6 segundos.
//            wait();
        }
        // Simulamos un tiempo de atención al clieente: 
        esperarTiempoAzar(TMP_MASAJE);
        arrayCamillas[readIndex] = true; // Atendemos al cliente y liberamos una camilla
        //Actualiza el indice
        readIndex =(readIndex+1)%arrayCamillas.length;
        --camillasOcupadas; // una celda queda libre
        displayState("Cliente que habia en la camilla "+readIndex +" ha sido atendido por "+fisio);
        //notifyAll(); // Se notifica a las hebras en estado waiting para que escriban en el buffer
    }
    // Muestra la opperación actual y cómo está el buffer
    public synchronized void displayState(String operation){
        // En este método vamos a poder ver qué tipos de acción está realizando el programa,
        // lecutra o escritura del buffer y el estado del buffer
        System.out.printf("%s%s%d)%n%s", operation, " (Camillas del buffer ocupadas: ",
                camillasOcupadas, "Celdas del buffer: ");
        for(boolean value: arrayCamillas)
            System.out.printf(" %b ", value);// valores buffer
        System.out.printf("%n ");

        for (int i = 0; i < arrayCamillas.length; i++)
            System.out.print("---- ");
        System.out.printf("%n ");

        for (int i = 0; i < arrayCamillas.length; i++)
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
    public void esperarTiempoAzar(int max){
        /* Se calculan unos milisegundos al azar*/
        int threadSleepTime = (1+generador.nextInt(max))*1000;
        try {
                Thread.currentThread().sleep(threadSleepTime);
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
}
