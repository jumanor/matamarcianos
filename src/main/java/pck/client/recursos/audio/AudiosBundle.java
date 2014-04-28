package pck.client.recursos.audio;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.DoNotEmbed;
import com.google.gwt.resources.client.DataResource.MimeType;

public interface AudiosBundle extends ClientBundle {
/*
 * Los mp3 siempre NotEmbed asi forzcemos a Embed
 */
	
	@Source("disparoMarciano.mp3")
	@MimeType(value = "audio/mpeg")
	//@DoNotEmbed
    public DataResource disparoMarciano();
	
	//Los wav si son EMBED no se ven en IE 10 > !!!!!
	@Source("disparoTerricola.mp3")
	@MimeType(value = "audio/mpeg")
	//@DoNotEmbed
	public DataResource disparoTerricola();
	
	@Source("fondo.mp3")
	@MimeType(value = "audio/mpeg")
	//@DoNotEmbed
    public DataResource musicaFondo();
	
	@Source("explosionBombas.mp3")
	@MimeType(value = "audio/mpeg")
	@DoNotEmbed
    public DataResource explosionBombas();
}//end class
