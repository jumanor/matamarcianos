package pck.client;

import pck.client.canvas.TCanvas;

import com.google.gwt.canvas.dom.client.Context2d;

public class TGrafica {
	  protected double  x,y;
	   protected double  w,h;
	   protected Context2d context;
	   protected TCanvas canvas;
	   
	 //***PROPIEDADES*********
	   
	   public double getX() {
		return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		public double getW() {
			return w;
		}
		public void setW(double w) {
			this.w = w;
		}
		public double getH() {
			return h;
		}
		public void setH(double h) {
			this.h = h;
		}

		public Context2d getContext() {
			return context;
		}
		public void setContext(Context2d context) {
			this.context = context;
		}
		public TCanvas getCanvas() {
			return canvas;
		}
		public void setCanvas(TCanvas canvas) {
			this.canvas = canvas;
		}
	//*****CONTRUCTOR
	   public TGrafica(double _x,double _y,double _w,double _h,TCanvas _canvas){
		   this(_canvas);
		   x=_x;
		   y=_y;
		   w=_w;
		   h=_h;
	   }//end function
	   public TGrafica(TCanvas _canvas){
		   canvas=_canvas;
		   context=canvas.getContext();
	   }//end function
	   public TGrafica(double _x,double _y,double _w,double _h){
		   x=_x;
		   y=_y;
		   w=_w;
		   h=_h;
	   }//end function
}//end class
