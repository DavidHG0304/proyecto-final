package vista;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controlador.ControladorClientes;
import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;
import vista.componentes.CartaClientes;
import vista.componentes.CartasCarros;
import vista.componentes.CartasRentas;
import vista.componentes.DialogoCrearCliente;
import vista.componentes.PanelesNavegacion;
import vista.recursos.componentesPersonalizados.ScrollBarPersonalizado;

public class VistaPanelClientes {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	private JPanel panelCartasClientes;
	private JPanel panelAux;
	private ArrayList<CartaClientes> cartaClientes;
	private ControladorClientes controlador;
	private DialogoCrearCliente dialogoComplementos;
	
	public VistaPanelClientes(ControladorClientes controlador){
		this.controlador = controlador;
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		clientes();
	}
	
	public void clientes() {
		panel = new PanelesNavegacion();
		
		frame.add(panel);
		panel.getBtnClientes().setForeground(new Color(33,147,246));
		panel.getBtnClientes().setBackground(new Color(255, 255, 255));
		panel.getBtnClientes().setFocusPainted(false);
		panel.getBtnClientes().setFocusPainted(false);
		panel.getBtnClientes().setBorderPainted(false);
		panel.getBtnClientes().setContentAreaFilled(false);
		panel.getBtnClientes().setFocusPainted(false);
		
		panel.getBtnVehiculos().setForeground(new Color(0,0,0));
		panel.getBtnVehiculos().setContentAreaFilled(true);
		panel.getLblTitulo().setText("Clientes");
		
		panel.getBtnAgregar().setText("Agregar cliente");
		
		panel.getBtnInicio().setActionCommand("Inicio pClientes");
		panel.getBtnMarcas().setActionCommand("Marcas pClientes");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pClientes");
		panel.getBtnCategorias().setActionCommand("Categorias pClientes");
		panel.getBtnRentas().setActionCommand("Rentas pClientes");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pClientes");
		panel.getBtnAgregar().setActionCommand("Agregar Cliente pClientes");
		
		panelCartasClientes = new JPanel();
		panelCartasClientes = new JPanel();
		panelCartasClientes.setBounds(10, 170, 894, 360);
		panelCartasClientes.setBackground(Color.WHITE);
		panelCartasClientes.add(Box.createRigidArea(new Dimension(15, 0)));
		panelCartasClientes.setLayout(new BoxLayout(panelCartasClientes, BoxLayout.Y_AXIS));
		
		panel.getPanelCentral().add(panelCartasClientes);
		panelAux = new JPanel();
		panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));
		panelAux.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(panelCartasClientes);
		scrollPane.setBounds(10, 220, 894, 360);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(894, 360));
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarPersonalizado());
        scrollPane.getHorizontalScrollBar().setUI(new ScrollBarPersonalizado());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.getPanelCentral().add(scrollPane);
		
		
		cartaClientes = new ArrayList<>();
	}

	public void mostrarClientes(ArrayList<Usuarios> usuarios) {
        cartaClientes.clear();
        panelCartasClientes.removeAll();
        panelAux.removeAll();
        panel.getPanelCentral().remove(panel.getLblCargandoGif());

        for (Usuarios usuario : usuarios) {
            CartaClientes carta = new CartaClientes(usuario, controlador);
            carta.getBtnbrdEliminar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     controlador.prepararEliminacionCliente(usuario);
                }
            });
            panelAux.add(carta);
            panelAux.add(Box.createVerticalStrut(10));
            panelCartasClientes.add(panelAux);
            cartaClientes.add(carta);
        }

        panelAux.revalidate();
        panelAux.repaint();
        panelCartasClientes.revalidate();
        panelCartasClientes.repaint();
        panel.getPanelCentral().repaint();
    }
	
	public void asignarActListner(ActionListener listener) {
		panel.getBtnInicio().addActionListener(listener);
		panel.getBtnClientes().addActionListener(listener);
		panel.getBtnMarcas().addActionListener(listener);
		panel.getBtnVehiculos().addActionListener(listener);
		panel.getBtnRentas().addActionListener(listener);
		panel.getBtnCategorias().addActionListener(listener);
		panel.getBtnCerrarSesion().addActionListener(listener);
		panel.getBtnAgregar().addActionListener(listener);
	}
	
	public void asignarListenersCartas(ActionListener listener) {
        for (Component comp : panelCartasClientes.getComponents()) {
            if (comp instanceof JPanel) {
                for (Component innerComp : ((JPanel) comp).getComponents()) {
                    if (innerComp instanceof CartaClientes) {
                        CartaClientes carta = (CartaClientes) innerComp;
                        carta.getBtnbrdEditar().addActionListener(listener);
                        carta.getBtnbrdDetalles().addActionListener(listener);
                        carta.getBtnbrdEliminar().addActionListener(listener);
                    }
                }
            }
        }
    }
	
	public Usuarios getUsuarioSeleccionado() {
		for (CartaClientes carta : cartaClientes) {
	        if (carta.isSeleccionado()) {
	            Usuarios usuario = carta.getUsuario();
	            return carta.getUsuario();
	        }
	    }

		return null;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public ControladorClientes getControlador() {
		return controlador;
	}

	public void setControlador(ControladorClientes controlador) {
		this.controlador = controlador;
	}
	
	
}
