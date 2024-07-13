package com.autozone.principal;

import java.util.List;
import java.util.Scanner;

import com.autozone.database.dao.LibroDAO;
import com.autozone.database.dao.MiembroDAO;
import com.autozone.database.dao.PrestamosDAO;
import com.autozone.enums.LibroOperacion;
import com.autozone.enums.LibroStatus;
import com.autozone.models.Libros;
import com.autozone.models.Miembros;
import com.autozone.models.Prestamos;

public class Principal {
	public static void main(String[] args) {

		/**
		 * En esta clase radica la interfaz con la que interactua el usuario.
		 * Principalmente sirve para leer las entradas que escribe el usuario, pero
		 * tambien puede tener validaciones y ciclos para una experiencia mas comoda.
		 */

		System.out.println("Bienvenido!");
		System.out.println("");
		mainMenu();
	}

	public static void mainMenu() {
		try (Scanner scn = new Scanner(System.in)) {
			System.out.println("A que menu quieres acceder?");
			System.out.println("1. Libros");
			System.out.println("2. Miembros");
			System.out.println("3. Presatmos");
			// Un ciclo switch para escoger entre las opciones.
			int welcomeMenuChoice = scn.nextInt();
			switch (welcomeMenuChoice) {
			case 1: {
				librosMenu();
				break;
			}
			case 2: {
				miembrosMenu();
				break;
			}
			case 3: {
				prestamosMenu();
				break;
			}
			default:
				throw new IllegalArgumentException("Valor invalido: " + welcomeMenuChoice);
			}
		} catch (Exception e) {
			mainMenu();
		}
	}

