/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package arbolgfamilias;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Esta clase representa una interfaz gráfica para gestionar y mostrar un árbol genealógico.
 * Permite buscar miembros por nombre o título y visualizar sus descendencias y antepasados.
 * 
 * @author Anthony Caldera
 */
public class PestanasArboles extends javax.swing.JFrame {
    
    
    Tree familyTree = GlobalData.getTree();
    
    Hash_Table tablita = GlobalData.getHashTable();
    
    

    /**
     * Crea una nueva instancia de PestanasArboles y inicializa los componentes de la interfaz.
     * 
     * @author Anthony Caldera
     */
    public PestanasArboles() {

        initComponents();
        setLocationRelativeTo(null);
        
        cargarArbol();
        llenarCajitaMiembros();
        familyTree.llenarComboBox(GeneracionLista);
        

    }
    
    Tree elnuevoarbol = new Tree();
    
    
     /**
     * Clase interna que maneja la genealogía y permite seleccionar un miembro de la familia.
     */
    public class Genealogia {
    
        private Hash_Table hashTable;
        
        /**
         * Constructor que inicializa la clase Genealogia con una tabla hash.
         * 
         * @param hashTable la tabla hash que contiene los miembros de la familia.
         * @author Anthony Caldera
         */
        public Genealogia(Hash_Table hashTable) {
        this.hashTable = hashTable;
        }
    
    
    /**
     * Permite seleccionar un miembro de la familia mediante un cuadro de diálogo.
     * 
     * @author Anthony Caldera
     */
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
            
        }
    }
    
        /**
         * Devuelve el sufijo correspondiente a un número.
         * 
         * @param numero el número al que se le quiere obtener el sufijo.
         * @return el sufijo correspondiente.
         */
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
    
    /**
     * Busca miembros de la familia por título y muestra los resultados.
     * 
     * @param tituloBuscado el título que se desea buscar.
     * @author Anthony Caldera
     */
    
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
    
    /**
     * Carga y muestra el árbol genealógico en el panel correspondiente.
     * 
     * @author Anthony Caldera
     */
    private void cargarArbol() {
        if (familyTree != null) {
            familyTree.mostrarArbol(MostrarArbolPanel);
        } else {
            System.out.println("No se encontró ningún árbol.");
        }
    }
    
    /**
     * Carga y muestra el árbol de descendencias en el panel correspondiente.
     * 
     * @author Anthony Caldera
     */
    
    private void cargarArbol2() {
        if (elnuevoarbol != null) {
            elnuevoarbol.mostrarArbol(Descendencias);
        } else {
            System.out.println("No se encontró ningún árbol.");
        }
    }
    
    /**
     * Llena la lista de miembros en el panel correspondiente.
     * 
     * @author Anthony Caldera
     */
    private void llenarCajitaMiembros() {
    if (familyTree != null && familyTree.getRaiz() != null) {
        recorrerYAgregar(familyTree.getRaiz()); 
    } else {
        System.out.println("El árbol está vacío o no está inicializado.");
    }
}

    /**
     * Recorre el árbol y agrega los miembros a la lista.
     * 
     * @param nodo el nodo actual a procesar.
     * @author Anthony Caldera
     */
    private void recorrerYAgregar(Nodo nodo) {
        if (nodo == null) {
            return; 
        }

        
        MiembroFamilia miembro = nodo.getMiembro();
        String item = miembro.getNombre() + " " + miembro.getSobrenombre();
        
        CajitaparaPasados.addItem(item);
        

        
        Nodo hijo = nodo.getHijo();
        while (hijo != null) {
            recorrerYAgregar(hijo); 
            hijo = hijo.getHermano(); 
        }
    }
    
    /**
     * Llena la lista de miembros de las descendencias.
     * 
     * @author Anthony Caldera
     */
    private void llenarCajitaMiembrosDescendencias() {
        
    if (elnuevoarbol != null && elnuevoarbol.getRaiz() != null) {
        recorrerYAgregar2(elnuevoarbol.getRaiz()); 
    } else {
        System.out.println("El árbol está vacío o no está inicializado.");
    }
}
    
    /**
     * Recorre el árbol de descendencias y agrega los miembros a la lista.
     * 
     * @param nodo el nodo actual a procesar.
     * @author Anthony Caldera
     */
    private void recorrerYAgregar2(Nodo nodo) {
        if (nodo == null) {
            return; 
        }

        
        MiembroFamilia miembro = nodo.getMiembro();
        String item = miembro.getNombre() + " " + miembro.getSobrenombre();
        

        
        Nodo hijo = nodo.getHijo();
        while (hijo != null) {
            recorrerYAgregar2(hijo); 
            hijo = hijo.getHermano(); 
        }
    }
    
    /**
     * Capitaliza el nombre ingresado.
     * 
     * @param nombre el nombre a capitalizar.
     * @return el nombre capitalizado.
     * @author Anthony Caldera
     */
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
    
    
    /**
     * Obtiene los miembros de la familia por nivel y devuelve una lista.
     * 
     * @param nivel el nivel de generación que se desea obtener.
     * @return una lista de miembros en el nivel especificado.
     * @author Anthony Caldera
     */
    public ListaSimple obtenerMiembrosPorNivel(int nivel) {
    ListaSimple listaMiembros = new ListaSimple();
    obtenerMiembrosPorNivelRecursivo(familyTree.getRaiz(), nivel, 0, listaMiembros);
    return listaMiembros;
}
    
    /**
     * Método recursivo que obtiene los miembros por nivel.
     * 
     * @param nodo el nodo actual a procesar.
     * @param nivelDeseado el nivel deseado.
     * @param nivelActual el nivel actual en la recursión.
     * @param listaMiembros la lista donde se almacenarán los miembros encontrados.
     * @author Anthony Caldera
     */
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
    
    /**
     * Muestra los miembros de la generación en un cuadro de diálogo.
     * 
     * @param listaMiembros la lista de miembros a mostrar.
     * @author Anthony Caldera
     */
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
        jLabel2 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Descendencias = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        CampoTitulo = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        GeneracionLista = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanelAntePasados = new javax.swing.JPanel();
        CajitaparaPasados = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 204, 0));

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(51, 204, 0));

        MostrarArbolPanel.setBackground(new java.awt.Color(153, 153, 153));

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

        jLabel2.setText("LLENAR");

        Regresar.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        Regresar.setText("Cargar Nuevo Archivo Json");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Arbol Completo Correspondiente:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MostrarArbolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Regresar)))
                        .addGap(0, 191, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MostrarArbolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Regresar)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Arbol Completo", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 204, 0));

        jButton1.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
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
            .addGap(0, 674, Short.MAX_VALUE)
        );
        DescendenciasLayout.setVerticalGroup(
            DescendenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jLabel10.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Busqueda por Nombre: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(Descendencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(Descendencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mostrar Arbol por Nombre", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 204, 0));

        CampoTitulo.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        CampoTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoTituloActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        GeneracionLista.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N

        jButton4.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jButton4.setText("Mostrar Generacion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ingresa el titulo de algun miembro de la familia");

        jLabel12.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Selecciona alguna generación para su lista (Por Nivel)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jButton4)
                    .addComponent(CampoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GeneracionLista, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(610, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CampoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(190, 190, 190)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GeneracionLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Busqueda por Titulo y Lista de Generacion", jPanel3);

        jPanel4.setBackground(new java.awt.Color(0, 204, 0));

        PanelAntePasados.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout PanelAntePasadosLayout = new javax.swing.GroupLayout(PanelAntePasados);
        PanelAntePasados.setLayout(PanelAntePasadosLayout);
        PanelAntePasadosLayout.setHorizontalGroup(
            PanelAntePasadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
        );
        PanelAntePasadosLayout.setVerticalGroup(
            PanelAntePasadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );

        CajitaparaPasados.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        CajitaparaPasados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CajitaparaPasadosActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jButton5.setText("Mostrar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Arbol Para los Nntepasados");

        jLabel8.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Muestra sus antepasados:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(PanelAntePasados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(CajitaparaPasados, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajitaparaPasados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5))
                    .addComponent(PanelAntePasados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mostrar Antepasados", jPanel4);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Maneja el evento de acción del botón para buscar un miembro por nombre.
     * Se crea una instancia de la clase Genealogia y se llama al método 
     * para seleccionar un miembro.
     * 
     * @param evt el evento de acción generado por el botón.
     * @author Anthony Caldera
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
         
        
       Genealogia genealogia = new Genealogia(tablita);
       genealogia.seleccionarMiembro();
        
        
        
        
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    /**
     * Maneja el evento de acción del botón para regresar a la ventana 
     * de carga de un nuevo archivo JSON. Se crea una instancia de 
     * CargarJSON y se establece visible, ocultando la ventana actual.
     * 
     * @param evt el evento de acción generado por el botón.
     * @author Anthony Caldera
     */
    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        // TODO add your handling code here:
        
        
        CargarJSON abrir = new CargarJSON();
        
        
        abrir.setVisible(true);
        this.setVisible(false);
        
        
        
        
    }//GEN-LAST:event_RegresarActionPerformed

    
    private void CampoTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoTituloActionPerformed

    
    /**
     * Maneja el evento de acción del botón para buscar miembros por título.
     * Obtiene el texto del campo de título y llama al método para buscar 
     * por título.
     * 
     * @param evt el evento de acción generado por el botón.
     * @author Anthony Caldera
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String titulito = CampoTitulo.getText();
        
        buscarPorTitulo(titulito);
    }//GEN-LAST:event_jButton3ActionPerformed

    
    /**
     * Maneja el evento de acción del botón para mostrar miembros por nivel.
     * Obtiene el nivel seleccionado en la lista y llama al método para 
     * obtener miembros de ese nivel, mostrando los resultados.
     * 
     * @param evt el evento de acción generado por el botón.
     * @author Anthony Caldera
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String rayado = GeneracionLista.getSelectedItem().toString();
        
        int numerin = Integer.parseInt(rayado);
        
        ListaSimple aca = obtenerMiembrosPorNivel(numerin);
        
        aca.imprimir();
        
        mostrarMiembrosGene(aca);
        
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void CajitaparaPasadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajitaparaPasadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CajitaparaPasadosActionPerformed

    
    /**
     * Maneja el evento de acción del botón para mostrar los antepasados 
     * del miembro seleccionado. Crea un nuevo árbol de antepasados y 
     * lo muestra en el panel correspondiente.
     * 
     * @param evt el evento de acción generado por el botón.
     * @author Anthony Caldera
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        FamilyTreeBuilder llamada = new FamilyTreeBuilder();
        
        
        
       
        
        String guardar = CajitaparaPasados.getSelectedItem().toString();
        
        MiembroFamilia nombrcompleto = tablita.buscar(guardar);
        
        String nuevonombre = nombrcompleto.getNombre() + " " + nombrcompleto.getSobrenombre();
        
        Nodo elhijodelquequierobuscar = familyTree.buscarNodoEnArbol(familyTree, nuevonombre);
        
        Nodo guardarpadre = llamada.ElModificador2(familyTree.getRaiz(), elhijodelquequierobuscar, familyTree);
        
        
       
        
        ListaSimpleMiembroFamilia guardar1 = familyTree.CrearListaMiembroFamilia(familyTree, nuevonombre);
        
        Tree nuevoarbol2 = familyTree.crearArbolDesdeLista(guardar1);
        
        
        nuevoarbol2.mostrarArbol(PanelAntePasados);
        
        
        
        
        
      
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

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
    private javax.swing.JComboBox<String> CajitaparaPasados;
    private javax.swing.JTextField CampoTitulo;
    private javax.swing.JPanel Descendencias;
    private javax.swing.JComboBox<String> GeneracionLista;
    private javax.swing.JPanel MostrarArbolPanel;
    private javax.swing.JPanel PanelAntePasados;
    private javax.swing.JButton Regresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
