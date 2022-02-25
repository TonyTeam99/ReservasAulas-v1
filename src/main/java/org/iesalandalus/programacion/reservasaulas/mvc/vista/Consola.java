package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

//	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Consola() {

	};

	public static void mostrarMenu() {
		mostrarCabecera("Gestión de clientes");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;

	}

	public static Aula leerAula() {
		return new Aula(leerNombreAula());

	}

	public static String leerNombreAula() {
		System.out.println("Nombre del Aula:");
		String nombreAula = Entrada.cadena();
		return nombreAula;

	}

	public static Profesor leerProfesor() {
		System.out.println("Correo del Profesor:");
		String correoProfesor = Entrada.cadena();
		System.out.println("Telefono del Profesor (Si no quieres insertar, pulse ENTER)");
		String telefonoProfesor = Entrada.cadena();
		return new Profesor(leerNombreProfesor(), correoProfesor, telefonoProfesor);

	}

	public static String leerNombreProfesor() {
		System.out.println("Nombre del Profesor:");
		String nombreProfesor = Entrada.cadena();
		return nombreProfesor;

	}

	public static Tramo leerTramo() {
		System.out.println("Que tramo diario: \n1-Mañana \n2-Tarde");
		int indice = Entrada.entero();
		switch (indice) {
		case 1:
			return Tramo.MANANA;

		case 2:
			return Tramo.TARDE;

		default:
			return null;
		}
	}

	public static LocalDate leerDia() {
		System.out.println("DIA:");
		int dia = Entrada.entero();
		System.out.println("MES:");
		int mes = Entrada.entero();
		System.out.println("ANIO:");
		int anio = Entrada.entero();
		return LocalDate.of(anio, mes, dia);
	}
}