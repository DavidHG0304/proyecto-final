package modelo.entidades;

public class Usuarios {
	private int idUsuario;
	private String nombreUsuario;
	private String apellido;
	private String correo;
	
	public Usuarios(int idUsuario, String nombreUsuario, String apellido, String correo) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellido = apellido;
		this.correo = correo;
		
	}
}
