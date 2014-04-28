package pck.client;

import java.util.ArrayList;

import pck.client.bombas.TBomba;
import pck.client.bombas.TBombaMarciano;
import pck.client.canvas.TCanvas;
import pck.client.recursos.Recursos;
import pck.client.recursos.imagenes.ImagenesBundle;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;


public class TNaveMarciano extends TNave{
	
	public boolean disparando;
	public enum Posicion{ARRIBA,ABAJO};
	
	
	private TCanvas canvasBombas;
	private Timer timerNaveMarciano;
	private Timer timerNaveMarcianoDisparar;
	private Posicion posicion;
	
	 /////////////////////CONSTRUCTORES/////////////////////////////
	
	public TNaveMarciano(TCanvas _canvas,TCanvas _canvasBombas,double _x,double _y,double _ancho,double _alto, Posicion _posicion){
			super(_canvas,_x, _y, _ancho,_alto);
			ImagenesBundle imagenes=Recursos.instance.imagenes();
			canvasBombas=_canvasBombas;
			
			posicion=_posicion;
			imgNave=new Image(imagenes.naveMarciano());
			disparando=false;
			bombas=new ArrayList<TBomba>();
	}	
		 ///////////////////// metodos  para el marciano/////////////////////////////
		   
		   public void EjecutarMovimiento()
			{
				if(posicion==Posicion.ARRIBA)
				    this.DibujarNaveArriba();
				
				if(posicion==Posicion.ABAJO)
					this.DibujarNaveAbajo();
				
				timerNaveMarciano=new Timer() {
					
					@Override
					public void run() {
						limitesArribaAbajo();
						
					}//end function
				};
				timerNaveMarciano.scheduleRepeating(300);
				
				timerNaveMarcianoDisparar=new Timer() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						DispararBomba();
					}
				};
				//TIEMPO DE DISPARO DE BOMBAS
				timerNaveMarcianoDisparar.scheduleRepeating(1000);
			}//end function 

			private void limitesArribaAbajo()
			{
				if(this.getY()+this.h>context.getCanvas().getHeight()){
					if(posicion==Posicion.ABAJO){
						this.DibujarNaveArriba();
							posicion=Posicion.ARRIBA;
						}
					
					//marciano.DibujarMarcianoIzquierda();
				}//end if
				else if(this.getY()<0){
						if(posicion==Posicion.ARRIBA){
							this.DibujarNaveAbajo();
							posicion=Posicion.ABAJO;
						}	
				}//end else if
				else{
					
					if(posicion==Posicion.ABAJO){
						this.DibujarNaveAbajo();
					}
					if(posicion==Posicion.ARRIBA){
						this.DibujarNaveArriba();
					}
				}//end else
				
			}//end function
			public void destruir(){
				timerNaveMarciano.cancel();
				timerNaveMarcianoDisparar.cancel();
				BorrarNave();
			}

			@Override
			public void DispararBomba() {
				TBombaMarciano bmb=new TBombaMarciano(canvasBombas);
				bombas.add(bmb);
				bmb.setY(this.getY());   
				bmb.setX(this.getX()-60);
				bmb.Disparar();
			}
			/*
			 * REFRESCAR CANVAS
			 */
			public void cancelTimer(){
				if(timerNaveMarciano!=null)
					timerNaveMarciano.cancel();
				if(timerNaveMarcianoDisparar!=null)
					timerNaveMarcianoDisparar.cancel();
			}
}//end clase
