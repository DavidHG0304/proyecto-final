package vista;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controlador.ControladorClientes;
import controlador.ControladorVehiculos;
import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;
import vista.componentes.CartaClientes;
import vista.componentes.CartasCarros;
import vista.componentes.PanelesNavegacion;
import vista.recursos.componentesPersonalizados.*;

public class VistaPanelVehiculos {
	JFrame frame = new JFrame();
//	private JPanel panelPrincipal;
	private JPanel panelCartasVehiculos;
	private ArrayList<CartasCarros> cartaCarros;
	private ControladorVehiculos controlador;
	
	private PanelesNavegacion panel;
	
	public VistaPanelVehiculos(ControladorVehiculos controlador) {
		this.controlador = controlador;
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		panelVehiculos();
	}
	
	public void panelVehiculos() {
		panel = new PanelesNavegacion();
		frame.add(panel);
		
		panel.getBtnVehiculos().setForeground(new Color(33,147,246));
		panel.getBtnVehiculos().setBounds(492, 25, 114, 23);
		panel.getBtnVehiculos().setContentAreaFilled(true);
		panel.getBtnVehiculos().setContentAreaFilled(false);
		panel.getLblTitulo().setText("Vehiculos");
		
		panel.getBtnAgregar().setText("Agregar vehiculo");
		
		panel.getBtnRentas().setActionCommand("Rentas pVehiculos");
		panel.getBtnCategorias().setActionCommand("Categorias pVehiculos");
		panel.getBtnMarcas().setActionCommand("Marcas pVehiculos");
		panel.getBtnInicio().setActionCommand("Inicio pVehiculos");
		panel.getBtnClientes().setActionCommand("Clientes pVehiculos");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pVehiculos");
		panel.getBtnAgregar().setActionCommand("AgregarVehiculo");
		
		panelCartasVehiculos = new JPanel();
		panelCartasVehiculos.setBounds(10, 170, 894, 360);
		panelCartasVehiculos.setBackground(Color.WHITE);
		panelCartasVehiculos.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		panel.getPanelCentral().add(panelCartasVehiculos);
        
		JScrollPane scrollPane= new JScrollPane(panelCartasVehiculos);
		scrollPane.setBounds(10, 220, 894, 410);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(894, 360));
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarPersonalizado());
        scrollPane.getHorizontalScrollBar().setUI(new ScrollBarPersonalizado());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.getPanelCentral().add(scrollPane);
		
		cartaCarros= new ArrayList<>();
	}
	
	public void mostrarVehiculos(ArrayList<Vehiculos> vehiculos) {
		cartaCarros.clear();
        panelCartasVehiculos.removeAll();
        panel.getPanelCentral().remove(panel.getLblCargandoGif());
        for (Vehiculos vehiculo : vehiculos) {
            CartasCarros carta = new CartasCarros(vehiculo);
            carta.getLblBorrarIcono().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 controlador.prepararEliminar(vehiculo);
				}
			});
            
            panelCartasVehiculos.add(carta);
            cartaCarros.add(carta);
        }
        
        panelCartasVehiculos.revalidate();
        panelCartasVehiculos.repaint();
        panel.getPanelCentral().repaint();
    }

	public void asignarActListner(ActionListener listener) {
		panel.getBtnCerrarSesion().addActionListener(listener);
		panel.getBtnInicio().addActionListener(listener);
		panel.getBtnClientes().addActionListener(listener);
		panel.getBtnVehiculos().addActionListener(listener);
		panel.getBtnRentas().addActionListener(listener);
		panel.getBtnCategorias().addActionListener(listener);
		panel.getBtnMarcas().addActionListener(listener);
		panel.getBtnAgregar().addActionListener(listener);
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
                
                carta.getBntInfoIcono().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (CartasCarros c : cartaCarros) {
                            c.setSeleccionado(false);
                        }
                        carta.setSeleccionado(true);
                    }
                });
                
                carta.getLbleditarIcono().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (CartasCarros c : cartaCarros) {
                            c.setSeleccionado(false);
                        }
                        carta.setSeleccionado(true);
                    }
                });
                
            }
        }
    }
	
	
	public Vehiculos getVehiculoSeleccionado() {
		for (CartasCarros carta : cartaCarros) {
	        if (carta.isSeleccionado()) {
	            return carta.getVehiculo();
	        }
	    }

		return null;
	}
	
	
	
	public ControladorVehiculos getControlador() {
		return controlador;
	}

	public void setControlador(ControladorVehiculos controlador) {
		this.controlador = controlador;
	}

	// Getters
	public JFrame getFrame() {
		return frame;
	}
	
	
	// Setters
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	
}
