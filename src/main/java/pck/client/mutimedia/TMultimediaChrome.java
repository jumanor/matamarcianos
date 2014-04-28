package pck.client.mutimedia;

import pck.client.TJAudio.LoadAudioHandler;

import com.google.gwt.core.client.GWT;

public class TMultimediaChrome extends TMultimedia{

	public TMultimediaChrome(){
		super();
		GWT.log("CONSTRUCTOR CHROME");
		//////////////////////////PRELOAD MUSIC///////////////////////////////////////////
		
		sndMusicaFondo.addLoadAudioHandler(new LoadAudioHandler() {
		
	
		public void onLoadAudio() {
		
			sndDisparoTerricola.addLoadAudioHandler(new LoadAudioHandler() {
				
				public void onLoadAudio() {
					// TODO Auto-generated method stub
					
					
					sndExplosion.addLoadAudioHandler(new LoadAudioHandler() {
						
		
				public void onLoadAudio() {
							// TODO Auto-generated method stub
							sndDisparoMarciano.addLoadAudioHandler(new LoadAudioHandler() {
								
								public void onLoadAudio() {
									// TODO Auto-generated method stub
									GWT.log("preload CHROME");
									nativeLoadMultimediaHandler();
									GWT.log("postload CHROME");
								}//end event
							});
							
							sndDisparoMarciano.load();
						}//end event
					});
					
					sndExplosion.load();
					
					/*
					GWT.log("preload OTROS");
					nativeLoadMultimediaHandler();
					GWT.log("postload OTROS");
					*/
				}//end event
			});

			sndDisparoTerricola.load();
		}//end event
		});
		sndMusicaFondo.load();

		//sndExplosion.load();
		//sndDisparoMarciano.load();

		
	}		
}
