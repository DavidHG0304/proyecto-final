package vista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import vista.componentes.PanelesNavegacion;


public class VistaPanelMarcas {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	
	public VistaPanelMarcas(){
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		marcas();
	}
	
	public void marcas() {
		panel = new PanelesNavegacion();
		
		frame.add(panel);
		panel.getBtnMarcas().setForeground(new Color(33,147,246));
		panel.getBtnMarcas().setBackground(new Color(255, 255, 255));
		panel.getBtnMarcas().setFocusPainted(false);
		panel.getBtnMarcas().setFocusPainted(false);
		panel.getBtnMarcas().setBorderPainted(false);
		panel.getBtnMarcas().setContentAreaFilled(false);
		panel.getBtnMarcas().setFocusPainted(false);
		
		panel.getLblTitulo().setText("Marcas");
		
		panel.getBtnAgregar().setText("Agregar marca");
		
		panel.getBtnInicio().setActionCommand("Inicio pMarcas");
		panel.getBtnCategorias().setActionCommand("Categorias pMarcas");
		panel.getBtnRentas().setActionCommand("Rentas pMarcas");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pMarcas");
		panel.getBtnClientes().setActionCommand("Clientes pMarcas");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pMarcas");
		panel.getBtnAgregar().setActionCommand("Agregar Marca pMarcas");
		
	}
	
	public void asignarActListner(ActionListener listener) {
		panel.getBtnInicio().addActionListener(listener);
		panel.getBtnClientes().addActionListener(listener);
		panel.getBtnMarcas().addActionListener(listener);
		panel.getBtnVehiculos().addActionListener(listener);
		panel.getBtnRentas().addActionListener(listener);
		panel.getBtnCategorias().addActionListener(listener);
		panel.getBtnCerrarSesion().addActionListener(listener);
	}

	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
	