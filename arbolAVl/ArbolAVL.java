package arbolAVl;

public class ArbolAVL {
    //atributos
    private Nodo raiz;
    //constructors
    public ArbolAVL() {
        raiz = null;
    }
    //ROTACIONES
    /**
	 * ROTACIONES
	 * @param n
	 * @param n1
	 * @return
	 */
	
	private Nodo rotacionII(Nodo n, Nodo n1) {
		n.setIzquierdo(n1.getDerecho());
		n1.setDerecho(n);
		//Actualizacion de los factores de equilibrio
		if(n1.getFe()==-1){ //Se cumple en la insercion
			n.setFe(0);
			n1.setFe(0);
		}
		else {
			n.setFe(-1);
			n1.setFe(+1);
		}
		return n1;
	}
	
	
	/**
	 * Rotación simple:derecha-derecha
	 * @param n referencia al nodo problema con fe +2
	 * @param n1 referencia al nodo de su rama derecha
	 * @return
	 */
	
	private Nodo rotacionDD(Nodo n, Nodo n1) {
		n.setDerecho(n1.getIzquierdo());
		n1.setIzquierdo(n);
		//Actualización de factores de equilibrio
		if(n1.getFe()==+1) {// se cumple en la inserción
			n.setFe(0);
			n1.setFe(0);
			
		}
		else {
			n.setFe(+1);
			n1.setFe(-1);
		}
		return n1;
	}
	
	
	/**
	 * Rotación doble:derecha-derecha
	 * @param n referencia al nodo problema con fe -2
	 * @param n1 referencia al nodo de su rama izquierda
	 * @return
	 */
	
	private Nodo rotacionID(Nodo n, Nodo n1) {
		Nodo n2;
		n2 = (Nodo)n1.getDerecho();
		n.setIzquierdo(n2.getDerecho());
		n2.setDerecho(n);
		n1.setDerecho(n2.getIzquierdo());
		n2.setIzquierdo(n1);
		//Actualización de los factores de equillibrio
		if(n2.getFe()==+1)
			n1.setFe(-1);
		else
			n1.setFe(0);
		if(n2.getFe()==-1)
			n.setFe(1);
		else
			n.setFe(0);
		n2.setFe(0);
		return n2;
		
	}
	
	
	/**
	 * Rotación doble:derecha-izquierda
	 * @param n referencia al nodo problema con fe -2
	 * @param n1 referencia al nodo de su rama izquierda
	 * @return
	 */
	
	private Nodo rotacionDI(Nodo n, Nodo n1) {
		Nodo n2;
		n2 = (Nodo)n1.getIzquierdo();
		n.setDerecho(n2.getIzquierdo());
		n2.setIzquierdo(n);
		n1.setIzquierdo(n2.getDerecho());
		n2.setDerecho(n1);
		//Actualización de los factores de equillibrio
		if(n2.getFe()==+1)
			n.setFe(-1);
		else
			n.setFe(0);
		if(n2.getFe()==-1)
			n1.setFe(1);
		else
			n1.setFe(0);
		n2.setFe(0);
		return n2;
		
	}
    
    
}
