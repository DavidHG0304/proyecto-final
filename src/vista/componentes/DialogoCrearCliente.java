package vista.componentes;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import raven.glasspanepopup.GlassPanePopup;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import vista.recursos.componentesPersonalizados.JTextFieldRedondeado;
import vista.recursos.componentesPersonalizados.PanelRedondeado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class DialogoCrearCliente extends JPanel {

	private BtnBordeado btnCrear; 

	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoCrearCliente(String titulo, String textoBtn) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setPreferredSize(new Dimension(550, 520));
		setOpaque(false);
		
		BtnBordeado Cerrar = new BtnBordeado(30, false, true, new Color(33, 147, 246));
		Cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlassPanePopup.closePopupLast();
			}
		});
		Cerrar.setFont(new Font("Inter", Font.PLAIN, 14));
		Cerrar.setForeground(new Color(33, 147, 246));
		Cerrar.setBackground(new Color(255, 255, 255));
		Cerrar.setText("Cerrar");
		Cerrar.setBounds(10, 479, 115, 25);
		add(Cerrar);
		
		JTextFieldRedondeado txtCorreo = new JTextFieldRedondeado(20,20, new Color(0,0,0,60));
		txtCorreo.setFont(new Font("Inter", Font.PLAIN, 11));
		txtCorreo.setBounds(108, 255, 331, 25);
		txtCorreo.setBackground(new Color(0,0,0,5));
		add(txtCorreo);
        
        JTextFieldRedondeado txtNombre = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtNombre.setFont(new Font("Inter", Font.PLAIN, 11));
        txtNombre.setBackground(new Color(0, 0, 0, 5));
        txtNombre.setBounds(108, 146, 331, 25);
        add(txtNombre);
        
        JTextFieldRedondeado txtApellidos = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtApellidos.setFont(new Font("Inter", Font.PLAIN, 11));
        txtApellidos.setBackground(new Color(0, 0, 0, 5));
        txtApellidos.setBounds(108, 199, 331, 25);
        add(txtApellidos);
        
        JEditorPane lblNombrePersona = new JEditorPane();
        lblNombrePersona.setText("Nombre");
        lblNombrePersona.setOpaque(false);
        lblNombrePersona.setFont(new Font("Inter", Font.PLAIN, 11));
        lblNombrePersona.setFocusable(false);
        lblNombrePersona.setBounds(108, 125, 214, 19);
        add(lblNombrePersona);
        
        JEditorPane maracaAuto = new JEditorPane();
        maracaAuto.setText("Correo Electronico");
        maracaAuto.setOpaque(false);
        maracaAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        maracaAuto.setFocusable(false);
        maracaAuto.setBounds(108, 236, 214, 19);
        add(maracaAuto);
        
        JEditorPane lblFechaNacimiento = new JEditorPane();
        lblFechaNacimiento.setText("Apellidos");
        lblFechaNacimiento.setOpaque(false);
        lblFechaNacimiento.setFont(new Font("Inter", Font.PLAIN, 11));
        lblFechaNacimiento.setFocusable(false);
        lblFechaNacimiento.setBounds(108, 180, 214, 19);
        add(lblFechaNacimiento);
		
		// Imagenes
		ImageIcon personasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/persona2.png"));
		
		ImageIcon puertasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/puertas.png"));
		
		ImageIcon fechaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/fecha.png"));
		
		ImageIcon kilomeIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/kilometraje.png"));
		
		ImageIcon transmisionIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/transmision.png"));
		
		ImageIcon acIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/aireAcondicionado.png"));
		ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
        Image imagen = cargandoCarro.getImage();
        Image imagenReescalada = imagen.getScaledInstance(200, 130, Image.SCALE_SMOOTH);
        ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
		
		btnCrear =  new BtnBordeado(30, false, true, new Color(33, 147, 246));
		btnCrear.setBounds(399, 479, 128, 25);
		add(btnCrear);
		btnCrear.setForeground(new Color(33, 147, 246));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlassPanePopup.closePopupLast();
			}
		});
		
		btnCrear.setText(textoBtn);
		btnCrear.setFont(new Font("Inter", Font.PLAIN, 14));
		btnCrear.setBackground(new Color(33, 147, 246));
		
		JEditorPane dtrpnContrasea = new JEditorPane();
		dtrpnContrasea.setText("Contraseña");
		dtrpnContrasea.setOpaque(false);
		dtrpnContrasea.setFont(new Font("Inter", Font.PLAIN, 11));
		dtrpnContrasea.setFocusable(false);
		dtrpnContrasea.setBounds(108, 291, 214, 19);
		add(dtrpnContrasea);
		
		JTextFieldRedondeado txtContrasenia = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
		txtContrasenia.setFont(new Font("Inter", Font.PLAIN, 11));
		txtContrasenia.setBackground(new Color(0, 0, 0, 5));
		txtContrasenia.setBounds(108, 310, 331, 25);
		add(txtContrasenia);
		
		JEditorPane dtrpnConfirmarContrasea = new JEditorPane();
		dtrpnConfirmarContrasea.setText("Confirmar contraseña");
		dtrpnConfirmarContrasea.setOpaque(false);
		dtrpnConfirmarContrasea.setFont(new Font("Inter", Font.PLAIN, 11));
		dtrpnConfirmarContrasea.setFocusable(false);
		dtrpnConfirmarContrasea.setBounds(108, 346, 214, 19);
		add(dtrpnConfirmarContrasea);
		
		JTextFieldRedondeado txtConfirmarContrasenia = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
		txtConfirmarContrasenia.setFont(new Font("Inter", Font.PLAIN, 11));
		txtConfirmarContrasenia.setBackground(new Color(0, 0, 0, 5));
		txtConfirmarContrasenia.setBounds(108, 365, 331, 25);
		add(txtConfirmarContrasenia);
		
		JLabel lblTitulo = new JLabel();
		lblTitulo.setText(titulo);
		lblTitulo.setOpaque(false);
		lblTitulo.setFont(new Font("Inter", Font.BOLD, 20));
		lblTitulo.setFocusable(false);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(108, 42, 331, 31);
		add(lblTitulo);
        
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),30,30));
		g2.dispose();
		super.paintComponent(g);
	}

	public BtnBordeado getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(BtnBordeado btnCrear) {
		this.btnCrear = btnCrear;
	}
}
