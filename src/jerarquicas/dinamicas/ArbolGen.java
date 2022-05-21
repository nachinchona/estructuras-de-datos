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
        //si el nodo retornado es distinto de null, el objeto se encuentra en el arbol
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
            //primero visito los hijos izquierdos
            if (nodo.getHijoIzquierdo() != null) {
                listarInordenPR(nodo.getHijoIzquierdo(), ls);
            }
            //luego al padre
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
            //luego a los hermanos
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen aux = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (aux != null) {
                    listarInordenPR(aux, ls);
                    aux = aux.getHermanoDerecho();
                }
            }

        }
    }

    public Lista listarPosorden() {
        Lista ls = new Lista();
        if (this.raiz != null) {
            listarPosordenPR(this.raiz, ls);
        }
        return ls;
    }

    private void listarPosordenPR(NodoGen nodo, Lista ls) {
        if (nodo != null) {
            NodoGen aux = nodo.getHijoIzquierdo();
            //primero se visitan los hijos
            while (aux != null) {
                listarPosordenPR(aux, ls);
                aux = aux.getHermanoDerecho();
            }
            //por ultimo la raiz
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
        }
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreordenPR(this.raiz, salida);
        return salida;
    }

    private void listarPreordenPR(NodoGen nodo, Lista ls) {
        if (nodo != null) {
            //primero se visita la raiz
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
            //luego los hijos
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null) {
                    listarPreordenPR(aux, ls);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPorNiveles() {
        Lista ls = new Lista();
        Cola cola = new Cola();
        cola.poner(this.raiz);
        while (!cola.esVacia()) {
            //inserto el elemento del frente de la cola
            NodoGen aux = (NodoGen) cola.obtenerFrente();
            ls.insertar(aux.getElem(), ls.longitud() + 1);
            cola.sacar();
            aux = aux.getHijoIzquierdo();
            while (aux != null) {
                //agrego en la cola todos los hijos tenga el nodo
                cola.poner(aux);
                aux = aux.getHermanoDerecho();
            }

        }
        return ls;
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
            //si el arbol es vacio, se crea su raiz
            this.raiz = new NodoGen(nuevo, null, null);
        } else {
            NodoGen nodoPadre = obtenerNodo(this.raiz, padre);
            if (nodoPadre != null) {
                //dos casos, si no tiene hijo izquierdo se crea, sino se le agrega un hermano
                if (nodoPadre.getHijoIzquierdo() == null) {
                    nodoPadre.setHijoIzquierdo(new NodoGen(nuevo, null, null));
                } else {
                    NodoGen aux = nodoPadre.getHijoIzquierdo();
                    while (aux.getHermanoDerecho() != null) {
                        aux = aux.getHermanoDerecho();
                    }
                    aux.setHermanoDerecho(new NodoGen(nuevo, null, null));
                }
            } else {
                //no encontro al nodo padre
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
        Object padre = null;
        if (this.raiz != null && !this.raiz.getElem().equals(buscado)) {
            NodoGen nodo = padrePR(this.raiz, buscado);
            if (nodo != null) {
                padre = nodo.getElem();
            }
        }
        return padre;
    }

    private NodoGen padrePR(NodoGen nodo, Object buscado) {
        NodoGen padre = null;
        if (nodo != null) {
            NodoGen aux = nodo.getHijoIzquierdo();
            while (aux != null && padre == null) {
                if (aux.getElem().equals(buscado)) {
                    padre = nodo;
                } else {
                    if (aux.getHijoIzquierdo() != null) {
                        padre = padrePR(aux, buscado);
                    }
                }
                aux = aux.getHermanoDerecho();
            }
        }
        return padre;
    }

    public Lista ancestros(Object buscado) {
        Lista ls = new Lista();
        if (this.raiz != null) {
            ancestrosPR(this.raiz, ls, buscado);
        }
        return ls;
    }

    public int altura() {
        int altura = -1;
        if (this.raiz != null) {
            altura = alturaPR(this.raiz);
        }
        return altura;
    }

    private int alturaPR(NodoGen nodo) {
        int altura = 0;
        int alturaMasGrande = 0;
        if (nodo.getHijoIzquierdo() != null) {
            NodoGen aux = nodo.getHijoIzquierdo();
            while (aux != null) {
                altura = alturaPR(aux);
                if (altura > alturaMasGrande) {
                    alturaMasGrande = altura;
                }
                aux = aux.getHermanoDerecho();
            }
            altura = alturaMasGrande + 1;
        }
        return altura;
    }

    private boolean ancestrosPR(NodoGen nodo, Lista ls, Object buscado) {
        //metodo recursivo que retorna verdadero o falso dependiendo de si encuentra el elemento buscado
        boolean encontro = false;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                ls.insertar(nodo.getElem(), ls.longitud() + 1);
                encontro = true;
            } else {
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null && !encontro) {
                    encontro = encontro || ancestrosPR(aux, ls, buscado);
                    aux = aux.getHermanoDerecho();
                }
                if (encontro) {
                    //si se encontro el elemento, se insertan en la lista los ancestros
                    ls.insertar(nodo.getElem(), ls.longitud() + 1);
                }
            }
        }
        return encontro;
    }

    public int nivel(Object buscado) {
        int nivel = -1;
        if (this.raiz != null) {
            nivel = nivelPR(this.raiz, buscado, 0);
        }
        return nivel;
    }

    private int nivelPR(NodoGen nodo, Object buscado, int nivelActual) {
        int nivel = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                nivel = nivelActual;
            } else {
                NodoGen aux = nodo.getHijoIzquierdo();
                boolean encontro = false;
                while (aux != null && !encontro) {
                    nivel = nivelPR(aux, buscado, nivelActual + 1);
                    encontro = nivel != -1;
                    if (!encontro) {
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return nivel;
    }

    public Lista frontera() {
        Lista lista = new Lista();
        frontera(this.raiz, lista);
        return lista;
    }

    private void frontera(NodoGen nodo, Lista lista) {
        while (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                //es hoja, se aniade a la lista
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            } else {
                frontera(nodo.getHijoIzquierdo(), lista);
            }
            nodo = nodo.getHermanoDerecho();
        }
    }

    @Override
    public ArbolGen clone() {
        ArbolGen arbolClon = new ArbolGen();
        if (raiz != null) {
            arbolClon.raiz = new NodoGen(null, null, null);
            clone(this.raiz, arbolClon.raiz);
        }
        return arbolClon;
    }

    private void clone(NodoGen nodo, NodoGen nodoClon) {
        while (nodo != null) {
            nodoClon.setElem(nodo.getElem());
            if (nodo.getHijoIzquierdo() != null) {
                nodoClon.setHijoIzquierdo(new NodoGen(null, null, null));
                clone(nodo.getHijoIzquierdo(), nodoClon.getHijoIzquierdo());
            }
            if (nodo.getHermanoDerecho() != null) {
                nodoClon.setHermanoDerecho(new NodoGen(nodo.getElem(), null, null));
            }

            nodoClon = nodoClon.getHermanoDerecho();
            nodo = nodo.getHermanoDerecho();
        }
    }

    public boolean sonFrontera(Lista unaLista) {
        Lista clon = unaLista.clone();
        return sonFronteraPR(this.raiz, clon);
    }

    private boolean sonFronteraPR(NodoGen nodo, Lista unaLista) {
        //precondicion: los elementos de la lista no se repiten
        boolean sonFrontera = true;
        while (!unaLista.esVacia() && sonFrontera && nodo != null) {
            if (nodo.getHijoIzquierdo() == null) {
                int pos = unaLista.localizar(nodo.getElem());
                if (pos != -1) {
                    unaLista.eliminar(pos);
                }
            } else {
                sonFronteraPR(nodo.getHijoIzquierdo(), unaLista);
            }
            nodo = nodo.getHermanoDerecho();
        }
        if (!unaLista.esVacia()) {
            //si la lista no se consumio, no es frontera
            sonFrontera = false;
        }
        return sonFrontera;
    }
}
