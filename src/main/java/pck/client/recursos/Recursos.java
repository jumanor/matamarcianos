package pck.client.recursos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;

public class Recursos {
	
	public static Bundle instance = GWT.create(Bundle.class);
	
	static {
		StyleInjector.inject(instance.cssFrmCargando().getText());
		StyleInjector.inject(instance.cssFrmMenu().getText());
		StyleInjector.inject(instance.cssMotorJuego().getText());
	}
}//end class