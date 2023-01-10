/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosud3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Nasa
 */
public class E2_socketClienteCalc {
    
    String destino;
    int puertoDestino;
    Socket socket;
    InetSocketAddress direccion;
    int num1, num2;

    
    /**
     * El cliente crea un socket indicando nombre de l servidor o la dirección IP 
     * Si se ejecuta el cliente y el servidor en la misma máquina se 
     * puede usar localhost 0 la IP 127.0.0.1 como nombre del host.
     */
    public E2_socketClienteCalc(String dest, int port, int num1, int num2) {
        this.destino = dest;
        this.puertoDestino = port;
        this.socket = new Socket();
        this.num1 = num1;
        this.num2 = num2;
        this.direccion = new InetSocketAddress(destino, puertoDestino);       
    }
    
     public E2_socketClienteCalc() {
        this.destino = "192.168.15.92";
        this.puertoDestino = 2001;
        this.socket = new Socket();
        this.direccion = new InetSocketAddress(destino, puertoDestino);       
    }
    
    public void conector(){
        try {
            System.out.println("Estableciendo conexion ... ");
            /**
            * Iniciamos una comunicacion haciendo uso del metodo connect. 
            * Al intentar establecer una conexion, si esta falla, genera una excepción del tipo IOException
            * - La conexión no se pudo establecer.
            * - Aunque la conexión se estableció no fue posible leer o escribir datos.
            */
            socket.connect(direccion);
            //Si llegamos aquí es que la conexión se ha establecido

            InputStream is=socket.getInputStream();
            OutputStream os=socket.getOutputStream();

            System.out.println("Conexion realizada con exito!");
            //Flujos que manejan caracteres
            InputStreamReader isr = new InputStreamReader(is);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            
 

            //Flujos de líneas
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(osw);

            pw.print("+\n");
            pw.print(this.num1+"\n");
            pw.print(this.num2+"\n");
            pw.flush();
            String resultado=br.readLine();
            System.out.println("El resultado fue: "+resultado);

            pw.close();
            br.close();
            isr.close();
            osw.close();
            is.close();
            os.close();
        } catch (IOException e) {
            System.out.println(
                    "No se pudo establecer la conexion "+
                    " o hubo un fallo al leer datos."
            );
        } 
    }
   
}
