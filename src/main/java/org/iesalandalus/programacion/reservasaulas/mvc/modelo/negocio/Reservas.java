package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {

	private List<Reserva> coleccionReservas;

	public Reservas() {
		coleccionReservas = new ArrayList<>();
	}

	public Reservas(Reservas reservas) {
		setReservas(reservas);
	}

	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		} else {
			coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
		}
	}

	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> copiaReserva = new ArrayList<>();
		Iterator<Reserva> listIterator = reservas.listIterator();
		while (listIterator.hasNext()) {
			copiaReserva.add(new Reserva(listIterator.next()));
		}
		return copiaReserva;
	}

	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return coleccionReservas.size();
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		} else {
			if (coleccionReservas.contains(reserva)) {
				throw new OperationNotSupportedException("ERROR: La reserva ya existe.");
			} else {
				coleccionReservas.add(new Reserva(reserva));
			}
		}

	}

	public Reserva buscar(Reserva reserva) {
		int indice = coleccionReservas.indexOf(reserva);
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		} else {
			if (indice == -1) {
				return null;
			} else {
				return new Reserva(coleccionReservas.get(indice));
			}
		}

	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede anular una reserva nula.");
		} else {
			if (!coleccionReservas.remove(reserva)) {
				throw new OperationNotSupportedException("ERROR: La reserva a anular no existe.");
			}
		}
	}

	public List<String> representar() {
		List<String> representaReservas = new ArrayList<>();
		Iterator<Reserva> listIterator = coleccionReservas.listIterator();
		while (listIterator.hasNext()) {
			representaReservas.add(listIterator.next().toString());
		}
		return representaReservas;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {
		List<Reserva> reservaProfesor = copiaProfundaReservas(coleccionReservas);
		Iterator<Reserva> listIterator = reservaProfesor.listIterator();
		while (listIterator.hasNext()) {
			if (listIterator.next().getProfesor() != profesor) {
				listIterator.remove();
				;
			}
		}
		return reservaProfesor;
	}

	public List<Reserva> getReservasAula(Aula aula) {
		List<Reserva> reservaAula = copiaProfundaReservas(coleccionReservas);
		Iterator<Reserva> listIterator = reservaAula.listIterator();
		while (listIterator.hasNext()) {
			if (listIterator.next().getAula() != aula) {
				listIterator.remove();
			}
		}
		return reservaAula;
	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		List<Reserva> reservaPermanencia = copiaProfundaReservas(coleccionReservas);
		Iterator<Reserva> listIterator = reservaPermanencia.listIterator();
		while (listIterator.hasNext()) {
			if (listIterator.next().getPermanencia() != permanencia) {
				listIterator.remove();
			}
		}
		return reservaPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		List<Reserva> consultarDisponibilidad = copiaProfundaReservas(coleccionReservas);
		Iterator<Reserva> listIterator = consultarDisponibilidad.listIterator();
		while (listIterator.hasNext()) {
			Reserva myobj = listIterator.next();
			if (myobj.getAula() == aula && myobj.getPermanencia() == permanencia) {
				return false;
			}
		}
		return true;
	}
}