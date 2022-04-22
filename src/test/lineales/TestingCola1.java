package test.lineales;

import lineales.dinamicas.Cola;
//import lineales.estaticas.Cola;

import java.util.Scanner;

public class TestingCola1 {

    public static void main(String[] args) {
        Cola cola1 = new Cola();
        Scanner sc = new Scanner(System.in);
        String cadena;
        for (int i = 0; i < 4; i++) {
            System.out.println(i + 1);
            cola1.poner(i + 1);
        }
        System.out.println(cola1.toString());
        System.out.println("frente:");
        System.out.println(cola1.obtenerFrente().toString());
        System.out.println("saco 0");
        cola1.sacar();
        System.out.println("frente: ");
        System.out.println(cola1.obtenerFrente().toString());

        System.out.println("to string");
        System.out.println(cola1.toString());
        Cola cola2 = cola1.clone();
        System.out.println("to string clon");
        System.out.println(cola2.toString());

        System.out.println("frente clon");
        System.out.println(cola2.obtenerFrente());
    
    }

}
