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
import javax.swing.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.view.ViewerListener;

/**
 *
 * @author dugla
 */
public class Tree implements ViewerListener {
    
    
    private Viewer viewersito;
    private Nodo raiz;
    protected boolean loop = true;
    private Hash_Table hashTable;
    private String datito;
    
    

    public Tree() {
        this.raiz = null;
        
        
    }
    
    
    public void enviardatito(String datito) {
      this.datito = datito;
    }
    
    public void setHashTable(Hash_Table hashTable) {
        this.hashTable = hashTable;
    }

    public Nodo getRoot() {
        return raiz;
    }
    
    public boolean isEmpty() {
        return raiz == null; 
    }
    
    public void insert(MiembroFamilia miembro, Nodo parent){
        Nodo newNode = new Nodo(miembro);
        if (!isEmpty()){
            parent.addsonsito(newNode);
        }
        
    }
    
    
    public void CrearRaiz(MiembroFamilia miembro){
        Nodo newNode = new Nodo(miembro);
        setRaiz(newNode);    
    }

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
    
    public void mostrarArbolrecuperado() {
    Graph graph = new SingleGraph("Árbol Familiar");
    graph.setAttribute("ui.stylesheet", "node {size: 28px; fill-color: lightblue; text-alignment: center;} " +
                                        "edge {arrow-shape: arrow; arrow-size: 15px, 5px;}");
    agregarNodosYEnlaces(graph, this.raiz, true);

    JFrame frame = new JFrame("Árbol Familiar");
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.setSize(1280, 720);

    SwingViewer viewer = new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
    viewer.enableAutoLayout();

    ViewerPipe pipe = viewer.newViewerPipe();
    pipe.addViewerListener(this);
    
    

    JPanel panel = (JPanel) viewer.addDefaultView(false);
    frame.add(panel, BorderLayout.CENTER);

    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    

    viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
}
    


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
    
    

    
    panelDestino.setLayout(new BorderLayout());
    panelDestino.removeAll(); 
    panelDestino.add(viewPanel, BorderLayout.CENTER); 
    panelDestino.revalidate(); 
    panelDestino.repaint(); 

    


}


    @Override
    public void buttonPushed(String id) {

        handleNodeClick(id);
        System.out.println("funciona el click");

    }
    
    private void handleNodeClick(String nodeId) {
        if (hashTable != null) {
            MiembroFamilia miembro = hashTable.buscar(nodeId);
            if (miembro != null) {
                JOptionPane.showMessageDialog(null, "Miembro encontrado: " + miembro.getNombre() + " " + miembro.getSobrenombre());
                mostrarInformacionMiembro(miembro);
            } else {
                JOptionPane.showMessageDialog(null, "Miembro no encontrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tabla hash no inicializada");
        }
    }
    
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

    
    



    
    public String preorder(Nodo root){
        String toPrint = "";
        if (root != null){
            toPrint += root.getMiembro().getNombre() + "\n";
            toPrint += preorder(root.getHijo());
            toPrint += preorder(root.getHermano());
            
    }
        return toPrint;
    }


    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    /**
     * @return the raiz
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
    
    
    
    public void vaciar(Nodo aux) {
        aux = null;
    }
    
    public boolean isEmpty1() {
        return raiz == null || raiz.getHijo() == null;
    }
    
    
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

public int calcularNiveles() {
    return calcularNivelesRecursivo(raiz);
}

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

public void llenarComboBox(JComboBox comboBox) {
    int niveles = calcularNiveles();
    comboBox.removeAllItems(); 
    for (int i = 0; i < niveles; i++) {
        String a = String.valueOf(i);
        comboBox.addItem(a);  
    }
}

}


