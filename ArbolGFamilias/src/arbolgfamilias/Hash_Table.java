/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 *
 * @author dugla
 */
public class Hash_Table {
    
    private static final int CAPACIDAD_INICIAL = 37; // Un número primo
    private static final double FACTOR_CARGA_MAXIMO = 0.7;
    private Nodo_hash[] tabla;
    private int numElementos;

    public Hash_Table() {
        this.tabla = new Nodo_hash[CAPACIDAD_INICIAL];
        this.numElementos = 0;
    }

    public MiembroFamilia buscar(String key) {
        int posicion = hashing(key);
        while (this.tabla[posicion] != null) {
            if (this.tabla[posicion].getName().equals(key)) {
                System.out.println("encontrado");
                return this.tabla[posicion].getMiembro();
            }
            posicion = (posicion + 1) % this.tabla.length; // Direccionamiento abierto
        }
        return null;
    }

    public void insertar(String key, MiembroFamilia miembro) {
        if (factorDeCarga() > FACTOR_CARGA_MAXIMO) {
            redimensionar();
        }

        int posicion = hashing(key);
        while (this.tabla[posicion] != null) {
            if (this.tabla[posicion].getName().equals(key)) {
                this.tabla[posicion].setMiembro(miembro); // Actualizar si ya existe
                return;
            }
            posicion = (posicion + 1) % this.tabla.length;
        }
        this.tabla[posicion] = new Nodo_hash(key, miembro);
        numElementos++;
    }

    private int hashing(String llave) {
        int hash = 0;
        int primo = 31;
        for (int i = 0; i < llave.length(); i++) {
            hash = primo * hash + llave.charAt(i);
        }
        return Math.abs(hash) % this.tabla.length;
    }

    private double factorDeCarga() {
        return (double) this.numElementos / this.tabla.length;
    }

    private void redimensionar() {
        Nodo_hash[] tablaAntigua = this.tabla;
        this.tabla = new Nodo_hash[siguientePrimo(this.tabla.length * 2)];
        this.numElementos = 0;

        for (Nodo_hash nodo : tablaAntigua) {
            if (nodo != null) {
                insertar(nodo.getName(), nodo.getMiembro());
            }
        }
    }

    private int siguientePrimo(int numero) {
        while (!esPrimo(numero)) {
            numero++;
        }
        return numero;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        if (numero <= 3) return true;
        if (numero % 2 == 0 || numero % 3 == 0) return false;
        for (int i = 5; i * i <= numero; i += 6) {
            if (numero % i == 0 || numero % (i + 2) == 0) return false;
        }
        return true;
    }
    
    public void imprimir() {
    for (int i = 0; i < this.tabla.length; i++) {
        Nodo_hash temp = this.tabla[i];
        while (temp != null) {
            System.out.println("Key: " + temp.getMiembro().getNombre() + " " + temp.getMiembro().getSobrenombre() + ", Value: " + temp.getMiembro());
            temp = temp.getNext();
        }
    }
}
    
}
