package vista.componentes;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import raven.glasspanepopup.GlassPanePopup;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import vista.recursos.componentesPersonalizados.JTextFieldRedondeado;
import vista.recursos.componentesPersonalizados.ScrollBarPersonalizado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class DialogoDetallesCliente extends JPanel {


	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoDetallesCliente(String titulo) {
		setBackground(new Color(240, 240, 240));
		setLayout(null);
		setPreferredSize(new Dimension(750, 600));
		setOpaque(false);
		
        BtnBordeado Cerrar = new BtnBordeado(30, false, true, new Color(33, 147, 246));
        Cerrar.setBounds(20, 564, 115, 25);
        add(Cerrar);
        Cerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GlassPanePopup.closePopupLast();
        	}
        });
        Cerrar.setFont(new Font("Inter", Font.PLAIN, 14));
        Cerrar.setForeground(new Color(33, 147, 246));
        Cerrar.setBackground(new Color(255, 255, 255));
        Cerrar.setText("Cerrar");
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(20, 54, 705, 500);
        panel.setLayout(null);  
        add(panel);
        
        

		String[] columnas = { "Usuario", "Fecha Inicial", "Fecha Final", "Auto", "Pago" };
		String[][] datos = new String[50][5];
		for (int i = 0; i < 50; i++) {
            datos[i][0] = "@Usuario" + (i + 1);
            datos[i][1] = "12/12/2024";
            datos[i][2] = "13/12/2024";        
            datos[i][3] = "AutoEjemplo";     
            datos[i][4] = "$" + (1800 + i);
        }

		JTable table = new JTable(datos, columnas);
		table.setCellSelectionEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 65, 657, 374);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarPersonalizado());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarPersonalizado());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);
		
		JLabel lblHistorialRentas = new JLabel("Historial Rentas");
		lblHistorialRentas.setPreferredSize(new Dimension(695, 32));
		lblHistorialRentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialRentas.setFont(new Font("Inter", Font.BOLD, 14));
		lblHistorialRentas.setBounds(-10, 11, 705, 43);
		panel.add(lblHistorialRentas);
		
		
	}
        
//        JScrollPane scrollPane= new JScrollPane(panel);
//		scrollPane.setBounds(10, 220, 894, 410);
//		scrollPane.setBorder(null);
//		scrollPane.setPreferredSize(new Dimension(894, 360));
//		scrollPane.getVerticalScrollBar().setUI(new ScrollBarPersonalizado());
//      scrollPane.getHorizontalScrollBar().setUI(new ScrollBarPersonalizado());
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		panel.add(scrollPane);
	
	
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
