package battlecity;

import java.util.ArrayList;
import java.util.List;

import utils.Vector2D;
import aestrella.Astar;
import aestrella.Posicion;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.appearances.Sprite;

public class TanqueEnemigo extends Tanque {

        private double tiempoDeDisparo = 0;
        private double comparadorDeDisparo = 1;
        private double comparadorDeBusqueda = 5;
        
        private Vector2D velocidadPolar = new Vector2D(0, Math.PI / 2);
        private double rapidezDisparo = 300;
        double deltaSpeed = 20;
        double anguloDisparo;
		private ArrayList<Posicion> caminoARecorrer;
		private Posicion posicionProxima;
		private int index = 3;
		private Direccion direccionAMover;
		
        
        public TanqueEnemigo(double posx, double posy, double xMin, double xMax,
                        double yMin, double yMax) {
                super(posx, posy, xMin, xMax, yMin, yMax);
                this.setAppearance(Sprite.fromImage("/tanqueEnemigoAbajo.png"));
        }

        @Override
        public void update(DeltaState deltaState) {
                this.tiempoDeDisparo += deltaState.getDelta();
                if( this.tiempoDeDisparo >= this.comparadorDeDisparo){
                        this.disparar();
                        this.comparadorDeDisparo += 1.0;
                }
                Posicion actual = this.posicionActual();
//                Posicion ESI = this.posicionExtremoSuperiorIzquierdo();
//                Posicion ESD = this.posicionExtremoSuperiorDerecho();
//                Posicion EII = this.posicionExtremoInferiorIzquierdo();
//                Posicion EID = this.posicionExtremoInferiorDerecho();
                if(index ==3){
                	this.buscarTanque();
                }
                
                this.tiempoDeDisparo += deltaState.getDelta();
                
                if(!(this.caminoARecorrer == null)){
                	if(this.llegoALaPosicionActual()){
                		System.out.println("entro aca "+this.index);
                		this.index +=1;
                		this.posicionProxima= this.caminoARecorrer.get(this.index);
                		this.direccionAMover = this.vieneDe(this.posicionProxima);
                		this.mover(deltaState);
                	}
                	else{
                		this.direccionAMover = this.vieneDe(this.posicionProxima);
                		this.mover(deltaState);                		
                	}
              	
                }
        }
        
        private void mover(DeltaState deltaState) {
			if(this.direccionAMover.equals(Direccion.ABAJO)){
				this.moverAbajo(deltaState);
			}else if(this.direccionAMover.equals(Direccion.ARRIBA)){
				this.moverArriba(deltaState);
			}else if(this.direccionAMover.equals(Direccion.IZQUIERDA)){
				this.moverAIzquierda(deltaState);
			}else if(this.direccionAMover.equals(Direccion.DERECHA)){
				this.moverADerecha(deltaState);
			}
		}

		public boolean llegoALaPosicionActual(){
        	if(!(this.posicionProxima == null)){
        		return (this.estaEnLaPosicion( this.posicionProxima));        		
        	}
        	return false;
        }
        
        private boolean estaEnLaPosicion(Posicion posicionProxima){
        	if(this.direccionAMover == Direccion.DERECHA || this.direccionAMover == Direccion.IZQUIERDA){
				return ((this.getX()>= this.posicionProxima.getX()*50+1) && (this.getX() <= this.posicionProxima.getX()*50+2));
			}
			return((this.getY() >= this.posicionProxima.getY()*50+1) && (this.getY()<= this.posicionProxima.getY()*50+2) );
		}
        
        private Direccion vieneDe(Posicion posicion) {
    		Posicion actual = this.posicionActual();
    		if(posicion.getX()>actual.getX()){
    			return Direccion.DERECHA;
    		}
    		if(posicion.getX()<actual.getX()){
    			return Direccion.IZQUIERDA;
    		}
    		if(posicion.getY()>=actual.getY()){
    			return Direccion.ABAJO;
    		}
//    		if(posicionMasCorta.getY()>actual.getY()){
    			return Direccion.ARRIBA;
    		
    	}

		protected void buscarTanque() {
                System.out.println("NUEVO CAMINO");
                ArrayList<Posicion> resultadosCaminos = new ArrayList<Posicion>();
                Tanque tanque = this.getScene().getTanque();
                Posicion miPosicionInicial = this.getScene().getGrilla().getPosicion(this.getX(), this.getY());
                Posicion posicionTanque = this.getScene().getGrilla().getPosicion(tanque.getX(), tanque.getY());
                System.out.println(miPosicionInicial.getX()+"/"+miPosicionInicial.getY());
                Astar camino = new Astar(this.getScene().getGrilla().getMapa(), miPosicionInicial, posicionTanque);
                resultadosCaminos = camino.calcularCamino();
                this.caminoARecorrer = resultadosCaminos;
                this.index = 0;
                this.posicionProxima = resultadosCaminos.get(0);
                if(!(resultadosCaminos == null) && (!(resultadosCaminos.size() == 0))){
                        for(Posicion posi : resultadosCaminos){
                                System.out.println(posi.toString());
                        }
                }
        }

