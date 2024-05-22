package vista.AccionesListeners;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.ImageIcon;

import raven.glasspanepopup.GlassPanePopup;
import vista.Vista;
import vista.componentes.DialogoPrueba;

public class ListenersLogin  implements ActionListener, FocusListener{
	
	public Vista nuevaVista;

	public ListenersLogin (Vista vista){
		this.nuevaVista = vista;
		asignarListeners();
	}

	public void asignarListeners() {
		nuevaVista.asignarFocusListener(this);
//		nuevaVista.asignarMouseClicked(this);
		nuevaVista.asignarActListnerCheckBox(this);
	}

	// Metodos FocusListener
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(nuevaVista.getTxtCorreo())) {
			if (nuevaVista.getTxtCorreo().getText().equals("Correo electronico")) {
				nuevaVista.getTxtCorreo().setText("");
				nuevaVista.getTxtCorreo().setForeground(Color.BLACK);
			}

		} else if (e.getSource().equals(nuevaVista.getTxtContrasenia())) {
			nuevaVista.getPassCheckBox().setSelected(false);
			nuevaVista.getTxtContrasenia().setEchoChar('*');
			String password = String.valueOf(nuevaVista.getTxtContrasenia().getPassword());
			nuevaVista.getPassCheckBox()
					.setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));

			if (password.toLowerCase().equals("contraseña")) {
				nuevaVista.getTxtContrasenia().setText("");
				nuevaVista.getTxtContrasenia().setForeground(Color.black);
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (nuevaVista.getTxtCorreo().getText().equals("")) {
			nuevaVista.getTxtCorreo().setText("Correo electronico");
			nuevaVista.getTxtCorreo().setForeground(new Color(0, 0, 0, 90));
		}

		String password = String.valueOf(nuevaVista.getTxtContrasenia().getPassword());
		if (password.toLowerCase().equals("contraseña") || password.toLowerCase().equals("")) {
			nuevaVista.getTxtContrasenia().setText("Contraseña");
			nuevaVista.getTxtContrasenia().setEchoChar((char) 0);
			nuevaVista.getTxtContrasenia().setForeground(new Color(0, 0, 0, 90));
		}
//	    nuevaVista.getPassCheckBox().setIcon(new ImageIcon(getClass().getResource("/imagenes/eye-closed.png")));
	}

	// Metodos ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "CheckBox1":
			// System.out.println("Prueba");
			String password = String.valueOf(nuevaVista.getTxtContrasenia().getPassword());
			if (!password.equals("Contraseña")) {
				if (nuevaVista.getPassCheckBox().isSelected()) {
					nuevaVista.getTxtContrasenia().setEchoChar((char) 0);
					nuevaVista.getPassCheckBox()
							.setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye.png")));
				} else {
					nuevaVista.getTxtContrasenia().setEchoChar('*');
					nuevaVista.getPassCheckBox()
							.setIcon(new ImageIcon(getClass().getResource("/vista/recursos/imagenes/eye-closed.png")));
				}
			}
		}
	}

	// Metodos MouseListener
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//	}

//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource().equals(nuevaVista.getBoton())) {
//			nuevaVista.getBoton().setForeground(Color.WHITE);
//		}
//		if (e.getSource().equals(nuevaVista.getBotonRegistrar())) {
//			nuevaVista.getBotonRegistrar().setForeground(new Color(0, 0, 0, 64));
//		}
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource().equals(nuevaVista.getBoton())) {
//			nuevaVista.getBoton().setForeground(Color.LIGHT_GRAY);
//		}
//		if (e.getSource().equals(nuevaVista.getBotonRegistrar())) {
//			nuevaVista.getBotonRegistrar().setForeground(new Color(0, 0, 0, 64));
//		}
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//		if (e.getSource().equals(nuevaVista.getBoton())) {
//			nuevaVista.getBoton().setForeground(Color.LIGHT_GRAY);
//		}
//		if (e.getSource().equals(nuevaVista.getBotonRegistrar())) {
//			nuevaVista.getBotonRegistrar().setForeground(new Color(0, 0, 0, 95));
//		}
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource().equals(nuevaVista.getBoton())) {
//			nuevaVista.getBoton().setForeground(Color.WHITE);
//		}
//		if (e.getSource().equals(nuevaVista.getBotonRegistrar())) {
//			nuevaVista.getBotonRegistrar().setForeground(new Color(0, 0, 0, 64));
//		}
//	}
}
