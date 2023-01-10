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
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class a1_servidor {
        private int puerto;

    public a1_servidor(int puerto) {
        this.puerto = puerto;
    }
    
    public void escuchar(){
        
        System.out.println("Arrancando el servidor ...");
        ServerSocket socketEscucha=null;
        try {
            // Generamos un ServerSocket asociado a la direccion IP del host y a un puerto, 
            // es decir, la IP de nuestra maquina. 
            socketEscucha=new ServerSocket(this.puerto);
        } catch (IOException e) {
            System.out.println( "No se pudo poner un socket a escuchar en TCP "+this.puerto);
            return;
        }   
        try{
            while (true){ // Acepta una conexion de cliente tras otra
                // Con el metodo accept estamos indicando que el servidor esta disponible para
                // recibir peticiones. 
                Socket conexion = socketEscucha.accept();
                /**
                 *En este punto la ejecución quedará detenida a la espera de una petición   
                 * Una vez recibida la petición se crean los flujos de entrada y de salida de datos.
                 * En este ejemplo, los flujos de comunicación se establecen a través de objetos 
                 * de las clases abstractas lnputStream y OutputStream (que trabajan a nivel byte)
                 * Se transforma de bytes a caracteres con InputStreamReader y viceversa con OutputStreamReader
                 * Se transforman en lineas con BufferedReader
                 */
                System.out.println("Conexion Establecida. Podemos recibir peticiones!");
                InputStream is_cliente = conexion.getInputStream(); // Leemos lo que ponga el cliente.
                InputStreamReader isr = new InputStreamReader(is_cliente, "UTF-8" );

               //Flujos de líneas
                BufferedReader br = new BufferedReader(isr);
                String linea = br.readLine();
                String res = "El mensaje recibido en el servidor es "+linea+"\n";
                System.out.println("Recibido "+linea);

                /* Calculamos el resultado*/
                OutputStream os=conexion.getOutputStream();
                PrintWriter pw_servidor = new PrintWriter(os); // Lo que va a contestar el servidor. Flujo salida. 
                pw_servidor.write(res);
                pw_servidor.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Errores "+ e.getMessage());       
        }
    }
     public static void main(String[] args) throws IOException {
        a1_servidor s2 = new a1_servidor(6001);
        s2.escuchar();
     }
}
