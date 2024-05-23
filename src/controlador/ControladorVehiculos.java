package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelVehiculoAccion;
import vista.VistaPanelVehiculos;
import vista.componentes.CartasCarros;
import vista.componentes.DialogoAvisos;
import vista.componentes.DialogoEmergentes;
import vista.componentes.DialogoRentar;

public class ControladorVehiculos implements ActionListener{
	
	private VistaPanelVehiculos pVehiculos;
	private VistaPanelVehiculoAccion pVAccion;
	private Modelo modelo;
	private Controlador controlador;

	public ControladorVehiculos(VistaPanelVehiculos pVehiculos, Modelo modelo, Controlador controlador) {
		this.pVehiculos = pVehiculos;
        this.modelo = modelo;
        this.controlador = controlador;
        pVehiculos.panelVehiculos();
		pVehiculos.asignarActListner(this);
		
		
		
		cargarVehiculos();
		GlassPanePopup.install(pVehiculos.getFrame());

	}
	
	public void cargarVehiculos() {
        SwingWorker<ArrayList<Vehiculos>, Void> worker = new SwingWorker<ArrayList<Vehiculos>, Void>() {
            @Override
            protected ArrayList<Vehiculos> doInBackground() throws Exception {
                ArrayList<Vehiculos> vehiculos = modelo.obtenerVehiculos();
                modelo.obtenerImagenesVehiculos(vehiculos);
                return vehiculos;
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Vehiculos> vehiculos = get();
                    pVehiculos.mostrarVehiculos(vehiculos);
                    pVehiculos.asignarListenersCartas(ControladorVehiculos.this);
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
		case "Info pVehiculo":
			Vehiculos vehiculo = new Vehiculos();
			System.out.println("Info");
			GlassPanePopup.showPopup(new DialogoEmergentes((vehiculo.getNombreVehiculo()+" "+vehiculo.getModelo()+" - "+vehiculo.getCategoria()),vehiculo.getPuertasVehiculo(),vehiculo.getAñoVehiculo(),vehiculo.getKilometrajeVehiculo(), vehiculo.getTransmision(), vehiculo.isAireAcondicionado(), vehiculo.getImagenUrl()));
			break;
		case "Borrar Vehiculo":
			System.out.println("Borrar");
			//GlassPanePopup.showPopup(new DialogoAvisos("Campos vacios", "Rellene los campos para poder\ncontinuar con el inicio de sesión."));GlassPanePopup.showPopup(new DialogoAvisos("Campos vacios", "Rellene los campos para poder\ncontinuar con el inicio de sesión."));
			break;
		case "Rentar Vehiculo":
			System.out.println("Rentar");
//			GlassPanePopup.showPopup(new DialogoEmergentes("NULL", "Rellene los campos para poder\ncontinuar con el inicio de sesión."));GlassPanePopup.showPopup(new DialogoAvisos("Campos vacios", "Rellene los campos para poder\ncontinuar con el inicio de sesión."));
			GlassPanePopup.showPopup(new DialogoRentar("Crear vehiculo"));
			break;
		case "Editar Vehiculo":
			System.out.println("Editar");
			//GlassPanePopup.showPopup(new DialogoAvisos("Campos vacios", "Rellene los campos para poder\ncontinuar con el inicio de sesión."));GlassPanePopup.showPopup(new DialogoAvisos("Campos vacios", "Rellene los campos para poder\ncontinuar con el inicio de sesión."));
			GlassPanePopup.showPopup(new DialogoRentar("Editar vehiculo"));
			break;
		}	
	}
}
