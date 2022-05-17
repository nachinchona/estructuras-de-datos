package tests.propios;

import lineales.dinamicas.Lista;

public class TestingLista1 {

    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertar(1, 1);
        lista.insertar(2, 2);
        lista.insertar(3, 3);
        lista.insertar(4, 4);
        lista.insertar(5, 5);
        lista.insertar(6, 6);
        lista.insertar(7, 7);
        System.out.println(lista.toString());
        lista.agregarElem(0, 2);
        System.out.println(lista.toString());


    }

}
