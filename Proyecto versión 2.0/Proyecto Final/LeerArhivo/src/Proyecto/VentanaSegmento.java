package Proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaSegmento extends javax.swing.JFrame {
public String auxp,aux1="C:\\Users\\Public\\Documents\\Archivo_Sincomentarios.txt"; 
 public int v=0,con=0,tota=0,r=0,e=0,p=0;
    public VentanaSegmento() {
        initComponents();
    }

    public VentanaSegmento(String ruta) {
        initComponents();
        auxp=ruta;
        respaldarNuevoArchivo(procesoDeDatos());
        respaldarNuevoArchivo(ordenarArchivo(aux1));
        bloquearbotones(false);
    }
    
    //Funcion que bloquea y desbloque los botones 
    public void bloquearbotones(boolean v){
    if(v==false){
    boton01.setEnabled(false);
    boton02.setEnabled(false);
    boton03.setEnabled(false);
    boton04.setEnabled(false);
    boton05.setEnabled(false);
    boton06.setEnabled(false);
    boton07.setEnabled(false);
    boton08.setEnabled(false);
    boton09.setEnabled(false);
    boton10.setEnabled(false);
    }else{
    boton01.setEnabled(true);
    botonI.setEnabled(false);
    }
    
    }
    
    //Eliminar Comentariosc de las lineas 
    public String eliminarComentarios(String cade){
    String aux=""; int b=0,i=0,tam=cade.length();
    if(!cade.equals("")){
    do{
       if(cade.charAt(i)!=';'){
       aux=aux+cade.charAt(i);
       }else{
        b=1;
       }
       i++;
    }while((b!=1)&&(i<tam));    
    }
    return aux;
    }  
    
    //Eliminar datos de dos puntos y comas
    public String eliminarDatos(String cadena){
    String aux=""; int i=0,b=0,tam=cadena.length();
    //System.out.println("el tamaño de la cadena es :"+tam);
    //System.out.println("el valor de la cadena es :"+cadena); 
    if(!cadena.equals("")){
    do{
         //System.out.println("!!El valor de la cadena en la posicion i es :"+cadena.charAt(i));
        
         if((cadena.charAt(i)==':')||(cadena.charAt(i)==',')){             
            b=1;
         aux=aux+" ";
         //System.out.println("El valor de aux de salida: "+aux);
         } 
         //System.out.println("El valor de i es:"+i);
         if(b<1){
         aux=aux+cadena.charAt(i);
         }
         b=0;
         //System.out.println("El valor de aux de salida: "+aux);
         i++;
         //System.out.println("El valor de b es :"+b);
     }while((b!=2)&&(i<tam));
    //System.out.println("El valor de aux es despues del while:"+aux);
    }else{
    aux=null;
    //System.out.println("No hay dato solo es espacio");
    }
    return aux;
    }
    
    
     //Proceso de todos los datos del archivo original   
    public String procesoDeDatos(){
    String auxi="";
    File archivo=null;    
    FileReader FileR=null;
    BufferedReader Buffered=null;
        try {
            archivo=new File(auxp);
            FileR=new FileReader(archivo);
            Buffered=new BufferedReader(FileR);
            String informacion;
 
               while((informacion=Buffered.readLine())!=null){      
               if(!informacion.equals("")){
               informacion=eliminarDatos(informacion);
               auxi=auxi+"\n"+eliminarComentarios(informacion);
               }
            }
               
        } catch (Exception e) {
        }finally{
       try{
           if(null!=FileR) FileR.close();
       }catch(IOException e2){}
       return auxi;
    }
  }      

  //Elimina las lineas que tiene espacios  
  public String ordenarArchivo(String cadena){
       String aux=""; 
    try {
         FileReader fr  = new FileReader(cadena);
         BufferedReader br = new BufferedReader(fr);
         String lineaLeida;
         while(((lineaLeida=br.readLine())!=null)){
             if(!lineaLeida.equals("")){
             aux=aux+"\n"+lineaLeida;
             } else{
                 //System.out.println("Hay un espacio");
             }
         }
    } catch (Exception e) {
       System.out.println("Error al ordenar el archivo");
       }
    //System.out.println("El valor de aux en el orden de archivo es: "+aux);
    return aux;
   }    
  
//Crear archivo de respaldo tipo texto
   public void respaldarNuevoArchivo(String datos){
   Formatter archivo = null;
       try {
           archivo= new Formatter(aux1);
           archivo.format("%s", datos);
       } catch (Exception e) {
           System.out.println("Ha ocurrido un  error:"+e.toString());
       }finally{
       archivo.close();
       }
   }
   
 public void mostrarDatosDeTabla(int nopag){
   File archivo=null;  int j=0,k=1,i=0;  String pala=null;
   FileReader FileR=null;
   BufferedReader Buffered=null;
   if(aux1!=null){
          if(nopag!=0){
          i=aumentarCantidad(nopag);
          }
        try{
            
            archivo=new File(aux1);
            FileR=new FileReader(archivo);
            Buffered=new BufferedReader(FileR);
            String informacion;
            
            DefaultTableModel tabla=new DefaultTableModel();
            tabla.addColumn("Datos del Archivo");
            tabla.addColumn("Identificación de linea");
            String []info = new String[2];         
            
            while (((informacion=Buffered.readLine())!=null)&&(k<21)&&(i>=0)) {
                //System.out.println("\nEl valor de k es : "+k);
                //System.out.println("\nLa informacion de la linea es: "+informacion);
               if(nopag==0){
                      if(k<21){
                              if(!informacion.equals("")){
                                 //pala=verificarLinea(informacion);
                                  //System.out.println("Salio de la funcion");
                                  info[0]=informacion;
                                 info[1]=verificarLinea(informacion);
                                 tabla.addRow(info);
                              }else{
                                k--;
                             }
                              k++;
                        } 
               }else{
                    if(j>=i){
                           if(k<21){
                                if(!informacion.equals("")){
                                   info[0]=informacion;
                                   //pala=verificarLinea(informacion);
                                   info[1]=verificarLinea(informacion);
                                   tabla.addRow(info);
                                }else{
                                 k--;
                                }
                            k++;
                           }
                    }
                    j++;
                } 
                //System.out.println("La informacion es: "+pala);
               }
           this.tabla.setModel(tabla); 
       } catch (Exception e) {
       }finally{
       try{
           if(null!=FileR) FileR.close();
       }catch(IOException e2){} 
       }
   }else{
       JOptionPane.showMessageDialog(null," Archivo Vacío");
   }
 } 
 
public String verificarLinea(String linea){
 String pala=null,auxw="",w="'"; int i=0,b=0; 
   do{
     //System.out.println("El valor de i es: "+i);
     //System.out.println("El valor de la letra antes de la condicion es: "+linea.charAt(i));
    if(linea.charAt(i)!=' '){
        if((linea.charAt(i)=='"')||(linea.charAt(i)==w.charAt(0))){   
         while((b==0)&&(i<linea.length())){
         if(linea.charAt(i)!=' '){
         auxw=auxw+linea.charAt(i);
         }
         i++;
         if(i==linea.length()-1){
             //System.out.println("El valor de la letra es: "+linea.charAt(i));
         if((linea.charAt(i)=='"')||(linea.charAt(i)==w.charAt(0))){
         b=1;
         }else{
         b=2;
         }
         }
         }
      }
        auxw=auxw+linea.charAt(i); 
    }else{
      if(i==(linea.length()-1)){
          b=1;
      }else{
      if((linea.charAt(i+1)!=' ')){
           //System.out.println("El valor de i+1 es: "+(i+1));
           //System.out.println("El valor de la siguiente letra es: "+linea.charAt(i+1));   
        auxw=auxw+" ";
        }
      }      
    }
     i++;
     //System.out.println("El valor de i es: "+i);
     //System.out.println("El tamaño de la cadena  es: "+linea.length());
     //System.out.println("Valor de auxw: "+auxw);
 }while(i<linea.length()&&(b==0));
 pala=validarLinea(auxw);
 return pala;
 }
 
public String procesoDePseudoinstruccion(String cadena){
String palabra=null;  int b=0; 
  if((cadena.toUpperCase().equals("DATA SEGMENT"))){
        if(r==0){
        palabra="Pseudoinstrucción";
        //palabra="Correcta";
        }   
     r++;
      }else{
         if((cadena.toUpperCase().equals("STACK SEGMENT"))){
            if(e==0){
            palabra="Pseudoinstrucción";    
            //palabra="Correcta";
            }    
          e++;
         }else{
              if((cadena.toUpperCase().equals("ENDS"))){
                  if(p==0){
                  palabra="Pseudoinstrucción";    
                      //System.out.println("Encontro una ends");
                  }   
                 p++;
                 //System.out.println("El valor de p es: "+p);
                }
         }
      }
  //if(palabra==null){
  //palabra="incorrecta, Esta Pseudoinstruccion ya existe";
  //}
   return palabra;
}

public String validarLinea(String linea){
String[] cadenaSeparada; String a=null,cad=""; boolean v=false; int i=0;
cadenaSeparada = linea.split(" ");
    //System.out.println("El valor de la linea es :"+linea);
    
if((!linea.equals(""))){
      a=procesoDePseudoinstruccion(linea);
        if(a!=null){
            v=true;
            cad=a;
        }else{ 
            if(buscarPseudoinstruccion(linea)){
              v=true;
              cad="Pseudoinstrucción";
             }else{
            
            
            for(i=0;i<cadenaSeparada.length;i++){
               a=cadenaSeparada[i];
               //System.out.println("El valor de i es: "+i);
                //System.out.println("El valor de a es:"+a);
                        if(a.equals(" ")||a.equals("")||a.equals("	")){
                         i++;
                               a=cadenaSeparada[i];
                               //System.out.println("El valor de a es: "+a);
                        }
                               if(buscarInstruccion(a)){
                                  v=true;
                                  cad="Instrucción";
                               }else{
                                    if(buscaRegistro(a)){
                                       v=true;
                                       cad="Registro";
                                    }else{
                                         if(buscarConstantes(a)!=null){
                                            v=true;
                                            cad=buscarConstantes(a);
                                         }else{
                                              if(buscarSimbolo(a)){
                                                 v=true;
                                                 cad="Símbolo";
                                              }else{
                                               v=false;
                                               cad=a+" Elemento sin identificar";
                                              }
                                         }
                                     }
                                }
                            
            } 
        }
        } 

}  
    //System.out.println("El valor de p es: "+p);   
if(p<3){
       if(v==true){
       cad="Correcto  /  "+cad;
       }else{
       cad="Incorrecto /  "+cad;
    }
}
if(p==2){
 p++;
 }
   //System.out.println("El valor de la cad es:"+cad);
   return cad;
}

  //Funcion busca si la una parte de la cadena es una Pseudoinstruccion
    public boolean buscarPseudoinstruccion(String cadena){
    int b=0; boolean v=false;
    if((cadena.toUpperCase().equals("CODE SEGMENT"))||(cadena.toUpperCase().equals("DB"))
        ||(cadena.toUpperCase().equals("DW"))||(cadena.toUpperCase().equals("EQU")) 
        ||(cadena.toUpperCase().equals("BYTE PTR"))||(cadena.toUpperCase().equals("WORD PTR"))
        ||(cadena.toUpperCase().equals("MACRO"))||(cadena.toUpperCase().equals("ENDM"))
        ||(cadena.toUpperCase().equals("PROC ENDP")||cadena.toUpperCase().equals("ENDS")) ){ 
      v=true;
    }else{
        int i=0; String auxi="DUP(";  b=0;
      //for(i=0;i<cadena.length();i++)
      do{
      if(cadena.toUpperCase().charAt(i)==auxi.charAt(i)){
          i++;
      }else{
          b=1;
      }
      }while((i<auxi.length())&&(b==0));
      
      char aux=')';
      while((b==0)&&(i<cadena.length())){
      if(cadena.charAt(i)==aux){
        b=2;
      }
      i++;
      }
    if(i==cadena.length()){  
      if(b==2){
          v=true;
      }
    }
    }
    return v;
    }

//Funcion busca si una parte de la cadena es una instruccion
    public boolean buscarInstruccion(String cadena){
     boolean v=false;
    if((cadena.toUpperCase().equals("AAM"))||(cadena.toUpperCase().equals("CMPSB"))
        || (cadena.toUpperCase().equals("POPF")) ||(cadena.toUpperCase().equals("STI"))
        ||(cadena.toUpperCase().equals("INT"))||(cadena.toUpperCase().equals("NOT"))
        ||(cadena.toUpperCase().equals("AND")) ||(cadena.toUpperCase().equals("CMP"))
        ||(cadena.toUpperCase().equals("JG"))||(cadena.toUpperCase().equals("JNB")) 
        ||(cadena.toUpperCase().equals("JNLE"))||(cadena.toUpperCase().equals("JA")) ){
      v=true;
    }
    return v;
    }

//Funcion busca si una parte de la cadena es un registro
 public boolean buscaRegistro(String cadena){
    String aux=null; boolean v=false;
    aux=cadena.toLowerCase();
    if((aux.equals("ax "))||(aux.equals("ah "))
        || (aux.equals("al ")) || (aux.equals("al")) ||(aux.equals("bx "))
        ||(aux.equals("bh "))||(aux.equals("bl "))
        ||(aux.equals("cx ")) ||(aux.equals("ch "))
        ||(aux.equals("cl "))||(aux.equals("dx ")) 
        ||(aux.equals("dh "))||(aux.equals("dl "))
        ||(aux.equals("si "))||(aux.equals("di "))    
        ||(aux.equals("sp "))||(aux.equals("bp "))
        ||(aux.equals("ss "))||(aux.equals("cs "))
        ||(aux.equals("ds "))||(aux.equals("es "))){
        //System.out.println("El aux es : "+aux);
      v=true;
    }
    return v;
    }
 
 //La funcion verifica si una parte de la cadena es una constante palabra,decimal,binaria o hexadecimal
    public String buscarConstantes(String caracter){
    String palabra=null,aux2="'"; int i=0,b=0,tam=caracter.length();  
    //Verifica si es una constante de tipo dato
    do{
        if((caracter.charAt(i)=='"')||(caracter.charAt(i)==aux2.charAt(0))) {            
        b++;
        }
    i++;  
    }while((i<tam)&&(b<2));
    
    if(b==2){
    palabra="Constante Caracter";
    }else{
        //Verifica si es una constante decimal
        if(isNumeric(caracter)){
        palabra="Constante númerica decimal";
       }else{
        //Verifica si es una constante binario     
           b=0;  i=0;
        while((b==0)&&(i<tam)){
              if((caracter.charAt(i)=='0')||(caracter.charAt(i)=='1')){
              i++;
              }else{
                    if(i==(tam-1)){
                          if((caracter.charAt(i)=='B')){
                          palabra="Constante númerica binaria";
                          i++;
                          }
                    }else{
                    b=1;
                    }    
                }
        }
        //Verifica si es una constante hexadecimal
        b=0;  i=0; int j=0;
        while((b==0)&&(i<tam)){
        if(i==0){    
            if(caracter.charAt(i)=='0'){
            i++; 
            }else{
            b=1;
            }
        }else{
              do {
                  if((caracter.charAt(i)=='0')||(caracter.charAt(i)=='1')
                  ||(caracter.charAt(i)=='2')||(caracter.charAt(i)=='3')
                  ||(caracter.charAt(i)=='4')||(caracter.charAt(i)=='5')
                  ||(caracter.charAt(i)=='6')||(caracter.charAt(i)=='7')
                  ||(caracter.charAt(i)=='8')||(caracter.charAt(i)=='9')
                  ||(caracter.toUpperCase().charAt(i)=='A')||(caracter.toUpperCase().charAt(i)=='B') 
                  ||(caracter.toUpperCase().charAt(i)=='C')||(caracter.toUpperCase().charAt(i)=='D')
                  ||(caracter.toUpperCase().charAt(i)=='E')||(caracter.toUpperCase().charAt(i)=='F')){
                  i++;
                  }else{
                      if(i==(tam-1)){
                          if((caracter.toUpperCase().charAt(i)=='H')){
                          palabra="Constante númerica hexadecimal";
                          i++;
                          }else{
                          b=1;
                          }
                    }else{
                    b=1;
                    }
                  }
              }while(((b==0))&&(i<tam));
        }
        }
        }
    
    }
    return palabra;
    }
 
 private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    } 
    
    private static boolean isCaracter(char letra){
	if(Character.isLetter(letra)){
        return true;
        }else{
        return false;
        }
    }
    
    public boolean buscarSimbolo(String cadena){
    String palabra; int b=0,i=0,tam=cadena.length(); boolean v=false; String cad="()";
    if(tam<11){
        //System.out.println("Tamaño de la cadena :"+tam);
          do {            
              if(i==0){
                   if(isCaracter(cadena.charAt(i))){
                   i++;
                   }else{
                   b=1;
                   }
              }else{
               if((cadena.charAt(i)==cad.charAt(0))||(cadena.charAt(i)==cad.charAt(1))){
               b=1;
               }   
               i++;   
              }
            }while ((b==0)&&(i<tam));
    }else{
    b=1;
    }
    if(b==1){
    palabra="Elemento inválido";
    }else{
    v=true;     
    palabra="Símbolo";
    }
    return v;
    }
    
    
