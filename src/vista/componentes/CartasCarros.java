package vista.componentes;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import modelo.entidades.Vehiculos;
import raven.glasspanepopup.GlassPanePopup;
import vista.VistaPanelVehiculos;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import vista.recursos.componentesPersonalizados.RoundedPanel;

@SuppressWarnings("serial")
public class CartasCarros extends RoundedPanel{
	private Vehiculos vehiculo;
	private JLabel lblImgCarro;
	
	private JButton bntInfoIcono;
	private JButton lbleditarIcono;
    private JButton btnRentar;
    private JButton lblBorrarIcono;
    private BtnBordeado btnDetalles;
    
	
	
	public CartasCarros(Vehiculos vehiculo) {
        super(30, false, true, new Color(0, 0, 0, 61), 6);
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(208, 317));
        setMaximumSize(new Dimension(208, 317));
        setMinimumSize(new Dimension(208, 317));
        setLayout(null);
        
        componentes(vehiculo);
    }
	
	public BtnBordeado getBtnDetalles() {
		return btnDetalles;
	}
	
	public void setBtnDetalles(BtnBordeado btnDetalles) {
		this.btnDetalles = btnDetalles;
	}

	public JButton getBntInfoIcono() {
		return bntInfoIcono;
	}

	public void setBntInfoIcono(JButton bntInfoIcono) {
		this.bntInfoIcono = bntInfoIcono;
	}

	public JButton getLbleditarIcono() {
		return lbleditarIcono;
	}

	public void setLbleditarIcono(JButton lbleditarIcono) {
		this.lbleditarIcono = lbleditarIcono;
	}

	public JButton getBtnRentar() {
		return btnRentar;
	}

	public void setBtnRentar(JButton btnRentar) {
		this.btnRentar = btnRentar;
	}

	public JButton getLblBorrarIcono() {
		return lblBorrarIcono;
	}

	public void setLblBorrarIcono(JButton lblBorrarIcono) {
		this.lblBorrarIcono = lblBorrarIcono;
	}

	public void componentes(Vehiculos vehiculo) {
//		RoundedPanel cartasCarros = new RoundedPanel(30, false, true, new Color(0,0,0,61), 6);
//		cartasCarros.setBackground(new Color(255, 255, 255));
//		cartasCarros.setBounds(10, 187, 208, 317);
//		cartasCarros.setPreferredSize(new Dimension(208, 317)); // Tamaño preferido fijo
//	    cartasCarros.setMaximumSize(new Dimension(208, 317)); // Tamaño máximo fijo
//	    cartasCarros.setMinimumSize(new Dimension(208, 317)); // Tamaño mínimo fijo
//		panelCartas.add(cartasCarros);
//		cartasCarros.setLayout(null);
		
		JPanel panelImgCarro = new JPanel();
		panelImgCarro.setBounds(8, 22, 188, 140);
		add(panelImgCarro);
		panelImgCarro.setLayout(null);
		
		// Cambiar imagenes por las que coincidan con la de la base de datos
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
		            panelImgCarro.revalidate();
		            panelImgCarro.repaint();
		        });
		    } catch (MalformedURLException e) {
		        ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
		        Image imagen = cargandoCarro.getImage();
		        Image imagenReescalada = imagen.getScaledInstance(187, 140, Image.SCALE_SMOOTH);
		        ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
		        SwingUtilities.invokeLater(() -> {
		            lblImgCarro.setIcon(iconoReescalado);
		            panelImgCarro.revalidate();
		            panelImgCarro.repaint();
		        });
		    }
		});
		loadImageThread.start();
		
		lblImgCarro.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgCarro.setBounds(0, 0, 187, 140);
		panelImgCarro.add(lblImgCarro);
		
		ImageIcon borrarIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eliminar.png"));
		lblBorrarIcono = new JButton(borrarIcono);
		lblBorrarIcono.setActionCommand("Borrar Vehiculo");
		lblBorrarIcono.setOpaque(false);
		lblBorrarIcono.setBorderPainted(false);
		lblBorrarIcono.setFocusPainted(false);
		lblBorrarIcono.setBackground(Color.WHITE);
		lblBorrarIcono.setBounds(184, 5, 14, 14);
		add(lblBorrarIcono);
		
		ImageIcon editarIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/editar.png"));
		lbleditarIcono = new JButton(editarIcono);
		lbleditarIcono.setActionCommand("Editar Vehiculo");
		lbleditarIcono.setOpaque(false);
		lbleditarIcono.setBorderPainted(false);
		lbleditarIcono.setFocusPainted(false);
		lbleditarIcono.setBackground(Color.WHITE);
		lbleditarIcono.setBounds(10, 5, 14, 14);
		add(lbleditarIcono);
		
		JLabel lblLinea = new JLabel("");
		lblLinea.setOpaque(true);
		lblLinea.setBackground(new Color(0,0,0,61));
		lblLinea.setBounds(0, 281, 202, 1);
		add(lblLinea);
		
		JLabel lblCosto = new JLabel("$"+vehiculo.getCostoTotal());
		lblCosto.setFont(new Font("Inter", Font.BOLD, 11));
		lblCosto.setBounds(10, 288, 55, 14);
		add(lblCosto);
		
		JLabel lbldia = new JLabel("/Dia");
		lbldia.setFont(new Font("Inter", Font.PLAIN, 10));
		lbldia.setBounds(57, 290, 20, 14);
		lbldia.setForeground(new Color(0,0,0,86));
		add(lbldia);
		
		ImageIcon flechaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/flecha.png"));
		JLabel lblflechaIcono = new JLabel(flechaIcono);
		lblflechaIcono.setBounds(175, 289, 14, 14);
		add(lblflechaIcono);
		
		btnRentar = new JButton();
		btnRentar.setText("Rentar");
		btnRentar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRentar.setOpaque(false);
		btnRentar.setForeground(new Color(33, 147, 246));
		btnRentar.setFont(new Font("Inter", Font.PLAIN, 10));
		btnRentar.setFocusPainted(false);
		btnRentar.setBorderPainted(false);
		btnRentar.setBackground(Color.WHITE);
		btnRentar.setBounds(122, 284, 71, 23);
		add(btnRentar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(8, 165, 188, 110);
		add(panel);
		
		ImageIcon infoIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/info.png"));
		panel.setLayout(null);
		bntInfoIcono = new JButton(infoIcono);
		bntInfoIcono.setActionCommand("Info pVehiculo");
		bntInfoIcono.setOpaque(false);
		bntInfoIcono.setBorderPainted(false);
		bntInfoIcono.setFocusPainted(false);
		bntInfoIcono.setBackground(Color.WHITE);
		bntInfoIcono.setBounds(170, 5, 14, 14);
		panel.add(bntInfoIcono);
		
		ImageIcon estrellaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/estrella.png"));
		JLabel lblestrellaIcono = new JLabel(estrellaIcono);
		lblestrellaIcono.setBounds(5, 5, 14, 14);
		panel.add(lblestrellaIcono);
		
		JLabel lblNombre = new JLabel(vehiculo.getNombreVehiculo()+" "+vehiculo.getModelo()+" - "+vehiculo.getCategoria());
		lblNombre.setFont(new Font("Inter", Font.BOLD, 10));
		lblNombre.setBounds(7, 28, 179, 14);
//		lblNombre.setOpaque(true);
//		lblNombre.setBackground(Color.gray);
		panel.add(lblNombre);
		
		DecimalFormat formatter = new DecimalFormat("#0.0");
		double randomNumero = 3+ Math.random()*2;
		JLabel lblEstrellas = new JLabel(""+formatter.format(randomNumero));
		lblEstrellas.setFont(new Font("Inter", Font.BOLD, 10));
		lblEstrellas.setBounds(22, 5, 24, 14);
		panel.add(lblEstrellas);
		
		JLabel lblReseas = new JLabel("(+999 Reseñas)");
		lblReseas.setFont(new Font("Inter", Font.PLAIN, 10));
		lblReseas.setBounds(42, 5, 80, 14);
		lblReseas.setForeground(new Color(0,0,0,86));
		panel.add(lblReseas);
		
		ImageIcon personasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/persona2.png"));
		JLabel lblPersonasIcono = new JLabel(personasIcono);
		lblPersonasIcono.setBounds(5, 46, 14, 14);
		panel.add(lblPersonasIcono);
		
		ImageIcon fechaIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/fecha.png"));
		JLabel lblFechaIcono = new JLabel(fechaIcono);
		lblFechaIcono.setBounds(5, 69, 14, 14);
		panel.add(lblFechaIcono);
		
		JLabel lblPersonas = new JLabel(""+vehiculo.getPuertasVehiculo());
		lblPersonas.setForeground(new Color(0, 0, 0, 86));
		lblPersonas.setFont(new Font("Inter", Font.PLAIN, 10));
		lblPersonas.setBounds(22, 47, 15, 14);
		panel.add(lblPersonas);
		
		JLabel lblFecha = new JLabel(vehiculo.getAñoVehiculo());
		lblFecha.setForeground(new Color(0, 0, 0, 86));
		lblFecha.setFont(new Font("Inter", Font.PLAIN, 10));
		lblFecha.setBounds(22, 69, 30, 14);
		panel.add(lblFecha);
		
		ImageIcon transmisionIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/transmision.png"));
		JLabel lbltransmisionIcono = new JLabel(transmisionIcono);
		lbltransmisionIcono.setBounds(108, 46, 14, 14);
		panel.add(lbltransmisionIcono);
		
		ImageIcon puertasIcono = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/puertas.png"));
		JLabel lblpuertasIcono = new JLabel(puertasIcono);
		lblpuertasIcono.setBounds(108, 69, 14, 14);
		panel.add(lblpuertasIcono);
		
		JLabel lblAutomatico = new JLabel(vehiculo.getTransmision());
		lblAutomatico.setForeground(new Color(0, 0, 0, 86));
		lblAutomatico.setFont(new Font("Inter", Font.PLAIN, 10));
		lblAutomatico.setBounds(125, 46, 59, 14);
		panel.add(lblAutomatico);
		
		JLabel lblPuertas = new JLabel(""+vehiculo.getPuertasVehiculo());
		lblPuertas.setForeground(new Color(0, 0, 0, 86));
		lblPuertas.setFont(new Font("Inter", Font.PLAIN, 10));
		lblPuertas.setBounds(125, 69, 59, 14);
		panel.add(lblPuertas);
		
		
		btnDetalles = new BtnBordeado(20, false, true, new Color(0,0,0,100));
		btnDetalles.setText("Detalles");
		btnDetalles.setFont(new Font("Inter", Font.BOLD, 10));
		btnDetalles.setBackground(Color.WHITE);
		btnDetalles.setBounds(40, 90, 104, 18);
		panel.add(btnDetalles);
	}
	
	public Vehiculos getVehiculo() {
        return vehiculo; // Obtener el vehiculo
    }
	
}
