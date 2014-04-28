package pck.client;

import pck.client.navegador.Navegador;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Matamarcianos implements EntryPoint {

	public void onModuleLoad() {
		// TODO Auto-generated method stub
		Navegador navegador=GWT.create(Navegador.class);
		navegador.cargarModulo();
	}
	
	/*
	private TMotorJuego motorJuego;
	
	public void onModuleLoad() {
		       //PRELOAD MUSIC
				final TfrmCargando load=new TfrmCargando();
				load.show();
				
				TFabricaMultimedia fab=TFabricaMultimedia.getInstance();
				fab.crearMultimedia(TiposNavedor.CHROME);
				
				fab.getMultimedia().addLoadMultimediaHandler(new LoadMultimediaHandler() {
					
					@Override
					public void onLoadMultimedia() {
						// TODO Auto-generated method stub
						ejecutar();
						load.hide();
					}
				});
				
		//ejecutar();
		
	}//end function
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
	*/
	
}//end class
