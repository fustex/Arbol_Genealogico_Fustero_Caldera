/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 * Clase que almacena datos globales utilizados en la aplicación.
 * Contiene instancias de {@link Tree} y {@link Hash_Table} que pueden ser accedidas globalmente.
 * 
 * @author Francisco Fustero
 */
public class GlobalData {
    
    private static Tree tree; 
    private static Hash_Table tablita; 

    /**
     * Obtiene la instancia del árbol.
     * 
     * @return la instancia de Tree.
     * @author Francisco Fustero
     */
    public static Tree getTree() {
        return tree;
    }

    /**
     * Establece la instancia del árbol.
     * 
     * @param tree la nueva instancia de Tree a establecer.
     * @author Francisco Fustero
     */
    public static void setTree(Tree tree) {
        GlobalData.tree = tree;
    }

    /**
     * Obtiene la instancia de la tabla hash.
     * 
     * @return la instancia de Hash_Table.
     * @author Francisco Fustero
     */
    public static Hash_Table getHashTable() {
        return tablita;
    }

    /**
     * Establece la instancia de la tabla hash.
     * 
     * @param tablita la nueva instancia de Hash_Table a establecer.
     * @author Francisco Fustero
     */
    public static void sethashTable(Hash_Table tablita) {
        GlobalData.tablita = tablita;
    }
}