	public static void librosMenu() {
		try (Scanner scn = new Scanner(System.in)) {
			System.out.println("1. Buscar libros");
			System.out.println("2. Agregar libro");
			System.out.println("3. Actualizar libro");
			System.out.println("4. Eliminar libro");
			int lMenuOpcion = scn.nextInt();
			switch (lMenuOpcion) {
			case 1: {
				buscarLib();
				break;
			}
			case 2: {
				agregLib();
				break;
			}
			case 3: {
				actualizarLib();
				break;
			}
			case 4: {
				eliminarlib();
				break;
			}
			default:
				throw new IllegalArgumentException("Valor Invalido: " + lMenuOpcion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void actualizarLib() {
		LibroDAO libroDAO = new LibroDAO();
		try (Scanner scanner = new Scanner(System.in); Scanner scanner1 = new Scanner(System.in)) {
			// Se declaran las variables a usar y se guardan las respuestas del usuario en
			// estas.
			String titulo, autor, isbn;
			int lib_id;
			System.out.println("Cual es el ID del libro que quiere actualizar?");
			lib_id = scanner1.nextInt();
			/**
			 * Luego se crea una lista donde se almacena informacion de un libro en
			 * especifico Esto se hizo para poder tomar la disponibilidad de un libro en
			 * especifico y poder usar este status para actualizar un registro en la base de
			 * datos.
			 */
			List<Libros> libros = libroDAO.obtenerEstatusDeLibro(lib_id);
			if (!libros.isEmpty()) {
				for (Libros libro : libros) {
					System.out.println("Nuevo ISBN del libro:");
					isbn = scanner.nextLine();
					System.out.println("Nuevo titulo del libro:");
					titulo = scanner.nextLine();
					System.out.println("Nuevo autor del libro:");
					autor = scanner.nextLine();
					Libros libro1 = new Libros(isbn, titulo, autor, libro.getDisponibilidad());
					libroDAO.actualizarLibro(lib_id, libro1);
					System.out.println("Libro actualizado con exito!");
				}
			} else {
				System.out.println("Ese ID no se encontro en la base de datos.");
				System.out.println("");
				menuRecursivo();
			}
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eliminarlib() {
		LibroDAO libroDAO = new LibroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			String isbn;
			System.out.println("ISBN del libro a eliminar:");
			isbn = scannerAgregar.nextLine();
			libroDAO.eliminarLibro(isbn);
			System.out.println("Libro eliminado");
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void buscarLib() {
		try (Scanner scn = new Scanner(System.in)) {
			System.out.println("1. Buscar por titulo");
			System.out.println("2. Buscar por Autor");
			System.out.println("3. Buscar por ISBN");
			int lMenuOpcion = scn.nextInt();
			switch (lMenuOpcion) {
			case 1: {
				buscarPorTitulo();
				break;
			}
			case 2: {
				buscarPorAutor();
				break;
			}
			case 3: {
				buscarPorISBN();
				break;
			}
			default:
				buscarLib();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void buscarPorISBN() {
		LibroDAO libroDAO = new LibroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("ISBN del libro:");
			String isbn = scannerAgregar.nextLine();
			List<Libros> libros = libroDAO.obtenerLibrosPorIsbn(isbn);
			if(!libros.isEmpty()) {
				// Aqui se leen los datos de una lista y se les da un poco de formato para
				// presentarse al usuario
				System.out.println("|     ISBN      | TITULO | AUTOR |");
				for (Libros libro : libros) {
					System.out
							.println("| " + libro.getIsbn() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " |");
				}
			} else {
				System.out.println("No se ha encontrado a ningun libro con ese ID");
				System.out.println("Intentelo de nuevo.");
				System.out.println("");
				menuRecursivo();
			}
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void buscarPorAutor() {
		LibroDAO libroDAO = new LibroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("Autor del libro:");
			String autor = scannerAgregar.nextLine();
			List<Libros> libros = libroDAO.obtenerLibrosPorAutor(autor);
			if(!libros.isEmpty()) {
				System.out.println("|      ISBN     | TITULO | AUTOR |");
				for (Libros libro : libros) {
					System.out
							.println("| " + libro.getIsbn() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " |");
				}
			} else {
				System.out.println("No se ha encontrado a ningun miembro con ese ID");
				System.out.println("Intentelo de nuevo.");
				System.out.println("");
				menuRecursivo();
			}
			
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void buscarPorTitulo() {
		LibroDAO libroDAO = new LibroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("Titulo del libro:");
			String titulo = scannerAgregar.nextLine();
			List<Libros> libros = libroDAO.obtenerLibrosPorTitulo(titulo);
			if(!libros.isEmpty()) {
				System.out.println("|       ISBN        | TITULO | AUTOR |");
				for (Libros libro : libros) {
					System.out
							.println("| " + libro.getIsbn() + " | " + libro.getTitulo() + " | " + libro.getAutor() + " |");
				}
			} else {
				System.out.println("No se ha encontrado a ningun miembro con ese ID");
				System.out.println("Intentelo de nuevo.");
				System.out.println("");
				menuRecursivo();
			}
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void agregLib() {
		LibroDAO libroDAO = new LibroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			String titulo, autor, isbn;
			System.out.println("ISBN del libro:");
			isbn = scannerAgregar.nextLine();
			System.out.println("Titulo del libro:");
			titulo = scannerAgregar.nextLine();
			System.out.println("Autor del libro:");
			autor = scannerAgregar.nextLine();
			// Aqui se crea un objeto de la clase libro con la informacion recopilada.
			Libros libro = new Libros(isbn, titulo, autor, LibroStatus.DISPONIBLE.toString());
			// Luego, se manda al metodo insertar libro para poder entregarse a la base de
			// datos.
			libroDAO.insertarLibro(libro);
			System.out.println("Libro registrado exitosamente!");
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void miembrosMenu() {
		try (Scanner scn = new Scanner(System.in)) {
			System.out.println("1. Buscar miembros");
			System.out.println("2. Agregar Miembros");
			System.out.println("3. Actualizar Miembros");
			System.out.println("4. Eliminar Miembro");
			int mMenuOpcion = scn.nextInt();
			switch (mMenuOpcion) {
			case 1: {
				buscarMi();
				break;
			}
			case 2: {
				agregMi();
				break;
			}
			case 3: {
				actualizarMi();
				break;
			}
			case 4: {
				eliminarMi();
				break;
			}
			default:
				throw new IllegalArgumentException("Valor Invalido: " + mMenuOpcion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void buscarMi() {
		try (Scanner scn = new Scanner(System.in)) {
			System.out.println("1. Buscar por Nombre");
			System.out.println("2. Buscar por ID");
			int lMenuOpcion = scn.nextInt();
			switch (lMenuOpcion) {
			case 1: {
				buscarPorNombre();
				break;
			}
			case 2: {
				buscarPorId();
				break;
			}

			default:
				buscarMi();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void buscarPorNombre() {
		MiembroDAO miembroDAO = new MiembroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("ID del miembro:");
			String nombre = scannerAgregar.nextLine();
			List<Miembros> miembros = miembroDAO.obtenerMiembrosPorNombre(nombre);
			if(!miembros.isEmpty()) {
				System.out.println("| ID | NOMBRE | TELEFONO |");
				for (Miembros miembro : miembros) {
					System.out.println(
							"| " + miembro.getId() + " | " + miembro.getNombre() + " | " + miembro.getTelefono() + " |");
				}
			} else {
				System.out.println("No se ha encontrado a ningun miembro con ese ID");
				System.out.println("");
				menuRecursivo();
			}	
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void buscarPorId() {
		MiembroDAO miembroDAO = new MiembroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("ID del miembro:");
			int id = scannerAgregar.nextInt();
			List<Miembros> miembros = miembroDAO.obtenerMiembrosPorID(id);
			if(!miembros.isEmpty()) {
				System.out.println("| ID | NOMBRE | TELEFONO |");
				for (Miembros miembro : miembros) {
					System.out.println(
							"| " + miembro.getId() + " | " + miembro.getNombre() + " | " + miembro.getTelefono() + " |");
				}
			} else {
				System.out.println("No se ha encontrado a ningun miembro con ese ID");
				System.out.println("");
				menuRecursivo();
			}
			
			buscarPorId();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void agregMi() {
		MiembroDAO miembroDAO = new MiembroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("Nombre completo del miembro:");
			String nombre = scannerAgregar.nextLine();
			System.out.println("Telefono del miembro:");
			String telefono = scannerAgregar.nextLine();
			Miembros miembro = new Miembros(nombre, telefono);
			miembroDAO.insertarMiembro(miembro);
			System.out.println("Miembro registrado exitosamente!");
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void actualizarMi() {
		MiembroDAO miembroDAO = new MiembroDAO();
		try (Scanner scanner = new Scanner(System.in); Scanner scanner1 = new Scanner(System.in)) {
			System.out.println("Cual es el ID del miembro que quiere actualizar?");
			int id = scanner1.nextInt();
			List<Miembros> miembros = miembroDAO.obtenerMiembrosPorID(id);
			if (!miembros.isEmpty()) {
				System.out.println("Nuevo nombre del miembro:");
				String nombre = scanner.nextLine();
				System.out.println("Nuevo telefono del miembro:");
				String telefono = scanner.nextLine();
				Miembros miembro = new Miembros(nombre, telefono);
				miembroDAO.actualizarMiembro(id, miembro);
				System.out.println("Miembro actualizado con exito!");
			} else {
				System.out.println("No se ha encontrado a ningun miembro con ese ID");
				System.out.println("Intentelo de nuevo.");
				System.out.println("");
				actualizarMi();
			}
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eliminarMi() {
		MiembroDAO miembroDAO = new MiembroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("ID del miembro a eliminar:");
			int id = scannerAgregar.nextInt();
			miembroDAO.eliminarMiembro(id);
			System.out.println("Miembro eliminado");
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void prestamosMenu() {
		try (Scanner scn = new Scanner(System.in)) {
			System.out.println("1. Prestamo de libro");
			System.out.println("2. Devolucion de libro");
			System.out.println("3. Historial de prestamos de miembro");
			System.out.println("4. Estatus de un libro");
			int pMenuOpcion = scn.nextInt();
			switch (pMenuOpcion) {
			case 1: {
				operacionPrestamo();
				break;
			}
			case 2: {
				operacionDevolucion();
				break;
			}
			case 3: {
				historialDePrestamosDeMiembro();
				break;
			}
			case 4: {
				estatusLibro();
				break;
			}
			default:
				throw new IllegalArgumentException("Valor Invalido: " + pMenuOpcion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void historialDePrestamosDeMiembro() {
		PrestamosDAO prestamoDAO = new PrestamosDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("ID del miembro:");
			int id = scannerAgregar.nextInt();
			List<Prestamos> prestamos = prestamoDAO.obtenerPrestamos(id);
			if(!prestamos.isEmpty()) {
				System.out.println("| FOLIO | ID DE LIBRO | ID DE MIEMBRO | FECHA | OPERACION |");
				for (Prestamos prestamo : prestamos) {
					System.out.println("| " + prestamo.getFolio() + " | " + prestamo.getLib_id() + " | " + prestamo.getId()
							+ " | " + prestamo.getFecha() + " | " + prestamo.getLibro_operacion() + " |");
				}
			} else {
				System.out.println("No se ha encontrado a ningun miembro con ese ID");
				System.out.println("Intentelo de nuevo.");
				System.out.println("");
				menuRecursivo();
			}
			historialDePrestamosDeMiembro();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void operacionDevolucion() {
		PrestamosDAO prestamoDAO = new PrestamosDAO();
		LibroDAO libroDAO = new LibroDAO();
		MiembroDAO miembrosDAO = new MiembroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("ID de miembro:");
			int id = scannerAgregar.nextInt();
			List<Miembros> miembros = miembrosDAO.obtenerMiembrosPorID(id);
			if(!miembros.isEmpty()) {
				System.out.println("ID del libro que quieres devolver:");
				int lib_id = scannerAgregar.nextInt();
				List<Libros> libros = libroDAO.obtenerEstatusDeLibro(lib_id);
				switch (libros.get(0).getDisponibilidad()) {
				case "PRESTADO": {
					java.util.Date fechaJava = new java.util.Date();
					java.sql.Date fechaSQL = new java.sql.Date(fechaJava.getTime());
					Prestamos prestamo = new Prestamos(lib_id, id, fechaSQL, LibroOperacion.DEVOLUCION.toString());
					prestamoDAO.insertarPrestamo(prestamo);
					System.out.println("Devolucion registrada exitosamente!");
					break;
				}
				case "DISPONIBLE": {
					System.out.println("Este libro no ha sido prestado");
					System.out.println("Selecciona otro");
					System.out.println();
					menuRecursivo();
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + libros.get(0).getDisponibilidad());
				}
			} else {
				System.out.println("No se ha encontrado a ningun miembro con ese ID");
				System.out.println("Intentelo de nuevo.");
				System.out.println("");
				menuRecursivo();
			}
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void operacionPrestamo() {
		PrestamosDAO prestamoDAO = new PrestamosDAO();
		LibroDAO libroDAO = new LibroDAO();
		MiembroDAO miembrosDAO = new MiembroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("ID de miembro:");
			int id = scannerAgregar.nextInt();
			List<Miembros> miembros = miembrosDAO.obtenerMiembrosPorID(id);
			if(!miembros.isEmpty()) {
				System.out.println("ID del libro que quieres pedir prestado:");
				int lib_id = scannerAgregar.nextInt();
				List<Libros> libros = libroDAO.obtenerEstatusDeLibro(lib_id);
				if(!libros.isEmpty()) {
					switch (libros.get(0).getDisponibilidad()) {
					case "PRESTADO": {
						System.out.println("Este libro no ha sido prestado");
						System.out.println("Selecciona otro");
						System.out.println();
						menuRecursivo();
						break;
					}
					case "DISPONIBLE": {
						java.util.Date fechaJava = new java.util.Date();
						java.sql.Date fechaSQL = new java.sql.Date(fechaJava.getTime());
						Prestamos prestamo = new Prestamos(lib_id, id, fechaSQL, LibroOperacion.PRESTAMO.toString());
						prestamoDAO.insertarPrestamo(prestamo);
						System.out.println("Prestamo registrado exitosamente!");
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + libros.get(0).getDisponibilidad());
					}
				} else {
					System.out.println("No se ha encontrado a ningun libro con ese ID");
					System.out.println("Intentelo de nuevo.");
					System.out.println("");
					menuRecursivo();
				}
			} else {
				System.out.println("No se ha encontrado a ningun miembro con ese ID");
				System.out.println("Intentelo de nuevo.");
				System.out.println("");
				menuRecursivo();
			}
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	private static void estatusLibro() {
		LibroDAO libroDAO = new LibroDAO();
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("ISBN del libro:");
			String isbn = scannerAgregar.nextLine();
			List<Libros> libros = libroDAO.obtenerEstatusDeLibroPorISBN(isbn);
			System.out.println("|      ISBN      | TITULO | DISPONIBILIDAD |");
			for (Libros libro : libros) {
				System.out.println(
						"| " + libro.getIsbn() + " | " + libro.getTitulo() + " | " + libro.getDisponibilidad() + " |");
			}
			menuRecursivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void menuRecursivo() {
		try (Scanner scannerAgregar = new Scanner(System.in)) {
			System.out.println("Deseas volver al menu principal? 1=Si 2=No");
			int opcion = scannerAgregar.nextInt();
			if (opcion == 1)
				mainMenu();
			if (opcion == 2) {
				System.exit(0);
			} else {
				System.out.println("Valor incorrecto, vuelva a intentarlo");
				menuRecursivo();
			}
		}
	}

}
