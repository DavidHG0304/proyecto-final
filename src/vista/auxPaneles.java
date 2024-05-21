package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vista.recursos.componentesPersonalizados.RoundedBorder;
import vista.recursos.componentesPersonalizados.RoundedPanel;
import vista.recursos.componentesPersonalizados.SombrasPaneles;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.JTable;

public class auxPaneles extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					auxPaneles frame = new auxPaneles();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public auxPaneles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 700);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(33, 147, 246));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(255, 255, 255));
		panelCentral.setBounds(10, 11, 914, 639);
		panelPrincipal.add(panelCentral);
		panelCentral.setLayout(null);
		
		
		JPanel elementosParteSuperior = new JPanel();
		elementosParteSuperior.setBackground(new Color(255, 255, 255));
		elementosParteSuperior.setBounds(10, 11, 904, 80);
		panelCentral.add(elementosParteSuperior);
		elementosParteSuperior.setLayout(null);
		
		ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logo.png"));
		JLabel imagenLogo = new JLabel(iconoLogo);
		imagenLogo.setBounds(0, 0, 89, 80);
		elementosParteSuperior.add(imagenLogo);

		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrarSesion.setBackground(new Color(33, 147, 246));
		btnCerrarSesion.setForeground(new Color(255, 255, 255));
		btnCerrarSesion.setFont(new Font("Inter", Font.BOLD, 13));
		btnCerrarSesion.setBounds(703, 25, 129, 23);
		elementosParteSuperior.add(btnCerrarSesion);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBackground(new Color(255, 255, 255));
		btnClientes.setFont(new Font("Inter", Font.BOLD, 13));
		btnClientes.setBounds(608, 25, 89, 23);
		btnClientes.setOpaque(false);
		btnClientes.setBorderPainted(false);
		btnClientes.setFocusPainted(false);
		elementosParteSuperior.add(btnClientes);
		
		JButton btnVehiculos = new JButton("Vehiculos");
		btnVehiculos.setBackground(new Color(255, 255, 255));
		btnVehiculos.setFont(new Font("Inter", Font.BOLD, 13));
		btnVehiculos.setBounds(492, 25, 114, 23);
		btnVehiculos.setOpaque(false);
		btnVehiculos.setBorderPainted(false);
		btnVehiculos.setFocusPainted(false);
		elementosParteSuperior.add(btnVehiculos);
		
		JButton btnRentas = new JButton("Rentas");
		btnRentas.setBackground(new Color(255, 255, 255));
		btnRentas.setFont(new Font("Inter", Font.BOLD, 13));
		btnRentas.setBounds(410, 25, 89, 23);
		btnRentas.setOpaque(false);
		btnRentas.setBorderPainted(false);
		btnRentas.setFocusPainted(false);
		elementosParteSuperior.add(btnRentas);
		
		JButton btnCategorias = new JButton("Categorias");
		btnCategorias.setBackground(new Color(255, 255, 255));
		btnCategorias.setFont(new Font("Inter", Font.BOLD, 13));
		btnCategorias.setBounds(297, 25, 114, 23);
		btnCategorias.setOpaque(false);
		btnCategorias.setBorderPainted(false);
		btnCategorias.setFocusPainted(false);
		elementosParteSuperior.add(btnCategorias);
		
		JButton btnMarcas = new JButton("Marcas");
		btnMarcas.setBackground(new Color(255, 255, 255));
		btnMarcas.setFont(new Font("Inter", Font.BOLD, 13));
		btnMarcas.setBounds(198, 25, 89, 23);
		btnMarcas.setOpaque(false);
		btnMarcas.setBorderPainted(false);
		btnMarcas.setFocusPainted(false);
		elementosParteSuperior.add(btnMarcas);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.setForeground(new Color(0, 0, 0));
		btnInicio.setBackground(new Color(255, 255, 255));
		btnInicio.setFont(new Font("Inter", Font.BOLD, 13));
		btnInicio.setBounds(99, 25, 89, 23);
		btnInicio.setOpaque(false);
		btnInicio.setBorderPainted(false);
		btnInicio.setFocusPainted(false);
		elementosParteSuperior.add(btnInicio);
		
		JLabel lblVehiculos = new JLabel("Vehiculos");
		lblVehiculos.setForeground(new Color(0, 0, 0));
		lblVehiculos.setFont(new Font("Inter", Font.BOLD, 36));
		lblVehiculos.setBounds(364, 102, 183, 44);
		panelCentral.add(lblVehiculos);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(415, 285, 64, 64);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.GRAY);
		panelCentral.add(lblNewLabel);
		
		
