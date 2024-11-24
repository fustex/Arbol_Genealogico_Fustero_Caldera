/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 *
 * @author ffust
 */
/**
 * Clase que representa un nodo en una lista simple enlazada de miembros de la familia.
 * Cada nodo contiene un puntero al siguiente nodo y un objeto de tipo {@link MiembroFamilia}.
 * 
 * @author Francisco Fustero
 */
public class NodosListaSimpleMiembroFamilia {
    
    private NodosListaSimpleMiembroFamilia pnext; // Puntero al siguiente nodo
    private MiembroFamilia Miembross; // Objeto miembro de la familia

    /**
     * Constructor de la clase NodosListaSimpleMiembroFamilia.
     * Inicializa el nodo con un miembro de la familia y establece el puntero siguiente como null.
     * 
     * @param Miembross el objeto MiembroFamilia a almacenar en el nodo.
     * @author Francisco Fustero
     */
    public NodosListaSimpleMiembroFamilia(MiembroFamilia Miembross) {
        this.pnext = null;
        this.Miembross = Miembross;
    }

    /**
     * Obtiene el puntero al siguiente nodo.
     * 
     * @return el siguiente nodo.
     * @author Francisco Fustero
     */
    public NodosListaSimpleMiembroFamilia getPnext() {
        return pnext;
    }

    /**
     * Establece el puntero al siguiente nodo.
     * 
     * @param pnext el nuevo nodo siguiente.
     * @author Francisco Fustero
     */
    public void setPnext(NodosListaSimpleMiembroFamilia pnext) {
        this.pnext = pnext;
    }

    /**
     * Obtiene el miembro de la familia asociado a este nodo.
     * 
     * @return el objeto MiembroFamilia.
     * @author Francisco Fustero
     */
    public MiembroFamilia getMiembross() {
        return Miembross;
    }

    /**
     * Establece el miembro de la familia para este nodo.
     * 
     * @param Miembross el nuevo objeto MiembroFamilia a almacenar en el nodo.
     * @author Francisco Fustero
     */
    public void setMiembross(MiembroFamilia Miembross) {
        this.Miembross = Miembross;
    }
}

