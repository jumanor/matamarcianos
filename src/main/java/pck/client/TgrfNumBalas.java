package pck.client;

import pck.client.canvas.TCanvas;

public class TgrfNumBalas extends TGrafica{

	
	public TgrfNumBalas(int _x, int _y, int _w, int _h){
		super(_x, _y, _w, _h);
		
		canvas=new TCanvas();
		canvas.setWidth(_w);
		canvas.setHeight(_h);
		context=canvas.getContext();
		
		context.beginPath();
			context.setStrokeStyle("red");
			context.setLineWidth(2);
			context.strokeRect(x, y,  w, h);
		context.closePath();
		
		llenado(100);
		
	}//end function
	/**
	 * llenado de barras en %
	 */
	public void llenado(double porcentaje){
		String color="";
		if(porcentaje>60){
			color="green";
		}
		else if(porcentaje<=60 && porcentaje>30){
			color="yellow";
		}
		else if(porcentaje<=30 && porcentaje>0){
			color="red";
		}
		
		int ancho=(int) (w*(porcentaje/100));
		
		context.clearRect(1,1,w-2,h-2);
		
		for(int i=3;i<ancho;i+=3){
			context.beginPath();
			    context.setStrokeStyle(color);
				context.moveTo(i, 2); 
				context.lineTo(i, h-2);
				context.stroke();
			context.closePath();
		}//end for
	}//end function
}//end class
