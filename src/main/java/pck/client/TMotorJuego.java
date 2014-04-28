package pck.client;

import java.util.ArrayList;
import java.util.Date;

import pck.client.TNave.Estado;
import pck.client.TNaveMarciano.Posicion;
import pck.client.bombas.TBomba;
import pck.client.bombas.TBomba.EstadoBomba;
import pck.client.canvas.TCanvas;
import pck.client.canvas.TFabricaCanvas;
import pck.client.mutimedia.TFabricaMultimedia;
import pck.client.recursos.Recursos;
import pck.client.recursos.css.CssMotorJuego;
import pck.client.recursos.imagenes.ImagenesBundle;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class TMotorJuego {

	
	private CssMotorJuego cssMotorJuego;
	private TfrmMenu frmMenu;
	private TNaveTerricola naveTerricola;
	private TNaveMarciano[] naveMarcianos;
    
    private FocusPanel focusPanel; //CREAMOS ESTE WIDGET PARA QUE LAS TECLAS FUNCIONEN CON IE 10
   
    private Timer timerEliminacionObjetos;
    private boolean teclaPresionadaCTRL;
    private long  intervalo;
   
    private AbsolutePanel absolutePanel;
    private Image imgFondo;
    private Label mensajePantalla,creditos;
    private Label mensajeNumBalas;
    
    private int ancho=-1;
    private int alto=-1;
    
    private TFabricaCanvas fabricaCanvas;
    private TgrfNumBalas grfNumBalas;
///////////////////////////////////////PROPIEDADES////////////////////////////////////// 

//////////////////////////////////////CONSTRUCTORES//////////////////////////////////////  
	public TMotorJuego(int _ancho,int _alto) {
		
		ancho=_ancho;
		alto=_alto;
		
		fabricaCanvas=new TFabricaCanvas(ancho, alto);
		//Los css del Modulo
		cssMotorJuego = Recursos.instance.cssMotorJuego();
		
		TCanvas canvasNaveMarciano=fabricaCanvas.getNextCanvas();
		TCanvas canvasBombasMarcianos=fabricaCanvas.getNextCanvas();
		
		TCanvas canvasNaveTerricola=fabricaCanvas.getNextCanvas();
		TCanvas canvasBombasTerricolas=fabricaCanvas.getNextCanvas();
		
		naveTerricola=new TNaveTerricola(canvasNaveTerricola,canvasBombasTerricolas,30);
		
		naveMarcianos=new TNaveMarciano[4];
	    naveMarcianos[0]=new TNaveMarciano(canvasNaveMarciano,canvasBombasMarcianos,ancho-50,100, 46, 42,Posicion.ARRIBA );
	    naveMarcianos[1]=new TNaveMarciano(canvasNaveMarciano,canvasBombasMarcianos,ancho-140,300, 46, 42,Posicion.ABAJO );
	    naveMarcianos[2]=new TNaveMarciano(canvasNaveMarciano,canvasBombasMarcianos,ancho-230,250, 46, 42,Posicion.ARRIBA );
	    naveMarcianos[3]=new TNaveMarciano(canvasNaveMarciano,canvasBombasMarcianos,ancho-320,150, 46, 42,Posicion.ABAJO );
	    
		teclaPresionadaCTRL=false;
		
		frmMenu=new TfrmMenu();
		 		 
	}//end functions
	///////////////////////////////////////METODOS PUBLICOS//////////////////////////////////////
	private void condicionesIniciales(){
		//int _altura=context.getCanvas().getHeight();
		/*  
		naveMarcianos[0].setY(y);
		naveMarcianos[1].setY(y);
		naveMarcianos[2].setY(y);
		naveMarcianos[3].setY(y);
		*/
		/* REPARAR!!!!!
		 * Cuando se redimensiona falta dimensionar las posiciones de los marcianos en
		 * caso el Ancho Alto disminuyan
		 */
		 inicioMovimientoMarcianos();
		 naveTerricola.setX(30);
		 naveTerricola.setY(30);
 		 naveTerricola.DibujarNaveArriba();
 		 eliminacionObjetosTimer();
	}
	public void ejecutar(){
	 //cremamos la imagen de fondo
	 crearEscenario();
	 
	 intervalo= new Date().getTime();
	
	 focusPanel.addKeyDownHandler(new  KeyDownHandler() {
			
		
			public void onKeyDown(KeyDownEvent event) {
				
				if(event.getNativeKeyCode()==KeyCodes.KEY_RIGHT){
					if(naveTerricola.getEstado()==Estado.VIVO)
						naveTerricola.DibujarNaveDerecha();
				}//end if
				if(event.getNativeKeyCode()==KeyCodes.KEY_LEFT){
					if(naveTerricola.getEstado()==Estado.VIVO)
					  naveTerricola.DibujarNaveIzquierda();
				}//end if
				if(event.getNativeKeyCode()==KeyCodes.KEY_CTRL){
					//EVITA QUE SE MANTEGA PRESIONADO CTRL
					if(teclaPresionadaCTRL==false){
						long now=new Date().getTime();
						//CONTROLAMOS EL TIEMPO ENTRE LA SALIDA DE CADA BOMBA
						if(now-intervalo>1000){
							teclaPresionadaCTRL=true;
							if(naveTerricola.getEstado()==Estado.VIVO)	
								naveTerricola.DispararBomba();
							intervalo=now;
						}
					}//end if						
				}//end if
                if(event.getNativeKeyCode()==KeyCodes.KEY_UP){
                	if(naveTerricola.getEstado()==Estado.VIVO)		
                		naveTerricola.DibujarNaveArriba();
                	
				}//end if
				if(event.getNativeKeyCode()==KeyCodes.KEY_DOWN){
					if(naveTerricola.getEstado()==Estado.VIVO)		
						naveTerricola.DibujarNaveAbajo();
					
				}//end if
				if(event.getNativeKeyCode()==KeyCodes.KEY_ESCAPE){
					//MOSTRAMOS el CUADRO DE DIALOGO
					if(naveTerricola.getEstado()==Estado.DESTRUIDO){
						//Recargamos el SITE
						Window.Location.reload();
					}
					else{
						//CARGAMOS FRMMENU
						frmMenu.txtAncho.setText(NumberFormat.getFormat("#").format(Window.getClientWidth()));
						frmMenu.txtAlto.setText(NumberFormat.getFormat("#").format(Window.getClientHeight()-1));
						
						frmMenu.PresionoAplicar=false;
						//PARAMOS TODOS LOS TIMERS DEL JUEGO
						cancelTimer();
						//centramos el formulario
						frmMenu.center();
					}
				}//end if
				
			}//end function
		});//end evento
	 
	 focusPanel.addKeyUpHandler(new KeyUpHandler() {
			
		
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				if(event.getNativeKeyCode()==KeyCodes.KEY_CTRL){
					//en caso la tecla se mantega presionada 
					teclaPresionadaCTRL=false;
	
				}//end if
				
			}//end function
		});//end evento	

	 	condicionesIniciales();
	 	
	 	/*
	 	//CARGAMOS FRMMENU
	 	frmMenu.txtAncho.setText(NumberFormat.getFormat("#").format(Window.getClientWidth()));
		frmMenu.txtAlto.setText(NumberFormat.getFormat("#").format(Window.getClientHeight()-1));
		
		frmMenu.PresionoAplicar=false;
		//PARAMOS TODOS LOS TIMERS DEL JUEGO
		cancelTimer();
		//centramos el formulario
		frmMenu.center();
	 	*/
	  }
