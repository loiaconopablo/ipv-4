package aestrella;

import java.util.ArrayList;

/**
 * Implementación del algoritmo A* con Java.
 * Dada una matriz de nodos (Posicion) e indicando cuál es el nodo de salida
 * y el nodo de llegada, calcula el mejor camino posible que hay que seguir
 * evitando los obstáculos (es decir, nodos no transitables).
 */
public class Astar
{
        /**
         * Matriz con una serie de filas y una serie de columnas que representa el tablero sobre
         * el que se buscará el camino. Cada celda de la matriz es un nodo que puede
         * ser transitable o no y que tiene un coste particular (por ejemplo, sería
         * más difícil caminar por el barro que por tierra seca).
         */
        private Posicion[][] matriz;

        /**
         * Nodo de partida que indica una posición (x,y) en la matriz de celdas.
         * No importa si es un nodo intransitable y tampoco se considera el coste.
         */
        private Posicion nodoInicial;

        /**
         * Nodo de llegada que indica una posición (x,y) en la matriz de celdas.
         * Debe ser un nodo transitable.
         */
        private Posicion nodoFinal;

        /**
         * Indica si el camino podrá seguir las diagonales aunque las ortogonales
         * no sean transitables.
         */
        private boolean forzar8conectado;

        /**
         * Constructor por defecto.
         * @param matriz Matriz con una serie de filas y una serie de columnas que representa el tablero sobre
         * el que se buscará el camino.
         * @param nodoInicial Nodo de partida que indica una posición (x,y) en la matriz de celdas.
         * @param nodoFinal Nodo de llegada que indica una posición (x,y) en la matriz de celdas.
         * @param forzar8conectado Indica si el camino podrá seguir las diagonales aunque las ortogonales no sean transitables.
         */
        public Astar (Posicion[][] matriz, Posicion nodoInicial, Posicion nodoFinal)
        {
                this.matriz = matriz;
                this.nodoInicial = nodoInicial;
                this.nodoFinal = nodoFinal;
                this.forzar8conectado = false;
        }

        /**
         * Ejecuta el algoritmo de A*, calculando el camino, si existe, desde el
         * punto de inicio hasta el punto final.
         * @return Devuelve una lista (no vacía) de nodos si existe el camino. Si no existe, devuelve null.
         */
        public ArrayList calcularCamino()
        {
                Deap listaAbierta = new Deap();
                ArrayList listaCerrada = new ArrayList<Posicion>();
                Posicion nodoActual = null;
                boolean caminoEncontrado = false;
                
                int filas = matriz.length;
                int columnas = 0;
                if (filas > 0)
                        columnas = matriz[0].length;
                
                // Añadimos el cuadro inicial a la lista abierta.
                listaAbierta.push(nodoInicial);

                int iteraciones = 0;
                // Buscamos el camino mientras queden nodos candidatos y no lo hayamos encontrado.
                while (!listaAbierta.isEmpty() && !caminoEncontrado)
                {
                        iteraciones++;
                        // Extraemos el nodo de menor F desde la lista abierta hacia la lista cerrada.
                        nodoActual = (Posicion) listaAbierta.popBottom();
                        listaCerrada.add(nodoActual);

                        // Extraemos los nodos adyacentes al nodo actual.
                        ArrayList nodosAdyacentes = new ArrayList<Posicion>();

                        boolean derecha = false, izquierda = false, arriba = false, abajo = false;
                        if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY() && nodoActual.getY() < filas)
                        {
                                if (matriz[nodoActual.getY()][nodoActual.getX()+1].getTransitable())
                                {
                                        nodosAdyacentes.add(matriz[nodoActual.getY()][nodoActual.getX()+1]);
                                        derecha = true;
                                }
                        }
                        if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY() && nodoActual.getY() < filas)
                        {
                                if (matriz[nodoActual.getY()][nodoActual.getX()-1].getTransitable())
                                {
                                        nodosAdyacentes.add(matriz[nodoActual.getY()][nodoActual.getX()-1]);
                                        izquierda = true;
                                }
                        }
                        if (0 <= nodoActual.getX() && nodoActual.getX() < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas)
                        {
                                if (matriz[nodoActual.getY()-1][nodoActual.getX()].getTransitable())
                                {
                                        nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()]);
                                        arriba = true;
                                }
                        }
                        if (0 <= nodoActual.getX() && nodoActual.getX() < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas)
                        {
                                if (matriz[nodoActual.getY()+1][nodoActual.getX()].getTransitable())
                                {
                                        nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()]);
                                        abajo = true;
                                }
                        }

                        // Sólo incluidos las diagonales si las ortogonales se han incluido previamente ya que para ser 8-conectado primero debe ser 4-conectado.
