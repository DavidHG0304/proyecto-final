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

import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;
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
public class DialogoRentar extends JPanel {

	private BtnBordeado btnCrear; 

	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoRentar(String titulo, String textoBtn, Vehiculos vehiculo) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setPreferredSize(new Dimension(871, 587));
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
		Cerrar.setBounds(24, 551, 115, 25);
		add(Cerrar);
		
		JTextFieldRedondeado txtMarca = new JTextFieldRedondeado(20,20, new Color(0,0,0,60));
		txtMarca.setFont(new Font("Inter", Font.PLAIN, 11));
		txtMarca.setBounds(263, 514, 214, 25);
		txtMarca.setBackground(new Color(0,0,0,5));
		add(txtMarca);
        
        JTextFieldRedondeado txtMarca_2 = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtMarca_2.setFont(new Font("Inter", Font.PLAIN, 11));
        txtMarca_2.setBackground(new Color(0, 0, 0, 5));
        txtMarca_2.setBounds(263, 405, 214, 25);
        add(txtMarca_2);
        
        JTextFieldRedondeado txtMarca_2_1 = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtMarca_2_1.setFont(new Font("Inter", Font.PLAIN, 11));
        txtMarca_2_1.setBackground(new Color(0, 0, 0, 5));
        txtMarca_2_1.setBounds(263, 458, 214, 25);
        add(txtMarca_2_1);
        
        JEditorPane lblNombrePersona = new JEditorPane();
        lblNombrePersona.setText("Nombre persona a rentar");
        lblNombrePersona.setOpaque(false);
        lblNombrePersona.setFont(new Font("Inter", Font.PLAIN, 11));
        lblNombrePersona.setFocusable(false);
        lblNombrePersona.setBounds(263, 384, 214, 19);
        add(lblNombrePersona);
        
        JEditorPane maracaAuto = new JEditorPane();
        maracaAuto.setText("Fecha renta");
        maracaAuto.setOpaque(false);
        maracaAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        maracaAuto.setFocusable(false);
        maracaAuto.setBounds(263, 495, 214, 19);
        add(maracaAuto);
        
        JEditorPane lblCobroPorDia = new JEditorPane();
        lblCobroPorDia.setText("Cobro por X dias");
        lblCobroPorDia.setOpaque(false);
        lblCobroPorDia.setFont(new Font("Inter", Font.PLAIN, 11));
        lblCobroPorDia.setFocusable(false);
        lblCobroPorDia.setBounds(15, 495, 124, 19);
        add(lblCobroPorDia);
        
        JEditorPane lblFechaNacimiento = new JEditorPane();
        lblFechaNacimiento.setText("Fecha de nacimiento");
        lblFechaNacimiento.setOpaque(false);
        lblFechaNacimiento.setFont(new Font("Inter", Font.PLAIN, 11));
        lblFechaNacimiento.setFocusable(false);
        lblFechaNacimiento.setBounds(263, 439, 214, 19);
        add(lblFechaNacimiento);
        
        JEditorPane lblSeguroVida = new JEditorPane();
        lblSeguroVida.setText("Seguro de vida");
        lblSeguroVida.setOpaque(false);
        lblSeguroVida.setFont(new Font("Inter", Font.PLAIN, 11));
        lblSeguroVida.setFocusable(false);
        lblSeguroVida.setBounds(24, 384, 124, 19);
        add(lblSeguroVida);
        
        JEditorPane lblSeguroAuto = new JEditorPane();
        lblSeguroAuto.setText("Seguro de auto");
        lblSeguroAuto.setOpaque(false);
        lblSeguroAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        lblSeguroAuto.setFocusable(false);
        lblSeguroAuto.setBounds(24, 439, 124, 19);
        add(lblSeguroAuto);
        
        ///////////////////////////////

		PanelRedondeado panelCartas = new PanelRedondeado(30, false, true, new Color(0, 0, 0, 61), 6);
		panelCartas.setBackground(new Color(255, 255, 255));
		panelCartas.setPreferredSize(new Dimension(208, 317));
		panelCartas.setMaximumSize(new Dimension(208, 317));
		panelCartas.setMinimumSize(new Dimension(208, 317));
		panelCartas.setLayout(null);
		panelCartas.setBounds(24, 54, 818, 319);
		add(panelCartas);

        
//        RoundedPanel cartasCarros = new RoundedPanel(30, false, true, new Color(0, 0, 0, 61), 6);
//        cartasCarros.setBackground(new Color(255, 255, 255));
//        cartasCarros.setPreferredSize(new Dimension(208, 317));
//        cartasCarros.setMaximumSize(new Dimension(208, 317));
//        cartasCarros.setMinimumSize(new Dimension(208, 317));
//        cartasCarros.setLayout(null);
//        add(cartasCarros);
		
		JLabel lblNombre = new JLabel("NombreCarro");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Inter", Font.BOLD, 14));
		lblNombre.setBounds(24, 22, 453, 32);
		panelCartas.add(lblNombre);
		
		
		JEditorPane tPersonas = new JEditorPane();
		tPersonas.setFont(new Font("Inter", Font.PLAIN, 10));
		tPersonas.setText("2 personas");
		tPersonas.setFocusable(false);
		tPersonas.setBounds(94, 234, 124, 19);
		tPersonas.setOpaque(false);
		panelCartas.add(tPersonas);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(24, 71, 762, 140);
		panelCartas.add(panel);
		
		JEditorPane anioV = new JEditorPane();
		anioV.setText("2022");
		anioV.setOpaque(false);
		anioV.setFont(new Font("Inter", Font.PLAIN, 10));
		anioV.setFocusable(false);
		anioV.setBounds(94, 264, 124, 19);
		panelCartas.add(anioV);
		
