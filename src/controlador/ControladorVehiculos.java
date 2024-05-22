package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.entidades.Vehiculo;
import vista.VistaPanelVehiculos;

public class ControladorVehiculos implements ActionListener{
	
	private VistaPanelVehiculos pVehiculos;
	private Modelo modelo;
	private Controlador controlador;

	public ControladorVehiculos(VistaPanelVehiculos pVehiculos, Modelo modelo, Controlador controlador) {
		this.pVehiculos = pVehiculos;
        this.modelo = modelo;
        this.controlador = controlador;
        pVehiculos.panelVehiculos();
		pVehiculos.asignarActListner(this);
		cargarVehiculos();
	}
	
	public void cargarVehiculos() {
        SwingWorker<ArrayList<Vehiculo>, Void> worker = new SwingWorker<ArrayList<Vehiculo>, Void>() {
            @Override
            protected ArrayList<Vehiculo> doInBackground() throws Exception {
                ArrayList<Vehiculo> vehiculos = modelo.obtenerVehiculos();
                modelo.obtenerImagenesVehiculos(vehiculos);
                return vehiculos;
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Vehiculo> vehiculos = get();
                    pVehiculos.mostrarVehiculos(vehiculos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()){	
		case "Inicio pVehiculos":
			pVehiculos.getFrame().dispose();
			controlador.panelPrincipal();
			break;
		case "Marcas pVehiculos":
			pVehiculos.getFrame().dispose();
			controlador.marcas();
			break;
		case "Categorias pVehiculos":
			pVehiculos.getFrame().dispose();
			controlador.categorias();
			break;
		case "Rentas pVehiculos":
			pVehiculos.getFrame().dispose();
			controlador.rentas();
			break;
		case "Clientes pVehiculos":
			pVehiculos.getFrame().dispose();
			controlador.clientes();
			break;
		case "Cerrar Sesión pVehiculos":
			pVehiculos.getFrame().dispose();
			controlador.login();
			controlador.nuevoModelo.setRegistroEncontrado(false);
			break;
			
		}
	}

}
