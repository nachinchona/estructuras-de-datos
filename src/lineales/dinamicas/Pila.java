package lineales.dinamicas;

/*
@author Ignacio Navarro FAI-3240
*/

public class Pila {
    private Nodo tope;
    
    public Pila(){
        this.tope = null;
    }
    
    public boolean apilar(Object elemento){
        Nodo nuevo = new Nodo(elemento, tope);
        this.tope = nuevo;
        return true;
    }
    
    public boolean desapilar(){
        boolean exito;
        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            exito = true;
        }else{
            exito = false;
        }
        return exito;
    }
    
    public Object obtenerTope(){
        Object elem;
        if (this.tope != null) {
            elem = this.tope.getElemento();
        }else{
            //si el nodo es null, no podria utilizar el metodo getElemento, por lo que elemento en tope es null
            elem = null;
        }
        return elem;
    }
    
    public boolean esVacia(){
        return this.tope == null;
    }
    
    public void vaciar(){
        while(this.tope != null){
            this.desapilar();
        }
    }
    
    public Pila clone(){
        Pila clon = new Pila();
        clonePR (clon, this.tope);               
        return clon;
    }
    
    public String toString() {
        String toString;
        if(!this.esVacia()){
            toString = "[ " + toStringPR(this.tope) + " ]";
        }else{
            toString = "[ PILA VACIA ]";
        }
        return toString;
    }
    
    private void clonePR (Pila clon, Nodo enlace){      
        if(enlace != null){         
            clonePR (clon, enlace.getEnlace());
            //desapila cuando en el enlace sea null (llega al ultimo nodo) y establece los nodos de la pila clon
            clon.tope = new Nodo(enlace.getElemento(), clon.tope);
        }
    }
    
    private String toStringPR (Nodo nodo){
        String toString = "";
        if(nodo != null){
            Nodo enlace = nodo.getEnlace();
            
            if (enlace != null) {
                //concatena cada elemento de los nodos en un string en orden cuando desapila
                toString = toStringPR(enlace)+" / "+ nodo.getElemento().toString();
            }else{
                toString = toString + nodo.getElemento().toString();
            }
        }
        return toString;
    }
    
}