//Hace una suma de aux dependiendo de cuantas veces se va a hacer con no
    public int aumentarCantidad(int no){   
     int i, numero=0;
     for(i=0;i<no;i++){
     numero=numero+21;
     }
     return numero;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        botonI = new javax.swing.JButton();
        boton01 = new javax.swing.JButton();
        boton02 = new javax.swing.JButton();
        boton03 = new javax.swing.JButton();
        boton05 = new javax.swing.JButton();
        boton06 = new javax.swing.JButton();
        boton07 = new javax.swing.JButton();
        boton08 = new javax.swing.JButton();
        boton09 = new javax.swing.JButton();
        boton10 = new javax.swing.JButton();
        boton04 = new javax.swing.JButton();

        setTitle("Identificación de líneas ");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        botonI.setText("Inicio");
        botonI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIActionPerformed(evt);
            }
        });

        boton01.setText("01");
        boton01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton01ActionPerformed(evt);
            }
        });

        boton02.setText("02");
        boton02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton02ActionPerformed(evt);
            }
        });

        boton03.setText("03");
        boton03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton03ActionPerformed(evt);
            }
        });

        boton05.setText("05");
        boton05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton05ActionPerformed(evt);
            }
        });

        boton06.setText("06");
        boton06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton06ActionPerformed(evt);
            }
        });

        boton07.setText("07");
        boton07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton07ActionPerformed(evt);
            }
        });

        boton08.setText("08");
        boton08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton08ActionPerformed(evt);
            }
        });

        boton09.setText("09");
        boton09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton09ActionPerformed(evt);
            }
        });

        boton10.setText("10");
        boton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton10ActionPerformed(evt);
            }
        });

        boton04.setText("04");
        boton04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton04ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(botonI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton01)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton02)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton03)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton04)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton05)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton06)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton07)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton08)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton09)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton10)
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonI)
                    .addComponent(boton01)
                    .addComponent(boton02)
                    .addComponent(boton03)
                    .addComponent(boton04)
                    .addComponent(boton05)
                    .addComponent(boton06)
                    .addComponent(boton07)
                    .addComponent(boton08)
                    .addComponent(boton09)
                    .addComponent(boton10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton07ActionPerformed
       boton08.setEnabled(true);
        mostrarDatosDeTabla(6);
    }//GEN-LAST:event_boton07ActionPerformed

    private void boton01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton01ActionPerformed
        p=0;
        boton02.setEnabled(true);
        mostrarDatosDeTabla(0);
    }//GEN-LAST:event_boton01ActionPerformed

    private void botonIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIActionPerformed
     if(aux1!=null){
         bloquearbotones(true);
     }else{
     JOptionPane.showMessageDialog(null,"Archivo vacío");
     }
    }//GEN-LAST:event_botonIActionPerformed

    private void boton02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton02ActionPerformed
        boton03.setEnabled(true);
        mostrarDatosDeTabla(1);
    }//GEN-LAST:event_boton02ActionPerformed

    private void boton03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton03ActionPerformed
        boton04.setEnabled(true);
        mostrarDatosDeTabla(2);
    }//GEN-LAST:event_boton03ActionPerformed

    private void boton04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton04ActionPerformed
        boton05.setEnabled(true);
        mostrarDatosDeTabla(3);
    }//GEN-LAST:event_boton04ActionPerformed

    private void boton05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton05ActionPerformed
        boton06.setEnabled(true);
        mostrarDatosDeTabla(4);
    }//GEN-LAST:event_boton05ActionPerformed

    private void boton06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton06ActionPerformed
        boton07.setEnabled(true);
        mostrarDatosDeTabla(5);
    }//GEN-LAST:event_boton06ActionPerformed

    private void boton08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton08ActionPerformed
       boton09.setEnabled(true);
        mostrarDatosDeTabla(7);
    }//GEN-LAST:event_boton08ActionPerformed

    private void boton09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton09ActionPerformed
        boton10.setEnabled(true);
        mostrarDatosDeTabla(8);
    }//GEN-LAST:event_boton09ActionPerformed

    private void boton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton10ActionPerformed
       mostrarDatosDeTabla(9);
    }//GEN-LAST:event_boton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaSegmento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaSegmento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaSegmento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaSegmento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaSegmento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton01;
    private javax.swing.JButton boton02;
    private javax.swing.JButton boton03;
    private javax.swing.JButton boton04;
    private javax.swing.JButton boton05;
    private javax.swing.JButton boton06;
    private javax.swing.JButton boton07;
    private javax.swing.JButton boton08;
    private javax.swing.JButton boton09;
    private javax.swing.JButton boton10;
    private javax.swing.JButton botonI;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
