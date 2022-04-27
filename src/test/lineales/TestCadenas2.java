package test.lineales;

import lineales.dinamicas.*;

public class TestCadenas2 {

    public static void main(String[] args) {
        String cuenta = "{[[()]]}";
        Cola matematica = new Cola();
        for (int i = 0; i < cuenta.length(); i++) {
            matematica.poner(cuenta.charAt(i));
        }
        System.out.println(verificarBalanceo(matematica));
    }

    public static boolean verificarBalanceo(Cola q) {
        Cola clon = q.clone();
        Pila simbolos = new Pila();
        boolean estaBalanceada = true;
        int aCerrar = 0;
        String simbolosAbiertos = "([{";
        String simbolosCerrados = ")]}";
        while (estaBalanceada && !clon.esVacia()) {
            if (simbolosAbiertos.contains(Character.toString((char) clon.obtenerFrente()))) {
                simbolos.apilar(clon.obtenerFrente());
                aCerrar++;
            } else {
                if (simbolosCerrados.contains(Character.toString((char) clon.obtenerFrente()))) {
                    if (!simbolos.esVacia()) {
                        switch ((char) simbolos.obtenerTope()) {
                            case '(':
                                estaBalanceada = clon.obtenerFrente().equals(')'); aCerrar--;
                                break;
                            case '[':
                                estaBalanceada = clon.obtenerFrente().equals(']'); aCerrar--;
                                break;
                            case '{':
                                estaBalanceada = clon.obtenerFrente().equals('}'); aCerrar--;
                                break;
                            default:
                                estaBalanceada = false;
                        }
                        simbolos.desapilar();
                    }else{
                        estaBalanceada = false;
                    }
                }
            }
            clon.sacar();
        }
        estaBalanceada = estaBalanceada && aCerrar == 0;
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
