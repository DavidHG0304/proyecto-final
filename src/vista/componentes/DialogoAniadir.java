package vista.componentes;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import raven.glasspanepopup.GlassPanePopup;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import vista.recursos.componentesPersonalizados.JTextFieldRedondeado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class DialogoAniadir extends JPanel {


	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoAniadir(String titulo) {
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
		
		JTextFieldRedondeado txtMarca = new JTextFieldRedondeado(20,20, new Color(0,0,0,60));
		txtMarca.setFont(new Font("Inter", Font.PLAIN, 11));
		txtMarca.setBounds(24, 256, 214, 25);
		txtMarca.setBackground(new Color(0,0,0,5));
		add(txtMarca);
		
		JLabel lblImgCarro = new JLabel();
		ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
        Image imagen = cargandoCarro.getImage();
        Image imagenReescalada = imagen.getScaledInstance(200, 130, Image.SCALE_SMOOTH);
        ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
        lblImgCarro.setIcon(iconoReescalado);
        panel.add(lblImgCarro);
        
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
        
        JTextFieldRedondeado txtMarca_1 = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtMarca_1.setFont(new Font("Inter", Font.PLAIN, 11));
        txtMarca_1.setBackground(new Color(0, 0, 0, 5));
        txtMarca_1.setBounds(263, 256, 214, 25);
        add(txtMarca_1);
        
        JTextFieldRedondeado txtMarca_2 = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtMarca_2.setFont(new Font("Inter", Font.PLAIN, 11));
        txtMarca_2.setBackground(new Color(0, 0, 0, 5));
        txtMarca_2.setBounds(24, 317, 214, 25);
        add(txtMarca_2);
        
        JTextFieldRedondeado txtMarca_2_1 = new JTextFieldRedondeado(20, 20, new Color(0, 0, 0, 60));
        txtMarca_2_1.setFont(new Font("Inter", Font.PLAIN, 11));
        txtMarca_2_1.setBackground(new Color(0, 0, 0, 5));
        txtMarca_2_1.setBounds(263, 317, 214, 25);
        add(txtMarca_2_1);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Inter", Font.PLAIN, 11));
        comboBox.setOpaque(false);
        
        comboBox.setBounds(24, 413, 214, 25);
        add(comboBox);
        
        JRadioButton rdBtnNpuertas = new JRadioButton("2");
        rdBtnNpuertas.setFont(new Font("Inter", Font.PLAIN, 11));
        rdBtnNpuertas.setBounds(263, 414, 53, 23);
        add(rdBtnNpuertas);
        
        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("4");
        rdbtnNewRadioButton_1.setFont(new Font("Inter", Font.PLAIN, 11));
        rdbtnNewRadioButton_1.setBounds(318, 414, 53, 23);
        add(rdbtnNewRadioButton_1);
        
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
        dtrpnAoDelAuto.setBounds(263, 297, 124, 19);
        add(dtrpnAoDelAuto);
        
        JEditorPane dtrpnModeloDeL = new JEditorPane();
        dtrpnModeloDeL.setText("Modelo del auto");
        dtrpnModeloDeL.setOpaque(false);
        dtrpnModeloDeL.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnModeloDeL.setFocusable(false);
        dtrpnModeloDeL.setBounds(263, 236, 124, 19);
        add(dtrpnModeloDeL);
        
        JEditorPane dtrpnTransmision = new JEditorPane();
        dtrpnTransmision.setText("Transmision");
        dtrpnTransmision.setOpaque(false);
        dtrpnTransmision.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnTransmision.setFocusable(false);
        dtrpnTransmision.setBounds(24, 393, 124, 19);
        add(dtrpnTransmision);
        
        JEditorPane dtrpnPuertasDelAuto = new JEditorPane();
        dtrpnPuertasDelAuto.setText("Puertas del auto");
        dtrpnPuertasDelAuto.setOpaque(false);
        dtrpnPuertasDelAuto.setFont(new Font("Inter", Font.PLAIN, 11));
        dtrpnPuertasDelAuto.setFocusable(false);
        dtrpnPuertasDelAuto.setBounds(263, 393, 124, 19);
        add(dtrpnPuertasDelAuto);
        
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
