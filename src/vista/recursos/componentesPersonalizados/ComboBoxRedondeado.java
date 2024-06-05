package vista.recursos.componentesPersonalizados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ComboBoxRedondeado<E> extends JComboBox<E>{
	private int radioBorde;
	private Color colorBorde = Color.black;
	
	public ComboBoxRedondeado(int radioBorde, Color colorBorde) {
		this.radioBorde = radioBorde;
		this.colorBorde = colorBorde;
		setOpaque(false);
		
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int height = getHeight();
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radioBorde, radioBorde));
		g2.drawRoundRect(0, 0, getWidth() - 1, height - 1, height, 100);
		g2.dispose();
        super.paintComponent(g);
    }
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorBorde);
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radioBorde, radioBorde);
        g2.dispose();
    }
	
}