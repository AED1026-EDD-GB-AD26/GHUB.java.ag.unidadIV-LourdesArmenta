package miPrincipal;
import arbolBinario.ArbolBinario;
import arbolBinario.ArbolBinarioBusqueda;
import arbolBinario.BTreePrinter;
import arbolBinario.Nodo;
import arbolBinario.Estudiante;
import arbolBinario.Entero;

public class Principal {
    
    public static void main(String[] args) throws Exception {
        ArbolBinario ab = new ArbolBinario();
        Nodo nodoA = new Nodo("A");
        Nodo nodoB = new Nodo("B");
        Nodo nodoC = new Nodo("C");
        Nodo nodoD = new Nodo("D");
        Nodo nodoE = new Nodo("E");
        Nodo nodoF = new Nodo("F");
        Nodo nodoG = new Nodo("G");
        ab.setRaiz(nodoA);
        ab.getRaiz().setIzquierdo(nodoB);
        ab.getRaiz().setDerecho(nodoC);
        nodoB.setIzquierdo(nodoD);
        nodoB.setDerecho(nodoE);
        nodoC.setIzquierdo(nodoF);
        nodoC.setDerecho(nodoG);
        //imprimir lo recorridps
        System.out.println("Recorrido preOrden Recursivo:");
        ab.preorden();
        System.out.println("\nRecorrido preOrden Iteraativo:");
        ab.preordenIterativo();
        System.out.println("\nRecorrido inOrden Recursivo:");
        ab.inorden();
        System.out.println("\nRecorrido inOrden Iterativo:");
        ab.inordenIterativo();
        System.out.println("\nRecorrido postOrden Recursivo:");
        ab.postorden();
        System.out.println("\nRecorrido postOrden Iterativo:");
        ab.postordenIterativo();
        System.out.println("\nRecorrido por niveles:");
        ab.recorridoPorNiveles();
        System.out.println();
        Estudiante est1 = new Estudiante(567, "Ana");
        Estudiante est2 = new Estudiante(123, "Berta");
        if(est1.esMayor(est2)){
            System.out.println(est1+" es mayor que "+est2);
        }
        Entero e1 = new Entero(5);
        Entero e2 = new Entero(10);
        if(e1.esMenor(e2)){
            System.out.println(e1+" es menor que "+e2);
        }

        // Arbol ordenado de estudiantes por codigo.
        Estudiante est3 = new Estudiante(789, "Carlos");
        Estudiante est4 = new Estudiante(45, "Diana");
        Estudiante est5 = new Estudiante(250, "Elena");
        Estudiante est6 = new Estudiante(650, "Fernando");
        Estudiante est7 = new Estudiante(900, "Gabriela");
        Estudiante est8 = new Estudiante(300, "Hugo");

        ArbolBinarioBusqueda arbolOrdenado = new ArbolBinarioBusqueda();
        arbolOrdenado.insertar(est1);
        arbolOrdenado.insertar(est2);
        arbolOrdenado.insertar(est3);
        arbolOrdenado.insertar(est4);
        arbolOrdenado.insertar(est5);
        arbolOrdenado.insertar(est6);
        arbolOrdenado.insertar(est7);
        arbolOrdenado.insertar(est8);

        System.out.println("Arbol ordenado (recorrido inorden):");
        arbolOrdenado.inorden();
        System.out.println();
        System.out.println("Arbol ordenado de estudiantes:");
        BTreePrinter.printNode(arbolOrdenado.getRaiz());
    }
}