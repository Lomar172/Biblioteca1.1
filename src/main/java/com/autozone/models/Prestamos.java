package com.autozone.models;

import java.sql.Date;

import com.autozone.enums.LibroOperacion;

public class Prestamos {
	
	/**En esta clase se declaran las variables, se aplican anotaciones
	 *se crean metodos getter y setters y se crea un metodo constructor para
	 *usarse como base a la hora de crear un objeto de esta clase.
	 */
	
	private int folio;
	private int lib_id;
	private int id;
	private Date fecha;
	private String libro_operacion;
	
	public Prestamos(int lib_id, int id, Date fecha, String libro_operacion) {
		super();
		this.lib_id = lib_id;
		this.id = id;
		this.fecha = fecha;
		this.libro_operacion = libro_operacion;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getLibro_operacion() {
		return libro_operacion.toString();
	}
	public void setLibro_operacion(LibroOperacion libro_operacion) {
		this.libro_operacion = libro_operacion.toString();
	}
	
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	
	public int getLib_id() {
		return lib_id;
	}
	public void setLib_id(int lib_id) {
		this.lib_id = lib_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
