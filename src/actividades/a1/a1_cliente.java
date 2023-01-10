/**
 * ENUNCIADO:
 * Intercambio de mensajes de texto entre cliente y servidor a traves de sockets TCP
 * Crear una aplicación cliente y una aplicación servidor que intercambien MENSAJES de texto
 * basando la comunicación en sockets TCP.
 */
package actividades.a1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nasa
 */
public class a1_cliente {
    String destino;
    int puertoDestino;
    Socket socket;
    String sms;
    InetSocketAddress direccion;
    
    /**
     * El cliente crea un socket indicando nombre de l servidor o la dirección IP 
     * Si se ejecuta el cliente y el servidor en la misma máquina se 
     * puede usar localhost 0 la IP 127.0.0.1 como nombre del host.
     */
    public a1_cliente(String dest, int port, String sms) {
        this.destino = dest;
        this.puertoDestino = port;
        this.sms = sms; 
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
            
            //Flujos que manejan bytes en el socket
            InputStream is=socket.getInputStream();
            OutputStream os=socket.getOutputStream();
            System.out.println("Conexion realizada con exito!");
            
            //Flujos que manejan caracteres
            InputStreamReader isr = new InputStreamReader(is);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            System.out.println("Cliente - Canales de Texto Creados");
            
            //Flujos de líneas
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(osw);
            System.out.println("Cliente - Flujos de linea establecidos");
            
            // Flujo de salida del cliente hacia el servidor. 
            System.out.println("Cliente - Enviando mensaje al servidor");
            pw.println(this.sms);
            pw.println("Tu puta madre ");
            pw.flush();
            System.out.println("Cliente - Mensaje enviado al servidor");
            
            // Flujo de entrada desde el servidor al cliente.
            System.out.println("Cliente - Recuperando respuesta del servidor");
            String resultado=br.readLine();
            System.out.println("Respuesta Recuperada: "+resultado);

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
