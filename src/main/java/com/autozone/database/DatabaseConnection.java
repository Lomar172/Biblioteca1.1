package com.autozone.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	//En esta clase se declaran el usuario, contraseña y localizacion de la base de datos para poder hacer una conexion a la misma.
	
	private static DatabaseConnection instance;
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/DB_BIBLIOTECA";
	private String username = "lomar1";
	private String password = "BBno$1724";
	
	private DatabaseConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public static DatabaseConnection getInstance() throws SQLException{
		try {
			if (instance == null) {
				instance = new DatabaseConnection();
			} else if (instance.getConnection().isClosed()){
				instance = new DatabaseConnection();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
}
