/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej10;

/**
 *
 * @author NASA
 */
// Buffer interface specifies methods called by Producer and Consumer.
public interface Buffer{
    // place int value into Buffer
    public void blockingPut(int value) throws InterruptedException;

    // return int value from Buffer
    public int blockingGet() throws InterruptedException;
} // end interface Buffer