//		RoundedPanel panelTest = new RoundedPanel(30, false, true, new Color(0,0,0), 6);
//		SombrasPaneles panelTest = new SombrasPaneles(5);
		
		
		// Carta de Carros
//		RoundedPanel cartasCarros = new RoundedPanel(30, false, true, new Color(0,0,0,61), 6);
//		cartasCarros.setBackground(new Color(255, 255, 255));
//		cartasCarros.setBounds(10, 187, 208, 317);
//		panelCentral.add(cartasCarros);
//		cartasCarros.setLayout(null);
//		
//		JPanel panelImgCarro = new JPanel();
//		panelImgCarro.setBounds(8, 22, 188, 140);
//		cartasCarros.add(panelImgCarro);
//		panelImgCarro.setLayout(null);
//		
//		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/blazerG.png"));
//		JLabel lblImgCarro = new JLabel(imageIcon);
//		lblImgCarro.setHorizontalAlignment(SwingConstants.CENTER);
//		lblImgCarro.setBounds(0, 0, 187, 140);
//		panelImgCarro.add(lblImgCarro);
//		
//		ImageIcon borrarIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eliminar.png"));
//		JButton lblBorrarIcono = new JButton(borrarIcono);
//		lblBorrarIcono.setOpaque(false);
//		lblBorrarIcono.setBorderPainted(false);
//		lblBorrarIcono.setFocusPainted(false);
//		lblBorrarIcono.setBackground(Color.WHITE);
//		lblBorrarIcono.setBounds(184, 5, 14, 14);
//		cartasCarros.add(lblBorrarIcono);
//		
//		ImageIcon editarIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/editar.png"));
//		JButton lbleditarIcono = new JButton(editarIcono);
//		lbleditarIcono.setOpaque(false);
//		lbleditarIcono.setBorderPainted(false);
//		lbleditarIcono.setFocusPainted(false);
//		lbleditarIcono.setBackground(Color.WHITE);
//		lbleditarIcono.setBounds(10, 5, 14, 14);
//		cartasCarros.add(lbleditarIcono);
//		
//		JLabel lblLinea = new JLabel("");
//		lblLinea.setOpaque(true);
//		lblLinea.setBackground(new Color(0,0,0,61));
//		lblLinea.setBounds(0, 281, 202, 1);
//		cartasCarros.add(lblLinea);
//		
//		JLabel lblCosto = new JLabel("$99,999");
//		lblCosto.setFont(new Font("Inter", Font.BOLD, 11));
//		lblCosto.setBounds(10, 288, 55, 14);
//		cartasCarros.add(lblCosto);
//		
//		JLabel lbldia = new JLabel("/Dia");
//		lbldia.setFont(new Font("Inter", Font.PLAIN, 10));
//		lbldia.setBounds(57, 290, 20, 14);
//		lbldia.setForeground(new Color(0,0,0,86));
//		cartasCarros.add(lbldia);
//		
//		ImageIcon flechaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/flecha.png"));
//		JLabel lblflechaIcono = new JLabel(flechaIcono);
//		lblflechaIcono.setBounds(175, 289, 14, 14);
//		cartasCarros.add(lblflechaIcono);
//		
//		
//		JButton btnRentar = new JButton("Rentar");
//		btnRentar.setHorizontalAlignment(SwingConstants.LEFT);
//		btnRentar.setOpaque(false);
//		btnRentar.setForeground(new Color(33, 147, 246));
//		btnRentar.setFont(new Font("Inter", Font.PLAIN, 10));
//		btnRentar.setFocusPainted(false);
//		btnRentar.setBorderPainted(false);
//		btnRentar.setBackground(Color.WHITE);
//		btnRentar.setBounds(122, 284, 71, 23);
//		cartasCarros.add(btnRentar);
//		
//		JPanel panel = new JPanel();
//		panel.setBackground(new Color(255, 255, 255));
//		panel.setBounds(8, 165, 188, 110);
//		cartasCarros.add(panel);
//		
//		ImageIcon infoIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/info.png"));
//		panel.setLayout(null);
//		JButton lblInfoIcono = new JButton(infoIcono);
//		lblInfoIcono.setOpaque(false);
//		lblInfoIcono.setBorderPainted(false);
//		lblInfoIcono.setFocusPainted(false);
//		lblInfoIcono.setBackground(Color.WHITE);
//		lblInfoIcono.setBounds(170, 5, 14, 14);
//		panel.add(lblInfoIcono);
//		
//		ImageIcon estrellaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/estrella.png"));
//		JLabel lblestrellaIcono = new JLabel(estrellaIcono);
//		lblestrellaIcono.setBounds(5, 5, 14, 14);
//		panel.add(lblestrellaIcono);
//		
//		JLabel lblNombre = new JLabel("Nombre + Marca + Tipo");
//		lblNombre.setFont(new Font("Inter", Font.BOLD, 11));
//		lblNombre.setBounds(7, 28, 129, 14);
//		panel.add(lblNombre);
//		
//		JLabel lblEstrellas = new JLabel("4.8");
//		lblEstrellas.setFont(new Font("Inter", Font.BOLD, 10));
//		lblEstrellas.setBounds(22, 5, 24, 14);
//		panel.add(lblEstrellas);
//		
//		JLabel lblReseas = new JLabel("(+999 Reseñas)");
//		lblReseas.setFont(new Font("Inter", Font.PLAIN, 10));
//		lblReseas.setBounds(42, 5, 80, 14);
//		lblReseas.setForeground(new Color(0,0,0,86));
//		panel.add(lblReseas);
//		
//		ImageIcon personasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/persona2.png"));
//		JLabel lblPersonasIcono = new JLabel(personasIcono);
//		lblPersonasIcono.setBounds(5, 46, 14, 14);
//		panel.add(lblPersonasIcono);
//		
//		ImageIcon fechaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/fecha.png"));
//		JLabel lblFechaIcono = new JLabel(fechaIcono);
//		lblFechaIcono.setBounds(5, 69, 14, 14);
//		panel.add(lblFechaIcono);
//		
//		JLabel lblPersonas = new JLabel("2");
//		lblPersonas.setForeground(new Color(0, 0, 0, 86));
//		lblPersonas.setFont(new Font("Inter", Font.PLAIN, 10));
//		lblPersonas.setBounds(22, 47, 15, 14);
//		panel.add(lblPersonas);
//		
//		JLabel lblFecha = new JLabel("2022");
//		lblFecha.setForeground(new Color(0, 0, 0, 86));
//		lblFecha.setFont(new Font("Inter", Font.PLAIN, 10));
//		lblFecha.setBounds(22, 69, 30, 14);
//		panel.add(lblFecha);
//		
//		ImageIcon transmisionIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/transmision.png"));
//		JLabel lbltransmisionIcono = new JLabel(transmisionIcono);
//		lbltransmisionIcono.setBounds(108, 46, 14, 14);
//		panel.add(lbltransmisionIcono);
//		
//		ImageIcon puertasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/puertas.png"));
//		JLabel lblpuertasIcono = new JLabel(puertasIcono);
//		lblpuertasIcono.setBounds(108, 69, 14, 14);
//		panel.add(lblpuertasIcono);
//		
//		JLabel lblAutomatico = new JLabel("Automatico");
//		lblAutomatico.setForeground(new Color(0, 0, 0, 86));
//		lblAutomatico.setFont(new Font("Inter", Font.PLAIN, 10));
//		lblAutomatico.setBounds(125, 46, 59, 14);
//		panel.add(lblAutomatico);
//		
//		JLabel lblPuertas = new JLabel("2 Puertas");
//		lblPuertas.setForeground(new Color(0, 0, 0, 86));
//		lblPuertas.setFont(new Font("Inter", Font.PLAIN, 10));
//		lblPuertas.setBounds(125, 69, 59, 14);
//		panel.add(lblPuertas);
//		
//		RoundedBorder btnDetalles = new RoundedBorder(15, false, new Color(0,0,0,42));
//		btnDetalles.setText("Detalles");
//		btnDetalles.setOpaque(false);
//		btnDetalles.setForeground(new Color(0, 0, 0));
//		btnDetalles.setFont(new Font("Inter", Font.BOLD, 10));
//		btnDetalles.setFocusPainted(false);
//		btnDetalles.setBorderPainted(false);
//		btnDetalles.setBackground(Color.WHITE);
//		btnDetalles.setBounds(40, 90, 104, 18);
//		panel.add(btnDetalles);
//		
//		table = new JTable();
//		table.setBounds(10, 175, 883, 344);
//		panelCentral.add(table);
		
		
		
		
	}
}
