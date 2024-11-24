/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;

/**
 *
 * @author dugla
 */
public class GlobalData {
    
    
    private static Tree tree;
    private static Hash_Table tablita;

    public static Tree getTree() {
        return tree;
    }

    public static void setTree(Tree tree) {
        GlobalData.tree = tree;
    }
    
    
    
    public static Hash_Table getHashTable() {
        return tablita;
    }

    public static void sethashTable(Hash_Table tablita) {
        GlobalData.tablita = tablita;
    }
}
