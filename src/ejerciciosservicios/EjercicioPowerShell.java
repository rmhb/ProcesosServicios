/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosservicios;

import java.io.*;
import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Nasa
 */
public class EjercicioPowerShell {
    private String[] argumentos;
    private final String powerShellScript;

    public EjercicioPowerShell(String[] argumentos) {
        this.powerShellScript = "C:\\Users\\NASA\\Documents\\NetBeansProjects\\EjerciciosServicios\\src\\scripts\\prueba.ps1";
        this.argumentos = argumentos;
    }
    
    public static void printResults(Process process) throws IOException {
        // A partir del stream binario que genera getInputStream se puede construir un stream de texto y sobre estos un stream de Buffers. Así obtenemos la salida como texto y línea a línea
        // InputStreamReader It reads bytes and decodes them into characters using a specified charset. 
        // BufferedReader Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines. 
        // getInputStream()  Returns the input stream connected to the normal output of the subprocess. Metodo de la clase Process que devueve un estream de entrada conectado con la salida estándar del proceso
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
    public static void printSTDOUT(Process process){
        String output;
        try (InputStream processStdOut = process.getInputStream()) {
            output = new String(processStdOut.readAllBytes());
            System.out.println(output);
        }catch(IOException e){
            System.out.println("CAgada la leer la salida de datos "+e.getMessage());
        }
    
    }
    public void lanzaProceso(){
        if ( this.argumentos.length <= 0) {
            System.out.println ( "Debe indicarse comando a ejecutar." );
            System.exit(1);
        }
        ProcessBuilder pb = new ProcessBuilder ( this.argumentos );
        pb.inheritIO();
        try {
            Process p = pb.start();
            int codRet = p.waitFor();
            System.out.println("La ejecución de "+ Arrays.toString(this.argumentos) + " devuelve " + codRet+" "+ (codRet==0 ? " Se ha ejecutado el proceso con éxito " : "Bad error" ));
        }catch ( IOException e ) {
            System.err.println ( "Error durante ejecución del proceso" );
            e.printStackTrace();
            System.exit(2);
        } catch ( InterruptedException e ) {
            System.err.println ( "Proceso interrumpido" );
        }
    }
        public void lanzaPowerShell(){
            try{
//                ProcessBuilder pb = new ProcessBuilder  ("cmd.exe", "/c", "start");
                Process p = new ProcessBuilder  ("powershell.exe", "-Command", "dir","C:\\Users\\Nasa\\Desktop" ).start();
                printResults(p);
                ProcessBuilder pb = new ProcessBuilder  ("powershell.exe", "-Command", powerShellScript);
                Process proceso = pb.start();
                
                while(proceso.isAlive()){
                    Thread.sleep( 3000);
                    System.out.println("El proceso esta vivo");
                 //   printResults(proceso);
                }
                int codigo = proceso.waitFor();
                System.out.println(codigo==0 ? " Se ha ejecutado el proceso con éxito " : "Bad error" );
            } catch(IOException | InterruptedException e){
                System.out.println(" Error Fatal, "+e.getMessage());
            }
            
        }
        public void usoInherit(){
        // Por lo que he entendido inheritIO lo que hace es heredar la entrada y salida estandar o de errores que tuviera el proceso padre. Basicamente para después poder hacer test y ver donde está el problema.
            try{
                ProcessBuilder builder = new ProcessBuilder().command("cmd.exe", "/C", "dir").inheritIO();
                builder.redirectOutput(ProcessBuilder.Redirect.PIPE);
                Process process = builder.start();
                printSTDOUT(process);
                process.waitFor();
            }catch(Exception ex){
                System.out.println("Excapción en el proceso "+ex.getMessage());   
            }
        }
        public void usoInheritError(){
        // En este metodo vamos a utilizar un método de inherit que redirija la salida de error con redirectErrorStream(true). También vamos a utilizar un 
        
        /**
         Los mecanismos para redireccionar stdin, stdout, stderror son:
            1. El método inheritIO( ). Esta es Ja solución más sencilla. La entrada y la salida estándares y de error de los procesos creados se enlazan con las del proceso actual.
            2. Los métodos 
                • redirectlnput ( ) redirige Ja entrada desde: La entrada estándar del padre con redirect.INHERIT o bien desde un objeto File
                • redirectOutput ( ) redirige la salida estándar de los nuevos procesos creados hacia: 
                    *   La salida estándar del proceso padre con redirect.INHERIT 
                    *   Un fichero, borrando contenidos actuales del fichero con f, de clase File.
                    *   Un fichero, añadiendo a sus contenidos actuales con Redirect . appendTo ( f) .
                    *   Ninguna parte, con lo que se descarta sin más con Redirect.DISCARD.
                · redirectError ( ) funciona como redirectOutput ( ) , pero para la salida de error. 
         */
        
        
        
            int MaxTime = 3000;
//          ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-Command", powerShellScript).inheritIO().redirectErrorStream(true); // Con el inheritIO veo lo que ha hecho el proceso del cmd junto con el redirectStream
            ProcessBuilder pb= new ProcessBuilder("powershell.exe", "-Command", powerShellScript);
            pb.redirectOutput( ProcessBuilder.Redirect.INHERIT); // eSTA ES OTRA forma de redireccionar la salida de los procesos hijos hacia los padres. 
            try{
              Process p =  pb.start();
//              printSTDOUT(p);
              if(!p.waitFor(MaxTime, TimeUnit.MILLISECONDS)){
                  p.destroy();
                  System.out.println("El proceso no ha terminado en "+ MaxTime+" milisegundos");
              }
            }catch(IOException e){
                e.printStackTrace();
            }catch(InterruptedException e){
                System.out.println("Ejecucion interrumpida "+e.getMessage());
            }
        }
        public void EjercicioAlCambiaDirectorioProceso(){
            // Prmero vamos a imprimir cual es el directorio del proceso 
            ProcessBuilder pb1 = new ProcessBuilder().command("cmd.exe", "/C", "dir");
            try{
                
                System.out.println("Directorio "+pb1.directory());
                Process p1 = pb1.start();
                printResults(p1);
                pb1.directory(new File("C:/"));
                System.out.println("Directorio "+pb1.directory());
                Process p = pb1.start();
                printResults(p);
            }catch (IOException e){
                System.out.println("Error "+e.getMessage());
            }
        }
        public void rescribeEntrada(ProcessBuilder pb) throws IOException{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            String line = "";
            System.out.println("Introduce dominio: ");
            while ((line = reader.readLine()) != null && line.length() != 0 ) {
                Process p = pb.start();
                try( OutputStream os = p.getOutputStream();
                    OutputStreamWriter writter = new OutputStreamWriter(os, "UTF-8"); ){
                    writter.write(line);
                }
                try{
                    p.waitFor();
                }catch(InterruptedException e){
                    System.out.println("Ejecución interrumpida " + e.getMessage());
                }
                System.out.println("Nombre dominio ");
            }
        }
        public void ejemploNSLOOKUP(){
            
            ProcessBuilder pb = new ProcessBuilder("nslookup");
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
           
            try{
                 rescribeEntrada(pb);

            }catch (IOException e){
                System.out.println("Error "+e.getMessage());
                e.printStackTrace();
            }
        }
        
    
    
}
