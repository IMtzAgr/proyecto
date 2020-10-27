package Proyecto;
import java.io.*;

/**
 *
 * @author ingiv
 * 
 *          EQUIPO 15
 * 
 *      Iván Martínez Aguirre
 *      Enrique Juárez Mendoza
 * 
 *      INSTRUCCIONES A IDENTIFICAR:
 * 
 *  AAM 	CMPSB 	POPF 	STI 	INT 	NOT 	
 *  
 *  AND 	CMP 	JG 	JNB 	JNLE 	JA
 * 
 * 
 */

public class GestionA {
     FileInputStream entrada;
     FileInputStream salida;
     File archivo;
     
     public GestionA(){
     }
     
     /*Abrir un archivo*/
     
     public String AbrirArchivo(File archivo){
     String contenido = "";
         try {
             entrada = new FileInputStream(archivo);
             int ascci;
             while((ascci = entrada.read())!=-1){
             char carcater = (char)ascci;
             contenido += carcater;
             
             }
         } catch (Exception e) {
         }
         return contenido;
     }
}