//Inicio Movimiento de Marcianos
 public void inicioMovimientoMarcianos(){
	 
	 for(TNaveMarciano _naveMarciano:naveMarcianos){
		// if(_naveMarciano.getEstado()== Estado.VIVO)
			 	_naveMarciano.EjecutarMovimiento();	 
	 }//end for
	 
 }//end function
 // Permite eliminar las Bombas que salieron
 // del Canvas para el rendimiento de la Busqueda
 private void EliminarBombasLimiteCanvas(){
	 //BOMBAS NAVE TERRICOLA
	 ArrayList<TBomba> bombas=naveTerricola.getBombas();
	 for(TBomba bomba:bombas){
			if(bomba.getEstado()==EstadoBomba.DESTRUIDO){
				continue;
			}//end if
			if(bomba.getX()>ancho){
				bomba.setEstado(EstadoBomba.DESTRUIDO);
				bomba=null;
			}
	 }//end for		
	 
	 //BOMBAS NAVE MARCIANO
	 for(TNaveMarciano _naveMarciano: naveMarcianos){
		 //if(_naveMarciano.estado==Estado.DESTRUIDO){
		//	 continue;
		 //}
		 ArrayList<TBomba> bombas1=_naveMarciano.getBombas();
		 for(TBomba bomba:bombas1){
				if(bomba.getEstado()==EstadoBomba.DESTRUIDO){
					continue;
				}//end if
				if(bomba.getX()<0){
					bomba.setEstado(EstadoBomba.DESTRUIDO);
					bomba=null;
				}
		 }//end for
	 }//end for	
 }//end function

