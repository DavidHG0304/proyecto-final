package vista.componentes;

import java.awt.Color;
import java.awt.Dimension;

import vista.recursos.componentesPersonalizados.PanelRedondeado;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controlador.ControladorClientes;
import controlador.ControladorRentas;
import modelo.entidades.Rentas;
import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import vista.recursos.componentesPersonalizados.BtnBordeado;

public class CartasRentas extends PanelRedondeado {
	private Rentas renta;
	private Usuarios usuario;
	private Vehiculos vehiculo;
	private BtnBordeado btnbrdEditar;
	private boolean seleccionado;
	private BtnBordeado btnbrdEliminar;
	private ControladorRentas controlador;
	
	public CartasRentas(Rentas renta, Usuarios usuario, Vehiculos vehiculo, ControladorRentas controlador) {
		super(30, false, true, new Color(0, 0, 0, 10), 5);
		this.renta = renta;
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.controlador = controlador;
		this.seleccionado = false;
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(780, 150));
        setMaximumSize(new Dimension(800, 150));
//      setLayout(null);
        
        cartas(renta);
	}
	
	
	public void cartas(Rentas renta) {
		
		PanelRedondeado panelPrincipalCartas = new PanelRedondeado(35, false, Color.WHITE);
		panelPrincipalCartas.setBackground(new Color(255, 255, 255));
		panelPrincipalCartas.setBounds(5, 5, 765, 134);
		panelPrincipalCartas.setLayout(null);
        add(panelPrincipalCartas);
		
		JPanel panelImgCarro = new JPanel();
        panelImgCarro.setLayout(null);
        panelImgCarro.setBounds(0, 2, 212, 127);
        panelPrincipalCartas.add(panelImgCarro);
        
        JLabel lblImgCarro = new JLabel();
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
        lblImgCarro.setBounds(0, 0, 212, 116);
		panelImgCarro.add(lblImgCarro);
		panelImgCarro.repaint();
		panelImgCarro.revalidate();
        
        JLabel lblNombre = new JLabel(vehiculo.getNombreVehiculo()+" "+vehiculo.getModelo());
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(new Font("Inter", Font.BOLD, 10));
        lblNombre.setBounds(5, 115, 197, 14);
        panelImgCarro.add(lblNombre);
        
        JPanel panelInfoCliente = new JPanel();
        panelInfoCliente.setBackground(new Color(255, 255, 255));
        panelInfoCliente.setBounds(222, 3, 398, 136);
        panelInfoCliente.setLayout(null);
        panelPrincipalCartas.add(panelInfoCliente);
        
        JLabel lblInformacinCliente = new JLabel("Información cliente");
        lblInformacinCliente.setFont(new Font("Inter", Font.BOLD, 13));
        lblInformacinCliente.setBounds(10, 11, 169, 14);
        panelInfoCliente.add(lblInformacinCliente);
        
        JLabel lblNombreC = new JLabel("Nombre del cliente");
        lblNombreC.setFont(new Font("Inter", Font.BOLD, 10));
        lblNombreC.setBounds(10, 32, 99, 14);
        panelInfoCliente.add(lblNombreC);
        
        JLabel lblNombreC_1 = new JLabel(usuario.getNombreUsuario() + " " + usuario.getApellido());
        lblNombreC_1.setFont(new Font("Inter", Font.PLAIN, 10));
        lblNombreC_1.setBounds(10, 48, 99, 14);
        panelInfoCliente.add(lblNombreC_1);
        
        JLabel lblFechaInicio = new JLabel("Fecha inicio");
        lblFechaInicio.setFont(new Font("Inter", Font.BOLD, 10));
        lblFechaInicio.setBounds(10, 91, 99, 14);
        panelInfoCliente.add(lblFechaInicio);
        
        JLabel lblFechaInicio_1 = new JLabel(renta.getFecha_inicial());
        lblFechaInicio_1.setFont(new Font("Inter", Font.PLAIN, 10));
        lblFechaInicio_1.setBounds(10, 106, 99, 14);
        panelInfoCliente.add(lblFechaInicio_1);
        
        JLabel lblFechaFinal = new JLabel("Fecha inicio");
        lblFechaFinal.setFont(new Font("Inter", Font.BOLD, 10));
        lblFechaFinal.setBounds(87, 91, 99, 14);
        panelInfoCliente.add(lblFechaFinal);
        
        JLabel lblFechaFinal_1 = new JLabel(renta.getFecha_final());
        lblFechaFinal_1.setFont(new Font("Inter", Font.PLAIN, 10));
        lblFechaFinal_1.setBounds(87, 106, 99, 14);
        panelInfoCliente.add(lblFechaFinal_1);
        
        JLabel lblPago = new JLabel("Pago");
        lblPago.setFont(new Font("Inter", Font.BOLD, 13));
        lblPago.setBounds(210, 11, 57, 14);
        panelInfoCliente.add(lblPago);
        
        JLabel lblSeguroVida = new JLabel("Seguro de vida:");
        lblSeguroVida.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSeguroVida.setBounds(210, 48, 84, 14);
        panelInfoCliente.add(lblSeguroVida);
        
        JLabel lblSeguroAuto = new JLabel("Seguro de auto:");
        lblSeguroAuto.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSeguroAuto.setBounds(210, 68, 84, 14);
        panelInfoCliente.add(lblSeguroAuto);
        
        JLabel lblCombustible = new JLabel("Combustible:");
        lblCombustible.setFont(new Font("Inter", Font.PLAIN, 10));
        lblCombustible.setBounds(210, 87, 84, 14);
        panelInfoCliente.add(lblCombustible);
        
        JLabel lblTarifaPorDia = new JLabel("Tarifa por dia:");
        lblTarifaPorDia.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTarifaPorDia.setBounds(210, 106, 84, 14);
        panelInfoCliente.add(lblTarifaPorDia);
        
        JLabel lblSeguroDanios = new JLabel("Seguro Daños:");
        lblSeguroDanios.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSeguroDanios.setBounds(210, 30, 84, 14);
        panelInfoCliente.add(lblSeguroDanios);
        
        JLabel lblSDanios = new JLabel(""+vehiculo.getTarifa().getSeguro_danios());
        lblSDanios.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSDanios.setBounds(304, 30, 84, 14);
        panelInfoCliente.add(lblSDanios);
        
        JLabel lblTarifaPorDia_1_1 = new JLabel("$");
        lblTarifaPorDia_1_1.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTarifaPorDia_1_1.setBounds(293, 30, 12, 14);
        panelInfoCliente.add(lblTarifaPorDia_1_1);
        
        JLabel lblTarifaPorDia_1_1_1 = new JLabel("$");
        lblTarifaPorDia_1_1_1.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTarifaPorDia_1_1_1.setBounds(293, 48, 12, 14);
        panelInfoCliente.add(lblTarifaPorDia_1_1_1);
        
        JLabel lblTarifaPorDia_1_1_2 = new JLabel("$");
        lblTarifaPorDia_1_1_2.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTarifaPorDia_1_1_2.setBounds(293, 68, 12, 14);
        panelInfoCliente.add(lblTarifaPorDia_1_1_2);
        
        JLabel lblTarifaPorDia_1_1_3 = new JLabel("$");
        lblTarifaPorDia_1_1_3.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTarifaPorDia_1_1_3.setBounds(293, 106, 12, 14);
        panelInfoCliente.add(lblTarifaPorDia_1_1_3);
        
        JLabel lblTarifaPorDia_1_1_4 = new JLabel("$");
        lblTarifaPorDia_1_1_4.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTarifaPorDia_1_1_4.setBounds(293, 87, 12, 14);
        panelInfoCliente.add(lblTarifaPorDia_1_1_4);
        
        JLabel lblSVida = new JLabel(""+vehiculo.getTarifa().getSeguro_vida());
        lblSVida.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSVida.setBounds(304, 48, 84, 14);
        panelInfoCliente.add(lblSVida);
        
        JLabel lblSKilometraje = new JLabel(""+vehiculo.getTarifa().getSeguro_kilometraje());
        lblSKilometraje.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSKilometraje.setBounds(304, 68, 84, 14);
        panelInfoCliente.add(lblSKilometraje);
        
        JLabel lblSCombustible = new JLabel(""+vehiculo.getTarifa().getSeguro_combustible());
        lblSCombustible.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSCombustible.setBounds(304, 87, 84, 14);
        panelInfoCliente.add(lblSCombustible);
        
        JLabel lblTDia = new JLabel(""+vehiculo.getTarifa().getSeguro_tarifa_por_dia());
        lblTDia.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTDia.setBounds(304, 106, 84, 14);
        panelInfoCliente.add(lblTDia);
        
        btnbrdEliminar = new BtnBordeado(30, false, true, new Color(250, 0, 0));
        btnbrdEliminar.setText("Eliminar");
        btnbrdEliminar.setActionCommand("EliminarRenta");
        btnbrdEliminar.setForeground(new Color(250, 0, 0));
        btnbrdEliminar.setFont(new Font("Inter", Font.PLAIN, 14));
        btnbrdEliminar.setBackground(Color.WHITE);
        btnbrdEliminar.setBounds(638, 105, 115, 25);
        panelPrincipalCartas.add(btnbrdEliminar);
        
        btnbrdEditar = new BtnBordeado(30, false, true, new Color(33, 147, 246));
        btnbrdEditar.setText("Editar");
        btnbrdEditar.setActionCommand("EditarRenta");
        btnbrdEditar.setForeground(new Color(33, 147, 246));
        btnbrdEditar.setFont(new Font("Inter", Font.PLAIN, 14));
        btnbrdEditar.setBackground(Color.WHITE);
        btnbrdEditar.setBounds(638, 70, 115, 25);
        panelPrincipalCartas.add(btnbrdEditar);
        
        btnbrdEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});      
        
        
        btnbrdEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prepararEdicion(renta);
			}
		});
        
	}
	
	public void prepararEliminar(Rentas renta) {
		controlador.prepararEliminacionRenta(renta);
	}
	
	public void prepararEdicion(Rentas renta) {
		controlador.prepararRentaEdicion(renta);
	}

	public Rentas getRenta() {
		return renta;
	}
	
	public void setRenta(Rentas renta) {
		this.renta = renta;
	}
	public BtnBordeado getBtnbrdEditar() {
		return btnbrdEditar;
	}
	public void setBtnbrdEditar(BtnBordeado btnbrdEditar) {
		this.btnbrdEditar = btnbrdEditar;
	}
	public BtnBordeado getBtnbrdEliminar() {
		return btnbrdEliminar;
	}
	public void setBtnbrdEliminar(BtnBordeado btnbrdEliminar) {
		this.btnbrdEliminar = btnbrdEliminar;
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}
