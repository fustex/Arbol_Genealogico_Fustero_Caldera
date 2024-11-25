/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

import javax.swing.JOptionPane;

/**
 * Esta clase representa a un miembro de la familia con varios atributos
 * que describen sus relaciones y características personales.
 * 
 * @author Anthony Caldera
 */
public class MiembroFamilia { 
    private String nombre;
    private String sobrenombre;
    private String padre;
    private String madre;
    private ListaSimple hijos = new ListaSimple();
    private String mote;
    private String esposa;
    private String titulonobilario;
    private String notes;
    private String colorojos;
    private String colorcabello;
    private String fate;

    /**
     * Constructor que inicializa un nuevo miembro de la familia.
     * 
     * @param nombre el nombre del miembro.
     * @param sobrenombre el sobrenombre del miembro.
     * @param padre el nombre del padre.
     * @param madre el nombre de la madre.
     * @param mote el mote del miembro.
     * @param titulonobilario el título nobiliario del miembro.
     * @param esposa el nombre de la esposa.
     * @param colorojos el color de ojos del miembro.
     * @param colorcabello el color de cabello del miembro.
     * @param hijos la lista de hijos del miembro.
     * @param notes notas adicionales sobre el miembro.
     * @param fate el destino del miembro.
     * @author Anthony Caldera
     */
    public MiembroFamilia(String nombre, String sobrenombre, String padre, String madre, String mote, String titulonobilario, String esposa, String colorojos, String colorcabello, ListaSimple hijos, String notes, String fate) {
        this.nombre = nombre;
        this.sobrenombre = sobrenombre;
        this.padre = padre;
        this.madre = madre;
        this.hijos = hijos;
        this.mote = mote;
        this.esposa = esposa;
        this.titulonobilario = titulonobilario;
        this.notes = notes;
        this.colorojos = colorojos;
        this.colorcabello = colorcabello;
        this.fate = fate;
    }

    /**
     * Agrega un hijo a la lista de hijos del miembro.
     * 
     * @param hijo el nombre del hijo a agregar.
     * @author Anthony Caldera
     */
    public void agregarHijo(String hijo) {
        hijos.agregar(hijo); 
    }

    /**
     * @return el nombre del miembro.
     * @author Anthony Caldera
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nombre a establecer.
     * @author Anthony Caldera
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return el nombre del padre.
     * @author Anthony Caldera
     */
    public String getPadre() {
        return padre;
    }

    /**
     * @param padre el nombre del padre a establecer.
     * @author Anthony Caldera
     */
    public void setPadre(String padre) {
        this.padre = padre;
    }

    /**
     * @return el nombre de la madre.
     * @author Anthony Caldera
     */
    public String getMadre() {
        return madre;
    }

    /**
     * @param madre el nombre de la madre a establecer.
     * @author Anthony Caldera
     */
    public void setMadre(String madre) {
        this.madre = madre;
    }

    /**
     * @return la lista de hijos del miembro.
     * @author Anthony Caldera
     */
    public ListaSimple getHijos() {
        return hijos;
    }

    /**
     * @param hijos la lista de hijos a establecer.
     * @author Anthony Caldera
     */
    public void setHijos(ListaSimple hijos) {
        this.hijos = hijos;
    }

    /**
     * @return el mote del miembro.
     * @author Anthony Caldera
     */
    public String getMote() {
        return mote;
    }

    /**
     * @param mote el mote a establecer.
     * @author Anthony Caldera
     */
    public void setMote(String mote) {
        this.mote = mote;
    }

    /**
     * @return el nombre de la esposa.
     * @author Anthony Caldera
     */
    public String getEsposa() {
        return esposa;
    }

    /**
     * @param esposa el nombre de la esposa a establecer.
     * @author Anthony Caldera
     */
    public void setEsposa(String esposa) {
        this.esposa = esposa;
    }

    /**
     * @return el título nobiliario del miembro.
     * @author Anthony Caldera
     */
    public String getTitulonobilario() {
        return titulonobilario;
    }

    /**
     * @param titulonobilario el título nobiliario a establecer.
     * @author Anthony Caldera
     */
    public void setTitulonobilario(String titulonobilario) {
        this.titulonobilario = titulonobilario;
    }

    /**
     * @return las notas sobre el miembro.
     * @author Anthony Caldera
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes las notas a establecer.
     * @author Anthony Caldera
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return el color de ojos del miembro.
     * @author Anthony Caldera
     */
    public String getColorojos() {
        return colorojos;
    }

    /**
     * @param colorojos el color de ojos a establecer.
     * @author Anthony Caldera
     */
    public void setColorojos(String colorojos) {
        this.colorojos = colorojos;
    }

    /**
     * @return el color de cabello del miembro.
     * @author Anthony Caldera
     */
    public String getColorcabello() {
        return colorcabello;
    }

    /**
     * @param colorcabello el color de cabello a establecer.
     * @author Anthony Caldera
     */
    public void setColorcabello(String colorcabello) {
        this.colorcabello = colorcabello;
    }

    /**
     * @return el destino del miembro.
     * @author Anthony Caldera
     */
    public String getFate() {
        return fate;
    }

    /**
     * @param fate el destino a establecer.
     * @author Anthony Caldera
     */
    public void setFate(String fate) {
        this.fate = fate;
    }

    /**
     * @return el sobrenombre del miembro.
     * @author Anthony Caldera
     */
    public String getSobrenombre() {
        return sobrenombre;
    }

    /**
     * @param sobrenombre el sobrenombre a establecer.
     * @author Anthony Caldera
     */
    public void setSobrenombre(String sobrenombre) {
        this.sobrenombre = sobrenombre;
    }
}
