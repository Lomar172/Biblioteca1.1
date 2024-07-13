package com.autozone.database.dao;

import java.sql.SQLException;

import org.junit.Test;

import com.autozone.enums.LibroStatus;
import com.autozone.models.Libros;

public class LibroDAOTest {

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, SQLException {
		LibroDAO libro = new LibroDAO();
		Libros l = new Libros("35431348431531", "El señor de los anillos: Las Dos Torres", "J.R.R. Tolkien", LibroStatus.DISPONIBLE.toString());
		libro.insertarLibro(l);
	}

}
