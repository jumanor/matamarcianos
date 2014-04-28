package pck.client;

import java.util.ArrayList;

import pck.client.bombas.TBomba;
import pck.client.bombas.TBombaTerricola;
import pck.client.canvas.TCanvas;
import pck.client.recursos.Recursos;
import pck.client.recursos.imagenes.ImagenesBundle;

import com.google.gwt.user.client.ui.Image;

public class TNaveTerricola extends TNave{
	
    private TCanvas canvasBombas;
	private int numBalas,totalBalas;
	
	////////////////////////////////CONSTRUCTORES////////////////////////////////////////////////////////
	public TNaveTerricola(TCanvas _canvas,TCanvas _canvasBombas,int _numBalas){
		 super(_canvas);
		 totalBalas=_numBalas;
		 numBalas=_numBalas;
		 ImagenesBundle imagenes=Recursos.instance.imagenes();
		 canvasBombas=_canvasBombas;
		 w=52;
	     h=39;
	     bombas=new ArrayList<TBomba>();
	     imgNave=new Image(imagenes.naceTerricola());
	     
	}//end function
    ///////////////////////////////PROPIEDADES/////////////////////////////////////////////////////////
	public int getNumBalas(){
		return numBalas;
	}
	public int getTotalBalas(){
		return totalBalas;
	}
	///////////////////////////METODOS PUBLICOS//////////////////////////////////////////////////////////
	public void DispararBomba(){
		
		if(numBalas==0)return;
		
		TBombaTerricola bmb=new TBombaTerricola(canvasBombas);
		bombas.add(bmb);
		bmb.setY(this.getY());   
		bmb.setX(this.getX()+27);
		bmb.Disparar();
		numBalas--;
		
	}//end function		
	@Override
	public void destruir() {
		// TODO Auto-generated method stub
		BorrarNave();
	}
	
}//end class


