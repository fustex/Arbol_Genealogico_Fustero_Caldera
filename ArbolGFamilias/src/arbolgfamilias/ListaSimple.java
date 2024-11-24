/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 *
 * @author dugla
 */
public class ListaSimple {
    
    public NodoListaSimple head;
    private int size;

    /**
     * Constructor de la clase lista simple
     * @author Anthony Caldera
     */
    
    public ListaSimple() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Obtengo la cabeza de la lista  simple (primer elemento)
     * @author Anthony Caldera
     * @return the head
     */
    public Object getHead() {
        return head;
    }

    /**
     * Colocamos el apuntador a la cabeza de la lista simple
     * @author Anthony Caldera
     * @param head the head to set
     */
    public void setHead(NodoListaSimple head) {
        this.head = head;
    }

    /**
     * Nos da el tamaño de la lista simple
     * @author Anthony Caldera
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Nos da para colocar el tamano de la lista simple
     * @author Anthony Caldera
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    
    
    
    public void agregar(String nombrehijo) {
           NodoListaSimple nuevo = new NodoListaSimple(nombrehijo);
           nuevo.setpNext(head);
           head = nuevo;
    
}
    
    
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
    
    public String impresora(){
        String vacio = "";
        if (head == null){
            
            return "No hay hijos para este miembro";
        }
        
        NodoListaSimple noditoactual = head;
        
        while(noditoactual != null){
            vacio += noditoactual.getNombrehijo() + ", ";
            noditoactual = noditoactual.getpNext();
        }
        
        return vacio;
        
    }
    
}
