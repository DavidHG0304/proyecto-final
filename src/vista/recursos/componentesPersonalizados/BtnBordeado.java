package vista.recursos.componentesPersonalizados;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class BtnBordeado extends JButton{

	private boolean botonPresionado;
	private int borde;
	private boolean aplicarGradiant;
	private GradientPaint colorGradiant;
	
	private boolean sinBordePers;
	private Color colorBorde;
	
	// Boton con color relleno
    public BtnBordeado(int borde, boolean aplicarGradiant) {
        this(borde, aplicarGradiant, false, null);
    }

    // Boton con solo borde
    public BtnBordeado(int borde, boolean aplicarGradiant, boolean sinBordePers, Color colorBorde) {
        this.borde = borde;
        this.aplicarGradiant = aplicarGradiant;
        this.sinBordePers = sinBordePers;
        this.colorBorde = colorBorde;
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(7, 5, 7, 5));
        setFocusPainted(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    botonPresionado = true;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    botonPresionado = false;
                    repaint();
                }
            }
        });
    }
	
	
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (sinBordePers) {
            // Solo borde
            g2.setColor(colorBorde);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borde, borde);
            if (botonPresionado) {
                g2.setColor(new Color(0,0,0,15));
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borde, borde));
            } else {
                g2.setColor(getBackground());
            }
        } else {
            // Con relleno
            if (aplicarGradiant) {
                if (botonPresionado) {
                    colorGradiant = new GradientPaint(0.0f, 0.0f, new Color(19, 79, 195), 0, getHeight(), new Color(20, 118, 204), true);
                } else {
                    colorGradiant = new GradientPaint(0.0f, 0.0f, new Color(0, 87, 255), 0, getHeight(), new Color(33, 147, 246), true);
                }
                g2.setPaint(colorGradiant);
            } else {
                if (botonPresionado) {
                    g2.setColor(getBackground().darker());
                } else {
                    g2.setColor(getBackground());
                }
            }
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borde, borde));
        }

        g2.dispose();
        super.paintComponent(g);
    }
}
