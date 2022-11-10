/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecuMulti;

/**
 *
 * @author Nasa
 */
public class Ej6B implements Runnable{

    @Override
    public void run() {
            int n=0;
            while(n<5){
                    System.out.println("Antes de crear el hilo ...");
                    try {
                            long tiempo=(long) (1000*Math.random()*10);
                            if (tiempo>8000){
                                    Thread esteHilo=Thread.currentThread();
                                    System.out.println("Terminando hilo: "+esteHilo.getName() );
                                    esteHilo.join();
                            }
                            Thread.sleep(tiempo);
                    } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
                    System.out.println("Vuelta "+n);
                    n++;
            }
            Thread esteHilo=Thread.currentThread();
            String miNombre=esteHilo.getName();
            System.out.println("Hilo terminado: "+miNombre);
    }
}


    
