package com.autozone.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.autozone.database.DatabaseConnection;
import com.autozone.enums.LibroStatus;
import com.autozone.models.Prestamos;
import com.autozone.utils.Validador;

/**
 * En esta clase se realizan todas las interacciones entre el usuario y la tabla de prestamos en la base de datos.
 */

public class PrestamosDAO {
	
	public void insertarPrestamo(Prestamos prestamos) throws SQLException, IllegalArgumentException, IllegalAccessException{
		//Aqui se manda a validar el objeto de libro.
		Validador.vaildar(prestamos);
		//Aqui se define el query que queremos ejecutar.
		String sql = "INSERT INTO Prestamos (ISBN, ID, Fecha, Libro_Operacion) VALUES (?,?,?,?);";
		//Aqui se ejecuta la conexion con la base de datos, se sustituyen los '?' por los valores deseados y se ejecuta el query.
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, prestamos.getIsbn());
			pstmt.setInt(2, prestamos.getId());
			pstmt.setDate(3, prestamos.getFecha());
			pstmt.setString(4, prestamos.getLibro_operacion().toString());
			pstmt.executeUpdate();
			LibroDAO libro = new LibroDAO();
			//Aqui estamos validando la disponibilidad de un libro, dependiendo del estatus, se tomara una accion u otra.
			switch (prestamos.getLibro_operacion().toString()) {		
			case "PRESTAMO": {
				libro.actualizarLibroDisp(prestamos.getIsbn(), LibroStatus.DISPONIBLE);
				break;
			}
			case "DEVOLUCION": {
				libro.actualizarLibroDisp(prestamos.getIsbn(), LibroStatus.PRESTADO);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + prestamos.getLibro_operacion().toString());
			}
		}
	}
	
	public List<Prestamos> obtenerPrestamos(int id) throws SQLException {
		
		String sql = "SELECT * FROM Prestamos WHERE ID = "+id+";";
		List<Prestamos> prestamos = new ArrayList<Prestamos>();
		
		Prestamos prestamo = null;
		
		try(Connection conn = DatabaseConnection.getInstance().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			//Aqui estamos repasando los registros que nos muestra el query de este metodo y lo guardamos en una lista. 
			while(rs.next()){
				prestamo = new Prestamos(
						rs.getInt("ISBN"),
						rs.getInt("Id"),
						rs.getDate("Fecha"),
						rs.getString("Libro_Operacion")
				);
				
				prestamo.setFolio(rs.getInt("Folio"));
				prestamos.add(prestamo);
			}
		}
		return prestamos;
	}
}
