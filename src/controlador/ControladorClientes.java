package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.Modelo.RentasAsociadasException;
import modelo.entidades.Rentas;
import modelo.entidades.Usuarios;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelClientes;
import vista.AccionesListeners.ListenersRegistrarCliente;
import vista.AccionesListeners.MetodosLog_Reg;
import vista.componentes.DialogoAvisos;
import vista.componentes.DialogoConfirmacion;
import vista.componentes.DialogoCrearCliente;
import vista.componentes.DialogoDetallesCliente;

public class ControladorClientes implements ActionListener{
	
	private VistaPanelClientes panelClientes;
	private Modelo modelo;
	private Controlador controlador;
	private MetodosLog_Reg metodos;
	private DialogoCrearCliente dialogoCrearCliente;
	private DialogoConfirmacion dialogoConfirmacion;
	private ListenersRegistrarCliente accionesRegistroCliente; 
	private Usuarios usuarioSeleccionadoParaEliminar;  
	private Usuarios usuarioSeleccionadoParaEditar;
	private boolean usuarioRegistrado;

	
	
	public ControladorClientes(VistaPanelClientes panelClientes, Modelo modelo, Controlador controlador) {
		this.panelClientes = panelClientes;
		this.panelClientes.asignarListenersCartas(this);
		this.panelClientes = panelClientes;
		this.modelo = modelo;
		this.controlador = controlador;
		this.metodos = new MetodosLog_Reg();
		panelClientes.setControlador(this);
		panelClientes.clientes();
		panelClientes.asignarActListner(this);
		cargarUsuario();
		GlassPanePopup.install(panelClientes.getFrame());
	}

	private void inicializar() {
		ArrayList<Usuarios> usuarios = modelo.obtenerUsuarios();
		panelClientes.mostrarClientes(usuarios);
	}
	

