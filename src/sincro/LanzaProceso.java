
package sincro;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author NASA
 */
public class LanzaProceso {
     public static void main(String[] args) {
        //    1. Ejecución sin errores, pasar como argumento para el main desde la interfaz run-> Set project configuration -> arguments : notepad  también se puede probar con cmd /c
        //    2. Ejecución programa no existe: pasar como pargumento p_noExiste
        //    3. Ejecución proceso que termina en codigo error, pasar como argumento: cmd /c/nas dir

         if( args.length <= 0 ){
            System.out.println("Para que funcione indica comando a ejecutar: ");
            System.exit(1);
        }
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.inheritIO();
        try{
            Process p = pb.start();
            int codigo = p.waitFor();
            System.out.println("La ejecución de "+Arrays.toString(args)+
                    " devuelve código "+codigo+ " "+ (codigo == 0 ? " Accion completada correctamente " : " ERROR "));
        }catch(IOException e){
            System.out.println("Error durante la ejecución del proceso"); 
            e.printStackTrace();
            System.exit(2);
        }catch(InterruptedException e){
            System.err.println("Proceso Interrumpido");
            System.exit(3);
        }
    }
}