        private List<List<Posicion>> generarCaminos(Tanque tanque) {
                List<ArrayList<Posicion>> caminos = new ArrayList<ArrayList<Posicion>>();
                Posicion posicionTanque = this.getScene().getGrilla().getPosicion(tanque.getX(), tanque.getY()); 
                //while()
                
                return null;
        }
        
        protected boolean sePuedeMover(Posicion actual, Direccion direccion) {
                if(!this.estaElTanque(actual, direccion)){
                        if(direccion.equals(Direccion.IZQUIERDA)){
                                return actual.getX()>=0;
                        }
                        if(direccion.equals(Direccion.DERECHA)){
                                return actual.getX()<=15;
                        }
                        if(direccion.equals(Direccion.ABAJO)){
                                return actual.getY()<=11;
                        }
                        if(direccion.equals(Direccion.ARRIBA)){
                                return actual.getY()>=0;
                        }
                }
                return false;
        }
        
        public boolean estaElTanque(Posicion actual, Direccion direccion){
                Posicion tanque = this.getScene().getGrilla().getPosicion(this.getScene().getTanque().getX(), this.getScene().getTanque().getY());
                if(direccion.equals(Direccion.IZQUIERDA)){
                        if(this.getScene().getGrilla().getPosicionIzquierda(actual) == tanque){
                                return true;
                        }
                }
                if(direccion.equals(Direccion.DERECHA)){
                        if(this.getScene().getGrilla().getPosicionDerecha(actual) == tanque){
                                return true;
                        }
                }
                if(direccion.equals(Direccion.ABAJO)){
                        if(this.getScene().getGrilla().getPosicionAbajo(actual) == tanque){
                                return true;
                        }
                }
                if(direccion.equals(Direccion.ARRIBA)){
                        if(this.getScene().getGrilla().getPosicionArriba(actual) == tanque){
                                return true;
                        }
                }
                return false;
                
        }

        protected void moverAIzquierda(DeltaState deltaState) {
        		double delta = deltaState.getDelta();
                double ro = velocidadPolar.getX();
                double movimiento = this.getX() - getVelocidad() * delta;
                if(movimiento < (this.posicionProxima.getX()*50 +1)){
                	this.setX(this.posicionProxima.getX()*50+1);
                }else{
                	this.setX(movimiento);
                }
                this.setAppearance(Sprite.fromImage("/tanqueIzquierda.png"));
                anguloDisparo = Math.PI;
        }
        
        protected void moverADerecha(DeltaState deltaState) {
                double delta = deltaState.getDelta();
                double ro = velocidadPolar.getX();   
                double movimiento = this.getX() + getVelocidad() * delta;
                if(movimiento > (this.posicionProxima.getX()*50+1)){
                	this.setX(this.posicionProxima.getX()*50+1);
                }else{
                	this.setX(movimiento);
                }
                this.setAppearance(Sprite.fromImage("/tanqueDerecha.png"));
                anguloDisparo = 2 * Math.PI;
                	
                
        }
        
        protected void moverAbajo(DeltaState deltaState) {
                double delta = deltaState.getDelta();
                double ro = velocidadPolar.getX();
                double movimiento = this.getY() + getVelocidad() * delta;
                if(movimiento > this.posicionProxima.getY()*50+1){
                	this.setY(this.posicionProxima.getY()*50+1);
                }else{
                	this.setY(movimiento);
                }
                this.setAppearance(Sprite.fromImage("/tanqueAbajo.png"));
                anguloDisparo = Math.PI / 2;
                ro = Math.max(0, ro - (deltaSpeed * deltaState.getDelta()));            
        }
        
        protected void moverArriba(DeltaState deltaState) {
                double delta = deltaState.getDelta();
                double ro = velocidadPolar.getX();
                double movimiento = this.getY() - getVelocidad() * delta;
                if(movimiento < this.posicionProxima.getY()*50+1){
                	this.setY(this.posicionProxima.getY()*50+1);
                }else{
                	this.setY(movimiento);
                }                	
                this.setAppearance(Sprite.fromImage("/tanqueArriba.png"));
                anguloDisparo = -Math.PI / 2;
                ro += deltaSpeed * deltaState.getDelta();

        }



        public void disparar() {
                if (this.isTieneBala()) {
                        Bala bala = new BalaEnemiga(this,this.getX() + this.getAncho() / 2,
                                        this.getY() + this.getAncho() / 2, this.velocidadPolar.suma(
                                                        new Vector2D(rapidezDisparo, 0)).toCartesians());
                        this.getScene().addComponent(bala);
                        this.getScene().getBalas().add(bala);
                        this.setTieneBala(false);
                }
        }
        public double getTiempoDeDisparo() {
                return tiempoDeDisparo;
        }

        public void setTiempoDeDisparo(double tiempoDeDisparo) {
                this.tiempoDeDisparo = tiempoDeDisparo;
        }
        
        

}
