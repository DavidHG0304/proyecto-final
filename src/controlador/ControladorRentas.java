package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.entidades.Rentas;
import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelRentas;
import vista.componentes.DialogoAniadir;
import vista.componentes.DialogoAniadirC_M;
import vista.componentes.DialogoConfirmacion;
import vista.componentes.DialogoDetalles;
import vista.componentes.DialogoInfoCarro;
import vista.componentes.DialogoRentar;

public class ControladorRentas implements ActionListener{
	
	private VistaPanelRentas panelRentas;
	private Modelo modelo;
	private Controlador controlador;

	public ControladorRentas(VistaPanelRentas panelRentas, Modelo modelo, Controlador controlador) {
		this.panelRentas = panelRentas;
        this.modelo = modelo;
        this.controlador = controlador;
        panelRentas.rentas();
        panelRentas.asignarActListner(this);
//        cargarRentas();
        
		panelRentas.asignarListenersCartas(ControladorRentas.this);
		GlassPanePopup.install(panelRentas.getFrame());
	}

//	private void inicializar() {
//		ArrayList<Rentas> rentas = modelo.mostrarRentas();
//		panelRentas.mostrarRentas(rentas);
//		
//	}
//	
//	public void cargarRentas() {
//		SwingWorker<ArrayList<Rentas>, Void> cargadorRentas = new SwingWorker<ArrayList<Rentas>, Void>() {
//			@Override
//			protected ArrayList<Rentas> doInBackground() throws Exception {
//				return modelo.mostrarRentas();
//			}
//
//			@Override
//			protected void done() {
//				try {
//					inicializar();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		cargadorRentas.execute();
//	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Inicio pRentas":
			panelRentas.getFrame().dispose();
			controlador.panelPrincipal();
			break;
		case "Marcas pRentas":
			panelRentas.getFrame().dispose();
			controlador.marcas();
			break;
		case "Categorias pRentas":
			panelRentas.getFrame().dispose();
			controlador.categorias();
			break;
		case "Vehiculos pRentas":
			panelRentas.getFrame().dispose();
			controlador.vehiculos();
			break;
		case "Clientes pRentas":
			panelRentas.getFrame().dispose();
			controlador.clientes();
			break;
		case "Cerrar Sesión pRentas":
			panelRentas.getFrame().dispose();
			controlador.login();
			controlador.nuevoModelo.setRegistroEncontrado(false);
			break;
		case "EliminarRenta":
			System.out.println("Eliminar");
			GlassPanePopup.showPopup(new DialogoConfirmacion("¿Estas seguro de querer \neliminar la renta?", ""));
			break;
		case "EditarRenta":
			// To - do
			System.out.println("Editar");
			GlassPanePopup.showPopup(new DialogoRentar("Editar nombre de la categoria", "Editar Renta", null));
			break;
		case "Agregar Renta pRentas":
			System.out.println("AgregarRenta");
			GlassPanePopup.showPopup(new DialogoRentar("Editar nombre de la categoria", "Crear Renta", null));
			break;
		}
	}
}
