package vista.recursos.componentesPersonalizados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class JPasswordFieldRedondeado extends JPasswordField {
    private int borderRadius;
    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";
    private Color colorB = Color.black;
    
    public JPasswordFieldRedondeado() {
    }
    
    public JPasswordFieldRedondeado(int size, int borderRadius) {
    	super(size);
    	this.borderRadius = borderRadius;
    	setOpaque(false); 
    }
    
    public String getHint() {
        return hint;
    }
    public void setHint(String hint) {
        this.hint = hint;
    }
    public Icon getPrefixIcon() {
        return prefixIcon;
    }
    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }
    public Icon getSuffixIcon() {
        return suffixIcon;
    }
    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }
    
    public Color getColorB() {
		return colorB;
	}
	public void setColorB(Color colorB) {
		this.colorB = colorB;
	}
    
    protected void paintComponent(Graphics g) {
    	((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
        paintIcon(g);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
    	g.setColor(colorB);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
    }
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
        return shape.contains(x, y);
    }
    
    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }
    }
    
    private void initBorder() {
        int left = 15;
        int right = 35;
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + 15;
        }
        if (suffixIcon != null) {
            //  suffix is right
            right = suffixIcon.getIconWidth() + 15;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
    
}