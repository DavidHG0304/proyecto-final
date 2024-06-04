package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelCategorias;
import vista.componentes.DialogoAvisos;
import vista.componentes.DialogoConfirmacion;
import vista.componentes.DialogoDetalles;
import vista.componentes.DialogoInfoCarro;
import vista.componentes.DialogoRentar;
import vista.componentes.DialogoAniadir;
import vista.componentes.DialogoAniadirC_M;

public class ControladorCategorias implements ActionListener{
	
	private VistaPanelCategorias panelCategorias;
	private Modelo modelo;
	private Controlador controlador;

	public ControladorCategorias(VistaPanelCategorias panelCategorias, Modelo modelo, Controlador controlador) {
		this.panelCategorias = panelCategorias;
        this.modelo = modelo;
        this.controlador = controlador;
        panelCategorias.categorias();
        panelCategorias.asignarActListner(this);
        
        //modelo.mostrarCategorias(1);
        //modelo.aniadirCategorias("nueva categoria");
        //modelo.eliminarCategorias(3);
        modelo.eliminarCategorias(11);
        	
        panelCategorias.asignarListenersCartas(ControladorCategorias.this);
        GlassPanePopup.install(panelCategorias.getFrame());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Inicio pCategorias":
			panelCategorias.getFrame().dispose();
			controlador.panelPrincipal();
			break;
		case "Marcas pCategorias":
			panelCategorias.getFrame().dispose();
			controlador.marcas();
			break;
		case "Rentas pCategorias":
			panelCategorias.getFrame().dispose();
			controlador.rentas();
			break;
		case "Vehiculos pCategorias":
			panelCategorias.getFrame().dispose();
			controlador.vehiculos();
			break;
		case "Clientes pCategorias":
			panelCategorias.getFrame().dispose();
			controlador.clientes();
			break;
		case "Cerrar Sesión pCategorias":
			panelCategorias.getFrame().dispose();
			controlador.login();
			controlador.nuevoModelo.setRegistroEncontrado(false);
			break;
		case "EliminarCategoria":
			GlassPanePopup.showPopup(new DialogoConfirmacion("¿Estas seguro de querer \neliminar la categoria?", ""));
		break;
		case "EditarCategoria":
			System.out.println("Hola");
			GlassPanePopup.showPopup(new DialogoAniadirC_M("Editar nombre de la categoria"));
		break;
		case "Info pVehiculo":
			// TO - DO
			Vehiculos vehiculo = new Vehiculos();
			System.out.println("Info");
			GlassPanePopup.showPopup(new DialogoInfoCarro(vehiculo));
			break;
		case "Borrar Vehiculo":
			System.out.println("Borrar");
			GlassPanePopup.showPopup(new DialogoConfirmacion("¿Estas seguro de querer \neliminar el auto?", ""));
			break;
		case "Rentar":
			// To - do
			GlassPanePopup.showPopup(new DialogoRentar("Test", "Crear Renta", null));
			break;
		case "Editar Vehiculo":
			System.out.println("Editar");
			GlassPanePopup.showPopup(new DialogoAniadir("Editar vehiculo"));
			break;
		case "Agregar Categoria pCategorias":
			GlassPanePopup.showPopup(new DialogoAniadirC_M("Nombre de la nueva categoria"));
			break;
		case "Detalles":
			GlassPanePopup.showPopup(new DialogoDetalles("Test"));
			break;
		}	
	}

}
