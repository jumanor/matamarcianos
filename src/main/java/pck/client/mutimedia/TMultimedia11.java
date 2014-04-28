package pck.client.mutimedia;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;

import pck.client.TJAudio;
import pck.client.TJAudio.LoadAudioHandler;
import pck.client.recursos.Recursos;
import pck.client.recursos.audio.AudiosBundle;

public class TMultimedia11 {
	
	/////////////////////////////////CREATE EVENTS///////////////////////////////////////////
	public interface LoadMultimediaHandler{
		
		public abstract void onLoadMultimedia();
	}
	ArrayList<LoadMultimediaHandler> handlers =new ArrayList<LoadMultimediaHandler>();
	public void addLoadMultimediaHandler(LoadMultimediaHandler event){
		handlers.add(event);

	}
	private void nativeLoadMultimediaHandler(){
		for(LoadMultimediaHandler handler:handlers){
			if(handler!=null){
				handler.onLoadMultimedia();
			}
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
	
	private static TMultimedia11 instance=null;
	
	private  TJAudio sndMusicaFondo;
	private TJAudio sndDisparoMarciano;
	private TJAudio sndDisparoTerricola;
	private TJAudio sndExplosion;
		
	protected TMultimedia11(){
			
		final AudiosBundle audios=Recursos.instance.audios();
		
		sndDisparoMarciano=new TJAudio();
		sndDisparoMarciano.setSrc(audios.disparoMarciano().getSafeUri().asString());
		
		sndDisparoTerricola=new TJAudio();
		sndDisparoTerricola.setSrc(audios.disparoTerricola().getSafeUri().asString());
		
		sndExplosion=new TJAudio();
		sndExplosion.setSrc(audios.explosionBombas().getSafeUri().asString());
		
		sndMusicaFondo=new TJAudio();
		sndMusicaFondo.setLoop(true);
		sndMusicaFondo.setSrc(audios.musicaFondo().getSafeUri().asString());
		
		//Window.alert(audios.musicaFondo().getSafeUri().asString());
		//////////////////////////PRELOAD MUSIC///////////////////////////////////////////
		/*
		sndMusicaFondo.addLoadAudioHandler(new LoadAudioHandler() {
			
			@Override
			public void onLoadAudio() {
				
				sndDisparoTerricola.addLoadAudioHandler(new LoadAudioHandler() {
					
					@Override
					public void onLoadAudio() {
						// TODO Auto-generated method stub
						
						sndExplosion.addLoadAudioHandler(new LoadAudioHandler() {
							
							@Override
							public void onLoadAudio() {
								// TODO Auto-generated method stub
								sndDisparoMarciano.addLoadAudioHandler(new LoadAudioHandler() {
									
									@Override
									public void onLoadAudio() {
										// TODO Auto-generated method stub
										GWT.log("preload");
										nativeLoadMultimediaHandler();
										GWT.log("postload");
									}//end event
								});
								
								sndDisparoMarciano.load();
							}//end event
						});
						
						sndExplosion.load();
					}//end event
				});
				
				sndDisparoTerricola.load();
			}//end event
		});
		sndMusicaFondo.load();
		*/
		/*
		 * OBSERVACION: Aperantemente en IE 10> cuando hay muchos requerimientos
		 * 				se queda colgado (BUG ?????)
		 * 				Este inconveniente no hay en FF o CHROME por eso modifcamos
		 * 				el codigo de arriba!!!
		 */
		GWT.log("CONSTRUCTOR");
		/**
		 *	sndMusicaFonso es el que mayor tamaño
		 */
		sndMusicaFondo.addLoadAudioHandler(new LoadAudioHandler() {
			
		
			public void onLoadAudio() {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				GWT.log("preload");
				
				//estos archivos son de menor tamaño
				sndDisparoTerricola.load();
				sndExplosion.load();
				sndDisparoMarciano.load();
				
				nativeLoadMultimediaHandler();
				
				GWT.log("postload");
			}
		});
		
		sndMusicaFondo.load();
		
		
	}//end function	
	public void sonidoBombaMarcianos(){
		
			sndDisparoMarciano.play();
		
	}//end function
	public void sonidoBombaTerricola(){
		
			sndDisparoTerricola.play();

	}//end function
	public void sonidoFondo(){
		
			sndMusicaFondo.play();
			
	}//end function
	public void sonidoExplosion(){
		
			sndExplosion.play();
		
	}//end function
	public static TMultimedia11 getInstance(){
		if(instance==null)
				instance=new TMultimedia11();
		return instance;
	}//end function
}//end class
