package vista.AccionesListeners;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import vista.recursos.componentesPersonalizados.*;

public class MetodosLog_Reg {
	
	
	public MetodosLog_Reg () {
		
	}
	
	public boolean loginValidado(String contrasenia,  RoundJTextField txtCorreo, RoundJPasswordField passwordField ) {
		txtCorreo.setColorB(new Color(0,0,0));
		txtCorreo.repaint();
		passwordField.setColorB(new Color(0,0,0));
		passwordField.repaint();
		// Valida si alguno de los espacios están vacios
		if(txtCorreo.getText().equals("Correo electronico") || contrasenia.equals("Contraseña")){
			if(txtCorreo.getText().equals("Correo electronico")) {
//				System.out.println("Campo correo Vacio");
				txtCorreo.setColorB(new Color(217,0,30));
				txtCorreo.repaint();
			}
			if(contrasenia.equals("Contraseña")) {
//				System.out.println("Campo contraseña Vacio");
				passwordField.setColorB(new Color(217,0,30));
				passwordField.repaint();
			}
			return false;
		}
		return true;
	}
	
	public void loginNoValido(String contrasenia, RoundJTextField txtCorreo, RoundJPasswordField passwordField) {
		if(!txtCorreo.getText().equals("Correo electronico") && !contrasenia.equals("Contraseña")){
			txtCorreo.setColorB(new Color(217,0,30));
			txtCorreo.repaint();
			passwordField.setColorB(new Color(217,0,30));
			passwordField.repaint();
		}		
		
	}
	
	public boolean registroValido(String contrasenia, String confirContrasenia, RoundJTextField nombreUsuario, RoundJTextField apellidoUsuario, RoundJTextField txtCorreo, RoundJPasswordField registrarContrasenia, RoundJPasswordField confirmarContrasenia) {
		nombreUsuario.setColorB(new Color(0,0,0));
		nombreUsuario.repaint();
		apellidoUsuario.setColorB(new Color(0,0,0));
		apellidoUsuario.repaint();
		txtCorreo.setColorB(new Color(0,0,0));
		txtCorreo.repaint();
		registrarContrasenia.setColorB(new Color(0,0,0));	
		registrarContrasenia.repaint();
		confirmarContrasenia.setColorB(new Color(0,0,0));
		confirmarContrasenia.repaint();
		
		// Valida si no hay espacios vacios
		if(nombreUsuario.getText().equals("Nombre") || apellidoUsuario.getText().equals("Apellidos") || txtCorreo.getText().equals("Correo electronico") || contrasenia.equals("Contraseña") || confirContrasenia.equals("Confirmar contraseña")){
			if(nombreUsuario.getText().equals("Nombre")) {
//				System.out.println("Campo correo Vacio");
				nombreUsuario.setColorB(new Color(217,0,30));
				nombreUsuario.repaint();
			}
			
			if(apellidoUsuario.getText().equals("Apellidos")) {
//				System.out.println("Campo correo Vacio");
				apellidoUsuario.setColorB(new Color(217,0,30));
				apellidoUsuario.repaint();
			}
			
			if(txtCorreo.getText().equals("Correo electronico")) {
//				System.out.println("Campo correo Vacio");
				txtCorreo.setColorB(new Color(217,0,30));
				txtCorreo.repaint();
			}
			
			if(contrasenia.equals("Contraseña")) {
//				System.out.println("Campo contraseña Vacio");
				registrarContrasenia.setColorB(new Color(217,0,30));
				registrarContrasenia.repaint();
			}
			
			if(confirContrasenia.equals("Confirmar contraseña")) {
//				System.out.println("Campo confirmar contraseña Vacio");
				confirmarContrasenia.setColorB(new Color(217,0,30));
				confirmarContrasenia.repaint();
			}
			// JOptionPane.showMessageDialog(null, "Hay Campos Vacios", "Rellene los campos", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// Validar que los campos sean correctos
		if (contrasenia.length() < 8 || !confirContrasenia.equals(contrasenia) || !esCorreoValido(txtCorreo.getText())) {
			if(contrasenia.length() < 8) {
//				System.out.println("Contraseña debe ser mayor a 8 caracteres");
				registrarContrasenia.setColorB(new Color(217, 0, 30));
				registrarContrasenia.repaint();
			}
			if(confirContrasenia.length() < 8) {
//				System.out.println("Contraseña debe ser mayor a 8 caracteres");
				confirmarContrasenia.setColorB(new Color(217,0,30));
				confirmarContrasenia.repaint();
			}
			
			if (!confirContrasenia.equals(contrasenia)) {
//				System.out.println("Contrasenias no coinciden");
				registrarContrasenia.setColorB(new Color(217, 0, 30));
				registrarContrasenia.repaint();
				confirmarContrasenia.setColorB(new Color(217,0,30));
				confirmarContrasenia.repaint();
			}
			if(!esCorreoValido(txtCorreo.getText())) {
//				System.out.println("El correo no es valido");
				JOptionPane.showMessageDialog(null, "Formato Correo Invalido", "Rellene los campos", JOptionPane.ERROR_MESSAGE);
				txtCorreo.setColorB(new Color(217,0,30));
				txtCorreo.repaint();
			}
			return false;
		}
		// Una vez validado todo lo anterior ingresar a la base de datos para registrar al usuario
		return true;
	}
	
	public void registroNoValido(RoundJTextField nombreUsuario, RoundJTextField apellidoUsuario, RoundJTextField txtCorreo, RoundJPasswordField registrarContrasenia, RoundJPasswordField confirmarContrasenia) {
		nombreUsuario.setColorB(new Color(217,0,30));
		nombreUsuario.repaint();
		apellidoUsuario.setColorB(new Color(217,0,30));
		apellidoUsuario.repaint();
		txtCorreo.setColorB(new Color(217,0,30));
		txtCorreo.repaint();
		registrarContrasenia.setColorB(new Color(217,0,30));
		registrarContrasenia.repaint();
		confirmarContrasenia.setColorB(new Color(217,0,30));
		confirmarContrasenia.repaint();
	}
	
	public static boolean esCorreoValido(String correoValidando) {
	    if (correoValidando != null) {
	        Pattern[] formatos = {
	            Pattern.compile("^[A-Za-z].*?@gmail\\.com$"),
	            Pattern.compile("^[A-Za-z].*?@outlook\\.com$"),
	            Pattern.compile("^[A-Za-z].*?@alu\\.uabcs\\.mx$")
	        };
	        
	        for (Pattern formato : formatos) {
	            Matcher coincide = formato.matcher(correoValidando);
	            if (coincide.matches()) {
	            	return true;
	            }
	        }
	    }
	    return false;
	}
}
