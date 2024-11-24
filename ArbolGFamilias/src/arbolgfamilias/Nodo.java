


package arbolgfamilias;




public class Nodo {
    
    public MiembroFamilia miembro;
    public Nodo hijo; 
    public Nodo hermano; 
    
    public Nodo(MiembroFamilia miembro) {
        this.miembro = miembro;
        this.hijo = null;
        this.hermano = null;
    }
    
    
    public void addsonsito(Nodo newson){
        if(getHijo() != null){
            Nodo temp = getHijo();
            Nodo sib = temp;
            while (temp != null){
            sib = temp;
            temp = temp.getHermano();
            
        }
            sib.setHermano(newson);
        }else{
            setHijo(newson);
        }
    }

    public MiembroFamilia getMiembro() {
        return miembro;
    }

    public Nodo getHijo() {
        return hijo;
    }

    public Nodo getHermano() {
        return hermano;
    }

    public void setHijo(Nodo hijo) {
        this.hijo = hijo;
    }

    public void setHermano(Nodo hermano) {
        this.hermano = hermano;
    }
    
    
    
    
}