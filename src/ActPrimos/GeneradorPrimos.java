/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActPrimos;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import java.util.concurrent.ExecutionException;
import java.security.SecureRandom;
/**
 * Calculadora de primos con Criba Eratostenes
 */
public class GeneradorPrimos extends SwingWorker<Integer,Integer> {
    private static final SecureRandom generador = new SecureRandom();
    private final JTextArea intermediateJTextArea; // Muestra los primos encontrados
    private final JButton getPrimesButton;
    private final JButton cancelButton;
    private final JLabel status; // Muestra la barra progreso
    private final boolean[] primos; // Array con los primos encontrados

    public GeneradorPrimos(JTextArea intermediateJTextArea, JButton getPrimesButton, 
            JButton cancelButton, JLabel status, int max) {
        // Max será el número máximo hasta el que buscar primos
        this.intermediateJTextArea = intermediateJTextArea;
        this.getPrimesButton = getPrimesButton;
        this.cancelButton = cancelButton;
        this.status = status;
        this.primos = new boolean[max];
        Arrays.fill(primos, true); // Inicializar todo a true, para implementar
        // la criba de Eratosthenes para encontrar todos los primos menores que max. 
        // Lo que hacía era buscar el primer primo y tacha todos los múltiplos de dicho primo. 
        // Después busca el siguiente primo y vuelvee a tachar todos sus múltiplos y así sucesivamente. 
    }
    
    public Integer doInBackground(){
        int count = 0; // Numero primos encontrados
        for (int i = 2; i < primos.length; i++) {
            if(isCancelled())// Si Cancelamos la busqueda (metodo de SwingWorker)
                return count;
            else{
                setProgress(100*(i+1)/primos.length);
                try{
                    Thread.sleep(generador.nextInt(5));
                }catch(InterruptedException ex){
                    status.setText("Hebra worker interrumpida");
                    return count;
                }
                if(primos[i]){ // Si i es un primo
                    publish(i); // publica resultado parcial para ir mostrando los primos encontrados
                    ++count;
                    for (int j = i+i; j < primos.length; j+=i) {
                        primos[j]=false; // eliminar todos los múltiplos de i
                    }
                }
            }    
        }
        return count;
    }
    // Mostrar los valores intermedios en la lista de primos
    protected void process(List<Integer> publishedVals){
        for (int i = 0; i < publishedVals.size(); i++) {
            intermediateJTextArea.append(publishedVals.get(i)+"\n");
        }
    }
    // Cuando doInBackGround termina: 
    protected void done(){
        getPrimesButton.setEnabled(true); // Permitimos el botón Get primos
        cancelButton.setEnabled(false); // Deshabilitamos el botón cancelar
        try{
            status.setText("Encontrados "+get()+" primos");
        }catch(InterruptedException | ExecutionException | CancellationException ex){
            status.setText(ex.getMessage());
        }
    }
    
    
}
