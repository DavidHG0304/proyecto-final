package vista;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import modelo.entidades.Rentas;
import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;
import vista.componentes.CartaClientes;
import vista.componentes.CartasCarros;
import vista.componentes.CartasRentas;
import vista.componentes.PanelesNavegacion;
import vista.recursos.componentesPersonalizados.PanelRedondeado;
import vista.recursos.componentesPersonalizados.ScrollBarPersonalizado;


public class VistaPanelRentas {
	private JFrame frame = new JFrame();
	private PanelesNavegacion panel;
	private JPanel panelCartasRentas;
	private JPanel panelAux;
	private ArrayList<CartasRentas> cartaRentas;
	
	
	public VistaPanelRentas(){
		frame = new JFrame();
		frame.setSize(950, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.gray);
		
		rentas();
	}
	
	public void rentas() {
		panel = new PanelesNavegacion();
		
		frame.getContentPane().add(panel);
		panel.getBtnRentas().setForeground(new Color(33,147,246));
		panel.getBtnRentas().setBackground(new Color(255, 255, 255));
		panel.getBtnRentas().setFocusPainted(false);
		panel.getBtnRentas().setFocusPainted(false);
		panel.getBtnRentas().setBorderPainted(false);
		panel.getBtnRentas().setContentAreaFilled(false);
		panel.getBtnRentas().setFocusPainted(false);
		panel.getLblTitulo().setText("Rentas");
		panel.getBtnAgregar().setText("Crear renta");
		panel.getBtnInicio().setActionCommand("Inicio pRentas");
		panel.getBtnMarcas().setActionCommand("Marcas pRentas");
		panel.getBtnCategorias().setActionCommand("Categorias pRentas");
		panel.getBtnVehiculos().setActionCommand("Vehiculos pRentas");
		panel.getBtnClientes().setActionCommand("Clientes pRentas");
		panel.getBtnCerrarSesion().setActionCommand("Cerrar Sesión pRentas");
		panel.getBtnAgregar().setActionCommand("Agregar Renta pRentas");
		
		
		
		// Añadir los complementos de manera que se pongan en vertical con el boxlayout y
		// usando el Box.createVerticalStrut en paneles auxiliares para poder darle separación a cada uno de los paneles que se crean y que no se
		// vea tan abarrotado todo
		panelCartasRentas = new JPanel();
		panelCartasRentas = new JPanel();
		panelCartasRentas.setBounds(10, 170, 894, 360);
		panelCartasRentas.setBackground(Color.WHITE);
		panelCartasRentas.add(Box.createRigidArea(new Dimension(15, 0)));
		panelCartasRentas.setLayout(new BoxLayout(panelCartasRentas, BoxLayout.Y_AXIS));
		
		panel.getPanelCentral().add(panelCartasRentas);
		panelAux = new JPanel();
		panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));
		panelAux.setBackground(Color.WHITE);
		
//		int tamanio = 25;
//		for (int i = 0; i < tamanio; i++) {
//			CartasRentas cartasRentas = new CartasRentas();
//			panelAux.add(cartasRentas);
//			panelAux.add(Box.createVerticalStrut(10));
//			panelCartasRentas.add(panelAux);
//		}

		JScrollPane scrollPane = new JScrollPane(panelCartasRentas);
		scrollPane.setBounds(10, 220, 894, 360);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(894, 360));
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarPersonalizado());
        scrollPane.getHorizontalScrollBar().setUI(new ScrollBarPersonalizado());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.getPanelCentral().add(scrollPane);
		
		// QUItar gif
//		panel.getPanelCentral().remove(panel.getLblCargandoGif());
//		panel.getPanelCentral().repaint();
		
		cartaRentas = new ArrayList<>();
		
	}
	
	public void mostrarRentas(ArrayList<Rentas> rentas) {
		cartaRentas.clear();
		panelCartasRentas.removeAll();
		panelAux.removeAll();
		panel.getPanelCentral().remove(panel.getLblCargandoGif());
		
		for(Rentas renta : rentas) {
			Usuarios usuario = renta.getUsuario();
			Vehiculos vehiculo = renta.getVehiculo();
			CartasRentas cartasRentas = new CartasRentas(renta, usuario, vehiculo);
			panelAux.add(cartasRentas);
			panelAux.add(Box.createVerticalStrut(10));
			panelCartasRentas.add(panelAux);
		}
		
		
		
		panelAux.revalidate();
		panelAux.repaint();
		panelCartasRentas.revalidate();
		panelCartasRentas.repaint();
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
        for (Component comp : panelCartasRentas.getComponents()) {
            if (comp instanceof JPanel) {
                for (Component innerComp : ((JPanel) comp).getComponents()) {
                    if (innerComp instanceof CartasRentas) {
                        CartasRentas carta = (CartasRentas) innerComp;
                        carta.getBtnbrdEditar().addActionListener(listener);
                        carta.getBtnbrdEliminar().addActionListener(listener);
                    }
                }
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
	