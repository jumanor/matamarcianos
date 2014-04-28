package pck.client.recursos.imagenes;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ImagenesBundle extends ClientBundle {
	
	@Source("imgExplosionMarciano.png")
    public ImageResource explosionMarciano();

    @Source("imgBombaMarciano.png")
    public ImageResource bombaMarciano();

    @Source("imgExplosionTerricola.png")
    public ImageResource explosionTerricola();

    @Source("imgBombaTerricola.png")
    public ImageResource bombaTerricola();
    
    @Source("fondo.gif")
    public ImageResource fondoJuego();
    
    @Source("imgNaveMarciano.gif")
    public ImageResource naveMarciano();
    
    @Source("imgNaveTerricola.gif")
    public ImageResource naceTerricola();
    
}//end class
