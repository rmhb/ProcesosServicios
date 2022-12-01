/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SpaPalacioPatos.v2;

import java.security.SecureRandom;

/**
 *
 * @author Nasa
 */
public class Masajista implements Runnable{
    
    private String nombre;
    private Boolean SPACerrado ;
    private SPA  spa;
    private SecureRandom generador;
    private int MAX_ESPERA_SEGS=5; // PAra esperar entre 1sg y 5 de forma aleatoria
    public Masajista(SPA spa,String nombre){
            this.nombre=nombre;
            this.spa=spa;
            this.generador=new SecureRandom();
            SPACerrado = false;
    }
    public void cerrarSPA(){
        SPACerrado = true;
    }
    public void esperarTiempoAzar(int max){
            /* Se calculan unos milisegundos al azar*/
            int threadSleepTime = (1+generador.nextInt(max))*1000;
            try {
                    Thread.currentThread().sleep(threadSleepTime);
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
    @Override
    public void run(){
            while (!SPACerrado){
                    int numCamilla=spa.atenderCliente();
                    while (numCamilla==-1){
                            /* Mientras no haya nadie a quien
                             * aatender hacemos otras cosas.
                             */
                            esperarTiempoAzar(MAX_ESPERA_SEGS);
                            numCamilla=spa.atenderCliente();
                    }
                    /* Si llegamos aqui es que había algún cliente
                     * Simulamos un tiempo de masage
                     */
                    esperarTiempoAzar(2*MAX_ESPERA_SEGS);
                    /* Tras ese tiempo de afeitado se
                     * libera la silla
                     */
                    spa.freeCamilla(numCamilla);
                    /* Y vuelta a empezar*/
            }
    }
}
