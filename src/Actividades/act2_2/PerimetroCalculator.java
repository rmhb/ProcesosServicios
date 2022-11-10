/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actividades.act2_2;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NASA
 */
public class PerimetroCalculator {
    private static final SecureRandom generator = new SecureRandom();
    public static long getPerimeter(long l1,long l2,long l3,long l4){
        long a = l1+l2;
        long b = l3+l4;
        try {
            Thread.sleep(generator.nextInt(3000));
        } catch (InterruptedException ex) {
            Logger.getLogger(PerimetroCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a+b;
    }
    
}
