/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.a1;

import java.io.File;
import java.io.IOException;


/**
 *
 * @author Nasa
 */
public class a1_main {
    
    public static void main(String[] args) throws IOException, InterruptedException {
       ProcessBuilder pb = new ProcessBuilder("java", "src//actividades//a1//a1_servidor.java");
       pb.redirectOutput(new File("log.txt"));
       Thread.currentThread().sleep(500);
       Process p1 = pb.start();
       a1_cliente a1 = new a1_cliente("127.0.0.1", 6001,"Hola que tal");
       a1.conector();
    }
    
}
