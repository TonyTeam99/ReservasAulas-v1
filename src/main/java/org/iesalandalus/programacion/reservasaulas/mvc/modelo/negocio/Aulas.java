package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {

	private int capacidad;
	private int tamano;
	Aula coleccionAulas[];

	public Aulas(int aulas) {
		if (aulas <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		capacidad = aulas;
		tamano = 0;
		this.coleccionAulas = new Aula[aulas];
	}

	public Aula[] get() {
		return copiaProfundaAulas();

	}

	private Aula[] copiaProfundaAulas() {
		Aula[] copia = new Aula[capacidad];
		for (int i = 0; i < tamano; i++) {
			copia[i] = coleccionAulas[i];
		}
		return copia;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		}
		if (tamanoSuperado(indice)) {
			coleccionAulas[indice] = new Aula(aula);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}
	}

	private int buscarIndice(Aula aula) {
		int indice = 0;
		boolean aulaEncontrada = false;
		while (!tamanoSuperado(indice) && !aulaEncontrada) {
			if (coleccionAulas[indice].equals(aula)) {
				aulaEncontrada = true;
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

	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Aula(coleccionAulas[indice]);
		}
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
		for (int i = posicion; !tamanoSuperado(i); i++) {
			coleccionAulas[i] = coleccionAulas[i + 1];
		}
		tamano--;
	}

	public String[] representar() {
		String[] representa = new String[tamano];
		for (int i = 0; i < tamano; i++) {
			representa[i] = coleccionAulas[i].toString();
		}
		return representa;
	}
}