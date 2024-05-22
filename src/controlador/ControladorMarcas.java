package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import vista.VistaPanelMarcas;

public class ControladorMarcas implements ActionListener {

    private VistaPanelMarcas panelMarcas;
    private Modelo modelo;

    public ControladorMarcas(VistaPanelMarcas panelMarcas, Modelo modelo) {
        this.panelMarcas = panelMarcas;
        this.modelo = modelo;
        panelMarcas.asignarActListner(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Inicio pMarcas":
                // Manejar la acción de ir al inicio desde el panel de marcas
                break;
            // Otras acciones específicas del panel de marcas
        }
    }
}