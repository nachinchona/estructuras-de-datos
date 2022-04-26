package test.lineales;
import lineales.dinamicas.Lista;
public class TestingLista1 {

    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertar('1', 1);
        lista.insertar('2', 2);
        lista.insertar('3', 3);
        lista.insertar('4', 4);
        lista.insertar('1', 5);
        lista.insertar('2', 6);
        lista.insertar('3', 7);
        lista.insertar('4', 8);
        lista.insertar('1', 9);
        lista.insertar('2', 10);
        
        System.out.println(lista.toString());
        System.out.println(lista.longitud());
        
        lista.eliminarApariciones('1');
        System.out.println(lista.toString());

    }

}
