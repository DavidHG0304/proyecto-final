package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;
import vista.VistaPanelCategorias;

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
			
		}
	}

}
