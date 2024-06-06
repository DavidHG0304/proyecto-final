package vista.componentes;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import raven.glasspanepopup.GlassPanePopup;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DialogoConfirmacion extends JPanel {
	private boolean confirmar = false;
	private BtnBordeado boton;


	/**
	 * Create the panel.
	 */
	public DialogoConfirmacion(String mensaje, String Contenido) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setPreferredSize(new Dimension(553, 195));
		setOpaque(false);
		
		JLabel lblNewLabel = new JLabel(mensaje);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 11, 533, 105);
		add(lblNewLabel);
		
		boton = new BtnBordeado(30, true);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConfirmar(true);
				
			}
		});
		boton.setFont(new Font("Inter", Font.PLAIN, 15));
		boton.setForeground(new Color(255, 255, 255));
		boton.setBackground(new Color(33,147,246));
		boton.setText("Aceptar");
		boton.setBounds(382, 138, 133, 31);
		add(boton);
		
		BtnBordeado cancelar = new BtnBordeado(30, false, true, new Color(33, 147, 246));
		cancelar.setText("Cancelar");
		cancelar.setForeground(new Color(33, 147, 246));
		cancelar.setFont(new Font("Inter", Font.PLAIN, 15));
		cancelar.setBackground(new Color(33, 147, 246));
		cancelar.setBounds(39, 138, 133, 31);
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlassPanePopup.closePopupLast();
			}
		});
		add(cancelar);
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
	
	public BtnBordeado getBoton() {
		return boton;
	}
	public void setBoton(BtnBordeado boton) {
		this.boton = boton;
	}
	public boolean isConfirmar() {
		return confirmar;
	}
	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}
}
