package conjuntistas.dinamicas;

import lineales.dinamicas.Lista;

public class ArbolBB {

    private NodoArbol raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public ArbolBB clonarParteInvertida(Comparable elem) {
        ArbolBB arbolito = new ArbolBB();
        NodoArbol nuevo;
        NodoArbol aux = obtenerNodo(this.raiz, elem);
        if (aux != null) {
            nuevo = new NodoArbol(aux.getElem(), null, null);
            arbolito.raiz = nuevo;
            clonarParteInvertidaPR(nuevo, aux);
        }
        return arbolito;
    }

    private NodoArbol obtenerNodo(NodoArbol nodo, Comparable elem) {
        NodoArbol retorno = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                retorno = nodo;
            } else {
                if (nodo.getElem().compareTo(elem) < 0) {
                    retorno = obtenerNodo(nodo.getDerecho(), elem);
                } else {
                    retorno = obtenerNodo(nodo.getIzquierdo(), elem);
                }
            }
        }
        return retorno;
    }

    private void clonarParteInvertidaPR(NodoArbol nuevo, NodoArbol nodo) {
        if (nodo != null) {
            if (nodo.getDerecho() != null) {
                nuevo.setIzquierdo(new NodoArbol(nodo.getDerecho().getElem(), null, null));
                clonarParteInvertidaPR(nuevo.getIzquierdo(), nodo.getDerecho());
            }
            if (nodo.getIzquierdo() != null) {
                nuevo.setDerecho(new NodoArbol(nodo.getIzquierdo().getElem(), null, null));
                clonarParteInvertidaPR(nuevo.getDerecho(), nodo.getIzquierdo());
            }
        }
    }

    public int diferenciaCandidatos(Comparable elem) {
        int diferencia = -1;
        if (this.raiz != null) {
            diferencia = diferenciaCandidatosPR(this.raiz, elem);
        }
        return diferencia;
    }

    private int diferenciaCandidatosPR(NodoArbol nodo, Comparable elem) {
        int diferencia = -1;
        if (nodo != null) {
            if (elem.equals(nodo.getElem())) {
                if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                    int menorDerecho;
                    int mayorIzquierdo;
                    NodoArbol izq = nodo.getIzquierdo();
                    NodoArbol der = nodo.getDerecho();
                    while (izq.getDerecho() != null) {
                        izq = izq.getDerecho();
                    }
                    while (der.getIzquierdo() != null) {
                        der = der.getIzquierdo();
                    }
                    mayorIzquierdo = (int) izq.getElem();
                    menorDerecho = (int) der.getElem();
                    diferencia = menorDerecho - mayorIzquierdo;
                } else {
                    diferencia = -2;
                }
            }
        }
        return diferencia;
    }

    public Lista listarMenores(Comparable elem) {
        Lista ls = new Lista();
        listarMenoresPR(this.raiz, elem, ls);
        return ls;
    }

    private void listarMenoresPR(NodoArbol nodo, Comparable elem, Lista ls) {
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) < 0) {
                ls.insertar(nodo.getElem(), ls.longitud() + 1);
                listarMenoresPR(nodo.getIzquierdo(), elem, ls);
                listarMenoresPR(nodo.getDerecho(), elem, ls);
            } else {
                listarMenoresPR(nodo.getIzquierdo(), elem, ls);
            }
        }
    }

    public Lista listarMayorIgual(Comparable elem) {
        Lista ls = new Lista();
        listarMayorIgualPR(this.raiz, elem, ls);
        return ls;
    }

    private void listarMayorIgualPR(NodoArbol nodo, Comparable elem, Lista ls) {
        if (nodo != null) {
            if (nodo.getElem().equals(elem) || nodo.getElem().compareTo(elem) > 0) {
                listarMayorIgualPR(nodo.getDerecho(), elem, ls);
                ls.insertar(nodo.getElem(), ls.longitud() + 1);
                listarMayorIgualPR(nodo.getIzquierdo(), elem, ls);
            } else {
                listarMayorIgualPR(nodo.getDerecho(), elem, ls);
            }
        }
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elem, null, null);
        } else {
            exito = insertarPR(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarPR(NodoArbol nodo, Comparable elem) {
        boolean exito = true;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                exito = false;
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    //es menor
                    if (nodo.getIzquierdo() != null) {
                        exito = insertarPR(nodo.getIzquierdo(), elem);
                    } else {
                        nodo.setIzquierdo(new NodoArbol(elem, null, null));
                    }
                } else {
                    //es mayor
                    if (nodo.getDerecho() != null) {
                        exito = insertarPR(nodo.getDerecho(), elem);
                    } else {
                        nodo.setDerecho(new NodoArbol(elem, null, null));
                    }
                }
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elem) {
        boolean exito = true;
        if (this.raiz != null) {
            exito = eliminarPR(null, this.raiz, elem);
        }
        return exito;
    }

    private boolean eliminarPR(NodoArbol padre, NodoArbol nodo, Comparable elem) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) == 0) {
                exito = true;
                eliminarSegunCaso(padre, nodo, determinarCaso(nodo));
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    exito = eliminarPR(nodo, nodo.getIzquierdo(), elem);
                } else {
                    exito = eliminarPR(nodo, nodo.getDerecho(), elem);
                }
            }
        }
        return exito;
    }

    private int determinarCaso(NodoArbol nodo) {
        int caso;
        if (nodo.getDerecho() != null) {
            if (nodo.getIzquierdo() != null) {
                //dos hijos
                caso = 3;
            } else {
                //con subarbol derecho
                caso = 2;
            }
        } else {
            if (nodo.getIzquierdo() == null) {
                //sin hijos
                caso = 1;
            } else {
                //con subarbol izquierdo
                caso = 2;
            }
        }
        return caso;
    }

    private void eliminarSegunCaso(NodoArbol padre, NodoArbol nodo, int caso) {
        System.out.println(caso);
        switch (caso) {
            case 1:
                padre.setIzquierdo(null);
                padre.setDerecho(null);
                break;
            case 2:
                if (nodo.getElem().compareTo(padre.getElem()) > 0) {
                    padre.setDerecho(nodo.getDerecho());
                } else {
                    padre.setDerecho(nodo.getIzquierdo());
                }
                break;
            case 3:
                NodoArbol padreAux = nodo;
                NodoArbol aux = nodo.getIzquierdo();
                if (aux.getDerecho() != null) {
                    padreAux = nodo.getIzquierdo();
                }
                while (aux.getDerecho() != null) {
                    padreAux = nodo.getIzquierdo();
                    aux = aux.getDerecho();
                }
                intercambiar(nodo, aux);
                System.out.println(padreAux.getElem());
                System.out.println(padreAux.getIzquierdo().getElem());
                System.out.println(padreAux.getDerecho().getElem());
                if (padreAux.getIzquierdo().getElem().compareTo(padreAux.getElem()) == 0) {
                    padreAux.setIzquierdo(null);
                } else {
                    padreAux.setDerecho(null);
                }
                break;
        }
    }

    private void intercambiar(NodoArbol nodo1, NodoArbol nodo2) {
        nodo1.setElem(nodo2.getElem());
    }

    public boolean pertenece(Comparable elem) {
        boolean pertenece = false;
        if (this.raiz != null) {
            pertenece = pertenecePR(this.raiz, elem);
        }
        return pertenece;
    }

    private boolean pertenecePR(NodoArbol nodo, Comparable elem) {
        boolean pertenece = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) == 0) {
                pertenece = true;
            } else {
                if (nodo.getElem().compareTo(elem) < 0) {
                    pertenece = pertenecePR(nodo.getIzquierdo(), elem);
                } else {
                    pertenece = pertenecePR(nodo.getDerecho(), elem);
                }
            }
        }
        return pertenece;
    }

    public String toString() {
        String toString = toStringPR(this.raiz);
        return toString;
    }

    public Lista listar() {
        Lista lista = new Lista();
        listarInordenPR(this.raiz, lista);
        return lista;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Comparable minimoElem() {
        Comparable elem = null;
        if (this.raiz != null) {
            NodoArbol aux = this.raiz;
            while (aux.getIzquierdo() != null) {
                aux = aux.getIzquierdo();
            }
            elem = aux.getElem();
        }
        return elem;
    }

    public Comparable maximoElem() {
        Comparable elem = null;
        if (this.raiz != null) {
            NodoArbol aux = this.raiz;
            while (aux.getDerecho() != null) {
                aux = aux.getDerecho();
            }
            elem = aux.getElem();
        }
        return elem;
    }

    private void listarInordenPR(NodoArbol nodo, Lista temp) {
        if (nodo != null) {
            listarInordenPR(nodo.getIzquierdo(), temp);
            temp.insertar(nodo.getElem(), temp.longitud() + 1);
            listarInordenPR(nodo.getDerecho(), temp);
        }
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
}
