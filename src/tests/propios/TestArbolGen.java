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
        
        System.out.println(arbol.toString());
        System.out.println("nuevo");
        System.out.println(arbol.listarHastaNivel(2).toString());
    }
}
