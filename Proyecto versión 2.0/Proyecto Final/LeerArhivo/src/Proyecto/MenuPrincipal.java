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

public class MenuPrincipal {
     
    public static void main(String[] args) {
           File midir = new File("C:\\Users\\Sparking\\Videos"); // directorio actual, cambiarlo si se quiere
        vercontenidoFolder(midir);
    }
    public static void vercontenidoFolder(File dir){
        String nombre_archivo= null,aux=null;
        try {
            File[] files = dir.listFiles();
            for(File file: files){
            if(file.isDirectory()){
                System.out.println("directorio:"+ file.getCanonicalPath());
                vercontenidoFolder(file);
            }else{
                System.out.println("\narchivo: "+file.getCanonicalPath());
                nombre_archivo = file.getCanonicalPath();
                aux = nombre_archivo; //direccion del archivo
                //System.out.println("El nombre del archivo es "+buscarArchivo(nombre_archivo));
                nombre_archivo = buscarArchivo(nombre_archivo);
                if(buscarFormato(nombre_archivo)==true){
                System.out.println("El nombre del archivo es "+nombre_archivo+"");                   
                }else{
               System.out.println("La extensión de este archivo no es .asm");
                }
            }  
          }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    public static String buscarArchivo(String cadena){
    String arch=null; int can=0,i=0,b=0; 
    arch="\\23";
        can = cadena.length(); 
        //System.out.println("el tamaño de la cadena es: "+can);
        i = can-1; 
        do{
            //System.out.println("La letra es: "+cadena.charAt(i));
            //System.out.println("El valor de aech; "+arch.charAt(0));
         if(cadena.charAt(i) == arch.charAt(0)){
             b=1;
         }
         i--;
        }while((b==0)&&(i!=0));
        //System.out.println("El valor de i es: "+i);
        //System.out.println("El valor de can  es: "+can);
        //System.out.println("la cadena es: "+cadena.substring(i+2, can));
        arch =cadena.substring(i+2, can);
        return arch;
    }
    
    public static boolean buscarFormato(String cadena){
    boolean b = false; String ext = ".asm",exte=null; int can=0,i=0;
    can = cadena.length();
    i = can - 1;
    do{
        if(cadena.charAt(i)=='.'){
         exte=cadena.substring(i, can);
         b = true;
        }else{
        i--;
        }
    }while((b==false)&&(i!=0));
    if(i==0){
    b=false;
    }else{
    if(exte.equals(ext)){
    b=true;
    }else{
    b=false;
    }
    }
    return b;
    }   
}