package jerarquicas.test;

import jerarquicas.dinamicas.ArbolBin;
import lineales.dinamicas.*;

public class TestingArbol {

    public static void main(String[] args) {
        ArbolBin arbolito = new ArbolBin();
        
        System.out.println(arbolito.toString());
        
        ArbolBin arbolillo = new ArbolBin();

        System.out.println(arbolillo.toString());
        System.out.println(arbolito.equals(arbolillo));
        
    }

}
