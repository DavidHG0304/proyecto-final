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

import modelo.entidades.Rentas;
import modelo.entidades.Usuarios;
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
	
	/*
	
	public boolean accionLogin(String correoElectronico, String contrasenia) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false","root","AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
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
	
	*/
	
	public Usuarios accionLogin(String correoElectronico, String contrasenia) {
		Usuarios usuarioIniciar = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false","root","AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
			String query = "SELECT * FROM usuarios WHERE correo_electronico = ? AND contraseña = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, correoElectronico);
            pst.setString(2, encriptarContrasenia(contrasenia));
            ResultSet rs = pst.executeQuery();
			
			while (rs.next() ) {
				usuarioIniciar = new Usuarios();
				usuarioIniciar.setIdUsuario(rs.getInt("id"));
				usuarioIniciar.setNombreUsuario(rs.getString("nombre"));
				usuarioIniciar.setApellido(rs.getString("prim_apellido"));
                usuarioIniciar.setCorreo(rs.getString("correo_electronico"));
                registroEncontrado = true;
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
			return usuarioIniciar;
		}else {
			System.out.println("Datos No coinciden");
			return null;
		}
	}
	
	public boolean accionRegistro(String nombreUsuario, String apellidoUsuario, String correo, String contrasenia, String confirContrasenia) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false","root","AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
			
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
		
		String sql = "SELECT v.id, v.nombre AS nombre_vehiculo, v.año, v.cantidad_puertas, v.transmision, v.modelo, " + "m.nombre AS nombre_marca, c.nombre AS categoria, t.tarifa_por_dia AS costo_por_dia, i.url AS imagen_url " +
	             "FROM vehiculos v " +
	             "INNER JOIN marca m ON v.marca_id = m.id " +
	             "INNER JOIN categoria c ON v.categoria_id = c.id " +
	             "INNER JOIN tarifas t ON v.tarifas_id = t.id " +
	             "INNER JOIN imagenes i ON v.imagenes_id = i.id";

		
		
//		try (Connection con = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_registros?useSSL=false","freedb_prueba","#ZKP5mSgzS6Ps&!");
		try (Connection con = DriverManager.getConnection("jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false","root","AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
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
			        vehiculo.setImagenUrl(rs.getString("imagen_url"));
			        vehiculos.add(vehiculo);
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return vehiculos;
	}
	
	// Obtener la inormación de los usuarios para imprimirla en pantalla
	public ArrayList<Usuarios> obtenerUsuarios() {
		ArrayList<Usuarios> usuarios = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "SELECT id, nombre, prim_apellido, correo_electronico, contraseña FROM usuarios";
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false","root","AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						Usuarios usuario = new Usuarios();
						usuario.setIdUsuario(rs.getInt("id"));
						usuario.setNombreUsuario(rs.getString("nombre"));
						usuario.setApellido(rs.getString("prim_apellido"));
						usuario.setCorreo(rs.getString("correo_electronico"));
						usuarios.add(usuario);
					}
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return usuarios;
	}
	
	public boolean eliminarUsuario(int idUsuario) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    String sql = "DELETE FROM usuarios WHERE id = ?";
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false","root","AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	        PreparedStatement stmt = con.prepareStatement(sql)) {
	        
	        stmt.setInt(1, idUsuario);
	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean actualizarUsuario(int id, String nombre, String apellidos, String correo, String contrasenia) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    String sql = "UPDATE usuarios SET nombre = ?, prim_apellido = ?, correo_electronico = ?, contraseña = ? WHERE id = ?";
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false","root","AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	        PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, nombre);
	        stmt.setString(2, apellidos);
	        stmt.setString(3, correo);
	        stmt.setString(4, encriptarContrasenia(contrasenia));
	        stmt.setInt(5, id);
	        
	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
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
	
	
	
	public Rentas mostrarRentas(int idRenta) {
		Rentas renta = null;
		String sql = "SELECT * FROM rentas WHERE id =  ?";
		//boolean rentaEncontrada;
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false","root","AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idRenta);
            ResultSet rs = stmt.executeQuery();
			
			if (rs.next() ) {
				renta= new Rentas();
				renta.setId(rs.getInt("id"));
				renta.setVehiculo_id(rs.getInt("vehiculo_id"));
				renta.setUsuario_id(rs.getInt("usuario_id"));
				renta.setFecha_inicial(rs.getString("fecha_inicial"));
				renta.setFecha_final(rs.getString("fecha_final"));
				renta.setCosto(rs.getDouble("Costo"));
				System.out.println(renta.getId() + " | " + renta.getVehiculo_id() + " | " +renta.getFecha_inicial() + " | " + renta.getFecha_final() + " | " + renta.getCosto());
				
				
				 
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Holaaa");
		return renta;
	}
	
	
	
	
	
	
}
