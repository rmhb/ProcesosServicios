/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serviciosud2;

/**
 *
 * @author NASA
 */
public class ServiciosUD2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejemplo 1
        // Ejemplo de programación secuencia. Cada corredor corre durante el tiempo indicado. Cada sentencia debe esperar a que la anterior termine
        // El tiempo de ejecución del programa sera la suma de los tiempos parciales de cada corredor. Luego no se aprovechan realmente los recursos del sistema con este tipo de programación.  (Imagina hacer ejecución secuencial en un e-commerce).
//        SecuMulti.Corredor p1 = new SecuMulti.Corredor("Isma", 6);
//        SecuMulti.Corredor p2 = new SecuMulti.Corredor("Dario", 2);
//        SecuMulti.Corredor p3 = new SecuMulti.Corredor("Laura", 10);
//        p3.corre();
//        p1.corre();
//        p2.corre();
//        
//        System.out.println("Ejemplo de program multihilo");
//        // Ejemplo 2
//        // Ejemplo de programación multihilo. En este caso, todos los corredores comienzan a correr a la vez... algunos tardan más y otros menos. 
//        SecuMulti.CorredorMulti p11 = new SecuMulti.CorredorMulti("Sergio", 6);
//        SecuMulti.CorredorMulti p21 = new SecuMulti.CorredorMulti("Alex", 2);
//        SecuMulti.CorredorMulti p31 = new SecuMulti.CorredorMulti("Lolo", 10);
//        p31.start();
//        p11.start();
//        p21.start();
        
        // Ejemplo 3
//        System.out.println("Ejemplo 3. Uso interfaz Runnable");
//        SecuMulti.CorredorRunnable p12 = new SecuMulti.CorredorRunnable("Nuria", 6);
//        SecuMulti.CorredorRunnable p22 = new SecuMulti.CorredorRunnable("Albert", 2);
//        SecuMulti.CorredorRunnable p32 = new SecuMulti.CorredorRunnable("Dani", 10);
//        
//        new Thread(p12).start();
//        new Thread(p22).start();
//        new Thread(p32).start();

        // Los objetos Runnable se deben envolver con objetos Thread para poder ser arrancados. 
//        Es mejor implementar la interfaz runnable que heredar de la clase Thread, porque java no permite herencia de múltiples clases. Luego si heredamos de Thread, ya estamos limitados porque no podemos heredar de otra 
//        Otra razón es que a partir de la interfaz Runnable se pueden lanzar muchos hilos de ejecución sobre un único objeto. Sin embargo, si lo hacemos con Thread se genera un objeto por cada hilo. 


//        El ejemplo 4. Muestra la implementación de un hilo mediante la interfaz Runnable y se crean múltiples hilos a partir de un único objeto. 
        
        System.out.println("Ejemplo 4. Uso interfaz Runnable para lanzar varios hilos sobre un mismo objeto");
        
        SecuMulti.CorredorRunnableObj p13 = new SecuMulti.CorredorRunnableObj("Alain", 3);
        new Thread(p13).start();
        new Thread(p13).start();
        new Thread(p13).start();
        new Thread(p13).start();
        // Con esto hemos conseguido que se ejecute el mismo trozo de codigo (metodo run) sobre el mismo objeto 4 veces. Como podemos ver sus atributos se han compartido, veremos mas adelante los posibles errores que genera esta concurrencia. 
        
        
    }
    
}
