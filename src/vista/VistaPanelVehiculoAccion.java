package vista;

import java.awt.Color;

import javax.swing.JFrame;

import vista.componentes.PanelesNavegacion;

public class VistaPanelVehiculoAccion {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	
	
	public VistaPanelVehiculoAccion(){
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		panelEditar();
	}
	
	
	public void panelEditar() {
		panel = new PanelesNavegacion();
		frame.add(panel);
		
	}
}

