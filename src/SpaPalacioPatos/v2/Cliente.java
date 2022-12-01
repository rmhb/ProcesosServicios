/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SpaPalacioPatos.v2;

/**
 *
 * @author Nasa
 */
public class Cliente implements Runnable {
        SPA spa;
        public Cliente(SPA spa){
                this.spa =spa;
        }
        public void entrarSalaMasajes(){
                int numCamillaLibre = this.spa.getCamilla();
                if (numCamillaLibre==-1){
                        System.out.println("No habia sillas libres, me marcho");
                        return ;
                }
                System.out.println("Me siento en la silla:"+numCamillaLibre);
        }
        @Override
        public void run(){
            entrarSalaMasajes();
        }
}
