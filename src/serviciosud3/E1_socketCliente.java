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
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Para crear un socket cliente lo que necesitamos es
 *  - Una dirección con la que contactar. ej: www.google.es o 152.17.241.20
 *  - Un puerto. Ej: 80, 21, 3306
 */
public class E1_socketCliente {
    String destino;
    int puertoDestino;
    Socket socket;
    InetSocketAddress direccion;

    public E1_socketCliente(String dest, int port) {
        this.destino = dest;
        this.puertoDestino = port;
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
            //Si llegamos aquí es que la conexión
            //sí se hizo.

            InputStream is=socket.getInputStream();
            OutputStream os=socket.getOutputStream();

            System.out.println("Conexion realizada con exito!");
            //Flujos que manejan caracteres
            InputStreamReader isr = new InputStreamReader(is, "UTF-8" );
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8" );

            //Flujos de líneas
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(osw);

            pw.println("GET /index.html");
            pw.flush();
            String line;
            FileWriter escritorArchivo = new FileWriter("resultado.txt");
            while ((line=br.readLine()) != null ){
                escritorArchivo.write(line);
            }
            //Thread.currentThread().sleep(3000);
            escritorArchivo.close();
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
//        catch (InterruptedException ex) {
//            Logger.getLogger(E1_socketCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
