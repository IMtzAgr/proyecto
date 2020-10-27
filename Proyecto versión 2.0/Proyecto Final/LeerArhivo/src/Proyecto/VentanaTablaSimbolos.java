package Proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class VentanaTablaSimbolos extends javax.swing.JFrame {
String aux="C:\\Users\\Public\\Documents\\Archivo.txt";
    
    public VentanaTablaSimbolos() {
        initComponents();
        
        mostrarDatosDeTabla();
    }
    
    //Mostrar todos los datos del archivo original   
    public void mostrarDatosDeTabla(){
    File archivo=null;
    FileReader FileR=null;
    BufferedReader Buffered=null;
        try {
            archivo=new File(aux);
            FileR=new FileReader(archivo);
            Buffered=new BufferedReader(FileR);
            String informacion;
            DefaultTableModel tabla=new DefaultTableModel();
            tabla.addColumn("Símbolo");
            tabla.addColumn("Tipo");
            tabla.addColumn("Valor");
            tabla.addColumn("Tamaño");
            String []info = new String[4];
            while((informacion=Buffered.readLine())!=null){     
               if(!informacion.equals("")){
                   if(buscarPseudoinstruccion(informacion)==null){
                      if(buscarInstruccion(informacion)==null){
                         if(buscaRegistro(informacion)==null){
                            if(buscarConstantes(informacion)==null){
                               if(buscarSimbolo(informacion)=="Símbolo"){
                                 info[0]=informacion;
                                 info[1]=" ";
                                 info[2]=" ";
                                 info[3]="x";
                                 tabla.addRow(info);
                                   
                               }
                            }
                         }
                      }
                   }
               }
            }
            this.tablaSimbolos.setModel(tabla); 
        } catch (Exception e) {
        }finally{
       try{
           if(null!=FileR) FileR.close();
       }catch(IOException e2){} 
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
        palabra="Pseudoinstrucción";
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

    public String buscarSimbolo(String cadena){
    String palabra; int b=0,i=0,tam=cadena.length();String cad="()";
    if(tam<11){
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSimbolos = new javax.swing.JTable();

        setTitle("Tabla de Simbolos");

        tablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaSimbolos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaTablaSimbolos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaSimbolos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaSimbolos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaTablaSimbolos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaTablaSimbolos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaSimbolos;
    // End of variables declaration//GEN-END:variables
}
