package tests.propios;
import conjuntistas.dinamicas.*;
public class TestABB {

    public static void main(String[] args) {
        ArbolBB arbol = new ArbolBB();
        System.out.println(arbol.toString());
        arbol.insertar(5);
        arbol.insertar(1);
        arbol.insertar(2);
        arbol.insertar(7);
        arbol.insertar(4);
        arbol.insertar(9);
        arbol.insertar(3);
        arbol.insertar(6);
        System.out.println(arbol.toString());
        System.out.println(arbol.listar().toString());
        System.out.println(arbol.eliminar(2));
        System.out.println(arbol.toString());
        System.out.println(arbol.listar().toString());
        System.out.println(arbol.minimoElem());
        System.out.println(arbol.maximoElem());
    }

}
