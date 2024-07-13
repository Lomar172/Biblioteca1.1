package com.autozone.database.dao;

import java.sql.SQLException;
import org.junit.Test;
import com.autozone.enums.LibroOperacion;
import com.autozone.models.Prestamos;

public class PrestamosDAOTest {

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, SQLException {
		PrestamosDAO prestamo = new PrestamosDAO();
		java.util.Date fechaJava = new java.util.Date();
		java.sql.Date fechaSQL = new java.sql.Date(fechaJava.getTime());
		Prestamos p = new Prestamos(1, 4, fechaSQL, LibroOperacion.PRESTAMO.toString());
		prestamo.insertarPrestamo(p);
	}

}
