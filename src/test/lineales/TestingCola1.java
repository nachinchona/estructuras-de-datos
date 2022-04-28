package test.lineales;

import lineales.dinamicas.Cola;
//import lineales.estaticas.Cola;

import java.util.Scanner;

public class TestingCola1 {

    public static void main(String[] args) {
        Cola cola1 = new Cola();
        Scanner sc = new Scanner(System.in);
        String cadena;
        cola1.poner('1');
        Cola cola2 = cola1.clone();
        System.out.println(cola2.toString());
        cola2.sacar();
        System.out.println(cola2.toString());
    }

}
