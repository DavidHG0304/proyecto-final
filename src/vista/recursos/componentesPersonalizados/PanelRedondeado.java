package vista.recursos.componentesPersonalizados;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class PanelRedondeado extends JPanel {
    private int borderRadius;
    private GradientPaint colorGradiant;
    private boolean conBorde = false;
    private boolean conSombra = false;
	private Color color;
	private int pixels;

    public PanelRedondeado(int borderRadius) {
    	super();
        this.borderRadius = borderRadius;
        setOpaque(false);
    }
    
    public PanelRedondeado(int borderRadius, boolean conBorde, Color color) {
    	super();
        this.borderRadius = borderRadius;
        this.conBorde = conBorde;
        this.color = color;
        setOpaque(false);
    }
    
    public PanelRedondeado(int borderRadius, boolean conBorde, boolean conSombra, Color color, int pix) {
    	super();
        this.borderRadius = borderRadius;
        this.conBorde = conBorde;
        this.color = color;
        this.conSombra = conSombra;
        setOpaque(false);
        this.pixels = pix;
		Border border = BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels);
		this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));
		this.setLayout(new BorderLayout());
    }
    
    
	public GradientPaint getColorGradiant() {
		return colorGradiant;
	}
	public void setColorGradiant(GradientPaint colorGradiant) {
		this.colorGradiant = colorGradiant;
	}

	protected void paintComponent(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (conSombra) {
			int shade = 0;
			int topOpacity = 80;
			for (int i = 0; i < pixels; i++) {
				g.setColor(color);
				g.setColor(new Color(shade, shade, shade, ((topOpacity / pixels) * i)));
				g.drawRoundRect(0, 0, this.getWidth() - ((i * 1) + 1), this.getHeight() - ((i*1) + 1),borderRadius,borderRadius);
				super.paintComponent(g);
			}
		}
		else {
			int height = getHeight();
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
			g.drawRoundRect(0, 0, getWidth() - 1, height - 1, height, 100);
			super.paintComponent(g);
		}
		
    }
    protected void paintBorder(Graphics g) {
    	if(conBorde) {
    		g.setColor(color);
    		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
    	}
    }
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
        return shape.contains(x, y);
    }
}