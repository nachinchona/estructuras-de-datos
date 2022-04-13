package lineales.dinamicas;

import lineales.dinamicas.Pila;
import java.util.Scanner;
public class TestPila {

    public static void main(String[] args) {
        Pila pila1 = new Pila();
        Pila pila2 = new Pila();
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese el elemento "+i);
            pila1.apilar(sc.next());
        }
        
        System.out.println(pila1.toString());
        
        pila2 = pila1.clone();
        System.out.println("pila clonada");
        System.out.println(pila2.toString());
        
        pila1.desapilar();
        System.out.println("elemento desapilado");
        
        System.out.println(pila1.toString());
        System.out.println(pila2.toString());
        System.out.println(pila1.obtenerTope());
        System.out.println(pila2.obtenerTope());
        
    }

}