	public void cargarUsuario() {
		SwingWorker<ArrayList<Usuarios>, Void> cargadorUsuarios = new SwingWorker<ArrayList<Usuarios>, Void>() {
			@Override
			protected ArrayList<Usuarios> doInBackground() throws Exception {
				return modelo.obtenerUsuarios();
			}

			@Override
			protected void done() {
				try {
					ArrayList<Usuarios> usuarios = get();
					inicializar();
					panelClientes.asignarListenersCartas(ControladorClientes.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		cargadorUsuarios.execute();
	}

	 public void accionRegistro() {
		 if (metodos.registroValido(new String(dialogoCrearCliente.getTxtContrasenia().getPassword()), new String(dialogoCrearCliente.getTxtConfirmarContrasenia().getPassword()), dialogoCrearCliente.getTxtNombre(), dialogoCrearCliente.getTxtApellidos(), dialogoCrearCliente.getTxtCorreo(), dialogoCrearCliente.getTxtContrasenia(), dialogoCrearCliente.getTxtConfirmarContrasenia())) {
	       	usuarioRegistrado = modelo.accionRegistro(dialogoCrearCliente.getTxtNombre().getText(), dialogoCrearCliente.getTxtApellidos().getText(), dialogoCrearCliente.getTxtCorreo().getText(), new String(dialogoCrearCliente.getTxtContrasenia().getPassword()), new String(dialogoCrearCliente.getTxtConfirmarContrasenia().getPassword()));
	       	if (usuarioRegistrado) {
	       		cargarUsuario();
	       		GlassPanePopup.closePopupLast();
	            GlassPanePopup.showPopup(new DialogoAvisos("Cuenta Creada", "Cuenta creada con éxito \nEl cliente ha sido registrado."));
	            usuarioRegistrado = false;
	            modelo.setHayRegistro(0);
	        }
		 } else {
			 metodos.registroNoValido(dialogoCrearCliente.getTxtNombre(), dialogoCrearCliente.getTxtApellidos(), dialogoCrearCliente.getTxtCorreo(), dialogoCrearCliente.getTxtContrasenia(), dialogoCrearCliente.getTxtConfirmarContrasenia(), modelo.isRegistrado(), new String(dialogoCrearCliente.getTxtContrasenia().getPassword()), new String(dialogoCrearCliente.getTxtConfirmarContrasenia().getPassword()), modelo.getHayRegistro());
		 }
	}
	 
	public void prepararEdicionCliente(Usuarios usuario) {
		usuarioSeleccionadoParaEditar = usuario;
		dialogoCrearCliente = new DialogoCrearCliente("Editar Cliente", "Editar", usuario);
		accionesRegistroCliente = new ListenersRegistrarCliente(dialogoCrearCliente);
		dialogoCrearCliente.getBtnCrear().setActionCommand("EditarUnCliente");
		dialogoCrearCliente.getBtnCrear().addActionListener(this);
		GlassPanePopup.showPopup(dialogoCrearCliente);
	}
	 
	public void prepararEliminacionCliente(Usuarios usuario) {
		usuarioSeleccionadoParaEliminar = usuario;
		dialogoConfirmacion = new DialogoConfirmacion("¿Estás seguro de querer eliminar el cliente?", "");
		dialogoConfirmacion.getBoton().setActionCommand("ConfirmarEliminar");
		dialogoConfirmacion.getBoton().addActionListener(this);
		GlassPanePopup.showPopup(dialogoConfirmacion);
	}
	
	public void prepararDetallesCliente(Usuarios usuario) {
        ArrayList<Rentas> rentas = modelo.obtenerRentasPorVehiculo(usuario.getIdUsuario());
        DialogoDetallesCliente dialogoDetallesCliente = new DialogoDetallesCliente("Detalles del Cliente", usuario, rentas);
        GlassPanePopup.showPopup(dialogoDetallesCliente);
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
		case "ConfirmarEliminar":
			boolean eliminado = false;
            try {
                eliminado = modelo.eliminarUsuario(usuarioSeleccionadoParaEliminar.getIdUsuario());
            } catch (RentasAsociadasException e1) {
                // Mostrar mensaje de error si hay rentas asociadas
                SwingUtilities.invokeLater(() -> {
                    GlassPanePopup.closePopupLast();
                    GlassPanePopup.showPopup(new DialogoAvisos("Error", e1.getMessage()));
                });
                return; // Salir del método después de mostrar el error
            }

            if (eliminado) {
                cargarUsuario();
                SwingUtilities.invokeLater(() -> {
                    GlassPanePopup.closePopupLast();
                    GlassPanePopup.showPopup(new DialogoAvisos("Eliminado", "El cliente ha sido eliminado."));
                });
            } else {
                SwingUtilities.invokeLater(() -> {
                    GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se pudo eliminar el cliente."));
                });
            }
            break;
		case "EditarCliente":
			Usuarios usuarioSeleccionado = panelClientes.getUsuarioSeleccionado();
            usuarioSeleccionado = panelClientes.getUsuarioSeleccionado();
            if(usuarioSeleccionado != null) {
            	prepararEdicionCliente(usuarioSeleccionado);   
            }
            break;
		case "EditarUnCliente":
//			System.out.println("HOLAWDITAR");
			if (metodos.registroValido(new String(dialogoCrearCliente.getTxtContrasenia().getPassword()), new String(dialogoCrearCliente.getTxtConfirmarContrasenia().getPassword()), dialogoCrearCliente.getTxtNombre(), dialogoCrearCliente.getTxtApellidos(), dialogoCrearCliente.getTxtCorreo(), dialogoCrearCliente.getTxtContrasenia(), dialogoCrearCliente.getTxtConfirmarContrasenia())) {
                boolean actualizado = modelo.actualizarUsuario(usuarioSeleccionadoParaEditar.getIdUsuario(), dialogoCrearCliente.getTxtNombre().getText(), dialogoCrearCliente.getTxtApellidos().getText(), dialogoCrearCliente.getTxtCorreo().getText(), new String(dialogoCrearCliente.getTxtContrasenia().getPassword()));
                if (actualizado) {
                    cargarUsuario();
                    GlassPanePopup.closePopupLast();
                    GlassPanePopup.showPopup(new DialogoAvisos("Actualizado", "El cliente ha sido \nactualizado correctamente."));
                }else {
                    GlassPanePopup.showPopup(new DialogoAvisos("Error", "No se pudo actualizar el cliente."));
                }
			}
			break;
		case "Agregar Cliente pClientes":
			Usuarios nuevoUsuario = new Usuarios();
            dialogoCrearCliente = new DialogoCrearCliente("Crear Cliente", "Crear", nuevoUsuario);
            accionesRegistroCliente = new ListenersRegistrarCliente(dialogoCrearCliente);
            dialogoCrearCliente.getBtnCrear().addActionListener(this);
            GlassPanePopup.showPopup(dialogoCrearCliente);
            break;
		case "CrearUnCliente":
//			System.out.println("Hola");
			accionRegistro();
			break;
		case "DetallesCliente":
			System.out.println("Detalles");
			Usuarios clienteSeleccionado = panelClientes.getUsuarioSeleccionado();
            prepararDetallesCliente(clienteSeleccionado);
            break;
		}
	}
}

