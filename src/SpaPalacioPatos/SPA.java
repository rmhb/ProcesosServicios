/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SpaPalacioPatos;

import java.util.Arrays;

/**
 *
 * @author Nasa
 */
public class SPA {
        /**
         * Vector que indica las camillas masaje libres
        */
        boolean[] camasLibres;
        /**
         * Vector que indica un cliente que ya esta en la camilla ha sido atendido
        */
        boolean[] clienteAtendido;

        public SPA(int numCamillas){
                camasLibres = new boolean[numCamillas];
                clienteAtendido = new boolean[numCamillas];
                Arrays.fill(camasLibres, true);
                Arrays.fill(clienteAtendido, false);
        }

        /**
         * Permite obtener una silla libre, usado por la
         * clase Cliente para saber si puede sentarse
         * en algún sitio o irse
         * @return Devuelve el número de la primera silla
         * que está libre o -1 si no hay ninguna
         */
        public synchronized int getCamilla(){
                for (int i=0; i<camasLibres.length; i++){
                        if (camasLibres[i]) {
                                /* ...se marca como ocupada*/
                                camasLibres[i]=false;
                                System.out.println("La camilla  "+i+" acaba de ser ocupada" );
                                return i;
                        }
                }
                /* Si llegamos aquí es que no había nada libre*/
                return -1;
        }

        /**
         * Nos dice qué camilla tiene algun cliente
         * que no está atendido
         * @return un número de silla o -1 si no
         * hay clientes sin atender
         */
        public synchronized int atenderCliente(){
                for (int i=0; i<camasLibres.length; i++){
                        /* Si una silla está ocupada (no libre, false)
                         * y está marcado como "sin atender" (false)
                         * entonces la marcamos como atendida
                         */
                        if (clienteAtendido[i]==false){
                                clienteAtendido[i]=true;
                                System.out.println("El cliente en camilla "+i+" está siendo atendido");
                                return i;
                        }
                }
                return -1;
        }

        /* El cliente de esa silla, se marcha, por lo
         * que se marca esa silla como "libre"
         * y como "sin atender"
         */
        public synchronized void freeCamilla(int i){
                camasLibres[i] = true;
                clienteAtendido[i] = false;
                System.out.println("Cliente que había en camilla  "+i+" ha sido atendido y se queda libre la camilla");
        }
}
