package conjuntistas.dinamicas;

public class NodoAVL {
    private Comparable elem;
    private int altura = 0;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    
    public NodoAVL(Comparable elem, NodoAVL izquierdo, NodoAVL derecho){
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        recalcularAltura();
    }
    
    public Comparable getElem(){
        return this.elem;
    }
    
    public void setElem(Comparable elem){
        this.elem = elem;
    }
    
    public int getAltura(){
        return this.altura;
    }
    
    public void recalcularAltura(){
        int altDerecho, altIzquierdo;
        if (this.derecho == null) {
            altDerecho = -1;
        }else{
            altDerecho = this.derecho.getAltura();
        }
        if (this.izquierdo == null) {
            altIzquierdo = -1;
        }else{
            altIzquierdo = this.izquierdo.getAltura();
        }
        this.altura = max(altDerecho, altIzquierdo) + 1;
    }
    
    public void setDerecho(NodoAVL nodo){
        this.derecho = nodo;
        recalcularAltura();
    }
    
    public void setIzquierdo(NodoAVL nodo){
        this.izquierdo = nodo;
        recalcularAltura();
    }
    
    public NodoAVL getDerecho(){
        return this.derecho;
    }
    
    public NodoAVL getIzquierdo(){
        return this.izquierdo;
    }
    
    private int max(int a, int b){
        int retorno;
        if (a > b) {
            retorno = a;
        }else{
            retorno = b;
        }
        return retorno;
    }
}
