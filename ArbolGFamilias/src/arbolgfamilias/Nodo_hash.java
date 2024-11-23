/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 *
 * @author dugla
 */
public class Nodo_hash {
    
    private String name;
    private MiembroFamilia miembro;
    private Nodo_hash next;

    public Nodo_hash(String name, MiembroFamilia miembro) {
        this.name = name;
        this.miembro = miembro;
        this.next = null;
    }

    public String getName() {
        return name;
    }

    public MiembroFamilia getMiembro() {
        return miembro;
    }

    public void setMiembro(MiembroFamilia miembro) {
        this.miembro = miembro;
    }

    /**
     * @return the next
     */
    public Nodo_hash getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Nodo_hash next) {
        this.next = next;
    }

    
    
    
    
    
}
