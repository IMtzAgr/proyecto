package leerarhivo;
import java.io.*;

public class LeerArhivo {
    
    public static void main(String[] args) {
    
    }
    
    public void leerarchivo(){
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
        try {
            archivo= new File("c:\\archivo.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(br);
            
            //lectura del fichero 
            String linea;
            while((linea=br.readLine())!=null){
                System.out.println(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }
    
}
