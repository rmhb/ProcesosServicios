/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosud3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

/**
 *
 * @author Nasa
 */
public class E2_socketServidorCalc {
    private int puerto;

    public E2_socketServidorCalc(int puerto) {
        this.puerto = puerto;
    }
    
    
    private int extraerNumero(String linea){
        /* 1. Comprobar si es un número
         * 2. Ver si el número es correcto (32a75)
         * 3. Ver si tiene de 1 a 8 cifras
         */
        int numero;
        try{
            numero=Integer.parseInt(linea);
        }
        catch (NumberFormatException e){
            numero=0;
        }
        /* Si el número es mayor de 100 millones no
         * es válido tampoco
         */
        if (numero>=100000000){
            numero=0;
        }
        return numero;
    }

    private int calcular(String op, String n1, String n2){
        int resultado=0;
        char simbolo=op.charAt(0);
        int num1=this.extraerNumero(n1);
        int num2=this.extraerNumero(n2);
        if (simbolo=='+'){
                resultado=num1+num2;
        }
        return resultado;
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
                String line=br.readLine();
                String num1=br.readLine();
                String num2=br.readLine();

                /* Calculamos el resultado*/
                Integer result=this.calcular(line, num1, num2);
                OutputStream os=conexion.getOutputStream();
                PrintWriter pw_servidor = new PrintWriter(os); // Lo que va a contestar el servidor. Flujo salida. 
                pw_servidor.write(result.toString()+"\n");
                pw_servidor.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Errores "+ e.getMessage());       
        }
    }
     public static void main(String[] args) throws IOException {
        E2_socketServidorCalc s2 = new E2_socketServidorCalc(9876);
        s2.escuchar();
     }
}
