/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecuMulti;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author NASA
 */
public class ejTimer extends TimerTask {
    int count = 0;
    @Override
    public void run(){
        System.out.println("Cavando..... "+count++);
    }
    public static void main (String[] args){
        Timer tempo = new Timer();
        tempo.schedule(new ejTimer(), 1000, 2000); //Ejecuta la tarea al segundo y luego la repite cada 2 sg.
    }
    
}
