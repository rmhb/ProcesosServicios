/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serviciosud2;

import Ej10.Buffer;
import Ej10.Cliente;
import Ej10.Proveedor;
import Ej11.SynBuffer;
import Ej11.Test;
import Ej15.sumaVector;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import SecuMulti.*;
import SpaPalacioPatos.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author NASA
 */
public class ServiciosUD2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //  //*** Ejemplo 1
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

//        //  //***Ejemplo 2
//        // Ejemplo de programación multihilo. En este caso, todos los corredores comienzan a correr a la vez... algunos tardan más y otros menos. 
//        SecuMulti.CorredorMulti p11 = new SecuMulti.CorredorMulti("Sergio", 6);
//        SecuMulti.CorredorMulti p21 = new SecuMulti.CorredorMulti("Alex", 2);
//        SecuMulti.CorredorMulti p31 = new SecuMulti.CorredorMulti("Lolo", 10);
//        p31.start();
//        p11.start();
//        p21.start();
        
        // //***  Ejemplo 3
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


////        //*** El ejemplo 4. Muestra la implementación de un hilo mediante la interfaz Runnable y se crean múltiples hilos a partir de un único objeto. 
//        
//        System.out.println("Ejemplo 4. Uso interfaz Runnable para lanzar varios hilos sobre un mismo objeto");
//        
//        SecuMulti.CorredorRunnableObj p13 = new SecuMulti.CorredorRunnableObj("Alain", 3);
//        new Thread(p13).start();
//        new Thread(p13).start();
//        new Thread(p13).start();
//        new Thread(p13).start();
//        // Con esto hemos conseguido que se ejecute el mismo trozo de codigo (metodo run) sobre el mismo objeto 4 veces. Como podemos ver sus atributos se han compartido, veremos mas adelante los posibles errores que genera esta concurrencia. 
        
        // //*** Ejemplo 5. Si lanzamos en un buccle 100 Thread podremos apreciar cccomo el numero de ccarreras corridas no llega a ser 100, estto se debe a que los hilos comparten los atributos. El último valor vistto no será 100, hay problema dee sincronización. 
//        
//         System.out.println("Ejemplo 5.. Uso interfaz Runnable para lanzar varios hilos sobre un mismo objeto en un bucle y mostrar errores de concurrenccia");
//         SecuMulti.CorredorRunnableObj p5 = new SecuMulti.CorredorRunnableObj("Alain", 3);
//         for (int i = 0; i < 1000; i++) {
//            new Thread(p5).start();
//        }


//         // Para ver la cagada de sincronización. Con esto muchas de las hebras imprimirán el mismo valor por panttalla quedandose siempre por debajo de 1000. Debo introducir una operación de E/S antes del incremento de las carreras corridas en CorredorRunnableObj. Es decir, que mueva el código a: 
////      public void corre(){
////        try{
////            System.out.println(this.name + " comienza a correr");
////            Thread.sleep(this.tiempoCorriendo*1000);
////            // Da el tiempo en milisegundo, lo multiplico por 1000 para que sean segundos
////            this.carrerasRealizadas++; /// Con esto hago el incremento y después una operación de E/S que es costossa...
////            System.out.println(this.name + " termina de correr");
////            System.out.println("Carreras corridas: "+ this.carrerasRealizadas);
////            
////        }catch(InterruptedException e){
////            e.printStackTrace();
////        }
////    }
        

////  //*** Ejemplo 6. Estados de una hebra: 
//        SecuMulti.CorredorRunnableObj p6 = new SecuMulti.CorredorRunnableObj("Pronto", 3);
//         ArrayList<Thread.State> estados = new ArrayList();
//         Thread h1 = new Thread(p6);
//         estados.add(h1.getState());
//         h1.start();
//         while(h1.getState() != Thread.State.TERMINATED){
//             if(!estados.contains(h1.getState()))
//                 estados.add(h1.getState());
//         }
//        if(!estados.contains(h1.getState()))
//            estados.add(h1.getState());
//        for(Thread.State estado : estados){
//            System.out.println(estado);
//        }
   

// Ejemplo 6B
// Declaración de un array de objetos de tipo Ej6B: 
//Student[ ] studentObjects;  or Student studentObjects[];
//Instantiate the array of objects – Class_Name obj[ ]= new Class_Name[Array_Length];
//
//    Ej6B arrayHilos[]=new Ej6B[5];
//    for (int i=0; i<5;i++){
//            arrayHilos[i]=new Ej6B();
//            Thread hilo=new Thread(arrayHilos[i]);
//            hilo.setName("Hilo "+i);
//            if (i==0){
//                hilo.setPriority(Thread.MAX_PRIORITY);
//            }
//            hilo.start();
//    }


