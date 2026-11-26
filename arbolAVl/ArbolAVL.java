package arbolAVl;

public class ArbolAVL {
    // atributos
    private Nodo raiz;

    // constructors
    public ArbolAVL() {
        raiz = null;
    }

    /**
     * ROTACIONES
     * 
     * @param n
     * @param n1
     * @return
     */

    private Nodo rotacionII(Nodo n, Nodo n1) {
        n.setIzquierdo(n1.getDerecho());
        n1.setDerecho(n);
        // Actualizacion de los factores de equilibrio
        if (n1.getFe() == -1) { // Se cumple en la insercion
            n.setFe(0);
            n1.setFe(0);
        } else {
            n.setFe(-1);
            n1.setFe(+1);
        }
        return n1;
    }

    /**
     * Rotación simple:derecha-derecha
     * 
     * @param n  referencia al nodo problema con fe +2
     * @param n1 referencia al nodo de su rama derecha
     * @return
     */

    private Nodo rotacionDD(Nodo n, Nodo n1) {
        n.setDerecho(n1.getIzquierdo());
        n1.setIzquierdo(n);
        // Actualización de factores de equilibrio
        if (n1.getFe() == +1) {// se cumple en la inserción
            n.setFe(0);
            n1.setFe(0);

        } else {
            n.setFe(+1);
            n1.setFe(-1);
        }
        return n1;
    }

    /**
     * Rotación doble:derecha-derecha
     * 
     * @param n  referencia al nodo problema con fe -2
     * @param n1 referencia al nodo de su rama izquierda
     * @return
     */

    private Nodo rotacionID(Nodo n, Nodo n1) {
        Nodo n2;
        n2 = (Nodo) n1.getDerecho();
        n.setIzquierdo(n2.getDerecho());
        n2.setDerecho(n);
        n1.setDerecho(n2.getIzquierdo());
        n2.setIzquierdo(n1);
        // Actualización de los factores de equillibrio
        if (n2.getFe() == +1)
            n1.setFe(-1);
        else
            n1.setFe(0);
        if (n2.getFe() == -1)
            n.setFe(1);
        else
            n.setFe(0);
        n2.setFe(0);
        return n2;

    }

    /**
     * Rotación doble:derecha-izquierda
     * 
     * @param n  referencia al nodo problema con fe -2
     * @param n1 referencia al nodo de su rama izquierda
     * @return
     */

    private Nodo rotacionDI(Nodo n, Nodo n1) {
        Nodo n2;
        n2 = (Nodo) n1.getIzquierdo();
        n.setDerecho(n2.getIzquierdo());
        n2.setIzquierdo(n);
        n1.setIzquierdo(n2.getDerecho());
        n2.setDerecho(n1);
        // Actualización de los factores de equillibrio
        if (n2.getFe() == +1)
            n.setFe(-1);
        else
            n.setFe(0);
        if (n2.getFe() == -1)
            n1.setFe(1);
        else
            n1.setFe(0);
        n2.setFe(0);
        return n2;

    }

    /**
     * Inserta un elemento en un arbol avl, llama al método recursivo insertarAVL
     * 
     * @param valor: valor a insertar
     * @throws Exception
     */
    public void insertar(Object valor) throws Exception {
        Comparable dato;
        dato = (Comparable) valor;
        Logical h = new Logical(false);
        raiz = insertarAVL(raiz, dato, h);

    }

    /**
     * Metodo Recursivo
     * 
     * @param raiz, raiz
     * @param dt,   dato
     * @param d
     * @return Nodo
     * @throws Exception
     * 
     */
    private Nodo insertarAVL(Nodo raiz, Comparable dt, Logical h) throws Exception {
        Nodo n1;
        if (raiz == null) {
            // Caso Base, termina la recursividad
            raiz = new Nodo(dt);
            h.setLogical(true);
        } else if (dt.esMenor(raiz.getValor())) {
            Nodo iz = insertarAVL(raiz.getIzquierdo(), dt, h);
            raiz.setIzquierdo(iz);
            // Regreso por los nodos del camino de búsqueda
            if (h.booleanValue()) {
                // decrementa el fe por aumentar la altura de rama izquierda
                switch (raiz.getFe()) {
                    case 1:
                        raiz.setFe(0);
                        h.setLogical(false);
                        break;
                    case 0:
                        raiz.setFe(-1);
                        break;
                    case -1: // Aplicar la rotación a la izquierda
                        n1 = (Nodo) raiz.getIzquierdo();
                        if (n1.getFe() == -1)
                            raiz = rotacionII(raiz, n1);
                        else
                            raiz = rotacionID(raiz, n1);
                        h.setLogical(false);

                }
            }
        } else if (dt.esMayor(raiz.getValor())) {
            Nodo dr = insertarAVL(raiz.getDerecho(), dt, h);
            raiz.setDerecho(dr);
            // Regreso por los nodos del camino de búsqueda
            if (h.booleanValue()) {
                // decrementa el fe por aumentar la altura de rama derecha
                switch (raiz.getFe()) {
                    case 1: // Aplicar rotación a la derecha
                        n1 = (Nodo) raiz.getDerecho();
                        if (n1.getFe() == +1)
                            raiz = rotacionDD(raiz, n1);
                        else
                            raiz = rotacionDI(raiz, n1);
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
        } else
            // Dato duplicado
            throw new Exception("Nodo Duplicado");
        return raiz;
    }
    /**
     * Eliminar un nodo de un arbol AVL
     * @param valor valor a eliminar
     * @throws Exception
     */
    public void eliminar(Object valor) throws Exception{
        Comparable dato;
        dato = (Comparable)valor;
        Logical flag = new Logical(false);
        raiz = eliminarAVL(raiz,dato,flag);
    }
    /**
     * Método recursivo para eliminar un nodo
     * @param r nodo raiz
     * @param clave elemento clave a borrar
     * @param cambioAltura tipo LOgical
     * @return
     * @throws Exception
     */
    private Nodo eliminarAVL(Nodo r, Comparable clave, Logical cambioAltura) throws Exception{
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
		//return r==null ? null : rebalancearAVL(r,cambioAltura);
        if (r==null){
            return null;
        }else{
            rebalancearAVL(r,cambioAltura);
        }
        
	
    }


}
