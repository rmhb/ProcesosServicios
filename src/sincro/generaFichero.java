/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sincro;

import java.io.*;
/**
 *
 * @author NASA
 */
public class generaFichero {

    public static void saveToFile(String name){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(name), "utf-8"))) 
        {
                writer.write("Este texto está generado por el proceso generaFichero ");
                System.out.println("Parece que se genera");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    public static void saveToFile2 (){
        try{    
            PrintWriter writer = new PrintWriter("rosa.txt", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        }catch (Exception e ){
            System.out.println("Error "+ e.getMessage());

        }
    }
    
    
    public static void main(String[] args) {
            System.out.println("Entrando a la clase 2 java");
//            saveToFile("rosa.txt");
//            saveToFile2();
    } 
}
