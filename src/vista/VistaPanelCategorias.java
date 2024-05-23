package vista;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import modelo.entidades.Vehiculos;
import vista.componentes.CartasCarros;
import vista.componentes.PanelesNavegacion;
import vista.recursos.componentesPersonalizados.BtnBordeado;
import vista.recursos.componentesPersonalizados.ScrollBarPersonalizado;


public class VistaPanelCategorias {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	private BtnBordeado botonEliminarC;
	private BtnBordeado botonEditarC;
	private JPanel panelCartasVehiculos;
	
	public VistaPanelCategorias(){
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		categorias();
	}
	
	public void categorias() {
		panel = new PanelesNavegacion();
		
		frame.getContentPane().add(panel);
		panel.getBtnCategorias().setForeground(new Color(33,147,246));
		panel.getBtnCategorias().setBackground(new Color(255, 255, 255));
		panel.getBtnCategorias().setFocusPainted(false);
		panel.getBtnCategorias().setFocusPainted(false);
		panel.getBtnCategorias().setBorderPainted(false);
		panel.getBtnCategorias().setContentAreaFilled(false);
		panel.getBtnCategorias().setFocusPainted(false);
		panel.getLblTitulo().setText("Categorias");
		
		panel.getBtnAgregar().setText("Agregar categoria");
		panel.getBtnAgregar().setBounds(740, 150, 155, 23);
		
		panel.getBtnInicio().setActionCommand("Inicio pCategorias");
		panel.getBtnMarcas().setActionCommand("Marcas pCategorias");
		panel.getBtnRentas().setActionCommand("Rentas pCategorias");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pCategorias");
		panel.getBtnClientes().setActionCommand("Clientes pCategorias");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pCategorias");
		panel.getBtnAgregar().setActionCommand("Agregar Categoria pCategorias");
		
		ImageIcon iconoEditarC = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/editar.png"));
		JLabel imagenEditar = new JLabel(iconoEditarC);
		imagenEditar.setBounds(707, 154, 14, 14);
		panel.getPanelCentral().add(imagenEditar);
		
		botonEditarC = new BtnBordeado(20, false, true, new Color(33,147,246));
		botonEditarC.setText("Editar Categoria    ");
		botonEditarC.setActionCommand("EditarCategoria");
		botonEditarC.setForeground(new Color(33,147,246));
		botonEditarC.setBounds(600, 150, 130, 23);
		panel.getPanelCentral().add(botonEditarC);
		
		ImageIcon iconoBorrarC = new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eliminar.png"));
		JLabel imagenEliminar = new JLabel(iconoBorrarC);
		imagenEliminar.setBounds(565, 153, 14, 14);
		panel.getPanelCentral().add(imagenEliminar);
		botonEliminarC = new BtnBordeado(20, false, true, new Color(250, 0, 0, 67));
		botonEliminarC.setActionCommand("EliminarCategoria");
		botonEliminarC.setText("Eliminar Categoria    ");
		botonEliminarC.setForeground(new Color(240,0,0));
		botonEliminarC.setBounds(455, 150, 130, 23);
		panel.getPanelCentral().add(botonEliminarC);
		
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
		botonEliminarC.addActionListener(listener);
		botonEditarC.addActionListener(listener);
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
	