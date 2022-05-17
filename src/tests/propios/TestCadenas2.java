package tests.propios;

import lineales.dinamicas.*;

public class TestCadenas2 {

    public static void main(String[] args) {
        Lista l1 = new Lista();
        l1.insertar(1, 1);
        l1.insertar(1, 2);
        l1.insertar(2, 3);
        l1.insertar(3, 4);
        l1.insertar(1, 5);
        l1.insertar(4, 6);
        
        System.out.println(contar2(l1,1,1));
        System.out.println(esCapicua(l1));

    }

    public static int contar(Lista l1, Object elem) {
        int longitud = l1.longitud();
        int contador = 0;
        for (int i = 1; i <= longitud; i++) {
            if (l1.recuperar(i).equals(elem)) {
                contador++;
            }
        }
        return contador;
    }

    public static int contar2(Lista l1, Object elem, int pos) {
        int contador = 0;
        int longitud = l1.longitud();
        if (pos <= longitud) {
            if (l1.recuperar(pos).equals(elem)) {
                contador = 1;
            }
            contador = contador + contar2(l1, elem, pos + 1);
        }
        return contador;
    }

    public static boolean esCapicua(Lista l1) {
        boolean sigue = true;
        int i = 1;
        int longitud = l1.longitud();
        Pila P1 = new Pila();
        Cola Q1 = new Cola();
        while (i <= longitud) {
            Object elem = l1.recuperar(i);
            P1.apilar(elem);
            Q1.poner(elem);
            i++;
        }
        i = 1;
        while (sigue && i <= longitud) {
            sigue = Q1.obtenerFrente().equals(P1.obtenerTope());
            i++;
            P1.desapilar();
            Q1.sacar();
        }
        return sigue;
    }

    public static Lista intercalar(Lista l1, Lista l2) {
        Lista retorno = new Lista();
        int longitud1 = l1.longitud();
        int longitud2 = l2.longitud();
        int longFinal;
        if (longitud1 >= longitud2) {
            longFinal = longitud1;
        } else {
            longFinal = longitud2;
        }
        int i = 1;
        int length = 1;
        while (i <= longFinal) {
            retorno.insertar(l1.recuperar(i), length++);
            retorno.insertar(l2.recuperar(i), length++);
            i++;
        }

        return retorno;
    }

    public static Lista generarSecuencia(Cola q, int t) {
        Cola clon = q.clone();
        Pila inverso = new Pila();
        Cola aux = new Cola();
        Lista retorno = new Lista();
        int i = 1;
        int longitud = 1;
        while (!clon.esVacia()) {
            char temp = (char) clon.obtenerFrente();
            clon.sacar();
            inverso.apilar(temp);
            aux.poner(temp);
            if (i % t == 0 || clon.esVacia()) {
                while (!inverso.esVacia()) {
                    retorno.insertar(inverso.obtenerTope(), longitud++);
                    inverso.desapilar();
                }
                while (!aux.esVacia()) {
                    retorno.insertar(aux.obtenerFrente(), longitud++);
                    aux.sacar();
                }
                if (!clon.esVacia()) {
                    retorno.insertar('$', longitud++);
                }
            }
            i++;
        }
        return retorno;
    }

    public static boolean verificarBalanceo(Cola q) {
        Cola clon = q.clone();
        Pila simbolos = new Pila();
        boolean estaBalanceada = true;
        String simbolosAbiertos = "([{";
        String simbolosCerrados = ")]}";
        while (estaBalanceada && !clon.esVacia()) {
            if (simbolosAbiertos.contains(Character.toString((char) clon.obtenerFrente()))) {
                simbolos.apilar(clon.obtenerFrente());
            } else {
                if (simbolosCerrados.contains(Character.toString((char) clon.obtenerFrente()))) {
                    if (!simbolos.esVacia()) {
                        switch ((char) simbolos.obtenerTope()) {
                            case '(':
                                estaBalanceada = clon.obtenerFrente().equals(')');
                                break;
                            case '[':
                                estaBalanceada = clon.obtenerFrente().equals(']');
                                break;
                            case '{':
                                estaBalanceada = clon.obtenerFrente().equals('}');
                                break;
                            default:
                                estaBalanceada = false;
                        }
                        simbolos.desapilar();
                    } else {
                        estaBalanceada = false;
                    }
                }
            }
            clon.sacar();
        }
        estaBalanceada = estaBalanceada && simbolos.esVacia();
        return estaBalanceada;
    }

    public static Cola generar(Cola c1) {
        Cola clon = c1.clone();
        Cola retorno = new Cola();
        Pila inversa = new Pila();
        Cola aux = new Cola();
        clon.poner('#');
        while (!clon.esVacia()) {
            if (!clon.obtenerFrente().equals('#')) {
                retorno.poner(clon.obtenerFrente());
                inversa.apilar(clon.obtenerFrente());
                aux.poner(clon.obtenerFrente());
            } else {
                while (!inversa.esVacia()) {
                    retorno.poner(inversa.obtenerTope());
                    inversa.desapilar();
                }
                while (!aux.esVacia()) {
                    retorno.poner(aux.obtenerFrente());
                    aux.sacar();
                }
                retorno.poner('#');
            }
            clon.sacar();
            if (clon.esVacia()) {
                retorno.sacar();
            }
        }
        return retorno;
    }
}
