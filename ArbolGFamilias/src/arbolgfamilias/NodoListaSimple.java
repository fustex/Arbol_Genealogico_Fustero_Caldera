/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 *
 * @author dugla
 */
public class NodoListaSimple {
    
    public NodoListaSimple pNext; 
    private String nombrehijo;
    
    

    public NodoListaSimple(String nombrehijo) {
        this.nombrehijo = nombrehijo;
        this.pNext = null;
    }
    
   

    /**
     * @return the pNext
     */
    public NodoListaSimple getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(NodoListaSimple pNext) {
        this.pNext = pNext;
    }

    /**
     * @return the nombrehijo
     */
    public String getNombrehijo() {
        return nombrehijo;
    }

    /**
     * @param nombrehijo the nombrehijo to set
     */
    public void setNombrehijo(String nombrehijo) {
        this.nombrehijo = nombrehijo;
    }

    /**
     * @return the nodito
     */
}