////  //*** Ejemplo 7. Proggramación de tareas con Timer y TimerTask
//    ProcessBuilder pb = new ProcessBuilder("java", "src\\SecuMulti\\ejTimer.java");
//    pb.redirectOutput(new File("dalida.txt"));
//    try {
//        pb.start();
//     //       System.out.println("");
//    }catch(IOException e){
//        System.out.println("Error");
//    }
//    // Para carggarmelo buscar un proceso que ponga JAva... bla bla

////  //*** Ejemplo 8. Ejemplo de implemntaciónd e Runnablee y ejecución con executor. 
//    
//     PrintTask task1 = new PrintTask("task1");
//     PrintTask task2 = new PrintTask("task2");
//     PrintTask task3 = new PrintTask("task3");
//     System.out.println("Llamando a Executor...");
//     ExecutorService exSer = Executors.newCachedThreadPool();
//     //Ejecutar el argumento Runnable (en este caso, un objeto PrintTask que implementa runnable)
//     exSer.execute(task1); 
//     exSer.execute(task2);
//     exSer.execute(task3);
//     exSer.shutdown(); // El mismo decide cuando cancelar las hebras. 
//     //Impide que se acepten nuevas task, pero continua ejecutando las existentes. 
//     System.out.println("Tareas lanzadas, fin del código en el main....");   
//    // El programa termina, no espera a que terminen las tareas lanzadas. 
//    // El ExecutorService terminará cuando las task1-3 hayan acabado. 
    
//    //*** Ejemplo 9. Problema de concurrencia 
//    
//    ej9_ProbConcuArray elArray = new ej9_ProbConcuArray(6);
//    ej9_ArrayWriter w1 = new ej9_ArrayWriter(elArray, 21 );
//    ej9_ArrayWriter w2 = new ej9_ArrayWriter(elArray, 11);
//    ExecutorService exSer = Executors.newCachedThreadPool();
//    exSer.execute(w1);
//    exSer.execute(w2);
//    exSer.shutdown(); 
//    try{
///**
// * Si recordamos executorService una veez haces shutdown regresa al programa principal y,
// * deja el hilo ejecutandose. 
// * Con awaitTermination la hebra de este programa se espera a que termine las que ha lanzado,
// * así pdoemos ver el resultaldo real del array una vez han terminado de ejecutarse las dos 
// * hebras o el tiempo máximo de ejecución se agote. 
//*/
//        boolean finTask = exSer.awaitTermination(1, TimeUnit.MINUTES);
//        if(finTask){
//            System.out.printf("%nContenido del array: %n");
//            System.out.println(elArray);
//        }else{
//            System.out.println("Tiempo de espera agotado, la tarea no termina");
//        }
//    }catch(InterruptedException e){
//        e.printStackTrace();
//    }    
//// // En la salida debemos ver cómo se sobreescriben valores. 
    
 
// //** Ejemplo 9-B uso de synchronized.
//    ej9_SynConcuArray1 a1 = new ej9_SynConcuArray1(6);
//
//    ej9B_ArrayWriter1 w1 = new ej9B_ArrayWriter1(a1, 1 );
//    ej9B_ArrayWriter1 w2 = new ej9B_ArrayWriter1(a1, 11);
//    ExecutorService exSer = Executors.newCachedThreadPool();
//    exSer.execute(w1);
//    exSer.execute(w2);
//    exSer.shutdown(); 
//    try{
////      Si recordamos executorService una veez haces shutdown regresa al programa princippal y deja el hilo ejecutandose. Con awaitTermination la hebra de este programa se espera a que termine las que ha lanzado, así pdoemos ver el resultaldo real del array una vez han terminado de ejecutarse las dos hebras o el tiempo máximo de ejecución se agote. 
//        boolean finTask = exSer.awaitTermination(1, TimeUnit.MINUTES);
//        if(finTask){
//            System.out.printf("%nContenido del array: %n");
//            System.out.println(a1);
//        }else{
//            System.out.println("Tiempo de espera agotado, la tarea no termina");
//        }
//    }catch(InterruptedException e){
//        e.printStackTrace();
//    }
//

   
    
    // **** Ejemplo 10. Proveedor Cliente sin sincronización
