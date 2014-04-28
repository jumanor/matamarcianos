package pck.client.librerias;

import pck.client.recursos.Recursos;
import pck.client.recursos.css.CssFrmCargando;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public class TfrmCargando extends PopupPanel {

	public TfrmCargando() {
		super(true);
		
		final CssFrmCargando cssFrmCargando = Recursos.instance.cssFrmCargando();
		
		//setStyleName("gwt-PopupPanelModificado");
		setStyleName(cssFrmCargando.gwtPopupPanelModificado());
		
		int w=Window.getClientWidth();
		this.setPopupPosition((w-133)/2, 10);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("133px", "37px");
		
		HTML htmlCargando = new HTML("Cargando...", true);
		absolutePanel.add(htmlCargando, 49, 10);
		
		Image image = new Image("load.gif");
		absolutePanel.add(image,13, 3);
		image.setSize("30px", "30px");
	}
}
