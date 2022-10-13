/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecuMulti;

/**
 *
 * @author NASA
 */
public class CorredorRunnableObj implements Runnable{
    private String name;
    private int tiempoCorriendo;
    private int carrerasRealizadas;
    
    public CorredorRunnableObj(String name, int tiempoCorriendo) {
         // super();
        this.name = name;
        this.tiempoCorriendo = tiempoCorriendo;
        //this.carrerasRealizadas = 0;
    }

    public void corre(){
        try{
            System.out.println(this.name + " comienza a correr");
            Thread.sleep(this.tiempoCorriendo*1000);
            // Da el tiempo en milisegundo, lo multiplico por 1000 para que sean segundos
            System.out.println(this.name + " termina de correr");
            this.carrerasRealizadas++;
            System.out.println("Carreras corridas: "+ this.carrerasRealizadas);
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    // Ahora vamos a sobreescribir el método run() de los procesos, para que cuando pongamos start() directamente llame a corre
    @Override
    public void run(){
        this.corre();
    }
    
}
