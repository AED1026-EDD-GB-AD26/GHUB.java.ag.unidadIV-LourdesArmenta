package arbolBinario;

import cola.Cola;
import pila.Pila;

public class ArbolBinarioBusqueda {
    // atributo
    protected Nodo raiz;

    // constructores
    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public ArbolBinarioBusqueda(Nodo raiz) {
        this.raiz = raiz;
    }
    //getter
    public Nodo getRaiz(){
        return raiz;
    }
    //setter
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    } 
    
    // Operaciones
    /*
    Inserta un nodo en el árbol binario

     */
    public void insertar(Object valor) throws Exception{
        Comparable dato = (Comparable) valor;
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (raiz==null){
            raiz = nuevo;
        }else{
            //utilizo una varible auxiliar que hace referencia al padre
            Nodo anterior = null;
            //utilizo otro variable auxiliar que va recorriendo los nodos,
            //desde la raiz
            Nodo aux=raiz;
            while(aux !=null){
                anterior = aux;
                if (dato.esMenor(aux.getValor()))
                    aux = aux.getIzquierdo();
                else if (dato.esMayor(aux.getValor()))
                    aux = aux.getDerecho();
                else
                    throw new Exception("Dato duplicado");

            }
            //es en este punto en donde se enlaza el nuevo nodo
            if (dato.esMenor(anterior.getValor()))
                anterior.setIzquierdo(nuevo);
            else
                anterior.setDerecho(nuevo);
        }

    }
    /*
    esta es la interfaz de la operación, llama al método
    insertarRecursivo que realiza la operacion y devuelve la raiz del nuevo






     */
    public void insertar2(Object valor) throws Exception{
       Comparable dato = (Comparable) valor;
       raiz = insertarRec(raiz,dato);

    }
    /*
    versión recursiva para inserta el nuevo nodo
     */
    private Nodo insertarRec(Nodo raizSub, Comparable dato) throws Exception{
        if (raizSub == null){
            //caso base, termina la recursividad
            raizSub = new Nodo(dato);
        }else{
            if(dato.esMenor(raizSub.getValor())){
                Nodo iz = insertarRec(raizSub.getIzquierdo(), dato);
                raizSub.setIzquierdo(iz);

            }else{
                if(dato.esMayor(raizSub.getValor())){
                    Nodo dr = insertarRec(raizSub.getDerecho(), dato);
                    raizSub.setDerecho(dr);
                }
                else{
                    throw new Exception("Nodo Duplicado");
                }
            }
        }
        return raizSub;

    }
    /*
    búsqueda version iterativa, que regresa en nodo buscado en caso de 
    encontrarlo y null en caso contrario
     */
    public Nodo buscar(Object valor){
        Comparable dato = (Comparable) valor;
        if (raiz == null){
            return null;
        }else{
            //aux: auxiliar que va recorriendo el arbol, desde la raiz
            Nodo aux = raiz;
            while(aux !=null){
                if(dato.esIgual(aux.getValor()))
                    return aux;
                if(dato.esMenor(aux.getValor()))
                    aux = aux.getIzquierdo();
                else    
                    aux = aux.getDerecho();
            }
            return null;
        }


    }
    /*
    interfaz de buscar que invoca al método recursivo
     */
    public Nodo buscar2(Object valor){

    }
    /*
    método recursivo
     */
    private Nodo localizar(Nodo raizSub, Comparable buscado){

    }
    /*
     * invocamos a los métodos recursivos
     */
    public void preorden() {
        preorden(raiz);

    }

    public void inorden() {
        inorden(raiz);
    }

    public void postorden() {
        postorden(raiz);
    }

    // Recorridos
    // Recursiva
    private void preorden(Nodo aux) {
        if (aux != null) {
            visitar(aux);
            preorden(aux.getIzquierdo());
            preorden(aux.getDerecho());
        }

    }

    private void inorden(Nodo aux) {
        if (aux != null) {
            inorden(aux.getIzquierdo());
            visitar(aux);
            inorden(aux.getDerecho());
        }

    }

    private void postorden(Nodo aux) {
        if (aux != null) {
            postorden(aux.getIzquierdo());
            postorden(aux.getDerecho());
            visitar(aux);
        }

    }
    // Recorrido
    // iterativos

    /*
     * el método visitar depende de la aplicación. Si simplemente se
     * quieren listar los nodos por consola, se implementaria asi...
     */
    private void visitar(Nodo aux) {
        System.out.print(aux.getValor() + " ");
    }

    /*
     * Recorrido preordenIterativo
     */
    public void preordenIterativo() {
        Pila<Nodo> pila = new Pila<>();
        pila.apilar(raiz);
        while (!pila.esVacia()) {
            Nodo aux = pila.cima();
            visitar(aux);
            pila.retirar();
            if (aux.getDerecho() != null) {
                pila.apilar(aux.getDerecho());
            }
            if (aux.getIzquierdo() != null) {
                pila.apilar(aux.getIzquierdo());
            }
        }
    }

    /*
     * Recorrido inorden iterativos
     */
    public void inordenIterativo() {
        Pila<Nodo> pila = new Pila<>();
        pila.apilar(raiz);
        Nodo aux = raiz.getIzquierdo();
        while (aux != null || !pila.esVacia()) {
            if (aux != null) {
                pila.apilar(aux);
                aux = aux.getIzquierdo();
            } else {
                aux = pila.cima();
                pila.retirar();
                visitar(aux);
                aux = aux.getDerecho();

            }

        }
    }

    /*
     * Recorrido postOrden Iterativos
     */
    public void postordenIterativo() {
        Pila<Nodo> pila = new Pila<>();
        Nodo aux = raiz;
        Nodo q = raiz;
        while (aux != null) {
            // avanza por la izquiera y apila los nocos
            while (aux.getIzquierdo() != null) {
                pila.apilar(aux);
                aux = aux.getIzquierdo();
            }
            while (aux != null && (aux.getDerecho() == null || aux.getDerecho() == q)) {
                visitar(aux);
                q = aux;
                if (pila.esVacia()) {
                    return;
                }
                aux = pila.cima();
                pila.retirar();

            }
            pila.apilar(aux);
            aux = aux.getDerecho();
        }
    }

    /*
     * Recorrido por niveles
     */
    public void recorridoPorNiveles() {
        Cola<Nodo> cola = new Cola<>();
        cola.encolar(raiz);
        while (!cola.esVacia()) {
            Nodo aux = cola.frente();
            visitar(aux);
            if (aux.getIzquierdo() != null) {
                cola.encolar(aux.getIzquierdo());
            }
            if (aux.getDerecho() != null) {
                cola.encolar(aux.getDerecho());
            }
            cola.desencolar();

        }


      }
    
    
}
