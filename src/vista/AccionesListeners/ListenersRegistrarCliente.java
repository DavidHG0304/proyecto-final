package vista.AccionesListeners;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.ImageIcon;

import vista.VistaRegistro;
import vista.componentes.DialogoCrearCliente;

public class ListenersRegistrarCliente implements ActionListener, FocusListener, MouseListener{

	public DialogoCrearCliente dialogoCrearCliente;
	
	
	public ListenersRegistrarCliente(DialogoCrearCliente dialogoCrear) {
		this.dialogoCrearCliente = dialogoCrear;
		asignarListeners();
	}
	
	public void asignarListeners() {
		dialogoCrearCliente.asignarFocusListener(this);
		dialogoCrearCliente.asignarMouseClicked(this);
//		dialogoCrearCliente.asignarActListnerCheckBox(this);
	}
	
	// Metodos FocusListener
		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource().equals(dialogoCrearCliente.getTxtNombre())) {
				if(dialogoCrearCliente.getTxtNombre().getText().equals("Nombre")) {
					dialogoCrearCliente.getTxtNombre().setText("");
					dialogoCrearCliente.getTxtNombre().setForeground(Color.BLACK);
				}
				
			}else if(e.getSource().equals(dialogoCrearCliente.getTxtApellidos())) {
				if(dialogoCrearCliente.getTxtApellidos().getText().equals("Apellidos")) {
					dialogoCrearCliente.getTxtApellidos().setText("");
					dialogoCrearCliente.getTxtApellidos().setForeground(Color.BLACK);
				}
			}
			else if (e.getSource().equals(dialogoCrearCliente.getTxtCorreo())) {
				if(dialogoCrearCliente.getTxtCorreo().getText().equals("Correo electronico")) {
					dialogoCrearCliente.getTxtCorreo().setText("");
					dialogoCrearCliente.getTxtCorreo().setForeground(Color.BLACK);
				}

			}else if (e.getSource().equals(dialogoCrearCliente.getTxtContrasenia())) {
//				dialogoCrearCliente.getPassCheckBox().setSelected(false);
				dialogoCrearCliente.getTxtContrasenia().setEchoChar('*');
				
				String password = String.valueOf(dialogoCrearCliente.getTxtContrasenia().getPassword());
//				nuevaVista.getPassCheckBox().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));

				if (password.toLowerCase().equals("contraseña")) {
					dialogoCrearCliente.getTxtContrasenia().setText("");
					dialogoCrearCliente.getTxtContrasenia().setForeground(Color.black);
				}
			}else if (e.getSource().equals(dialogoCrearCliente.getTxtConfirmarContrasenia())) {
//				dialogoCrearCliente.getPassCheckBox2().setSelected(false);
				dialogoCrearCliente.getTxtConfirmarContrasenia().setEchoChar('*');
				
				String password = String.valueOf(dialogoCrearCliente.getTxtConfirmarContrasenia().getPassword());
//				dialogoCrearCliente.getPassCheckBox2().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));

				if (password.toLowerCase().equals("confirmar contraseña")) {
					dialogoCrearCliente.getTxtConfirmarContrasenia().setText("");
					dialogoCrearCliente.getTxtConfirmarContrasenia().setForeground(Color.black);
				}
			}
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			if(dialogoCrearCliente.getTxtNombre().getText().equals("")) {
				dialogoCrearCliente.getTxtNombre().setText("Nombre");
				dialogoCrearCliente.getTxtNombre().setForeground(new Color(0,0,0,90));
			}
			
			if(dialogoCrearCliente.getTxtApellidos().getText().equals("")) {
				dialogoCrearCliente.getTxtApellidos().setText("Apellidos");
				dialogoCrearCliente.getTxtApellidos().setForeground(new Color(0,0,0,90));
			}
			
			if(dialogoCrearCliente.getTxtCorreo().getText().equals("")) {
				dialogoCrearCliente.getTxtCorreo().setText("Correo electronico");
				dialogoCrearCliente.getTxtCorreo().setForeground(new Color(0,0,0,90));
			}
			
			String contraseñaAux = String.valueOf(dialogoCrearCliente.getTxtContrasenia().getPassword());
		    if(contraseñaAux.toLowerCase().equals("contraseña") || contraseñaAux.toLowerCase().equals("")) {
		    	dialogoCrearCliente.getTxtContrasenia().setText("Contraseña");
		    	dialogoCrearCliente.getTxtContrasenia().setEchoChar((char)0);
		    	dialogoCrearCliente.getTxtContrasenia().setForeground(new Color(0,0,0,90));
		    }
		    String confirmarContraseñaAux = String.valueOf(dialogoCrearCliente.getTxtConfirmarContrasenia().getPassword());
		    if(confirmarContraseñaAux.toLowerCase().equals("confirmar contraseña") || confirmarContraseñaAux.toLowerCase().equals("")) {
		    	dialogoCrearCliente.getTxtConfirmarContrasenia().setText("Confirmar contraseña");
		    	dialogoCrearCliente.getTxtConfirmarContrasenia().setEchoChar((char)0);
		    	dialogoCrearCliente.getTxtConfirmarContrasenia().setForeground(new Color(0,0,0,90));
		    }
		}
		
		// Metodos ActionListener
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			switch (e.getActionCommand()) {
//			case "CheckBox1":
//				// System.out.println("Prueba");
//				String contraseñaAux = String.valueOf(dialogoCrearCliente.getTxtContrasenia().getPassword());
//				if (!contraseñaAux.equals("Contraseña")) {
//					if (dialogoCrearCliente.getPassCheckBox().isSelected()) {
//						dialogoCrearCliente.getTxtContrasenia().setEchoChar((char) 0);
//						dialogoCrearCliente.getPassCheckBox().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye.png")));
//					} else {
//						nuevaVista.getTxtContrasenia().setEchoChar('*');
//						nuevaVista.getPassCheckBox().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));
//					}
//				}
//			case "CheckBox2":
//				String confirmarContraseñaAux = String.valueOf(nuevaVista.getConfirmarContrasenia().getPassword());
//				// System.out.println("HOLA");
//				if (!confirmarContraseñaAux.equals("Confirmar contraseña")) {
//					if (nuevaVista.getPassCheckBox2().isSelected()) {
//						nuevaVista.getConfirmarContrasenia().setEchoChar((char) 0);
//						nuevaVista.getPassCheckBox2().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye.png")));
//					} else {
//						nuevaVista.getConfirmarContrasenia().setEchoChar('*');
//						nuevaVista.getPassCheckBox2().setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));
//					}
//				}
//			}
//		}
		
		// Metodos MouseListener
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}