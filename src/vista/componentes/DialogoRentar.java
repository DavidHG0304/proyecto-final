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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import vista.recursos.componentesPersonalizados.ComboBoxRedondeado;
import vista.recursos.componentesPersonalizados.JTextFieldRedondeado;
import vista.recursos.componentesPersonalizados.PanelRedondeado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class DialogoRentar extends JPanel {

	private BtnBordeado btnCrear;
	private JTextFieldRedondeado txtFechaRenta;
	private JTextFieldRedondeado txtFechaN;
	
	private JLabel lblNombre;
	private JLabel tPersonas;
	private JLabel anioV;
	private JLabel nPuertas;
	private JLabel kilometraje;
	private JLabel tTransmision;
	private JLabel aireAcondicionado;
	private JLabel lblImgCarro;
	private JLabel precioSVida;
	private JLabel precioK;
	private JLabel precioC;
	private JLabel precioD;
	private JLabel precioT;
	private JLabel txtTotal;
	private ComboBoxRedondeado<String> comboBoxUsuarios;
	private JLabel txtFechaInicio;
	private JLabel txtFechaFinal;
	private float sumaTotal;
	private DialogoFecha dialogoFecha;

	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoRentar(String titulo, String textoBtn, Vehiculos vehiculo, ArrayList<String> usuarios) {
		this.dialogoFecha = dialogoFecha;
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
		
		ImageIcon fechaIcono2 = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/fecha.png"));
		BtnBordeado lblFecha_1 = new  BtnBordeado(15, false, true, new Color(0,0,0,70));
		lblFecha_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogoFecha = new DialogoFecha(true, DialogoRentar.this);
				GlassPanePopup.showPopup(dialogoFecha);
			}
		});
		lblFecha_1.setIcon(fechaIcono2);		
		lblFecha_1.setBounds(487, 460, 39, 25);
		add(lblFecha_1);
		Cerrar.setFont(new Font("Inter", Font.PLAIN, 14));
		Cerrar.setForeground(new Color(33, 147, 246));
		Cerrar.setBackground(new Color(255, 255, 255));
		Cerrar.setText("Cerrar");
		Cerrar.setBounds(24, 551, 115, 25);
		add(Cerrar);
		
		txtFechaRenta = new JTextFieldRedondeado(20,20, new Color(0,0,0,60));
		txtFechaRenta.setEditable(false);
		txtFechaRenta.setFont(new Font("Inter", Font.PLAIN, 11));
		txtFechaRenta.setBounds(263, 460, 214, 25);
		txtFechaRenta.setBackground(new Color(0,0,0,5));
		add(txtFechaRenta);
        
        
//        txtFechaN = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
//        txtFechaN.setEditable(false);
//        txtFechaN.setFont(new Font("Inter", Font.PLAIN, 11));
//        txtFechaN.setBackground(new Color(0, 0, 0, 5));
//        txtFechaN.setBounds(263, 458, 214, 25);
//        add(txtFechaN);
		
//        JEditorPane lblFechaNacimiento = new JEditorPane();
//        lblFechaNacimiento.setText("Fecha de nacimiento");
//        lblFechaNacimiento.setOpaque(false);
//        lblFechaNacimiento.setFont(new Font("Inter", Font.PLAIN, 11));
//        lblFechaNacimiento.setFocusable(false);
//        lblFechaNacimiento.setBounds(263, 439, 214, 19);
//        add(lblFechaNacimiento);
		
