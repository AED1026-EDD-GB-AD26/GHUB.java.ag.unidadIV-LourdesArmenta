package miPrincipal;

import java.util.TreeSet;
import java.util.Iterator;

public class AppDemTreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> t = new TreeSet<>();
        t.add(5);
        t.add(3);
        t.add(9);
        t.add(2);
        
        System.out.println("El arbol contiene el elemento 5?" + t.contains(5));
        System.out.println("El arbol contiene el elemento 12?" + t.contains(12));
        System.out.println();
        System.out.println("Recorremos con Iterador:");
        for (Iterator<Integer> it = t.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
        t.remove(5);
        System.out.println("Recorremos con Iterador después de la eliminación:");
        for (Iterator<Integer> it = t.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }

        System.out.println("Inorden (Ascendente):");
        for (Integer valor : t) {
            System.out.print(valor + " ");
        }
        // 2 3  9
        System.out.println("\nDesendente:");
        System.out.println(t.descendingSet());
        // [9,  3, 2]

    }

}
