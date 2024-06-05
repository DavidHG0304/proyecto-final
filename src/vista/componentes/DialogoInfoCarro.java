package vista.componentes;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;



@SuppressWarnings("serial")
public class DialogoInfoCarro extends JPanel {
	private JLabel lblNombreVehiculo;
	private JEditorPane tPersonas;
	private JEditorPane anioV;
	private JEditorPane nPuertas;
	private JEditorPane tTransmision;
	private JEditorPane aireAcondicionado;
	private JEditorPane kilometraje;
	private JLabel lblImgCarro;
	
	
	/**
	 * Create the panel.
	 * @param url 
	 */
	public DialogoInfoCarro(Vehiculos vehiculo) {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setPreferredSize(new Dimension(500, 300));
		setOpaque(false);
		
		lblNombreVehiculo = new JLabel("ASD");
		lblNombreVehiculo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreVehiculo.setFont(new Font("Inter", Font.BOLD, 14));
		lblNombreVehiculo.setBounds(24, 22, 453, 32);
		add(lblNombreVehiculo);
		
		
		tPersonas = new JEditorPane();
		tPersonas.setFont(new Font("Inter", Font.PLAIN, 10));
		tPersonas.setText("ASD");
		tPersonas.setFocusable(false);
		tPersonas.setBounds(55, 204, 124, 19);
		tPersonas.setOpaque(false);
		add(tPersonas);
		
		BtnBordeado Cerrar = new BtnBordeado(30, false, true, new Color(33, 147, 246));
		Cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlassPanePopup.closePopupLast();
			}
		});
		Cerrar.setFont(new Font("Inter", Font.PLAIN, 14));
		Cerrar.setForeground(new Color(33, 147, 246));
		Cerrar.setBackground(new Color(255, 255, 255));
		Cerrar.setText("Cerrar");
		Cerrar.setBounds(24, 264, 115, 25);
		add(Cerrar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(24, 56, 453, 140);
		add(panel);
		
		anioV = new JEditorPane();
		anioV.setText("ASD");
		anioV.setOpaque(false);
		anioV.setFont(new Font("Inter", Font.PLAIN, 10));
		anioV.setFocusable(false);
		anioV.setBounds(55, 234, 124, 19);
		add(anioV);
		
		nPuertas = new JEditorPane();
		nPuertas.setText("ASD");
		nPuertas.setOpaque(false);
		nPuertas.setFont(new Font("Inter", Font.PLAIN, 10));
		nPuertas.setFocusable(false);
		nPuertas.setBounds(203, 204, 124, 19);
		add(nPuertas);
		
		kilometraje = new JEditorPane();
		kilometraje.setText(""+1500);
		kilometraje.setOpaque(false);
		kilometraje.setFont(new Font("Inter", Font.PLAIN, 10));
		kilometraje.setFocusable(false);
		kilometraje.setBounds(203, 234, 124, 19);
		add(kilometraje);
		
		tTransmision = new JEditorPane();
		tTransmision.setText("ASD");
		tTransmision.setOpaque(false);
		tTransmision.setFont(new Font("Inter", Font.PLAIN, 10));
		tTransmision.setFocusable(false);
		tTransmision.setBounds(353, 204, 124, 19);
		add(tTransmision);
		
		aireAcondicionado = new JEditorPane();
		aireAcondicionado.setText(" ");
		aireAcondicionado.setOpaque(false);
		aireAcondicionado.setFont(new Font("Inter", Font.PLAIN, 10));
		aireAcondicionado.setFocusable(false);
		aireAcondicionado.setBounds(353, 234, 124, 19);
		add(aireAcondicionado);
		
		// Imagenes
		ImageIcon personasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/persona2.png"));
		JLabel lblPersonasIcono = new JLabel(personasIcono);
		lblPersonasIcono.setBounds(35, 204, 14, 14);
		add(lblPersonasIcono);
		
		ImageIcon puertasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/puertas.png"));
		JLabel lblpuertasIcono = new JLabel(puertasIcono);
		lblpuertasIcono.setBounds(184, 204, 14, 14);
		add(lblpuertasIcono);
		
		ImageIcon fechaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/fecha.png"));
		JLabel lblFecha = new JLabel(fechaIcono);
		lblFecha.setBounds(35, 235, 14, 14);
		add(lblFecha);
		
		ImageIcon kilomeIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/kilometraje.png"));
		JLabel lblKilometraje = new JLabel(kilomeIcono);
		lblKilometraje.setBounds(184, 235, 14, 14);
		add(lblKilometraje);
		
		ImageIcon transmisionIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/transmision.png"));
		JLabel lblTransmision = new JLabel(transmisionIcono);
		lblTransmision.setBounds(329, 204, 14, 14);
		add(lblTransmision);
		
		ImageIcon acIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/aireAcondicionado.png"));
		JLabel lblAC = new JLabel(acIcono);
		lblAC.setBounds(329, 239, 14, 14);
		add(lblAC);
		
		lblImgCarro = new JLabel();
		Thread loadImageThread = new Thread(() -> {
		    try {
		        URL imageUrl = new URL(vehiculo.getImagenUrl());
		        ImageIcon imagenCarro = new ImageIcon(imageUrl);
		        Image imagen = imagenCarro.getImage();
		        Image imagenReescalada = imagen.getScaledInstance(187, 140, Image.SCALE_SMOOTH);
		        ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
		        SwingUtilities.invokeLater(() -> {
		            lblImgCarro.setIcon(iconoReescalado);
		            panel.revalidate();
		            panel.repaint();
		        });
		    } catch (MalformedURLException e) {
		        ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
		        Image imagen = cargandoCarro.getImage();
		        Image imagenReescalada = imagen.getScaledInstance(187, 140, Image.SCALE_SMOOTH);
		        ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
		        SwingUtilities.invokeLater(() -> {
		            lblImgCarro.setIcon(iconoReescalado);
		            panel.revalidate();
		            panel.repaint();
		        });
		    }
		});
		loadImageThread.start();
        
		lblImgCarro.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgCarro.setBounds(0, 0, 187, 140);
		panel.add(lblImgCarro);
        
		if (vehiculo != null) {
			lblNombreVehiculo.setText(vehiculo.getNombreVehiculo()+" "+vehiculo.getModelo()+" - "+vehiculo.getCategoria());
			tPersonas.setText(""+vehiculo.getPuertasVehiculo());
			anioV.setText(vehiculo.getAñoVehiculo());
			nPuertas.setText(""+vehiculo.getPuertasVehiculo());
			tTransmision.setText(""+vehiculo.getTransmision());
			kilometraje.setText(""+vehiculo.getKilometrajeVehiculo());
			
			if (vehiculo.isAireAcondicionado()) {
				aireAcondicionado.setText("Si");
			} else {
				aireAcondicionado.setText("No");
			}
		}
        panel.revalidate();
        panel.repaint();
	}
	
	
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
