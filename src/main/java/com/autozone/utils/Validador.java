package com.autozone.utils;

import java.lang.reflect.Field;

import com.autozone.annotations.Nombre_s;
import com.autozone.annotations.NotNull;
import com.autozone.annotations.telef;
import com.autozone.principal.Principal;

public class Validador {

	public static void vaildar(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			
			//Validacion para que los campos no sean nulos
			if(field.isAnnotationPresent(NotNull.class)) {
				String value = (String) field.get(obj);
				if(value == "") {
					System.out.println("Ha ingresado uno o mas campos en blanco");
					System.out.println("No se pudo agregar el libro.");
					System.out.println("");
					Principal.menuRecursivo();
				}
			}
			
			// Validacion para que el telefono solo sea un numero de 10 digitos
			if(field.isAnnotationPresent(telef.class)) {
				String value = (String) field.get(obj);
				
				if (value != null && !value.matches("^[0-9]{10,10}")){
					System.out.println("Los caracteres que ha introducido son no son numeros o la cantidad de numeros es diferente de la esperada");
					System.out.println("Vuelva a intentarlo.");
					Principal.agregMi();
				}
			}
			
			if (field.isAnnotationPresent(Nombre_s.class)){
				String value = (String) field.get(obj);
				
				if (value != null && !value.matches("[a-zA-Z]")){
					System.out.println("Ha introducido numeros o caracteres que no son letras en el nombre.");
					System.out.println("Vuelva a intentarlo.");
					System.out.println("");
					Principal.agregMi();
				}
			}
		}
	}	
}
