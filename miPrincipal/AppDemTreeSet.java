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
        System.out.println(t.contains(5));
        System.out.println(t.contains(12));
        System.out.println();
        for(Iterator<Integer> it = t.iterator();it.hasNext();){
            System.out.println(it.next());
        }
        t.remove(5);
         for(Iterator<Integer> it = t.iterator();it.hasNext();){
            System.out.println(it.next());
        }

    }
    
}
