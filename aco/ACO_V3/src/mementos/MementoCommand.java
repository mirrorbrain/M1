/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package mementos;

import editor.Recorder;
import engine.EditionEngine;
import recordables.CommandRecordable;

/**
 * MementoCommand is used to store the state of recordable commands. It defines
 * stores the engine and the recorder of recordable commands and define their
 * getter and setter.
 *
 * @see CommandRecordable
 * @see Recorder
 */
public abstract class MementoCommand {

	/** The engine. */
	protected EditionEngine engine;

	/** The recorder. */
	protected Recorder recorder;

	/**
	 * Instantiate a MementoCommand that will store the state of a command
	 * (given engine and recorder).
	 *
	 * @param engine
	 *            the engine to store
	 * @param recorder
	 *            the recorder to store
	 */
	public MementoCommand(EditionEngine engine, Recorder recorder) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("engine is null");
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		/* Treatment */
		this.engine = engine;
		this.recorder = recorder;
	}

	/**
	 * Gets the stored engine.
	 *
	 * @return the stored engine
	 */
	public final EditionEngine getEngine() {
		return engine;
	}

	/**
	 * Gets the stored recorder.
	 *
	 * @return the stored recorder
	 */
	public final Recorder getRecorder() {
		return recorder;
	}

	/**
	 * Sets the stored engine.
	 *
	 * @param engine
	 *            the new engine (not null)
	 */
	public final void setEngine(EditionEngine engine) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("engine is null");

		/* Treatment */
		this.engine = engine;
	}

	/**
	 * Sets the stored recorder.
	 *
	 * @param recorder
	 *            the new recorder (not null)
	 */
	public final void setRecorder(Recorder recorder) {
		/* Precondition */
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		/* Treatment */
		this.recorder = recorder;
	}
}
