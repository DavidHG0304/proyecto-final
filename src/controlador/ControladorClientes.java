package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import modelo.Modelo;
import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelClientes;
import vista.VistaPanelRentas;
import vista.AccionesListeners.ListenersRegistrarCliente;
import vista.AccionesListeners.ListenersRegistro;
import vista.AccionesListeners.MetodosLog_Reg;
import vista.componentes.CartaClientes;
import vista.componentes.DialogoAvisos;
import vista.componentes.DialogoConfirmacion;
import vista.componentes.DialogoCrearCliente;
import vista.componentes.DialogoDetallesCliente;
import vista.componentes.DialogoRentar;
import vista.recursos.componentesPersonalizados.BtnBordeado;

public class ControladorClientes implements ActionListener{
	
	private VistaPanelClientes panelClientes;
	private Modelo modelo;
	private Controlador controlador;
	private MetodosLog_Reg metodos;
	private DialogoCrearCliente dialogoCrearCliente;
	private DialogoConfirmacion dialogoConfirmacion;
	private ListenersRegistrarCliente accionesRegistroCliente; 
	private Usuarios usuarioSeleccionadoParaEliminar;  
	private boolean usuarioRegistrado;

	
	
	public ControladorClientes(VistaPanelClientes panelClientes, Modelo modelo, Controlador controlador) {
		this.panelClientes = panelClientes;
	    this.panelClientes.asignarListenersCartas(this);
		this.panelClientes = panelClientes;
        this.modelo = modelo;
        this.controlador = controlador;
        this.metodos = new MetodosLog_Reg(); 
        
        
        panelClientes.setControlador(this);
//        inicializar();
        panelClientes.clientes();
        panelClientes.asignarActListner(this);
        cargarUsuario();
        GlassPanePopup.install(panelClientes.getFrame());
	}
	
	private void inicializar() {
        ArrayList<Usuarios> usuarios = modelo.obtenerUsuarios();
        panelClientes.mostrarClientes(usuarios);
    }
	
	public void eliminarCliente(Usuarios usuario) {
	    if (modelo.eliminarUsuario(usuario.getIdUsuario())) {
		modelo.eliminarUsuario(usuario.getIdUsuario());
	        ArrayList<Usuarios> usuariosActualizados = modelo.obtenerUsuarios();
	        panelClientes.mostrarClientes(usuariosActualizados);
            panelClientes.asignarListenersCartas(ControladorClientes.this);

	    } else {
	        System.out.println("No se pudo");
	    }
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
	 
	public void prepararEliminacionCliente(Usuarios usuario) {
	        usuarioSeleccionadoParaEliminar = usuario;
	        dialogoConfirmacion = new DialogoConfirmacion("¿Estás seguro de querer eliminar el cliente?", "");
	        dialogoConfirmacion.getBoton().setActionCommand("ConfirmarEliminar");
	        dialogoConfirmacion.getBoton().addActionListener(this);
	        GlassPanePopup.showPopup(dialogoConfirmacion);
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
			
//		case "EliminarCliente":
//			System.out.println("Eliminar");
//			break;
			
		case "ConfirmarEliminar":
			System.out.println("Eliminado");
                eliminarCliente(usuarioSeleccionadoParaEliminar);
                GlassPanePopup.closePopupLast();
			break;
		case "EditarCliente":
			Usuarios usuarioSeleccionado = panelClientes.getUsuarioSeleccionado();
            usuarioSeleccionado = panelClientes.getUsuarioSeleccionado();
            if(usuarioSeleccionado != null) {
                cargarUsuario();
            }
            break;
		case "DetallesCliente":
			System.out.println("Detalles");
			GlassPanePopup.showPopup(new DialogoDetallesCliente(""));
			break;
		case "Agregar Cliente pClientes":
			Usuarios nuevoUsuario = new Usuarios();
            dialogoCrearCliente = new DialogoCrearCliente("Crear Cliente", "Crear", nuevoUsuario);
            accionesRegistroCliente = new ListenersRegistrarCliente(dialogoCrearCliente);
            dialogoCrearCliente.getBtnCrear().addActionListener(this);
            GlassPanePopup.showPopup(dialogoCrearCliente);
            break;
		case "CrearUnCliente":
			System.out.println("Hola");
			accionRegistro();
			break;
		}
	}
}

