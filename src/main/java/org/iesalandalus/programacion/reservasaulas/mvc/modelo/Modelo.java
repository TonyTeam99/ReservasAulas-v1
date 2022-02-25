package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {

	private static final int CAPACIDAD = 50;
	Profesores profesores;
	Aulas aulas;
	Reservas reservas;

	public Modelo() {
		profesores = new Profesores(CAPACIDAD);
		aulas = new Aulas(CAPACIDAD);
		reservas = new Reservas(CAPACIDAD);
	};

	public Aula[] getAulas() {
		return aulas.get();
	}

	public int getNumAulas() {
		return aulas.getTamano();
	}

	public String[] representaAulas() {
		return aulas.representar();
	}

	public Aula buscarAula(Aula aula) {
		return aulas.buscar(aula);
	}

	public void insertarAula(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {
		aulas.borrar(aula);
	}

	public Profesor[] getProfesores() {
		return profesores.get();
	}

	public int getNumProfesores() {
		return profesores.getTamano();
	}

	public String[] representaProfesores() {
		return profesores.representar();
	}

	public Profesor buscarProfesor(Profesor profesor) {
		return profesores.buscar(profesor);
	}

	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}

	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}

	public Reserva[] getReservas() {
		return reservas.get();
	}

	public int getNumReservas() {
		return reservas.getTamano();
	}

	public String[] representaReservas() {
		return reservas.representar();
	}

	public Reserva buscarReserva(Reserva Reserva) {
		return reservas.buscar(Reserva);
	}

	public void realizarReserva(Reserva Reserva) throws OperationNotSupportedException {
		reservas.insertar(Reserva);
	}

	public void anularReserva(Reserva Reserva) throws OperationNotSupportedException {
		reservas.borrar(Reserva);
	}

	public Reserva[] getReservasAula(Aula aula) {
		return reservas.getReservasAula(aula);
	}

	public Reserva[] getReservaProfesor(Profesor profesor) {
		return reservas.getReservasProfesor(profesor);
	}

	public Reserva[] getReservaPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}

}