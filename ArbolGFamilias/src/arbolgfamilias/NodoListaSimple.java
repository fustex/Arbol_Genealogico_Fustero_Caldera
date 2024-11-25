/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 * Clase que representa un nodo en una lista simple enlazada.
 * Cada nodo contiene un nombre de hijo y un puntero al siguiente nodo.
 * 
 * @author Francisco Fustero
 */
public class NodoListaSimple {
    
    public NodoListaSimple pNext; 
    private String nombrehijo; 

    /**
     * Constructor de la clase NodoListaSimple.
     * Inicializa el nodo con el nombre del hijo y establece el puntero siguiente como null.
     * 
     * @param nombrehijo el nombre del hijo.
     * @author Francisco Fustero
     */
    public NodoListaSimple(String nombrehijo) {
        this.nombrehijo = nombrehijo;
        this.pNext = null;
    }

    /**
     * Obtiene el puntero al siguiente nodo.
     * 
     * @return el siguiente nodo.
     * @author Francisco Fustero
     */
    public NodoListaSimple getpNext() {
        return pNext;
    }

    /**
     * Establece el puntero al siguiente nodo.
     * 
     * @param pNext el nuevo nodo siguiente.
     * @author Francisco Fustero
     */
    public void setpNext(NodoListaSimple pNext) {
        this.pNext = pNext;
    }

    /**
     * Obtiene el nombre del hijo.
     * 
     * @return el nombre del hijo.
     * @author Francisco Fustero
     */
    public String getNombrehijo() {
        return nombrehijo;
    }

    /**
     * Establece el nombre del hijo.
     * 
     * @param nombrehijo el nuevo nombre del hijo.
     * @author Francisco Fustero
     */
    public void setNombrehijo(String nombrehijo) {
        this.nombrehijo = nombrehijo;
    }
}
