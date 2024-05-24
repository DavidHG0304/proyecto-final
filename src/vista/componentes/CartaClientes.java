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

public class CartaClientes extends PanelRedondeado {
	private BtnBordeado btnbrdDetalles;
	private BtnBordeado btnbrdEliminar;
	private BtnBordeado btnbrdEditar;
	
	
	public CartaClientes() {
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
        
		JLabel lblImgCliente = new JLabel();
		lblImgCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgCliente.setBounds(5, 0, 182, 140);
		panelImgCarro.add(lblImgCliente);
		ImageIcon cargandoCarro = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/persona2.png"));
		Image imagen = cargandoCarro.getImage();
		Image imagenReescalada = imagen.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon iconoReescalado = new ImageIcon(imagenReescalada);
		lblImgCliente.setIcon(iconoReescalado);
		panelImgCarro.revalidate();
		panelImgCarro.repaint();
        
        
        JLabel lblNombre = new JLabel("@Usuario123123");
        lblNombre.setFont(new Font("Inter", Font.BOLD, 10));
        lblNombre.setBounds(10, 11, 99, 14);
        panelImgCarro.add(lblNombre);
        
        JPanel panelInfoCliente = new JPanel();
        panelInfoCliente.setBackground(new Color(255, 255, 255));
        panelInfoCliente.setBounds(222, 3, 261, 136);
        panelInfoCliente.setLayout(null);
        panelPrincipalCartas.add(panelInfoCliente);
        
        JLabel lblCorreoElectronicio = new JLabel("Correo Electronico");
        lblCorreoElectronicio.setFont(new Font("Inter", Font.BOLD, 13));
        lblCorreoElectronicio.setBounds(10, 23, 169, 14);
        panelInfoCliente.add(lblCorreoElectronicio);
        
        JLabel lblNombreC = new JLabel("Nombre del cliente");
        lblNombreC.setFont(new Font("Inter", Font.BOLD, 10));
        lblNombreC.setBounds(10, 44, 99, 14);
        panelInfoCliente.add(lblNombreC);
        
        btnbrdEliminar = new BtnBordeado(30, false, true, new Color(250, 0, 0));
        btnbrdEliminar.setText("Eliminar");
        btnbrdEliminar.setActionCommand("EliminarCliente");
        btnbrdEliminar.setForeground(new Color(250, 0, 0));
        btnbrdEliminar.setFont(new Font("Inter", Font.PLAIN, 14));
        btnbrdEliminar.setBackground(Color.WHITE);
        btnbrdEliminar.setBounds(581, 114, 172, 25);
        panelPrincipalCartas.add(btnbrdEliminar);
        
        btnbrdDetalles = new BtnBordeado(30, false, true, new Color(0,0,0,70));
        btnbrdDetalles.setText("Detalles del cliente");
        btnbrdDetalles.setActionCommand("DetallesCliente");
        btnbrdDetalles.setForeground(new Color(0, 0, 0));
        btnbrdDetalles.setFont(new Font("Inter", Font.PLAIN, 14));
        btnbrdDetalles.setBackground(Color.WHITE);
        btnbrdDetalles.setBounds(581, 61, 172, 25);
        panelPrincipalCartas.add(btnbrdDetalles);
        
        btnbrdEditar = new BtnBordeado(30, false, true, new Color(33, 147, 246));
        btnbrdEditar.setText("Editar perfil");
        btnbrdEditar.setForeground(new Color(33, 147, 246));
        btnbrdEditar.setFont(new Font("Inter", Font.PLAIN, 14));
        btnbrdEditar.setBackground(Color.WHITE);
        btnbrdEditar.setActionCommand("EditarCliente");
        btnbrdEditar.setBounds(581, 11, 172, 25);
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

	public BtnBordeado getBtnbrdDetalles() {
		return btnbrdDetalles;
	}

	public void setBtnbrdDetalles(BtnBordeado btnbrdDetalles) {
		this.btnbrdDetalles = btnbrdDetalles;
	}
	
	
	
}
