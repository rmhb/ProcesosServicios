/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej14;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
/**
 * Basicamente este programa lo que demuestra es como utilizando hebras lanzadas de forma asincrona,
 * implementadas a partir de completableFuture. El tiempo de calculo se reduce porque si lo comparamos 
 * con la ejecución secuencial, en la que primero se lanza una hebra y cuando esta devuelve 
 * el resultado final es cuando se comienza con la siguiente. 
 * El calculo de timepo final bothhilos se hace teniendo en cuenta en qué instante
 * se lanzó la primera hebra de las dos, es decir,  la mas antigua y el timepo en 
 * el que acabo la hebra mas tardona. Es decir, en sincronas basicamente sera 
 * la suma de ambos tiemps y en asincrona sera aproximadamente el tiempo de la
 * hebra que tarde mas porque se calculan a la vez.
 */
public class FiboCompletableFuture {
    public static void main(String[] args ) throws InterruptedException, ExecutionException{
        // Cálculo de forma síncrona del fibonacci de 40 y 45
        System.out.println(" ***** Calculo sincro Fibo 45 y 46....");
        myTimes synRes1 = startFibo(45);
        myTimes synRes2 = startFibo(46);
        double tiempoSyn = calculaTiempo(synRes1,synRes2);
        System.out.printf("Total tiempo de calculo = %.3f segundos %n", tiempoSyn);
        
        // Cálculo de forma asíncrona del fibonacci de 40 y 45
        System.out.println(" ***** Calculo asincrono Fibo 45 y 46....");
        CompletableFuture<myTimes> futuSynRes1 = CompletableFuture.supplyAsync(()-> startFibo(45));
        CompletableFuture<myTimes> futuSynRes2 = CompletableFuture.supplyAsync(()-> startFibo(46));
        // CompletableFuture lo que hace es enviar en varias hebras diferentes
        // de forma asincrona para la ejecucion de startFibo
        // Recoge el valor que returne startFibo en un objeto de tipo myTimes
        // Por si no lo saben, dentro de supplyAsync se utilizan lambdas
        // (sin parametros de ahí el () para llamar a startFibo
        
        myTimes AsynRes1 = futuSynRes1.get();
        myTimes AsynRes2 = futuSynRes2.get();
        // Cuando llamamos a get obtenemos los resultados de tareas asincronas
        // con llamadas que implementan bloqueo del main hasta que se devuelven los resultados. 
        // Una vez que devuelven los resultados el main se sigue ejecutando, por eso tenemos los datos disponibles. 
        double tiempoASyn = calculaTiempo(AsynRes1,AsynRes2);
        System.out.printf("Total tiempo de calculo = %.3f segundos %n", tiempoASyn);
        
        System.out.println("Diferencia de tiempo en porcentaje ");
        String porcen = NumberFormat.getPercentInstance().format(tiempoSyn/tiempoASyn);
        System.out.printf("%nEl calculo con hilos sincronos fue %s "+" mayor que el asincrono", porcen);
        
 }
    
    private static myTimes startFibo(int n){
        myTimes timeData = new myTimes();
        System.out.println("Calculando el fibonacci de "+ n);
        timeData.inicio = Instant.now();
        long fiboValue = fibo(n);
        timeData.terminado = Instant.now();
        displayResultados(n, fiboValue, timeData);
        return timeData;
    }
    private static long fibo(long n){   
        if(n == 0 | n ==1 )
            return n;
        else
            return fibo(n-1)+fibo(n-2);
    }
    
    private static void displayResultados(int n, long value, myTimes timeData){
        System.out.printf("   fibo(%d) =%d%n ", n, value);
        System.out.printf("Tiempo empleado para fibo(%d) = %.3f segundos%n ", n, timeData.tiempoSegundos());
        
    }
    private static double calculaTiempo(myTimes res1, myTimes res2){
        myTimes ambosHilos = new myTimes();
        // Para ver quien empieza antes
        ambosHilos.inicio = res1.inicio.compareTo(res2.inicio) < 0 ? res1.inicio : res2.inicio;
        // Para el que acaba después
        ambosHilos.terminado = res1.terminado.compareTo(res2.terminado)  > 0 ? res1.terminado : res2.terminado;
        return ambosHilos.tiempoSegundos();
    }
}
class myTimes{
    public Instant inicio;
    public Instant terminado;
    public double tiempoSegundos(){
        return Duration.between(inicio,terminado).toMillis()/1000;
    }
}
