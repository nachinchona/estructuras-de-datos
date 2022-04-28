package jerarquicas.test;

import jerarquicas.dinamicas.ArbolBin;
import lineales.dinamicas.*;

public class TestingArbol {

    public static void main(String[] args) {
        ArbolBin arbolito = new ArbolBin();
        arbolito.insertar('A', null, 'I');
        arbolito.insertar('B', 'A', 'I');
        arbolito.insertar('C', 'A', 'D');
        arbolito.insertar('D', 'B', 'I');
        arbolito.insertar('E', 'C', 'I');
        arbolito.insertar('F', 'C', 'D');
        arbolito.insertar('G', 'E', 'I');
        arbolito.insertar('H', 'E', 'D');
        System.out.println(arbolito.toString());
        System.out.println(arbolito.frontera());
        ArbolBin inverso = arbolito.clonarInvertido();
        System.out.println(inverso.toString());
    }

}
