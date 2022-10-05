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
        this.powerShellScript = "C:\\Users\\Nasa\\Desktop\\prueba.ps1";
        this.argumentos = argumentos;
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
            int MaxTime = 500;
            ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c", "find", "caa", "*.*").inheritIO().redirectErrorStream(true);
            try{
              Process p =  pb.start();
              if(!p.waitFor(MaxTime, TimeUnit.MILLISECONDS)){
                  p.destroy();
                  System.out.println("El proceso no ha terminado en %d milisegundos");
              }
            }catch(IOException e){
                e.printStackTrace();
            }catch(InterruptedException e){
                System.out.println("Ejecucion interrumpida "+e.getMessage());
            }
            
            
        }
        
    
    
}
