package conjuntistas.dinamicas;
/**
 *
 * @author ignacio.navarro
 */
public class NodoArbol {
    private Comparable elem;
    private NodoArbol izq;
    private NodoArbol der;
    
    
    public NodoArbol (Comparable elem, NodoArbol izq, NodoArbol der){
        this.elem = elem;
        this.izq = izq;
        this.der = der;
    }
    
    public Comparable getElem (){
        return this.elem;
    }
    
    public NodoArbol getIzquierdo (){
        return this.izq;
    }
    
    public NodoArbol getDerecho () {
        return this.der;
    }
    
    public void setElem (Comparable elem){
        this.elem = elem;
    }
    
    public void setIzquierdo (NodoArbol izq){
        this.izq = izq;
    }
    
    public void setDerecho (NodoArbol der){
        this.der = der;
    }
}
