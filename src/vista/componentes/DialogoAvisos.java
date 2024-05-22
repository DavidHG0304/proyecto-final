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
public class DialogoAvisos extends JPanel {


	/**
	 * Create the panel.
	 */
	public DialogoAvisos(String Mensasje, String Contenido) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setPreferredSize(new Dimension(300, 255));
		setOpaque(false);
		
		JLabel lblNewLabel = new JLabel(Mensasje);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 33));
		lblNewLabel.setBounds(10, 23, 278, 70);
		add(lblNewLabel);
		
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font("Inter", Font.PLAIN, 14));
		editorPane.setText(Contenido);
		editorPane.setFocusable(false);
		editorPane.setBounds(31, 98, 241, 85);
		editorPane.setOpaque(false);
		add(editorPane);
		
		BtnBordeado boton = new BtnBordeado(30, true);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlassPanePopup.closePopupLast();
			}
		});
		boton.setFont(new Font("Inter", Font.PLAIN, 18));
		boton.setForeground(new Color(255, 255, 255));
		boton.setBackground(new Color(33,147,246));
		boton.setText("Aceptar");
		boton.setBounds(50, 182, 199, 31);
		add(boton);
		
	}
	
	
	@Override
	protected void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),15,15));
		g2.dispose();
		super.paintComponent(g);
	}
}
