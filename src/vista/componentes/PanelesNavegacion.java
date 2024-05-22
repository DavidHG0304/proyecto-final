package vista.componentes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import vista.recursos.componentesPersonalizados.BtnBordeado;
import vista.recursos.componentesPersonalizados.RoundedPanel;

@SuppressWarnings("serial")
public class PanelesNavegacion extends JPanel{
	
	private JButton btnInicio;
	private JPanel lblCargandoGif;
	private BtnBordeado btnCerrarSesion;
	private BtnBordeado btnAgregar;
	private JButton btnClientes;
	private JButton btnVehiculos;
	private JButton btnRentas;
	private JButton btnCategorias;
	private JButton btnMarcas;
	private JLabel lblTitulo;
	private RoundedPanel panelCentral;
	private JLabel lblAniadir;
	
	
	
	public PanelesNavegacion() {
		setBackground(new Color(33, 147, 246));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		panelCentral = new RoundedPanel(30);
		
		btnInicio = new JButton("Inicio");
		btnCerrarSesion = new BtnBordeado(20, false); 
		btnAgregar = new BtnBordeado(20, false);
		btnClientes = new JButton();
		btnVehiculos = new JButton();
		btnRentas = new JButton();
		btnCategorias = new JButton();
		btnMarcas = new JButton();
		
		lblTitulo = new JLabel();
		componentesPanel();
	}

