/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serviciosud3;

import java.io.IOException;

/**
 *
 * @author Nasa
 */
public class ServiciosUD3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /**
         * Ejemplo 1. Generar un Socket cliente que se conecte a un servicio web html
         * En este caso a www.google.es a través del puerto 80
         **/
//        E1_socketCliente e1 = new E1_socketCliente("www.google.es", 80);
//        e1.conector();
        
        /**
         * Ejemplo 2. Generar un Socket Servidor, con un servicio de calculo de sumas 
         * Ademas, generar un Socket cliente que se conecte a dicho servicio. 
         * 
         **/
        Process p1 = new ProcessBuilder("java", "src//serviciosud3//E2_socketServidorCalc.java").start();
        E2_socketClienteCalc c2 = new E2_socketClienteCalc("127.0.0.1", 9876, 22, 13);
        c2.conector();
        
        // Puedo introducir un ejemplo con un servidor en Python para que vean que aunque estén en diferentes 
        // lenguajes de programacion se establece la conexion.
        
         /**
         * Ejemplo 3. Generar un Socket Servidor, con un servicio de calculo de sumas 
         * Ademas, generar un Socket cliente que se conecte a dicho servicio. 
         * 
         **/
  
    }    
}
