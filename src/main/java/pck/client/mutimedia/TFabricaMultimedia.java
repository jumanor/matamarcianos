package pck.client.mutimedia;

import com.google.gwt.core.shared.GWT;

public class TFabricaMultimedia {
	public enum TiposNavedor{FF,CHROME,OTROS};
	
		private static TFabricaMultimedia instance;
		private TMultimedia multimedia=null;
	
		private TFabricaMultimedia(){
		
	}//end function
	public void crearMultimedia(TiposNavedor tipo){
		if(multimedia!=null){
			GWT.log("ADVERTENCIA : Se intento llamar nuevamente a crearMultimedia(TiposNavedor)");
			return;
		} //solo una vez
		
		switch(tipo){
						case FF:
							multimedia=new TMultimediaFF();
						break;
						case CHROME:
							multimedia=new TMultimediaChrome();
						break;
						case OTROS:
							multimedia=new TMultimediaOtros();
						break;	
		}

	}//end function
	public TMultimedia getMultimedia(){
		return multimedia;
	}//end function
	public static TFabricaMultimedia getInstance(){
		if(instance==null)
				instance=new TFabricaMultimedia();
		return instance;
	}//end function
}//end class
