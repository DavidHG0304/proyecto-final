package modelo;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.entidades.Vehiculos;


//Aqui se haran todas las validaciones para poder hacer las acciones en base a la base
//De datos y todo eso

public class Modelo {
	private boolean registroEncontrado = false;
	private boolean registrado = false;
	// 0 = no pudo completarse --- 1 = pudo completarse --- 3 = hay coincidencias (no se completa)
	private int hayRegistro = 0;

	public boolean isRegistroEncontrado() {
		return registroEncontrado;
	}

	public boolean isRegistrado() {
		return registrado;
	}
	public void setRegistrado(boolean registrado) {
		this.registrado = registrado;
	}

	public void setRegistroEncontrado(boolean registroEncontrado) {
		this.registroEncontrado = registroEncontrado;
	}

	public int getHayRegistro() {
		return hayRegistro;
	}

	public void setHayRegistro(int hayRegistro) {
		this.hayRegistro = hayRegistro;
	}

	public Modelo() {
	}
	
	
	public boolean accionLogin(String correoElectronico, String contrasenia) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_registros?useSSL=false","freedb_prueba","#ZKP5mSgzS6Ps&!");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
			
			while (rs.next() ) {
				String contraEncriptada = encriptarContrasenia(contrasenia);
				// Encriptar la contraseña que ingresa el usuario para validar si esta coincide con la que está registrada en la base de datos
				if(rs.getString("correo_electronico").equals(correoElectronico) && rs.getString("contraseña").equals(contraEncriptada)) {
					registroEncontrado = true;
					break;
				}
				
			}
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(registroEncontrado) {
			System.out.println("Datos Coinciden");
			return true;
		}else {
			System.out.println("Datos No coinciden");
			return false;
		}
	}
	
	public boolean accionRegistro(String nombreUsuario, String apellidoUsuario, String correo, String contrasenia, String confirContrasenia) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_registros?useSSL=false","freedb_prueba","#ZKP5mSgzS6Ps&!");
			
			String query = "INSERT INTO usuarios (nombre, prim_apellido, correo_electronico, contraseña) values('" + nombreUsuario + "','" + apellidoUsuario + "','" + correo + "','" + encriptarContrasenia(contrasenia) + "')";
			Statement st = con.createStatement();
			
			int x = st.executeUpdate(query);
			if(x != 0) {
				registrado = false;
				hayRegistro = 1;
				return true;
			}
			JOptionPane.showMessageDialog(null, "No se pudo crear la cuenta");
			con.close();
			return false;
		} catch (Exception exception) {
			registrado = true;
			exception.printStackTrace();
        }
		hayRegistro = 0;
		return false;
	}
	
	public ArrayList<Vehiculos> obtenerVehiculos() {
		ArrayList<Vehiculos> vehiculos = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		String sql = "SELECT v.nombre AS nombre_vehiculo, v.año, v.cantidad_puertas, v.transmision, v.modelo, m.nombre AS nombre_marca, c.nombre AS categoria, t.tarifa_por_dia AS costo_por_dia, i.url " +
//	            "FROM vehiculos v " +
//	            "INNER JOIN marca m ON v.marca_id = m.id " +
//	            "INNER JOIN categoria c ON v.categoria_id = c.id " +
//	            "INNER JOIN tarifas t ON v.tarifas_id = t.id " +
//	            "INNER JOIN imagenes_vehiculos iv ON v.id = iv.vehiculo_id " +
//	            "INNER JOIN imagenes i ON iv.imagen_id = i.id";
		String sql = "SELECT v.id, v.nombre AS nombre_vehiculo, v.año, v.cantidad_puertas, v.transmision, v.modelo, m.nombre AS nombre_marca, c.nombre AS categoria, t.tarifa_por_dia AS costo_por_dia " +
	            "FROM vehiculos v " +
	            "INNER JOIN marca m ON v.marca_id = m.id " +
	            "INNER JOIN categoria c ON v.categoria_id = c.id " +
	            "INNER JOIN tarifas t ON v.tarifas_id = t.id ";
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_registros?useSSL=false","freedb_prueba","#ZKP5mSgzS6Ps&!");
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Vehiculos vehiculo = new Vehiculos();
					vehiculo.setIdVehiculo(rs.getInt("id"));
					vehiculo.setNombreVehiculo(rs.getString("nombre_marca"));
					vehiculo.setAñoVehiculo(rs.getString("año"));
					vehiculo.setPuertasVehiculo(rs.getInt("cantidad_puertas"));
					vehiculo.setTransmision(rs.getString("transmision"));
					vehiculo.setModelo(rs.getString("modelo"));
			        vehiculo.setCategoria(rs.getString("categoria"));
			        vehiculo.setCostoTotal(rs.getFloat("costo_por_dia"));
		//	        vehiculo.setImagenUrl(rs.getString("url"));
			        vehiculos.add(vehiculo);
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return vehiculos;
	}
	
	public void obtenerImagenesVehiculos(ArrayList<Vehiculos> vehiculos) {
		// Solo imagenes
//		String sql = "SELECT v.id, i.url " + 
//				"FROM vehiculos v "
//				+ "INNER JOIN imagenes_vehiculos iv ON v.id = iv.vehiculo_id "
//				+ "INNER JOIN imagenes i ON iv.imagen_id = i.id";
//		try (Connection con = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_registros?useSSL=false", "freedb_prueba", "#ZKP5mSgzS6Ps&!");
//				PreparedStatement stmt = con.prepareStatement(sql);
//				ResultSet rs = stmt.executeQuery()) {
//			while (rs.next()) {
//                int vehiculoId = rs.getInt("id");
////                String url = rs.getString("url");
//                for (Vehiculo vehiculo : vehiculos) {
////                	if (vehiculo.getIdVehiculo() == vehiculoId) {
////                		vehiculo.setImagenUrl(url);
////                	}
//                }
//            }
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	public static String encriptarContrasenia(String contrasenia) {
		try{
	        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        final byte[] hash = digest.digest(contrasenia.getBytes("UTF-8"));
	        final StringBuilder hexString = new StringBuilder();
	        for (int i = 0; i < hash.length; i++) {
	            final String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) 
	              hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	
	public Vehiculos obtenerVehiculoPorId(int id) {
	    for (Vehiculos vehiculo : obtenerVehiculos()) {
	        if (vehiculo.getIdVehiculo() == id) {
	            return vehiculo;
	        }
	    }
	    return null;
	}
	
}
