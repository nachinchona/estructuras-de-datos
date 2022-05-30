package conjuntistas.dinamicas;

public class ArbolBB {

    private NodoArbol raiz;

    public ArbolBB() {
        this.raiz = null;
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

    public boolean pertenece(Comparable elem) {
        boolean pertenece = false;

        return pertenece;
    }

    private boolean pertenecePR(NodoArbol nodo, Comparable elem) {
        boolean pertenece = false;
        if (nodo != null) {
            
        }
        return pertenece;
    }
}
