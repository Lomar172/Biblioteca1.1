package com.autozone.utils;

import java.lang.reflect.Field;

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
				Object value = field.get(obj);
				if(value == null) {
					throw new IllegalArgumentException(field.getName() + " no puede ser nulo.");
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
		}
	}	
}
