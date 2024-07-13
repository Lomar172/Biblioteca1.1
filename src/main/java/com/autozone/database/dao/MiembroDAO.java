package com.autozone.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.autozone.database.DatabaseConnection;
import com.autozone.models.Miembros;
import com.autozone.utils.Validador;

/**
 * En esta clase se realizan todas las interacciones entre el usuario y la tabla de miembros en la base de datos.
 */

public class MiembroDAO {
	public void insertarMiembro(Miembros miembros) throws SQLException, IllegalArgumentException, IllegalAccessException{
		//Aqui se manda a validar el objeto de miembro.
		Validador.vaildar(miembros);
		//Aqui se define el query que queremos ejecutar.
		String sql = "INSERT INTO Miembros (Nombre, Telefono) VALUES (?,?);";
		//Aqui se ejecuta la conexion con la base de datos, se sustituyen los '?' por los valores deseados y se ejecuta el query.
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, miembros.getNombre());
			pstmt.setString(2, miembros.getTelefono());
			
			pstmt.executeUpdate();
		}
	}
	
	public void actualizarMiembro(int id,Miembros miembros) throws SQLException, IllegalArgumentException, IllegalAccessException {
		Validador.vaildar(miembros);
		String sql = "UPDATE Miembros SET Nombre = ?, Telefono = ? WHERE ID = "+id+";";
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, miembros.getNombre());
			pstmt.setString(2, miembros.getTelefono());
			pstmt.executeUpdate();
		}
	}
	
	public void eliminarMiembro(int idMiembro) throws SQLException {
		
		String sql = "DELETE FROM Miembros WHERE ID = ?;";
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idMiembro);
			
			pstmt.executeUpdate();
		}
	}
	
	public List<Miembros> obtenerMiembrosPorNombre(String nombre) throws SQLException {
		String sql = "SELECT * FROM Miembros WHERE Nombre = '"+nombre+"';";
		List<Miembros> miembros = new ArrayList<Miembros>();
		Miembros miembro = null;
		//Aqui estamos repasando los registros que nos muestra el query de este metodo y lo guardamos en una lista.
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()){
				miembro = new Miembros(rs.getString("Nombre"), rs.getString("Telefono"));
				miembro.setId(rs.getInt("ID"));
				miembros.add(miembro);
			}
		}
		return miembros;
	}

	public List<Miembros> obtenerMiembrosPorID(int id) throws SQLException {
		String sql = "SELECT * FROM Miembros WHERE ID = '"+id+"';";
		List<Miembros> miembros = new ArrayList<Miembros>();
		Miembros miembro = null;
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()){
				miembro = new Miembros(rs.getString("Nombre"), rs.getString("Telefono"));
				miembro.setId(rs.getInt("ID"));
				miembros.add(miembro);
			}
		}
		return miembros;
	}
}
