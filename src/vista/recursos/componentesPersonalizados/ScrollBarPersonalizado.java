package vista.recursos.componentesPersonalizados;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;

public class ScrollBarPersonalizado extends BasicScrollBarUI{
	
	@Override
    protected void configureScrollBarColors() {
        thumbColor = new Color(100, 100, 100, 100); // Set thumb color to transparent
        trackColor = new Color(200, 200, 200, 100); // Set track color to transparent
        super.configureScrollBarColors();
    }
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }
    private JButton createZeroButton() {
        JButton button = new JButton();
        Dimension zeroDim = new Dimension(0, 0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }
      
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(100, 100, 100, 100));
        g2.fillRoundRect(r.x, r.y, r.width, r.height, 20, 20); // Bordes redondeados
        g2.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(100, 100, 100, 0));
        g2.dispose();
    }
    

    
}