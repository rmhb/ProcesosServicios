/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actividades.act2_2;

/**
 *
 * @author NASA
 */
public class Parcela implements Runnable{
    long l1,l2,l3,l4, perimetro;
    String nombre;
    
    
    public Parcela(long l1, long l2, long l3, long l4, String nombre) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
        this.nombre = nombre;
        this.perimetro = 0;
    }
    @Override 
    public void run(){
        this.perimetro = PerimetroCalculator.getPerimeter(l1, l2, l3, l4);
        System.out.printf("El hilo %s para la parcela con lados %d %d %d %d ha calculado un perimetro de %d %n",
                Thread.currentThread().getName(), l1,l2, l3, l4, perimetro);
    }
    
    
}
