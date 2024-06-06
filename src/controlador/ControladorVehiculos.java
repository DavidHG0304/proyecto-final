package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.Modelo.RentasAsociadasException;
import modelo.entidades.Categorias;
import modelo.entidades.Marcas;
import modelo.entidades.Rentas;
import modelo.entidades.Usuarios;
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
	private DialogoConfirmacion dialogoConfirmacion;
	private Vehiculos vehiculoSeleccionadoParaEliminar;

	public ControladorVehiculos(VistaPanelVehiculos pVehiculos, Modelo modelo, Controlador controlador) {
		this.pVehiculos = pVehiculos;
        this.modelo = modelo;
        this.controlador = controlador;
        pVehiculos.setControlador(this);
        pVehiculos.panelVehiculos();
		pVehiculos.asignarActListner(this);
		
//		modelo.editarVehiculos(13, "Nuevo", "2020",4, "Manual", true, "Modelo Prueba", "Deportivo", "Toyota");
		
//		modelo.aniadirVehiculo("Toyota Corolla", "2020", 4, 30000, "Automático", true, "Corolla", "Deportivo", "Toyota", "", 1000, 500, 0, 500, 150);
//		modelo.eliminarVehiculo(24);
		
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
	
	public ArrayList<String> obtenerNombresUsuarios() {
		ArrayList<Usuarios> usuarios = modelo.obtenerUsuarios();
		ArrayList<String> nombresUsuarios = new ArrayList<>();
		for (Usuarios usuario : usuarios) {
			nombresUsuarios.add(usuario.getNombreUsuario());
		}

		return nombresUsuarios;
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
		
		String nombreCategoria = vehiculo.getCategoria();
	    String nombreMarca = vehiculo.getMarcas();
		dialogoAniadir = new DialogoAniadir("Editar vehiculo", vehiculo, categorias, marcas, nombreCategoria, nombreMarca);
		dialogoAniadir.getBtnAgregar().addActionListener(this);
		GlassPanePopup.showPopup(dialogoAniadir);
	}
	
	public void prepararEliminar(Vehiculos vehiculo) {
		vehiculoSeleccionadoParaEliminar = vehiculo;
		dialogoConfirmacion = new DialogoConfirmacion("¿Estas seguro de querer \neliminar el auto?", "");
		dialogoConfirmacion.getBoton().setActionCommand("ConfirmarEliminar");
		dialogoConfirmacion.getBoton().addActionListener(this);
		GlassPanePopup.showPopup(dialogoConfirmacion);
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
//		case "Borrar Vehiculo":
//			vehiculoSeleccionadoParaEliminar = pVehiculos.getVehiculoSeleccionado();
//            prepararEliminar(vehiculoSeleccionadoParaEliminar);
//			break;
		case "Rentar":
			// To - do
			ArrayList<String> usuarios = obtenerNombresUsuarios();
			vehiculoSeleccionado = pVehiculos.getVehiculoSeleccionado();
			GlassPanePopup.showPopup(new DialogoRentar("Test", "Crear Renta", vehiculoSeleccionado, usuarios));
			break;
		case "Editar Vehiculo":
			vehiculoSeleccionado = pVehiculos.getVehiculoSeleccionado();
			prepararVehiculoEditar(vehiculoSeleccionado);
			
			break;
		case "AgregarVehiculo":
			Vehiculos nVehiculo = new Vehiculos();
			ArrayList<String> categorias = obtenerNombresCategorias();
			ArrayList<String> marcas = obtenerNombresMarcas();
			
			
			dialogoAniadir = new DialogoAniadir("Crear vehiculo", nVehiculo, marcas, categorias, null, null);
			dialogoAniadir.getBtnAgregar().addActionListener(this);
			GlassPanePopup.showPopup(dialogoAniadir);
			break;
		case "Detalles":
			vehiculoSeleccionado = pVehiculos.getVehiculoSeleccionado();
			GlassPanePopup.showPopup(new DialogoDetalles("Test", modelo.obtenerRentasPorVehiculo(vehiculoSeleccionado.getIdVehiculo()), vehiculoSeleccionado));
			break;
		case "CrearUnVehiculo":
			System.out.println("Va a crear");
			
			String nombre = dialogoAniadir.getTxtNombre().getText();
		    String anio = dialogoAniadir.getTxtAnio().getText();
		    String puertasString = dialogoAniadir.getGrupoPuertas().getSelection() != null ? dialogoAniadir.getGrupoPuertas().getSelection().getActionCommand() : "0";
		    int cantidadPuertas = 0;
		    if (!puertasString.equals("0")) {
		        cantidadPuertas = Integer.parseInt(puertasString);
		    } else {
		        System.out.println("Seleccione la cantidad de puertas");
		        return;
		    }
		    String transmision = (String) dialogoAniadir.getComboBox().getSelectedItem();
		    boolean aireAcondicionado = dialogoAniadir.getGrupoAireRadioButton().getSelection().getActionCommand().equals("Si");
		    String modeloCarro = dialogoAniadir.getTxtModelo().getText();
		    String nombreCategoria = (String) dialogoAniadir.getComboBoxCategorias().getSelectedItem();
		    String nombreMarca = (String) dialogoAniadir.getComboBoxMarcas().getSelectedItem();
            
		    float seguroDanios = Float.parseFloat(dialogoAniadir.getTxtSeguroDanios().getText());
		    float seguroVida = Float.parseFloat(dialogoAniadir.getTxtSeguroVida().getText());
		    float seguroKilometraje = Float.parseFloat(dialogoAniadir.getTxtSeguroKilometraje().getText());
		    float combustible = Float.parseFloat(dialogoAniadir.getTxtSeguroCombustible().getText());
		    float tarifaPorDia = Float.parseFloat(dialogoAniadir.getTxtSeguroTarifa().getText());
		    
		    boolean resultado = modelo.aniadirVehiculo(nombre, anio, cantidadPuertas, 3000, transmision, aireAcondicionado, modeloCarro, nombreMarca, nombreCategoria, "https://firebasestorage.googleapis.com/v0/b/fotinhoscarros.appspot.com/o/bugatata.png?alt=media&token=41feddc5-379f-429e-9040-16d7cd4bb739", seguroDanios, seguroVida, seguroKilometraje, combustible, tarifaPorDia);
			if (resultado) {
				GlassPanePopup.closePopupLast();
				GlassPanePopup.showPopup(new DialogoAvisos("Vehiculo Creado", "El vehiculo ha sido credo con exito"));
		        cargarVehiculos();
		    } else {
		    	GlassPanePopup.closePopupLast();
		    	GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se ha podido crear el vehiculo"));
		    }
			break;
		case "EditarUnVehiculo":
			System.out.println("Va a Editar");
			vehiculoSeleccionado = pVehiculos.getVehiculoSeleccionado();
			
			nombre = dialogoAniadir.getTxtNombre().getText();
		    anio = dialogoAniadir.getTxtAnio().getText();
		    puertasString = dialogoAniadir.getGrupoPuertas().getSelection() != null ? dialogoAniadir.getGrupoPuertas().getSelection().getActionCommand() : "0";
		    cantidadPuertas = 0;
		    if (!puertasString.equals("0")) {
		        cantidadPuertas = Integer.parseInt(puertasString);
		    } else {
		    	GlassPanePopup.closePopupLast();
		    	GlassPanePopup.showPopup(new DialogoAvisos("Error", "Faltan campos por rellenar"));
		        return;
		    }
		    transmision = (String) dialogoAniadir.getComboBox().getSelectedItem();
		    aireAcondicionado = dialogoAniadir.getGrupoAireRadioButton().getSelection().getActionCommand().equals("Si");
		    modeloCarro = dialogoAniadir.getTxtModelo().getText();
		    nombreCategoria = (String) dialogoAniadir.getComboBoxCategorias().getSelectedItem();
		    nombreMarca = (String) dialogoAniadir.getComboBoxMarcas().getSelectedItem();
            
		    seguroDanios = Float.parseFloat(dialogoAniadir.getTxtSeguroDanios().getText());
		    seguroVida = Float.parseFloat(dialogoAniadir.getTxtSeguroVida().getText());
		    seguroKilometraje = Float.parseFloat(dialogoAniadir.getTxtSeguroKilometraje().getText());
		    combustible = Float.parseFloat(dialogoAniadir.getTxtSeguroCombustible().getText());
		    tarifaPorDia = Float.parseFloat(dialogoAniadir.getTxtSeguroTarifa().getText());
		    
		    resultado = modelo.editarVehiculos(vehiculoSeleccionado.getIdVehiculo(), nombre, anio, cantidadPuertas, transmision, aireAcondicionado, modeloCarro, nombreCategoria, nombreMarca, seguroDanios, seguroVida, seguroKilometraje, combustible, tarifaPorDia);
			if (resultado) {
				cargarVehiculos();
				GlassPanePopup.closePopupLast();
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Actualizado", "El vehiculo ha sido \nactualizado correctamente."));
				});
				return;
		    }
			break;
			
		case "ConfirmarEliminar":
			boolean eliminado = false;
			try {
				
				eliminado = modelo.eliminarVehiculo(vehiculoSeleccionadoParaEliminar.getIdVehiculo());
				cargarVehiculos();
			} catch (RentasAsociadasException e1) {
				// TODO Auto-generated catch block
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Error", e1.getMessage()));
				});
				return;
			}
			
			if(eliminado) {
				GlassPanePopup.closePopupLast();
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Eliminado", "Se ha eliminado el vehiculo."));
				});
				return;
			}
			break;
		}
	}
}
