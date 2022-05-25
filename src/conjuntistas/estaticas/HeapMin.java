package conjuntistas.estaticas;

public class HeapMin {

    private static final int TAMANIO = 10;
    private Comparable[] arreglo;
    private int ultimo;

    public HeapMin() {
        arreglo = new Comparable[TAMANIO];
        ultimo = 0;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (ultimo != TAMANIO) {
            arreglo[ultimo] = elem;
            siftUp(arreglo, ultimo);
            ultimo++;
        } else {
            exito = false;
        }
        return exito;
    }

    private void siftUp(Comparable[] arr, int i) {
        int padre;
        if (i != 0 && i % 2 == 0) {
            padre = i / 2 - 1;
        } else {
            padre = i / 2;
        }
        if (arr[i].compareTo(arr[padre]) < 0) {
            intercambiar(arr, padre, i);
            i = padre;
            siftUp(arr, i);
        }
    }

    public boolean eliminarCima() {
        boolean exito = true;
        if (ultimo != 0) {
            arreglo[0] = arreglo[ultimo - 1];
            arreglo[ultimo - 1] = null;
            ultimo--;
            reordenar(arreglo, 0);
        }
        return exito;
    }

    public Comparable recuperarCima() {
        return arreglo[0];
    }

    private void reordenar(Comparable[] arr, int i) {
        Comparable hijoI = null, hijoD = null;
        int posI = 2 * i + 1, posD = 2 * i + 2, posMasChico = -1;
        if (posI < TAMANIO) {
            hijoI = arr[posI];
            if (posD < TAMANIO) {
                hijoD = arr[posD];
            }
        }
        Comparable hijoMasChico;
        if (hijoI != null) {
            if (hijoD != null && hijoD.compareTo(hijoI) < 0) {
                hijoMasChico = hijoD;
                posMasChico = posD;
            } else {
                hijoMasChico = hijoI;
                posMasChico = posI;
            }
            if (hijoMasChico.compareTo(arr[i]) < 0) {
                intercambiar(arr, posMasChico, i);
                reordenar(arr, posMasChico);
            }
        }
    }

    private void intercambiar(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private int retornarPosMasGrande(Comparable[] arr, int a, int b) {
        int pos = -1;
        if (arr[a] != null) {
            if (arr[b] != null && arr[a].compareTo(arr[b]) < 0) {
                pos = b;
            } else {
                pos = a;
            }
        }
        return pos;
    }

    public String toString() {
        String retorno = "";
        for (int i = 0; i < ultimo; i++) {
            if (i != ultimo - 1) {
                retorno = retorno + arreglo[i].toString() + " / ";
            } else {
                retorno = retorno + arreglo[i].toString();
            }
        }
        return retorno;
    }
}
