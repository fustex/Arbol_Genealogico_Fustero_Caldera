


package arbolgfamilias;






/**
 * Esta clase representa un nodo en una estructura de árbol, donde cada nodo
 * contiene un miembro de la familia y referencias a sus hijos y hermanos.
 * 
 * @author Anthony Caldera
 */
public class Nodo {
    
    public MiembroFamilia miembro; 
    public Nodo hijo; 
    public Nodo hermano; 

    /**
     * Constructor que inicializa un nodo con un miembro de la familia.
     * 
     * @param miembro el miembro de la familia que se asociará al nodo.
     * @author Anthony Caldera
     */
    public Nodo(MiembroFamilia miembro) {
        this.miembro = miembro;
        this.hijo = null;
        this.hermano = null;
    }

    /**
     * Agrega un nuevo hijo al nodo. Si ya existe un hijo, lo añade como hermano
     * del último hijo.
     * 
     * @param newson el nuevo nodo hijo a agregar.
     * @author Anthony Caldera
     */
    public void addsonsito(Nodo newson) {
        if (getHijo() != null) {
            Nodo temp = getHijo();
            Nodo sib = temp;
            while (temp != null) {
                sib = temp;
                temp = temp.getHermano();
            }
            sib.setHermano(newson);
        } else {
            setHijo(newson);
        }
    }

    /**
     * @return el miembro de la familia asociado a este nodo.
     * @author Anthony Caldera
     */
    public MiembroFamilia getMiembro() {
        return miembro;
    }

    /**
     * @return el primer hijo del nodo.
     * @author Anthony Caldera
     */
    public Nodo getHijo() {
        return hijo;
    }

    /**
     * @return el hermano del nodo.
     * @author Anthony Caldera
     */
    public Nodo getHermano() {
        return hermano;
    }

    /**
     * Establece el primer hijo del nodo.
     * 
     * @param hijo el nuevo hijo a establecer.
     * @author Anthony Caldera
     */
    public void setHijo(Nodo hijo) {
        this.hijo = hijo;
    }

    /**
     * Establece el hermano del nodo.
     * 
     * @param hermano el nuevo hermano a establecer.
     * @author Anthony Caldera
     */
    public void setHermano(Nodo hermano) {
        this.hermano = hermano;
    }
}
