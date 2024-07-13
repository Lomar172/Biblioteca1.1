package com.autozone.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//Aqui se crea una anotacion para indicar que variables no queremos que sean nulas.

@Retention(RUNTIME)
@Target(FIELD)
public @interface NotNull {

}
