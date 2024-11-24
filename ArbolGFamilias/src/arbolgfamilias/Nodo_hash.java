/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;


/**
 * Esta clase representa un nodo en una estructura de hash, donde cada nodo
 * contiene un nombre y un miembro de la familia, así como una referencia
 * al siguiente nodo en la lista.
 * 
 * @author Anthony Caldera
 */
public class Nodo_hash {
    
    private String name; 
    private MiembroFamilia miembro; 
    private Nodo_hash next; 

    /**
     * Constructor que inicializa un nodo hash con un nombre y un miembro de la familia.
     * 
     * @param name el nombre asociado al nodo.
     * @param miembro el miembro de la familia que se asociará al nodo.
     * @author Anthony Caldera
     */
    public Nodo_hash(String name, MiembroFamilia miembro) {
        this.name = name;
        this.miembro = miembro;
        this.next = null;
    }

    /**
     * @return el nombre asociado al nodo.
     * @author Anthony Caldera
     */
    public String getName() {
        return name;
    }

    /**
     * @return el miembro de la familia asociado a este nodo.
     * @author Anthony Caldera
     */
    public MiembroFamilia getMiembro() {
        return miembro;
    }

    /**
     * Establece el miembro de la familia asociado a este nodo.
     * 
     * @param miembro el nuevo miembro a establecer.
     * @author Anthony Caldera
     */
    public void setMiembro(MiembroFamilia miembro) {
        this.miembro = miembro;
    }

    /**
     * @return el siguiente nodo en la lista.
     * @author Anthony Caldera
     */
    public Nodo_hash getNext() {
        return next;
    }

    /**
     * Establece el siguiente nodo en la lista.
     * 
     * @param next el nuevo nodo a establecer como siguiente.
     * @author Anthony Caldera
     */
    public void setNext(Nodo_hash next) {
        this.next = next;
    }
}
