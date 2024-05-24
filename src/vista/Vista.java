package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import vista.recursos.componentesPersonalizados.*;

public class Vista {
	private JFrame frame;
	private BtnBordeado boton;
	private JTextFieldRedondeado txtCorreo;
	private JPasswordFieldRedondeado txtContrasenia;
	private JCheckBox passCheckBox;
	private JButton botonRegistrar;
	private ImageIcon iconoLogo; 
	//Constructor de la vista donde se inicializa lo que se va a necesitar
	public Vista() {
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		passCheckBox = new JCheckBox();
		txtCorreo = new JTextFieldRedondeado(35, 35);
		txtContrasenia = new JPasswordFieldRedondeado(35, 35);
		boton = new BtnBordeado(38, true);
		botonRegistrar = new JButton("Registrarse");
	}
	
	
	
	public void login() {
		// Creacion de paneles
		JPanel panelPrincipal= new JPanel();
		panelPrincipal.setLayout(new GridLayout(1,2,0,0));
		panelPrincipal.setBackground(Color.BLACK);
		frame.add(panelPrincipal);
		
		// Panel Login
		JPanel panelIzquierda= new JPanel();
		panelIzquierda.setBackground(Color.WHITE);
		panelIzquierda.setLayout(null);
		panelPrincipal.add(panelIzquierda);
	
		
		// PanelDerecha
		@SuppressWarnings("serial")
		JPanel panelDerecha = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int width = getWidth();
				int height = getHeight();
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(new GradientPaint(0.0f, 0.0f, new Color(2,67,161), 0, height, new Color(33, 147, 246), true));
				g2d.fillRect(0, 0, width, height);
				g2d.setColor(getBackground());
				
				// Dibujar rombos
				int tamanio = 40;
				int[] posX = {60, 210, 420, 320, 420, 100, 420, 65, 165, 75, 260, 340};
				int[] posY = {50, 80, 30, 170, 270, 240, 430, 410, 470, 570, 530, 620};

				Color color1 = new Color(78, 112, 229, 63);
				Color color2 = new Color(33, 147, 246, 100);
				Color color;
				for (int i = 0; i < posX.length; i++) {
				    if (i < 6) {
				        color = color1;
				    } else {
				        color = color2;
				    }
				    PintarRombos rombo = new PintarRombos(color, tamanio, posX[i], posY[i]);
				    rombo.paintComponent(g2d);
				}
			}
		};
		
		panelDerecha.setBackground(new Color(102,255,2,2));
		panelDerecha.setLayout(null);
		panelPrincipal.add(panelDerecha);
		
		// Label bienvenida
		JLabel lblBienvenida = new JLabel("¡Bienvenido!");
		lblBienvenida.setForeground(Color.WHITE);
		lblBienvenida.setBounds(120, 288, 260, 48);
		lblBienvenida.setFont(new Font("Inter", Font.BOLD, 38));
		panelDerecha.add(lblBienvenida);
		
		// Boton para iniciar sesion
		boton.setText("Iniciar");
		boton.setForeground(Color.WHITE);
		boton.setFont(new Font("Inter", Font.PLAIN, 24));
		boton.setBounds(100, 490, 250, 40);
		boton.setBackground(new Color(33, 147, 246));
		panelIzquierda.add(boton);
		
