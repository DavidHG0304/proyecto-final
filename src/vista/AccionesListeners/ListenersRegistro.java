package vista.AccionesListeners;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.ImageIcon;

import vista.VistaRegistro;

public class ListenersRegistro implements ActionListener, FocusListener, MouseListener{

	public VistaRegistro nuevaVista;
	
	public ListenersRegistro(VistaRegistro vista) {
		this.nuevaVista = vista;
		asignarListeners();
	}
	
	public void asignarListeners() {
		nuevaVista.asignarFocusListener(this);
		nuevaVista.asignarMouseClicked(this);
		nuevaVista.asignarActListnerCheckBox(this);
	}
	
	// Metodos FocusListener
		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource().equals(nuevaVista.getNombre())) {
				if(nuevaVista.getNombre().getText().equals("Nombre")) {
					nuevaVista.getNombre().setText("");
					nuevaVista.getNombre().setForeground(Color.BLACK);
				}
				
			}else if(e.getSource().equals(nuevaVista.getApellidos())) {
				if(nuevaVista.getApellidos().getText().equals("Apellidos")) {
					nuevaVista.getApellidos().setText("");
					nuevaVista.getApellidos().setForeground(Color.BLACK);
				}
			}
			else if (e.getSource().equals(nuevaVista.getTxtCorreo())) {
				if(nuevaVista.getTxtCorreo().getText().equals("Correo electronico")) {
					nuevaVista.getTxtCorreo().setText("");
					nuevaVista.getTxtCorreo().setForeground(Color.BLACK);
				}

			}else if (e.getSource().equals(nuevaVista.getTxtContrasenia())) {
				nuevaVista.getPassCheckBox().setSelected(false);
				nuevaVista.getTxtContrasenia().setEchoChar('*');
				
				String password = String.valueOf(nuevaVista.getTxtContrasenia().getPassword());
				nuevaVista.getPassCheckBox().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));

				if (password.toLowerCase().equals("contraseña")) {
					nuevaVista.getTxtContrasenia().setText("");
					nuevaVista.getTxtContrasenia().setForeground(Color.black);
				}
			}else if (e.getSource().equals(nuevaVista.getConfirmarContrasenia())) {
				nuevaVista.getPassCheckBox2().setSelected(false);
				nuevaVista.getConfirmarContrasenia().setEchoChar('*');
				
				String password = String.valueOf(nuevaVista.getConfirmarContrasenia().getPassword());
				nuevaVista.getPassCheckBox2().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));

				if (password.toLowerCase().equals("confirmar contraseña")) {
					nuevaVista.getConfirmarContrasenia().setText("");
					nuevaVista.getConfirmarContrasenia().setForeground(Color.black);
				}
			}
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			if(nuevaVista.getNombre().getText().equals("")) {
				nuevaVista.getNombre().setText("Nombre");
				nuevaVista.getNombre().setForeground(new Color(0,0,0,90));
			}
			
			if(nuevaVista.getApellidos().getText().equals("")) {
				nuevaVista.getApellidos().setText("Apellidos");
				nuevaVista.getApellidos().setForeground(new Color(0,0,0,90));
			}
			
			if(nuevaVista.getTxtCorreo().getText().equals("")) {
				nuevaVista.getTxtCorreo().setText("Correo electronico");
				nuevaVista.getTxtCorreo().setForeground(new Color(0,0,0,90));
			}
			
			String contraseñaAux = String.valueOf(nuevaVista.getTxtContrasenia().getPassword());
		    if(contraseñaAux.toLowerCase().equals("contraseña") || contraseñaAux.toLowerCase().equals("")) {
		    	nuevaVista.getTxtContrasenia().setText("Contraseña");
		    	nuevaVista.getTxtContrasenia().setEchoChar((char)0);
		    	nuevaVista.getTxtContrasenia().setForeground(new Color(0,0,0,90));
		    }
		    String confirmarContraseñaAux = String.valueOf(nuevaVista.getConfirmarContrasenia().getPassword());
		    if(confirmarContraseñaAux.toLowerCase().equals("confirmar contraseña") || confirmarContraseñaAux.toLowerCase().equals("")) {
		    	nuevaVista.getConfirmarContrasenia().setText("Confirmar contraseña");
		    	nuevaVista.getConfirmarContrasenia().setEchoChar((char)0);
		    	nuevaVista.getConfirmarContrasenia().setForeground(new Color(0,0,0,90));
		    }
		}
		
		// Metodos ActionListener
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "CheckBox1":
				// System.out.println("Prueba");
				String contraseñaAux = String.valueOf(nuevaVista.getTxtContrasenia().getPassword());
				if (!contraseñaAux.equals("Contraseña")) {
					if (nuevaVista.getPassCheckBox().isSelected()) {
						nuevaVista.getTxtContrasenia().setEchoChar((char) 0);
						nuevaVista.getPassCheckBox().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye.png")));
					} else {
						nuevaVista.getTxtContrasenia().setEchoChar('*');
						nuevaVista.getPassCheckBox().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));
					}
				}
			case "CheckBox2":
				String confirmarContraseñaAux = String.valueOf(nuevaVista.getConfirmarContrasenia().getPassword());
				// System.out.println("HOLA");
				if (!confirmarContraseñaAux.equals("Confirmar contraseña")) {
					if (nuevaVista.getPassCheckBox2().isSelected()) {
						nuevaVista.getConfirmarContrasenia().setEchoChar((char) 0);
						nuevaVista.getPassCheckBox2().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye.png")));
					} else {
						nuevaVista.getConfirmarContrasenia().setEchoChar('*');
						nuevaVista.getPassCheckBox2().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));
					}
				}
			}
		}
		
		// Metodos MouseListener
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource().equals(nuevaVista.getBoton())) {
				nuevaVista.getBoton().setForeground(Color.WHITE);
			}
			if(e.getSource().equals(nuevaVista.getBotonIniciarSesion())){
				nuevaVista.getBotonIniciarSesion().setForeground(new Color(0,0,0,64));
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource().equals(nuevaVista.getBoton())) {
				nuevaVista.getBoton().setForeground(Color.LIGHT_GRAY);
			}
			if(e.getSource().equals(nuevaVista.getBotonIniciarSesion())){
				nuevaVista.getBotonIniciarSesion().setForeground(new Color(0,0,0,64));
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource().equals(nuevaVista.getBoton())) {
				nuevaVista.getBoton().setForeground(Color.LIGHT_GRAY);
			}
			if(e.getSource().equals(nuevaVista.getBotonIniciarSesion())){
				nuevaVista.getBotonIniciarSesion().setForeground(new Color(0,0,0,95));
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource().equals(nuevaVista.getBoton())) {
				nuevaVista.getBoton().setForeground(Color.WHITE);
			}
			if(e.getSource().equals(nuevaVista.getBotonIniciarSesion())){
				nuevaVista.getBotonIniciarSesion().setForeground(new Color(0,0,0,64));
			}
		}
	}