///////////////////////////////////////METODOS PRIVADOS//////////////////////////////////////
 private void RedimensionarEscenario(){
	 
	 int _width=ancho;
	 int _height=alto;
	 
	 //Redimensionamos Canvas
	  fabricaCanvas.changeAnchoAltoListCanvas(ancho, alto);
	  
	//Cambiamos AbsolutePanel
	 absolutePanel.setWidth(Double.toString(_width)+"px");
	 absolutePanel.setHeight(Double.toString(_height)+"px");
	 
	//Cambiamos FoculPanel
	 focusPanel.setWidth(Double.toString(_width)+"px");
	 focusPanel.setHeight(Double.toString(_height)+"px");
		
	 //Cambiamos Imagen de Fondo
	 imgFondo.setWidth(Double.toString(_width)+"px");
	 imgFondo.setHeight(Double.toString(_height)+"px");
	 
	 //Cambiamos Posicion de los Textos;
	 absolutePanel.setWidgetPosition(mensajePantalla, (int)(_width/2-50),10);
	 absolutePanel.setWidgetPosition(creditos,(int)(_width-150),(int)(_height-20));
	 
 }
 private void crearEscenario(){
	 //creamos sonido de fondo
	 TFabricaMultimedia _music=TFabricaMultimedia.getInstance();
	 _music.getMultimedia().sonidoFondo();
	 
	 ImagenesBundle imagenes=Recursos.instance.imagenes();
	 
	 int _width=ancho;
	 int _height=alto;
	 
			absolutePanel=new AbsolutePanel();
			
			imgFondo=new Image(imagenes.fondoJuego());
			
			mensajeNumBalas=new Label("Balas :"+Integer.toString(naveTerricola.getNumBalas()));
			mensajeNumBalas.setStyleName(cssMotorJuego.gwtLabelMoficado());
			
			mensajePantalla=new Label("Ayuda <ESC>");
			creditos=new Label("Designed by @Jumanor");
			focusPanel=new FocusPanel();
			focusPanel.setWidth(Double.toString(_width)+"px");
			focusPanel.setHeight(Double.toString(_height)+"px");
			
			//agregamos hojas de estilo a los LABEL's
			mensajePantalla.setStyleName(cssMotorJuego.gwtLabelMoficado());
			creditos.setStyleName(cssMotorJuego.gwtLabelMoficado());
	 
	 absolutePanel.setWidth(Double.toString(_width)+"px");
	 absolutePanel.setHeight(Double.toString(_height)+"px");	
	 
	 imgFondo.setWidth(Double.toString(_width)+"px");
	 imgFondo.setHeight(Double.toString(_height)+"px");	
	 
	 absolutePanel.add(imgFondo, 0, 0);
	 absolutePanel.add(mensajePantalla,(int)(_width/2-50),10);
	 absolutePanel.add(creditos,(int)(_width-150),(int)(_height-20));
	 
	 absolutePanel.add(mensajeNumBalas,15,15);
	 
	 grfNumBalas=new TgrfNumBalas(0, 0, 80, 30);
	 
	 absolutePanel.add(grfNumBalas.getCanvas().getCanvas(),100,10);
		 /*
		  * FocusPanel capturar los eventos del Teclado (TODOS LOS NAVEGADORES)
		  * Canvas no soporta la captura de eventos con IE
		  */
	 //INICIO INSERTAMOS LOS CANVAS

	 	fabricaCanvas.insertListCanvasToParent(absolutePanel);
	 	
	 	absolutePanel.add(focusPanel, 0, 0);
	 	
	 	//TextBox edt=new TextBox();
	 	//edt.setFocus(true);
	 	//absolutePanel.add(edt,0,0);
	 	
	 	//focusPanel.getElement().getStyle().setZIndex(100);
	 	
	 //FIN INSERCION CANVAS
	 	
	 	//ESTE FOCUS FUNCIONA CON TODOS LOS NAVEGADORES   
	 	focusPanel.setFocus(true);
	 	
	 	//RootPanel.getBodyElement().getStyle().setProperty("margin", "0px");
	 	Window.setMargin("0px");
	 	RootPanel.get("wCanvas").add(absolutePanel);
	 	
	 	focusPanel.setFocus(true);
	 	/*
	  * Creamos un evento cuendo se cierre el Cuadro de Dialogo de Opciones
	  */
	 
     frmMenu.addCloseHandler(new CloseHandler<PopupPanel>() {
		
		
		public void onClose(CloseEvent<PopupPanel> event) {
			 if(frmMenu.PresionoAplicar==true){
				 
				 		 ancho=frmMenu.txtAncho.getValue();
				 		 alto=frmMenu.txtAlto.getValue();
				 		 
				 		 RedimensionarEscenario();
				 		 /*
				 		  * Limpiamos los Canvas...Esto debe de modificarse a FUTURO para el soporte de <PAUSE>
				 		  */
				 		 fabricaCanvas.clearListCanvas();
				 		
				 		//Iniciamos el juego
				 		 condicionesIniciales();
				  }
			 focusPanel.setFocus(true);		   //ESTO FUNCIONA CON TODOS LOS NAVEGADORES
			 //canvasNave.getCanvas().setFocus(true);//ESTO NO FUNCIONA CON IE
		}
	});	
		 
 }//end function
 /**
  * La Nave Terricola es eliminado por una Bomba Marciana
  * 
  */
 private void EliminarNaveTerricola(){
	 	if(naveTerricola.getEstado()==Estado.DESTRUIDO){
			return;
		}//end if
	 	
		ArrayList<TBomba> bombasMarciano;
		salida:
		 for(TNaveMarciano _naveMarciano:naveMarcianos){
			 /*
			 if(_naveMarciano.getEstado()==Estado.DESTRUIDO){
	    			continue;
	    		}//end if
			 */
			 bombasMarciano=_naveMarciano.getBombas();
			 for(TBomba bomba:bombasMarciano){
							 if(bomba.getEstado()==EstadoBomba.DESTRUIDO){
					    			continue;
					    		}//end if
				 			double _x1=bomba.getX()+bomba.getW();
							double _w1=bomba.getW();
							double _y1=bomba.getY();
							double _h1=bomba.getH();
							
							double _x2=naveTerricola.getX();
							double _w2=naveTerricola.getW();
							double _y2=naveTerricola.getY();
							double _h2=naveTerricola.getH();
							
							//ALGORITMO DE INTERSECCION DE AREAS
							if(InterseccionX(_x1,_w1,_x2,_w2)==1 && InterseccionY(_y1,_h1,_y2,_h2)==1){
							
								//Window.alert("");
								naveTerricola.destruir();
								naveTerricola.setEstado(Estado.DESTRUIDO);
								//naveTerricola=null;
								
								bomba.setEstado(EstadoBomba.DESTRUIDO);
			    				bomba.Explotar();
			    			
								break salida;
							}//end if			
			 }//end for
		 }//end for
	}//end functino
 private void eliminarColisionBombas(){
	 /*
	 if(naveTerricola.getEstado()==Estado.DESTRUIDO){
			return;
		}//end if
	 	*/
		ArrayList<TBomba> bombasMarciano;
		ArrayList<TBomba> bombasTerricola;
		salida:
		 for(TNaveMarciano _naveMarciano:naveMarcianos){
			 /*
			 if(_naveMarciano.getEstado()==Estado.DESTRUIDO){
	    			continue;
	    		}//end if
			 */
			 bombasMarciano=_naveMarciano.getBombas();
			 for(TBomba _bmbMarciano:bombasMarciano){
				 
							 if(_bmbMarciano.getEstado()==EstadoBomba.DESTRUIDO){
					    			continue;
					    		}//end if
						 
					 			double _x1=_bmbMarciano.getX()+_bmbMarciano.getW();
								double _w1=_bmbMarciano.getW();
								double _y1=_bmbMarciano.getY();
								double _h1=_bmbMarciano.getH();
							
							bombasTerricola=naveTerricola.getBombas();
							 
							for(TBomba _bmbTerricola: bombasTerricola){
								if(_bmbTerricola.getEstado()==EstadoBomba.DESTRUIDO){
					    			continue;
					    		}//end if
								double _x2=_bmbTerricola.getX();
								double _w2=_bmbTerricola.getW();
								double _y2=_bmbTerricola.getY();
								double _h2=_bmbTerricola.getH();
								
								//ALGORITMO DE INTERSECCION DE AREAS
								if(InterseccionX(_x1,_w1,_x2,_w2)==1 && InterseccionY(_y1,_h1,_y2,_h2)==1){
								
									_bmbTerricola.setEstado(EstadoBomba.DESTRUIDO);
									_bmbTerricola.Explotar(false);
				    			
									_bmbMarciano.setEstado(EstadoBomba.DESTRUIDO);
				    				_bmbMarciano.Explotar(true);
				    			
									break salida;
								}//end if		
								
							}//end for
			 }//end for
		 }//end for
	 
	 
 }//end function
 private void EliminarNavesMarcianas(){
	ArrayList<TBomba> bombas=naveTerricola.getBombas();
	
	salida:
	for(TBomba bomba:bombas){
		if(bomba.getEstado()==EstadoBomba.DESTRUIDO){
			continue;
		}
    	double x=bomba.getX()+20;
    	double y=bomba.getY();
    	for(TNaveMarciano nave:naveMarcianos){
    		
    		if(nave.getEstado()==Estado.DESTRUIDO){
    			continue;
    		}//end if
    		
    		if(nave.getX()< x && x < nave.getX()+ nave.getW()){
    			if(nave.getY()-20< y && y < nave.getY()+ nave.getH()-10){ 			
    				
    				nave.setEstado(Estado.DESTRUIDO);
    				nave.destruir();
    				
    				bomba.setEstado(EstadoBomba.DESTRUIDO);
    				bomba.Explotar();
    				
    				break salida;//SALIMOS DE LOS BUCLES FOR ANIDADOS
    			}//end if
    		}//end if
    	}//end for
    }//end for
    
}//end function
 /*
  * Cancelamos los Timer para reinicio
  */
 public void cancelTimer(){
	 /*
	  * OPTIMIZAR EL CODIGO !!!!!!!!!!!!!!
	  * FALTAR REMOVER LOS ELEMENTOS NULL DE LAS LISTAS
	  */
	 if(timerEliminacionObjetos!=null)
		 timerEliminacionObjetos.cancel();
	 
	 ArrayList<TBomba> bombas=naveTerricola.getBombas();
	 
	 for(TBomba _bomba: bombas){
		 if(_bomba!=null){
			 //removemos la bomba del ARRAY
			 //bombas.remove(_bomba);
			 //cancelamos su Timer y null para el GARBAGE COLLECTION
			 _bomba.cancelTimer();
			 _bomba.setEstado(EstadoBomba.DESTRUIDO);
			 _bomba=null;
			  
		 }
			 
	 }
	 
	 for(TNaveMarciano _naveM: naveMarcianos){
		 bombas=_naveM.getBombas();
		 	for(TBomba _bombaa: bombas){
		 		if(_bombaa!=null){
		 			
		 			// bombas.remove(_bombaa);
		 			_bombaa.cancelTimer();
		 			_bombaa.setEstado(EstadoBomba.DESTRUIDO);
		 			_bombaa=null;
		 		}
		 			
		 	}
		 if(_naveM!=null)
			_naveM.cancelTimer();
	 }
	 
 }
 
 private void eliminacionObjetosTimer()
 {
	 timerEliminacionObjetos=new Timer() {
		
		@Override
		public void run() {
			EliminarNavesMarcianas();
			EliminarBombasLimiteCanvas();
			EliminarNaveTerricola();
			eliminarColisionBombas();
			
			//actualizamos numBalas
			mensajeNumBalas.setText("Balas :"+Integer.toString(naveTerricola.getNumBalas()));
			grfNumBalas.llenado(100*naveTerricola.getNumBalas()/(double)naveTerricola.getTotalBalas());
		}//end function
	};
	timerEliminacionObjetos.scheduleRepeating(100);
 }//end function

	private int InterseccionX(double _x1,double _w1,double _x2,double _w2){
	int estado=0;
			
	if(_x1<=_x2+_w2 && _x2+_w2<=_x1+_w1){
		//Window.alert("1");
		estado=1;
	}//end if
	else if(_x1<=_x2 && _x2<=_x1+_w1){
		//Window.alert("2");
		estado=1;
	}//end if
	else if(_x2<=_x1+_w1 && _x1+_w1<=_x2+_w2){
		//Window.alert("3");
		estado=1;
	}//end if
	else if(_x2<=_x1 && _x1<=_x2+_w2){
		//Window.alert("4");
		estado=1;
	}//end if
	
	return estado;
}//end function
private int InterseccionY(double _y1,double _h1,double _y2,double _h2){
	int estado=0;
	if(_y1<=_y2+_h2 && _y2+_h2<=_y1+_h1){
		estado=1;

	}//end if
	else if(_y1<=_y2 && _y2<=_y1+_h1){
		estado=1;
	
	}//end if
	else if(_y2<=_y1+_h1 && _y1+_h1<=_y2+_h2){
		estado=1;
		
	}//end if
	else if(_y2<=_y1 && _y1<=_y2+_h2){
		estado=1;
	
	}//end if

	return estado;
}

}//end class
