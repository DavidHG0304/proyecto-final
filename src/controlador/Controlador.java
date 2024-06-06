package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import modelo.entidades.Usuarios;
import raven.glasspanepopup.GlassPanePopup;
import vista.Vista;
import vista.VistaPanelCategorias;
import vista.VistaPanelClientes;
import vista.VistaPanelInicio;
import vista.VistaPanelMarcas;
import vista.VistaPanelRentas;
import vista.VistaPanelVehiculos;
import vista.VistaRegistro;
import vista.AccionesListeners.ListenersLogin;
import vista.AccionesListeners.ListenersRegistro;
import vista.AccionesListeners.MetodosLog_Reg;
import vista.componentes.DialogoAvisos;
import vista.componentes.PanelesNavegacion;

public class Controlador implements ActionListener{
	
	// Constructor del controlador donde se inicializan lo que se va a necesitar
	public Controlador()  {
		this.nuevoModelo = new Modelo();
		this.metodos = new MetodosLog_Reg();
	}
	
	// Metodos para llamar las vistas
	public void login() {
		this.nuevaVista = new Vista();
		GlassPanePopup.install(nuevaVista.getFrame());
		this.accionesLogin = new ListenersLogin(nuevaVista);
		nuevaVista.asignarActListner(this);
		nuevaVista.login();
	}
	
	public void registro() {
		this.vistaRegistro = new VistaRegistro();
		GlassPanePopup.install(vistaRegistro.getFrame());
		this.accionesRegistro = new ListenersRegistro(vistaRegistro);
		vistaRegistro.asignarActListner(this);
		vistaRegistro.registro();
	}
	
	public void panelPrincipal() {
		VistaPanelInicio panelInicio = new VistaPanelInicio();
		this.controladorPInicio = new ControladorPanelPrincipal(panelInicio, nuevoModelo, this);
//		GlassPanePopup.install(panelInicio.getFrame());
	}
	
	public void marcas() {
		VistaPanelMarcas panelMarcas = new VistaPanelMarcas();
        this.controladorMarcas = new ControladorMarcas(panelMarcas, nuevoModelo, this);
	}
	
	public void categorias() {
		VistaPanelCategorias panelCategorias = new VistaPanelCategorias(controladorCategorias);
		this.controladorCategorias = new ControladorCategorias(panelCategorias, nuevoModelo, this);
	}
	
	public void rentas() {
		VistaPanelRentas panelRentas = new VistaPanelRentas(controladorRentas);
		this.controladorRentas = new ControladorRentas(panelRentas, nuevoModelo, this);
	}
	
	public void vehiculos() {
		VistaPanelVehiculos panelVehiculos = new VistaPanelVehiculos(controladorVehiculos);
		this.controladorVehiculos = new ControladorVehiculos(panelVehiculos, nuevoModelo, this);
	}
	
	public void clientes() {
		VistaPanelClientes panelClientes = new VistaPanelClientes(controladorClientes);
		this.controladorClientes = new ControladorClientes(panelClientes, nuevoModelo, this);
	}
	
	// Metodo validacion Login
	public void accionLogin() {
//		if (metodos.loginValidado(new String(nuevaVista.getTxtContrasenia().getPassword()), nuevaVista.getTxtCorreo(), nuevaVista.getTxtContrasenia())) {
//			Usuarios usuario = nuevoModelo.accionLogin(nuevaVista.getTxtCorreo().getText(), new String(nuevaVista.getTxtContrasenia().getPassword()));
//	        if (usuario != null) {
//	        	nuevaVista.getFrame().dispose();
//	            // Aquí puedes utilizar el objeto usuario para lo que necesites, por ejemplo:
//	            System.out.println("Usuario logueado: " + usuario.getNombreUsuario());
//	            panelPrincipal();
//	        } else {
//	        	metodos.loginNoValido(new String(nuevaVista.getTxtContrasenia().getPassword()), nuevaVista.getTxtCorreo(), nuevaVista.getTxtContrasenia(), nuevoModelo.isRegistroEncontrado());
//	        }
//		}
		nuevaVista.getFrame().dispose();
		panelPrincipal();
	}
	
	// Metodo validacion Registro
	public void accionRegistro() {
        if (metodos.registroValido(new String(vistaRegistro.getTxtContrasenia().getPassword()), new String(vistaRegistro.getConfirmarContrasenia().getPassword()), vistaRegistro.getNombre(), vistaRegistro.getApellidos(), vistaRegistro.getTxtCorreo(), vistaRegistro.getTxtContrasenia(), vistaRegistro.getConfirmarContrasenia())) {
            usuarioRegistrado = nuevoModelo.accionRegistro(vistaRegistro.getNombre().getText(), vistaRegistro.getApellidos().getText(), vistaRegistro.getTxtCorreo().getText(), new String(vistaRegistro.getTxtContrasenia().getPassword()), new String(vistaRegistro.getConfirmarContrasenia().getPassword()));
            if (usuarioRegistrado) {
                vistaRegistro.getFrame().dispose();
                login();
                GlassPanePopup.showPopup(new DialogoAvisos("Cuenta Creada", "Cuenta creada con éxito \nInicie sesión para poder navegar \npor el sistema."));
            }
        } else {
            metodos.registroNoValido(vistaRegistro.getNombre(), vistaRegistro.getApellidos(), vistaRegistro.getTxtCorreo(), vistaRegistro.getTxtContrasenia(), vistaRegistro.getConfirmarContrasenia(), nuevoModelo.isRegistrado(), new String(vistaRegistro.getTxtContrasenia().getPassword()), new String(vistaRegistro.getConfirmarContrasenia().getPassword()), nuevoModelo.getHayRegistro());
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method s
		switch (e.getActionCommand()) {
		
		// Acciones Login - Registro
		case "Iniciar":
			accionLogin();
			break;
		case "Crear Cuenta":
			accionRegistro();
			break;
			
		// Acciones Registro
		case "Registrarse":
			nuevaVista.getFrame().dispose();
			registro();
			nuevoModelo.setHayRegistro(0);
			break;
		case "Iniciar Sesion":
			vistaRegistro.getFrame().dispose();
			login();
			break;
		}
	}
	
	public Modelo getNuevoModelo() {
		return nuevoModelo;
	}
	
	
	// Controladores
	private ControladorPanelPrincipal controladorPInicio;
	private ControladorMarcas controladorMarcas;
	private ControladorCategorias controladorCategorias;
	private ControladorVehiculos controladorVehiculos;
	private ControladorRentas controladorRentas;
	private ControladorClientes controladorClientes;
	// Vistas
	public Vista nuevaVista;
	public VistaRegistro vistaRegistro;
	public VistaPanelInicio panelInicio;
	public VistaPanelMarcas panelMarcas;
	public VistaPanelCategorias panelCategorias;
	public VistaPanelRentas panelRentas;
	public VistaPanelVehiculos panelVehiculos;
	public VistaPanelClientes panelClientes;
	public PanelesNavegacion panelesNavegacion;
	// Modelo
	public MetodosLog_Reg metodos;
	public Modelo nuevoModelo;
	public ListenersLogin accionesLogin;
	public ListenersRegistro accionesRegistro;
	public boolean sesionIniciada;
	public boolean usuarioRegistrado;
	
}
