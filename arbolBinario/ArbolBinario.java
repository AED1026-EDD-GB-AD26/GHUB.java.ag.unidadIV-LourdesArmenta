package arbolBinario;

import pila.Pila;
import cola.Cola;

public class ArbolBinario {
    // atributo
    protected Nodo raiz;

    // constructores
    public ArbolBinario() {
        raiz = null;
    }

    public ArbolBinario(Nodo raiz) {
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
