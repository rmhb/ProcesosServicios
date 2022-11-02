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
public class ej9_ArrayWriter implements Runnable {
    private final ej9_ProbConcuArray miArray ;
    private final int startValue;

    public ej9_ArrayWriter(ej9_ProbConcuArray miArray, int startValue) {
        this.miArray = miArray;
        this.startValue = startValue;
    }
    
    public  void run(){
        for (int i = startValue; i < startValue +3 ; i++) {
            miArray.add(i); // Insertamos 3 enteros consecutivos. 
        }
    }
}
