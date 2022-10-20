/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosservicios;

/**
 *
 * @author NASA
 */
public class Recursos {
    public static int generador(int n1, int n2, int n3){
        return (int)Math.pow((n1-n2), n3);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Recursos r = new Recursos();
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int n3 = Integer.parseInt(args[2]);
        System.out.println(generador(n1,n2,n3));
                
    }
    
}
