package pck.client.recursos;

import pck.client.recursos.audio.AudiosBundle;
import pck.client.recursos.css.CssFrmCargando;
import pck.client.recursos.css.CssFrmMenu;
import pck.client.recursos.css.CssMotorJuego;
import pck.client.recursos.imagenes.ImagenesBundle;

import com.google.gwt.resources.client.ClientBundle;

public interface Bundle extends ClientBundle{

	@Source("css/FrmMenu.css")
	public CssFrmMenu cssFrmMenu();
	
	@Source("css/MotorJuego.css")
    public CssMotorJuego cssMotorJuego();
	
	@Source("css/FrmCargando.css")
    public CssFrmCargando cssFrmCargando();
	
	public ImagenesBundle imagenes();
	
	public AudiosBundle audios();
	
}