	public void componentesPanel() {
		
		panelCentral.setBackground(new Color(255, 255, 255));
		panelCentral.setBounds(10, 11, 914, 639);
		panelCentral.setLayout(null);
		add(panelCentral);
		
		lblCargandoGif = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Image loading = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/gifCargando.gif")).getImage();
				g2d.drawImage(loading, 0, 0, lblCargandoGif.getWidth(), lblCargandoGif.getHeight(), this);
				repaint();
			}

		};
		lblCargandoGif.setBounds(380, 260, 150, 150);
		panelCentral.add(lblCargandoGif);
		
		JPanel elementosParteSuperior = new JPanel();
		elementosParteSuperior.setBackground(new Color(255, 255, 255));
		elementosParteSuperior.setBounds(10, 11, 904, 80);
		panelCentral.add(elementosParteSuperior);
		elementosParteSuperior.setLayout(null);
		
		ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logo.png"));
		JLabel imagenLogo = new JLabel(iconoLogo);
		imagenLogo.setBounds(0, 0, 89, 80);
		elementosParteSuperior.add(imagenLogo);

		btnCerrarSesion.setText("Cerrar Sesión");
		btnCerrarSesion.setBackground(new Color(33, 147, 246));
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setFont(new Font("Inter", Font.BOLD, 13));
		btnCerrarSesion.setBounds(703, 25, 129, 23);
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setBorderPainted(false);
//		btnCerrarSesion.setContentAreaFilled(false);
		elementosParteSuperior.add(btnCerrarSesion);
		
		btnClientes.setText("Clientes");
		btnClientes.setBackground(new Color(255, 255, 255));
		btnClientes.setFont(new Font("Inter", Font.BOLD, 13));
		btnClientes.setBounds(608, 25, 89, 23);
		btnClientes.setOpaque(false);
		btnClientes.setBorderPainted(false);
		btnClientes.setFocusPainted(false);
		elementosParteSuperior.add(btnClientes);
		
		btnVehiculos.setText("Vehiculos");
		btnVehiculos.setBackground(new Color(255, 255, 255));
		btnVehiculos.setFont(new Font("Inter", Font.BOLD, 13));
		btnVehiculos.setBounds(492, 25, 114, 23);
		btnVehiculos.setOpaque(false);
		btnVehiculos.setBorderPainted(false);
		btnVehiculos.setFocusPainted(false);
		elementosParteSuperior.add(btnVehiculos);
		
		btnRentas .setText("Rentas");
		btnRentas.setBackground(new Color(255, 255, 255));
		btnRentas.setFont(new Font("Inter", Font.BOLD, 13));
		btnRentas.setBounds(410, 25, 89, 23);
		btnRentas.setOpaque(false);
		btnRentas.setBorderPainted(false);
		btnRentas.setFocusPainted(false);
		elementosParteSuperior.add(btnRentas);
		
		btnCategorias.setText("Categorias");
		btnCategorias.setBackground(new Color(255, 255, 255));
		btnCategorias.setFont(new Font("Inter", Font.BOLD, 13));
		btnCategorias.setBounds(297, 25, 114, 23);
		btnCategorias.setOpaque(false);
		btnCategorias.setBorderPainted(false);
		btnCategorias.setFocusPainted(false);
		elementosParteSuperior.add(btnCategorias);
		
		btnMarcas.setText("Marcas");
		btnMarcas.setBackground(new Color(255, 255, 255));
		btnMarcas.setFont(new Font("Inter", Font.BOLD, 13));
		btnMarcas.setBounds(198, 25, 89, 23);
		btnMarcas.setOpaque(false);
		btnMarcas.setBorderPainted(false);
		btnMarcas.setFocusPainted(false);
		elementosParteSuperior.add(btnMarcas);
		
		btnInicio.setBackground(new Color(255, 255, 255));
		btnInicio.setFont(new Font("Inter", Font.BOLD, 13));
		btnInicio.setBounds(99, 25, 89, 23);
		btnInicio.setOpaque(false);
		btnInicio.setBorderPainted(false);
		btnInicio.setFocusPainted(false);
		elementosParteSuperior.add(btnInicio);
		
		ImageIcon aniadirIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/add.png"));
		lblAniadir = new JLabel(aniadirIcono);
		lblAniadir.setBounds(872, 155, 14, 14);
		panelCentral.add(lblAniadir);
		btnAgregar.setText("Agregar un      ");
		btnAgregar.setHorizontalAlignment(SwingConstants.LEFT);
		btnAgregar.setBackground(new Color(33, 147, 246));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Inter", Font.BOLD, 12));
		btnAgregar.setBounds(750, 150, 145, 23);
		btnAgregar.setFocusPainted(false);
		btnAgregar.setFocusPainted(false);
		btnAgregar.setBorderPainted(false);
		btnAgregar.setContentAreaFilled(false);
		btnAgregar.setFocusPainted(false);
		panelCentral.add(btnAgregar);
		
		// Aniadir vehiculos al panel y agregar un scrollpane
		lblTitulo.setText("TITULO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Inter", Font.BOLD, 36));
		lblTitulo.setBounds(330, 102, 273, 44);
		panelCentral.add(lblTitulo);
		
	}
		
	
	public JButton getBtnInicio() {
		return btnInicio;
	}
	public void setBtnInicio(JButton btnInicio) {
		this.btnInicio = btnInicio;
	}
	public BtnBordeado getBtnCerrarSesion() {
		return btnCerrarSesion;
	}
	public void setBtnCerrarSesion(BtnBordeado btnCerrarSesion) {
		this.btnCerrarSesion = btnCerrarSesion;
	}
	public BtnBordeado getBtnAgregar() {
		return btnAgregar;
	}
	public void setBtnagregarAuto(BtnBordeado btnAgregar) {
		this.btnAgregar = btnAgregar;
	}
	public JButton getBtnClientes() {
		return btnClientes;
	}
	public void setBtnClientes(JButton btnClientes) {
		this.btnClientes = btnClientes;
	}
	public JButton getBtnVehiculos() {
		return btnVehiculos;
	}
	public void setBtnVehiculos(JButton btnVehiculos) {
		this.btnVehiculos = btnVehiculos;
	}
	public JButton getBtnRentas() {
		return btnRentas;
	}
	public void setBtnRentas(JButton btnRentas) {
		this.btnRentas = btnRentas;
	}
	public JButton getBtnCategorias() {
		return btnCategorias;
	}
	public void setBtnCategorias(JButton btnCategorias) {
		this.btnCategorias = btnCategorias;
	}
	public JButton getBtnMarcas() {
		return btnMarcas;
	}
	public void setBtnMarcas(JButton btnMarcas) {
		this.btnMarcas = btnMarcas;
	}
	
	public JLabel getLblTitulo() {
		return lblTitulo;
	}
	
	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}
	public RoundedPanel getPanelCentral() {
		return panelCentral;
	}
	public void setPanelCentral(RoundedPanel panelCentral) {
		this.panelCentral = panelCentral;
	}
	public JPanel getLblCargandoGif() {
		return lblCargandoGif;
	}
	public void setLblCargandoGif(JPanel lblCargandoGif) {
		this.lblCargandoGif = lblCargandoGif;
	}
	public void setBtnAgregar(BtnBordeado btnAgregar) {
		this.btnAgregar = btnAgregar;
	}
	public JLabel getLblAniadir() {
		return lblAniadir;
	}
	public void setLblAniadir(JLabel lblAniadir) {
		this.lblAniadir = lblAniadir;
	}
	
}
