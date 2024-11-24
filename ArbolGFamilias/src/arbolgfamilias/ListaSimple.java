/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 *
 * @author ffustero
 */
public class ListaSimple {
    
    public NodoListaSimple head; 
    private int size; 

    /**
     * Constructor de la clase ListaSimple.
     * Inicializa la cabeza de la lista como null y el tamaño como 0.
     * 
     * @author Francisco Fustero
     */
    public ListaSimple() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Obtiene la cabeza de la lista simple (primer elemento).
     * 
     * @return el nodo cabeza de la lista.
     * @author Francisco Fustero
     */
    public Object getHead() {
        return head;
    }

    /**
     * Establece el apuntador a la cabeza de la lista simple.
     * 
     * @param head el nuevo nodo cabeza de la lista.
     * @author Francisco Fustero
     */
    public void setHead(NodoListaSimple head) {
        this.head = head;
    }

    /**
     * Obtiene el tamaño de la lista simple.
     * 
     * @return el tamaño actual de la lista.
     * @author Francisco Fustero
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece el tamaño de la lista simple.
     * 
     * @param size el nuevo tamaño a establecer.
     * @author Francisco Fustero
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Agrega un nuevo nodo a la lista simple con el nombre del hijo.
     * 
     * @param nombrehijo el nombre del hijo a agregar.
     * @author Francisco Fustero
     */
    public void agregar(String nombrehijo) {
        NodoListaSimple nuevo = new NodoListaSimple(nombrehijo);
        nuevo.setpNext(head);
        head = nuevo;
    }
    
    /**
     * Agrega un nuevo nodo a la lista simple usando un objeto MiembroFamilia.
     * 
     * @param miembro el objeto MiembroFamilia a agregar como nodo.
     * @author Francisco Fustero
     */
    public void agregar1(MiembroFamilia miembro) {
        NodoListaSimple nuevoNodo = new NodoListaSimple(miembro.getNombre() + " " + miembro.getSobrenombre());
        if (head == null) {
            head = nuevoNodo;
        } else {
            NodoListaSimple temp = head;
            while (temp.getpNext() != null) {
                temp = temp.getpNext();
            }
            temp.pNext = nuevoNodo;
        }
    }
    
    /**
     * Imprime los nombres de los nodos en la lista simple.
     * Si la lista está vacía, se indica que no hay elementos.
     * 
     * @author Francisco Fustero
     */
    public void imprimir() {
        if (head == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoListaSimple nodoActual = head;
        
        while (nodoActual != null) {
            System.out.print(nodoActual.getNombrehijo() + " "); 
            nodoActual = nodoActual.getpNext(); 
        }
        
        System.out.println(); 
    }
    
    /**
     * Devuelve una representación en cadena de los nombres de los nodos en la lista.
     * 
     * @return una cadena con los nombres de los hijos, o un mensaje si no hay hijos.
     * @author Francisco Fustero
     */
    public String impresora() {
        String vacio = "";
        if (head == null) {
            return "No hay hijos para este miembro";
        }
        
        NodoListaSimple noditoactual = head;
        
        while (noditoactual != null) {
            vacio += noditoactual.getNombrehijo() + "\n";
            noditoactual = noditoactual.getpNext();
        }
        
        return vacio;
    }
}
