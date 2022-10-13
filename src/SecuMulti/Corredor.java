package SecuMulti;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.lang.InterruptedException;
/**
 *
 * @author NASA
 */
public class Corredor {
    private String name;
    private int tiempoCorriendo;

    public Corredor(String name, int tiempoCorriendo) {
        this.name = name;
        this.tiempoCorriendo = tiempoCorriendo;
    }
    public void corre(){
        try{
            System.out.println(this.name + " comienza a correr");
            Thread.sleep(this.tiempoCorriendo*1000);
            // Da el tiempo en milisegundo, lo multiplico por 1000 para que sean segundos
            System.out.println(this.name + " termina de correr");
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
