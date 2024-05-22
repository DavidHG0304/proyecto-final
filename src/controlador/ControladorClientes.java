package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;
import vista.VistaPanelClientes;
import vista.VistaPanelRentas;

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
			
		}
	}

}

