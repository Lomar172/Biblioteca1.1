package com.autozone.database.dao;

import java.sql.SQLException;

import org.junit.Test;


import com.autozone.models.Miembros;

public class MiembroDAOTest {

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, SQLException {
		MiembroDAO miembro = new MiembroDAO();
		Miembros m = new Miembros("Alvaro Talavera", "6394567192");
		miembro.insertarMiembro(m);;
	}

}
