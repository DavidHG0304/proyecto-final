package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelMarcas;
import vista.componentes.DialogoAvisos;
import vista.componentes.DialogoConfirmacion;
import vista.componentes.DialogoDetalles;
import vista.componentes.DialogoEmergentes;
import vista.componentes.DialogoRentar;
import vista.componentes.DialogoAniadir;
import vista.componentes.DialogoAniadirC_M;

public class ControladorMarcas implements ActionListener {

    private VistaPanelMarcas panelMarcas;
    private Modelo modelo;
    private Controlador controlador;

    public ControladorMarcas(VistaPanelMarcas panelMarcas, Modelo modelo, Controlador controlador) {
        this.panelMarcas = panelMarcas;
        this.modelo = modelo;
        this.controlador = controlador;
        panelMarcas.marcas();
        panelMarcas.asignarActListner(this);
        
        // Asignar Listener y agregar el componente Popup al frame
        panelMarcas.asignarListenersCartas(ControladorMarcas.this);
        GlassPanePopup.install(panelMarcas.getFrame());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Inicio pMarcas":
                panelMarcas.getFrame().dispose();
                controlador.panelPrincipal();
                break;
            case "Categorias pMarcas":
                panelMarcas.getFrame().dispose();
                controlador.categorias();
                break;
            case "Rentas pMarcas":
                panelMarcas.getFrame().dispose();
                controlador.rentas();
                break;
            case "Vehiculos pMarcas":
                panelMarcas.getFrame().dispose();
                controlador.vehiculos();
                break;
            case "Clientes pMarcas":
                panelMarcas.getFrame().dispose();
                controlador.clientes();
                break;
            case "Cerrar Sesión pMarcas":
                panelMarcas.getFrame().dispose();
                controlador.login();
                controlador.getNuevoModelo().setRegistroEncontrado(false);
                break;
                
            case "EliminarMarca":
    			GlassPanePopup.showPopup(new DialogoConfirmacion("¿Estas seguro de querer \neliminar la marca?", ""));
    		break;
    		case "EditarMarca":
    			System.out.println("Hola");
    			GlassPanePopup.showPopup(new DialogoAniadirC_M("Editar nombre de la marca"));
    		break;
    		case "Info pVehiculo":
    			Vehiculos vehiculo = new Vehiculos();
    			System.out.println("Info");
    			GlassPanePopup.showPopup(new DialogoEmergentes((vehiculo.getNombreVehiculo()+" "+vehiculo.getModelo()+" - "+vehiculo.getCategoria()),vehiculo.getPuertasVehiculo(),vehiculo.getAñoVehiculo(),vehiculo.getKilometrajeVehiculo(), vehiculo.getTransmision(), vehiculo.isAireAcondicionado(), vehiculo.getImagenUrl()));
    			break;
    		case "Borrar Vehiculo":
    			System.out.println("Borrar");
    			GlassPanePopup.showPopup(new DialogoConfirmacion("¿Estas seguro de querer \neliminar el auto?", ""));
    			break;
    		case "Rentar":
    			GlassPanePopup.showPopup(new DialogoRentar("Test", "Crear Renta"));
    			break;
    		case "Editar Vehiculo":
    			System.out.println("Editar");
    			GlassPanePopup.showPopup(new DialogoAniadir("Editar vehiculo"));
    			break;
    		case "Agregar Marca pMarcas":
    			GlassPanePopup.showPopup(new DialogoAniadirC_M("Nombre de la nueva marca"));
    			break;
    		case "Detalles":
    			GlassPanePopup.showPopup(new DialogoDetalles("Test"));
    			break;
    		}	
    }
}