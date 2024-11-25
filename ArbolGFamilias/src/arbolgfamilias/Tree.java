/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.EdgeRejectedException;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.swing_viewer.SwingViewer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.view.ViewerListener;

/**
 * Clase que representa un árbol genealógico.
 * Permite insertar miembros, mostrar el árbol y gestionar eventos de visualización.
 * 
 * @author Anthony Caldera
 */
public class Tree implements ViewerListener {
    
    
    private Viewer viewersito;
    private Nodo raiz;
    protected boolean loop = true;
    private Hash_Table hashTable;
    private String datito;
    
    
        
    
    
    /**
     * Constructor que inicializa el árbol con la raíz en null.
     * 
     * @author Francisco Fustero
     */

    public Tree() {
        this.raiz = null;
        
        
    }
    
    
    /**
     * Establece la tabla hash asociada al árbol.
     * 
     * @param hashTable la tabla hash a establecer.
     * @author Francisco Fustero
     */
    
    public void setHashTable(Hash_Table hashTable) {
        this.hashTable = hashTable;
    }

    
    /**
     * Obtiene la raíz del árbol.
     * 
     * @return la raíz del árbol.
     * @author Francisco Fustero
     */
    public Nodo getRoot() {
        return raiz;
    }
    
    
    /**
     * Verifica si el árbol está vacío.
     * 
     * @return true si el árbol está vacío, false en caso contrario.
     * @author Anthony Caldera
     */
    public boolean isEmpty() {
        return raiz == null; 
    }
    
    /**
     * Inserta un nuevo miembro en el árbol bajo un nodo padre.
     * 
     * @param miembro el miembro a insertar.
     * @param parent el nodo padre donde se insertará.
     * @author Francisco Fustero
     */
    public void insert(MiembroFamilia miembro, Nodo parent){
        Nodo newNode = new Nodo(miembro);
        if (!isEmpty()){
            parent.addsonsito(newNode);
        }
        
    }
    

    /**
     * Crea la raíz del árbol con un nuevo miembro.
     * 
     * @param miembro el miembro que se convertirá en la raíz.
     * @author Anthony Caldera
     */
    
    public void CrearRaiz(MiembroFamilia miembro){
        Nodo newNode = new Nodo(miembro);
        setRaiz(newNode);    
    }

