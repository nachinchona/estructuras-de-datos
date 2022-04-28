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

    public boolean equals(ArbolBin otro) {
        boolean equals = equalsPR(this.raiz, otro.raiz);
        return equals;
    }

    private boolean equalsPR(NodoArbol nodo1, NodoArbol nodo2) {
        boolean coincide = false;
        if (nodo1 != null && nodo2 != null) {
            if (nodo1.getElem().equals(nodo2.getElem())) {
                coincide = true;
            }
            if (coincide) {
                if (nodo1.getIzquierdo() != null) {
                    if (nodo2.getIzquierdo() != null) {
                        coincide = equalsPR(nodo1.getIzquierdo(), nodo2.getIzquierdo());
                    } else {
                        coincide = false;
                    }
                } else {
                    if (nodo2.getIzquierdo() != null) {
                        coincide = false;
                    }
                }
                if (coincide) {
                    if (nodo1.getDerecho() != null) {
                        if (nodo2.getDerecho() != null) {
                            coincide = equalsPR(nodo1.getDerecho(), nodo2.getDerecho());
                        } else {
                            coincide = false;
                        }
                    } else {
                        if (nodo2.getDerecho() != null) {
                            coincide = false;
                        }
                    }
                }
            }
        } else {
            if (nodo1 == null && nodo2 == null) {
                coincide = true;
            }
        }
        return coincide;
    }

    public ArbolBin clonarInvertido() {
        ArbolBin invertido = new ArbolBin();
        if (this.raiz != null) {
            invertido.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            clonarInvPR(this.raiz, invertido.raiz);
        }
        return invertido;
    }

    private void clonarInvPR(NodoArbol nodo, NodoArbol nodoInv) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                nodoInv.setDerecho(new NodoArbol(nodo.getIzquierdo().getElem(), null, null));
                clonarInvPR(nodo.getIzquierdo(), nodoInv.getDerecho());
            }
            if (nodo.getDerecho() != null) {
                nodoInv.setIzquierdo(new NodoArbol(nodo.getDerecho().getElem(), null, null));
                clonarInvPR(nodo.getDerecho(), nodoInv.getIzquierdo());
            }
        }
    }

    public Lista frontera() {
        Lista hojas = new Lista();
        if (this.raiz != null) {
            fronteraPR(hojas, this.raiz);
        }
        return hojas;
    }

    private void fronteraPR(Lista hojas, NodoArbol nodo) {
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                hojas.insertar(nodo.getElem(), hojas.longitud() + 1);
            } else {
                fronteraPR(hojas, nodo.getIzquierdo());
                fronteraPR(hojas, nodo.getDerecho());
            }
        }
    }

    public boolean verificarPatron(Lista patron) {
        /*Implementar la operación boolean verificarPatron(Lista patron), que recibe por parámetro una lista patron
        y determine si coincide exactamente con al menos un camino del árbol que comience en la raíz y termine en
        una hoja. El método debe ser eficiente, es decir, recorrer el árbol lo estrictamente necesario. */
        boolean coincide = false;
        if (this.raiz != null) {
            coincide = verificarPatronPR(patron, this.raiz, 1);
        }
        return coincide;
    }

    private boolean verificarPatronPR(Lista patron, NodoArbol nodo, int pos) {
        boolean coincide = false;
        int longitud = patron.longitud();
        Object elem = patron.recuperar(pos);
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                coincide = true;
            }
            if (coincide && pos < longitud) {
                coincide = verificarPatronPR(patron, nodo.getIzquierdo(), pos + 1);
                if (!coincide) {
                    coincide = verificarPatronPR(patron, nodo.getDerecho(), pos + 1);
                }
            }
        }
        return coincide;
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

    public Lista listarAncestros(Object elem) {
        Lista l1 = new Lista();
        listarAncestros(this.raiz, l1, elem);
        return l1;
    }

    private void listarAncestros(NodoArbol n, Lista l1, Object elem) {
        if (n != null) {
            if (n.getElem().equals(elem)) {
                l1.insertar(n.getElem(), l1.longitud() + 1);
            } else {
                listarAncestros(n.getIzquierdo(), l1, elem);
                if (l1.esVacia()) {
                    listarAncestros(n.getDerecho(), l1, elem);
                }
                if (!l1.esVacia()) {
                    l1.insertar(n.getElem(), l1.longitud() + 1);
                }
            }
        }
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
            } else {
                //si el elemento coincide, devuelve el nivel en el cual el algoritmo esta trabajando
                nivelRetorno = nivel;
            }
        }
        return nivelRetorno;
    }

    public Lista listarPorNiveles() {
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
        Object elem = null;
        if (this.raiz != null && !this.raiz.getElem().equals(buscado)) {
            NodoArbol nodoPadre = obtenerNodoPadre(this.raiz, buscado);
            if (nodoPadre != null) {
                elem = nodoPadre.getElem();
            }
        }
        return elem;
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

    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            clon.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            clonePR(this.raiz, clon.raiz);
        }
        return clon;
    }

    private void clonePR(NodoArbol nodoAClonar, NodoArbol nodoClon) {
        NodoArbol hijoIzq = nodoAClonar.getIzquierdo();
        NodoArbol hijoDer = nodoAClonar.getDerecho();
        //primero tomamos el hijo izq si no es null
        if (nodoAClonar.getIzquierdo() != null) {
            NodoArbol hijoIzqClon = new NodoArbol(hijoIzq.getElem(), null, null);
            nodoClon.setIzquierdo(hijoIzqClon);
            clonePR(hijoIzq, hijoIzqClon);
        }
        //luego el hijo derecho
        if (nodoAClonar.getDerecho() != null) {
            NodoArbol hijoDerClon = new NodoArbol(hijoDer.getElem(), null, null);
            nodoClon.setDerecho(hijoDerClon);
            clonePR(hijoDer, hijoDerClon);
        }
    }
}
