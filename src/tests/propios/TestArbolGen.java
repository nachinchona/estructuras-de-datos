package tests.propios;
import jerarquicas.dinamicas.*;
import lineales.dinamicas.*;
public class TestArbolGen {

    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        arbol.insertar(20, null);
        arbol.insertar(5, 20);
        arbol.insertar(7, 20);
        arbol.insertar(15, 20);
        arbol.insertar(6, 5);
        arbol.insertar(52, 15);
        Lista ls = new Lista();
        ls.insertar(20, 1);
        ls.insertar(5, 2);
        ls.insertar(6, 3);
        
        System.out.println(arbol.toString());
        System.out.println(ls.toString());
        System.out.println(arbol.verificarCamino(ls));
    }
}
