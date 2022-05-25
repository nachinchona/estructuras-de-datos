package tests.conjuntistas;
import conjuntistas.estaticas.HeapMin;
public class TestHeapMin {

    public static void main(String[] args) {
        HeapMin heap = new HeapMin();
        heap.insertar(9);
        System.out.println(heap.toString());
        heap.insertar(8);
        System.out.println(heap.toString());
        heap.insertar(7);
        System.out.println(heap.toString());
        heap.insertar(6);
        System.out.println(heap.toString());
        heap.insertar(5);
        System.out.println(heap.toString());
        heap.insertar(4);
        System.out.println(heap.toString());
        heap.insertar(3);
        System.out.println(heap.toString());
        heap.insertar(2);
        System.out.println(heap.toString());
        heap.insertar(1);
        
        System.out.println(heap.toString());
        System.out.println(heap.recuperarCima());
        heap.eliminarCima();
        
        System.out.println(heap.toString());
        System.out.println(heap.recuperarCima());
        heap.eliminarCima();
        
        System.out.println(heap.toString());
        System.out.println(heap.recuperarCima());
        heap.eliminarCima();
        
        System.out.println(heap.toString());
        System.out.println(heap.recuperarCima());
        heap.eliminarCima();
        
    }

}