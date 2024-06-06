package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.Modelo.RentasAsociadasException;
import modelo.entidades.Categorias;
import modelo.entidades.Marcas;
import modelo.entidades.Usuarios;
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

	private Vehiculos vehiculoMostrar;
	private DialogoInfoCarro dialogoInfoCarro;
	private DialogoAniadir dialogoAniadir;
	private DialogoConfirmacion dialogoConfirmacion;
	private DialogoRentar dialogoRenta;
	private Vehiculos vehiculoSeleccionadoParaEliminar;
	private DialogoAniadirC_M dialogoAniadirC_M;
	
	
	public ControladorCategorias(VistaPanelCategorias panelCategorias, Modelo modelo, Controlador controlador) {
		this.panelCategorias = panelCategorias;
        this.modelo = modelo;
        this.controlador = controlador;
        panelCategorias.setControlador(this);
        ArrayList<String> categorias = obtenerNombresCategorias();
        panelCategorias.categorias(categorias);
        panelCategorias.asignarActListner(this);
        cargarVehiculos();
        panelCategorias.asignarListenersCartas(ControladorCategorias.this);
        GlassPanePopup.install(panelCategorias.getFrame());
	}
	
	public void cargarVehiculosPorCategoria(String categoria) {
        SwingWorker<ArrayList<Vehiculos>, Void> cargadorVehiculos = new SwingWorker<ArrayList<Vehiculos>, Void>() {
            @Override
            protected ArrayList<Vehiculos> doInBackground() throws Exception {
                return modelo.obtenerVehiculosPorCategoria(categoria);
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Vehiculos> vehiculos = get();
                    panelCategorias.mostrarVehiculos(vehiculos);
                    panelCategorias.asignarListenersCartas(ControladorCategorias.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        cargadorVehiculos.execute();
    }
	
	
	
	
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
                    panelCategorias.mostrarVehiculos(vehiculos);
                    panelCategorias.asignarListenersCartas(ControladorCategorias.this);
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
	
	private void actualizarCategorias() {
        ArrayList<String> categorias = obtenerNombresCategorias();
        panelCategorias.actualizarComboBoxCategorias(categorias);
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
		if (e.getSource() == panelCategorias.getComboBoxCategorias()) {
            String categoriaSeleccionada = (String) panelCategorias.getComboBoxCategorias().getSelectedItem();
            cargarVehiculosPorCategoria(categoriaSeleccionada);
        }
		
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
			System.out.println("Eliminar");
			DialogoConfirmacion dialogoConfirmacion = new DialogoConfirmacion("¿Estas seguro de querer \neliminar la categoria?", "");
			GlassPanePopup.showPopup(dialogoConfirmacion);
			dialogoConfirmacion.getBoton().addActionListener(this);
			dialogoConfirmacion.getBoton().setActionCommand("ConfirmarEliminarCategoria");
		break;
		case "EditarCategoria":
			System.out.println("Editar");
			dialogoAniadirC_M = new DialogoAniadirC_M("Editar nombre de la categoria");
			GlassPanePopup.showPopup(dialogoAniadirC_M);
			dialogoAniadirC_M.getBoton().addActionListener(this);
			dialogoAniadirC_M.getBoton().setActionCommand("ConfirmarEditarCategoria");
		break;
		case "Info pVehiculo":
			// TO - DO
			Vehiculos vehiculoSeleccionado = panelCategorias.getVehiculoSeleccionado();
			prepararVehiculoDetalles(vehiculoSeleccionado);
			break;
//		case "Borrar Vehiculo":
//			System.out.println("Borrar");
//			GlassPanePopup.showPopup(new DialogoConfirmacion("¿Estas seguro de querer \neliminar el auto?", ""));
//			break;
		case "Rentar":
			ArrayList<String> usuarios = obtenerNombresUsuarios();
			vehiculoSeleccionado = panelCategorias.getVehiculoSeleccionado();
			ArrayList<String> vehiculos = obtenerNombresCarros();
			dialogoRenta = new DialogoRentar("Test", "Crear Renta", vehiculoSeleccionado, usuarios, null, vehiculos);
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
		case "Editar Vehiculo":
			vehiculoSeleccionado = panelCategorias.getVehiculoSeleccionado();
			prepararVehiculoEditar(vehiculoSeleccionado);
//			vehiculo = new Vehiculos();
//			GlassPanePopup.showPopup(new DialogoAniadir("Editar vehiculo", vehiculo));
			break;
		case "Agregar Categoria pCategorias":
			System.out.println("Aniadir");
			dialogoAniadirC_M = new DialogoAniadirC_M("Nombre de la nueva categoria");
			GlassPanePopup.showPopup(dialogoAniadirC_M);
			dialogoAniadirC_M.getBoton().setActionCommand("ConfirmarAniadir");
			dialogoAniadirC_M.getBoton().addActionListener(this);
			break;
		case "Detalles":
			vehiculoSeleccionado = panelCategorias.getVehiculoSeleccionado();
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
			vehiculoSeleccionado = panelCategorias.getVehiculoSeleccionado();
			
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
		case "ConfirmarRenta":
			vehiculoSeleccionado = panelCategorias.getVehiculoSeleccionado();
			String fechaFinal = dialogoRenta.getTxtFechaFinal().getText();
			String fechaInicial = dialogoRenta.getTxtFechaInicio().getText();
			Double costo = Double.parseDouble(dialogoRenta.getTxtTotal().getText());
			String nombreUsuario = (String) dialogoRenta.getComboBoxUsuarios().getSelectedItem();
			
			boolean resultado2 = modelo.aniadirRentas(fechaFinal, fechaInicial, costo, nombreUsuario, vehiculoSeleccionado.getIdVehiculo());
			if (resultado2) {
		        GlassPanePopup.closePopupLast();
		        GlassPanePopup.showPopup(new DialogoAvisos("Renta Creada", "La renta ha sido creada con éxito"));
		    } else {
		        GlassPanePopup.closePopupLast();
		        GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se ha podido crear la renta"));
		    }
			break;
		case "ConfirmarAniadir":
			if(modelo.aniadirCategorias(dialogoAniadirC_M.getTxtMarca_2().getText())) {
				actualizarCategorias();
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Añadido", "La categoria ha sido \nañadida correctamente."));
				});
			}else {
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se pudo añadir\n una nueva categoria."));
				});
			}
			break;
		case "ConfirmarEliminarCategoria":
			if(modelo.eliminarCategorias((String)panelCategorias.getComboBoxCategorias().getSelectedItem())) {
				actualizarCategorias();
				cargarVehiculosPorCategoria((String)panelCategorias.getComboBoxCategorias().getSelectedItem());
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Eliminado", "La categoria ha sido \neliminada correctamente."));
				});
			}else {
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se pudo eliminar\n la categoria."));
				});
				
			}
			break;
		case "ConfirmarEditarCategoria":
			String nombreCategoriaActual = (String) panelCategorias.getComboBoxCategorias().getSelectedItem();
			String nuevoNombreCategoria = dialogoAniadirC_M.getTxtMarca_2().getText();
			int idCategoria = modelo.obtenerIdCategoriaPorNombre(nombreCategoriaActual);
			if(modelo.editarCategorias(idCategoria, nuevoNombreCategoria)) {
				actualizarCategorias();
				cargarVehiculosPorCategoria(nombreCategoriaActual);
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Actualizado", "La categoria ha sido \neditada correctamente."));
				});
				
			}else {
				SwingUtilities.invokeLater(() -> {
					GlassPanePopup.closePopupLast();
					GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se pudo editar\n la categoria."));
				});
			}
			
			
			break;
		}
	}
}
