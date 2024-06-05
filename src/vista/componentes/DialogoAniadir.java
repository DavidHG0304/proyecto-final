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
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import vista.recursos.componentesPersonalizados.ComboBoxRedondeado;
import vista.recursos.componentesPersonalizados.JTextFieldRedondeado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class DialogoAniadir extends JPanel {

	private JLabel lblImgCarro;
	private BtnBordeado btnAgregar;
	private JTextFieldRedondeado txtNombre;
	private JTextFieldRedondeado txtAnio;
	private ComboBoxRedondeado<String> comboBox;
	private ComboBoxRedondeado<String> comboBoxMarcas;
	private ComboBoxRedondeado<String> comboBoxCategorias;
	private JTextFieldRedondeado txtModelo;
	private JTextFieldRedondeado txtSeguroDanios;
	private JTextFieldRedondeado txtSeguroTarifa;
	private JTextFieldRedondeado txtSeguroVida;
	private JTextFieldRedondeado txtSeguroCombustible;
	private JTextFieldRedondeado txtSeguroKilometraje;
	private ButtonGroup grupoPuertas;
	
	private JRadioButton rdbtnNumPuertas1;
	private JRadioButton rdbtnNumPuertas2;
	private JRadioButton rdbtnTieneAire1;
	private JRadioButton rdbtnTieneAire2;
	private ButtonGroup grupoAireRadioButton;
	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoAniadir(String titulo, Vehiculos vehiculo, ArrayList<String> categorias, ArrayList<String> marcas,  String nombreCategoria, String nombreMarca) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setPreferredSize(new Dimension(500, 550));
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
		Cerrar.setBounds(24, 514, 115, 25);
		add(Cerrar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(24, 56, 453, 140);
		add(panel);
        
        btnAgregar = new BtnBordeado(30, false);
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GlassPanePopup.closePopupLast();
        	}
        });
        
        btnAgregar.setText("Agregar");
        btnAgregar.setForeground(new Color(255, 255, 255));
        btnAgregar.setFont(new Font("Inter", Font.PLAIN, 14));
        btnAgregar.setBackground(new Color(33, 147, 246));
        btnAgregar.setBounds(362, 514, 115, 25);
        add(btnAgregar);
        
        txtNombre = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtNombre.setFont(new Font("Inter", Font.PLAIN, 11));
        txtNombre.setBackground(new Color(0, 0, 0, 5));
        txtNombre.setBounds(263, 242, 214, 25);
        add(txtNombre);
        
        txtAnio = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtAnio.setFont(new Font("Inter", Font.PLAIN, 11));
        txtAnio.setBackground(new Color(0, 0, 0, 5));
        txtAnio.setBounds(263, 363, 214, 25);
        add(txtAnio);
        
        comboBox = new ComboBoxRedondeado<String>(20, new Color(0,0,0,60));
        UIManager.put("Component.innerFocusWidth", comboBox);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Automático", "Manual"}));
        comboBox.setFont(new Font("Inter", Font.PLAIN, 11));
        comboBox.setOpaque(false);
        comboBox.setBackground(new Color(0, 0, 0, 5));
        comboBox.setBounds(24, 363, 214, 25);
        // IMPORTANTE 
        comboBox.setLightWeightPopupEnabled(false);
        add(comboBox);
        
        rdbtnNumPuertas1 = new JRadioButton("2");
        rdbtnNumPuertas1.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnNumPuertas1.setBounds(263, 416, 53, 23);
        add(rdbtnNumPuertas1);
        
        rdbtnNumPuertas2 = new JRadioButton("4");
        rdbtnNumPuertas2.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnNumPuertas2.setBounds(318, 416, 53, 23);
        add(rdbtnNumPuertas2);
        
        grupoPuertas = new ButtonGroup();
        grupoPuertas.add(rdbtnNumPuertas1);
        grupoPuertas.add(rdbtnNumPuertas2);
        
        
        JLabel lblNewLabel = new JLabel(titulo);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Inter", Font.BOLD, 14));
        lblNewLabel.setBounds(24, 11, 453, 32);
        add(lblNewLabel);
        
        JEditorPane dtrpnTipoDelAuto = new JEditorPane();
        dtrpnTipoDelAuto.setText("Tipo del auto");
        dtrpnTipoDelAuto.setOpaque(false);
        dtrpnTipoDelAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnTipoDelAuto.setFocusable(false);
        dtrpnTipoDelAuto.setBounds(24, 283, 124, 19);
        add(dtrpnTipoDelAuto);
        
        JEditorPane maracaAuto = new JEditorPane();
        maracaAuto.setText("Marca del auto");
        maracaAuto.setOpaque(false);
        maracaAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        maracaAuto.setFocusable(false);
        maracaAuto.setBounds(24, 222, 124, 19);
        add(maracaAuto);
        
        JEditorPane dtrpnAoDelAuto = new JEditorPane();
        dtrpnAoDelAuto.setText("Año del auto");
        dtrpnAoDelAuto.setOpaque(false);
        dtrpnAoDelAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnAoDelAuto.setFocusable(false);
        dtrpnAoDelAuto.setBounds(263, 344, 124, 19);
        add(dtrpnAoDelAuto);
        
        JEditorPane dtrpnNombre = new JEditorPane();
        dtrpnNombre.setText("Nombre del auto");
        dtrpnNombre.setOpaque(false);
        dtrpnNombre.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnNombre.setFocusable(false);
        dtrpnNombre.setBounds(263, 222, 124, 19);
        add(dtrpnNombre);
        
        JEditorPane dtrpnTransmision = new JEditorPane();
        dtrpnTransmision.setText("Transmision");
        dtrpnTransmision.setOpaque(false);
        dtrpnTransmision.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnTransmision.setFocusable(false);
        dtrpnTransmision.setBounds(24, 344, 124, 19);
        add(dtrpnTransmision);
        
        JEditorPane dtrpnPuertasDelAuto = new JEditorPane();
        dtrpnPuertasDelAuto.setText("Puertas del auto");
        dtrpnPuertasDelAuto.setOpaque(false);
        dtrpnPuertasDelAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnPuertasDelAuto.setFocusable(false);
        dtrpnPuertasDelAuto.setBounds(263, 395, 124, 19);
        add(dtrpnPuertasDelAuto);
        
        comboBoxMarcas = new ComboBoxRedondeado<String>(20, new Color(0, 0, 0, 60));
        comboBoxMarcas.setOpaque(false);
        comboBoxMarcas.setLightWeightPopupEnabled(false);
        comboBoxMarcas.setFont(new Font("Inter", Font.PLAIN, 11));
        comboBoxMarcas.setBackground(new Color(0, 0, 0, 5));
        comboBoxMarcas.setBounds(24, 243, 214, 25);
