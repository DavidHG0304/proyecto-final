package vista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import vista.componentes.PanelesNavegacion;


public class VistaPanelCategorias {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	
	public VistaPanelCategorias(){
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		categorias();
	}
	
	public void categorias() {
		panel = new PanelesNavegacion();
		
		frame.add(panel);
		panel.getBtnCategorias().setForeground(new Color(33,147,246));
		panel.getBtnCategorias().setBackground(new Color(255, 255, 255));
		panel.getBtnCategorias().setFocusPainted(false);
		panel.getBtnCategorias().setFocusPainted(false);
		panel.getBtnCategorias().setBorderPainted(false);
		panel.getBtnCategorias().setContentAreaFilled(false);
		panel.getBtnCategorias().setFocusPainted(false);
		
		panel.getLblTitulo().setText("Categorias");
//		panel.getLblTitulo().setBounds(320, 102, 273, 44);
		
		panel.getBtnAgregar().setText("Agregar categoria");
		panel.getBtnAgregar().setBounds(740, 150, 155, 23);
		
		panel.getBtnInicio().setActionCommand("Inicio pCategorias");
		panel.getBtnMarcas().setActionCommand("Marcas pCategorias");
		panel.getBtnRentas().setActionCommand("Rentas pCategorias");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pCategorias");
		panel.getBtnClientes().setActionCommand("Clientes pCategorias");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pCategorias");
		panel.getBtnAgregar().setActionCommand("Agregar Categoria pCategorias");
		
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
	