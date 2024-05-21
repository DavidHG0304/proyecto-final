package modelo.entidades;

public class Usuario {
	private int idUsuario;
	private String nombreUsuario;
	private String apellido;
	private String correo;
	
	public Usuario(int idUsuario, String nombreUsuario, String apellido, String correo) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellido = apellido;
		this.correo = correo;
		
	}
}