//		BtnBordeado testbtn = new BtnBordeado(26, false, true, new Color(0,0,0,100));
//		testbtn.setText("Hola");
//		testbtn.setBounds(200, 200, 97, 20);
//		panelIzquierda.add(testbtn);
		
		
		// lbl Iniciar sesion
		JLabel lblIniciarSesion = new JLabel("Iniciar Sesión");
		lblIniciarSesion.setFont(new Font("Inter", Font.PLAIN, 38));
		lblIniciarSesion.setForeground(Color.BLACK);
		lblIniciarSesion.setBounds(110, 108, 271, 54);
		panelIzquierda.add(lblIniciarSesion);
		
		// lbl Logo
		JLabel lblDahuRental = new JLabel("DahuRental");
		lblDahuRental.setFont(new Font("Inter", Font.BOLD, 20));
		lblDahuRental.setForeground(Color.BLACK);
		lblDahuRental.setBounds(100, 10, 271, 54);
		panelIzquierda.add(lblDahuRental);
		
		// Text Fields con Labels
		txtCorreo.setText("Correo electronico");
		txtCorreo.setFont(new Font("Inter", Font.PLAIN, 12));
		txtCorreo.setForeground(new Color(0,0,0,90));
		txtCorreo.setBounds(80, 250, 300, 35);
		txtCorreo.setBackground(new Color(240,240,240));
		((JTextFieldRedondeado) txtCorreo).setPrefixIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/mail.png")));
		panelIzquierda.add(txtCorreo);
		
		passCheckBox.setText("CheckBox1");
		passCheckBox.setLocation(340, 355);
		passCheckBox.setSize(24, 24);
		passCheckBox.setOpaque(false);
		passCheckBox.setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));
		panelIzquierda.add(passCheckBox);
		
		txtContrasenia.setText("Contraseña");
		txtContrasenia.setEchoChar((char)0);
		txtContrasenia.setForeground(new Color(0,0,0,90));
		txtContrasenia.setFont(new Font("Inter", Font.PLAIN, 12));
		txtContrasenia.setBounds(80, 350, 300, 35);
		txtContrasenia.setBackground(new Color(240,240,240));
		((JPasswordFieldRedondeado) txtContrasenia).setPrefixIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/pass.png")));
		panelIzquierda.add(txtContrasenia);
		
		try {
			iconoLogo = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/logo.png"));
			JLabel imagenLogo = new JLabel(iconoLogo);
			imagenLogo.setBounds(-20, -30, 144, 142);
			panelIzquierda.add(imagenLogo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR IMAGEN");
		}
		
		
		// lbl y boton para registro
		JLabel noHayCuenta = new JLabel("¿Aun no tienes una cuenta?");
		noHayCuenta.setForeground(new Color(0,0,0,64));
		noHayCuenta.setBounds(110, 515, 260, 48);
		noHayCuenta.setFont(new Font("Inter", Font.PLAIN, 12));
		panelIzquierda.add(noHayCuenta);
		
		
		botonRegistrar.setFont(new Font("Inter", Font.PLAIN, 12));
		// Subrayado en labels
		// https://stackoverflow.com/questions/15892844/underlined-jlabel#:~:text=Font%20font%20%3D%20label.getFont%28%29%3B%20Map,attributes%20%3D%20font.getAttributes%28%29%3B%20attributes.put%28TextAttribute.UNDERLINE%2C%20TextAttribute.UNDERLINE_ON%29%3B%20label.setFont%28font.deriveFont%28attributes%29%29%3B
		Font font = botonRegistrar.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		botonRegistrar.setFont(font.deriveFont(attributes));
		
		botonRegistrar.setForeground(new Color(0,0,0,64));
		botonRegistrar.setBounds(255, 529, 97, 20);
		botonRegistrar.setFocusPainted(false);
		botonRegistrar.setOpaque(false);
		botonRegistrar.setFocusPainted(false);
		botonRegistrar.setBorderPainted(false);
		botonRegistrar.setContentAreaFilled(false);
		
		
		panelIzquierda.add(botonRegistrar);
		// AsignarActionListener a botones
		frame.revalidate();
		frame.repaint();
	}

	
	
	// Metodo que asigna los action listeners a los botones que se utilizarán en los paneles
	public void asignarActListner(ActionListener listener) {
		boton.addActionListener(listener);
		botonRegistrar.addActionListener(listener);
	}
	
	public void asignarActListnerCheckBox(ActionListener listener) {
		passCheckBox.addActionListener(listener);
	}
	
	public void asignarFocusListener(FocusListener fListener) {
		txtCorreo.addFocusListener(fListener);
		txtContrasenia.addFocusListener(fListener);
	}
	
	public void asignarMouseClicked(MouseListener mListener) {
		boton.addMouseListener(mListener);
		botonRegistrar.addMouseListener(mListener);
	}
	
	// Getters
	public JTextFieldRedondeado getTxtCorreo() {
		return txtCorreo;
	}
	public JPasswordFieldRedondeado getTxtContrasenia() {
		return txtContrasenia;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JCheckBox getPassCheckBox() {
		return passCheckBox;
	}
	public BtnBordeado getBoton() {
		return boton;
	}
	public JButton getBotonRegistrar() {
		return botonRegistrar;
	}
	
	// Setters
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public void setTxtCorreo(JTextFieldRedondeado txtCorreo) {
		this.txtCorreo = txtCorreo;
	}
	public void setTxtContrasenia(JPasswordFieldRedondeado txtContrasenia) {
		this.txtContrasenia = txtContrasenia;
	}
	public void setPassCheckBox(JCheckBox passCheckBox) {
		this.passCheckBox = passCheckBox;
	}
	public void setBoton(BtnBordeado boton) {
		this.boton = boton;
	}
	public void setBotonRegistrar(JButton botonRegistrar) {
		this.botonRegistrar = botonRegistrar;
	}
}