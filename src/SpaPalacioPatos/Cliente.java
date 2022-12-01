/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SpaPalacioPatos;

/**
 *
 * @author Nasa
 */
public class Cliente implements Runnable{
        SPA spa;
        public Cliente(SPA spa){
                this.spa =spa;
        }
        public void run(){
                /* Los clientes no esperan que haya
                 * sillas libres, no hay bucle infinito.
                 * Si no hay sillas libres se van...
                 */
               int res=  spa.getCamilla();
               if(res==-1){ System.out.println("Se ha largado un cliente ");}
        }
}