		JEditorPane nPuertas = new JEditorPane();
		nPuertas.setText("2 puertas");
		nPuertas.setOpaque(false);
		nPuertas.setFont(new Font("Inter", Font.PLAIN, 10));
		nPuertas.setFocusable(false);
		nPuertas.setBounds(310, 234, 124, 19);
		panelCartas.add(nPuertas);
		
		JEditorPane kilometraje = new JEditorPane();
		kilometraje.setText("80000 km");
		kilometraje.setOpaque(false);
		kilometraje.setFont(new Font("Inter", Font.PLAIN, 10));
		kilometraje.setFocusable(false);
		kilometraje.setBounds(310, 264, 124, 19);
		panelCartas.add(kilometraje);
		
		JEditorPane tTransmision = new JEditorPane();
		tTransmision.setText("Transmision" + " automatico");
		tTransmision.setOpaque(false);
		tTransmision.setFont(new Font("Inter", Font.PLAIN, 10));
		tTransmision.setFocusable(false);
		tTransmision.setBounds(596, 234, 124, 19);
		panelCartas.add(tTransmision);
		
		JEditorPane aireAcondicionado = new JEditorPane();
		aireAcondicionado.setText("Aire Acondicionado Si");
		aireAcondicionado.setOpaque(false);
		aireAcondicionado.setFont(new Font("Inter", Font.PLAIN, 10));
		aireAcondicionado.setFocusable(false);
		aireAcondicionado.setBounds(596, 264, 124, 19);
		panelCartas.add(aireAcondicionado);
		
		// Imagenes
		ImageIcon personasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/persona2.png"));
		JLabel lblPersonasIcono = new JLabel(personasIcono);
		lblPersonasIcono.setBounds(74, 234, 14, 14);
		panelCartas.add(lblPersonasIcono);
		
		ImageIcon puertasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/puertas.png"));
		JLabel lblpuertasIcono = new JLabel(puertasIcono);
		lblpuertasIcono.setBounds(291, 234, 14, 14);
		panelCartas.add(lblpuertasIcono);
		
		ImageIcon fechaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/fecha.png"));
		JLabel lblFecha = new JLabel(fechaIcono);
		lblFecha.setBounds(74, 265, 14, 14);
		panelCartas.add(lblFecha);
		
		ImageIcon kilomeIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/kilometraje.png"));
		JLabel lblKilometraje = new JLabel(kilomeIcono);
		lblKilometraje.setBounds(291, 265, 14, 14);
		panelCartas.add(lblKilometraje);
		
		ImageIcon transmisionIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/transmision.png"));
		JLabel lblTransmision = new JLabel(transmisionIcono);
		lblTransmision.setBounds(572, 234, 14, 14);
		panelCartas.add(lblTransmision);
		
		ImageIcon acIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/aireAcondicionado.png"));
		JLabel lblAC = new JLabel(acIcono);
		lblAC.setBounds(572, 269, 14, 14);
		panelCartas.add(lblAC);
		
		
		JLabel lblImgCarro = new JLabel();
		ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
        Image imagen = cargandoCarro.getImage();
        Image imagenReescalada = imagen.getScaledInstance(200, 130, Image.SCALE_SMOOTH);
        ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
        lblImgCarro.setIcon(iconoReescalado);
        panel.add(lblImgCarro);
        
        PanelRedondeado panelResumen = new PanelRedondeado(30, false, true, new Color(0, 0, 0, 61), 6);
        panelResumen.setBackground(new Color(255, 255, 255));
        panelResumen.setPreferredSize(new Dimension(208, 317));
        panelResumen.setMaximumSize(new Dimension(208, 317));
        panelResumen.setMinimumSize(new Dimension(208, 317));
        panelResumen.setLayout(null);
        panelResumen.setBounds(612, 384, 230, 192);
		add(panelResumen);
		
		btnCrear =  new BtnBordeado(30, false, true, new Color(33, 147, 246));
		btnCrear.setBounds(21, 145, 184, 25);
		btnCrear.setForeground(new Color(33, 147, 246));
		panelResumen.add(btnCrear);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlassPanePopup.closePopupLast();
			}
		});
		
		btnCrear.setText(textoBtn);
		btnCrear.setFont(new Font("Inter", Font.PLAIN, 14));
		btnCrear.setBackground(new Color(33, 147, 246));
		
		JLabel lblResumen = new JLabel("Resumen");
		lblResumen.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumen.setFont(new Font("Inter", Font.BOLD, 14));
		lblResumen.setBounds(10, 11, 208, 32);
		panelResumen.add(lblResumen);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaInicio.setFont(new Font("Inter", Font.PLAIN, 11));
		lblFechaInicio.setBounds(21, 50, 184, 32);
		panelResumen.add(lblFechaInicio);
		
		JLabel lblFechaInicio_1 = new JLabel("Fecha Inicio");
		lblFechaInicio_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaInicio_1.setFont(new Font("Inter", Font.PLAIN, 11));
		lblFechaInicio_1.setBounds(21, 84, 184, 32);
		panelResumen.add(lblFechaInicio_1);
		
		JLabel lblFechaInicio_1_1 = new JLabel("Fecha Inicio");
		lblFechaInicio_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaInicio_1_1.setFont(new Font("Inter", Font.PLAIN, 11));
		lblFechaInicio_1_1.setBounds(21, 116, 184, 32);
		panelResumen.add(lblFechaInicio_1_1);
        
        
        
        panel.revalidate();
        panel.repaint();
        
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
