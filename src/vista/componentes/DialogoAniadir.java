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
	
	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoAniadir(String titulo, Vehiculos vehiculo, ArrayList<String> categorias, ArrayList<String> marcas) {
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
        
        BtnBordeado btnAgregar = new BtnBordeado(30, false);
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
        
        JTextFieldRedondeado txtNombre = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtNombre.setFont(new Font("Inter", Font.PLAIN, 11));
        txtNombre.setBackground(new Color(0, 0, 0, 5));
        txtNombre.setBounds(263, 256, 214, 25);
        add(txtNombre);
        
        JTextFieldRedondeado txtAnio = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtAnio.setFont(new Font("Inter", Font.PLAIN, 11));
        txtAnio.setBackground(new Color(0, 0, 0, 5));
        txtAnio.setBounds(263, 377, 214, 25);
        add(txtAnio);
        
        ComboBoxRedondeado<String> comboBox = new ComboBoxRedondeado<String>(20, new Color(0,0,0,60));
        UIManager.put("Component.innerFocusWidth", comboBox);
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Automatico", "Manual"}));
        comboBox.setFont(new Font("Inter", Font.PLAIN, 11));
        comboBox.setOpaque(false);
        comboBox.setBackground(new Color(0, 0, 0, 5));
        comboBox.setBounds(24, 377, 214, 25);
        // IMPORTANTE 
        comboBox.setLightWeightPopupEnabled(false);
        add(comboBox);
        
        JRadioButton rdbtnNumPuertas1 = new JRadioButton("2");
        rdbtnNumPuertas1.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnNumPuertas1.setBounds(263, 430, 53, 23);
        add(rdbtnNumPuertas1);
        
        JRadioButton rdbtnNumPuertas2 = new JRadioButton("4");
        rdbtnNumPuertas2.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnNumPuertas2.setBounds(318, 430, 53, 23);
        add(rdbtnNumPuertas2);
        
        ButtonGroup grupoPuertas = new ButtonGroup();
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
        dtrpnTipoDelAuto.setBounds(24, 297, 124, 19);
        add(dtrpnTipoDelAuto);
        
        JEditorPane maracaAuto = new JEditorPane();
        maracaAuto.setText("Marca del auto");
        maracaAuto.setOpaque(false);
        maracaAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        maracaAuto.setFocusable(false);
        maracaAuto.setBounds(24, 236, 124, 19);
        add(maracaAuto);
        
        JEditorPane dtrpnAoDelAuto = new JEditorPane();
        dtrpnAoDelAuto.setText("Año del auto");
        dtrpnAoDelAuto.setOpaque(false);
        dtrpnAoDelAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnAoDelAuto.setFocusable(false);
        dtrpnAoDelAuto.setBounds(263, 358, 124, 19);
        add(dtrpnAoDelAuto);
        
        JEditorPane dtrpnNombre = new JEditorPane();
        dtrpnNombre.setText("Nombre del auto");
        dtrpnNombre.setOpaque(false);
        dtrpnNombre.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnNombre.setFocusable(false);
        dtrpnNombre.setBounds(263, 236, 124, 19);
        add(dtrpnNombre);
        
        JEditorPane dtrpnTransmision = new JEditorPane();
        dtrpnTransmision.setText("Transmision");
        dtrpnTransmision.setOpaque(false);
        dtrpnTransmision.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnTransmision.setFocusable(false);
        dtrpnTransmision.setBounds(24, 358, 124, 19);
        add(dtrpnTransmision);
        
        JEditorPane dtrpnPuertasDelAuto = new JEditorPane();
        dtrpnPuertasDelAuto.setText("Puertas del auto");
        dtrpnPuertasDelAuto.setOpaque(false);
        dtrpnPuertasDelAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnPuertasDelAuto.setFocusable(false);
        dtrpnPuertasDelAuto.setBounds(263, 409, 124, 19);
        add(dtrpnPuertasDelAuto);
        
        ComboBoxRedondeado<String> comboBoxMarcas = new ComboBoxRedondeado<String>(20, new Color(0, 0, 0, 60));
        comboBoxMarcas.setOpaque(false);
        comboBoxMarcas.setLightWeightPopupEnabled(false);
        comboBoxMarcas.setFont(new Font("Inter", Font.PLAIN, 11));
        comboBoxMarcas.setBackground(new Color(0, 0, 0, 5));
        comboBoxMarcas.setBounds(24, 257, 214, 25);
//      comboBoxMarcas.setModel(new DefaultComboBoxModel<>(new String[] {"Automatico", "Manual"}));
        add(comboBoxMarcas);
        
        ComboBoxRedondeado<String> comboBoxCategorias = new ComboBoxRedondeado<String>(20, new Color(0, 0, 0, 60));
        comboBoxCategorias.setOpaque(false);
        comboBoxCategorias.setLightWeightPopupEnabled(false);
        comboBoxCategorias.setFont(new Font("Inter", Font.PLAIN, 11));
        comboBoxCategorias.setBackground(new Color(0, 0, 0, 5));
        comboBoxCategorias.setBounds(24, 318, 214, 25);
        comboBox.setLightWeightPopupEnabled(false);
//      comboBoxCategorias.setModel(new DefaultComboBoxModel<>(new String[] {"Automatico", "Manual"}));
        add(comboBoxCategorias);
        
        comboBoxMarcas.setModel(new DefaultComboBoxModel<>(categorias.toArray(new String[0])));
        comboBoxCategorias.setModel(new DefaultComboBoxModel<>(marcas.toArray(new String[0])));
        
        JTextFieldRedondeado txtModelo = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtModelo.setFont(new Font("Inter", Font.PLAIN, 11));
        txtModelo.setBackground(new Color(0, 0, 0, 5));
        txtModelo.setBounds(263, 318, 214, 25);
        add(txtModelo);
        
        JEditorPane dtrpnModelo = new JEditorPane();
        dtrpnModelo.setText("Modelo del auto");
        dtrpnModelo.setOpaque(false);
        dtrpnModelo.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnModelo.setFocusable(false);
        dtrpnModelo.setBounds(263, 298, 124, 19);
        add(dtrpnModelo);
        
        JEditorPane dtrpnTieneAire = new JEditorPane();
        dtrpnTieneAire.setText("Tiene A/C");
        dtrpnTieneAire.setOpaque(false);
        dtrpnTieneAire.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnTieneAire.setFocusable(false);
        dtrpnTieneAire.setBounds(24, 409, 124, 19);
        add(dtrpnTieneAire);
        
        JRadioButton rdbtnTieneAire1 = new JRadioButton("Si");
        rdbtnTieneAire1.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnTieneAire1.setBounds(24, 430, 53, 23);
        add(rdbtnTieneAire1);
        
        JRadioButton rdbtnTieneAire2 = new JRadioButton("No");
        rdbtnTieneAire2.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnTieneAire2.setBounds(79, 430, 53, 23);
        add(rdbtnTieneAire2);
        
        ButtonGroup grupoAireRadioButton = new ButtonGroup();
        grupoAireRadioButton.add(rdbtnTieneAire1);
        grupoAireRadioButton.add(rdbtnTieneAire2);
        
        if(vehiculo != null && titulo.equals("Editar vehiculo")) {
        	System.out.println("HOLa");
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
}
