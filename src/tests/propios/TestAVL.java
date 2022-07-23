package tests.propios;

import conjuntistas.dinamicas.ArbolAVL;

public class TestAVL {

    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(4);
        arbol.insertar(9);
        arbol.insertar(7);
        arbol.insertar(8);
        arbol.insertar(6);
        arbol.insertar(10);
        arbol.insertar(23);
        arbol.insertar(40);
        
        
        
        
        System.out.println(arbol.toString());
    }

}
