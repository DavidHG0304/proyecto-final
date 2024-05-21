package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import vista.recursos.componentesPersonalizados.*;

public class VistaRegistro {
	JFrame frame = new JFrame();
	private JButton boton;
	private RoundJTextField nombre;
	private RoundJTextField apellidos;
	private RoundJTextField txtCorreo;
	private RoundJPasswordField txtContrasenia;
	private RoundJPasswordField confirmarContrasenia;
	private JCheckBox passCheckBox;
	private JCheckBox passCheckBox2;
	private JButton botonIniciarSesion;
	
	public VistaRegistro() {
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		boton = new RoundedBorder(35);
		passCheckBox = new JCheckBox();
		passCheckBox2 = new JCheckBox();
		nombre = new RoundJTextField(35, 35);
		apellidos = new RoundJTextField(35,35);
		txtCorreo = new RoundJTextField(35, 35);
		txtContrasenia = new RoundJPasswordField(35, 35);
		confirmarContrasenia = new RoundJPasswordField(35, 35);
		botonIniciarSesion = new JButton("Iniciar Sesion");
	}
	
	public void registro() {
		// Creacion de paneles
		JPanel panelPrincipal= new JPanel();
		panelPrincipal.setLayout(new GridLayout(1,2,0,0));
		panelPrincipal.setBackground(Color.WHITE);
		frame.add(panelPrincipal);
		
		// PanelDerecha
		@SuppressWarnings("serial")
		JPanel panelIzquierda = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int width = getWidth();
				int height = getHeight();
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(
						new GradientPaint(0.0f, 0.0f, new Color(2, 67, 161), 0, height, new Color(33, 147, 246), true));
				g2d.fillRect(0, 0, width, height);
				g2d.setColor(getBackground());
				
				// Dibujar rombos
				int tamanio = 40;
				int[] posX = { 60, 210, 420, 320, 420, 100, 420, 65, 165, 75, 260, 340 };
				int[] posY = { 50, 80, 30, 170, 270, 240, 430, 410, 470, 570, 530, 620 };
				
				Color color1 = new Color(78, 112, 229, 63);
				Color color2 = new Color(33, 147, 246, 100);
				Color color;
				for (int i = 0; i < posX.length; i++) {
					if (i < 6) {
						color = color1;
					} else {
						color = color2;
					}
					PaintRombos rombo = new PaintRombos(color, tamanio, posX[i], posY[i]);
					rombo.paintComponent(g2d);
				}
			}
		};
		panelIzquierda.setLayout(null);
		panelPrincipal.add(panelIzquierda);
		
		// Añadir logo al panel
		ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logo.png"));
		JLabel imagenLogo = new JLabel(iconoLogo);
		imagenLogo.setBounds(-20, -30, 144, 142);
		panelIzquierda.add(imagenLogo);
		
		// Label bienvenida
		JLabel lblBienvenida = new JLabel("Crea una cuenta");
		lblBienvenida.setForeground(Color.WHITE);
		lblBienvenida.setBounds(85, 288, 310, 48);
		lblBienvenida.setFont(new Font("Inter", Font.BOLD, 38));
		panelIzquierda.add(lblBienvenida);

		// lbl NombrePagina
		JLabel lblDahuRental = new JLabel("DahuRental");
		lblDahuRental.setFont(new Font("Inter", Font.BOLD, 20));
		lblDahuRental.setForeground(Color.WHITE);
		lblDahuRental.setBounds(100, 10, 271, 54);
		panelIzquierda.add(lblDahuRental);
		
		// Panel Registro
		JPanel panelDerecha = new JPanel();
		panelDerecha.setBackground(Color.WHITE);
		panelDerecha.setLayout(null);
		panelPrincipal.add(panelDerecha);

		// lbl Registrarse
		JLabel lblIniciarSesion = new JLabel("Registrarse");
		lblIniciarSesion.setFont(new Font("Inter", Font.PLAIN, 38));
		lblIniciarSesion.setForeground(Color.BLACK);
		lblIniciarSesion.setBounds(135, 85, 205, 54);
		panelDerecha.add(lblIniciarSesion);
		
		// Boton para Crear cuenta
		boton.setText("Crear Cuenta");
		boton.setForeground(Color.WHITE);
		boton.setFont(new Font("Inter", Font.PLAIN, 24));
		boton.setBounds(100, 550, 250, 40);
		boton.setFocusPainted(false);
		boton.setBackground(new Color(0, 122, 247));
		boton.setBorderPainted(false);
		boton.setContentAreaFilled(false);
		boton.setFocusPainted(false);
		boton.setOpaque(false);
		panelDerecha.add(boton);
		
		// lbl y boton para registro
		JLabel noHayCuenta = new JLabel("¿Ya tienes una cuenta?");
		noHayCuenta.setForeground(new Color(0, 0, 0, 64));
		noHayCuenta.setBounds(110, 575, 260, 48);
		noHayCuenta.setFont(new Font("Inter", Font.PLAIN, 12));
		panelDerecha.add(noHayCuenta);

		// Subrayado en labels/Botones
		botonIniciarSesion.setFont(new Font("Inter", Font.PLAIN, 12));
		Font font = botonIniciarSesion.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		botonIniciarSesion.setFont(font.deriveFont(attributes));
		botonIniciarSesion.setForeground(new Color(0, 0, 0, 64));
		botonIniciarSesion.setBounds(240, 589, 110, 20);
		botonIniciarSesion.setFocusPainted(false);
		botonIniciarSesion.setOpaque(false);
		botonIniciarSesion.setFocusPainted(false);
		botonIniciarSesion.setBorderPainted(false);
		botonIniciarSesion.setContentAreaFilled(false);
		panelDerecha.add(botonIniciarSesion);
		
		// Text Fields con Labels
		nombre.setText("Nombre");
		nombre.setFont(new Font("Inter", Font.PLAIN, 12));
		nombre.setForeground(new Color(0, 0, 0, 90));
		nombre.setBounds(80, 200, 300, 35);
		nombre.setBackground(new Color(240, 240, 240));
		nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/user.png")));
		panelDerecha.add(nombre);
		
		apellidos.setText("Apellidos");
		apellidos.setFont(new Font("Inter", Font.PLAIN, 12));
		apellidos.setForeground(new Color(0, 0, 0, 90));
		apellidos.setBounds(80, 260, 300, 35);
		apellidos.setBackground(new Color(240, 240, 240));
		apellidos.setPrefixIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/user.png")));
		panelDerecha.add(apellidos);
		
		txtCorreo.setText("Correo electronico");
		txtCorreo.setFont(new Font("Inter", Font.PLAIN, 12));
		txtCorreo.setForeground(new Color(0, 0, 0, 90));
		txtCorreo.setBounds(80, 320, 300, 35);
		txtCorreo.setBackground(new Color(240, 240, 240));
		txtCorreo.setPrefixIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/mail.png")));
		panelDerecha.add(txtCorreo);

		passCheckBox.setText("CheckBox1");
		passCheckBox.setLocation(340, 385);
		passCheckBox.setSize(24, 24);
		passCheckBox.setOpaque(false);
		passCheckBox.setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));
		panelDerecha.add(passCheckBox);

		txtContrasenia.setText("Contraseña");
		txtContrasenia.setEchoChar((char) 0);
		txtContrasenia.setForeground(new Color(0, 0, 0, 90));
		txtContrasenia.setFont(new Font("Inter", Font.PLAIN, 12));
		txtContrasenia.setBounds(80, 380, 300, 35);
		txtContrasenia.setBackground(new Color(240, 240, 240));
		txtContrasenia.setPrefixIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/pass.png")));
		panelDerecha.add(txtContrasenia);
		
		passCheckBox2.setText("CheckBox1");
		passCheckBox2.setLocation(340, 445);
		passCheckBox2.setSize(24, 24);
		passCheckBox2.setOpaque(false);
		passCheckBox2.setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));
		panelDerecha.add(passCheckBox2);
		
		confirmarContrasenia.setText("Confirmar contraseña");
		confirmarContrasenia.setEchoChar((char) 0);
		confirmarContrasenia.setForeground(new Color(0, 0, 0, 90));
		confirmarContrasenia.setFont(new Font("Inter", Font.PLAIN, 12));
		confirmarContrasenia.setBounds(80, 440, 300, 35);
		confirmarContrasenia.setBackground(new Color(240, 240, 240));
		confirmarContrasenia.setPrefixIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/pass.png")));
		panelDerecha.add(confirmarContrasenia);
		
		
		frame.revalidate();
		frame.repaint();
	}
	
	public void asignarActListner(ActionListener listener) {
		boton.addActionListener(listener);
		botonIniciarSesion.addActionListener(listener);
	}
	
	public void asignarActListnerCheckBox(ActionListener listener) {
		passCheckBox.addActionListener(listener);
		passCheckBox2.addActionListener(listener);
	}
	
	public void asignarFocusListener(FocusListener fListener) {
		nombre.addFocusListener(fListener);
		apellidos.addFocusListener(fListener);
		txtCorreo.addFocusListener(fListener);
		txtContrasenia.addFocusListener(fListener);
		confirmarContrasenia.addFocusListener(fListener);
	}
	
	public void asignarMouseClicked(MouseListener mListener) {
		boton.addMouseListener(mListener);
		botonIniciarSesion.addMouseListener(mListener);
	}
	
	// Getters
	public RoundJTextField getTxtCorreo() {
		return txtCorreo;
	}
	public RoundJPasswordField getTxtContrasenia() {
		return txtContrasenia;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JCheckBox getPassCheckBox() {
		return passCheckBox;
	}
	public JButton getBoton() {
		return boton;
	}
	public JButton getBotonIniciarSesion() {
		return botonIniciarSesion;
	}
	public RoundJTextField getNombre() {
		return nombre;
	}
	public RoundJTextField getApellidos() {
		return apellidos;
	}
	public RoundJPasswordField getConfirmarContrasenia() {
		return confirmarContrasenia;
	}
	public JCheckBox getPassCheckBox2() {
		return passCheckBox2;
	}
	



	// Setters
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public void setTxtCorreo(RoundJTextField txtCorreo) {
		this.txtCorreo = txtCorreo;
	}
	public void setTxtContrasenia(RoundJPasswordField txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}
	public void setPassCheckBox(JCheckBox passCheckBox) {
		this.passCheckBox = passCheckBox;
	}
	public void setBoton(JButton boton) {
		this.boton = boton;
	}
	public void setBotonIniciarSesion(JButton botonRegistrar) {
		this.botonIniciarSesion = botonRegistrar;
	}
	public void setNombre(RoundJTextField nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(RoundJTextField apellidos) {
		this.apellidos = apellidos;
	}
	public void setConfirmarContrasenia(RoundJPasswordField confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}
	public void setPassCheckBox2(JCheckBox passCheckBox2) {
		this.passCheckBox2 = passCheckBox2;
	}
}