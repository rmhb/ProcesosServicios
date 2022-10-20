/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosservicios;

import static ejerciciosservicios.EjercicioPowerShell.printResults;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author NASA
 */
public class EjerciciosFinalesUD1 {
    private  ProcessBuilder pb;
    
     public  void ejecutar(String ruta){
        try {
                pb = new ProcessBuilder(ruta);
                pb.start();
        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    public void llamaGenerador(int n1, int n2, Integer n3, String salida){
        // int aleatorio = (int)Math.floor((Math.random()*5)+1);
//      System.out.println("Aleatorio "+aleatorio);
    
//        String recursos = "ejerciciosservicios.Recursos";
// No se puede utilizar la ruta a partir del paquete... no la reconoce y no encueentra la clase..... así que ruta absoluta
        String recursos = "C:\\Users\\NASA\\Documents\\NetBeansProjects\\EjerciciosServicios\\src\\ejerciciosservicios\\Recursos.java"; 
        
        ProcessBuilder pb;
        try{
            pb=new ProcessBuilder("java", recursos, String.valueOf(n1), String.valueOf(n2), n3.toString() );
            pb.redirectError(new File("errores.txt"));
            pb.redirectOutput(new File(salida));
            pb.start();

//           Process p = pb.start();
//           printResults(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void llamaGenerador_v2(int n1, int n2, Integer n3, String salida){
        
        //File directorioGenerador = new File("C:\\Users\\NASA\\Documents\\NetBeansProjects\\EjerciciosServicios\\salidaGenerador\\");
        //File directorioGenerador = new File("C:/Users/NASA/Documents/NetBeansProjects/EjerciciosServicios/salidaGenerador/");
        File directorioGenerador = new File("C:/salidaGenerador/");
        String recursos = "C:\\Users\\NASA\\Documents\\NetBeansProjects\\EjerciciosServicios\\src\\ejerciciosservicios\\Recursos.java"; 
        
        ProcessBuilder pb;
        try{
            pb=new ProcessBuilder("java", recursos, String.valueOf(n1), String.valueOf(n2), n3.toString() );
//            pb1.directory(new File("C:/"));
            pb.directory(new File("C:\\Users\\NASA\\Documents\\NetBeansProjects\\EjerciciosServicios\\salidaGenerador\\"));
            pb.redirectError(new File("errores.txt"));
            pb.redirectOutput(new File(salida));
            pb.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int getResultadoGenerador_v2_FromFile(String fileName){
        int res = 0;
        try{
            FileInputStream f = new FileInputStream(fileName);
            InputStreamReader  fisr = new InputStreamReader(f);
            BufferedReader  buffer = new BufferedReader(fisr);
            String line = buffer.readLine();
            res = Integer.valueOf(line);
        }catch(FileNotFoundException e){
            System.out.println("Error porque no se puede abrir el fichero "+ fileName+"  "+e.getMessage());
        }catch(IOException ex){
            System.out.println("Error porque no hay anda en  "+ fileName +"  "+ex.getMessage());
        }
        return res;
    }
     public static  ArrayList<Integer> arrayGeneradores(String prefijo, int numFicheros){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=1; i<=numFicheros ; i++){
            String nombre = prefijo + "_" + String.valueOf(i)+".txt";
            System.out.println("Nombre Ficch "+nombre);
//            int valor = getResultadoGenerador_v2_FromFile(nombre);
//            System.out.println("Valor recuperado "+valor);
            res.add(getResultadoGenerador_v2_FromFile(nombre));
        }   
        return res;
     }

     
     
}
