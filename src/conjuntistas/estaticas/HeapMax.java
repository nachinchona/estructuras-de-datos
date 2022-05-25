package conjuntistas.estaticas;

public class HeapMax {

    private static final int TAMANIO = 10;
    private Comparable[] arreglo;
    private int ultimo;

    public HeapMax() {
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

    public boolean eliminarCima() {
        boolean exito = false;
        if (ultimo != 0) {
            arreglo[0] = arreglo[ultimo - 1];
            arreglo[ultimo - 1] = null;
            ultimo--;
            reordenar(arreglo, 0);
            exito = true;
        }
        return exito;
    }
    
    public Comparable recuperarCima(){
        return arreglo[0];
    }

    private void reordenar(Comparable[] arr, int i) {
        Comparable hijoI = null, hijoD = null;
        int posI = 2 * i + 1, posD = 2 * i + 2, posMasGrande = -1;
        if (posI < TAMANIO) {
            hijoI = arr[posI];
            if (posD < TAMANIO) {
                hijoD = arr[posD];
            }
        }
        Comparable hijoMasGrande;
        if (hijoI != null) {
            if (hijoD != null && hijoD.compareTo(hijoI) > 0) {
                hijoMasGrande = hijoD;
                posMasGrande = posD;
            }else{
                hijoMasGrande = hijoI;
                posMasGrande = posI;
            }
            if (hijoMasGrande.compareTo(arr[i]) > 0) {
                intercambiar(arr, posMasGrande, i);
                reordenar(arr, posMasGrande);
            }
        }
    }

    private void siftUp(Comparable[] arr, int i) {
        int padre;
        if (i != 0 && i % 2 == 0) {
            padre = i / 2 - 1;
        } else {
            padre = i / 2;
        }
        if (arr[i].compareTo(arr[padre]) > 0) {
            intercambiar(arr, padre, i);
            i = padre;
            siftUp(arr, i);
        }
    }

    private void intercambiar(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
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
