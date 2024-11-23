/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

import javax.swing.JOptionPane;

/**
 *
 * @author ffust
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
    
    
    public void mostrarInformacionMiembro(MiembroFamilia miembro) {
        String hijos = miembro.getHijos().impresora();
        String info = "Nombre: " + miembro.getNombre() + "\n" +
                      "Sobrenombre: " + miembro.getSobrenombre() + "\n" +
                      "Padre: " + miembro.getPadre() + "\n" +
                      "Madre: " + miembro.getMadre() + "\n" +
                      "Mote: " + miembro.getMote() + "\n" +
                      "Esposa: " + miembro.getEsposa() + "\n" +
                      "Título Nobilario: " + miembro.getTitulonobilario() + "\n" +
                      "Notas: " + miembro.getNotes() + "\n" +
                      "Color de Ojos: " + miembro.getColorojos() + "\n" +
                      "Color de Cabello: " + miembro.getColorcabello() + "\n" +
                      "Destino: " + miembro.getFate() + "\n" +
                      "Hijos: " + hijos;

        JOptionPane.showMessageDialog(null, info, "Información del Miembro", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
    public void agregarHijo(String hijo) {
        hijos.agregar(hijo); 
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the padre
     */
    public String getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(String padre) {
        this.padre = padre;
    }

    /**
     * @return the madre
     */
    public String getMadre() {
        return madre;
    }

    /**
     * @param madre the madre to set
     */
    public void setMadre(String madre) {
        this.madre = madre;
    }

    /**
     * @return the hijos
     */
    public ListaSimple getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(ListaSimple hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the mote
     */
    public String getMote() {
        return mote;
    }

    /**
     * @param mote the mote to set
     */
    public void setMote(String mote) {
        this.mote = mote;
    }

    /**
     * @return the esposa
     */
    public String getEsposa() {
        return esposa;
    }

    /**
     * @param esposa the esposa to set
     */
    public void setEsposa(String esposa) {
        this.esposa = esposa;
    }

    /**
     * @return the titulonobilario
     */
    public String getTitulonobilario() {
        return titulonobilario;
    }

    /**
     * @param titulonobilario the titulonobilario to set
     */
    public void setTitulonobilario(String titulonobilario) {
        this.titulonobilario = titulonobilario;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the colorojos
     */
    public String getColorojos() {
        return colorojos;
    }

    /**
     * @param colorojos the colorojos to set
     */
    public void setColorojos(String colorojos) {
        this.colorojos = colorojos;
    }

    /**
     * @return the colorcabello
     */
    public String getColorcabello() {
        return colorcabello;
    }

    /**
     * @param colorcabello the colorcabello to set
     */
    public void setColorcabello(String colorcabello) {
        this.colorcabello = colorcabello;
    }

    /**
     * @return the fate
     */
    public String getFate() {
        return fate;
    }

    /**
     * @param fate the fate to set
     */
    public void setFate(String fate) {
        this.fate = fate;
    }

    /**
     * @return the sobrenombre
     */
    public String getSobrenombre() {
        return sobrenombre;
    }

    /**
     * @param sobrenombre the sobrenombre to set
     */
    public void setSobrenombre(String sobrenombre) {
        this.sobrenombre = sobrenombre;
    }
 
}