//      comboBoxMarcas.setModel(new DefaultComboBoxModel<>(new String[] {"Automatico", "Manual"}));
        add(comboBoxMarcas);
        
        comboBoxCategorias = new ComboBoxRedondeado<String>(20, new Color(0, 0, 0, 60));
        comboBoxCategorias.setOpaque(false);
        comboBoxCategorias.setLightWeightPopupEnabled(false);
        comboBoxCategorias.setFont(new Font("Inter", Font.PLAIN, 11));
        comboBoxCategorias.setBackground(new Color(0, 0, 0, 5));
        comboBoxCategorias.setBounds(24, 304, 214, 25);
        comboBox.setLightWeightPopupEnabled(false);
//      comboBoxCategorias.setModel(new DefaultComboBoxModel<>(new String[] {"Automatico", "Manual"}));
        add(comboBoxCategorias);
        
        comboBoxMarcas.setModel(new DefaultComboBoxModel<>(marcas.toArray(new String[0])));
        comboBoxCategorias.setModel(new DefaultComboBoxModel<>(categorias.toArray(new String[0])));
        
        txtModelo = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtModelo.setFont(new Font("Inter", Font.PLAIN, 11));
        txtModelo.setBackground(new Color(0, 0, 0, 5));
        txtModelo.setBounds(263, 304, 214, 25);
        add(txtModelo);
        
        JEditorPane dtrpnModelo = new JEditorPane();
        dtrpnModelo.setText("Modelo del auto");
        dtrpnModelo.setOpaque(false);
        dtrpnModelo.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnModelo.setFocusable(false);
        dtrpnModelo.setBounds(263, 284, 124, 19);
        add(dtrpnModelo);
        
        JEditorPane dtrpnTieneAire = new JEditorPane();
        dtrpnTieneAire.setText("Tiene A/C");
        dtrpnTieneAire.setOpaque(false);
        dtrpnTieneAire.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnTieneAire.setFocusable(false);
        dtrpnTieneAire.setBounds(24, 395, 124, 19);
        add(dtrpnTieneAire);
        
        rdbtnTieneAire1 = new JRadioButton("Si");
        rdbtnTieneAire1.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnTieneAire1.setBounds(24, 416, 53, 23);
        add(rdbtnTieneAire1);
        
        rdbtnTieneAire2 = new JRadioButton("No");
        rdbtnTieneAire2.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnTieneAire2.setBounds(79, 416, 53, 23);
        add(rdbtnTieneAire2);
        
        grupoAireRadioButton = new ButtonGroup();
        grupoAireRadioButton.add(rdbtnTieneAire1);
        grupoAireRadioButton.add(rdbtnTieneAire2);
        
        rdbtnTieneAire1.setActionCommand("Si");
        rdbtnNumPuertas1.setActionCommand("2");
        rdbtnNumPuertas2.setActionCommand("4");
        rdbtnTieneAire2.setActionCommand("No");
        
        JEditorPane dtrpnSeguroDanios = new JEditorPane();
        dtrpnSeguroDanios.setText("Seguro Daños");
        dtrpnSeguroDanios.setOpaque(false);
        dtrpnSeguroDanios.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnSeguroDanios.setFocusable(false);
        dtrpnSeguroDanios.setBounds(24, 444, 81, 19);
        add(dtrpnSeguroDanios);
        
        txtSeguroDanios = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtSeguroDanios.setFont(new Font("Inter", Font.PLAIN, 11));
        txtSeguroDanios.setBackground(new Color(0, 0, 0, 5));
        txtSeguroDanios.setBounds(24, 464, 81, 25);
        add(txtSeguroDanios);
        
        txtSeguroTarifa = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtSeguroTarifa.setFont(new Font("Inter", Font.PLAIN, 11));
        txtSeguroTarifa.setBackground(new Color(0, 0, 0, 5));
        txtSeguroTarifa.setBounds(396, 464, 81, 25);
        add(txtSeguroTarifa);
        
        JEditorPane dtrpnTarifaDia = new JEditorPane();
        dtrpnTarifaDia.setText("Tarifa p/dia");
        dtrpnTarifaDia.setOpaque(false);
        dtrpnTarifaDia.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnTarifaDia.setFocusable(false);
        dtrpnTarifaDia.setBounds(396, 444, 81, 19);
        add(dtrpnTarifaDia);
        
        txtSeguroCombustible = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtSeguroCombustible.setFont(new Font("Inter", Font.PLAIN, 11));
        txtSeguroCombustible.setBackground(new Color(0, 0, 0, 5));
        txtSeguroCombustible.setBounds(210, 466, 81, 25);
        add(txtSeguroCombustible);
        
        JEditorPane dtrpnSeguroCombustible = new JEditorPane();
        dtrpnSeguroCombustible.setText("Combustible $");
        dtrpnSeguroCombustible.setOpaque(false);
        dtrpnSeguroCombustible.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnSeguroCombustible.setFocusable(false);
        dtrpnSeguroCombustible.setBounds(210, 446, 86, 19);
        add(dtrpnSeguroCombustible);
        
        txtSeguroVida = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtSeguroVida.setFont(new Font("Inter", Font.PLAIN, 11));
        txtSeguroVida.setBackground(new Color(0, 0, 0, 5));
        txtSeguroVida.setBounds(115, 466, 81, 25);
        add(txtSeguroVida);
        
        JEditorPane dtrpnSeguroVida = new JEditorPane();
        dtrpnSeguroVida.setText("Seguro Vida");
        dtrpnSeguroVida.setOpaque(false);
        dtrpnSeguroVida.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnSeguroVida.setFocusable(false);
        dtrpnSeguroVida.setBounds(115, 446, 81, 19);
        add(dtrpnSeguroVida);
        
        txtSeguroKilometraje = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtSeguroKilometraje.setFont(new Font("Inter", Font.PLAIN, 11));
        txtSeguroKilometraje.setBackground(new Color(0, 0, 0, 5));
        txtSeguroKilometraje.setBounds(306, 466, 81, 25);
        add(txtSeguroKilometraje);
        
        JEditorPane dtrpnSeguroKilometraje = new JEditorPane();
        dtrpnSeguroKilometraje.setText("Seguro          Kilometraje");
        dtrpnSeguroKilometraje.setOpaque(false);
        dtrpnSeguroKilometraje.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnSeguroKilometraje.setFocusable(false);
        dtrpnSeguroKilometraje.setBounds(306, 436, 81, 40);
        add(dtrpnSeguroKilometraje);
        
        if(vehiculo != null && titulo.equals("Editar vehiculo")) {
        	btnAgregar.setActionCommand("EditarUnVehiculo");
        	btnAgregar.setText("Editar");
        	
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
        	
        	txtNombre.setText(vehiculo.getNombreVehiculo());
        	txtModelo.setText(vehiculo.getModelo());
        	txtAnio.setText(vehiculo.getAñoVehiculo());
        	comboBoxCategorias.setSelectedItem(nombreCategoria);
        	comboBoxMarcas.setSelectedItem(nombreMarca);
        	
//        	txtSeguroDanios.setText(String.valueOf(vehiculo.getSeguroDanios()));
//            txtSeguroVida.setText(String.valueOf(vehiculo.getSeguroVida()));
//            txtSeguroKilometraje.setText(String.valueOf(vehiculo.getSeguroKilometraje()));
//            txtSeguroCombustible.setText(String.valueOf(vehiculo.getCombustible()));
//            txtSeguroTarifa.setText(String.valueOf(vehiculo.getTarifaPorDia()));
        	
        	
        	if(vehiculo.getPuertasVehiculo() == 2) {
        		rdbtnNumPuertas1.setSelected(true);

        	}else if (vehiculo.getPuertasVehiculo() == 4) {
        		rdbtnNumPuertas2.setSelected(true);
        	}
        	
        	if(vehiculo.isAireAcondicionado()) {
        		rdbtnTieneAire1.setSelected(true);
        	}else {
        		rdbtnTieneAire2.setSelected(true);
        	}
        }else if (titulo.equals("Crear vehiculo")) {
        	comboBoxCategorias.setSelectedIndex(0);
            comboBoxMarcas.setSelectedIndex(0);
        	btnAgregar.setActionCommand("CrearUnVehiculo");
    		JLabel lblImgCarro = new JLabel();
    		ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
            Image imagen = cargandoCarro.getImage();
            Image imagenReescalada = imagen.getScaledInstance(200, 130, Image.SCALE_SMOOTH);
            ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
            lblImgCarro.setIcon(iconoReescalado);
            panel.add(lblImgCarro);
		}
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

	public BtnBordeado getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(BtnBordeado btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JLabel getLblImgCarro() {
		return lblImgCarro;
	}

	public void setLblImgCarro(JLabel lblImgCarro) {
		this.lblImgCarro = lblImgCarro;
	}

	public JTextFieldRedondeado getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextFieldRedondeado txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextFieldRedondeado getTxtAnio() {
		return txtAnio;
	}

	public void setTxtAnio(JTextFieldRedondeado txtAnio) {
		this.txtAnio = txtAnio;
	}

	public ComboBoxRedondeado<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(ComboBoxRedondeado<String> comboBox) {
		this.comboBox = comboBox;
	}

	public ComboBoxRedondeado<String> getComboBoxMarcas() {
		return comboBoxMarcas;
	}

	public void setComboBoxMarcas(ComboBoxRedondeado<String> comboBoxMarcas) {
		this.comboBoxMarcas = comboBoxMarcas;
	}

	public ComboBoxRedondeado<String> getComboBoxCategorias() {
		return comboBoxCategorias;
	}

	public void setComboBoxCategorias(ComboBoxRedondeado<String> comboBoxCategorias) {
		this.comboBoxCategorias = comboBoxCategorias;
	}

	public JTextFieldRedondeado getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(JTextFieldRedondeado txtModelo) {
		this.txtModelo = txtModelo;
	}

	public ButtonGroup getGrupoPuertas() {
		return grupoPuertas;
	}

	public void setGrupoPuertas(ButtonGroup grupoPuertas) {
		this.grupoPuertas = grupoPuertas;
	}

	public JRadioButton getRdbtnNumPuertas1() {
		return rdbtnNumPuertas1;
	}

	public void setRdbtnNumPuertas1(JRadioButton rdbtnNumPuertas1) {
		this.rdbtnNumPuertas1 = rdbtnNumPuertas1;
	}

	public JRadioButton getRdbtnNumPuertas2() {
		return rdbtnNumPuertas2;
	}

	public void setRdbtnNumPuertas2(JRadioButton rdbtnNumPuertas2) {
		this.rdbtnNumPuertas2 = rdbtnNumPuertas2;
	}

	public JRadioButton getRdbtnTieneAire1() {
		return rdbtnTieneAire1;
	}

	public void setRdbtnTieneAire1(JRadioButton rdbtnTieneAire1) {
		this.rdbtnTieneAire1 = rdbtnTieneAire1;
	}

	public JRadioButton getRdbtnTieneAire2() {
		return rdbtnTieneAire2;
	}

	public void setRdbtnTieneAire2(JRadioButton rdbtnTieneAire2) {
		this.rdbtnTieneAire2 = rdbtnTieneAire2;
	}

	public ButtonGroup getGrupoAireRadioButton() {
		return grupoAireRadioButton;
	}

	public void setGrupoAireRadioButton(ButtonGroup grupoAireRadioButton) {
		this.grupoAireRadioButton = grupoAireRadioButton;
	}

	public JTextFieldRedondeado getTxtSeguroDanios() {
		return txtSeguroDanios;
	}

	public void setTxtSeguroDanios(JTextFieldRedondeado txtSeguroDanios) {
		this.txtSeguroDanios = txtSeguroDanios;
	}

	public JTextFieldRedondeado getTxtSeguroTarifa() {
		return txtSeguroTarifa;
	}

	public void setTxtSeguroTarifa(JTextFieldRedondeado txtSeguroTarifa) {
		this.txtSeguroTarifa = txtSeguroTarifa;
	}

	public JTextFieldRedondeado getTxtSeguroVida() {
		return txtSeguroVida;
	}

	public void setTxtSeguroVida(JTextFieldRedondeado txtSeguroVida) {
		this.txtSeguroVida = txtSeguroVida;
	}

	public JTextFieldRedondeado getTxtSeguroCombustible() {
		return txtSeguroCombustible;
	}

	public void setTxtSeguroCombustible(JTextFieldRedondeado txtSeguroCombustible) {
		this.txtSeguroCombustible = txtSeguroCombustible;
	}

	public JTextFieldRedondeado getTxtSeguroKilometraje() {
		return txtSeguroKilometraje;
	}

	public void setTxtSeguroKilometraje(JTextFieldRedondeado txtSeguroKilometraje) {
		this.txtSeguroKilometraje = txtSeguroKilometraje;
	}
	
	
}
