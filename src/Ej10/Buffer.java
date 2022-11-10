/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej10;


/**
 * Generamos una interfaz Buffer, que especifica los métodos que usuarán el cliente
 * y el proveedor para leer y escribir del Buffer
 * */
public interface Buffer{
    // Escribe un valor de tipo entero (int) en el Buffer
    public void blockingPut(int value) throws InterruptedException;

    // Lee un valor de tipo entero del Buffer
    public int blockingGet() throws InterruptedException;
} 