//      RoundedPanel cartasCarros = new RoundedPanel(30, false, true, new Color(0, 0, 0, 61), 6);
//      cartasCarros.setBackground(new Color(255, 255, 255));
//      cartasCarros.setPreferredSize(new Dimension(208, 317));
//      cartasCarros.setMaximumSize(new Dimension(208, 317));
//      cartasCarros.setMinimumSize(new Dimension(208, 317));
//      cartasCarros.setLayout(null);
//      add(cartasCarros);
        
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
        maracaAuto.setBounds(263, 441, 214, 19);
        add(maracaAuto);
        
        JEditorPane lblCobroPorDia = new JEditorPane();
        lblCobroPorDia.setText("Tarifa por día:");
        lblCobroPorDia.setOpaque(false);
        lblCobroPorDia.setFont(new Font("Inter", Font.BOLD, 11));
        lblCobroPorDia.setFocusable(false);
        lblCobroPorDia.setBounds(24, 486, 110, 19);
        add(lblCobroPorDia);
        
        JEditorPane lblSeguroVida = new JEditorPane();
        lblSeguroVida.setText("Seguro de vida:");
        lblSeguroVida.setOpaque(false);
        lblSeguroVida.setFont(new Font("Inter", Font.BOLD, 11));
        lblSeguroVida.setFocusable(false);
        lblSeguroVida.setBounds(24, 407, 110, 19);
        add(lblSeguroVida);
        
        JEditorPane lblSeguroKilometraje = new JEditorPane();
        lblSeguroKilometraje.setText("Seguro Kilometraje:");
        lblSeguroKilometraje.setOpaque(false);
        lblSeguroKilometraje.setFont(new Font("Inter", Font.BOLD, 11));
        lblSeguroKilometraje.setFocusable(false);
        lblSeguroKilometraje.setBounds(24, 431, 110, 19);
        add(lblSeguroKilometraje);

		PanelRedondeado panelCartas = new PanelRedondeado(30, false, true, new Color(0, 0, 0, 61), 6);
		panelCartas.setBackground(new Color(255, 255, 255));
		panelCartas.setPreferredSize(new Dimension(208, 317));
		panelCartas.setMaximumSize(new Dimension(208, 317));
		panelCartas.setMinimumSize(new Dimension(208, 317));
		panelCartas.setLayout(null);
		panelCartas.setBounds(24, 54, 818, 319);
		add(panelCartas);
		
		lblNombre = new JLabel("NombreCarro");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Inter", Font.BOLD, 14));
		lblNombre.setBounds(24, 22, 453, 32);
		panelCartas.add(lblNombre);
		
		tPersonas = new JLabel();
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
		
		anioV = new JLabel();
		anioV.setText("2022");
		anioV.setOpaque(false);
		anioV.setFont(new Font("Inter", Font.PLAIN, 10));
		anioV.setFocusable(false);
		anioV.setBounds(94, 264, 124, 19);
		panelCartas.add(anioV);
		
		nPuertas = new JLabel();
		nPuertas.setText("2 puertas");
		nPuertas.setOpaque(false);
		nPuertas.setFont(new Font("Inter", Font.PLAIN, 10));
		nPuertas.setFocusable(false);
		nPuertas.setBounds(310, 234, 124, 19);
		panelCartas.add(nPuertas);
		
		kilometraje = new JLabel();
		kilometraje.setText("80000 km");
		kilometraje.setOpaque(false);
		kilometraje.setFont(new Font("Inter", Font.PLAIN, 10));
		kilometraje.setFocusable(false);
		kilometraje.setBounds(310, 264, 124, 19);
		panelCartas.add(kilometraje);
		
		tTransmision = new JLabel();
		tTransmision.setText("Transmision" + " automatico");
		tTransmision.setOpaque(false);
		tTransmision.setFont(new Font("Inter", Font.PLAIN, 10));
		tTransmision.setFocusable(false);
		tTransmision.setBounds(596, 234, 124, 19);
		panelCartas.add(tTransmision);
		
		aireAcondicionado = new JLabel();
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
		
		btnCrear.setText(textoBtn);
		btnCrear.setFont(new Font("Inter", Font.PLAIN, 14));
		btnCrear.setBackground(new Color(33, 147, 246));
		
		JLabel lblResumen = new JLabel("Resumen");
		lblResumen.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumen.setFont(new Font("Inter", Font.BOLD, 14));
		lblResumen.setBounds(10, 11, 208, 32);
		panelResumen.add(lblResumen);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio: ");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaInicio.setFont(new Font("Inter", Font.BOLD, 11));
		lblFechaInicio.setBounds(21, 48, 78, 25);
		panelResumen.add(lblFechaInicio);
		
		JLabel lblFechaFinal = new JLabel("Fecha Final: ");
		lblFechaFinal.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaFinal.setFont(new Font("Inter", Font.BOLD, 11));
		lblFechaFinal.setBounds(21, 81, 78, 25);
		panelResumen.add(lblFechaFinal);
		
		JLabel lblCostoTotal = new JLabel("Total: ");
		lblCostoTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCostoTotal.setFont(new Font("Inter", Font.BOLD, 11));
		lblCostoTotal.setBounds(21, 115, 68, 25);
		panelResumen.add(lblCostoTotal);
		
		txtFechaInicio = new JLabel();
		txtFechaInicio.setOpaque(false);
		txtFechaInicio.setFont(new Font("Inter", Font.PLAIN, 11));
		txtFechaInicio.setFocusable(false);
		txtFechaInicio.setBounds(99, 48, 106, 25);
		panelResumen.add(txtFechaInicio);
		
		txtFechaFinal = new JLabel();
		txtFechaFinal.setOpaque(false);
		txtFechaFinal.setFont(new Font("Inter", Font.PLAIN, 11));
		txtFechaFinal.setFocusable(false);
		txtFechaFinal.setBounds(99, 81, 106, 25);
		panelResumen.add(txtFechaFinal);
		
		txtTotal = new JLabel();
		txtTotal.setText("");
		txtTotal.setOpaque(false);
		txtTotal.setFont(new Font("Inter", Font.PLAIN, 11));
		txtTotal.setFocusable(false);
		txtTotal.setBounds(99, 115, 106, 25);
		panelResumen.add(txtTotal);
		
		JLabel auxD_5 = new JLabel();
		auxD_5.setText("$");
		auxD_5.setOpaque(false);
		auxD_5.setFont(new Font("Inter", Font.PLAIN, 11));
		auxD_5.setFocusable(false);
		auxD_5.setBounds(86, 117, 16, 19);
		panelResumen.add(auxD_5);
		
		JEditorPane dtrpnSeguroDeDanios = new JEditorPane();
		dtrpnSeguroDeDanios.setText("Seguro de Daños:");
		dtrpnSeguroDeDanios.setOpaque(false);
		dtrpnSeguroDeDanios.setFont(new Font("Inter", Font.BOLD, 11));
		dtrpnSeguroDeDanios.setFocusable(false);
		dtrpnSeguroDeDanios.setBounds(24, 384, 110, 19);
		add(dtrpnSeguroDeDanios);
		
		JEditorPane lblCombustible = new JEditorPane();
		lblCombustible.setText("Combustible:");
		lblCombustible.setOpaque(false);
		lblCombustible.setFont(new Font("Inter", Font.BOLD, 11));
		lblCombustible.setFocusable(false);
		lblCombustible.setBounds(24, 459, 110, 19);
		add(lblCombustible);
		
		comboBoxUsuarios = new ComboBoxRedondeado<String>(20, new Color(0, 0, 0, 60));
		comboBoxUsuarios.setOpaque(false);
		comboBoxUsuarios.setLightWeightPopupEnabled(false);
		comboBoxUsuarios.setFont(new Font("Inter", Font.PLAIN, 11));
		comboBoxUsuarios.setBackground(new Color(0, 0, 0, 5));
		comboBoxUsuarios.setBounds(263, 405, 214, 25);
		comboBoxUsuarios.setModel(new DefaultComboBoxModel<>(usuarios.toArray(new String[0])));
