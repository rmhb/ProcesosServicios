/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sincro;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author NASA
 */
public class Ejemplillos {
    
    //__________________/Ejemplos Iniciales con Clases Runtime y Process___________________________
    //_____________________________________________________________________________________________
    public void eje1(){
        try{
    
            // Ejecución proceso con RunTime y exec... ya en desuso porque ha sido sustituido por ProcessBuilder
                Runtime.getRuntime().exec("Notepad.exe");
                Runtime.getRuntime().exec("Notepad.exe notas.exe"); //*******  2. Igual pero pasandole parámetros al proceso
            //******* 3. También se puede invocar un proceso pasando un array de objetos
                String[] lanzaNotepad = {"Notepad.exe", "notas.txt"};
                Runtime.getRuntime().exec(lanzaNotepad);
            //******* 4. Ahora si pretencemos gestionar le proceso que hemos creado.... debemo sutilizar un objeto de la clase Process (que representa al proceso en ejecución)
                Process proceso = Runtime.getRuntime().exec(lanzaNotepad);
                int codigoFin = proceso.waitFor();
                System.out.println("El código de fin de mi proceso es: "+ codigoFin);
                System.out.println("El código de fin de mi proceso es: "+ proceso.exitValue());
        
        } catch(IOException | InterruptedException e){
            e.printStackTrace();
            // printStackTrace es un método que ayuda a diagnosticar el fallo donde se ha producido a la hora de ejecutar nuestro código.. ya que te dice qué ha pasado y dónde. 
            
            // Cualquiera de los múltiples errores que se pueden producir durante Ja creación y ejecución del proceso hará que se produzca una excepción, en general, de Ja clase IOException .
            // Si mientras utilizamos waitFor() se interrumpe le programa se genera excepción InterruptedException
        }
    }
    
