package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import raven.glasspanepopup.GlassPanePopup;
import vista.componentes.PanelesNavegacion;


public class VistaPanelInicio {
	JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	
	public VistaPanelInicio() {
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
	}
	
	public void panelPrincipal() {
		panel = new PanelesNavegacion();
		frame.add(panel);
//		Imagen Blazer
		ImageIcon blazerImg = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/blazerG.png"));
		JLabel lblBlazer = new JLabel(blazerImg);
		lblBlazer.setBounds(270, 140, 655, 317);
		panel.getPanelCentral().add(lblBlazer);
		
		// Lbl Centro Linea Azul
		JPanel lineaCentro = new JPanel();
		lineaCentro.setBackground(new Color(33, 147, 246));
		lineaCentro.setBounds(0, 249, 914, 101);
		lineaCentro.setLayout(null);
		panel.getPanelCentral().add(lineaCentro);
		
		JLabel lblNewLabel = new JLabel("Encuentra y renta el auto");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 36));
		lblNewLabel.setBounds(27, 161, 447, 44);
		panel.getPanelCentral().add(lblNewLabel);
		
		JLabel lblDeTusSueos = new JLabel("de tus sueños en");
		lblDeTusSueos.setForeground(new Color(0, 0, 0));
		lblDeTusSueos.setFont(new Font("Inter", Font.BOLD, 36));
		lblDeTusSueos.setBounds(27, 205, 447, 44);
		panel.getPanelCentral().add(lblDeTusSueos);
		
		JLabel lblDeTusSueos_1 = new JLabel("sencillos pasos.");
		lblDeTusSueos_1.setForeground(new Color(255, 255, 255));
		lblDeTusSueos_1.setBounds(27, 0, 297, 44);
		lineaCentro.add(lblDeTusSueos_1);
		lblDeTusSueos_1.setFont(new Font("Inter", Font.BOLD, 36));
		
		JPanel panelLogos = new JPanel();
		panelLogos.setBounds(10, 489, 894, 90);
		panelLogos.setOpaque(false);
		panelLogos.setLayout(new FlowLayout(FlowLayout.LEFT, 32, 10));		
		panel.getPanelCentral().add(panelLogos);
		
		panel.getBtnInicio().setForeground(new Color(33,147,246));
		panel.getBtnInicio().setBackground(new Color(255, 255, 255));
		panel.getBtnInicio().setFocusPainted(false);
		panel.getBtnInicio().setBorderPainted(false);
		panel.getBtnInicio().setContentAreaFilled(false);
		
		panel.getBtnRentas().setActionCommand("Rentas pInicial");
		panel.getBtnCategorias().setActionCommand("Categorias pInicial");
		panel.getBtnMarcas().setActionCommand("Marcas pInicial");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pInicial");
		panel.getBtnClientes().setActionCommand("Clientes pInicial");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pInicial");
		
		
		ImageIcon logoMazda = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoMazda.png"));
		JLabel lblMazda = new JLabel(logoMazda);	
		panelLogos.add(lblMazda);
		ImageIcon logoAudi = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoAudi.png"));
		JLabel lblAudi = new JLabel(logoAudi);	
		panelLogos.add(lblAudi);
		ImageIcon logoPorsche = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoPorsche.png"));
		JLabel lblPorsche = new JLabel(logoPorsche);	
		panelLogos.add(lblPorsche);		
		ImageIcon logoFord = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoFord.png"));
		JLabel lblFord = new JLabel(logoFord);	
		panelLogos.add(lblFord);	
		ImageIcon logoNissan = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoNissan.png"));
		JLabel lblNissan = new JLabel(logoNissan);	
		panelLogos.add(lblNissan);	
		ImageIcon logoLamborghini = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoLamborghini.png"));
		JLabel lblLamborghini = new JLabel(logoLamborghini);	
		panelLogos.add(lblLamborghini);	
		ImageIcon logoChevrolet = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoChevrolet.png"));
		JLabel lblChevrolet = new JLabel(logoChevrolet);	
		panelLogos.add(lblChevrolet);	
		ImageIcon logoToyota = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoToyota.png"));
		JLabel lblToyota = new JLabel(logoToyota);	
		panelLogos.add(lblToyota);	
		ImageIcon logoBmw = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logoBmw.png"));
		JLabel lblBmw = new JLabel(logoBmw);	
		panelLogos.add(lblBmw);	
		
		panel.getPanelCentral().remove(panel.getLblAniadir());
		panel.getPanelCentral().remove(panel.getBtnAgregar());
		panel.getPanelCentral().remove(panel.getLblTitulo());
		panel.getPanelCentral().remove(panel.getLblCargandoGif());
		panel.getPanelCentral().revalidate();
		panel.getPanelCentral().repaint();
	}
	
	public void asignarActListner(ActionListener listener) {
		panel.getBtnCerrarSesion().addActionListener(listener);
		panel.getBtnInicio().addActionListener(listener);
		panel.getBtnClientes().addActionListener(listener);
		panel.getBtnVehiculos().addActionListener(listener);
		panel.getBtnRentas().addActionListener(listener);
		panel.getBtnCategorias().addActionListener(listener);
		panel.getBtnMarcas().addActionListener(listener);
	}
	// Getters
	public JFrame getFrame() {
		return frame;
	}
	// Setters
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
