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

    // getter
    public Nodo getRaiz() {
        return raiz;
    }

    // setter
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    // Operaciones
    /*
     * Inserta un nodo en el árbol binario
     * 
     */
    public void insertar(Object valor) throws Exception {
        Comparable dato = (Comparable) valor;
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            // utilizo una varible auxiliar que hace referencia al padre
            Nodo anterior = null;
            // utilizo otro variable auxiliar que va recorriendo los nodos,
            // desde la raiz
            Nodo aux = raiz;
            while (aux != null) {
                anterior = aux;
                if (dato.esMenor(aux.getValor()))
                    aux = aux.getIzquierdo();
                else if (dato.esMayor(aux.getValor()))
                    aux = aux.getDerecho();
                else
                    throw new Exception("Dato duplicado");

            }
            // es en este punto en donde se enlaza el nuevo nodo
            if (dato.esMenor(anterior.getValor()))
                anterior.setIzquierdo(nuevo);
            else
                anterior.setDerecho(nuevo);
        }

    }

    /*
     * esta es la interfaz de la operación, llama al método
     * insertarRecursivo que realiza la operacion y devuelve la raiz del nuevo
     */
    public void insertar2(Object valor) throws Exception {
        Comparable dato = (Comparable) valor;
        raiz = insertarRec(raiz, dato);

    }

    /*
     * versión recursiva para inserta el nuevo nodo
     */
    private Nodo insertarRec(Nodo raizSub, Comparable dato) throws Exception {
        if (raizSub == null) {
            // caso base, termina la recursividad
            raizSub = new Nodo(dato);
        } else {
            if (dato.esMenor(raizSub.getValor())) {
                Nodo iz = insertarRec(raizSub.getIzquierdo(), dato);
                raizSub.setIzquierdo(iz);

            } else {
                if (dato.esMayor(raizSub.getValor())) {
                    Nodo dr = insertarRec(raizSub.getDerecho(), dato);
                    raizSub.setDerecho(dr);
                } else {
                    throw new Exception("Nodo Duplicado");
                }
            }
        }
        return raizSub;

    }

    /*
     * búsqueda version iterativa, que regresa en nodo buscado en caso de
     * encontrarlo y null en caso contrario
     */
    public Nodo buscar(Object valor) {
        Comparable dato = (Comparable) valor;
        if (raiz == null) {
            return null;
        } else {
            // aux: auxiliar que va recorriendo el arbol, desde la raiz
            Nodo aux = raiz;
            while (aux != null) {
                if (dato.esIgual(aux.getValor()))
                    return aux;
                if (dato.esMenor(aux.getValor()))
                    aux = aux.getIzquierdo();
                else
                    aux = aux.getDerecho();
            }
            return null;
        }

    }

    /*
     * interfaz de buscar que invoca al método recursivo
     */
    public Nodo buscar2(Object valor) {
        Comparable dato = (Comparable) valor;
        if (raiz == null) {
            return null;
        } else {
            return localizar(raiz, dato);
        }

    }

    /*
     * implementacion iterativa para eliminar el nodo de un árbol
     */
    public boolean eliminar(Object valor) {
        Comparable dato = (Comparable) valor;
        // buscar el nodo eliminar y su antecesor
        Nodo antecesor = null; // antecesor del nodo a eliminar
        // aux: auxiliar que va recorriendo los nodos,desde la raiz
        Nodo aux = raiz;
        while (aux != null) {
            if (dato.esIgual(aux.getValor())) {
                break;
            }
            antecesor = aux;
            if (dato.esMenor(aux.getValor())) {
                aux = aux.getIzquierdo();
            } else {
                aux = aux.getDerecho();
            }
        }
        if (aux == null) {
            return false;
        }
        // si llega a este punto, el nodo a eliminar existe y esta en aux y
        // su antercesor es antecesor
        // Examinar cada caso:
        // 1.- Si el nodo a eliminar es la raiz
        //Sin hijos: asigna null a raiz.
        //Solo hijo derecho: ese hijo pasa a ser la raíz.
        //Solo hijo izquierdo: ese hijo pasa a ser la raíz.
        if (antecesor == null &&
                (aux.getIzquierdo() == null || aux.getDerecho() == null)) {
            if (aux.getIzquierdo() == null) {
                raiz = aux.getDerecho();
            } else {
                raiz = aux.getIzquierdo();
            }
            return true;
        }

        // 2.- Si tiene menos de dos hijos, incluso una hoja, reajustar los enlaces
        if (aux.getIzquierdo() == null) {
            // cuando llego aquí ya se que NO TIENE DOS HIJOS
            // Version anterior: if (((Comparable) aux.getValor()).esMenor(antecesor.getValor())) {
            // Compara los nodos directamente para saber que enlace actualizar.
            if (antecesor.getIzquierdo() == aux) {
                antecesor.setIzquierdo(aux.getDerecho());
            } else {
                antecesor.setDerecho(aux.getDerecho());
            }
        } else if (aux.getDerecho() == null) {
            // Version anterior: if (((Comparable) aux.getValor()).esMenor(antecesor.getValor())) {
            if (antecesor.getIzquierdo() == aux) {
                antecesor.setIzquierdo(aux.getIzquierdo());
            } else {
                antecesor.setDerecho(aux.getIzquierdo());
            }
        } else {
            // el nodo a eliminar tiene rama izquierda y rama derecha
            reemplazarPorMayorIzquierdo(aux);
        }
        aux = null;
        return true;
    }

    /*
     * Reemplazar el nodo actual, por el mayor de la rama izquierda
     */
    private void reemplazarPorMayorIzquierdo(Nodo act) {
        Nodo mayor = act;
        Nodo ant = act;
        mayor = act.getIzquierdo();
        // buscar el mayor de la rama izquiera
        // ant:es el antecesor de mayor
        while (mayor.getDerecho() != null) {
            ant = mayor; //AQUI TENIA UN ERROR, ANTES PONIA act
            mayor = mayor.getDerecho();
        }
        // cuando llego ya encontre el mayor
        act.setValor(mayor.getValor());// reemplazo
        // reajuste
        if (ant == act) {
            ant.setIzquierdo(mayor.getIzquierdo());
        } else {
            ant.setDerecho(mayor.getIzquierdo());
        }

    }

    /*
     * método recursivo
     */
    private Nodo localizar(Nodo raizSub, Comparable buscado) {
        // caso base
        if (raizSub == null) {
            return null;
        } else {
            if (buscado.esIgual(raizSub.getValor())) {
                return raizSub;
            } else if (buscado.esMenor(raizSub.getValor())) {
                return localizar(raizSub.getIzquierdo(), buscado);
            } else {
                return localizar(raizSub.getDerecho(), buscado);
            }
        }

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
        if (raiz == null) {
            return;
        }
        //EL CODIGO DE ARRIBA ES NUEVO PORQUE ANTES NO TENIA ESA VALIDACION, 
        // SI LA RAIZ ES NULL, NO HACE NADA
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
        if (raiz == null) {
            return;
        }
        //EL CODIGO DE ARRIBA ES NUEVO PORQUE ANTES NO TENIA ESA VALIDACION, 
        // SI LA RAIZ ES NULL, NO HACE NADA
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
        if (raiz == null) {
            return;
        }
        //EL CODIGO DE ARRIBA ES NUEVO PORQUE ANTES NO TENIA ESA VALIDACION, 
        // SI LA RAIZ ES NULL, NO HACE NADA
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
