/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 * Clase que representa una lista simple enlazada de miembros de la familia.
 * Permite agregar y manejar nodos de tipo {@link NodosListaSimpleMiembroFamilia}.
 * 
 * @author Francisco Fustero
 */
public class ListaSimpleMiembroFamilia {
    
    private NodosListaSimpleMiembroFamilia cabeza; 

    /**
     * Constructor de la clase ListaSimpleMiembroFamilia.
     * Inicializa la cabeza de la lista como null.
     * 
     * @author Francisco Fustero
     */
    public ListaSimpleMiembroFamilia() {
        this.cabeza = null;
    }
    
    /**
     * Agrega un nuevo miembro al inicio de la lista.
     * 
     * @param miembro el objeto MiembroFamilia a agregar.
     * @author Francisco Fustero
     */
    public void AgregarAlinicio(MiembroFamilia miembro) {
        NodosListaSimpleMiembroFamilia nuevo = new NodosListaSimpleMiembroFamilia(miembro);
        if (!esVacia()) {
            nuevo.setPnext(cabeza);
            cabeza = nuevo;
        } else {
            cabeza = nuevo;
        }
    }
    
    /**
     * Imprime los nombres de los miembros en la lista.
     * Si la lista está vacía, se indica que no hay elementos.
     * 
     * @author Francisco Fustero
     */
    public void Imprimir() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodosListaSimpleMiembroFamilia nodoActual = cabeza;
        
        while (nodoActual != null) {
            System.out.print(nodoActual.getMiembross().getNombre() + " "); 
            nodoActual = nodoActual.getPnext(); 
        }
        
        System.out.println(); 
    }
    
    /**
     * Verifica si la lista está vacía.
     * 
     * @return true si la lista está vacía, false en caso contrario.
     * @author Francisco Fustero
     */
    public boolean esVacia() {
        return cabeza == null;
    }

    /**
     * Obtiene la cabeza de la lista.
     * 
     * @return el nodo cabeza de la lista.
     */
    public NodosListaSimpleMiembroFamilia getCabeza() {
        return cabeza;
    }

    /**
     * Establece el nodo cabeza de la lista.
     * 
     * @param cabeza el nuevo nodo cabeza de la lista.
     * @author Francisco Fustero
     */
    public void setCabeza(NodosListaSimpleMiembroFamilia cabeza) {
        this.cabeza = cabeza;
    }
}
