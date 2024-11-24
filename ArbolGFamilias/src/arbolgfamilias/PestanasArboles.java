/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package arbolgfamilias;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author dugla
 */
public class PestanasArboles extends javax.swing.JFrame {
    
    
    Tree familyTree = GlobalData.getTree();
    
    Hash_Table tablita = GlobalData.getHashTable();
    
    

    /**
     * Creates new form PestanasArboles
     */
    public PestanasArboles() {

        initComponents();
        setLocationRelativeTo(null);
        
        cargarArbol();
        llenarCajitaMiembros();
        familyTree.llenarComboBox(GeneracionLista);
        jButton2.setEnabled(false);

    }
    
    Tree elnuevoarbol = new Tree();
    
    public class Genealogia {
    
           private Hash_Table hashTable;

    public Genealogia(Hash_Table hashTable) {
        this.hashTable = hashTable;
    }
    
    

    public void seleccionarMiembro() {
        String nombreBuscado = JOptionPane.showInputDialog("Ingrese el nombre del miembro de la familia (Nombre y Apellido). Ejemplo, William Baratheon ú Aerys Targaryen: ");

        
        if (nombreBuscado == null || nombreBuscado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        nombreBuscado = nombreBuscado.trim().toLowerCase();

        StringBuilder opciones = new StringBuilder();
        int contador = 0;

       
        for (int i = 0; i < hashTable.getTabla().length; i++) {
            Nodo_hash nodo = hashTable.getTabla()[i];
            while (nodo != null) {
                
                if (nodo.getMiembro().getNombre().toLowerCase().startsWith(nombreBuscado)) {
                    contador++;
                    String aux = String.valueOf(contador);
                    
                    opciones.append(aux + ", " +nodo.getName()).append("\n");
                }
                nodo = nodo.getNext();
            }
        }

       
        if (contador == 0) {
            JOptionPane.showMessageDialog(null, "No se encontraron miembros con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        String seleccion = (String) JOptionPane.showInputDialog(null,
                "Seleccione un miembro (Nombre + Apellido + Numeral) sin contar el numero del final:\n" + opciones.toString(),
                "Selección de Miembro",
                JOptionPane.QUESTION_MESSAGE);

        if (seleccion != null) {
            
            String nombreSeleccionado = capitalizarNombre(seleccion.trim());


            Hash_Table aux = GlobalData.getHashTable();
            MiembroFamilia Guardar_miembro = aux.buscar(nombreSeleccionado);

            
            if (Guardar_miembro == null) {
                JOptionPane.showMessageDialog(null, "Error: Miembro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            elnuevoarbol = familyTree.crearArbolDescendencia(familyTree, Guardar_miembro);
            llenarCajitaMiembrosDescendencias();
            cargarArbol2();
            jButton2.setEnabled(true);
        }
    }

    private String obtenerSufijo(int numero) {
        switch (numero) {
            case 1: return "1";
            case 2: return "2";
            case 3: return "3";
            case 4: return "4";
            case 5: return "5";
            case 6: return "6";
            case 7: return "7";
            default: return numero + "th"; 
        }
    }



    }
    
    public void buscarPorTitulo(String tituloBuscado) {
    StringBuilder opciones = new StringBuilder();
    int contador = 0;
    
    String vacio = "";
    
    if (tituloBuscado == null || tituloBuscado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
   

    
    for (int i = 0; i < tablita.getTabla().length; i++) {
        Nodo_hash nodo = tablita.getTabla()[i];
        while (nodo != null) {
            if (nodo.getMiembro().getTitulonobilario().equalsIgnoreCase(tituloBuscado)) { 
                contador++;
                vacio = String.valueOf(contador);
               
                opciones.append(vacio + ", " + nodo.getName()).append("\n");
            }
            nodo = nodo.getNext();
        }
    }

    
    if (contador == 0) {
        JOptionPane.showMessageDialog(null, "No se encontraron miembros con el título: " + tituloBuscado, "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

   
    String seleccion = (String) JOptionPane.showInputDialog(null,
            "Seleccione un miembro (Ejemplo: William Baratheon Second):\n" + opciones.toString(),
            "Selección de Miembro",
            JOptionPane.QUESTION_MESSAGE);

    if (seleccion != null) {
        
        
        String nombreSeleccionado = capitalizarNombre(seleccion.trim());


            Hash_Table aux = GlobalData.getHashTable();
            MiembroFamilia Guardar_miembro = aux.buscar(nombreSeleccionado);
            familyTree.mostrarInformacionMiembro(Guardar_miembro);

            
            if (Guardar_miembro == null) {
                JOptionPane.showMessageDialog(null, "Error: Miembro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    }
}
    
    
    private void cargarArbol() {
        if (familyTree != null) {
            familyTree.mostrarArbol(MostrarArbolPanel);
        } else {
            System.out.println("No se encontró ningún árbol.");
        }
    }
    
    private void cargarArbol2() {
        if (elnuevoarbol != null) {
            elnuevoarbol.mostrarArbol(Descendencias);
        } else {
            System.out.println("No se encontró ningún árbol.");
        }
    }
    
    private void llenarCajitaMiembros() {
    if (familyTree != null && familyTree.getRaiz() != null) {
        recorrerYAgregar(familyTree.getRaiz()); 
    } else {
        System.out.println("El árbol está vacío o no está inicializado.");
    }
}

    private void recorrerYAgregar(Nodo nodo) {
        if (nodo == null) {
            return; 
        }

        
        MiembroFamilia miembro = nodo.getMiembro();
        String item = miembro.getNombre() + " " + miembro.getSobrenombre();
        CajitaMiembros.addItem(item);
        

        
        Nodo hijo = nodo.getHijo();
        while (hijo != null) {
            recorrerYAgregar(hijo); 
            hijo = hijo.getHermano(); 
        }
    }
    
    private void llenarCajitaMiembrosDescendencias() {
        CajitaDescendencias.removeAllItems();
    if (elnuevoarbol != null && elnuevoarbol.getRaiz() != null) {
        recorrerYAgregar2(elnuevoarbol.getRaiz()); 
    } else {
        System.out.println("El árbol está vacío o no está inicializado.");
    }
}
    
    private void recorrerYAgregar2(Nodo nodo) {
        if (nodo == null) {
            return; 
        }

        
        MiembroFamilia miembro = nodo.getMiembro();
        String item = miembro.getNombre() + " " + miembro.getSobrenombre();
        CajitaDescendencias.addItem(item);

        
        Nodo hijo = nodo.getHijo();
        while (hijo != null) {
            recorrerYAgregar2(hijo); 
            hijo = hijo.getHermano(); 
        }
    }
    
    private String capitalizarNombre(String nombre) {
        String[] partes = nombre.split(" ");
        StringBuilder nombreCapitalizado = new StringBuilder();

        for (String parte : partes) {
            if (parte.length() > 0) {
                nombreCapitalizado.append(Character.toUpperCase(parte.charAt(0)))
                                  .append(parte.substring(1).toLowerCase())
                                  .append(" ");
            }
        }

        return nombreCapitalizado.toString().trim();
    }
    
    public ListaSimple obtenerMiembrosPorNivel(int nivel) {
    ListaSimple listaMiembros = new ListaSimple();
    obtenerMiembrosPorNivelRecursivo(familyTree.getRaiz(), nivel, 0, listaMiembros);
    return listaMiembros;
}

    private void obtenerMiembrosPorNivelRecursivo(Nodo nodo, int nivelDeseado, int nivelActual, ListaSimple listaMiembros) {
    
    if (nodo == null) {
        return; 
    }

    
    if (nivelActual == nivelDeseado) {
        listaMiembros.agregar1(nodo.getMiembro());
    } else {
        
        Nodo hijo = nodo.getHijo();
        while (hijo != null) {
            obtenerMiembrosPorNivelRecursivo(hijo, nivelDeseado, nivelActual + 1, listaMiembros);
            hijo = hijo.getHermano(); 
        }
    }
            
}
    
    private static void mostrarMiembrosGene(ListaSimple listaMiembros) {
        StringBuilder miembrosText = new StringBuilder("Miembros en el nivel:\n");

        
        NodoListaSimple nodoActual = listaMiembros.head; 
        while (nodoActual != null) {
            miembrosText.append(nodoActual.getNombrehijo()).append("\n"); 
            nodoActual = nodoActual.getpNext(); 
        }

        
        JOptionPane.showMessageDialog(null, miembrosText.toString(), "Miembros de la Generación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        MostrarArbolPanel = new javax.swing.JPanel();
        CajitaMiembros = new javax.swing.JComboBox<>();
        InformacionBoton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Descendencias = new javax.swing.JPanel();
        CajitaDescendencias = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        CampoTitulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        GeneracionLista = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MostrarArbolPanel.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout MostrarArbolPanelLayout = new javax.swing.GroupLayout(MostrarArbolPanel);
        MostrarArbolPanel.setLayout(MostrarArbolPanelLayout);
        MostrarArbolPanelLayout.setHorizontalGroup(
            MostrarArbolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 759, Short.MAX_VALUE)
        );
        MostrarArbolPanelLayout.setVerticalGroup(
            MostrarArbolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        InformacionBoton.setText("Buscar Informacion");
        InformacionBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InformacionBotonActionPerformed(evt);
            }
        });

        jLabel1.setText("Arbol Correspondiente:");

        jLabel2.setText("LLENAR");

        Regresar.setText("Cargar Nuevo Archivo Json");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(Regresar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(MostrarArbolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CajitaMiembros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(InformacionBoton, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(CajitaMiembros, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(InformacionBoton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MostrarArbolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Regresar)
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Arbol Completo", jPanel1);

        jButton1.setText("Buscar por Nombre");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Descendencias.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout DescendenciasLayout = new javax.swing.GroupLayout(Descendencias);
        Descendencias.setLayout(DescendenciasLayout);
        DescendenciasLayout.setHorizontalGroup(
            DescendenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        DescendenciasLayout.setVerticalGroup(
            DescendenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jButton2.setText("Mostrar Informacion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Busca por nombre dandole al boton");

        jLabel4.setText("Selecciona uno de los miembros para mostrar su informacion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(Descendencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CajitaDescendencias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jButton1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jButton2)))
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CajitaDescendencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(Descendencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mostrar Arbol por Nombre", jPanel2);

        CampoTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoTituloActionPerformed(evt);
            }
        });

        jLabel5.setText("Ingresa el titulo de algun miembro de la familia:");

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Seleccione alguna generacion para su lista (Por Nivel):");

        jButton4.setText("Mostrar Generacion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(CampoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(GeneracionLista, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(665, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(CampoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(184, 184, 184)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GeneracionLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Busqueda por Titulo y Lista de Generacion", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
         
        
       Genealogia genealogia = new Genealogia(tablita);
       genealogia.seleccionarMiembro();
        
        
        
        
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void InformacionBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InformacionBotonActionPerformed
        // TODO add your handling code here:
        
        String miembrito = CajitaMiembros.getSelectedItem().toString();
        
        MiembroFamilia miembre = tablita.buscar(miembrito);
        
        familyTree.mostrarInformacionMiembro(miembre);
        
        
        
        
        
    }//GEN-LAST:event_InformacionBotonActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        // TODO add your handling code here:
        
        
        CargarJSON abrir = new CargarJSON();
        
        
        abrir.setVisible(true);
        this.setVisible(false);
        
        
        
        
    }//GEN-LAST:event_RegresarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        MiembroFamilia mostrarcito = tablita.buscar(CajitaDescendencias.getSelectedItem().toString());
        
        elnuevoarbol.mostrarInformacionMiembro(mostrarcito);
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CampoTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoTituloActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String titulito = CampoTitulo.getText();
        
        buscarPorTitulo(titulito);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String rayado = GeneracionLista.getSelectedItem().toString();
        
        int numerin = Integer.parseInt(rayado);
        
        ListaSimple aca = obtenerMiembrosPorNivel(numerin);
        
        aca.imprimir();
        
        mostrarMiembrosGene(aca);
        
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(PestanasArboles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PestanasArboles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PestanasArboles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PestanasArboles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PestanasArboles().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CajitaDescendencias;
    private javax.swing.JComboBox<String> CajitaMiembros;
    private javax.swing.JTextField CampoTitulo;
    private javax.swing.JPanel Descendencias;
    private javax.swing.JComboBox<String> GeneracionLista;
    private javax.swing.JButton InformacionBoton;
    private javax.swing.JPanel MostrarArbolPanel;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