//     new Ej10.Test().runTest_unsyn();
     // **** Ejemplo 10_B. Proveedor Cliente con sincronización
//     new Ej10.Test().runTest_syn();
     
     // **** Ejemplo 11. Proveedor Cliente con sincro propia.. Buffer de un entero
//     new Ej11.Test().runTestSynBuffer();

// Ejemplo 11-C
//        new Ej11.Test().testBufferCircular();
        
        
    // Ejemplo 12
//        new Ej12.Test12().runTestSynBufferLockCondi();
        
    // Ejemplo 13
//        System.out.println("Ejemplo 13. Interfaz swing con hilos");
//        new Ej13.InterfazFibonacci().setVisible(true);


    // Ejemplo 14. Fibonnaci
    
//Ejecutar directamente el fichero... paso de implementarlo aquí
    
// Ejemplo coon Exchanger
    
//    Este ejemplo funciona, porque el objeto sobre el que se intercambia información es el objeto ex, que es común tanto para task1 como para task2 al haberlo usado en la creación de las clases
//    Exchanger<String> ex = new Exchanger<>();
//    new Thread(new ej_Exanger_Task1(ex)).start();
//    new Thread(new ej_Exanger_Task2(ex)).start();

    // Actividades***********************************
       
     // Act 2.2
//        Actividades.act2_2.Parcela unaParcela;
//        ExecutorService exSer = Executors.newCachedThreadPool();
//        for (int i = 0; i < 20; i++) {
//            
//            unaParcela = new Actividades.act2_2.Parcela(new Random().nextLong(10000-500)+500,
//                    new Random().nextLong(10000-500)+500,new Random().nextLong(10000-500)+500,
//                   new Random().nextLong(10000-500)+500, "Parcela_"+i);
//            exSer.execute(unaParcela);
//        }
//
//        exSer.shutdown();

// Actividad primos
//
//    ActPrimos.InterfazPrimos application = new ActPrimos.InterfazPrimos();
//    application.setVisible(true);
//    application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    // Ejemplo 15. Clase RecursiveTask, extiende de ForkJoinTask que implementa Future.
    
//    /* Construimos un vector de ejemplo...*/
//        final int MAX_NUMEROS=32;
//        SecureRandom generador = new SecureRandom();
//        int[] numeros=new int[MAX_NUMEROS];
//
//        /*Y lo rellenamos con números*/
//        for (int i=0; i<MAX_NUMEROS; i++) {
//           
//            numeros[i]=generador.nextInt(100);
//        }
//        int[]  miArrayNumeros = IntStream.generate(generador::nextInt).limit(MAX_NUMEROS).toArray();
//        System.out.println(Arrays.toString(miArrayNumeros));
//        System.out.println(Arrays.toString(numeros));
//
//        /* Esta clase gestionará el paralelismo de las tareas.
//        Es una pool de subprocesos. Estos son ligeros ya que se 
//        generan a partir de un fork del padre.*/
//        ForkJoinPool pool=new ForkJoinPool();
//
//        /* Fabricamos un sumador inicial que intente sumar todo*/
//        sumaVector sumador=new sumaVector(numeros, 0, 16);
////        sumaVector sumador1=new sumaVector(miArrayNumeros, 0, (int) Math.floor(MAX_NUMEROS/2));
//           sumaVector sumador1=new sumaVector(miArrayNumeros, 0, MAX_NUMEROS-1);
//        /*Y la clase ForkJoinPool invocará a nuestro sumador lanzando
//        los hilos, recogiendo los resultados y haciendo todo lo necesario
//        para que al final solo tengamos que recoger el resultado*/
//        pool.invoke(sumador);
//        pool.invoke(sumador1);
//
//        /* Resultado que podemos ver aquí*/
//        Long resultado,res2;
//        try {
//            resultado = sumador.get();
//            res2 = sumador1.get();
//            System.out.println("La suma es:"+resultado);
//            System.out.println("La otra suma es:"+res2);
//        } catch (InterruptedException | ExecutionException ex) {
//            Logger.getLogger(ServiciosUD2.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        


/**
 * Actividad SPA PAlacio Patos. 
 * El problema de esta solución es que siempre comenzamos a atender las camillas por la posición inicial
 * Con lo que puede que haya clientes que nunca se atiendan
 * */
        SpaPalacioPatos.test t = new SpaPalacioPatos.test();
//        t.runV1(); // Esta solución sufre de inanición
//        t.runV2();
           SpaPalacioPatos.test.testSPAv3();
        
    }
    
}