//                        if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas && (forzar8conectado || (arriba && derecha)))
//                                if (matriz[nodoActual.getY()-1][nodoActual.getX()+1].getTransitable())
//                                        nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()+1]);
//
//                        if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas && (forzar8conectado || (arriba && izquierda)))
//                                if (matriz[nodoActual.getY()-1][nodoActual.getX()-1].getTransitable())
//                                        nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()-1]);
//
//                        if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas && (forzar8conectado || (abajo && derecha)))
//                                if (matriz[nodoActual.getY()+1][nodoActual.getX()+1].getTransitable())
//                                        nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()+1]);
//                        
//                        if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas && (forzar8conectado || (abajo && izquierda)))
//                                if (matriz[nodoActual.getY()+1][nodoActual.getX()-1].getTransitable())
//                                        nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()-1]);

                        // Para cada nodo encontrado, comprobamos si hemos llegado al punto de destino.
                        
                        while (!nodosAdyacentes.isEmpty() && !caminoEncontrado)
                        {
                                Posicion nodoAdyacente = (Posicion) nodosAdyacentes.remove(0);
                                if (!listaCerrada.contains(nodoAdyacente))
                                {
                                        if (!listaAbierta.contains(nodoAdyacente))
                                        {
                                                nodoAdyacente.setNodoPadre(nodoActual);
                                                listaAbierta.push(nodoAdyacente);

                                                if (nodoFinal == nodoAdyacente)
                                                {
                                                        caminoEncontrado = true;
                                                }
                                        }
                                        else
                                        {
                                                int nuevoG = nodoActual.getG();
                                                if (nodoAdyacente.getX()==nodoActual.getX() || nodoAdyacente.getY()==nodoActual.getY())
                                                        nuevoG += 10;
                                                else
                                                        nuevoG += 14;

                                                if (nuevoG < nodoAdyacente.getG())
                                                {
                                                        nodoAdyacente.setNodoPadre(nodoActual);
                                                        listaAbierta.reordenar();
                                                }
                                        }
                                }
                        }

                }


                // Si hemos llegado al nodo final, volvemos hacia atrás desde ese nodo extrayendo el camino hasta el nodo inicial.
                if (caminoEncontrado)
                {
                	ArrayList camino = new ArrayList<Posicion>();
                        Posicion nodoAuxiliar = nodoFinal;
                        Posicion nodoAnterior = null;
//                        while (nodoAuxiliar != null && !(nodoAuxiliar == nodoAnterior))
//                        while (nodoAuxiliar != null)
                        while (nodoAuxiliar != null && !(camino.contains(nodoAuxiliar)))
                        {	
                        		camino.add(0, nodoAuxiliar);
                                nodoAnterior = nodoAuxiliar.getNodoPadre();
                                nodoAuxiliar = nodoAuxiliar.getNodoPadre();
//                                if(!(nodoAuxiliar==null)){
//                                	System.out.println(nodoAuxiliar);
//                                	System.out.println(nodoAuxiliar.getNodoPadre());
//                                }
                        }
                        return camino;
                }
                else
                {		
//                	System.out.println("no se puede calcular camino");
                        return null;
                	
                }
        }
}