package pck.client.mutimedia;

import pck.client.TJAudio.LoadAudioHandler;

import com.google.gwt.core.shared.GWT;

public class TMultimediaFF extends TMultimedia {

	public TMultimediaFF(){
		super();
		GWT.log("CONSTRUCTOR FF");
		/**
		 *	sndMusicaFonso es el que mayor tamaño
		 */
		sndMusicaFondo.addLoadAudioHandler(new LoadAudioHandler() {
			
			public void onLoadAudio() {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				GWT.log("preload ff");
				
				//estos archivos son de menor tamaño
				//sndDisparoTerricola.load();
				//sndExplosion.load();
				//sndDisparoMarciano.load();
				
				nativeLoadMultimediaHandler();
				
				GWT.log("postload ff");
			}
		});
		
		sndMusicaFondo.load();
		
		sndDisparoTerricola.load();
		sndExplosion.load();
		sndDisparoMarciano.load();
		
	}//end function

	
}
