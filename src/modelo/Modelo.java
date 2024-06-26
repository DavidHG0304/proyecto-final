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

import modelo.entidades.Categorias;
import modelo.entidades.Marcas;
import modelo.entidades.Rentas;
import modelo.entidades.Tarifas;
import modelo.entidades.Usuarios;
import modelo.entidades.Vehiculos;

//Aqui se haran todas las validaciones para poder hacer las acciones en base a la base
//De datos y todo eso

public class Modelo {
	private boolean registroEncontrado = false;
	private boolean registrado = false;
	// 0 = no pudo completarse --- 1 = pudo completarse --- 3 = hay coincidencias
	// (no se completa)
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

	public Usuarios accionLogin(String correoElectronico, String contrasenia) {
		Usuarios usuarioIniciar = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
					"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
			String query = "SELECT * FROM usuarios WHERE correo_electronico = ? AND contraseña = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, correoElectronico);
			pst.setString(2, encriptarContrasenia(contrasenia));
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
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
		if (registroEncontrado) {
			System.out.println("Datos Coinciden");
			return usuarioIniciar;
		} else {
			System.out.println("Datos No coinciden");
			return null;
		}
	}

	public boolean accionRegistro(String nombreUsuario, String apellidoUsuario, String correo, String contrasenia,
			String confirContrasenia) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
					"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");

			String query = "INSERT INTO usuarios (nombre, prim_apellido, correo_electronico, contraseña) values('"
					+ nombreUsuario + "','" + apellidoUsuario + "','" + correo + "','"
					+ encriptarContrasenia(contrasenia) + "')";
			Statement st = con.createStatement();

			int x = st.executeUpdate(query);
			if (x != 0) {
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

	    String sql = "SELECT v.id, v.nombre AS nombre_vehiculo, v.año, v.cantidad_puertas, v.transmision, v.modelo, v.kilometraje, v.aire_acondicionado, "
	               + "m.nombre AS nombre_marca, c.nombre AS categoria, t.tarifa_por_dia AS costo_por_dia, i.url AS imagen_url, "
	               + "t.seguro_danios, t.seguro_vida, t.seguro_kilometraje, t.combustible, t.tarifa_por_dia "
	               + "FROM vehiculos v "
	               + "INNER JOIN marca m ON v.marca_id = m.id "
	               + "INNER JOIN categoria c ON v.categoria_id = c.id "
	               + "INNER JOIN tarifas t ON v.tarifas_id = t.id "
	               + "INNER JOIN imagenes i ON v.imagenes_id = i.id";

	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            Vehiculos vehiculo = new Vehiculos();
	            vehiculo.setIdVehiculo(rs.getInt("id"));
	            vehiculo.setNombreVehiculo(rs.getString("nombre_vehiculo"));
	            vehiculo.setMarcas(rs.getString("nombre_marca"));
	            vehiculo.setAñoVehiculo(rs.getString("año"));
	            vehiculo.setPuertasVehiculo(rs.getInt("cantidad_puertas"));
	            vehiculo.setTransmision(rs.getString("transmision"));
	            vehiculo.setModelo(rs.getString("modelo"));
	            vehiculo.setCategoria(rs.getString("categoria"));
	            vehiculo.setCostoTotal(rs.getFloat("costo_por_dia"));
	            vehiculo.setImagenUrl(rs.getString("imagen_url"));
	            vehiculo.setKilometrajeVehiculo(rs.getInt("kilometraje"));
	            vehiculo.setAireAcondicionado(rs.getBoolean("aire_acondicionado"));

	            // Establecer tarifas
	            Tarifas tarifas = new Tarifas();
	            tarifas.setSeguro_danios(rs.getFloat("seguro_danios"));
	            tarifas.setSeguro_vida(rs.getFloat("seguro_vida"));
	            tarifas.setSeguro_kilometraje(rs.getFloat("seguro_kilometraje"));
	            tarifas.setSeguro_combustible(rs.getFloat("combustible"));
	            tarifas.setSeguro_tarifa_por_dia(rs.getFloat("tarifa_por_dia"));
	            vehiculo.setTarifa(tarifas);

	            vehiculos.add(vehiculo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return vehiculos;
	}
	
	public ArrayList<Vehiculos> obtenerVehiculosPorCategoria(String categoria) {
        ArrayList<Vehiculos> vehiculos = new ArrayList<>();
        String sql = "SELECT v.id, v.nombre AS nombre_vehiculo, v.año, v.cantidad_puertas, v.transmision, v.modelo, v.kilometraje, v.aire_acondicionado, "
                   + "m.nombre AS nombre_marca, c.nombre AS categoria_nombre, i.url AS imagen_url, "
                   + "t.seguro_danios, t.seguro_vida, t.seguro_kilometraje, t.combustible, t.tarifa_por_dia "
                   + "FROM vehiculos v "
                   + "INNER JOIN marca m ON v.marca_id = m.id "
                   + "INNER JOIN categoria c ON v.categoria_id = c.id "
                   + "INNER JOIN tarifas t ON v.tarifas_id = t.id "
                   + "INNER JOIN imagenes i ON v.imagenes_id = i.id "
                   + "WHERE c.nombre = ?";
        try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, categoria);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehiculos vehiculo = new Vehiculos();
                    vehiculo.setIdVehiculo(rs.getInt("id"));
                    vehiculo.setNombreVehiculo(rs.getString("nombre_vehiculo"));
                    vehiculo.setAñoVehiculo(rs.getString("año"));
                    vehiculo.setPuertasVehiculo(rs.getInt("cantidad_puertas"));
                    vehiculo.setTransmision(rs.getString("transmision"));
                    vehiculo.setModelo(rs.getString("modelo"));
                    vehiculo.setKilometrajeVehiculo(rs.getInt("kilometraje"));
                    vehiculo.setAireAcondicionado(rs.getBoolean("aire_acondicionado"));
                    vehiculo.setImagenUrl(rs.getString("imagen_url"));
                    vehiculo.setCategoria(rs.getString("categoria_nombre"));
                    vehiculo.setMarcas(rs.getString("nombre_marca"));

                    // Establecer tarifas
                    Tarifas tarifas = new Tarifas();
                    tarifas.setSeguro_danios(rs.getFloat("seguro_danios"));
                    tarifas.setSeguro_vida(rs.getFloat("seguro_vida"));
                    tarifas.setSeguro_kilometraje(rs.getFloat("seguro_kilometraje"));
                    tarifas.setSeguro_combustible(rs.getFloat("combustible"));
                    tarifas.setSeguro_tarifa_por_dia(rs.getFloat("tarifa_por_dia"));
                    vehiculo.setTarifa(tarifas);

                    vehiculos.add(vehiculo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
	
	public ArrayList<Vehiculos> obtenerVehiculosPorMarca(String marca) {
        ArrayList<Vehiculos> vehiculos = new ArrayList<>();
        String sql = "SELECT v.id, v.nombre AS nombre_vehiculo, v.año, v.cantidad_puertas, v.transmision, v.modelo, v.kilometraje, v.aire_acondicionado, "
                   + "m.nombre AS nombre_marca, c.nombre AS categoria_nombre, i.url AS imagen_url, "
                   + "t.seguro_danios, t.seguro_vida, t.seguro_kilometraje, t.combustible, t.tarifa_por_dia "
                   + "FROM vehiculos v "
                   + "INNER JOIN marca m ON v.marca_id = m.id "
                   + "INNER JOIN categoria c ON v.categoria_id = c.id "
                   + "INNER JOIN tarifas t ON v.tarifas_id = t.id "
                   + "INNER JOIN imagenes i ON v.imagenes_id = i.id "
                   + "WHERE m.nombre = ?";
        try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, marca);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vehiculos vehiculo = new Vehiculos();
                    vehiculo.setIdVehiculo(rs.getInt("id"));
                    vehiculo.setNombreVehiculo(rs.getString("nombre_vehiculo"));
                    vehiculo.setAñoVehiculo(rs.getString("año"));
                    vehiculo.setPuertasVehiculo(rs.getInt("cantidad_puertas"));
                    vehiculo.setTransmision(rs.getString("transmision"));
                    vehiculo.setModelo(rs.getString("modelo"));
                    vehiculo.setKilometrajeVehiculo(rs.getInt("kilometraje"));
                    vehiculo.setAireAcondicionado(rs.getBoolean("aire_acondicionado"));
                    vehiculo.setImagenUrl(rs.getString("imagen_url"));
                    vehiculo.setCategoria(rs.getString("categoria_nombre"));
                    vehiculo.setMarcas(rs.getString("nombre_marca"));

                    // Establecer tarifas
                    Tarifas tarifas = new Tarifas();
                    tarifas.setSeguro_danios(rs.getFloat("seguro_danios"));
                    tarifas.setSeguro_vida(rs.getFloat("seguro_vida"));
                    tarifas.setSeguro_kilometraje(rs.getFloat("seguro_kilometraje"));
                    tarifas.setSeguro_combustible(rs.getFloat("combustible"));
                    tarifas.setSeguro_tarifa_por_dia(rs.getFloat("tarifa_por_dia"));
                    vehiculo.setTarifa(tarifas);

                    vehiculos.add(vehiculo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
	
	public Vehiculos obtenerVehiculoPorNombre(String nombreVehiculo) {
	    for (Vehiculos vehiculo : obtenerVehiculos()) {
	        if (vehiculo.getNombreVehiculo().equals(nombreVehiculo)) {
	            return vehiculo;
	        }
	    }
	    return null;
	}

	// Editar Vehiculos
	public boolean editarVehiculos(int idVehiculo, String nombre, String año, int cantidadPuertas, String transmision,
		    boolean aireAcondicionado, String modelo, String nombreCategoria, String nombreMarca,
		    float seguroDanios, float seguroVida, float seguroKilometraje, float combustible, float tarifaPorDia) {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		        return false;
		    }

		    String actualizarTarifaSql = "UPDATE tarifas SET seguro_danios = ?, seguro_vida = ?, seguro_kilometraje = ?, combustible = ?, tarifa_por_dia = ? WHERE id = (SELECT tarifas_id FROM vehiculos WHERE id = ?)";
		    String obtenerTarifaIdSql = "SELECT tarifas_id FROM vehiculos WHERE id = ?";
		    String actualizarVehiculoSql = "UPDATE vehiculos SET nombre = ?, año = ?, cantidad_puertas = ?, transmision = ?, aire_acondicionado = ?, modelo = ?, categoria_id = (SELECT id FROM categoria WHERE nombre = ?), marca_id = (SELECT id FROM marca WHERE nombre = ?) WHERE id = ?";

		    try (Connection con = DriverManager.getConnection(
		            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
		            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI")) {

		        con.setAutoCommit(false);


		        try (PreparedStatement actualizarTarifaStmt = con.prepareStatement(actualizarTarifaSql)) {
		            actualizarTarifaStmt.setFloat(1, seguroDanios);
		            actualizarTarifaStmt.setFloat(2, seguroVida);
		            actualizarTarifaStmt.setFloat(3, seguroKilometraje);
		            actualizarTarifaStmt.setFloat(4, combustible);
		            actualizarTarifaStmt.setFloat(5, tarifaPorDia);
		            actualizarTarifaStmt.setInt(6, idVehiculo);
		            actualizarTarifaStmt.executeUpdate();
		        }

		        int tarifaId;
		        try (PreparedStatement obtenerTarifaIdStmt = con.prepareStatement(obtenerTarifaIdSql)) {
		            obtenerTarifaIdStmt.setInt(1, idVehiculo);
		            try (ResultSet rs = obtenerTarifaIdStmt.executeQuery()) {
		                if (rs.next()) {
		                    tarifaId = rs.getInt("tarifas_id");
		                } else {
		                    throw new SQLException("No se pudo obtener el ID de la tarifa");
		                }
		            }
		        }

		        try (PreparedStatement stmt = con.prepareStatement(actualizarVehiculoSql)) {
		            stmt.setString(1, nombre);
		            stmt.setString(2, año);
		            stmt.setInt(3, cantidadPuertas);
		            stmt.setString(4, transmision);
		            stmt.setBoolean(5, aireAcondicionado);
		            stmt.setString(6, modelo);
		            stmt.setString(7, nombreCategoria);
		            stmt.setString(8, nombreMarca);
		            stmt.setInt(9, idVehiculo);

		            int filasAfectadas = stmt.executeUpdate();
		            con.commit();
		            return filasAfectadas > 0;

		        } catch (SQLException e) {
		            con.rollback();
		            e.printStackTrace();
		            return false;
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	
	
// 	Eliminar Vehiculo
    public boolean eliminarVehiculo(int idVehiculo) throws RentasAsociadasException{
		if (tieneRentasAsociadas(idVehiculo)) {
			throw new RentasAsociadasException("No se puede eliminar el vehículo \nporque tiene rentas asociadas.");
		}
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        String eliminarVehiculoSql = "DELETE FROM vehiculos WHERE id = ?";

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
                "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI")) {

            con.setAutoCommit(false);

            try (PreparedStatement eliminarVehiculoStmt = con.prepareStatement(eliminarVehiculoSql)) {
                eliminarVehiculoStmt.setInt(1, idVehiculo);
                int filasAfectadas = eliminarVehiculoStmt.executeUpdate();
                con.commit();
                return filasAfectadas > 0;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
    public boolean tieneRentasAsociadas(int idVehiculo) {
	    String sql = "SELECT COUNT(*) AS count FROM rentas WHERE vehiculo_id = ?";
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    }

	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setInt(1, idVehiculo);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next() && rs.getInt("count") > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

    public boolean tieneRentasAsociadasCliente(int idUsuario) {
        String sql = "SELECT COUNT(*) AS count FROM rentas WHERE usuario_id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
                "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt("count") > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
//	Añadir vehiculos
    public boolean aniadirVehiculo(String nombre, String año, int cantidadPuertas, int kilometraje, String transmision,
            boolean aireAcondicionado, String modelo, String nombreCategoria, String nombreMarca, String urlImagen,
            float seguroDanios, float seguroVida, float seguroKilometraje, float combustible, float tarifaPorDia) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        if (urlImagen == null || urlImagen.isEmpty()) {
            urlImagen = "https://firebasestorage.googleapis.com/v0/b/fotinhoscarros.appspot.com/o/bugatata.png?alt=media&token=41feddc5-379f-429e-9040-16d7cd4bb739";
        }

        String verificarImagenSql = "INSERT INTO imagenes (url) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM imagenes WHERE url = ?)";
        String obtenerImagenIdSql = "SELECT id FROM imagenes WHERE url = ?";
        String insertarTarifaSql = "INSERT INTO tarifas (seguro_danios, seguro_vida, seguro_kilometraje, combustible, tarifa_por_dia) VALUES (?, ?, ?, ?, ?)";
        String obtenerCategoriaIdSql = "SELECT id FROM categoria WHERE nombre = ?";
        String obtenerMarcaIdSql = "SELECT id FROM marca WHERE nombre = ?";
        String insertarVehiculoSql = "INSERT INTO vehiculos (nombre, año, cantidad_puertas, kilometraje, transmision, aire_acondicionado, modelo, categoria_id, marca_id, tarifas_id, imagenes_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
                "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI")) {

            con.setAutoCommit(false);

            int imagenId;
            try (PreparedStatement verificarImagenStmt = con.prepareStatement(verificarImagenSql)) {
                verificarImagenStmt.setString(1, urlImagen);
                verificarImagenStmt.setString(2, urlImagen);
                verificarImagenStmt.executeUpdate();
            }

            try (PreparedStatement obtenerImagenIdStmt = con.prepareStatement(obtenerImagenIdSql)) {
                obtenerImagenIdStmt.setString(1, urlImagen);
                try (ResultSet rs = obtenerImagenIdStmt.executeQuery()) {
                    if (rs.next()) {
                        imagenId = rs.getInt("id");
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la imagen");
                    }
                }
            }

            int tarifaId;
            try (PreparedStatement insertarTarifaStmt = con.prepareStatement(insertarTarifaSql, Statement.RETURN_GENERATED_KEYS)) {
                insertarTarifaStmt.setFloat(1, seguroDanios);
                insertarTarifaStmt.setFloat(2, seguroVida);
                insertarTarifaStmt.setFloat(3, seguroKilometraje);
                insertarTarifaStmt.setFloat(4, combustible);
                insertarTarifaStmt.setFloat(5, tarifaPorDia);
                insertarTarifaStmt.executeUpdate();

                try (ResultSet rs = insertarTarifaStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        tarifaId = rs.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la tarifa");
                    }
                }
            }

            int categoriaId;
            try (PreparedStatement obtenerCategoriaIdStmt = con.prepareStatement(obtenerCategoriaIdSql)) {
                obtenerCategoriaIdStmt.setString(1, nombreCategoria);
                try (ResultSet rs = obtenerCategoriaIdStmt.executeQuery()) {
                    if (rs.next()) {
                        categoriaId = rs.getInt("id");
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la categoría");
                    }
                }
            }

            int marcaId;
            try (PreparedStatement obtenerMarcaIdStmt = con.prepareStatement(obtenerMarcaIdSql)) {
                obtenerMarcaIdStmt.setString(1, nombreMarca);
                try (ResultSet rs = obtenerMarcaIdStmt.executeQuery()) {
                    if (rs.next()) {
                        marcaId = rs.getInt("id");
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la marca");
                    }
                }
            }

            try (PreparedStatement stmt = con.prepareStatement(insertarVehiculoSql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, año);
                stmt.setInt(3, cantidadPuertas);
                stmt.setInt(4, kilometraje);
                stmt.setString(5, transmision);
                stmt.setBoolean(6, aireAcondicionado);
                stmt.setString(7, modelo);
                stmt.setInt(8, categoriaId);
                stmt.setInt(9, marcaId);
                stmt.setInt(10, tarifaId);
                stmt.setInt(11, imagenId);

                int filasAfectadas = stmt.executeUpdate();
                con.commit();
                return filasAfectadas > 0;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
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

	public boolean eliminarUsuario(int idUsuario) throws RentasAsociadasException {
		if (tieneRentasAsociadasCliente(idUsuario)) {
	        throw new RentasAsociadasException("No se puede eliminar el cliente \nporque tiene rentas asociadas.");
	    }
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "DELETE FROM usuarios WHERE id = ?";
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI"); PreparedStatement stmt = con.prepareStatement(sql)) {

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
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI"); PreparedStatement stmt = con.prepareStatement(sql)) {
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
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			final byte[] hash = digest.digest(contrasenia.getBytes("UTF-8"));
			final StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				final String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
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
// Metodo Editar renta
	public boolean editarRenta(int idRenta, String fechaFinal, String fechaInicial, 
	        Double costo, String usuarioNombre, String nombreVehiculo, int vehiculoId) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    }

	    int usuarioId = obtenerUsuarioIdPorNombre(usuarioNombre);
	    int idDelVehiculo = obtenerVehiculoIdPorNombre(nombreVehiculo);
	    
	    if (usuarioId == -1) {
	        System.out.println("Error: Usuario no encontrado.");
	        return false;
	    }
	    
	    if (idDelVehiculo == -1) {
	        System.out.println("Error: Vehiculo no encontrado.");
	        return false;
	    }

	    String actualizarRentaSql = "UPDATE rentas SET fecha_inicial = ?, fecha_final = ?, costo = ?, usuario_id = ?, vehiculo_id = ? WHERE id = ?";

	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI")) {

	        con.setAutoCommit(false);

	        // Actualizar la renta
	        try (PreparedStatement stmt = con.prepareStatement(actualizarRentaSql)) {
	            stmt.setString(1, fechaInicial);
	            stmt.setString(2, fechaFinal);
	            stmt.setDouble(3, costo);
	            stmt.setInt(4, usuarioId);
	            stmt.setInt(5, idDelVehiculo);
	            stmt.setInt(6, idRenta);

	            int filasAfectadas = stmt.executeUpdate();
	            if (filasAfectadas > 0) {
	                con.commit();
	                return true;
	            } else {
	                con.rollback();
	                return false;
	            }

	        } catch (SQLException e) {
	            con.rollback();
	            e.printStackTrace();
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	
	// Metódo añadir rentas
	public boolean aniadirRentas(String fechaFinal, String fechaInicial, Double costo, String usuarioNombre, int vehiculoId) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    }

	    int usuarioId = obtenerUsuarioIdPorNombre(usuarioNombre);

	    if (usuarioId == -1) {
	        System.out.println("No se encontró un usuario con el nombre proporcionado.");
	        return false;
	    }

	    String verificarDisponibilidadSql = "SELECT COUNT(*) FROM rentas WHERE vehiculo_id = ? AND " +
	                                        "(fecha_inicial <= ? AND fecha_final >= ?)";
	    String insertarRentaSql = "INSERT INTO rentas(fecha_inicial, fecha_final, costo, usuario_id, vehiculo_id) VALUES (?, ?, ?, ?, ?)";

	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI")) {

	        con.setAutoCommit(false);

	        try (PreparedStatement verificarStmt = con.prepareStatement(verificarDisponibilidadSql)) {
	            verificarStmt.setInt(1, vehiculoId);
	            verificarStmt.setString(2, fechaFinal);
	            verificarStmt.setString(3, fechaInicial);

	            try (ResultSet rs = verificarStmt.executeQuery()) {
	                if (rs.next() && rs.getInt(1) > 0) {
	                    System.out.println("El vehículo ya está reservado en estas fechas.");
	                    con.rollback();
	                    return false;
	                }
	            }
	        }

// Insertar la renta si el vehículo está disponible
	        try (PreparedStatement stmt = con.prepareStatement(insertarRentaSql)) {
	            stmt.setString(1, fechaInicial);
	            stmt.setString(2, fechaFinal);
	            stmt.setDouble(3, costo);
	            stmt.setInt(4, usuarioId);
	            stmt.setInt(5, vehiculoId);

	            int filasAfectadas = stmt.executeUpdate();
	            con.commit();
	            return filasAfectadas > 0;

	        } catch (SQLException e) {
	            con.rollback();
	            e.printStackTrace();
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public int obtenerUsuarioIdPorNombre(String nombreUsuario) {
	    int usuarioId = -1;

	    String sql = "SELECT id FROM usuarios WHERE nombre = ?";
	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, nombreUsuario);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            usuarioId = rs.getInt("id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return usuarioId;
	}
	
	public int obtenerVehiculoIdPorNombre(String nombreVehiculo) {
	    String sql = "SELECT id FROM vehiculos WHERE nombre = ?";
	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, nombreVehiculo);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("id");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1; // Retorna -1 si no se encuentra el vehículo
	}
	
	public ArrayList<Rentas> obtenerRentasPorUsuario(int usuarioId) {
	    ArrayList<Rentas> rentas = new ArrayList<>();

	    String sql = "SELECT r.*, v.*, u.* FROM rentas r "
	               + "JOIN vehiculos v ON r.vehiculo_id = v.id "
	               + "JOIN usuarios u ON r.usuario_id = u.id "
	               + "WHERE r.usuario_id = ?";

	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setInt(1, usuarioId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Rentas renta = new Rentas();
	            Usuarios usuario = new Usuarios();
	            Vehiculos vehiculo = new Vehiculos();

	            // Llenar datos de la renta
	            renta.setId(rs.getInt("r.id"));
	            renta.setFecha_inicial(rs.getString("r.fecha_inicial"));
	            renta.setFecha_final(rs.getString("r.fecha_final"));
	            renta.setCosto(rs.getDouble("r.costo"));

	            // Llenar datos del usuario
	            usuario.setIdUsuario(rs.getInt("u.id"));
	            usuario.setNombreUsuario(rs.getString("u.nombre"));
	            usuario.setApellido(rs.getString("u.prim_apellido"));
	            usuario.setCorreo(rs.getString("u.correo_electronico"));
	            renta.setUsuario(usuario);

	            // Llenar datos del vehículo
	            vehiculo.setIdVehiculo(rs.getInt("v.id"));
	            vehiculo.setNombreVehiculo(rs.getString("v.nombre"));
	            vehiculo.setAñoVehiculo(rs.getString("v.año"));
	            vehiculo.setPuertasVehiculo(rs.getInt("v.cantidad_puertas"));
	            vehiculo.setTransmision(rs.getString("v.transmision"));
	            vehiculo.setModelo(rs.getString("v.modelo"));
	            vehiculo.setKilometrajeVehiculo(rs.getInt("v.kilometraje"));
	            vehiculo.setAireAcondicionado(rs.getBoolean("v.aire_acondicionado"));
	            renta.setVehiculo(vehiculo);

	            rentas.add(renta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return rentas;
	}
	
	public ArrayList<Rentas> mostrarRentas() {
	    ArrayList<Rentas> rentas = new ArrayList<>();

	    String sql = "SELECT r.id, r.fecha_inicial, r.fecha_final, r.costo, " +
	                 "u.id AS usuario_id, u.nombre AS usuario_nombre, u.prim_apellido AS usuario_apellido, u.correo_electronico AS usuario_correo, " +
	                 "v.id AS vehiculo_id, v.nombre AS vehiculo_nombre, v.año, v.cantidad_puertas, v.transmision, v.modelo, v.kilometraje, v.aire_acondicionado, i.url AS imagen_url, " +
	                 "t.seguro_danios, t.seguro_vida, t.seguro_kilometraje, t.combustible, t.tarifa_por_dia " +
	                 "FROM rentas r " +
	                 "JOIN usuarios u ON r.usuario_id = u.id " +
	                 "JOIN vehiculos v ON r.vehiculo_id = v.id " +
	                 "JOIN imagenes i ON v.imagenes_id = i.id " +
	                 "JOIN tarifas t ON v.tarifas_id = t.id";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Rentas renta = new Rentas();
	            renta.setId(rs.getInt("id"));
	            renta.setFecha_inicial(rs.getString("fecha_inicial"));
	            renta.setFecha_final(rs.getString("fecha_final"));
	            renta.setCosto(rs.getDouble("costo"));

	            // Crear objeto Usuario
	            Usuarios usuario = new Usuarios();
	            usuario.setIdUsuario(rs.getInt("usuario_id"));
	            usuario.setNombreUsuario(rs.getString("usuario_nombre"));
	            usuario.setApellido(rs.getString("usuario_apellido"));
	            usuario.setCorreo(rs.getString("usuario_correo"));
	            renta.setUsuario(usuario);

	            // Crear objeto Vehiculo
	            Vehiculos vehiculo = new Vehiculos();
	            vehiculo.setIdVehiculo(rs.getInt("vehiculo_id"));
	            vehiculo.setNombreVehiculo(rs.getString("vehiculo_nombre"));
	            vehiculo.setAñoVehiculo(rs.getString("año"));
	            vehiculo.setPuertasVehiculo(rs.getInt("cantidad_puertas"));
	            vehiculo.setTransmision(rs.getString("transmision"));
	            vehiculo.setModelo(rs.getString("modelo"));
	            vehiculo.setKilometrajeVehiculo(rs.getInt("kilometraje"));
	            vehiculo.setAireAcondicionado(rs.getBoolean("aire_acondicionado"));
	            vehiculo.setImagenUrl(rs.getString("imagen_url"));

	            // Establecer tarifas
	            Tarifas tarifas = new Tarifas();
	            tarifas.setSeguro_danios(rs.getFloat("seguro_danios"));
	            tarifas.setSeguro_vida(rs.getFloat("seguro_vida"));
	            tarifas.setSeguro_kilometraje(rs.getFloat("seguro_kilometraje"));
	            tarifas.setSeguro_combustible(rs.getFloat("combustible"));
	            tarifas.setSeguro_tarifa_por_dia(rs.getFloat("tarifa_por_dia"));
	            vehiculo.setTarifa(tarifas);

	            renta.setVehiculo(vehiculo);
	            rentas.add(renta);
	        }
	        con.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return rentas;
	}

	// Metodo Eliminar rentas
	public boolean eliminarRenta(int idRenta) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "DELETE FROM rentas WHERE id = ?";
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI"); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, idRenta);
			int filasAfectadas = stmt.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Rentas> obtenerRentasPorVehiculo(int idVehiculo) {
	    ArrayList<Rentas> rentas = new ArrayList<>();

	    String sql = "SELECT r.*, u.id AS usuario_id, u.nombre AS usuario_nombre, u.prim_apellido AS usuario_apellido, u.correo_electronico AS usuario_correo, "
	               + "v.id AS vehiculo_id, v.nombre AS vehiculo_nombre, v.año, v.cantidad_puertas, v.transmision, v.modelo, v.kilometraje, v.aire_acondicionado, i.url AS imagen_url "
	               + "FROM rentas r "
	               + "JOIN usuarios u ON r.usuario_id = u.id "
	               + "JOIN vehiculos v ON r.vehiculo_id = v.id "
	               + "JOIN imagenes i ON v.imagenes_id = i.id "
	               + "WHERE v.id = ?";

	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI"); 
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setInt(1, idVehiculo);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Rentas renta = new Rentas();

	            // Llenar datos de la renta
	            renta.setId(rs.getInt("r.id"));
	            renta.setFecha_inicial(rs.getString("r.fecha_inicial"));
	            renta.setFecha_final(rs.getString("r.fecha_final"));
	            renta.setCosto(rs.getDouble("r.costo"));

	            // Llenar datos del usuario
	            Usuarios usuario = new Usuarios();
	            usuario.setIdUsuario(rs.getInt("usuario_id"));
	            usuario.setNombreUsuario(rs.getString("usuario_nombre"));
	            usuario.setApellido(rs.getString("usuario_apellido"));
	            usuario.setCorreo(rs.getString("usuario_correo"));
	            renta.setUsuario(usuario);

	            // Llenar datos del vehículo
	            Vehiculos vehiculo = new Vehiculos();
	            vehiculo.setIdVehiculo(rs.getInt("vehiculo_id"));
	            vehiculo.setNombreVehiculo(rs.getString("vehiculo_nombre"));
	            vehiculo.setAñoVehiculo(rs.getString("v.año"));
	            vehiculo.setPuertasVehiculo(rs.getInt("v.cantidad_puertas"));
	            vehiculo.setTransmision(rs.getString("v.transmision"));
	            vehiculo.setModelo(rs.getString("v.modelo"));
	            vehiculo.setKilometrajeVehiculo(rs.getInt("v.kilometraje"));
	            vehiculo.setAireAcondicionado(rs.getBoolean("v.aire_acondicionado"));
	            vehiculo.setImagenUrl(rs.getString("imagen_url"));
	            renta.setVehiculo(vehiculo);

	            rentas.add(renta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return rentas;
	}
	

	// metodo mostrarcategorias
	public ArrayList<Categorias> mostrarCategorias() {
		ArrayList<Categorias> categorias = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT * FROM categoria";
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");) {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Categorias categoria = new Categorias();
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				categorias.add(categoria);

			}
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return categorias;
	}

	// Meotodo para añadir categorias
	public boolean aniadirCategorias(String nombreCategoria) {

		String sql = "INSERT INTO categoria(nombre) VALUES (?)";

		// boolean rentaEncontrada;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");) {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, nombreCategoria);

			int filasAfectadas = stmt.executeUpdate();
			con.close();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean editarCategorias(int idCategoria, String nuevoNombreCategoria) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    String sql = "UPDATE categoria SET nombre = ? WHERE id = ?";
	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI"); 
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, nuevoNombreCategoria);
	        stmt.setInt(2, idCategoria);

	        int filasAfectadas = stmt.executeUpdate();
	        System.out.println("Editar categoria");
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public int obtenerIdCategoriaPorNombre(String nombreCategoria) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    String sql = "SELECT id FROM categoria WHERE nombre = ?";
	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, nombreCategoria);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("id");
	        } else {
	            return -1; 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    }
	}
	
	public int obtenerIdMarcaPorNombre(String nombreMarca) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    String sql = "SELECT id FROM marca WHERE nombre = ?";
	    try (Connection con = DriverManager.getConnection(
	            "jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
	            "AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
	         PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, nombreMarca);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("id");
	        } else {
	            return -1; 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    }
	}

	// metodo eliminar catego
	public boolean eliminarCategorias(String nombreCategoria) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sqlCategoria = "DELETE FROM categoria Where nombre = ?";
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
				PreparedStatement stmtCategoria = con.prepareStatement(sqlCategoria)) {
			stmtCategoria.setString(1, nombreCategoria);
			int filasAfectadas = stmtCategoria.executeUpdate();
//			System.out.println("Eliminado Cat");
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// mostrar marcas
	public ArrayList<Marcas> mostrarMarcas() {
		ArrayList<Marcas> marcas = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT * FROM marca";
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");) {
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Marcas marca = new Marcas();
				marca.setId(rs.getInt("id"));
				marca.setNombre(rs.getString("nombre"));
				marcas.add(marca);

			}
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return marcas;
	}

// añadir Marca
	public boolean aniadirMarcas(String nombreMarca) {

		String sql = "INSERT INTO marca(nombre) VALUES (?)";

		// boolean rentaEncontrada;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");) {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, nombreMarca);
			System.out.println("");

			int filasAfectadas = stmt.executeUpdate();
			con.close();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// Metodo para eliminar marcas
	public boolean eliminarMarcas(String nombreMarca) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sqlCategoria = "DELETE FROM marca WHERE nombre = ?";
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI");
				PreparedStatement stmtCategoria = con.prepareStatement(sqlCategoria)) {
			stmtCategoria.setString(1, nombreMarca);
			int filasAfectadas = stmtCategoria.executeUpdate();
//			System.out.println("Marca Eliminada");
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// editar marca
	public boolean editarMarcas(int idMarcas, String nombreMarcas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "UPDATE marca SET nombre = ? WHERE id = ?";
		try (Connection con = DriverManager.getConnection(
				"jdbc:mysql://monorail.proxy.rlwy.net:28289/railway?useSSL=false", "root",
				"AZsyCwUGzmURenQkgkEOksyBwsWuQBFI"); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, nombreMarcas);
			stmt.setInt(2, idMarcas);

			int filasAfectadas = stmt.executeUpdate();
			System.out.println("Editar marca");
			return filasAfectadas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public class RentasAsociadasException extends Exception {
	    public RentasAsociadasException(String message) {
	        super(message);
	    }
	}
	
}
