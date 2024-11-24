/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolgfamilias;


import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author dugla
 */
public class FamilyTreeBuilder {
    
   
    
   public Tree buildTree(String jsonString, Hash_Table tabla_hash) {
    JSONObject json = new JSONObject(jsonString);
    String houseName = json.keys().next();
    JSONArray houseMembers = json.getJSONArray(houseName);
    Tree familyTree = new Tree();
    
    

    
    for (int i = 0; i < houseMembers.length(); i++) {
        JSONObject member = houseMembers.getJSONObject(i);
        for (String memberName : member.keySet()) {
            JSONArray attributes = member.getJSONArray(memberName);
            MiembroFamilia newMember = createMember(memberName, attributes);
            
            if (familyTree.getRaiz() == null) {
                familyTree.CrearRaiz(newMember);
                tabla_hash.insertar(newMember.getNombre() + " " + newMember.getSobrenombre(), newMember);
            }
            
            
        }
    }

    
    

    
    for (int i = 0; i < houseMembers.length(); i++) {
        JSONObject member = houseMembers.getJSONObject(i);
        for (String memberName : member.keySet()) {
            
            if (familyTree.getRaiz().getMiembro().getNombre() != memberName){
            JSONArray attributes = member.getJSONArray(memberName);
            MiembroFamilia newMember = createMember(memberName, attributes);
            Nodo hijoNodo = new Nodo(newMember);
            // Llamar a ElModificador para establecer las relaciones
            ElModificador(familyTree.getRoot(), hijoNodo, familyTree, tabla_hash);
            tabla_hash.insertar(newMember.getNombre() + " " + newMember.getSobrenombre(), newMember);
        }
        }
    }
    
    tabla_hash.imprimir();

    return familyTree; 
}

    public void ElModificador(Nodo padre, Nodo hijo, Tree familyTree, Hash_Table tabla_hash) {
    String elPapa = hijo.getMiembro().getPadre();
    
    
    String nombreCompletoPadre = padre.getMiembro().getNombre() + ", " + padre.getMiembro().getSobrenombre() + " of his name";
    

    
    if (padre.getMiembro().getNombre().equals(elPapa) || padre.getMiembro().getMote().equals(elPapa) || nombreCompletoPadre.equals(elPapa)) {
        System.out.println(nombreCompletoPadre);
        familyTree.insert(hijo.getMiembro(), padre);
        
    } else {
       
        Nodo hijoActual = padre.getHijo();
        while (hijoActual != null) {
            ElModificador(hijoActual, hijo, familyTree, tabla_hash); 
            hijoActual = hijoActual.getHermano(); 
        }
    }
    
}


    private MiembroFamilia createMember(String memberName, JSONArray attributes) {
    String sobrenombre = "";
    String motes = "";
    String bornToFather = null;
    String bornToMother = "";
    String title = "";
    String wedTo = "";
    String eyesColor = "";
    String hairColor = "";
    ListaSimple children = new ListaSimple();
    String notes = "";
    String fate = "";

    for (int j = 0; j < attributes.length(); j++) {
        JSONObject attribute = attributes.getJSONObject(j);
        
        if (attribute.has("Of his name")) {
            sobrenombre = attribute.getString("Of his name");
        }
        
        if (attribute.has("Born to")) {
            String parents = attribute.getString("Born to");
            if (bornToFather == null) {
                bornToFather = parents; 
            } else {
                bornToMother = parents; 
            }
        }
        
        if (attribute.has("Known throughout as")){
            motes = attribute.getString("Known throughout as");
        }
        
        if (attribute.has("Held title")) {
            title = attribute.getString("Held title");
        }
        if (attribute.has("Wed to")) {
            wedTo = attribute.getString("Wed to");
        }
        if (attribute.has("Of eyes")) {
            eyesColor = attribute.getString("Of eyes");
        }
        if (attribute.has("Of hair")) {
            hairColor = attribute.getString("Of hair");
        }
        if (attribute.has("Father to")) {
            JSONArray fatherToArray = attribute.getJSONArray("Father to");
            for (int k = 0; k < fatherToArray.length(); k++) {
                children.agregar(fatherToArray.getString(k)); 
            }
        }
        if (attribute.has("Notes")) {
            notes = attribute.getString("Notes");
        }
        if (attribute.has("Fate")) {
            fate = attribute.getString("Fate");
        }
    }

    
    return new MiembroFamilia(memberName, sobrenombre, bornToFather, bornToMother, motes, title, wedTo, eyesColor, hairColor, children, notes, fate);
}

}


