package pck.client;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;

public class TJAudio{

	public interface LoadAudioHandler{
		
		public abstract void onLoadAudio();
	}
	
	ArrayList<LoadAudioHandler> handlers =new ArrayList<LoadAudioHandler>();
	
	public void addLoadAudioHandler(LoadAudioHandler event){
		handlers.add(event);

	}
	private void nativeCanplaythroughHandler(){
	//	GWT.log("CARGADO HTML");
		for(LoadAudioHandler handler:handlers){
		
		//	GWT.log("CARGADO HTML HANDLER PREVIEW");
			
			if(handler!=null){
				GWT.log("Se cargo audio");
				handler.onLoadAudio();
			}
		}
	}
	/*
	 * Verifica si el audio ha sido cargado
	 */
	private boolean dataCargada=false;
	/*
	 * Indice de los de $wnd.arrayAudios=[]
	 */
	private int index=-1;
	/////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////CONSTRUCTOR/////////////////////////////////////////////////////
	public TJAudio(){
		crearAudio();
		
	}
	////////////////////////////////PROPIEDADES/////////////////////////////////////////////////////
	/**
	 * Verifica  Audio ha sido cargado
	 * 
	 * @author Jumanor
	 */
	public boolean isDataCargada(){
		return dataCargada;
	}
	//////////////////////////////METODOS PUBLICO///////////////////////////////////////////////////
	private native String crearAudio() /*-{
	  if(typeof $wnd.arrayAudios === "undefined"){
	  		$wnd.arrayAudios=[];
	  }
	  
	  var audio= $wnd.document.createElement("audio");
	  audio.autoplay=false;
	 
	  $wnd.arrayAudios.push(audio);
	  this.@pck.client.TJAudio::index=$wnd.arrayAudios.length;
	  
	}-*/;
	
	private native void updateId() /*-{
		$wnd.audio=$wnd.arrayAudios[this.@pck.client.TJAudio::index-1];
	}-*/;
	
	private native void createCanplaythrough() /*-{
		this.@pck.client.TJAudio::updateId()();
		var tmp=this;
		$wnd.audio.addEventListener("canplaythrough", function () {
	
			if ( tmp.@pck.client.TJAudio::dataCargada ==false){
			    //Llamamos solo una vez !!!!!!
				tmp.@pck.client.TJAudio::dataCargada=true;
		   		tmp.@pck.client.TJAudio::nativeCanplaythroughHandler()();
			}
			
		 }, false); 
		
	}-*/;
	public native void setLoop(boolean loop) /*-{
		this.@pck.client.TJAudio::updateId()();
		$wnd.audio.loop=loop;
	}-*/;
	private native void nativePlay() /*-{
		this.@pck.client.TJAudio::updateId()();
		$wnd.audio.play();
	}-*/;
	public native void pause() /*-{
		this.@pck.client.TJAudio::updateId()();
		$wnd.audio.pause();
	}-*/;
	private native void nativeLoad() /*-{
		this.@pck.client.TJAudio::updateId()();
		$wnd.audio.load();
	}-*/;
	/**
	 * Antes de Cargar se debe de crear el evento onLoadAudioHandler
	 * 
	 * Este metodo solo se debe llamarse una vez
	 * 
	 * @author Jumanor
	 */
	public void load(){
		//Modificar con una EXECPTION !!!!
		if (dataCargada==true){
			Window.alert("Solo esta permitido LOAD una vez");
			GWT.log("ADVERTENCIA: Se esta intentando carga otra vez TJAudio load()");
			return;
		}
		createCanplaythrough();
		nativeLoad();
	}
	private native void setNativeSrc(String src) /*-{
		this.@pck.client.TJAudio::updateId()();
		$wnd.audio.src=src;
	}-*/;
	public void setSrc(String src){
		setNativeSrc(src);
		//crearEvento();
	}
	public void play(){
		
		nativePlay();
	}
	
}//end class
