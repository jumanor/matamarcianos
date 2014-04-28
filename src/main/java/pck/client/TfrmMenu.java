package pck.client;

import pck.client.recursos.Recursos;
import pck.client.recursos.css.CssFrmMenu;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.CaptionPanel;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.NumberFormat;

import com.google.gwt.user.client.ui.IntegerBox;

public class TfrmMenu extends PopupPanel {
	public IntegerBox txtAncho;
	public IntegerBox txtAlto;
	public boolean PresionoAplicar=false;
	
	public TfrmMenu() {
		super(true);
		
		final CssFrmMenu cssFrmMenu = Recursos.instance.cssFrmMenu();
		
		setStyleName(cssFrmMenu.gwtPopupPanelModificado());
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("395px", "306px");
		
		Button btnAplica = new Button("Aplica");
		btnAplica.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PresionoAplicar=true;
				hide();
			}
		});
		btnAplica.setStyleName(cssFrmMenu.gwtButtonModificado());
		btnAplica.setText("Aplicar");
		absolutePanel.add(btnAplica, 169, 266);
		
		CaptionPanel cptnpnlNewPanel = new CaptionPanel("Pantalla");
		cptnpnlNewPanel.setStyleName(cssFrmMenu.gwtCaptionPanelModificado());
		absolutePanel.add(cptnpnlNewPanel, 10, 10);
		cptnpnlNewPanel.setSize("158px", "139px");
		
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		cptnpnlNewPanel.setContentWidget(absolutePanel_1);
		absolutePanel_1.setSize("155px", "124px");
		
		Label label = new Label("Ancho");
		absolutePanel_1.add(label, 84, 10);
		label.setStyleName(cssFrmMenu.gwtLabelMoficado());
		label.setSize("56px", "18px");
		
		Label lblAltura = new Label("Altura");
		absolutePanel_1.add(lblAltura, 84, 43);
		
		lblAltura.setStyleName(cssFrmMenu.gwtLabelMoficado());
		lblAltura.setSize("56px", "18px");
		
		Button btnModificar = new Button("Pantalla");
		btnModificar.setTitle("Captura el ancho y altura\r\nde la Pantalla");
		btnModificar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//txtAlto.setValue( Window.getClientHeight()-1);
				//txtAncho.setValue(Window.getClientWidth());
				txtAncho.setText(NumberFormat.getFormat("#").format(Window.getClientWidth()));
				txtAlto.setText(NumberFormat.getFormat("#").format(Window.getClientHeight()-1));
				
			}
		});
		btnModificar.setStyleName(cssFrmMenu.gwtButtonModificado());
		absolutePanel_1.add(btnModificar, 45, 84);
		
		txtAncho = new IntegerBox();
		txtAncho.setStyleName(cssFrmMenu.gwtTextBoxModificado());
		txtAncho.setText("123456");
		absolutePanel_1.add(txtAncho, 10, 6);
		txtAncho.setSize("46px", "10px");
		
		txtAlto = new IntegerBox();
		txtAlto.setText("12345");
		txtAlto.setStyleName(cssFrmMenu.gwtTextBoxModificado());
		absolutePanel_1.add(txtAlto, 10, 43);
		txtAlto.setSize("46px", "10px");
		
		CaptionPanel cptnpnlAyuda = new CaptionPanel("Ayuda");
		cptnpnlAyuda.setStyleName(cssFrmMenu.gwtCaptionPanelModificado());
		absolutePanel.add(cptnpnlAyuda, 208, 10);
		cptnpnlAyuda.setSize("145px", "139px");
		
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		cptnpnlAyuda.setContentWidget(absolutePanel_2);
		absolutePanel_2.setSize("148px", "123px");
		
		Label lblRwerewr = new Label("Para disparar presione\r\nla tecla <CTRL>");
		absolutePanel_2.add(lblRwerewr, 0, 0);
		lblRwerewr.setStyleName(cssFrmMenu.gwtLabelMoficado());
		lblRwerewr.setSize("138px", "58px");
		
		CaptionPanel cptnpnlCreditos = new CaptionPanel("Creditos");
		cptnpnlCreditos.setStyleName(cssFrmMenu.gwtCaptionPanelModificado());
		absolutePanel.add(cptnpnlCreditos, 10, 171);
		cptnpnlCreditos.setSize("343px", "71px");
		
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		cptnpnlCreditos.setContentWidget(absolutePanel_3);
		absolutePanel_3.setSize("341px", "54px");
		
		Label lblRer = new Label("Powered by : Google Web Toolkit");
		lblRer.setStyleName(cssFrmMenu.gwtLabelMoficado());
		absolutePanel_3.add(lblRer, 10, 0);
		lblRer.setSize("321px", "24px");
		
		Label lblProgrammersistemasunajma = new Label("TACNA - 2014");
		lblProgrammersistemasunajma.setStyleName(cssFrmMenu.gwtLabelMoficado());
		absolutePanel_3.add(lblProgrammersistemasunajma, 10, 30);
		lblProgrammersistemasunajma.setSize("321px", "24px");
	}
}
