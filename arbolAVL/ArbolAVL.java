package arbolAVL;
import pila.Pila;
import cola.Cola;
/**
 * Representa un árbol binario ordenado. Tiene las operaciones
 * básicas: insertar, eliminar, buscar y recorridos
 */

 public class ArbolAVL  {
	/**
	 * Raiz del arbol
	 */
	private Nodo raiz;
	
	/**
	 * Constructor por defecto
	 */
	
	public ArbolAVL() {
		raiz = null;
	}
	public Nodo getRaiz() {
		return raiz;
	}
	
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
	
	
	
		//Implementación recursiva de los recorridos
		/**
		 * Invoca al método recursivo
		 */
		public void preorden() {
			preorden(raiz);
		}
		/**
		 * 
		 * @param aux
		 */
		private void preorden(Nodo aux) {
			if(aux !=null) {
				visitar(aux);
				preorden(aux.getIzquierdo());
				preorden(aux.getDerecho());
			}
		}
		private void visitar(Nodo aux) {
			System.out.print(aux.getValor()+" ");
		}
		public String preorden2() {
			return preorden2(raiz,"");
		}
		/**
		 * 
		 * @param aux
		 */
		private String preorden2(Nodo aux,String cadena) {
			if(aux !=null) {
				cadena=cadena+visitar2(aux);
				cadena=preorden2(aux.getIzquierdo(),cadena);
				cadena=preorden2(aux.getDerecho(),cadena);
			}
			return cadena;
		}
		private String visitar2(Nodo aux) {
			return aux.getValor()+",";
			
		}
		/**
		 * Invoca al método recursivo
		 */
		public void inorden() {
			inorden(raiz);
		}
		/**
		 * 
		 * @param aux referencia a un nodo
		 */
		private void inorden(Nodo aux) {
			if(aux !=null) {
				inorden(aux.getIzquierdo());
				visitar(aux);
				inorden(aux.getDerecho());
			}
		}
		/**
		 * Invoca al método recursivo
		 */
		public void postorden() {
			postorden(raiz);
		}
		/**
		 * 
		 * @param aux referencia a un nodo
		 */
		
		private void postorden(Nodo aux) {
			if(aux !=null) {
				postorden(aux.getIzquierdo());
				postorden(aux.getDerecho());
				visitar(aux);
			}
			
			
		}
		
	
	
	/**
	 * versión recursiva
	 * Inserta un elemento en el árbol AVL y llama al método
	 * recursivo insertarAVL
	 * @param valor a insertar
	 * @throws Exception
	 */
	public void insertar(Object valor) throws Exception{
		if (!(valor instanceof Comparable))
			throw new Exception("El valor debe implementar arbolAVL.Comparable");
		Comparable dato = (Comparable) valor;
		Logical h = new Logical(false);//intercambio un valor booleano
		raiz = insertarAVL(raiz,dato,h);
	}
	/**
	 * Método Recursivo
	 * @param raizSub
	 * @param dato
	 * @param h
	 * @return Nodo
	 * @throws Exception
	 */
	private Nodo insertarAVL(Nodo raiz, Comparable dt, Logical h) throws Exception{
		Nodo n1;
		if (raiz == null) {
			//Caso Base, termina la recursividad
			raiz = new Nodo(dt);
			h.setLogical(true);
		}
		else if(dt.esMenor(raiz.getValor())) {
				Nodo iz = insertarAVL(raiz.getIzquierdo(),dt,h);
				raiz.setIzquierdo(iz);
				//Regreso por los nodos del camino de búsqueda
				if(h.booleanValue()) {
					//decrementa el fe por aumentar la altura de rama izquierda
					switch(raiz.getFe()) {
					case 1:
						raiz.setFe(0);
						h.setLogical(false);
						break;
					case 0:
						raiz.setFe(-1);
						break;
					case -1: //Aplicar la rotación a la izquierda
						n1=(Nodo)raiz.getIzquierdo();
						if(n1.getFe()==-1)
							raiz=rotacionII(raiz,n1);
						else
							raiz=rotacionID(raiz,n1);
						h.setLogical(false);
						
					}
				}
			}
		    else if(dt.esMayor(raiz.getValor())) {
				Nodo dr = insertarAVL(raiz.getDerecho(),dt,h);
				raiz.setDerecho(dr);
				//Regreso por los nodos del camino de búsqueda
				if(h.booleanValue()) {
					//decrementa el fe por aumentar la altura de rama derecha
					switch(raiz.getFe()) {
					case 1: //Aplicar rotación a la derecha
						n1=(Nodo)raiz.getDerecho();
						if(n1.getFe()==+1)
							raiz=rotacionDD(raiz,n1);
						else
							raiz=rotacionDI(raiz,n1);
						h.setLogical(false);
						break;							
					case 0:
						raiz.setFe(+1);
						break;
					case -1: 
						raiz.setFe(0);
						h.setLogical(false);							
							
					}
				}
			}
		    else 
				//Dato duplicado
					throw new Exception("Nodo Duplicado");
		return raiz;
	}
	
	/**
	 *  Version iterativa
	 * @param valor a buscar
	 * @return Nodo buscado o Null si no lo encuentra
	 */
	public Nodo buscar(Object valor) {
		if (!(valor instanceof Comparable))
			return null;
		Comparable dato = (Comparable)valor;
		if(raiz ==null)
			return raiz;
		else {
			//aux: auxiliar que va recorriendo los nodos, desde la raiz
			Nodo aux = raiz;
			while (aux !=null) {
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
	
	/**
	 * Version Recursiva
	 * @param Elemento Buscado
	 * @return Nodo si lo encuentra y si no Null
	 */
	public Nodo buscar2(Object buscado) {
		if (!(buscado instanceof Comparable))
			return null;
		Comparable dato = (Comparable)buscado;
		if (raiz ==null)
			return null;
		else
			return localizar(raiz, dato);
	}
	
	public Nodo localizar(Nodo raizSub, Comparable buscado) {
		if (raizSub ==null)
			return null;
		else if(buscado.esIgual(raizSub.getValor()))
			return raizSub;
		else if(buscado.esMenor(raizSub.getValor()))
			return localizar(raizSub.getIzquierdo(),buscado);
		else
			return localizar(raizSub.getDerecho(),buscado);
	}
	/**
	 * 
	 * @param valor a eliminar
	 * @throws Exception
	 */
	public void eliminar(Object valor) throws Exception{
		if (!(valor instanceof Comparable))
			throw new Exception("El valor debe implementar arbolAVL.Comparable");
		Comparable dato;
		dato = (Comparable) valor;
		Logical flag = new Logical(false);
		raiz = eliminarAVL(raiz,dato,flag);
		
	}
	/**
	 * Elimina un nodo y rebalancea la rama AVL.
	 * @param r raiz de la subrama
	 * @param clave elemento a eliminar
	 * @param cambioAltura indica si debe propagarse el cambio de altura
	 * @return raiz actualizada de la subrama
	 * @throws Exception
	 */
	
	public Nodo eliminarAVL(Nodo r, Comparable clave, Logical cambioAltura)
			throws Exception{
		if(r==null) {
			throw new Exception("Nodo no encontrado");
		}
		else if(clave.esMenor(r.getValor())) {
			Nodo iz = eliminarAVL(r.getIzquierdo(),clave,cambioAltura);
			r.setIzquierdo(iz);
		}
		else if(clave.esMayor(r.getValor())) {
			Nodo dr = eliminarAVL(r.getDerecho(),clave,cambioAltura);
			r.setDerecho(dr);
		}
		else //Nodo encontrado
		{
			if(r.getIzquierdo()==null) {
				r=r.getDerecho();
				cambioAltura.setLogical(true);
			}
			else if(r.getDerecho()==null) {
				r=r.getIzquierdo();
				cambioAltura.setLogical(true);
			}
			else {
				Nodo sucesor = r.getDerecho();
				while(sucesor.getIzquierdo()!=null)
					sucesor=sucesor.getIzquierdo();
				r.setValor(sucesor.getValor());
				r.setDerecho(eliminarAVL(r.getDerecho(),sucesor.getValor(),cambioAltura));
			}
		}
		return r==null ? null : rebalancearAVL(r,cambioAltura);
	}
		
	private int altura(Nodo n) {
		if(n==null)
			return 0;
		return 1+Math.max(altura(n.getIzquierdo()),altura(n.getDerecho()));
	}

	private void actualizarFE(Nodo n) {
		if(n!=null)
			n.setFe(altura(n.getDerecho())-altura(n.getIzquierdo()));
	}

	/**
	 * Actualiza el factor de equilibrio y aplica la rotación necesaria.
	 * @param n raiz de la subrama a rebalancear
	 * @param cambioAltura indica si debe propagarse el cambio de altura
	 * @return raiz rebalanceada
	 */
	private Nodo rebalancearAVL(Nodo n, Logical cambioAltura) {
		actualizarFE(n);

		if(n.getFe()>1) {
			Nodo derecho=n.getDerecho();
			actualizarFE(derecho);
			if(derecho!=null && derecho.getFe()<0)
				n=rotacionDI(n,derecho);
			else
				n=rotacionDD(n,derecho);
		}
		else if(n.getFe()<-1) {
			Nodo izquierdo=n.getIzquierdo();
			actualizarFE(izquierdo);
			if(izquierdo!=null && izquierdo.getFe()>0)
				n=rotacionID(n,izquierdo);
			else
				n=rotacionII(n,izquierdo);
		}

		actualizarFE(n);
		actualizarFE(n.getIzquierdo());
		actualizarFE(n.getDerecho());
		cambioAltura.setLogical(true);
		return n;
	}
    

    //Recorrido Iterativo en Preorden, utilizando una pila
    public void preordenIterativo(){
		if (raiz == null) {
            return;
        }
        Pila<Nodo> pila = new Pila<Nodo>();
        pila.apilar(raiz);
        while (!pila.esVacia()){
            Nodo aux = pila.cima();
            visitar(aux);
            pila.retirar();
            if(aux.getDerecho() !=null)
               pila.apilar(aux.getDerecho());
            if(aux.getIzquierdo() !=null)
               pila.apilar(aux.getIzquierdo());
        }
    }

    //Recorrido  Iterativo en InOrden, utilizando una pila
    public void inordenIterativo(){
		if (raiz == null) {
            return;
        }
        Pila<Nodo> pila = new Pila<Nodo>();
        pila.apilar(raiz);
        Nodo aux = raiz.getIzquierdo();
        while(aux != null || !pila.esVacia()){
            if(aux !=null){
                pila.apilar(aux);
                aux = aux.getIzquierdo();
            }else{
                aux = pila.cima();
                pila.retirar();
                visitar(aux);
                aux = aux.getDerecho();
            }
        }

    }

    //Recorrido Iterativo en PostOrden, utilizando una pila
    public void postordenIterativo(){
        if (raiz == null) {
            return;
        }
        Pila<Nodo> pila = new Pila<Nodo>();
        Nodo aux = raiz;
        Nodo q = raiz;
        while(aux !=null){
            //avanza por la izquierda y apila los nodos
            while(aux.getIzquierdo() !=null){
                pila.apilar(aux);
                aux = aux.getIzquierdo();
            }
            while (aux !=null && (aux.getDerecho() == null || aux.getDerecho() == q)){
                visitar(aux);
                q = aux;
                if (pila.esVacia())
                  return;

                aux = pila.cima();
                pila.retirar();
            }
            pila.apilar(aux);
            aux = aux.getDerecho();
        }
    }
    public void recorridoPorNivel(){
		if (raiz == null) {
            return;
        }
        Cola<Nodo> cola = new Cola<Nodo>();
        cola.encolar(raiz);
        while(!cola.esVacia()){
            Nodo aux = cola.frente();
            visitar(aux);
            
            if(aux.getIzquierdo()!=null)
               cola.encolar(aux.getIzquierdo());
            if(aux.getDerecho() !=null)
               cola.encolar(aux.getDerecho());
            cola.desencolar();

        }

    }



}