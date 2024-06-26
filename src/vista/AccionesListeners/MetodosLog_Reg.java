package vista.AccionesListeners;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import raven.glasspanepopup.GlassPanePopup;
import vista.componentes.DialogoAvisos;
import vista.recursos.componentesPersonalizados.*;

public class MetodosLog_Reg {
	
	
	public MetodosLog_Reg () {
		
	}
	
	public boolean loginValidado(String contrasenia,  JTextFieldRedondeado txtCorreo, JPasswordFieldRedondeado passwordField ) {
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
			GlassPanePopup.showPopup(new DialogoAvisos("Campos vacios", "Rellene los campos para poder\ncontinuar con el inicio de sesión."));
			return false;
		}
		return true;
	}
	
	public void loginNoValido(String contrasenia, JTextFieldRedondeado txtCorreo, JPasswordFieldRedondeado passwordField, boolean registroEncontrado) {
		if(!registroEncontrado && (!txtCorreo.getText().equals("Correo electronico") && !contrasenia.equals("Contraseña"))){
			txtCorreo.setColorB(new Color(217,0,30));
			txtCorreo.repaint();
			passwordField.setColorB(new Color(217, 0, 30));
			passwordField.repaint();
			GlassPanePopup.showPopup(new DialogoAvisos("Datos invalidos", "Revise su información, si no tiene   una cuenta, puede crearla."));
		}
		
	}
	
	public boolean registroValido(String contrasenia, String confirContrasenia, JTextFieldRedondeado nombreUsuario, JTextFieldRedondeado apellidoUsuario, JTextFieldRedondeado txtCorreo, JPasswordFieldRedondeado registrarContrasenia, JPasswordFieldRedondeado confirmarContrasenia) {
		
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
			GlassPanePopup.showPopup(new DialogoAvisos("Campos vacios", "Rellene los campos para poder\ncontinuar con el registro."));
			return false;
		}
		
		// Validar que los campos sean correctos
		if (contrasenia.length() < 8 || confirContrasenia.length() < 8  || !confirContrasenia.equals(contrasenia)) {
			if(contrasenia.length() < 8 || confirContrasenia.length() < 8) {
				if((contrasenia.length() < 8)) {
//				System.out.println("Contraseña debe ser mayor a 8 caracteres");
				registrarContrasenia.setColorB(new Color(217, 0, 30));
				registrarContrasenia.repaint();
				}
			if(confirContrasenia.length() < 8) {
//				System.out.println("Contraseña debe ser mayor a 8 caracteres");
				confirmarContrasenia.setColorB(new Color(217,0,30));
				confirmarContrasenia.repaint();
			}
			GlassPanePopup.showPopup(new DialogoAvisos("Error", "Su contraseña es demasiado corta \ningrese una mayor a 8 caracteres."));
			return false;
			}
		}
			if (!confirContrasenia.equals(contrasenia)) {
//				System.out.println("Contrasenias no coinciden");
				registrarContrasenia.setColorB(new Color(217, 0, 30));
				registrarContrasenia.repaint();
				confirmarContrasenia.setColorB(new Color(217,0,30));
				confirmarContrasenia.repaint();
				GlassPanePopup.showPopup(new DialogoAvisos("Error", "Las contraseñas no coinciden \nRevise sus contraseñas."));
				return false;
			}
		
			if(!esCorreoValido(txtCorreo.getText())) {
//				System.out.println("El correo no es valido");
				txtCorreo.setColorB(new Color(217,0,30));
				txtCorreo.repaint();
				GlassPanePopup.showPopup(new DialogoAvisos("Error", "Revise su correo electronico e \ningrese uno que sea valido."));
				return false;
			}
		
		// Una vez validado todo lo anterior ingresar a la base de datos para registrar al usuario
		return true;
	}
	
	public void registroNoValido(JTextFieldRedondeado nombreUsuario, JTextFieldRedondeado apellidoUsuario, JTextFieldRedondeado txtCorreo, JPasswordFieldRedondeado registrarContrasenia, JPasswordFieldRedondeado confirmarContrasenia, boolean estadoRegistro, String contrasenia, String confirContrasenia, int sepudocrear) {
		if(estadoRegistro){
		txtCorreo.setColorB(new Color(217,0,30));
		txtCorreo.repaint();
		GlassPanePopup.showPopup(new DialogoAvisos("Correo en uso", "Ya existe una cuenta con esa \ndirección de correo electronico."));
		}else if(sepudocrear == 1){
			GlassPanePopup.showPopup(new DialogoAvisos("Cuenta Creada", "Cuenta creada con éxito \nInicie sesión para poder navegar \n por el sistema."));
		}
	}
	
	public static boolean esCorreoValido(String correoValidando) {
	    if (correoValidando != null) {
	        Pattern[] formatos = {

	            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
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
