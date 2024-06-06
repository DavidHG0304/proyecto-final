package modelo.entidades;

public class Rentas {
	private int id;
	private int usuario_id;
	private int vehiculo_id;
	private String fecha_inicial;
	private String fecha_final;
	private String fecha_nacimiento;
	private Double costo;
	private Usuarios usuario;
    private Vehiculos vehiculo;
	

	public Vehiculos getVehiculo() {
		return vehiculo;
	}



	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Rentas() {

	}
	
	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public int getVehiculo_id() {
		return vehiculo_id;
	}

	public void setVehiculo_id(int vehiculo_id) {
		this.vehiculo_id = vehiculo_id;
	}

	public String getFecha_inicial() {
		return fecha_inicial;
	}

	public void setFecha_inicial(String fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
                                                             