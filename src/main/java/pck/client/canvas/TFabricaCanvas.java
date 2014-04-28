package pck.client.canvas;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.AbsolutePanel;

public class TFabricaCanvas {

	private ArrayList<TCanvas> listCanvas=new ArrayList<TCanvas>();
	private int ancho,alto;	
	private boolean hasParent=false;
	
	public ArrayList<TCanvas> getListCanvas(){
		
		return listCanvas;
	}
	
	public TFabricaCanvas(int _ancho,int _alto){
		ancho=_ancho;
		alto=_alto;
	}//end function
	
	public void changeAnchoAltoListCanvas(int ancho,int alto){
		for(TCanvas tmp:listCanvas){
			tmp.setHeight(alto);
			tmp.setWidth(ancho);
		}
	}//end function
	/**
	 * Insertar la Lista de TCanvas en un AbsolutePanel
	 * 
	 * @author Jumanor 
	 * @return verifica exito de asociacion a AbsolutePanel
	 * 
	 */
	public boolean insertListCanvasToParent(AbsolutePanel _parent){
		//Falta controlar cuando _parent ya exista!!!!!!!
		if(hasParent==false){
			TCanvas _ult=null;
			for(TCanvas _cnv:listCanvas){
				
				 _parent.add(_cnv.getCanvas(),0,0);
				 _ult=_cnv; 
			 }
			_ult.getCanvas().setFocus(true);
		 hasParent=true;	
		}
		
		return hasParent;	
			
	}//end function
	public void clearListCanvas(){
	
		for(TCanvas _cnv:listCanvas){
			 
 			_cnv.getContext().clearRect(0, 0, ancho, alto);
			 
 		 }
	}//end function
	public TCanvas getNextCanvas(){
	 TCanvas _cnv=new TCanvas(ancho, alto);
	 listCanvas.add(_cnv);
	return _cnv;	
	}//end function
}//end class
