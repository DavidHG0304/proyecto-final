package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.entidades.Categorias;
import modelo.entidades.Marcas;
import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelVehiculoAccion;
import vista.VistaPanelVehiculos;
import vista.componentes.CartasCarros;
import vista.componentes.DialogoAvisos;
import vista.componentes.DialogoConfirmacion;
import vista.componentes.DialogoDetalles;
import vista.componentes.DialogoInfoCarro;
import vista.componentes.DialogoRentar;
import vista.componentes.DialogoAniadir;

public class ControladorVehiculos implements ActionListener{
	
	private VistaPanelVehiculos pVehiculos;
	private VistaPanelVehiculoAccion pVAccion;
	private Modelo modelo;
	private Controlador controlador;
	
	private Vehiculos vehiculoMostrar;
	private DialogoInfoCarro dialogoInfoCarro;
	private DialogoAniadir dialogoAniadir;

	public ControladorVehiculos(VistaPanelVehiculos pVehiculos, Modelo modelo, Controlador controlador) {
		this.pVehiculos = pVehiculos;
        this.modelo = modelo;
        this.controlador = controlador;
        pVehiculos.panelVehiculos();
		pVehiculos.asignarActListner(this);
		
		
		
		cargarVehiculos();
		GlassPanePopup.install(pVehiculos.getFrame());

	}
	
	// Implementación de swingworker para poder optimizar la actualización, ejecuta primero la consulta a la base de datos para después de eso llamar al metodo done()
	// una vez haya terminado la consulta para poder mostrar bien la interfaz y todo
	public void cargarVehiculos() {
        SwingWorker<ArrayList<Vehiculos>, Void> cargadprVehiculos = new SwingWorker<ArrayList<Vehiculos>, Void>() {
            @Override
            protected ArrayList<Vehiculos> doInBackground() throws Exception {
                return modelo.obtenerVehiculos();
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
        cargadprVehiculos.execute();
    }
	
	public ArrayList<String> obtenerNombresCategorias() {
		ArrayList<Categorias> categorias = modelo.mostrarCategorias();
		ArrayList<String> nombresCategorias = new ArrayList<>();
		for (Categorias categoria : categorias) {
			nombresCategorias.add(categoria.getNombre());
		}

		return nombresCategorias;
	}

	public ArrayList<String> obtenerNombresMarcas() {
		ArrayList<Marcas> marcas = modelo.mostrarMarcas();
		ArrayList<String> nombresMarcas = new ArrayList<>();
		for (Marcas marca : marcas) {
			nombresMarcas.add(marca.getNombre());
		}

		return nombresMarcas;
	}
	
	
	public void prepararVehiculoDetalles(Vehiculos vehiculo) {
		vehiculoMostrar = vehiculo;
		dialogoInfoCarro = new DialogoInfoCarro(vehiculo);
		GlassPanePopup.showPopup(dialogoInfoCarro);
	}
	
	public void prepararVehiculoEditar(Vehiculos vehiculo) {
		vehiculoMostrar = vehiculo;
		ArrayList<String> categorias = obtenerNombresCategorias();
		ArrayList<String> marcas = obtenerNombresMarcas();
		
		dialogoAniadir = new DialogoAniadir("Editar vehiculo", vehiculo, marcas, categorias);
		GlassPanePopup.showPopup(dialogoAniadir);
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
			Vehiculos vehiculoSeleccionado = pVehiculos.getVehiculoSeleccionado();
			prepararVehiculoDetalles(vehiculoSeleccionado);
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
//			System.out.println("Editar");
//			vehiculoSeleccionado = pVehiculos.getVehiculoSeleccionado();
//			GlassPanePopup.showPopup(new DialogoAniadir("Editar vehiculo", vehiculoSeleccionado));
			
			vehiculoSeleccionado = pVehiculos.getVehiculoSeleccionado();
			prepararVehiculoEditar(vehiculoSeleccionado);
			
			break;
		case "AgregarVehiculo":
			Vehiculos nVehiculo = new Vehiculos();
			ArrayList<String> categorias = obtenerNombresCategorias();
			ArrayList<String> marcas = obtenerNombresMarcas();
			GlassPanePopup.showPopup(new DialogoAniadir("Crear vehiculo", nVehiculo, marcas, categorias));
			break;
		case "Detalles":
			GlassPanePopup.showPopup(new DialogoDetalles("Test"));
			break;
		}	
	}
}
