package pck.client.bombas;

import pck.client.TGrafica;
import pck.client.canvas.TCanvas;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;

public abstract class TBomba extends TGrafica {
	
	public enum EstadoBomba{VIVO,DESTRUIDO}
	
	protected Image imgExplosion;
	protected Image imgBomba;
	protected EstadoBomba estado; 
	
	private Timer timerMovBomba;
	private Timer timerExplosion;
	
	//////////////////////////////////////////////PROPIEDADES////////////////////////////////////////
	public EstadoBomba getEstado() {
		return estado;
	}
	public void setEstado(EstadoBomba estado) {
		this.estado = estado;
	}
	/////////////////////////////////////////////CONSTRUCTORES/////////////////////////////////////////777
	public TBomba(TCanvas _canvas){
			super(_canvas);
			estado=EstadoBomba.VIVO;
		}
	/////////////////////////////////////////////METODOS PUBLICOS///////////////////////////////////////
	public void Explotar(){
		timerMovBomba.cancel();
		BorrarBomba();
		SonidoColision();
		explosionColision(500);
	}//end function
	public void Explotar(boolean conExplosion){
		timerMovBomba.cancel();
		BorrarBomba();
		if(conExplosion==true){
			SonidoColision();
			explosionColision(500);
		}
	}//end function
	public  void Disparar(){
		//Window.alert("disparando");
		SonidoDisparo();
		timerMovBomba=new Timer() {
			
			@Override
			public void run() {
				BorrarBomba();
				MoverBomba();
			}
		};
		//velocidad de la Bomba
		timerMovBomba.scheduleRepeating(50);
	}//end function
	/*
	 * Para refrescar el Canvas
	 */
	public void cancelTimer(){
		if(timerMovBomba!=null)
			timerMovBomba.cancel();
		if(timerExplosion!=null)
			timerExplosion.cancel();
	}
	///////////////////////////////////////////////METODOS PROTECTED/////////////////////////////////////////////
	protected abstract void SonidoColision();
	protected abstract void SonidoDisparo();
	protected abstract void BorrarBomba();
	protected abstract void MoverBomba();
	///////////////////////////////////////////////METODOS PRIVADOS/////////////////////////////////////////////
	/**
	 * 
	 * Cuando la bomba llegua a un Objetivo explota
	 *  
	 */
	private void explosionColision(int _delay){
		context.beginPath();
			ImageElement imageElement = ImageElement.as(imgExplosion.getElement());
			//La Altura y Ancho de la Explosion es CTE
			context.drawImage(imageElement, x, y,40,30);
		context.closePath();
		timerExplosion=new Timer() {

			@Override
			public void run() {
				//Borramos la imagen de la explosion despues de 1000 milisegundos
				context.clearRect(x, y, 40, 30);
			}
		};
		timerExplosion.schedule(_delay);
	}//end function
	
}//end class
