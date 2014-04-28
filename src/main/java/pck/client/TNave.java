package pck.client;

import java.util.ArrayList;

import pck.client.bombas.TBomba;
import pck.client.canvas.TCanvas;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;
import com.reveregroup.gwt.imagepreloader.ImageLoadEvent;
import com.reveregroup.gwt.imagepreloader.ImageLoadHandler;
import com.reveregroup.gwt.imagepreloader.ImagePreloader;

public abstract class TNave extends TGrafica{
	
	public enum Estado{VIVO,DESTRUIDO}
	protected Estado estado; 
	protected Image imgNave;
	protected ArrayList<TBomba> bombas;
	   
	///////////////////////////////////////PROPIEDADES////////////////////////////////////// 
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public ArrayList<TBomba> getBombas() {
		return bombas;
	}//end fucntion

	public void setBombas(ArrayList<TBomba> bombas) {
		this.bombas = bombas;
	}//end function
	//////////////////////////////////////CONSTRUCTORES//////////////////////////////////////  
	public TNave(TCanvas _canvas){
		super(_canvas);
	    y=50;
	    x=30;    //imagen al medio del canvas
		w=52;
		h=39;
		estado=Estado.VIVO;
	}
	
	public TNave(TCanvas _canvas,double _x,double _y,double _ancho,double _alto){
		super(_canvas);
		this.x=_x;
		this.y=_y;
		this.w=_ancho;
		this.h=_alto;
		estado=Estado.VIVO;
	
	}
	///////////////////////////////////////METODOS PUBLICOS//////////////////////////////////////
	public void DibujarNaveArriba(){
		ImagePreloader.load("marco.gif", new  ImageLoadHandler() {
			
			
			public void imageLoaded(ImageLoadEvent event) {
				BorrarNave();
				y=y-20;
				context.beginPath();
				ImageElement imageElement = ImageElement.as(imgNave.getElement());
				context.drawImage(imageElement, x-25, y,w,h);
				context.closePath();		
			}
		});
			
	}
	//////////////////////////////
   public void DibujarNaveAbajo(){
	BorrarNave();
		
		y=y+20;
		context.beginPath();
		ImageElement imageElement = ImageElement.as(imgNave.getElement());
		context.drawImage(imageElement, x-25, y,w,h);
		context.closePath();
		
	}
	///////////////////////////////
	public void DibujarNaveIzquierda(){
	BorrarNave();
		
		x=x-20;
		context.beginPath();
		ImageElement imageElement = ImageElement.as(imgNave.getElement());
		context.drawImage(imageElement, x-25, y,w,h);
		context.closePath();
		
	}
	//////////////////////////////
   public void DibujarNaveDerecha(){
	BorrarNave();
		
		x=x+20;
		context.beginPath();
		ImageElement imageElement = ImageElement.as(imgNave.getElement());
		context.drawImage(imageElement, x-25, y,w,h);
		context.closePath();
		
	}
   public abstract void DispararBomba();
   public abstract void destruir();
   /////////////////////////////// METODOS PRIVADOS //////////////////////////////////////////
  
   /////////////////////////////// METODOS PROTEGIDOS //////////////////////////////////////////
    protected void BorrarNave(){
		context.clearRect(x-25, y, w, h);	
    }
}
