package tests.propios;
//importo al acalofsd oca cola

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class TestCadenas {

    public static void main(String[] args) {
        /*En una clase TestCadenas, que utilice los TDA Lista, Pila y Cola vistos en la materia, para guardar elementos
de tipo CHAR, implementar el método generar (Cola c1) que recibe por parámetro una estructura cola c1
que tiene el siguiente formato: a1#a2#a3#….#an, donde cada ai en una sucesión de letras mayúsculas y a
partir de c1 debe generar como salida otra Cola de la forma: a1a1´a1#a2a2´a2#….#anan´an donde cada ai´ es la
secuencia de letras mayúsculas ai pero invertida. Ejemplo.: Si c1 es : AB#C#DEF , entonces la operación
generar devolverá una Cola con el siguiente formato: ABBAAB#CCC#DEFFEDDEF.
        
         */
        Cola cola = new Cola();
        cola.poner("ABCD");
        cola.poner("CE");
        cola.poner("DGF");
        cola.poner("FPO");
        Cola colaNueva = generar(cola);
        System.out.println(colaNueva.toString());
    }

    public static Cola generar(Cola c1) {
        Cola colaRetorno = new Cola();
        Cola clon = c1.clone();
        String cadena;
        String nuevaCadena;
        while (!clon.esVacia()) {
            cadena = clon.obtenerFrente().toString();
            nuevaCadena = cadena + invertir(cadena) + cadena;
            colaRetorno.poner(nuevaCadena);
            clon.sacar();
        }
        return colaRetorno;      
    }

    public static String invertir(String cadena) {
        String cadenaInvertida = "";
        int longitud = cadena.length();
        Pila inversa = new Pila();
        for (int i = 0; i < longitud; i++) {
            inversa.apilar(cadena.charAt(i));
        }
        for (int i = 0; i < longitud; i++) {
            cadenaInvertida = cadenaInvertida + inversa.obtenerTope().toString();
            inversa.desapilar();
        }
        return cadenaInvertida;
    }

}
