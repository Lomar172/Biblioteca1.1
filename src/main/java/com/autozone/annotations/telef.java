package com.autozone.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//Aqui se crea una anotacion que indica que variable es un numero de telefono.
@Retention(RUNTIME)
@Target(FIELD)
public @interface telef {

}
