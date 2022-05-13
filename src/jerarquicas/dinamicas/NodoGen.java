package jerarquicas.dinamicas;

public class NodoGen {

    private NodoGen hijoIzq;
    private NodoGen hermanoDer;
    private Object elem;

    public NodoGen(Object elem, NodoGen izq, NodoGen hermanoDer) {
        this.hijoIzq = izq;
        this.elem = elem;
        this.hermanoDer = hermanoDer;
    }

    public Object getElem() {
        return this.elem;
    }

    public NodoGen getHijoIzquierdo() {
        return this.hijoIzq;
    }

    public NodoGen getHermanoDerecho() {
        return this.hermanoDer;
    }
    
    public void setElem (Object unElem){
        this.elem = unElem;
    }
    
    public void setHijoIzquierdo(NodoGen hijoIzq){
        this.hijoIzq = hijoIzq;
    }
    
    public void setHermanoDerecho(NodoGen hermano){
        this.hermanoDer = hermano;
    }
}
