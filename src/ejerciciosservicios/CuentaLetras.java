/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosservicios;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.nio.file.Files;
/**
 *
 * @author NASA
 */
// Este ejercicio corresponde con el ejercicio 4 de la UD1
public class CuentaLetras {
    
         
     // Para ejercicio 44 ya hace falta esto: 
     public static ArrayList<String> leeFichero(String fileName){
        ArrayList<String> filas = new ArrayList<String>();
        try{
            FileInputStream f = new FileInputStream(fileName);
            InputStreamReader  fisr = new InputStreamReader(f);
            BufferedReader  buffer = new BufferedReader(fisr);
            String line = "";
            
            while((line = buffer.readLine()) != null){
                filas.add(line);                
            }
         }catch(FileNotFoundException e){
            System.out.println("Error porque no se puede abrir el fichero "+ fileName+"  "+e.getMessage());
        }catch(IOException ex){
            System.out.println("Error porque no hay anda en  "+ fileName +"  "+ex.getMessage());
        }
        return filas;
     }
    public static void escribeFichero(String fileName, String dato){
        try{

            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            BufferedWriter bufer = new BufferedWriter(fileWriter);
            bufer.write(dato);
        }catch(IOException e){
            System.out.println("Error al escribir fichero "+e.getMessage());
        }
    } 
     
    public static void hacerRecuento(String fileName, char letra, String outFileName  ){
        ArrayList<String> listaFrases =   leeFichero(fileName);
        Iterator<String> frase  = listaFrases.iterator();
        String fraseActual ="";
        int count = 0;
        while (frase.hasNext()) {
           fraseActual =frase.next();
            for(int i = 0; i < fraseActual.length(); i++) {    
                if(fraseActual.charAt(i) == letra )    
                    count++;    
            }    
        }
        System.out.println("Cuenta letra "+count);
        String dato = String.valueOf(count);
        System.out.println("Cuenta letra "+dato);
        escribeFichero(outFileName, dato);
        
    }
    public static void main(String[] args){
//        String inputFileName = args[0];
//        char letra = args[1].charAt(0);
//        String outputFileName =args[2];
        String inputFileName = "hola.txt";
        String outputFileName="salida.txt";
        String var = " ";
        char letra=var.charAt(0);
        hacerRecuento(inputFileName, letra, outputFileName);
        
    }
    
}
