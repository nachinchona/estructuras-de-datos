package jerarquicas.dinamicas;

import lineales.dinamicas.*;

public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean pertenece(Object buscado) {
        boolean pertenece = false;
        NodoGen nodo = obtenerNodo(this.raiz, buscado);
        if (nodo != null) {
            pertenece = true;
        }
        return pertenece;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenPR(this.raiz, salida);
        return salida;
    }

    private void listarInordenPR(NodoGen nodo, Lista ls) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null) {
                listarInordenPR(nodo.getHijoIzquierdo(), ls);
            }
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen aux = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (aux != null) {
                    listarInordenPR(aux, ls);
                    aux = aux.getHermanoDerecho();
                }
            }

        }
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreordenPR(this.raiz, salida);
        return salida;
    }

    private void listarPreordenPR(NodoGen nodo, Lista ls) {
        if (nodo != null) {
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null) {
                    listarPreordenPR(aux, ls);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
    }

    public String toString() {
        return toStringPR(this.raiz);
    }

    private String toStringPR(NodoGen nodo) {
        String s = "";
        if (nodo != null) {
            s = s + nodo.getElem().toString() + "->";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                s = s + hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                s = s + "\n" + toStringPR(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public boolean insertar(Object nuevo, Object padre) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoGen(nuevo, null, null);
        } else {
            NodoGen nodoPadre = obtenerNodo(this.raiz, padre);
            if (nodoPadre != null) {
                if (nodoPadre.getHijoIzquierdo() == null) {
                    nodoPadre.setHijoIzquierdo(new NodoGen(nuevo, null, null));
                } else {
                    NodoGen aux = nodoPadre.getHijoIzquierdo();
                    while (aux.getHermanoDerecho() != null) {
                        //lo penso el tomi
                        aux = aux.getHermanoDerecho();
                    }
                    aux.setHermanoDerecho(new NodoGen(nuevo, null, null));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object buscado) {
        NodoGen resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                resultado = nodo;
            } else {
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen aux = nodo.getHijoIzquierdo();
                    while (aux != null && resultado == null) {
                        if (aux.getElem().equals(buscado)) {
                            resultado = aux;
                        } else {
                            if (aux.getHijoIzquierdo() != null) {
                                resultado = obtenerNodo(aux, buscado);
                            }
                        }
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return resultado;
    }

    public Object padre(Object buscado) {
        NodoGen nodo = padrePR(this.raiz, buscado);
        Object padre = null;
        if (nodo != null) {
            padre = nodo.getElem();
        }
        return padre;
    }

    private NodoGen padrePR(NodoGen nodo, Object buscado) {
        NodoGen padre = null;
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null && padre == null) {
                    if (aux.getElem().equals(buscado)) {
                        padre = nodo;
                    }
                    aux = aux.getHermanoDerecho();
                }
                if (padre == null && nodo.getHijoIzquierdo().getHijoIzquierdo() != null) {
                    padre = padrePR(nodo.getHijoIzquierdo().getHijoIzquierdo(), buscado);
                }
            }
        }
        return padre;
    }

}
