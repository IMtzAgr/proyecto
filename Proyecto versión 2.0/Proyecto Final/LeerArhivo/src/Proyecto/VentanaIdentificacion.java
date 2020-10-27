package Proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

public class VentanaIdentificacion extends javax.swing.JFrame {
public String auxp,aux="C:\\Users\\Public\\Documents\\Archivo.txt"; 
 public int v=0,con=0,tota=0,r=0,e=0,p=0;
    public VentanaIdentificacion() {
        initComponents();
        bloquearBotones(false);
    }
    
    public VentanaIdentificacion(String ruta) {
        initComponents();
        auxp=ruta;
        bloquearBotones(false);
    }
    
    //Funcion para bloquear todas los botones paginados
    public void bloquearBotones(Boolean v){
    if(v==false){
    boton1.setEnabled(false);
    boton2.setEnabled(false);
    boton3.setEnabled(false);
    boton4.setEnabled(false);
    boton5.setEnabled(false);
    boton6.setEnabled(false);
    boton7.setEnabled(false);
    boton8.setEnabled(false);
    boton9.setEnabled(false);
    boton10.setEnabled(false);
    }else{
    boton1.setEnabled(true);
    botonI.setEnabled(false);
    
    }
    }
    
    //Funcion busca si la cadena es una Pseudoinstruccion
    public String buscarPseudoinstruccion(String cadena){
    String palabra=null;  int b=0;
    if((cadena.toUpperCase().equals("DATA SEGMENT"))||(cadena.toUpperCase().equals("STACK SEGMENT"))
        || (cadena.toUpperCase().equals("CODE SEGMENT")) ||(cadena.toUpperCase().equals("ENDS"))
        ||(cadena.toUpperCase().equals("DB"))||(cadena.toUpperCase().equals("DW"))
        ||(cadena.toUpperCase().equals("EQU")) ||(cadena.toUpperCase().equals("BYTE PTR"))
        ||(cadena.toUpperCase().equals("WORD PTR"))||(cadena.toUpperCase().equals("MACRO")) 
        ||(cadena.toUpperCase().equals("ENDM"))||(cadena.toUpperCase().equals("PROC ENDP")) ){ 
     
     if((cadena.toUpperCase().equals("DATA SEGMENT"))){
        if(r==0){
        palabra="Pseudoinstruccion";
        }   
     r++;
      }else{
         if((cadena.toUpperCase().equals("STACK SEGMENT"))){
            if(e==0){
            palabra="Pseudoinstrucción";
            }    
          e++;
         }else{
              if((cadena.toUpperCase().equals("CODE SEGMENT"))){
                  if(p==0){
                  palabra="Pseudoinstrucción";
                  }
                  p++;
              }else{
              palabra="Pseudoinstrucción";   
              }
         }
      }
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
      palabra="Pseudoinstrucción";
      }
    }
    }
    return palabra;
    }
    
    //Funcion busca si la cadena es una Pseudoinstruccion
    public String buscarInstruccion(String cadena){
     String palabra=null;
    if((cadena.toUpperCase().equals("AAM"))||(cadena.toUpperCase().equals("CMPSB"))
        || (cadena.toUpperCase().equals("POPF")) ||(cadena.toUpperCase().equals("STI"))
        ||(cadena.toUpperCase().equals("INT"))||(cadena.toUpperCase().equals("NOT"))
        ||(cadena.toUpperCase().equals("AND")) ||(cadena.toUpperCase().equals("CMP"))
        ||(cadena.toUpperCase().equals("JG"))||(cadena.toUpperCase().equals("JNB")) 
        ||(cadena.toUpperCase().equals("JNLE"))||(cadena.toUpperCase().equals("JA")) ){
      palabra="Instrucción";
    }
    return palabra;
    }
    
    public String buscaRegistro(String cadena){
    String palabra=null,aux=null;
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
      palabra="Registro";
    }
    return palabra;
    }
    
    //La funcion verifica si es una constante palabra,decimal,binaria o hexadecimal
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
    palabra="Constante caracter";
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
    
    public String buscarSimbolo(String cadena){
    String palabra; int b=0,i=0,tam=cadena.length();
    if(tam<11){
          do {            
              if(i==0){
                   if(isCaracter(cadena.charAt(i))){
                   i++;
                   }else{
                   b=1;
                   }
              }else{
               i++;   
              }
            }while ((b==0)&&(i<tam));
    }else{
    b=1;
    }
    if(b==1){
    palabra="Elemento inválido";
    }else{
    palabra="Símbolo";
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
    
    //Hace una suma de aux dependiendo de cuantas veces se va a hacer con no
    public int aumentarCantidad(int no){   
     int i, numero=0;
     for(i=0;i<no;i++){
     numero=numero+21;
     }
     return numero;
    }
    
   //Funcion para selecionar la paginacion
   public void mostrarDatosTabla(int nopag){
   File archivo=null;  int j=0,k=1,i=0;  String pala=null;
   FileReader FileR=null;
   BufferedReader Buffered=null;
  if(aux!=null){  
      if(nopag!=0){
      i=aumentarCantidad(nopag);
      }
   try {
       archivo=new File(aux);
       FileR=new FileReader(archivo);
       Buffered=new BufferedReader(FileR);
       String informacion;
       DefaultTableModel tabla=new DefaultTableModel();
       tabla.addColumn("Datos del Archivo");
       tabla.addColumn("Identificación");
       String []info = new String[2];
       while(((informacion=Buffered.readLine())!=null)&&(k<21)&&(i>=0)){
           //System.out.println("El valor de informacion es :"+informacion);
        if(i==0){
          if(k<21){
              if(!informacion.equals("")){
                   pala=buscarPseudoinstruccion(informacion);
                   if(pala!=null){ 
                   info[0]=informacion;
                   info[1]=pala;
                   tabla.addRow(info);
                   }else{
                        pala=buscarInstruccion(informacion);
                        if(pala!=null){ 
                        info[0]=informacion;
                        info[1]=pala;
                        tabla.addRow(info);
                        }else{
                             pala=buscaRegistro(informacion);
                             if(pala!=null){ 
                             info[0]=informacion;
                             info[1]=pala;
                             tabla.addRow(info);
                             }else{
                                  pala=buscarConstantes(informacion);
                                  if(pala!=null){ 
                                  info[0]=informacion;
                                  info[1]=pala;
                                  tabla.addRow(info);
                                  }else{
                                       pala=buscarSimbolo(informacion);
                                       info[0]=informacion;
                                       info[1]=pala;
                                       tabla.addRow(info);
                                  }                                   
                             }
                        }
                   }  
              }else{
              k--;
              }
             k++;
          }
        }else{
          if(j>=i){
               if(k<21){
                    if(!informacion.equals("")){
                          pala=buscarPseudoinstruccion(informacion);
                          if(pala!=null){ 
                          info[0]=informacion;
                          info[1]=pala;
                          tabla.addRow(info);
                          }else{
                               pala=buscarInstruccion(informacion);
                               if(pala!=null){ 
                               info[0]=informacion;
                               info[1]=pala;
                               tabla.addRow(info);
                               }else{
                                    pala=buscaRegistro(informacion);
                                    if(pala!=null){ 
                                    info[0]=informacion;
                                    info[1]=pala;
                                    tabla.addRow(info);
                                    }else{
                                         pala=buscarConstantes(informacion);
                                         if(pala!=null){ 
                                         info[0]=informacion;
                                         info[1]=pala;
                                         tabla.addRow(info);
                                         }else{
                                              pala=buscarSimbolo(informacion);
                                              info[0]=informacion;
                                              info[1]=pala;
                                              tabla.addRow(info);
                                         }                                   
                                    }
                            }
                        }
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
        boton1 = new javax.swing.JButton();
        boton2 = new javax.swing.JButton();
        boton3 = new javax.swing.JButton();
        boton4 = new javax.swing.JButton();
        boton5 = new javax.swing.JButton();
        boton6 = new javax.swing.JButton();
        boton7 = new javax.swing.JButton();
        boton8 = new javax.swing.JButton();
        boton9 = new javax.swing.JButton();
        boton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Identificación de elementos");

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

        botonI.setText("Iniciar");
        botonI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIActionPerformed(evt);
            }
        });

        boton1.setText("01");
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });

        boton2.setText("02");
        boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });

        boton3.setText("03");
        boton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });

        boton4.setText("04");
        boton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton4ActionPerformed(evt);
            }
        });

        boton5.setText("05");
        boton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton5ActionPerformed(evt);
            }
        });

        boton6.setText("06");
        boton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton6ActionPerformed(evt);
            }
        });

        boton7.setText("07");
        boton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton7ActionPerformed(evt);
            }
        });

        boton8.setText("08");
        boton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton8ActionPerformed(evt);
            }
        });

        boton9.setText("09");
        boton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton9ActionPerformed(evt);
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
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(botonI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton10)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonI)
                    .addComponent(boton1)
                    .addComponent(boton2)
                    .addComponent(boton3)
                    .addComponent(boton4)
                    .addComponent(boton5)
                    .addComponent(boton6)
                    .addComponent(boton7)
                    .addComponent(boton8)
                    .addComponent(boton9)
                    .addComponent(boton10)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        r=0;e=0;p=0;
        boton2.setEnabled(true);
        mostrarDatosTabla(0);  
    }//GEN-LAST:event_boton1ActionPerformed

    private void boton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton6ActionPerformed
       boton7.setEnabled(true);
        mostrarDatosTabla(5); 
    }//GEN-LAST:event_boton6ActionPerformed

    private void botonIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIActionPerformed
    if(aux!=null){
    String a;
        bloquearBotones(true);
        //respaldarNuevoArchivo(procesoSepara(auxp));
        //respaldarNuevoArchivo(ordenarArchivo(aux2));
        //respaldarNuevoArchivo(mostrarDatos(aux2));
    }else{
    JOptionPane.showMessageDialog(null,"Archivo vacío");
    }
    }//GEN-LAST:event_botonIActionPerformed

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        boton3.setEnabled(true);
        mostrarDatosTabla(1);
    }//GEN-LAST:event_boton2ActionPerformed

    private void boton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton3ActionPerformed
        boton4.setEnabled(true); 
        mostrarDatosTabla(2); 
    }//GEN-LAST:event_boton3ActionPerformed

    private void boton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton4ActionPerformed
       boton5.setEnabled(true);
        mostrarDatosTabla(3); 
    }//GEN-LAST:event_boton4ActionPerformed

    private void boton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton5ActionPerformed
       boton6.setEnabled(true);
        mostrarDatosTabla(4); 
    }//GEN-LAST:event_boton5ActionPerformed

    private void boton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton7ActionPerformed
        boton8.setEnabled(true);
        mostrarDatosTabla(6); 
    }//GEN-LAST:event_boton7ActionPerformed

    private void boton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton8ActionPerformed
       boton9.setEnabled(true);
        mostrarDatosTabla(7); 
    }//GEN-LAST:event_boton8ActionPerformed

    private void boton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton9ActionPerformed
        boton10.setEnabled(true);
        mostrarDatosTabla(8); 
    }//GEN-LAST:event_boton9ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaIdentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaIdentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaIdentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaIdentificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaIdentificacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton1;
    private javax.swing.JButton boton10;
    private javax.swing.JButton boton2;
    private javax.swing.JButton boton3;
    private javax.swing.JButton boton4;
    private javax.swing.JButton boton5;
    private javax.swing.JButton boton6;
    private javax.swing.JButton boton7;
    private javax.swing.JButton boton8;
    private javax.swing.JButton boton9;
    private javax.swing.JButton botonI;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
