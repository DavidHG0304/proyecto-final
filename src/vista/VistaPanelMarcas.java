package vista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import modelo.entidades.Vehiculos;
import vista.componentes.CartasCarros;
import vista.componentes.PanelesNavegacion;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import vista.recursos.componentesPersonalizados.ScrollBarPersonalizado;


public class VistaPanelMarcas {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	private BtnBordeado botonEliminarM;
	private BtnBordeado botonEditarM;
	private JPanel panelCartasVehiculos;
	
	public VistaPanelMarcas(){
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		marcas();
	}
	
	public void marcas() {
		panel = new PanelesNavegacion();
		
		frame.add(panel);
		panel.getBtnMarcas().setForeground(new Color(33,147,246));
		panel.getBtnMarcas().setBackground(new Color(255, 255, 255));
		panel.getBtnMarcas().setFocusPainted(false);
		panel.getBtnMarcas().setFocusPainted(false);
		panel.getBtnMarcas().setBorderPainted(false);
		panel.getBtnMarcas().setContentAreaFilled(false);
		panel.getBtnMarcas().setFocusPainted(false);
		
		panel.getLblTitulo().setText("Marcas");
		
		panel.getBtnAgregar().setText("Agregar marca");
		
		panel.getBtnInicio().setActionCommand("Inicio pMarcas");
		panel.getBtnCategorias().setActionCommand("Categorias pMarcas");
		panel.getBtnRentas().setActionCommand("Rentas pMarcas");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pMarcas");
		panel.getBtnClientes().setActionCommand("Clientes pMarcas");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pMarcas");
		panel.getBtnAgregar().setActionCommand("Agregar Marca pMarcas");
		
		ImageIcon iconoEditarM = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/editar.png"));
		JLabel imagenEditar = new JLabel(iconoEditarM);
		imagenEditar.setBounds(707, 154, 14, 14);
		panel.getPanelCentral().add(imagenEditar);
		
		botonEditarM = new BtnBordeado(20, false, true, new Color(33,147,246));
		botonEditarM.setText("Editar Marca        ");
		botonEditarM.setActionCommand("EditarMarca");
		botonEditarM.setForeground(new Color(33,147,246));
		botonEditarM.setBounds(600, 150, 130, 23);
		panel.getPanelCentral().add(botonEditarM);
		
		ImageIcon iconoBorrarM = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eliminar.png"));
		JLabel imagenEliminar = new JLabel(iconoBorrarM);
		imagenEliminar.setBounds(565, 153, 14, 14);
		panel.getPanelCentral().add(imagenEliminar);
		botonEliminarM = new BtnBordeado(20, false, true, new Color(250, 0, 0, 67));
		botonEliminarM.setActionCommand("EliminarMarca");
		botonEliminarM.setText("Eliminar Marca        ");
		botonEliminarM.setForeground(new Color(240,0,0));
		botonEliminarM.setBounds(455, 150, 130, 23);
		panel.getPanelCentral().add(botonEliminarM);
		
		panelCartasVehiculos = new JPanel();
		panelCartasVehiculos.setBounds(10, 170, 894, 360);
		panelCartasVehiculos.setBackground(Color.WHITE);
		panelCartasVehiculos.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		panel.getPanelCentral().add(panelCartasVehiculos);
		
		int tamanio = 25;
		Vehiculos vehiculos = new Vehiculos();
		for(int i = 0; i < tamanio; i++) {
			 CartasCarros carta = new CartasCarros(vehiculos);
			 panelCartasVehiculos.add(carta);
		}

		JScrollPane scrollPane= new JScrollPane(panelCartasVehiculos);
		scrollPane.setBounds(10, 220, 894, 410);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(894, 360));
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarPersonalizado());
        scrollPane.getHorizontalScrollBar().setUI(new ScrollBarPersonalizado());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.getPanelCentral().add(scrollPane);
		
		// QUItar gif
		panel.getPanelCentral().remove(panel.getLblCargandoGif());
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
		botonEliminarM.addActionListener(listener);
		botonEditarM.addActionListener(listener);
	}

	public void asignarListenersCartas(ActionListener listener) {
        for (Component comp : panelCartasVehiculos.getComponents()) {
            if (comp instanceof CartasCarros) {
                CartasCarros carta = (CartasCarros) comp;
                carta.getLblBorrarIcono().addActionListener(listener);
                carta.getBntInfoIcono().addActionListener(listener);
                carta.getLbleditarIcono().addActionListener(listener);
                carta.getBtnRentar().addActionListener(listener);
                carta.getBtnDetalles().addActionListener(listener);
            }
        }
    }
	
	
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
	