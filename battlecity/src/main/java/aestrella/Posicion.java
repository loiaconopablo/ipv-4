package aestrella;

import battlecity.Pasto;

import com.uqbar.vainilla.GameComponent;

public class Posicion implements Comparable{
	
	private int x;
	private int y;
	private GameComponent elemento;
	
   // private boolean transitable; //Indica si el nodo puede ser visitado o accedido. Es el puedePasar
    private int coste; //Coste extra del nodo. Por ejemplo, sería más costoso caminar sobre el barro que sobre tierra firme.
    private int F; //Valor total del nodo. F = G + H + coste
    private int G; //Valor desde el nodo hasta el nodo inicial.  Las diagonales suman 14 y las ortogonales 10.
    private int H;
    /**
     * Valor desde el nodo hasta el nodo final.
     * Se considera el peor caso, en el no se pueden hacer diagonales, por lo
     * tanto, H + 10*(diferencia de filas + diferencia de columnas entre el nodo
     * y el nodo final).
     */
    private Posicion nodoPadre; //Referencia al nodo padre. Necesario para calcular G.
    private Posicion nodoFinal; //Referencia al nodo final. Necesario para calcular H.
		
	public Posicion(int x, int y, GameComponent elemento){
		this.setX(x);
		this.setY(y);
		this.setElemento(elemento);
		this.coste = 0;
		this. F = 0;
       this.G = 0;
        this.H = 0;
        this.nodoPadre = null;
        this.nodoFinal = null;
	}
	
	/**
     * Compara dos nodos según su valor de F.
     * @param objeto Nodo con el que se va a comparar el nodo que invocó el
     * método.
     * @return Devuelve 1 si el nodo que invocó el método tiene menor F,
     * devuelve 0 si son iguales o -1 en otro caso.
     */
    public int compareTo(Object objeto)
    {
            if (F > ((Posicion) objeto).F) return 1;
            else if (F < ((Posicion) objeto).F) return -1;
            else return 0;
    }
    
    /**
     * Recalcula el valor de F. Normalmente, cuando G, H o coste han cambiado.
     */
    private void recalcularF()
    {
            F = G + H + coste;
    }
	
    /**
     * Recalcula el valor de G. Normalmente, cuando el padre se ha modificado.
     */
    private void recalcularG()
    {
            G = nodoPadre.G;
            if (x==nodoPadre.x || y==nodoPadre.y)
                    G += 10;
            else
                    G += 14;
            recalcularF();
    }

    /**
     * Recalcula el valor de H. Normalmente, cuando el nodo final ha cambiado.
     */
    private void recalcularH()
    {
            if (nodoFinal != null)
            {
                    H = (Math.abs(x-nodoFinal.x) + Math.abs(y-nodoFinal.y))*10;
            }
            else
            {
                    H = 0;
            }
            recalcularF();
    }

	/**
     * Genera una cadena de caracteres con la posición del nodo (fila, columna).
     * @return Devuelve una cadena con la posición (fila, columna).
     */
    @Override
    public String toString()
    {
            return "(" + x + ", " + y + ")";
    }
    
	public void setNodoPadre(Posicion nodoPadre) {
		this.nodoPadre = nodoPadre;
		this.recalcularG();
	}

	 public boolean getTransitable()
     {
             return this.puedePasar();
     }
	 
	public Posicion getNodoFinal() {
		return nodoFinal;
	}

	public void setNodoFinal(Posicion nodoFinal) {
		this.nodoFinal = nodoFinal;
		this.recalcularH();
	}

	public GameComponent getElemento() {
		return elemento;
	}
	public void setElemento(GameComponent elemento) {
		this.elemento = elemento;
	}
	
	public boolean puedePasar(){
		if(this.getElemento() == null || this.getElemento().getClass() == Pasto.class)
			return true;
		else {return false;}
	}
	

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
		this.recalcularF();
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public Posicion getNodoPadre() {
		return nodoPadre;
	}


	

}
