package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;
import vista.VistaPanelInicio;

public class ControladorPanelPrincipal2 implements ActionListener{
	
	private VistaPanelInicio panelInicio;
	private Modelo modelo;
	private Controlador controlador;

	public ControladorPanelPrincipal2(VistaPanelInicio panelInicio, Modelo modelo, Controlador controlador) {
		this.panelInicio = panelInicio;
        this.modelo = modelo;
        this.controlador = controlador;
        panelInicio.panelPrincipal();
        panelInicio.asignarActListner(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()){	
		case "Marcas pInicial":
			panelInicio.getFrame().dispose();
			controlador.marcas();
			break;
		case "Categorias pInicial":
			panelInicio.getFrame().dispose();
			controlador.categorias();
			break;
		case "Rentas pInicial":
			panelInicio.getFrame().dispose();
			controlador.rentas();
			break;
		case "Vehiculos pInicial":
			panelInicio.getFrame().dispose();
			controlador.vehiculos();
			break;
		case "Clientes pInicial":
			panelInicio.getFrame().dispose();
			controlador.clientes();
			break;
		case "Cerrar Sesión pInicial":
			panelInicio.getFrame().dispose();
			controlador.nuevoModelo.setRegistroEncontrado(false);
			controlador.login();
			break;
			
		}
	}

}
