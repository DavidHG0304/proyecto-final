package vista.recursos.componentesPersonalizados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PintarRombos extends JPanel {
	private Color color;
    private int tamanio;
    private int xPos;
    private int yPos;

    public PintarRombos(Color color, int tamanio, int xPos, int yPos) {
        this.color = color;
        this.tamanio = tamanio;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        int[] xPoints = {xPos, xPos + (tamanio / 2), xPos, xPos - (tamanio / 2)};
        int[] yPoints = {yPos - (tamanio / 2), yPos, yPos + (tamanio / 2), yPos};
        
        g2d.setColor(color);
        g2d.fillPolygon(xPoints, yPoints, 4);
    
    
    }
    
    
    
    
}