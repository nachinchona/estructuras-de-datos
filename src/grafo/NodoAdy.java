package grafo;

public class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    public NodoAdy(NodoVert vertice, NodoAdy sigAdyacente){
        this.vertice = vertice;
        this.sigAdyacente = sigAdyacente;
    }
}
