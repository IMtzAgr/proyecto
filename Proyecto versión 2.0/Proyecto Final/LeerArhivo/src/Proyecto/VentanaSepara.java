package Proyecto;
import javax.swing.*;
import java.io.*;
import java.util.Formatter;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class VentanaSepara extends javax.swing.JFrame {
 public String auxp,aux1,aux2="C:\\Users\\Public\\Documents\\Archivo.txt"; 
 public int v=0,con=0,tota;
    
    public VentanaSepara() {
        initComponents();
    }

    public VentanaSepara(String ruta){
      initComponents();
      auxp=ruta;
        bloquearBotones(false);
    }

    public String getAux2() {
        return aux2;
    }

    public void setAux2(String aux2) {
        this.aux2 = aux2;
    }
    
    //Proceso Principal 
    public String procesoSepara(String ruta){
        String aux="";
        try {
            FileReader fr =  new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            String lineaLeida;
            
          while(((lineaLeida=br.readLine())!=null)){
             if(lineaLeida.toUpperCase().equals("DATA SEGMENT")){
              aux= aux+"\n"+lineaLeida;   
             }else{
                if(lineaLeida.toUpperCase().equals("STACK SEGMENT")){
                aux= aux+"\n"+lineaLeida;
                }else{
                   if(lineaLeida.toUpperCase().equals("CODE SEGMENT")){
                   aux= aux+"\n"+lineaLeida;
                   }else{
                      if(lineaLeida.toUpperCase().equals("BYTE PTR")){
                      aux= aux+"\n"+lineaLeida;
                      }else{
                        if(lineaLeida.toUpperCase().equals("WORD PTR")){
                        aux= aux+"\n"+lineaLeida;
                        }else{
                           //aux= aux+"\n\n"+lineaLeida;
                            //System.out.println("\nlinealeida:  "+lineaLeida);
                            String a=lineaLeida;
                            a=eliminarComentarios(a);
                            //System.out.println("\nEl valor de a despues de eliminar comentario:  "+a);
                            a=separarComa(a);
                            a=buscarPalabra(a);
                      if(v==0){
                            String[] cadenaSeparada;
                            cadenaSeparada = a.split(" ");
                           
                            for(int i=0;i<cadenaSeparada.length;i++){
                               a=cadenaSeparada[i];
                               if(a.equals(" ")){
                               //System.out.println("El valor de a es: "+a);
                               i++;
                               a=cadenaSeparada[i];
                               //ystem.out.println("El valor de a es: "+a);
                               }
                               //System.out.println("el valor de a :"+a);
                               a=eliminarDatos(a);
                               //System.out.println("El valor de a es: "+a);
                               if(a!=null){
                               aux=aux+"\n"+a;    
                               }
                            }
                            
                      }else{ 
                            String[] cadenaSeparada;
                            cadenaSeparada = a.split(" ");
                            
                            for(int i=0;i<cadenaSeparada.length;i++){
                               a=cadenaSeparada[i];
                               if(a.equals(" ")){
                               i++;
                               a=cadenaSeparada[i];
                               }
                               //System.out.println("el valor de a :"+a);
                               a=eliminarDatos(a);
                               if(a!=null){
                               aux=aux+"\n"+a;    
                               }
                            }     
                            aux=aux+"\n"+aux1;      
                            }
                          aux1="";
                        }
                      }
                   }
                }
             }
            }
          //termina el while
        } catch (Exception e) {
            System.out.println("Error en el proceso de separación");
        }
        //System.out.println("El valor de aux en el proceso separa es: "+aux);
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
   
    //Eliminar Comentarios 
    public String eliminarComentarios(String cade){
    String aux=""; int b=0,i=0,tam=cade.length();
    if(!cade.equals("")){
    do{
        //System.out.println("Valor de tam es: "+tam);
        //System.out.println("Valor de i es: "+i);
       if(cade.charAt(i)!=';'){
       aux=aux+cade.charAt(i);
       }else{
        b=1;
       }
       i++;
    }while((b!=1)&&(i<tam));    
    //System.out.println("Valor de aux despues del do: "+aux);
    }
    return aux;
    }
    
    //Buscar las palabras que bienen con comillas dobles
    public String buscarPalabra(String linea){
    String aux=""; int b=0,i=0, tam=linea.length();
    if(!linea.equals("")){
       //Buscar palabra entre comillas y las respaldamos
        do{
         if(linea.charAt(i)=='"'){
             b=1;
             while (i<tam) {                 
             aux=aux+linea.charAt(i);
             i++;    
             }
         }else{
         i++;
         }
       }while(i<tam);
        
    if(b==1){ 
       aux1=aux;  v=1;
       aux=""; i=0;
       //Buscar palabra entre comillas y la eliminamos
       do{
         if(linea.charAt(i)=='"'){
             while (i<tam) {                 
             aux=aux+"";
             i++;    
             }
         }else{
         aux=aux+linea.charAt(i);
         i++;
         }
       }while(i<tam);
     }else{
        aux=linea;
     }
     }
     //System.out.println("\nel valor de aux es: "+aux1);
     //System.out.println("el valor de aux es : "+aux);
     return aux;
    }
    
    //Buscamos el total de lineas que hay dentro de un archivo
      public static int totalLineas(String dire){
          String lineaLeida=null; int j=0;
          try {
              FileReader fr =  new FileReader(dire);
              BufferedReader br = new BufferedReader(fr);
             
           while(((lineaLeida=br.readLine())!=null)){
               j++;
           }
          } catch (Exception e) {
          }
      return j;
      }
      
    //Separar numero de la coma
    public String separarComa(String cadena){
    String aux=""; int b=0,i=0,tam=cadena.length(); char letra;
        //System.out.println("Tamaño de la cadena: "+tam);
    if(!cadena.equals("")){
    do{
        //System.out.println("El valor de i es: "+i);
        //System.out.println("La letra es: "+cadena.charAt(i));
      if(cadena.charAt(i)==','){
          //System.out.println("La letra es: "+cadena.charAt(i+1));
        if(cadena.charAt(i+1)!=' '){
            aux=aux+cadena.charAt(i);
            i++;
            //letra=cadena.charAt(i+v);
            aux=aux+" ";
            //aux=aux+letra;
            do{
            if(cadena.charAt(i)!=' '){
            aux=aux+cadena.charAt(i);
            }
            i++;
            }while(i<tam);
            //System.out.println("El valor de aux es: "+aux);
            b=1;
        }    
      }else{
      aux=aux+cadena.charAt(i);
      }
      i++;
    }while((i<tam)&&(b==0));
    
    if(b==0){
    aux=cadena;
    }
    }
    return aux;
    }
   
  //Elimina lineas en blanco del archivo  
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
        // System.out.println("La linea leida en orden es : "+aux);
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
           archivo= new Formatter(aux2);
           archivo.format("%s", datos);
       } catch (Exception e) {
           System.out.println("Ha ocurrido un  error:"+e.toString());
       }finally{
       archivo.close();
       }
   }
   
   //Funcion para eliminar espacios entre lineas del archivo
   public String mostrarDatos(String ruta){
   String aux="",aux2="",aux3="	"; int i=0;
       try {
            FileReader fr =  new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            String lineaLeida;

            while(((lineaLeida=br.readLine())!=null)){
                aux2=lineaLeida;
                //System.out.println("linealeida :"+aux2);
                if(aux2.equals(aux3)){
                lineaLeida=br.readLine();
                aux2=lineaLeida;
                }
                //System.out.println("linealeida :"+aux2);
              if(!aux2.equals("")){
                if(i==0){   
                aux=lineaLeida;
                }else{
                aux= aux+"\n"+lineaLeida;
                }
                  //System.out.println("Entro ala condicion\n");
              }
              i++;
            }            
            //System.out.println("/////El valor de aux :"+aux);
       } catch (Exception e) {
           System.out.println("Ha ocurrido un  error:"+e.toString());
       }
     return aux;
   }
  
    public void recuperarDatosDeTabla(){
    int i;
    for(i=0;i<tabla.getRowCount();i++){
        System.out.println("Dato: "+tabla.getValueAt(i, 0));
      }
    }
    
   //Funcion para selecionar la paginacion
   public void mostrarDatosTabla(int nopag){
   File archivo=null;  int j=0,k=1,i=0; 
   FileReader FileR=null;
   BufferedReader Buffered=null;
  if(aux2!=null){  
      if(nopag!=0){
      i=aumentarCantidad(nopag);
      }
   try {
       archivo=new File(aux2);
       FileR=new FileReader(archivo);
       Buffered=new BufferedReader(FileR);
       String informacion;
       DefaultTableModel tabla=new DefaultTableModel();
       tabla.addColumn("Datos del Archivo");
       
       while(((informacion=Buffered.readLine())!=null)&&(k<21)&&(i>=0)){
           //System.out.println("El valor de informacion es :"+informacion);
        if(i==0){
          if(k<21){
              if(!informacion.equals("")){
              tabla.addRow(new String[]{informacion});
              }else{
              k--;
              }
             k++;
          }
        }else{
          if(j>=i){
               if(k<21){
                   if(!informacion.equals("")){
                     tabla.addRow(new String[]{informacion});
                    }else{
                   k--;
                   }
                   k++;
                }  
            }
         j++; 
        }  
       }
       this.tabla.setModel(tabla);
       } catch (Exception e) {
       }finally{
       try{
           if(null!=FileR) FileR.close();
       }catch(IOException e2){} 
       }
     }
    }
   
    //Hace una suma de aux dependiendo de cuantas veces se va a hacer con no
    public int aumentarCantidad(int no){   
     int i, numero=0;
     for(i=0;i<no;i++){
     numero=numero+21;
     }
     return numero;
    }
   
    public void bloquearBotones(boolean v){
    if(v==false){
    boton01.setEnabled(false);
    boton02.setEnabled(false);
    boton03.setEnabled(false);
    boton04.setEnabled(false);
    boton05.setEnabled(false);
    boton06.setEnabled(false);
    boton7.setEnabled(false);
    boton08.setEnabled(false);
    boton09.setEnabled(false);
    boton10.setEnabled(false);
    }else{
    boton01.setEnabled(true);
    BotonrInicio.setEnabled(false);
    } 
   
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonrInicio = new javax.swing.JButton();
        boton02 = new javax.swing.JButton();
        boton01 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        boton03 = new javax.swing.JButton();
        boton04 = new javax.swing.JButton();
        boton05 = new javax.swing.JButton();
        boton06 = new javax.swing.JButton();
        boton7 = new javax.swing.JButton();
        boton08 = new javax.swing.JButton();
        boton09 = new javax.swing.JButton();
        boton10 = new javax.swing.JButton();

        setTitle("Elementos Separados");

        BotonrInicio.setText("Iniciar");
        BotonrInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonrInicioActionPerformed(evt);
            }
        });

        boton02.setText("02");
        boton02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton02ActionPerformed(evt);
            }
        });

        boton01.setText("01");
        boton01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton01ActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(tabla);

        boton03.setText("03");
        boton03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton03ActionPerformed(evt);
            }
        });

        boton04.setText("04");
        boton04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton04ActionPerformed(evt);
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

        boton7.setText("07");
        boton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton7ActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BotonrInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton01, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton02)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton03)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton04)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton05)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton06)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton08)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton09)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton06, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BotonrInicio)
                        .addComponent(boton02)
                        .addComponent(boton01)
                        .addComponent(boton03)
                        .addComponent(boton04)
                        .addComponent(boton05)
                        .addComponent(boton7)
                        .addComponent(boton08)
                        .addComponent(boton09)
                        .addComponent(boton10)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton02ActionPerformed
        boton03.setEnabled(true);
        mostrarDatosTabla(1);
    }//GEN-LAST:event_boton02ActionPerformed

    private void BotonrInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonrInicioActionPerformed
    if(aux2!=null){
    String a;
        bloquearBotones(true);
        respaldarNuevoArchivo(procesoSepara(auxp));
        respaldarNuevoArchivo(ordenarArchivo(aux2));
        respaldarNuevoArchivo(mostrarDatos(aux2));
    }else{
    JOptionPane.showMessageDialog(null,"Archivo vacío");
    }     
    }//GEN-LAST:event_BotonrInicioActionPerformed

    private void boton01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton01ActionPerformed
    boton02.setEnabled(true);
        mostrarDatosTabla(0);    
    }//GEN-LAST:event_boton01ActionPerformed

    private void boton03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton03ActionPerformed
     boton04.setEnabled(true);
        mostrarDatosTabla(2);
    }//GEN-LAST:event_boton03ActionPerformed

    private void boton04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton04ActionPerformed
        boton05.setEnabled(true);
        mostrarDatosTabla(3);
    }//GEN-LAST:event_boton04ActionPerformed

    private void boton05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton05ActionPerformed
       boton06.setEnabled(true);
        mostrarDatosTabla(4);
    }//GEN-LAST:event_boton05ActionPerformed

    private void boton06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton06ActionPerformed
         boton7.setEnabled(true);
        mostrarDatosTabla(5);
    }//GEN-LAST:event_boton06ActionPerformed

    private void boton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton7ActionPerformed
        boton08.setEnabled(true);
        mostrarDatosTabla(6);
    }//GEN-LAST:event_boton7ActionPerformed

    private void boton08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton08ActionPerformed
      boton09.setEnabled(true);
        mostrarDatosTabla(7);
    }//GEN-LAST:event_boton08ActionPerformed

    private void boton09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton09ActionPerformed
       boton10.setEnabled(true);
        mostrarDatosTabla(8);
    }//GEN-LAST:event_boton09ActionPerformed

    private void boton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton10ActionPerformed
       mostrarDatosTabla(9);
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
            java.util.logging.Logger.getLogger(VentanaSepara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaSepara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaSepara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaSepara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaSepara().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonrInicio;
    private javax.swing.JButton boton01;
    private javax.swing.JButton boton02;
    private javax.swing.JButton boton03;
    private javax.swing.JButton boton04;
    private javax.swing.JButton boton05;
    private javax.swing.JButton boton06;
    private javax.swing.JButton boton08;
    private javax.swing.JButton boton09;
    private javax.swing.JButton boton10;
    private javax.swing.JButton boton7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
