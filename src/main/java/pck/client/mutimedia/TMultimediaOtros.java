package pck.client.mutimedia;

import com.google.gwt.core.client.GWT;

public class TMultimediaOtros extends TMultimedia {

	public TMultimediaOtros(){
		
		super();
		GWT.log("CONSTRUCTOR OTRO");
		
			
				GWT.log("preload OTRO");
				
				sndDisparoTerricola.load();
				sndExplosion.load();
				sndDisparoMarciano.load();
				sndMusicaFondo.load();
				
				nativeLoadMultimediaHandler();
				
				GWT.log("postload OTRO");
		
		
	}//end function
	
}//end class
