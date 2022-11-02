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
public class ej_Exanger_Task1 implements Runnable {
    private Exchanger<String> ex;

    public ej_Exanger_Task1(Exchanger<String> exchanger) {
        this.ex = exchanger;
    }
    public void run(){
        try{
            String smsRecibido = ex.exchange("Este sms se genera y se envia en Task1");
            System.out.println("Mensaje recibido en Task1 <--> "+smsRecibido);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
   
}
