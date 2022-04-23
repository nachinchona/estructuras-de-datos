package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;

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
    
    public int altura(){
        return maxDepth(this.raiz);
    }
    
    private int maxDepth(NodoArbol nodo){
        if (nodo == null)
            return -1;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(nodo.getIzquierdo());
            int rDepth = maxDepth(nodo.getDerecho());
  
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
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
    

    private int alturaPR(NodoArbol nodo){
        int altura = 0;
        
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
}
