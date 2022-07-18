package conjuntistas.dinamicas;

import lineales.dinamicas.Lista;

public class ArbolAVL {
    private NodoAVL raiz;
    
    public ArbolAVL(){
        this.raiz = null;
    }
    
    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem, null, null);
        } else {
            exito = insertarPR(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarPR(NodoAVL nodo, Comparable elem) {
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
                        nodo.setIzquierdo(new NodoAVL(elem, null, null));
                    }
                } else {
                    //es mayor
                    if (nodo.getDerecho() != null) {
                        exito = insertarPR(nodo.getDerecho(), elem);
                    } else {
                        nodo.setDerecho(new NodoAVL(elem, null, null));
                    }
                }
            }
        }
        if (exito) {
            nodo.recalcularAltura();
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

    private boolean eliminarPR(NodoAVL padre, NodoAVL nodo, Comparable elem) {
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

    private int determinarCaso(NodoAVL nodo) {
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

    private void eliminarSegunCaso(NodoAVL padre, NodoAVL nodo, int caso) {
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
                NodoAVL padreAux = nodo;
                NodoAVL aux = nodo.getIzquierdo();
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

    private void intercambiar(NodoAVL nodo1, NodoAVL nodo2) {
        nodo1.setElem(nodo2.getElem());
    }

    public boolean pertenece(Comparable elem) {
        boolean pertenece = false;
        if (this.raiz != null) {
            pertenece = pertenecePR(this.raiz, elem);
        }
        return pertenece;
    }

    private boolean pertenecePR(NodoAVL nodo, Comparable elem) {
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
            NodoAVL aux = this.raiz;
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
            NodoAVL aux = this.raiz;
            while (aux.getDerecho() != null) {
                aux = aux.getDerecho();
            }
            elem = aux.getElem();
        }
        return elem;
    }

    private void listarInordenPR(NodoAVL nodo, Lista temp) {
        if (nodo != null) {
            listarInordenPR(nodo.getIzquierdo(), temp);
            temp.insertar(nodo.getElem(), temp.longitud() + 1);
            listarInordenPR(nodo.getDerecho(), temp);
        }
    }

    private String toStringPR(NodoAVL nodo) {
        String toString = "Arbol vacio";
        if (nodo != null) {
            toString = nodo.getElem().toString();
            NodoAVL hijoIzq = nodo.getIzquierdo();
            NodoAVL hijoDer = nodo.getDerecho();
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
