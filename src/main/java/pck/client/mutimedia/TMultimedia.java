package pck.client.mutimedia;

import java.util.ArrayList;

import pck.client.TJAudio;
import pck.client.recursos.Recursos;
import pck.client.recursos.audio.AudiosBundle;

public abstract class TMultimedia {

	/////////////////////////////////CREATE EVENTS///////////////////////////////////////////
	public interface LoadMultimediaHandler{
	
			public abstract void onLoadMultimedia();
	}
	
	ArrayList<LoadMultimediaHandler> handlers =new ArrayList<LoadMultimediaHandler>();
	public void addLoadMultimediaHandler(LoadMultimediaHandler event){
	handlers.add(event);
	
	}
	protected void nativeLoadMultimediaHandler(){
	for(LoadMultimediaHandler handler:handlers){
	if(handler!=null){
	handler.onLoadMultimedia();
	}
	}
	}
	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
		
	protected  TJAudio sndMusicaFondo;
	protected TJAudio sndDisparoMarciano;
	protected TJAudio sndDisparoTerricola;
	protected  TJAudio sndExplosion;
	
	protected TMultimedia(){
		
		
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
	}
	
	
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
}
