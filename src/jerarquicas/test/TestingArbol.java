package jerarquicas.test;
import jerarquicas.dinamicas.ArbolBin;
public class TestingArbol {

    public static void main(String[] args) {
        ArbolBin arbolito = new ArbolBin();
        System.out.println(arbolito.toString());
        ArbolBin arbolillo = new ArbolBin();
        arbolito.insertar('A', null, 'I');
        arbolito.insertar('B', 'A', 'I');
        arbolito.insertar('C', 'A', 'D');
        arbolito.insertar('D', 'B', 'I');
        arbolito.insertar('E', 'C', 'I');
        arbolito.insertar('F', 'C', 'D');
        arbolito.insertar('G', 'E', 'I');
        arbolito.insertar('H', 'E', 'D');
        System.out.println(arbolito.toString());
        System.out.println("recorrido preorden");
        System.out.println(arbolito.listarPreorden().toString());
        System.out.println("recorrido inorden");
        System.out.println(arbolito.listarInorden().toString());
        System.out.println("recorrido posorden");
        System.out.println(arbolito.listarPosorden().toString());
        System.out.println("recorrido por nivel");
        System.out.println(arbolito.listarPorNivel().toString());
        System.out.println("altura: "+arbolito.altura());      
        System.out.println("nivel "+arbolito.nivel('P'));
    }

}
