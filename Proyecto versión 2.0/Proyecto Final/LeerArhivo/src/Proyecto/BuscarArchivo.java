package Proyecto;
import static Proyecto.MenuPrincipal.buscarArchivo;
import static Proyecto.MenuPrincipal.buscarFormato;
import static Proyecto.MenuPrincipal.vercontenidoFolder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

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

public class BuscarArchivo {

    public BuscarArchivo(String dire) {
        File midir = new File(dire); // directorio actual, cambiarlo si se quiere
        if(vercontenidoFolder(midir)==false){
        JOptionPane.showMessageDialog(null,"No se encuentra ningun archivo" );
        PanelPrincipal pan= new PanelPrincipal();
        pan.setVisible(true);
        }
    }
 
    public static boolean vercontenidoFolder(File dir){
        String nombre_archivo= null,aux=null; boolean b=false;
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
                 b = true;
                if(b==true){
                int resp = JOptionPane.showConfirmDialog(null, "Deseas abrir este archivo: "+nombre_archivo);
                if(resp==0){ 
                //compararPalabra(aux);
                VentanaArchivo ven = new VentanaArchivo(aux); 
                ven.setVisible(true);
                }
                if((resp==1)||resp==2){
                    b=false;
                }
                }
                }else{
                System.out.println("La extension de este archivo no es .ens");
                b=false;
                }
            }  
          }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return b;
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
    boolean b = false; String ext = ".ens",exte=null; int can=0,i=0;
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