package test.jerarquicas;
import jerarquicas.dinamicas.*;

public class TestArbolGen {

    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar(1, 0);
        arbol.insertar(2, 1);
        arbol.insertar(3, 1);
        arbol.insertar(4, 1);
        arbol.insertar(5, 2);
        arbol.insertar(6, 2);
        arbol.insertar(7, 2);
        arbol.insertar(8, 3);
        arbol.insertar(9, 3);
        arbol.insertar(10, 5);
        System.out.println(arbol.toString());
        System.out.println(arbol.nivel(5));
    }
}