    public void eje2(){
        try{
//    *********** Otros ejemplos para lanzar CMD ****************
            Process proceso1 = Runtime.getRuntime().exec("cmd /c echo %var1%", new String[]{"var1=value1"});
            printResults(proceso1);  
//            *** Hacere un ping ****
            Process process2 = Runtime.getRuntime().exec("ping www.google.com");
            printResults(process2);  
            
             String[] lanzaProceso = {"java", "sincro.LanzaProceso"};
             Process proceso = Runtime.getRuntime().exec(lanzaProceso);
             printResults(proceso);  
             
               
            int codigo = proceso.waitFor();
            if(codigo ==0){
                System.out.println("El proceso termino con exito");
            }
            else{
                System.out.println("Error, el proceso ha fallado con codigo "+ codigo);
                System.out.println(proceso.getErrorStream());

            }
            } catch(IOException | InterruptedException e){
            e.printStackTrace();
            // printStackTrace es un método que ayuda a diagnosticar el fallo donde se ha producido a la hora de ejecutar nuestro código.. ya que te dice qué ha pasado y dónde. 
            
            // Cualquiera de los múltiples errores que se pueden producir durante Ja creación y ejecución del proceso hará que se produzca una excepción, en general, de Ja clase IOException .
            // Si mientras utilizamos waitFor() se interrumpe le programa se genera excepción InterruptedException
        }

    
    }
    public void eje3(){
        try{
                // Ejemplo 3. ***
            // 3.1. Creación de procesos con ProcessBuilder
             //ProcessBuilder pb = new ProcessBuilder  ("cmd.exe", "/c", "start");
//             But when I initially call the cmd.exe using the /k (keep open) switch:
//             String[] commandList = {"cmd.exe", "/k", "dir"}; 
             new ProcessBuilder("Notepad.exe");
             // Este comando crea un proceso, pero no hace que se EJECUTE
             //3.2 La ejecución del proceso se realiza con startt()
            new ProcessBuilder("Notepad.exe","hola.txt").start();
            //3.3 Equivalentemente al metodo exec que usabamos en Runtime el método start en processBuilder retorna un proceso, luego podemos hacer para gestionarlo
            Process proceso = new ProcessBuilder("Notepad.exe", "hola.txt").start();
            // 3.4 Creación de subprocesos con Process Builder
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe");
            for(int i=0 ; i < 3 ; i++){
                pb.start();
            }
            // 3.5 Utilización del método environment que proporciona información sobre el entorno de ejecución del proceso
            ProcessBuilder pb1 = new ProcessBuilder("Notepad.exe", "yeah.txt");
            Map<String, String> env = pb1.environment();
            System.out.println("Directorio "+pb1.directory());
            System.out.println("Numero procesadores "+env.get("NUMBER_OF_PROCESSORS"));
            // Para listar todas las variables que hay en env: 
            for (Map.Entry<String, String> entry : env.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
            
            int codigo = proceso.waitFor();
            if(codigo ==0){
                System.out.println("El proceso termino con exito");
            }
            else{
                System.out.println("Error, el proceso ha fallado con codigo "+ codigo);
                System.out.println(proceso.getErrorStream());

            }
            } catch(IOException | InterruptedException e){
            e.printStackTrace();
            // printStackTrace es un método que ayuda a diagnosticar el fallo donde se ha producido a la hora de ejecutar nuestro código.. ya que te dice qué ha pasado y dónde. 
            
            // Cualquiera de los múltiples errores que se pueden producir durante Ja creación y ejecución del proceso hará que se produzca una excepción, en general, de Ja clase IOException .
            // Si mientras utilizamos waitFor() se interrumpe le programa se genera excepción InterruptedException
        }
    }
    public void eje4_paraAlumnos(){
        try{
          //*********** Running .bat and .sh Files ******************
            //Sometimes, it's just much easier to offload everything into a file and run that file instead of adding everything programmatically.
            //Depending on your operating system, you'd use either .bat or .sh files. Let's create one with the contents:   echo Hello World
            //Then, let's use the same approach as before:
            Process p1 = Runtime.getRuntime().exec("cmd /c start file.bat",null, new File("C:\\Users\\NASA\\Documents\\NetBeansProjects\\sincro\\src\\sincro\\script\\file.bat"));
            printResults(p1);        

            ProcessBuilder pb = new ProcessBuilder("Python","C:\\Users\\NASA\\Documents\\NetBeansProjects\\sincro\\src\\sincro\\script\\eje4.py" );
            pb.redirectErrorStream(true);
            Process proceso = pb.start();
            printResults(proceso);  
                   
            int codigo = proceso.waitFor();
            if(codigo ==0){
                System.out.println("El proceso termino con exito");
            }
            else{
                System.out.println("Error, el proceso ha fallado con codigo "+ codigo);
                System.out.println(proceso.getErrorStream());
            }
            } catch(IOException | InterruptedException e){
                e.printStackTrace();
                // printStackTrace es un método que ayuda a diagnosticar el fallo donde se ha producido a la hora de ejecutar nuestro código.. ya que te dice qué ha pasado y dónde. 

                // Cualquiera de los múltiples errores que se pueden producir durante Ja creación y ejecución del proceso hará que se produzca una excepción, en general, de Ja clase IOException .
                // Si mientras utilizamos waitFor() se interrumpe le programa se genera excepción InterruptedException
            }   
                  // -------------------------------------------------------------------------------------------------------------
          // Ejemplo 4 para los alumnos.
          //****************************************************
          //*****  Contenido del fichero eje4.pY
//            import json
//            import sys
//            pelicula = {"El Resplandor" :
//                {
//                    "Director" : "Kubrick",
//                    "Year":1980,
//                    "Reparto": [
//                        {"Nombre" :"Jack Nicholson"},
//                        {"Nombre" : "sbelley Duvall"} ,
//                        {"Nombre" : "DannyLloyd"},
//                        {"Nombre" : "scatman Crothers" }
//                    ]
//                }
//            }
//            print (json.dumps (pelicula ))
//            sys.exit(0)
          //*** Fin fichero eje4.py
          //
          //
        }
    

    
    
    
    public static void printResults(Process process) throws IOException {
        // InputStreamReader It reads bytes and decodes them into characters using a specified charset
        // BufferedReader Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines. 
        // getInputStream()  Returns the input stream connected to the normal output of the subprocess
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
    
}
