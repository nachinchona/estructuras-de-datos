/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 *
 * @author Seba
 */

public class TestSonFrontera {
    
    private static final String BLACK   = "\033[0;30m";
    private static final String RED     = "\033[0;31m";
    private static final String GREEN   = "\033[0;32m";   
    private static final String FORMAT  = "%-60s --> espera: %-5b, obtiene %-5b (%s"+BLACK+")\n";

    static private void print(String string, boolean espera, boolean obtiene)
    {
        System.out.printf(FORMAT, string, espera, obtiene, espera==obtiene ? GREEN+"OK" : RED+"ERROR");
    }
    
    static public void main(String[] args)
    {
        System.out.println("Prueba del metodo ArbolGen::sonFrontera(Lista)\n");
        
        ArbolGen arbol = new ArbolGen();
        Lista lista = new Lista();
        
        print("Prueba con arbol vacio y lista vacia", true, arbol.sonFrontera(lista));

        lista.insertar('A', 1);
        print("Prueba con arbol vacio y elemento no hoja", false, arbol.sonFrontera(lista));

        lista.insertar('E', 1);
        lista.insertar('C', 1);
        print("Prueba con arbol vacio y varios elementos no hoja", false, arbol.sonFrontera(lista));

        arbol.insertar('A', null);
        arbol.insertar('B', 'A');
        arbol.insertar('C', 'A');
        arbol.insertar('D', 'A');
        arbol.insertar('E', 'B');                 
        arbol.insertar('F', 'B'); 
        arbol.insertar('G', 'B');
        arbol.insertar('C', 'D');
        print("Prueba con arbol y varios elementos hoja y no hoja", false, arbol.sonFrontera(lista));

        lista.eliminar(lista.localizar('A'));
        print("Prueba con arbol y varios elementos hoja", true, arbol.sonFrontera(lista));
        
        lista.vaciar();
        print("Prueba con arbol y lista vacia", true, arbol.sonFrontera(lista));
        
        lista.insertar('K', lista.longitud()+1);
        print("Prueba con arbol y lista con elemento no hoja", false, arbol.sonFrontera(lista));

        System.out.println("\nExit.");
    }
    
}
