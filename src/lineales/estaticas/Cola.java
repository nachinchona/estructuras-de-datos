package lineales.estaticas;

public class Cola {

    private int frente;
    private int fin;
    private static final int TAMANIO = 10;
    private Object[] arreglo;

    public Cola() {
        this.frente = 0;
        this.fin = 0;
        this.arreglo = new Object[this.TAMANIO];
    }

    public boolean poner(Object elem) {
        boolean exito;
        if (this.frente != (this.fin + 1)%TAMANIO) {
            this.arreglo[this.fin] = elem;
            this.fin = (this.fin + 1) % TAMANIO;
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito;
        if (this.frente != this.fin) {
            arreglo[frente] = null;
            frente = (frente + 1) % TAMANIO;
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public boolean esVacia() {
        return this.frente == this.fin;
    }

    public void vaciar() {
        for (int i = 0; i < TAMANIO; i++) {
            arreglo[i] = null;
        }
        this.frente = 0;
        this.fin = 0;
    }

    public Object obtenerFrente() {
        Object elem;
        if (!this.esVacia()) {
            elem = this.arreglo[frente];
        } else {
            elem = null;
        }
        return elem;
    }

    public Cola clone() {
        Cola clon = new Cola();
        if (!this.esVacia()) {
            clon.fin = this.fin;
            clon.frente = this.frente;
            int i = frente;
            while (i != fin && i < TAMANIO) {
                clon.arreglo[i] = this.arreglo[i];
                i = (i+1)%TAMANIO;
            }
        }
        return clon;
    }

    public String toString() {
        String toString = "";
        int i = frente;
        if (frente == fin) {
            toString = "COLA VACIA";
        } else {
            while (i != fin && i < TAMANIO) {
                if (i == frente) {
                    toString = this.arreglo[i].toString();
                } else {
                    toString = toString + " / " + this.arreglo[i].toString();
                }
                i = (i + 1) % TAMANIO;
            }
        }
        toString = "[" + toString + "]";
        return toString;
    }

}
