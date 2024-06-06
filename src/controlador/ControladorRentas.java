package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.entidades.Categorias;
import modelo.entidades.Marcas;
import modelo.entidades.Rentas;
import modelo.entidades.Tarifas;
import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelRentas;
import vista.componentes.DialogoAniadir;
import vista.componentes.DialogoAniadirC_M;
import vista.componentes.DialogoAvisos;
import vista.componentes.DialogoConfirmacion;
import vista.componentes.DialogoDetalles;
import vista.componentes.DialogoInfoCarro;
import vista.componentes.DialogoRentar;

public class ControladorRentas implements ActionListener{
	
	private Vehiculos vehiculoMostrar;
	private VistaPanelRentas panelRentas;
	private Modelo modelo;
	private Controlador controlador;
	private Rentas rentaSeleccionadaParaEliminar;
	private Rentas rentaSeleccionadaParaEditar;
	private Rentas rentaAniadir;
	private DialogoConfirmacion dialogoConfirmacion;
	private DialogoRentar dialogoRenta;

	public ControladorRentas(VistaPanelRentas panelRentas, Modelo modelo, Controlador controlador) {
		this.panelRentas = panelRentas;
        this.modelo = modelo;
        this.controlador = controlador;
        panelRentas.rentas();
        panelRentas.setControlador(this);
        panelRentas.asignarActListner(this);
        cargarRentas();
        
		panelRentas.asignarListenersCartas(this);
		GlassPanePopup.install(panelRentas.getFrame());
	}

	
	public void cargarRentas() {
		SwingWorker<ArrayList<Rentas>, Void> cargadorRentas = new SwingWorker<ArrayList<Rentas>, Void>() {
			@Override
			protected ArrayList<Rentas> doInBackground() throws Exception {
				return modelo.mostrarRentas();
			}

			@Override
			protected void done() {
				try {
					ArrayList<Rentas> rentas = get();
					panelRentas.mostrarRentas(rentas);
					panelRentas.asignarListenersCartas(ControladorRentas.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		cargadorRentas.execute();
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
	
	public ArrayList<Vehiculos> obtenerVehiculosConTarifas() {
        ArrayList<Vehiculos> vehiculos = modelo.obtenerVehiculos();
        for (Vehiculos vehiculo : vehiculos) {
            if (vehiculo.getTarifa() == null) {
                vehiculo.setTarifa(new Tarifas());
            }
        }
        return vehiculos;
    }
	
	public ArrayList<String> obtenerNombresCarros() {
		ArrayList<Vehiculos> vehiculos = modelo.obtenerVehiculos();
		ArrayList<String> nombresVehiculos = new ArrayList<>();
		for (Vehiculos vehiculo : vehiculos) {
			nombresVehiculos.add(vehiculo.getNombreVehiculo());
		}

		return nombresVehiculos;
	}
	
	private void actualizarInformacionVehiculo(Vehiculos vehiculo) {
	    dialogoRenta.actualizarInformacionVehiculo(vehiculo);
	}
	
	public void prepararEliminacionRenta(Rentas renta) {
		rentaSeleccionadaParaEliminar = renta;
		dialogoConfirmacion = new DialogoConfirmacion("¿Estás seguro de querer eliminar la renta?", "");
		dialogoConfirmacion.getBoton().setActionCommand("ConfirmarEliminar");
		dialogoConfirmacion.getBoton().addActionListener(this);
		GlassPanePopup.showPopup(dialogoConfirmacion);
	}
	
	public void prepararRentaEdicion(Rentas renta) {
		rentaSeleccionadaParaEditar = renta;
	    ArrayList<String> usuarios = obtenerNombresUsuarios();
	    ArrayList<String> vehiculos = obtenerNombresCarros();
	    String nombreUsuario = renta.getUsuario().getNombreUsuario();
	    dialogoRenta = new DialogoRentar("EditarRenta", "Editar Renta", renta.getVehiculo(), usuarios, nombreUsuario,vehiculos);
	    dialogoRenta.getComboBoxVehiculos().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String nombreVehiculoSeleccionado = (String) dialogoRenta.getComboBoxVehiculos().getSelectedItem();
	            Vehiculos vehiculoSeleccionado = modelo.obtenerVehiculoPorNombre(nombreVehiculoSeleccionado);
	            actualizarInformacionVehiculo(vehiculoSeleccionado);
	        }
	    });
	    dialogoRenta.getBtnCrear().addActionListener(this);
		GlassPanePopup.showPopup(dialogoRenta);
	}
	
//	public void prepararAniadirRenta(Rentas renta) {
//		rentaSeleccionadaParaEditar = renta;
//		rentaSeleccionadaParaEditar.getVehiculo().getNombreVehiculo();
//		
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
			prepararEliminacionRenta(panelRentas.getRentaSeleccionada());
			break;
		case "EditarRenta":
//			ArrayList<String> usuarios = obtenerNombresUsuarios();
//			rentaSeleccionadaParaEditar = panelRentas.getRentaSeleccionada();
//			dialogoRenta = new DialogoRentar("Test", "Editar Renta", rentaSeleccionadaParaEditar.getVehiculo(), usuarios);
//			dialogoRenta.getBtnCrear().addActionListener(this);
//			GlassPanePopup.showPopup(dialogoRenta);
			
			
			break;
		case "Agregar Renta pRentas":
			System.out.println("AgregarRenta");
			ArrayList<String> usuarios = obtenerNombresUsuarios();
			ArrayList<String> vehiculos = obtenerNombresCarros();
			ArrayList<Rentas> rentas = modelo.mostrarRentas();
			Vehiculos vehiculoInicial = modelo.obtenerVehiculos().get(0);
			dialogoRenta = new DialogoRentar("Test", "Crear Renta", vehiculoInicial, usuarios, null, vehiculos);
			dialogoRenta.getComboBoxVehiculos().addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            String nombreVehiculoSeleccionado = (String) dialogoRenta.getComboBoxVehiculos().getSelectedItem();
		            Vehiculos vehiculoSeleccionado = modelo.obtenerVehiculoPorNombre(nombreVehiculoSeleccionado);
		            actualizarInformacionVehiculo(vehiculoSeleccionado);
		        }
		    });
			dialogoRenta.getBtnCrear().addActionListener(this);
			GlassPanePopup.showPopup(dialogoRenta);
			break;
		case "ConfirmarEliminar":
            if (modelo.eliminarRenta(rentaSeleccionadaParaEliminar.getId())) {
                cargarRentas();
                SwingUtilities.invokeLater(() -> {
                    GlassPanePopup.closePopupLast();
                    GlassPanePopup.showPopup(new DialogoAvisos("Eliminada", "La renta ha sido eliminada."));
                });
            }else {
            	SwingUtilities.invokeLater(() -> {
                    GlassPanePopup.closePopupLast();
                    GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se pudo eliminar la renta."));
                });
            }
            break;
		case "EditarLaRenta":
			System.out.println("HOLA");
			rentaSeleccionadaParaEditar = panelRentas.getRentaSeleccionada();
			String fechaFinal = dialogoRenta.getTxtFechaFinal().getText();
			String fechaInicial = dialogoRenta.getTxtFechaInicio().getText();
			Double costo = Double.parseDouble(dialogoRenta.getTxtTotal().getText());
			String nombreUsuario = (String) dialogoRenta.getComboBoxUsuarios().getSelectedItem();
			String nombreVehiculo = (String) dialogoRenta.getComboBoxVehiculos().getSelectedItem(); 
			
			boolean resultado2 = modelo.editarRenta(rentaSeleccionadaParaEditar.getId(), fechaFinal, fechaInicial, costo, nombreUsuario, nombreVehiculo, rentaSeleccionadaParaEditar.getVehiculo().getIdVehiculo());
			if (resultado2) {
				cargarRentas();
		        GlassPanePopup.closePopupLast();
		        GlassPanePopup.showPopup(new DialogoAvisos("Renta Actualizada", "La renta se ha actualizado con éxito"));
		    } else {
		        GlassPanePopup.closePopupLast();
		        GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se ha podido actualizar la renta"));
		    }
			break;
			
		case "ConfirmarRenta":
			Vehiculos vehiculoRenta = dialogoRenta.getVehiculo();
			fechaFinal = dialogoRenta.getTxtFechaFinal().getText();
			fechaInicial = dialogoRenta.getTxtFechaInicio().getText();
			costo = Double.parseDouble(dialogoRenta.getTxtTotal().getText());
			nombreUsuario = (String) dialogoRenta.getComboBoxUsuarios().getSelectedItem();
			
			boolean resultado = modelo.aniadirRentas(fechaFinal, fechaInicial, costo, nombreUsuario, vehiculoRenta.getIdVehiculo());
			if (resultado) {
				cargarRentas();
		        GlassPanePopup.closePopupLast();
		        GlassPanePopup.showPopup(new DialogoAvisos("Renta Creada", "La renta ha sido creada con éxito"));
		    } else {
		        GlassPanePopup.closePopupLast();
		        GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se ha podido crear la renta"));
		    }
			break;
		}
	}
}
