package test.lineales;

import lineales.dinamicas.Lista;

public class TestingLista {

    public static void main(String[] args) {
        Lista lista1 = new Lista();

        for (int i = 1; i < 6; i++) {
            lista1.insertar(i, i);
        }

        System.out.println(lista1.toString());

        System.out.println("inserto 9 en pos 1");
        lista1.insertar(9, 1);
        System.out.println(lista1.toString());
        
        System.out.println("inserto 7 en pos 6");
        lista1.insertar(7, 7);
        System.out.println(lista1.toString());
        
        System.out.println("eliminar 1 en pos 2");
        lista1.eliminar(2);
        System.out.println(lista1.toString());
        
        System.out.println("clonar lista");
        Lista lista2 = lista1.clone();
        
        System.out.println("lista 2");
        System.out.println(lista2.toString());
        System.out.println("elimino el primer y ultimo elemento de la lista 2");
        lista2.eliminar(1);
        lista2.eliminar(5);
        System.out.println("nueva lista 2");
        System.out.println(lista2.toString());
        
        System.out.println("lista 1");
        System.out.println(lista1.toString());

        System.out.println("vaciar lista");
        lista1.vaciar();
        System.out.println(lista1.toString());
        
        

    }

}