//		comboBoxUsuarios.setModel(new DefaultComboBoxModel<>(new String[] {"Usuario1", "Usuario2", "Usuario3", "Usuario4", "Usuario5", "Usuario6"}));
        add(comboBoxUsuarios);
        
        precioT = new JLabel();
        precioT.setText("");
        precioT.setOpaque(false);
        precioT.setFont(new Font("Inter", Font.PLAIN, 11));
        precioT.setFocusable(false);
        precioT.setBounds(144, 486, 93, 19);
        add(precioT);
        
        precioC = new JLabel();
        precioC.setText("");
        precioC.setOpaque(false);
        precioC.setFont(new Font("Inter", Font.PLAIN, 11));
        precioC.setFocusable(false);
        precioC.setBounds(144, 460, 93, 19);
        add(precioC);
        
        precioK = new JLabel();
        precioK.setText("");
        precioK.setOpaque(false);
        precioK.setFont(new Font("Inter", Font.PLAIN, 11));
        precioK.setFocusable(false);
        precioK.setBounds(144, 431, 93, 19);
        add(precioK);
        
        precioSVida = new JLabel();
        precioSVida.setText("");
        precioSVida.setOpaque(false);
        precioSVida.setFont(new Font("Inter", Font.PLAIN, 11));
        precioSVida.setFocusable(false);
        precioSVida.setBounds(144, 407, 93, 19);
        add(precioSVida);
        
        precioD = new JLabel();
        precioD.setText("");
        precioD.setOpaque(false);
        precioD.setFont(new Font("Inter", Font.PLAIN, 11));
        precioD.setFocusable(false);
        precioD.setBounds(144, 384, 93, 19);
        add(precioD);
        
        JLabel auxD = new JLabel();
        auxD.setText("$");
        auxD.setOpaque(false);
        auxD.setFont(new Font("Inter", Font.PLAIN, 11));
        auxD.setFocusable(false);
        auxD.setBounds(131, 384, 16, 19);
        add(auxD);
        
        JLabel auxD_1 = new JLabel();
        auxD_1.setText("$");
        auxD_1.setOpaque(false);
        auxD_1.setFont(new Font("Inter", Font.PLAIN, 11));
        auxD_1.setFocusable(false);
        auxD_1.setBounds(131, 407, 16, 19);
        add(auxD_1);
        
        JLabel auxD_2 = new JLabel();
        auxD_2.setText("$");
        auxD_2.setOpaque(false);
        auxD_2.setFont(new Font("Inter", Font.PLAIN, 11));
        auxD_2.setFocusable(false);
        auxD_2.setBounds(131, 431, 16, 19);
        add(auxD_2);
        
        JLabel auxD_3 = new JLabel();
        auxD_3.setText("$");
        auxD_3.setOpaque(false);
        auxD_3.setFont(new Font("Inter", Font.PLAIN, 11));
        auxD_3.setFocusable(false);
        auxD_3.setBounds(131, 460, 16, 19);
        add(auxD_3);
        
        JLabel auxD_4 = new JLabel();
        auxD_4.setText("$");
        auxD_4.setOpaque(false);
        auxD_4.setFont(new Font("Inter", Font.PLAIN, 11));
        auxD_4.setFocusable(false);
        auxD_4.setBounds(131, 486, 16, 19);
        add(auxD_4);
		
		
		if (vehiculo != null && textoBtn.equals("Crear Renta")) {
			btnCrear.setActionCommand("ConfirmarRenta");
			System.out.println("CrearRentaDeUnCarro");
			lblNombre.setText(vehiculo.getNombreVehiculo());
			tPersonas.setText("" + vehiculo.getPuertasVehiculo());
			anioV.setText(vehiculo.getAñoVehiculo());
			nPuertas.setText("" + vehiculo.getPuertasVehiculo());
			kilometraje.setText("" + vehiculo.getKilometrajeVehiculo());
			tTransmision.setText(vehiculo.getTransmision());
			
			precioD.setText(""+vehiculo.getTarifa().getSeguro_danios());
			precioSVida.setText(""+vehiculo.getTarifa().getSeguro_vida());
			precioK.setText(""+vehiculo.getTarifa().getSeguro_kilometraje());
			precioC.setText(""+vehiculo.getTarifa().getSeguro_combustible());
			precioT.setText(""+vehiculo.getTarifa().getSeguro_tarifa_por_dia());
			
			sumaTotal = vehiculo.getTarifa().getSeguro_danios()+vehiculo.getTarifa().getSeguro_vida()+vehiculo.getTarifa().getSeguro_kilometraje()+vehiculo.getTarifa().getSeguro_combustible();;
			txtTotal.setText(""+vehiculo.getCostoTotalTarifa());
//			actualizarCostoTotal();
			
			lblImgCarro = new JLabel();
        	Thread loadImageThread = new Thread(() -> {
        		try {
        			URL imageUrl = new URL(vehiculo.getImagenUrl());
        			ImageIcon imagenCarro = new ImageIcon(imageUrl);
        			Image imagen = imagenCarro.getImage();
        			Image imagenReescalada = imagen.getScaledInstance(187, 140, Image.SCALE_SMOOTH);
        			ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
        			SwingUtilities.invokeLater(() -> {
        				lblImgCarro.setIcon(iconoReescalado);
        				panel.revalidate();
        				panel.repaint();
        			});
        		} catch (MalformedURLException e) {
        			ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
        			Image imagen = cargandoCarro.getImage();
        			Image imagenReescalada = imagen.getScaledInstance(187, 140, Image.SCALE_SMOOTH);
        			ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
        			SwingUtilities.invokeLater(() -> {
        				lblImgCarro.setIcon(iconoReescalado);
        				panel.revalidate();
        				panel.repaint();
        			});
        		}
        	});
        	loadImageThread.start();	
        	panel.add(lblImgCarro);

			if (vehiculo.isAireAcondicionado()) {
				aireAcondicionado.setText("Si");
			} else {
				aireAcondicionado.setText("No");
			}
		}
        
        panel.revalidate();
        panel.repaint();
        
	}
	
	public void actualizarCostoTotal() {
	    String fechaInicioStr = txtFechaInicio.getText();
	    String fechaFinalStr = txtFechaFinal.getText();
	    if (!fechaInicioStr.isEmpty() && !fechaFinalStr.isEmpty()) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
	        LocalDate fechaFinal = LocalDate.parse(fechaFinalStr, formatter);
	        long diasDiferencia = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFinal);
	        if (diasDiferencia >= 0) {
	            float tarifaPorDia = Float.parseFloat(precioT.getText());
	            float costoTotal;
	            if (diasDiferencia == 0) {
	                diasDiferencia = 1;
	            }
	            costoTotal = (diasDiferencia * tarifaPorDia) + sumaTotal;
	            txtTotal.setText(String.format("%.2f", costoTotal));
	        } else {
	            txtTotal.setText("0.00");
	        }
	    }
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

	public JTextFieldRedondeado getTxtFechaRenta() {
		return txtFechaRenta;
	}

	public void setTxtFechaRenta(JTextFieldRedondeado txtFechaRenta) {
		this.txtFechaRenta = txtFechaRenta;
	}

	public JTextFieldRedondeado getTxtFechaN() {
		return txtFechaN;
	}

	public void setTxtFechaN(JTextFieldRedondeado txtFechaN) {
		this.txtFechaN = txtFechaN;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel gettPersonas() {
		return tPersonas;
	}

	public void settPersonas(JLabel tPersonas) {
		this.tPersonas = tPersonas;
	}

	public JLabel getAnioV() {
		return anioV;
	}

	public void setAnioV(JLabel anioV) {
		this.anioV = anioV;
	}

	public JLabel getnPuertas() {
		return nPuertas;
	}

	public void setnPuertas(JLabel nPuertas) {
		this.nPuertas = nPuertas;
	}

	public JLabel getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(JLabel kilometraje) {
		this.kilometraje = kilometraje;
	}

	public JLabel gettTransmision() {
		return tTransmision;
	}

	public void settTransmision(JLabel tTransmision) {
		this.tTransmision = tTransmision;
	}

	public JLabel getAireAcondicionado() {
		return aireAcondicionado;
	}

	public void setAireAcondicionado(JLabel aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}

	public JLabel getLblImgCarro() {
		return lblImgCarro;
	}

	public void setLblImgCarro(JLabel lblImgCarro) {
		this.lblImgCarro = lblImgCarro;
	}

	public JLabel getPrecioSVida() {
		return precioSVida;
	}

	public void setPrecioSVida(JLabel precioSVida) {
		this.precioSVida = precioSVida;
	}

	public JLabel getPrecioK() {
		return precioK;
	}

	public void setPrecioK(JLabel precioK) {
		this.precioK = precioK;
	}

	public JLabel getPrecioC() {
		return precioC;
	}

	public void setPrecioC(JLabel precioC) {
		this.precioC = precioC;
	}

	public JLabel getPrecioD() {
		return precioD;
	}

	public void setPrecioD(JLabel precioD) {
		this.precioD = precioD;
	}

	public JLabel getPrecioT() {
		return precioT;
	}

	public void setPrecioT(JLabel precioT) {
		this.precioT = precioT;
	}

	public JLabel getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JLabel txtTotal) {
		this.txtTotal = txtTotal;
	}

	public ComboBoxRedondeado<String> getComboBoxUsuarios() {
		return comboBoxUsuarios;
	}

	public void setComboBoxUsuarios(ComboBoxRedondeado<String> comboBoxUsuarios) {
		this.comboBoxUsuarios = comboBoxUsuarios;
	}

	public JLabel getTxtFechaInicio() {
		return txtFechaInicio;
	}

	public void setTxtFechaInicio(JLabel txtFechaInicio) {
		this.txtFechaInicio = txtFechaInicio;
	}

	public JLabel getTxtFechaFinal() {
		return txtFechaFinal;
	}

	public void setTxtFechaFinal(JLabel txtFechaFinal) {
		this.txtFechaFinal = txtFechaFinal;
	}

	public DialogoFecha getDialogoFecha() {
		return dialogoFecha;
	}

	public void setDialogoFecha(DialogoFecha dialogoFecha) {
		this.dialogoFecha = dialogoFecha;
	}
}
