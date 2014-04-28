package pck.client.navegador;

import pck.client.TMotorJuego;

import com.google.gwt.user.client.Window;

public abstract class Navegador {
 
	private TMotorJuego motorJuego;
	
	public abstract void cargarModulo();
	
	public void ejecutar(){
		//ancho y altura del navegador
		int w=Window.getClientWidth();
		int h=Window.getClientHeight()-1;
		//condiciones minimas de resolucion
		if(w<1000 || h < 400){
			motorJuego=new TMotorJuego(1000,600);	
		}
		else{
			motorJuego=new TMotorJuego(w,h);
			
		}
     
		motorJuego.ejecutar();
		
	}//end function
}
