package vista.componentes;

import java.awt.Color;
import java.awt.Dimension;

import vista.recursos.componentesPersonalizados.PanelRedondeado;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.recursos.componentesPersonalizados.BtnBordeado;

public class CartasRentas extends PanelRedondeado {
	private BtnBordeado btnbrdEditar;
	private BtnBordeado btnbrdEliminar;
	
	
	public CartasRentas() {
		super(30, false, true, new Color(0, 0, 0, 10), 5);
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(780, 150));
        setMaximumSize(new Dimension(800, 150));
//      setLayout(null);
        
        cartas();
	}
	
	
	public void cartas() {
		
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
		lblImgCarro.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgCarro.setBounds(5, 0, 182, 140);
		panelImgCarro.add(lblImgCarro);
		ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/carroPrueba.png"));
		Image imagen = cargandoCarro.getImage();
		Image imagenReescalada = imagen.getScaledInstance(187, 140, Image.SCALE_SMOOTH);
		ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
		lblImgCarro.setIcon(iconoReescalado);
		panelImgCarro.revalidate();
		panelImgCarro.repaint();
        
        
        JLabel lblNombre = new JLabel("<dynamic> <dynamic> - <dynamic>");
        lblNombre.setFont(new Font("Inter", Font.BOLD, 10));
        lblNombre.setBounds(5, 115, 179, 14);
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
        
        JLabel lblNombreC_1 = new JLabel("Nombre del cliente");
        lblNombreC_1.setFont(new Font("Inter", Font.PLAIN, 10));
        lblNombreC_1.setBounds(10, 48, 99, 14);
        panelInfoCliente.add(lblNombreC_1);
        
        JLabel lblFechaInicio = new JLabel("Fecha inicio");
        lblFechaInicio.setFont(new Font("Inter", Font.BOLD, 10));
        lblFechaInicio.setBounds(10, 91, 99, 14);
        panelInfoCliente.add(lblFechaInicio);
        
        JLabel lblFechaInicio_1 = new JLabel("12/12/2000");
        lblFechaInicio_1.setFont(new Font("Inter", Font.PLAIN, 10));
        lblFechaInicio_1.setBounds(10, 106, 99, 14);
        panelInfoCliente.add(lblFechaInicio_1);
        
        JLabel lblFechaFinal = new JLabel("Fecha inicio");
        lblFechaFinal.setFont(new Font("Inter", Font.BOLD, 10));
        lblFechaFinal.setBounds(119, 91, 99, 14);
        panelInfoCliente.add(lblFechaFinal);
        
        JLabel lblFechaFinal_1 = new JLabel("12/12/2000");
        lblFechaFinal_1.setFont(new Font("Inter", Font.PLAIN, 10));
        lblFechaFinal_1.setBounds(119, 106, 99, 14);
        panelInfoCliente.add(lblFechaFinal_1);
        
        JLabel lblPago = new JLabel("Pago");
        lblPago.setFont(new Font("Inter", Font.BOLD, 13));
        lblPago.setBounds(217, 11, 57, 14);
        panelInfoCliente.add(lblPago);
        
        JLabel lblSeguroVida = new JLabel("Seguro de vida");
        lblSeguroVida.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSeguroVida.setBounds(217, 32, 99, 14);
        panelInfoCliente.add(lblSeguroVida);
        
        JLabel lblSeguroAuto = new JLabel("Seguro de auto");
        lblSeguroAuto.setFont(new Font("Inter", Font.PLAIN, 10));
        lblSeguroAuto.setBounds(217, 57, 99, 14);
        panelInfoCliente.add(lblSeguroAuto);
        
        JLabel lblCombustible = new JLabel("Combustible");
        lblCombustible.setFont(new Font("Inter", Font.PLAIN, 10));
        lblCombustible.setBounds(217, 81, 99, 14);
        panelInfoCliente.add(lblCombustible);
        
        JLabel lblTarifaPorDia = new JLabel("Tarifa por dia");
        lblTarifaPorDia.setFont(new Font("Inter", Font.PLAIN, 10));
        lblTarifaPorDia.setBounds(217, 106, 99, 14);
        panelInfoCliente.add(lblTarifaPorDia);
        
        btnbrdEliminar = new BtnBordeado(30, false, true, new Color(250, 0, 0));
        btnbrdEliminar.setText("Eliminar");
        btnbrdEliminar.setActionCommand("EliminarRenta");
        btnbrdEliminar.setForeground(new Color(250, 0, 0));
        btnbrdEliminar.setFont(new Font("Inter", Font.PLAIN, 14));
        btnbrdEliminar.setBackground(Color.WHITE);
        btnbrdEliminar.setBounds(638, 114, 115, 25);
        panelPrincipalCartas.add(btnbrdEliminar);
        
        btnbrdEditar = new BtnBordeado(30, false, true, new Color(33, 147, 246));
        btnbrdEditar.setText("Editar");
        btnbrdEditar.setActionCommand("EditarRenta");
        btnbrdEditar.setForeground(new Color(33, 147, 246));
        btnbrdEditar.setFont(new Font("Inter", Font.PLAIN, 14));
        btnbrdEditar.setBackground(Color.WHITE);
        btnbrdEditar.setBounds(638, 79, 115, 25);
        panelPrincipalCartas.add(btnbrdEditar);
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
	
	
	
	
	
	
}
