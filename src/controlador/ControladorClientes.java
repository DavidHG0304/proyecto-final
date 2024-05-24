package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelClientes;
import vista.VistaPanelRentas;
import vista.componentes.DialogoConfirmacion;
import vista.componentes.DialogoCrearCliente;
import vista.componentes.DialogoDetallesCliente;
import vista.componentes.DialogoRentar;

public class ControladorClientes implements ActionListener{
	
	private VistaPanelClientes panelClientes;
	private Modelo modelo;
	private Controlador controlador;

	public ControladorClientes(VistaPanelClientes panelClientes, Modelo modelo, Controlador controlador) {
		this.panelClientes = panelClientes;
        this.modelo = modelo;
        this.controlador = controlador;
        panelClientes.clientes();
        panelClientes.asignarActListner(this);
        
        panelClientes.asignarListenersCartas(ControladorClientes.this);
        GlassPanePopup.install(panelClientes.getFrame());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Inicio pClientes":
			panelClientes.getFrame().dispose();
			controlador.panelPrincipal();
			break;
		case "Marcas pClientes":
			panelClientes.getFrame().dispose();
			controlador.marcas();
			break;
		case "Categorias pClientes":
			panelClientes.getFrame().dispose();
			controlador.categorias();
			break;
		case "Rentas pClientes":
			panelClientes.getFrame().dispose();
			controlador.rentas();
			break;
		case "Vehiculos pClientes":
			panelClientes.getFrame().dispose();
			controlador.vehiculos();
			break;
		case "Cerrar Sesión pClientes":
			panelClientes.getFrame().dispose();
			controlador.login();
			controlador.nuevoModelo.setRegistroEncontrado(false);
			break;
		case "EliminarCliente":
			System.out.println("Eliminar");
			GlassPanePopup.showPopup(new DialogoConfirmacion("¿Estas seguro de querer \neliminar el cliente?", ""));
			break;
		case "EditarCliente":
			//TO-DO
			System.out.println("Editar");
//			GlassPanePopup.showPopup(new DialogoRentar("Editar nombre de la categoria", "Editar Renta"));
			GlassPanePopup.showPopup(new DialogoCrearCliente("Editar Cliente", "Editar"));
			break;
		case "DetallesCliente":
			System.out.println("Detalles");
			GlassPanePopup.showPopup(new DialogoDetallesCliente(""));
			break;
		case "Agregar Cliente pClientes":
			//TO-DO
			System.out.println("Agregar Cliente pClientes");
//			GlassPanePopup.showPopup(new DialogoRentar("Editar nombre de la categoria", "Crear Renta"));
			GlassPanePopup.showPopup(new DialogoCrearCliente("Crear Cliente", "Crear"));
			break;
		}
	}
}

