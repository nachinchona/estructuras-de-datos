package tests.conjuntistas;
import conjuntistas.estaticas.HeapMax;
public class TestHeapMax {

    public static void main(String[] args) {
        HeapMax heap = new HeapMax();
        heap.insertar(52);
        heap.insertar(11);
        heap.insertar(42);
        heap.insertar(89);
        heap.insertar(8);
        heap.insertar(23);
        heap.insertar(11);
        heap.insertar(1);
        heap.insertar(39);
        
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
