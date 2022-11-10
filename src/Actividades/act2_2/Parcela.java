/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actividades.act2_2;

/**
 *
 * @author NASA
 */
public class Parcela {
    long l1,l2,l3,l4, perimetro;
    
    
    public Parcela(long l1, long l2, long l3, long l4) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
        this.perimetro = PerimetroCalculator.getPerimeter(l1, l2, l3, l4);
    }
    
    
}
