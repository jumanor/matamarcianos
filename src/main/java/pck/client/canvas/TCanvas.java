package pck.client.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
/**
 *     - Juego de Motoss
 *     - Se muestra el manejo de los eventos del teclado dentro del Canvas
 *     - 
 */
public class TCanvas{
	
	private int height;
	
	private int width;
	
	private Canvas canvas;
	private Context2d context;
	
	//*****PROPIEDADES*****
	public void setHeight(int height) {
	     canvas.setHeight(height + "px");
	     canvas.setCoordinateSpaceHeight(height);
	}
	public void setWidth(int width) {
		canvas.setWidth(width + "px");
	    canvas.setCoordinateSpaceWidth(width);
	}
	public Context2d getContext() {
		return context;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	//*****CONSTRUCTOR*****
	public TCanvas()
	{
		this.height=400;
		this.width=1000;
		createCanvas();
	}
	public TCanvas(int _width,int _height){
		this.height=_height;
		this.width=_width;
		createCanvas();
	}
	//*****METODOS*****
	private void createCanvas(){
		   
		 canvas = Canvas.createIfSupported();
		  if (canvas == null) {
		        RootPanel.get().add(new Label("Tu Buscador no Soporta HTML5"));
		        return;
		  }
	     canvas.setWidth(width + "px");
	     canvas.setHeight(height + "px");
	     canvas.setCoordinateSpaceWidth(width);
	     canvas.setCoordinateSpaceHeight(height);
	      
	     context = canvas.getContext2d();
	   
	     //context.closePath();      
	 }//end function

}//end class
