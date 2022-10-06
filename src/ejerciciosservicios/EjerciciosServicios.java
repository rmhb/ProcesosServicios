/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejerciciosservicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Nasa
 */
public class EjerciciosServicios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EjercicioPowerShell obj = new EjercicioPowerShell(args);
//        obj.lanzaProceso();
//        obj.lanzaPowerShell();
//        obj.usoInherit();
//        obj.usoInheritError();
//        obj.EjercicioAlCambiaDirectorioProceso();
        obj.ejemploNSLOOKUP();
        
        
        
//        ProcessBuilder pb=new ProcessBuilder("nslookup" );
//        pb.redirectOutput(ProcessBuilder.Redirect . INHERIT);
//        try (InputStreamReader isstdin=new InputStreamReader(System. in, "UTF-8" );
//            BufferedReader brstdin=new BufferedReader(isstdin)) {
//            String linea;
//            System. out. print("Introducir nombre de dominio: " );
//            while (( linea = brstdin. readLine ()) != null && linea . length() !=0) {         
//                Process p = pb.start (); //Lanza el proceso
//                try (OutputStream osp = p. getOutputStream();
//                    OutputStreamWriter oswp = new OutputStreamWriter(osp, "UTF-8" )) {
//                        oswp .write(linea); 
//                }
//                try {
//                    p .waitFor();
//                } catch (InterruptedException ex) {
//                }
//                System.out . print ("Introducir nombre de dominio: " );
//            }   
//        }catch (IOException e ) {
//                System. out. println( "ERROR: de E/S" );
//                e . printStackTrace();
//        }
    }
            
    
}