    /**
     * Agrega un hijo a un nodo padre en el árbol.
     * 
     * @param padre el nodo padre.
     * @param hijo el nodo hijo a agregar.
     * @author Francisco Fustero
     */
    public void agregarHijo(Nodo padre, Nodo hijo) {
    if (padre.getHijo() == null) {
        padre.setHijo(hijo);
    } else {
        Nodo actual = padre.getHijo();
        while (actual.getHermano() != null) {
            actual = actual.getHermano();
        }
        actual.setHermano(hijo);
    }
    
}
    
    


    
    /**
     * Muestra el árbol en un panel específico.
     * 
     * @param panelDestino el panel donde se mostrará el árbol.
     * @author Anthony Caldera
     */
    public void mostrarArbol(JPanel panelDestino) {
    
    Graph graph = new SingleGraph("Árbol Familiar");
    graph.setAttribute("ui.stylesheet", 
        "node {size: 28px; fill-color: lightblue; text-alignment: center;} " +
        "edge {arrow-shape: arrow; arrow-size: 15px, 5px;} " +
        "graph {padding: 50px;}"); 

    
    agregarNodosYEnlaces(graph, this.raiz, true);

   
    SwingViewer viewer = new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
    viewer.enableAutoLayout(); 
    
    
    ViewPanel viewPanel = (ViewPanel) viewer.addDefaultView(false);
       
  
    viewPanel.setPreferredSize(new Dimension(panelDestino.getWidth(), panelDestino.getHeight()));
    viewPanel.setSize(panelDestino.getSize());
    viewPanel.setMinimumSize(panelDestino.getSize());
    
    
    
    ViewerPipe parabotones;

    
    panelDestino.setLayout(new BorderLayout());
    panelDestino.removeAll(); 
    panelDestino.add(viewPanel, BorderLayout.CENTER); 
    panelDestino.revalidate(); 
    panelDestino.repaint();
    
    parabotones = viewer.newViewerPipe();
    parabotones.addViewerListener(this);
    
    new Thread(() -> {
            while(true) {
                try {
                    parabotones.pump();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();


}


    @Override
    public void buttonPushed(String id) {

        handleNodeClick(id);
        System.out.println(id);
        System.out.println("funciona el click");

    }
    
    /**
     * Maneja el evento de clic en un nodo.
     * 
     * @param nodeId el ID del nodo que fue clickeado.
     * @author Francisco Fustero
     */
    
    private void handleNodeClick(String nodeId) {
        
            Hash_Table tablita = GlobalData.getHashTable();
            MiembroFamilia miembro = tablita.buscar(nodeId);
            if (miembro != null) {
                mostrarInformacionMiembro(miembro);
            } else {
                JOptionPane.showMessageDialog(null, "Miembro no encontrado");
            }
        
    }
    
    /**
     * Muestra la información de un miembro en un cuadro de diálogo.
     * 
     * @param miembro el miembro cuya información se mostrará.
     * @author Anthony Caldera
     */
    
    public void mostrarInformacionMiembro(MiembroFamilia miembro) {
        String hijos = miembro.getHijos().impresora();
        String info = "Nombre: " + miembro.getNombre() + "\n" +
                      "Sobrenombre: " + miembro.getSobrenombre() + "\n" +
                      "Padre: " + miembro.getPadre() + "\n" +
                      "Madre: " + miembro.getMadre() + "\n" +
                      "Mote: " + miembro.getMote() + "\n" +
                      "Esposa: " + miembro.getEsposa() + "\n" +
                      "Título Nobilario: " + miembro.getTitulonobilario() + "\n" +
                      "Notas: " + miembro.getNotes() + "\n" +
                      "Color de Ojos: " + miembro.getColorojos() + "\n" +
                      "Color de Cabello: " + miembro.getColorcabello() + "\n" +
                      "Destino: " + miembro.getFate() + "\n" +
                      "Hijos: " + hijos;

        JOptionPane.showMessageDialog(null, info, "Información del Miembro", JOptionPane.INFORMATION_MESSAGE);
    }
    
    


 
    
    /**
     * Agrega nodos y enlaces al gráfico del árbol.
     * 
     * @param graph el gráfico al que se agregarán los nodos y enlaces.
     * @param nodo el nodo actual a procesar.
     * @param esRaiz indica si el nodo es la raíz.
     * @author Anthony Caldera
     */
    private void agregarNodosYEnlaces(Graph graph, Nodo nodo, boolean esRaiz) {
    if (nodo == null) return;

    
    String nodeId = nodo.getMiembro().getNombre() + " " + nodo.getMiembro().getSobrenombre();
    System.out.println("Intentando agregar nodo con ID: " + nodeId);

    if (graph.getNode(nodeId) == null) {
        graph.addNode(nodeId);
        graph.getNode(nodeId).setAttribute("ui.label", nodo.getMiembro().getNombre() + " " + nodo.getMiembro().getSobrenombre());

        if (esRaiz) {
            graph.getNode(nodeId).setAttribute("ui.style", "fill-color: green;");
        } else {
            graph.getNode(nodeId).setAttribute("ui.style", "fill-color: lightblue;");
            
        }
    } else {
        System.out.println("Nodo con ID " + nodeId + " ya existe.");
        return; 
    }
    

    Nodo hijo = nodo.getHijo();
    while (hijo != null) {
        agregarNodosYEnlaces(graph, hijo, false);

        
        String childId = hijo.getMiembro().getNombre() + " " + hijo.getMiembro().getSobrenombre();
        System.out.println("Intentando agregar nodo hijo con ID: " + childId);

        if (graph.getNode(childId) == null) {
            graph.addNode(childId);
            graph.getNode(childId).setAttribute("ui.label", hijo.getMiembro().getNombre());
            graph.getNode(childId).setAttribute("ui.style", "fill-color: lightblue;");
        } else {
            System.out.println("Nodo hijo con ID " + childId + " ya existe.");
        }

        
        String edgeId = nodeId + "-" + childId;
        if (graph.getEdge(edgeId) == null) {
            graph.addEdge(edgeId, nodeId, childId, true);
        }

        hijo = hijo.getHermano();
    }
}


    



    /**
     * Realiza un recorrido en preorden del árbol.
     * 
     * @param root la raíz del árbol a recorrer.
     * @return una cadena con los nombres de los miembros en preorden.
     * @author Francisco Fustero
     */
    public String preorder(Nodo root){
        String toPrint = "";
        if (root != null){
            toPrint += root.getMiembro().getNombre() + "\n";
            toPrint += preorder(root.getHijo());
            toPrint += preorder(root.getHermano());
            
    }
        return toPrint;
    }

    
    /**
     * Establece la raíz del árbol.
     * 
     * @param raiz el nodo raíz a establecer.
     * @author Anthony Caldera
     */

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    /**
     * Obtiene la raíz del árbol.
     * 
     * @return la raíz del árbol.
     * @author Francisco Fustero
     */
    public Nodo getRaiz() {
        return raiz;
    }
    
    @Override
    
    public void viewClosed(String id) {
        loop = false;
    }

    @Override
    

      
    public void buttonReleased(String id) {
        
    }
    
    
    @Override
    
    public void mouseLeft(String id) {
        
    }
    
    
    @Override
    
    public void mouseOver(String id) {
        
    }
    
    
    /**
     * Vacía un nodo.
     * 
     * @param aux el nodo a vaciar.
     * @author Francisco Fustero
     */
    public void vaciar(Nodo aux) {
        aux = null;
    }
    
    /**
     * Verifica si el árbol o su raíz están vacíos.
     * 
     * @return true si el árbol o su raíz están vacíos, false en caso contrario.
     * @author Anthony Caldera
     */
    
    public boolean isEmpty1() {
        return raiz == null || raiz.getHijo() == null;
    }
    
    
    
    /**
     * Crea un árbol de descendencia a partir de un miembro específico.
     * 
     * @param arbolOriginal el árbol original.
     * @param nombreMiembro el miembro del cual se creará el árbol de descendencia.
     * @return un nuevo árbol de descendencia.
     * @author Francisco Fustero
     */
    public Tree crearArbolDescendencia(Tree arbolOriginal, MiembroFamilia nombreMiembro) {
    Tree descendenciaTree = new Tree();
    Nodo miembroNodo = buscarMiembro(arbolOriginal.getRaiz(), nombreMiembro); 

    if (miembroNodo != null) {
        
        descendenciaTree.CrearRaiz(new MiembroFamilia(
            miembroNodo.getMiembro().getNombre(),
            miembroNodo.getMiembro().getSobrenombre(),
            miembroNodo.getMiembro().getPadre(),
            miembroNodo.getMiembro().getMadre(),
            miembroNodo.getMiembro().getMote(),
            miembroNodo.getMiembro().getTitulonobilario(),
            miembroNodo.getMiembro().getEsposa(),
            miembroNodo.getMiembro().getColorojos(),
            miembroNodo.getMiembro().getColorcabello(),
            miembroNodo.getMiembro().getHijos(), 
            miembroNodo.getMiembro().getNotes(),
            miembroNodo.getMiembro().getFate()
        ));

        
        copiarDescendencia(descendenciaTree.getRaiz(), miembroNodo);
    }

    return descendenciaTree;
}

    
    
    /**
     * Copia la descendencia de un nodo original a un nuevo nodo.
     * 
     * @param nodoNuevo el nodo nuevo donde se copiará la descendencia.
     * @param nodoOriginal el nodo original del cual se copiará la descendencia.
     * @author Anthony Caldera
     */
    private void copiarDescendencia(Nodo nodoNuevo, Nodo nodoOriginal) {
    
    Nodo hijoActual = nodoOriginal.getHijo();

    while (hijoActual != null) {
        
        MiembroFamilia nuevoMiembro = new MiembroFamilia(
            hijoActual.getMiembro().getNombre(),
            hijoActual.getMiembro().getSobrenombre(),
            hijoActual.getMiembro().getPadre(),
            hijoActual.getMiembro().getMadre(),
            hijoActual.getMiembro().getMote(),
            hijoActual.getMiembro().getTitulonobilario(),
            hijoActual.getMiembro().getEsposa(),
            hijoActual.getMiembro().getColorojos(),
            hijoActual.getMiembro().getColorcabello(),
            hijoActual.getMiembro().getHijos(), 
            hijoActual.getMiembro().getNotes(),
            hijoActual.getMiembro().getFate()
        );

        
        Nodo nuevoNodoHijo = new Nodo(nuevoMiembro);
        nodoNuevo.addsonsito(nuevoNodoHijo); 

        
        copiarDescendencia(nuevoNodoHijo, hijoActual);

        hijoActual = hijoActual.getHermano(); 
    }
}


    /**
     * Busca un miembro en el árbol.
     * 
     * @param currentNode el nodo actual a procesar.
     * @param nombreMiembro el miembro a buscar.
     * @return el nodo del miembro encontrado, o null si no se encuentra.
     * @author Francisco Fustero
     */
    private Nodo buscarMiembro(Nodo currentNode, MiembroFamilia nombreMiembro) {
    if (currentNode == null) {
        return null; 
    }

    
    if (currentNode.getMiembro().getNombre().equals(nombreMiembro.getNombre()) &&
        currentNode.getMiembro().getSobrenombre().equals(nombreMiembro.getSobrenombre())) {
        return currentNode; 
    }

    
    Nodo hijo = currentNode.getHijo();
    while (hijo != null) {
        Nodo encontrado = buscarMiembro(hijo, nombreMiembro); 
        if (encontrado != null) {
            return encontrado; 
        }
        hijo = hijo.getHermano(); 
    }

    return null;
}

    
    /**
     * Calcula el número de niveles en el árbol.
     * 
     * @return el número de niveles en el árbol.
     * @author Anthony Caldera
     */
    public int calcularNiveles() {
        return calcularNivelesRecursivo(raiz);
    }

    
    /**
     * Método recursivo para calcular los niveles en el árbol.
     * 
     * @param nodo el nodo actual a procesar.
     * @return el número máximo de niveles desde el nodo actual.
     * @author Francisco Fustero
     */
    private int calcularNivelesRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0; 
        }

    int maxNivelHijos = 0;
    Nodo hijo = nodo.getHijo();
    while (hijo != null) {
        int nivelHijo = calcularNivelesRecursivo(hijo);
        if (nivelHijo > maxNivelHijos) {
            maxNivelHijos = nivelHijo; 
        }
        hijo = hijo.getHermano(); 
    }

    return maxNivelHijos + 1; 
}

    
    /**
     * Llena un JComboBox con los niveles del árbol.
     * 
     * @param comboBox el JComboBox a llenar.
     * @author Anthony Caldera
     */
    public void llenarComboBox(JComboBox comboBox) {
        int niveles = calcularNiveles();
        comboBox.removeAllItems(); 
        for (int i = 0; i < niveles; i++) {
            String a = String.valueOf(i);
            comboBox.addItem(a);  
        }
    }


    /**
     * Busca un nodo en el árbol por el nombre del miembro.
     * 
     * @param arbol el árbol donde se realizará la búsqueda.
     * @param nombreMiembro el nombre del miembro a buscar.
     * @return el nodo encontrado o null si no se encuentra.
     * @author Francisco Fustero
     */
    public Nodo buscarNodoEnArbol(Tree arbol, String nombreMiembro) {
        return buscarNodoRecursivo(arbol.getRaiz(), nombreMiembro);
    }

    
    /**
     * Método recursivo para buscar un nodo en el árbol.
     * 
     * @param nodoActual el nodo actual a procesar.
     * @param nombreMiembro el nombre del miembro a buscar.
     * @return el nodo encontrado o null si no se encuentra.
     * @author Anthony Caldera
     */
    private Nodo buscarNodoRecursivo(Nodo nodoActual, String nombreMiembro) {
        if (nodoActual == null) {
            return null;
        }

        MiembroFamilia miembroActual = nodoActual.getMiembro();
        String revisar = miembroActual.getNombre() + " " + miembroActual.getSobrenombre();


        if (revisar.equals(nombreMiembro)) {
            System.out.println("se encontro: " + (miembroActual.getNombre() + " " + miembroActual.getSobrenombre() == nombreMiembro));
            return nodoActual;
        }

        Nodo hijo = nodoActual.getHijo();
        while (hijo != null) {
            Nodo nodoEncontrado = buscarNodoRecursivo(hijo, nombreMiembro);
            if (nodoEncontrado != null) {
                return nodoEncontrado;
            }
            hijo = hijo.getHermano();
        }



        return null;
    }

    
    /**
     * Crea una lista de miembros de familia a partir del árbol.
     * 
     * @param arbolito el árbol del cual se creará la lista.
     * @param nombremiembro el nombre del miembro a buscar.
     * @return una lista de miembros de familia.
     * @author Francisco Fustero
     */
    public ListaSimpleMiembroFamilia CrearListaMiembroFamilia(Tree arbolito, String nombremiembro){

        FamilyTreeBuilder pa = new FamilyTreeBuilder();
        ListaSimpleMiembroFamilia nuevalista = new ListaSimpleMiembroFamilia();


        Nodo guardar = arbolito.buscarNodoEnArbol(arbolito, nombremiembro);
        MiembroFamilia miembroguardar = guardar.getMiembro();

        nuevalista.AgregarAlinicio(miembroguardar);
        while(guardar != null){



        Nodo padrenuestro = pa.ElModificador2(arbolito.getRaiz(), guardar, arbolito);
        if(padrenuestro != null){
        MiembroFamilia aux = padrenuestro.getMiembro();
        nuevalista.AgregarAlinicio(aux);
        }
        guardar = padrenuestro;
        }
        return nuevalista;



    }



    
    /**
     * Crea un árbol a partir de una lista de miembros de familia.
     * 
     * @param lista la lista de miembros de familia a partir de la cual se creará el árbol.
     * @return un nuevo árbol creado a partir de la lista.
     * @author Anthony Caldera
     */
    public Tree crearArbolDesdeLista(ListaSimpleMiembroFamilia lista) {
        Tree nuevoArbol = new Tree(); 

        if (lista == null || lista.getCabeza() == null) {
            return nuevoArbol; 
        }

        NodosListaSimpleMiembroFamilia actual = lista.getCabeza();
        MiembroFamilia primerMiembro = actual.getMiembross();


        nuevoArbol.CrearRaiz(primerMiembro);

        Nodo nodoActual = nuevoArbol.getRaiz(); 


        while (actual.getPnext() != null) {
            actual = actual.getPnext(); 
            MiembroFamilia nuevoMiembro = actual.getMiembross();

            Nodo nuevoNodo = new Nodo(nuevoMiembro); 
            nuevoArbol.agregarHijo(nodoActual, nuevoNodo); 
            nodoActual = nuevoNodo; 
        }

        return nuevoArbol; 
    }





}


