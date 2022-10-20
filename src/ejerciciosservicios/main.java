/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejerciciosservicios;

import static ejerciciosservicios.EjerciciosFinalesUD1.arrayGeneradores;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Nasa
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
//        EjercicioPowerShell obj = new EjercicioPowerShell(args);
//        obj.lanzaProceso();
//        obj.lanzaPowerShell();
//        obj.usoInherit();
//        obj.usoInheritError();
//        obj.EjercicioAlCambiaDirectorioProceso();
//        obj.ejemploNSLOOKUP();


// *************** Ejercicios Finales UD1 *********************
        EjerciciosFinalesUD1 ud1 = new EjerciciosFinalesUD1();
//        String ruta = "C:\\Program Files\\Firefox Developer Edition\\firefox.exe";
//        ud1.ejecutar(ruta);
//        System.out.println("Fin ejecución ejercicio 1");
        
//        ud1.llamaGenerador(3,1,2,"salida.txt");
//        ud1.llamaGenerador(5,2,3,"salida2.txt");
//        System.out.println("Fin ejecucion ejercicio 2");
//        
        
       // Generamos un numero aleatorio entre 1 y 5 que serán las veces que lacemos procesos
        int numFicheros = (int)Math.floor((Math.random()*5)+1);
        int n1, n2, n3;
        String prefijo = "fich";
        System.out.println("Se van a lanzar "+numFicheros+" procesos");
        for(int i=1; i<= numFicheros ; i++){
            String nombreFich =  prefijo + "_" + String.valueOf(i)+".txt";
            n1 = (int)Math.floor((Math.random()*100)+1);
            n2 = (int)Math.floor((Math.random()*100)+1);
            n3 = (int)Math.floor((Math.random()*5)+2);
            ud1.llamaGenerador_v2(n1,n2,n3, nombreFich);
        } 
        Thread.sleep(3000); // Debemos dar un timepo para que se escriban los ficchereos. 
        
        ArrayList<Integer> res = arrayGeneradores(prefijo, numFicheros);
        Iterator<Integer> item = res.iterator();
        while(item.hasNext()){
            System.out.println("Valor "+item.next());
        }
        
        System.out.println("Fin ejecucion ejercicio 3");
        
        
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
