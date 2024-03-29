/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Paste;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoPaste;

/**
 * PasteRecordable executes a text insertion command will saving its state in
 * a recorder.
 *
 * @see Recorder
 * @see Paste
 * @see CommandRecordable
 */
public final class PasteRecordable implements CommandRecordable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(PasteRecordable.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/** The engine. */
	private EditionEngine engine;

	/**
	 * Instantiate a PasteRecordable from the given parameters (all shall be
	 * not null).
	 *
	 * @param engine
	 *            the engine to ask to execute the command
	 * @param recorder
	 *            the command recorder
	 */
	public PasteRecordable(EditionEngine engine, Recorder recorder) {
		/* Preconditions */
		if (engine == null)
			throw new IllegalArgumentException("engine is null");
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		/* Treatment */
		this.engine = engine;
		this.recorder = recorder;
	}

	/**
	 * Instantiate a PasteRecordable from a memento and executes a paste
	 * command.
	 *
	 * @param memento
	 *            the memento from which we restore the state and execute the
	 *            select command
	 */
	public PasteRecordable(MementoCommand memento) {
		restore(memento);

		LOGGER.trace("Executing paste command");

		new Paste(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public final void execute() {
		recorder.record(this);

		LOGGER.trace("Executing command paste");

		new Paste(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see recordables.CommandRecordable#getMemento()
	 */
	@Override
	public final MementoCommand getMemento() {
		return new MementoPaste(engine, recorder);
	}

	/*
	 * (non-Javadoc)
	 * @see recordables.CommandRecordable#restore(mementos.MementoCommand)
	 */
	@Override
	public final void restore(MementoCommand memento) {
		/* Preconditions */
		if (memento == null)
			throw new IllegalArgumentException("memento is null");
		if (!(memento instanceof MementoPaste))
			throw new IllegalArgumentException("memento not of type MementoPaste");

		LOGGER.trace("PasteRecordable from memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
	}
}
