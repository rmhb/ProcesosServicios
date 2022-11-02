/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecuMulti;

import java.util.concurrent.Exchanger;

/**
 *
 * @author NASA
 */
public class ej_Exanger_Task2 implements Runnable {
    private Exchanger<String> ex;

    public ej_Exanger_Task2(Exchanger<String> exchanger) {
        this.ex = exchanger;
    }
    public void run(){
        try{
            String smsRecibido = ex.exchange("Mensaje enviado Task2");
            System.out.println("Mensaje recibido en Task2 --> "+smsRecibido);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
   
}
