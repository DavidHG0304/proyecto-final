package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import vista.VistaPanelMarcas;

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
        }
    }
}
