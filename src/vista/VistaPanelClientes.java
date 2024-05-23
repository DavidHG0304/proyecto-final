package vista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import vista.componentes.PanelesNavegacion;

public class VistaPanelClientes {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	
	public VistaPanelClientes(){
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		clientes();
	}
	
	public void clientes() {
		
		
		panel = new PanelesNavegacion();
		
		frame.add(panel);
		panel.getBtnClientes().setForeground(new Color(33,147,246));
		panel.getBtnClientes().setBackground(new Color(255, 255, 255));
		panel.getBtnClientes().setFocusPainted(false);
		panel.getBtnClientes().setFocusPainted(false);
		panel.getBtnClientes().setBorderPainted(false);
		panel.getBtnClientes().setContentAreaFilled(false);
		panel.getBtnClientes().setFocusPainted(false);
		
		panel.getBtnVehiculos().setForeground(new Color(0,0,0));
		panel.getBtnVehiculos().setContentAreaFilled(true);
		panel.getLblTitulo().setText("Clientes");
		
		panel.getBtnAgregar().setText("Agregar cliente");
		
		panel.getBtnInicio().setActionCommand("Inicio pClientes");
		panel.getBtnMarcas().setActionCommand("Marcas pClientes");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pClientes");
		panel.getBtnCategorias().setActionCommand("Categorias pClientes");
		panel.getBtnRentas().setActionCommand("Rentas pClientes");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pClientes");
		panel.getBtnAgregar().setActionCommand("Agregar Cliente pClientes");
		
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
