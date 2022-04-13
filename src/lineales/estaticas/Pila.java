package lineales.estaticas;

/*
@author Ignacio Navarro FAI-3240
*/

public class Pila {

    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar(Object nuevoElem) {
        boolean exito;

        if (this.tope + 1 >= this.TAMANIO) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar() {
        boolean exito;

        if (this.tope < this.TAMANIO && this.tope >= 0) {
            this.tope--;
            exito = true;
            //no es necesario hacer que el objeto en la pos tope sea nulo, con tal de actualizar la pos de tope es suficiente
        } else {
            exito = false;
        }

        return exito;
    }

    public Object obtenerTope() {
        Object objTope;
        if (this.tope != -1) {
            objTope = this.arreglo[tope];
        } else {
            objTope = null;
        }
        return objTope;
    }

    public boolean esVacia() {
        return tope == -1;
    }

    public void vaciar() {
        int n = this.arreglo.length;
        for (int i = 0; i < n; i++) {
            this.desapilar();
        }
    }

    public Pila clone() {
        Pila clon = new Pila();
        int i = 0;

        while (i <= this.tope) {
            //copio uno a uno los elementos a la pila clon
            clon.arreglo[i] = this.arreglo[i];
            i++;
        }
        //copio el tope a la pila clon
        clon.tope = this.tope;

        return clon;
    }

    @Override
    public String toString() {
        String toString = "";

        if (!this.esVacia()) {
            for (int i = 0; i <= this.tope; i++) {
                toString = toString + this.arreglo[i].toString();
                if (i != this.tope) {
                    toString = toString + " / ";
                }
            }
        }else{
            toString = "PILA VACIA";
        }
        toString = "[ "+toString+" ]";
        return toString;
    }
}