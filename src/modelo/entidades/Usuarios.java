package modelo.entidades;

public class Usuarios {
	private int idUsuario;
	private String nombreUsuario;
	private String apellido;
	private String correo;
	private String contrasenia;
	
	public Usuarios() {
		
	}
	
	public Usuarios(String nombreUsuario, String correo) {
		
	}
	
	public Usuarios(int idUsuario, String nombreUsuario, String apellido, String correo) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellido = apellido;
		this.correo = correo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}
