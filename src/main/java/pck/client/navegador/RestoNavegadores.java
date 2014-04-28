package pck.client.navegador;

import pck.client.librerias.TfrmCargando;
import pck.client.mutimedia.TFabricaMultimedia;
import pck.client.mutimedia.TFabricaMultimedia.TiposNavedor;
import pck.client.mutimedia.TMultimedia.LoadMultimediaHandler;

public class RestoNavegadores extends Navegador{

	@Override
	public void cargarModulo() {
		// TODO Auto-generated method stub
		//PRELOAD MUSIC

				TFabricaMultimedia fab=TFabricaMultimedia.getInstance();
				fab.crearMultimedia(TiposNavedor.OTROS);
				ejecutar();
				
	}

}
