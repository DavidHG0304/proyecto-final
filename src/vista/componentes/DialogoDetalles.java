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
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;

import modelo.entidades.Rentas;
import modelo.entidades.Tarifas;
import modelo.entidades.Vehiculos;
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
public class DialogoDetalles extends JPanel {

		private ArrayList<Rentas> rentas;
		private Vehiculos vehiculo;
		
	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoDetalles(String titulo, ArrayList<Rentas> rentas, Vehiculos vehiculo ) {
		this.rentas = rentas;
		this.vehiculo = vehiculo;
		setBackground(new Color(240, 240, 240));
		setLayout(null);
		setPreferredSize(new Dimension(750, 600));
		setOpaque(false);
		ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
        Image imagen = cargandoCarro.getImage();
        Image imagenReescalada = imagen.getScaledInstance(200, 130, Image.SCALE_SMOOTH);
        ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
        
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

        JLabel lblNewLabel = new JLabel("Tarifas");
        lblNewLabel.setSize(705, 43);
        lblNewLabel.setLocation(0, 10);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Inter", Font.BOLD, 14));
        lblNewLabel.setPreferredSize(new Dimension(695, 32));
        panel.add(lblNewLabel);
        
        

		String[] columnas = { "Usuario", "Fecha Inicial", "Fecha Final", "Auto", "Pago" };
		
		String[][] datos = new String[rentas.size()][5];
		for (int i = 0; i < rentas.size(); i++) {
			Rentas renta = rentas.get(i);
			datos[i][0] = renta.getUsuario().getNombreUsuario() + " " + renta.getUsuario().getApellido();
            datos[i][1] = renta.getFecha_inicial();
            datos[i][2] = renta.getFecha_final();        
            datos[i][3] = renta.getVehiculo().getNombreVehiculo();
            datos[i][4] = ""+renta.getCosto();
            JTable table = new JTable(datos, columnas);
            table.setCellSelectionEnabled(false);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(23, 301, 657, 175);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUI(new ScrollBarPersonalizado());
            scrollPane.getHorizontalScrollBar().setUI(new ScrollBarPersonalizado());
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            panel.add(scrollPane);
        }
		
		String[] columnas2 = { "Seguro Daños", "Seguro Vida", "Seguro Kilometraje", "Combustible", "Tarifa por día" };
		String[][] datos2 = new String[1][5];
		datos2[0][0] = String.valueOf(vehiculo.getTarifa().getSeguro_danios());
		datos2[0][1] = String.valueOf(vehiculo.getTarifa().getSeguro_vida());
		datos2[0][2] = String.valueOf(vehiculo.getTarifa().getSeguro_kilometraje());
		datos2[0][3] = String.valueOf(vehiculo.getTarifa().getSeguro_combustible());
		datos2[0][4] = String.valueOf(vehiculo.getTarifa().getSeguro_tarifa_por_dia());

		JTable table = new JTable(datos2, columnas2);
		table.setCellSelectionEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 100, 657, 50);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarPersonalizado());
		scrollPane.getHorizontalScrollBar().setUI(new ScrollBarPersonalizado());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);

		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 250, 705, 3);
		lblNewLabel_1.setOpaque(true);
		panel.add(lblNewLabel_1);
		
		JLabel lblHistorialRentas = new JLabel("Historial Rentas");
		lblHistorialRentas.setPreferredSize(new Dimension(695, 32));
		lblHistorialRentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialRentas.setFont(new Font("Inter", Font.BOLD, 14));
		lblHistorialRentas.setBounds(0, 250, 705, 43);
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
