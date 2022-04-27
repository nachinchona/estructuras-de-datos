package test.lineales;

import lineales.dinamicas.*;

public class TestCadenas2 {

    public static void main(String[] args) {

    }

    public static boolean verificarBalanceo(Cola q) {
        Cola clon = q.clone();
        Pila simbolos = new Pila();
        boolean estaBalanceada = false;
        String simbolosAbiertos = "([{";
        String simbolosCerrados = ")]}";
        while (!clon.esVacia()) {
            if (simbolosAbiertos.contains((String) clon.obtenerFrente())) {
                simbolos.apilar(clon.obtenerFrente());
            }
            if (simbolosCerrados.contains((String) clon.obtenerFrente())) {
                if (!simbolos.esVacia()) {
                    switch((char)simbolos.obtenerTope()){
                        case '(': 
                    }
                }
            }
        }

        return estaBalanceada;
    }

    public static Cola generar(Cola c1) {
        Cola clon = c1.clone();
        Cola retorno = new Cola();
        Pila inversa = new Pila();
        String cadena = "";
        clon.poner('#');
        while (!clon.esVacia()) {
            if (!clon.obtenerFrente().equals('#')) {
                retorno.poner(clon.obtenerFrente());
                inversa.apilar(clon.obtenerFrente());
                cadena = cadena + clon.obtenerFrente();
            } else {
                while (!inversa.esVacia()) {
                    retorno.poner(inversa.obtenerTope());
                    inversa.desapilar();
                }
                for (int i = 0; i < cadena.length(); i++) {
                    retorno.poner(cadena.charAt(i));
                }
                cadena = "";
                retorno.poner('#');
            }
            clon.sacar();
        }
        return retorno;
    }
    /*String cadena = "AB#CDE#FGHI#JK";
        Cola chars = new Cola();
        for (int i = 0; i < cadena.length(); i++) {
            chars.poner(cadena.charAt(i));
        }
        Cola generada = generar(chars);
        System.out.println(generada.toString());*/
}
