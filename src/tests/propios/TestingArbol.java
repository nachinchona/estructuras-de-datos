package tests.propios;
import jerarquicas.dinamicas.ArbolBin;
public class TestingArbol {

    public static void main(String[] args) {
        ArbolBin arbol1 = new ArbolBin();
        arbol1.insertar(1, null, 'I');
        arbol1.insertar(2, 1, 'I');
        arbol1.insertar(3, 1, 'D');
        arbol1.insertar(4, 2, 'I');
        
        System.out.println(arbol1.toString());
    }

}
