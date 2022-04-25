package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 *
 * @author ignacio.navarro
 */
public class ArbolBin {
    
    private NodoArbol raiz;
    
    public ArbolBin() {
        this.raiz = null;
    }
    
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            
            if (nPadre != null) {
                if (lugar == 'I' && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (lugar == 'D' && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }
    
    public boolean esVacio() {
        return this.raiz == null;
    }
    
    public String toString() {
        String toString = toStringPR(this.raiz);
        return toString;
    }
    
    private String toStringPR(NodoArbol nodo) {
        String toString = "Arbol vacio";
        if (nodo != null) {
            toString = nodo.getElem().toString();
            NodoArbol hijoIzq = nodo.getIzquierdo();
            NodoArbol hijoDer = nodo.getDerecho();
            if (hijoIzq != null) {
                toString = toString + ", H.I: " + hijoIzq.getElem().toString();
                
            } else {
                toString = toString + ", H.I: -";
            }
            if (hijoDer != null) {
                toString = toString + ", H.D: " + hijoDer.getElem().toString() + "\n";
            } else {
                toString = toString + ", H.D: -\n";
            }
            
            if (hijoIzq != null) {
                toString = toString + toStringPR(hijoIzq);
            }
            
            if (hijoDer != null) {
                toString = toString + toStringPR(hijoDer);
            }
        }
        return toString;
    }
    
    public int nivel(Object buscado) {
        int nivel;
        nivel = nivelPR(this.raiz, buscado, 0);
        
        return nivel;
    }
    
    private int nivelPR(NodoArbol nodo, Object buscado, int nivel) {
        //nivel empieza valiendo 0
        int nivelRetorno = -1;
        if (nodo != null) {
            if (!nodo.getElem().equals(buscado)) {
                nivelRetorno = nivelPR(nodo.getIzquierdo(), buscado, nivel + 1);
                if (nivelRetorno == -1) {
                    nivelRetorno = nivelPR(nodo.getDerecho(), buscado, nivel + 1);
                }
            }else{
                //si el elemento coincide, devuelve el nivel en el cual el algoritmo esta trabajando
                nivelRetorno = nivel;
            }
        }
        return nivelRetorno;
    }
    
    public Lista listarPorNivel() {
        Lista lista = new Lista();
        
        if (this.raiz != null) {
            Cola cola = new Cola();
            cola.poner(this.raiz);
            while (!cola.esVacia()) {
                NodoArbol aux = (NodoArbol) cola.obtenerFrente();
                lista.insertar(aux.getElem(), lista.longitud() + 1);
                cola.sacar();
                if (aux.getIzquierdo() != null) {
                    cola.poner(aux.getIzquierdo());
                }
                if (aux.getDerecho() != null) {
                    cola.poner(aux.getDerecho());
                }
            }
        }
        return lista;
    }
    
    public Lista listarPreorden() {
        Lista lista = new Lista();
        listarPreordenPR(this.raiz, lista);
        return lista;
    }
    
    public Lista listarInorden() {
        Lista lista = new Lista();
        listarInordenPR(this.raiz, lista);
        return lista;
    }
    
    public Lista listarPosorden() {
        Lista lista = new Lista();
        listarPosorden(this.raiz, lista);
        return lista;
    }
    
    public int altura() {
        return alturaPR(this.raiz);
    }
    
    private int alturaPR(NodoArbol nodo) {
        int altura = -1;
        if (nodo != null) {
            int alturaSubArbolIzq = alturaPR(nodo.getIzquierdo());
            int alturaSubArbolDer = alturaPR(nodo.getDerecho());
            if (alturaSubArbolIzq > alturaSubArbolDer) {
                altura = alturaSubArbolIzq + 1;
            } else {
                altura = alturaSubArbolDer + 1;
            }
        }
        
        return altura;
    }
    
    private void listarPosorden(NodoArbol nodo, Lista temp) {
        if (nodo != null) {
            listarPosorden(nodo.getIzquierdo(), temp);
            listarPosorden(nodo.getDerecho(), temp);
            temp.insertar(nodo.getElem(), temp.longitud() + 1);
        }
    }
    
    private void listarInordenPR(NodoArbol nodo, Lista temp) {
        if (nodo != null) {
            listarInordenPR(nodo.getIzquierdo(), temp);
            temp.insertar(nodo.getElem(), temp.longitud() + 1);
            listarInordenPR(nodo.getDerecho(), temp);
        }
    }
    
    private void listarPreordenPR(NodoArbol nodo, Lista temp) {
        if (nodo != null) {
            temp.insertar(nodo.getElem(), temp.longitud() + 1);
            listarPreordenPR(nodo.getIzquierdo(), temp);
            listarPreordenPR(nodo.getDerecho(), temp);
        }
    }
    
    public Object padre(Object buscado) {
        return obtenerNodoPadre(this.raiz, buscado).getElem();
    }
    
    private NodoArbol obtenerNodoPadre(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        if (n != null) {
            NodoArbol izq = n.getIzquierdo();
            NodoArbol der = n.getDerecho();
            if (izq != null) {
                if (izq.getElem().equals(buscado)) {
                    resultado = n;
                } else {
                    if (der != null) {
                        if (der.getElem().equals(buscado)) {
                            resultado = n;
                        }
                    }
                }
            }
            
            if (resultado == null) {
                resultado = obtenerNodoPadre(izq, buscado);
                if (resultado == null) {
                    resultado = obtenerNodoPadre(der, buscado);
                }
            }
            
        }
        return resultado;
    }
    
    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }
    
    public void vaciar() {
        this.raiz = null;
    }
    
    public ArbolBin clone(){
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            clon.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            clonePR(this.raiz, clon.raiz);
        }
        return clon;
    }
    
    private void clonePR(NodoArbol nodoAClonar, NodoArbol nodoClon){
        NodoArbol hijoIzq = nodoAClonar.getIzquierdo();
        NodoArbol hijoDer = nodoAClonar.getDerecho();
        //primero tomamos el hijo izq si no es null
        if (nodoAClonar.getIzquierdo() != null) {
            NodoArbol hijoIzqClon = new NodoArbol(hijoIzq.getElem(), null, null);
            nodoClon.setIzquierdo(hijoIzq);
            clonePR(hijoIzq, hijoIzqClon);
        }
        //luego el hijo derecho
        if (nodoAClonar.getDerecho() != null) {
            NodoArbol hijoDerClon = new NodoArbol(hijoDer.getElem(), null, null);
            nodoClon.setDerecho(hijoDer);
            clonePR(hijoDer, hijoDerClon);
        }         
    }
}
