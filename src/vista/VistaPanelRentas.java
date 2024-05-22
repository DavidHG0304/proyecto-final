package vista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import vista.componentes.PanelesNavegacion;


public class VistaPanelRentas {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	
	public VistaPanelRentas(){
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		rentas();
	}
	
	public void rentas() {
		panel = new PanelesNavegacion();
		
		frame.add(panel);
		panel.getBtnRentas().setForeground(new Color(33,147,246));
		panel.getBtnRentas().setBackground(new Color(255, 255, 255));
		panel.getBtnRentas().setFocusPainted(false);
		panel.getBtnRentas().setFocusPainted(false);
		panel.getBtnRentas().setBorderPainted(false);
		panel.getBtnRentas().setContentAreaFilled(false);
		panel.getBtnRentas().setFocusPainted(false);
		
		panel.getLblTitulo().setText("Rentas");
		
		panel.getBtnAgregar().setText("Crear renta");
		
		panel.getBtnInicio().setActionCommand("Inicio pRentas");
		panel.getBtnMarcas().setActionCommand("Marcas pRentas");
		panel.getBtnCategorias().setActionCommand("Categorias pRentas");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pRentas");
		panel.getBtnClientes().setActionCommand("Clientes pRentas");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pRentas");
		panel.getBtnAgregar().setActionCommand("Agregar Marca pRentas");
		
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
	