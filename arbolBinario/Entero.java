package arbolBinario;

public class Entero implements Comparable{
    //atributo
    private int dato;
    //constructores

    public Entero(int dato) {
        this.dato = dato;
    }
    //getter y setter

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return dato + " ";
    }

    @Override
    public boolean esIgual(Object q) {
       Entero obj = (Entero) q;
       return dato == obj.getDato();
    }

    @Override
    public boolean esMayor(Object q) {
        Entero obj = (Entero) q;
       return dato > obj.getDato();
    }

    @Override
    public boolean esMenor(Object q) {
        Entero obj = (Entero) q;
       return dato < obj.getDato();
    }
    
    
}
