package jerarquicas.dinamicas;

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

    public Object padre(Object elem) {
        NodoArbol nPadre = this.raiz;

        return null;
    }

    public String toString() {
        String toString = imprimir(this.raiz);
        return toString;
    }

    private String imprimir(NodoArbol nodo) {
        String toString;
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
            toString = toString + ", H.D: - \n";
        }
        
        if (hijoIzq != null) {
            toString = toString + imprimir(hijoIzq);

        }
        
        if (hijoDer != null) {
            toString = toString + imprimir(hijoDer);
        }
        return toString;
    }

    private Object obtenerPadre(NodoArbol n, Object buscado) {
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
