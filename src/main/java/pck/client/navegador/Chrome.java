package pck.client.navegador;

import pck.client.librerias.TfrmCargando;
import pck.client.mutimedia.TFabricaMultimedia;
import pck.client.mutimedia.TFabricaMultimedia.TiposNavedor;
import pck.client.mutimedia.TMultimedia.LoadMultimediaHandler;

public class Chrome extends Navegador{

	@Override
	public void cargarModulo() {
		//PRELOAD MUSIC
		final TfrmCargando load=new TfrmCargando();
		load.show();
		
		TFabricaMultimedia fab=TFabricaMultimedia.getInstance();
		fab.crearMultimedia(TiposNavedor.CHROME);
		
		fab.getMultimedia().addLoadMultimediaHandler(new LoadMultimediaHandler() {
			
			public void onLoadMultimedia() {
				// TODO Auto-generated method stub
				ejecutar();
				load.hide();
			}
		});
		
	}

}
