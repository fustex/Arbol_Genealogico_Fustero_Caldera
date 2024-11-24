/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 *
 * @author dugla
 */
package arboles;

/**
 * Esta clase implementa una tabla hash simple para almacenar miembros de la familia.
 * Utiliza un esquema de direccionamiento abierto para manejar colisiones.
 * 
 * @author Anthony Caldera
 */
public class Hash_Table {
    
    private static final int CAPACIDAD_INICIAL = 37; 
    private static final double FACTOR_CARGA_MAXIMO = 0.7;
    private Nodo_hash[] tabla;
    private int numElementos;

    /**
     * Constructor que inicializa la tabla hash.
     * @author Anthony Caldera
     */
    public Hash_Table() {
        this.tabla = new Nodo_hash[CAPACIDAD_INICIAL];
        this.numElementos = 0;
    }
    
    /**
     * Busca un miembro de la familia por su clave.
     * 
     * @param key la clave del miembro a buscar.
     * @return el miembro encontrado o null si no existe.
     * @author Anthony Caldera
     */
    public MiembroFamilia buscar(String key) {
        int posicion = hashing(key);
        while (this.getTabla()[posicion] != null) {
            if (this.getTabla()[posicion].getName().equals(key)) {
                System.out.println("encontrado");
                return this.getTabla()[posicion].getMiembro();
            }
            posicion = (posicion + 1) % this.getTabla().length; 
        }
        return null;
    }

    /**
     * Inserta un nuevo miembro de la familia en la tabla.
     * 
     * @param key la clave del miembro.
     * @param miembro el objeto MiembroFamilia a insertar.
     * @author Anthony Caldera
     */
    public void insertar(String key, MiembroFamilia miembro) {
        if (factorDeCarga() > FACTOR_CARGA_MAXIMO) {
            redimensionar();
        }

        int posicion = hashing(key);
        while (this.getTabla()[posicion] != null) {
            if (this.getTabla()[posicion].getName().equals(key)) {
                this.getTabla()[posicion].setMiembro(miembro); 
                return;
            }
            posicion = (posicion + 1) % this.getTabla().length;
        }
        this.getTabla()[posicion] = new Nodo_hash(key, miembro);
        numElementos++;
    }

    /**
     * Calcula la posición hash para una clave dada.
     * 
     * @param llave la clave para la cual se calculará el hash.
     * @return la posición hash calculada.
     * @author Anthony Caldera
     */
    private int hashing(String llave) {
        int hash = 0;
        int primo = 31;
        for (int i = 0; i < llave.length(); i++) {
            hash = primo * hash + llave.charAt(i);
        }
        return Math.abs(hash) % this.getTabla().length;
    }

    /**
     * Calcula el factor de carga actual de la tabla.
     * 
     * @return el factor de carga como un valor decimal.
     * @author Anthony Caldera
     */
    private double factorDeCarga() {
        return (double) this.numElementos / this.getTabla().length;
    }

    /**
     * Redimensiona la tabla cuando el factor de carga supera el máximo permitido.
     * @author Anthony Caldera
     */
    private void redimensionar() {
        Nodo_hash[] tablaAntigua = this.getTabla();
        this.setTabla(new Nodo_hash[siguientePrimo(this.getTabla().length * 2)]);
        this.numElementos = 0;

        for (Nodo_hash nodo : tablaAntigua) {
            if (nodo != null) {
                insertar(nodo.getName(), nodo.getMiembro());
            }
        }
    }

    /**
     * Encuentra el siguiente número primo mayor o igual al número dado.
     * 
     * @param numero el número a evaluar.
     * @return el siguiente número primo.
     * @author Anthony Caldera
     */
    private int siguientePrimo(int numero) {
        while (!esPrimo(numero)) {
            numero++;
        }
        return numero;
    }

    /**
     * Verifica si un número es primo.
     * 
     * @param numero el número a verificar.
     * @return true si es primo, false en caso contrario.
     * @author Anthony Caldera
     */
    private boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        if (numero <= 3) return true;
        if (numero % 2 == 0 || numero % 3 == 0) return false;
        for (int i = 5; i * i <= numero; i += 6) {
            if (numero % i == 0 || numero % (i + 2) == 0) return false;
        }
        return true;
    }
    
    /**
     * Imprime todos los miembros de la tabla hash.
     * @author Anthony Caldera
     */
    public void imprimir() {
        for (int i = 0; i < this.getTabla().length; i++) {
            Nodo_hash temp = this.getTabla()[i];
            while (temp != null) {
                System.out.println("Key: " + temp.getMiembro().getNombre() + " " + temp.getMiembro().getSobrenombre() + ", Value: " + temp.getMiembro());
                temp = temp.getNext();
            }
        }
    }

    /**
     * @return la tabla hash.
     * @author Anthony Caldera
     */
    public Nodo_hash[] getTabla() {
        return tabla;
    }

    /**
     * @param tabla la nueva tabla a establecer.
     * @author Anthony Caldera
     */
    public void setTabla(Nodo_hash[] tabla) {
        this.tabla = tabla;
    }
}
