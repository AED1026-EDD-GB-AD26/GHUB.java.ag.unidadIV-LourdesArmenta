package arbolBinario;

public class ArbolBinario {
    //atributo
    protected Nodo raiz;
    //constructores
    public ArbolBinario() {
        raiz = null;
    }
    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }
    //Operaciones
    /*
    invocamos a los métodos recursivos
     */
    public void preorden(){
        preorden(raiz);

    }
    public void inorden(){
        inorden(raiz);
    }
    public void postorden(){
        postorden(raiz);
    }
    //Recorridos
    //Recursiva
    private void preorden(Nodo aux){
        if(aux !=null){
            visitar(aux);
            preorden(aux.getIzquierdo());
            preorden(aux.getDerecho());
        }

    }
    private void inorden(Nodo aux){
        if(aux !=null){
            inorden(aux.getIzquierdo());
            visitar(aux);
            inorden(aux.getDerecho());
        }

    }
    private void postorden(Nodo aux){
        if(aux!=null){
            postorden(aux.getIzquierdo());
            postorden(aux.getDerecho());
            visitar(aux);
        }

    }
    //Recorrido
    //iterativos
    
    /*
    el método visitar depende de la aplicación. Si simplemente se 
    quieren listar los nodos por consola, se implementaria asi...
     */
    private void visitar(Nodo aux){
        System.out.print(aux.getValor()+" ");
    }

    
    
    
    
    
}
