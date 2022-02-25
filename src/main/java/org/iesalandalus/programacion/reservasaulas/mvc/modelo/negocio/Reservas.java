package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	private int capacidad;
	private int tamano;
	Reserva[] coleccionReservas;

	public Reservas(int reservas) {
		if (reservas <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		capacidad = reservas;
		tamano = 0;
		coleccionReservas = new Reserva[reservas];

	}

	public Reserva[] get() {
		return copiaProfundaReservas();
	}

	private Reserva[] copiaProfundaReservas() {
		Reserva[] copia = new Reserva[capacidad];
		for (int i = 0; i < tamano; i++) {
			copia[i] = new Reserva(coleccionReservas[i]);
		}
		return copia;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		}
		if (tamanoSuperado(indice)) {
			coleccionReservas[indice] = new Reserva(reserva);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva con ese nombre.");
		}

	}

	private int buscarIndice(Reserva reserva) {
		int indice = 0;
		boolean reservaEncontrada = false;
		while (!tamanoSuperado(indice) && !reservaEncontrada) {
			if (coleccionReservas[indice].equals(reserva)) {
				reservaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean tamanoSuperado(int indice) {

		return (indice >= tamano);
	}

	private boolean capacidadSuperada(int indice) {

		return (indice >= capacidad);
	}

	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Reserva(reserva);
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
		for (int i = posicion; !tamanoSuperado(i); i++) {
			coleccionReservas[i] = coleccionReservas[i + 1];
		}
		tamano--;
	}

	public String[] representar() {
		String[] representa = new String[tamano];
		for (int i = 0; i < tamano; i++) {
			representa[i] = coleccionReservas[i].toString();
		}
		return representa;
	}

	public Reserva[] getReservasProfesor(Profesor reservasProfesor) {
		if (reservasProfesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}

		Reserva[] reservas = new Reserva[capacidad];

		int indiceReservas = 0;
		for (int i = 0; i < tamano; i++) {
			if (coleccionReservas[i].getProfesor().equals(reservasProfesor)) {
				reservas[indiceReservas] = coleccionReservas[i];
				indiceReservas++;
			}
		}
		return reservas;
	}

	public Reserva[] getReservasAula(Aula reservasAula) {
		if (reservasAula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		}

		Reserva[] reservas = new Reserva[capacidad];

		int indiceReservas = 0;
		for (int i = 0; i < tamano; i++) {
			if (coleccionReservas[i].getAula().equals(reservasAula)) {
				reservas[indiceReservas] = coleccionReservas[i];
				indiceReservas++;
			}
		}
		return reservas;
	}

	public Reserva[] getReservasPermanencia(Permanencia reservasPermanencia) {
		if (reservasPermanencia == null) {
			throw new NullPointerException("ERROR: La permanencia no puede ser nula.");
		}

		Reserva[] reservas = new Reserva[capacidad];

		int indiceReservas = 0;
		for (int i = 0; i < tamano; i++) {
			if (coleccionReservas[i].getPermanencia().equals(reservasPermanencia)) {
				reservas[indiceReservas] = coleccionReservas[i];
				indiceReservas++;
			}
		}

		return reservas;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (int i = 0; i < tamano; i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				if (coleccionReservas[i].getPermanencia().equals(permanencia)) {
					return false;
				}
			}
		}
		return true;
	}
}