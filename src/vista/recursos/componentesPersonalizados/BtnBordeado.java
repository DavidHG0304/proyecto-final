package vista.recursos.componentesPersonalizados;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class BtnBordeado extends JButton{

	private boolean botonPresionado;
	
	public BtnBordeado(int borde){
		setContentAreaFilled(false);
		setBorder(new EmptyBorder(7,5,7,5));
		setFocusPainted(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					botonPresionado=true;
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					botonPresionado=false;
				}
			}
			
		});
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(botonPresionado) {
			g2.setColor(getBackground().darker());
		}else {
			g2.setColor(getBackground());
		}
		g2.fill(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),20,20));
		g2.dispose();
		super.paintComponent(g);
	}
}
