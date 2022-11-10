/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ej13;
// SwingWorker subclass for calculating Fibonacci numbers
// in a background thread.
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import java.util.concurrent.ExecutionException;
/**
 *
 * @author Nasa
 */
//SwingWorker<Long, Object> -> El primer parametro indica el tipo de dato que va a devolver doInBackground. 
//El segundo parametro indica el tipo de dato que se pasa entre los metodos publish y process para el intercambio de resultados intermedios
//En este ejemplo se usa Object porque no vamos a utilziar ni publish ni process

//Esta clase puede ser instanciada por una clase que implemente una interfaz Swing.
public class FiboBackground extends SwingWorker<Long, Object> {
    private final int n; // Fibonacci number to calculate
    private final JLabel resultJLabel; // JLabel para mostrar el resultado (en este caso el fibonacci n)

    public FiboBackground(int n, JLabel resultJLabel) {
        this.n = n;
        this.resultJLabel = resultJLabel;
    }
    public Long doInBackground(){
        return fibonacci(n);
    }
    // code to run on the event dispatch thread when doInBackground returns
    protected void done(){
        try{
            // get the result of doInBackground and display it
            resultJLabel.setText(get().toString());
        }catch(InterruptedException ex){
            resultJLabel.setText("Hebra Interrumpida cuando estaba esperando a los resultados");
        }catch(ExecutionException e){
            resultJLabel.setText("Error mientras estaba calculando");
        }
    }
    // recursive method fibonacci; calculates nth Fibonacci number. Como estamos haciendo llamadas Recursivas... esto va a ser muy lento porque va a petar la pila. 
    public long fibonacci(long n){
        if(n == 0 || n == 1)
            return n;
        else
            return fibonacci(n-1)+fibonacci(n-2);
    }
    
    
    
    
}
