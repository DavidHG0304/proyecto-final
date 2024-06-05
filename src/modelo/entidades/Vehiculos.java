package modelo.entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class Vehiculos {
    private int idVehiculo;
	private String nombreVehiculo;
	private String añoVehiculo;
	private int puertasVehiculo;
	private int kilometrajeVehiculo;
	private String transmision;
	private boolean aireAcondicionado;
	private String modelo;
	private String marcas;
	private Tarifas tarifa;
	private String categoria;
	private String imagenUrl;
	private double costoTotal;
	
	// Cambio
	private int categoriaId;
	private int marcaId;
	
	public Vehiculos() {
		
	}
	public Vehiculos(int idVehiculo, String nombreVehiculo, String añoVehiculo, int puertasVehiculo, int kilometrajeVehiculo, String transmision, boolean aireAcondicionado, String modelo) {
		this.idVehiculo = idVehiculo;
		this.nombreVehiculo = nombreVehiculo;
		this.añoVehiculo = añoVehiculo;
		this.puertasVehiculo = puertasVehiculo;
		this.kilometrajeVehiculo = kilometrajeVehiculo;
		this.transmision = transmision;
		this.aireAcondicionado = aireAcondicionado;
		this.modelo = modelo;
	}
	
	
	// Getters
	public int getIdVehiculo() {
		return idVehiculo;
	}
	public String getNombreVehiculo() {
		return nombreVehiculo;
	}
	public String getAñoVehiculo() {
		return añoVehiculo;
	}
	public int getPuertasVehiculo() {
		return puertasVehiculo;
	}
	public int getKilometrajeVehiculo() {
		return kilometrajeVehiculo;
	}
	public String getTransmision() {
		return transmision;
	}
	public boolean isAireAcondicionado() {
		return aireAcondicionado;
	}
	public String getModelo() {
		return modelo;
	}
	public String getMarcas() {
		return marcas;
	}
	public Tarifas getTarifa() {
		return tarifa;
	}
	public String getCategoria() {
		return categoria;
	}
	public String getImagenUrl() {
		return imagenUrl;
	}
	public double getCostoTotal() {
		return costoTotal;
	}
	
	
	// Setters
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public void setNombreVehiculo(String nombreVehiculo) {
		this.nombreVehiculo = nombreVehiculo;
	}
	public void setAñoVehiculo(String añoVehiculo) {
		this.añoVehiculo = añoVehiculo;
	}
	public void setPuertasVehiculo(int puertasVehiculo) {
		this.puertasVehiculo = puertasVehiculo;
	}
	public void setKilometrajeVehiculo(int kilometrajeVehiculo) {
		this.kilometrajeVehiculo = kilometrajeVehiculo;
	}
	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}
	public void setAireAcondicionado(boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void setMarcas(String marcas) {
		this.marcas = marcas;
	}
	public void setTarifa(Tarifas tarifa) {
		this.tarifa = tarifa;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	public int getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	public int getMarcaId() {
		return marcaId;
	}
	public void setMarcaId(int marcaId) {
		this.marcaId = marcaId;
	}
	
	
}
