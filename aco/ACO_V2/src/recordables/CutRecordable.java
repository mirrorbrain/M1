/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Cut;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoCut;

/**
 * CutRecordable executes a cut command will saving its state in a
 * recorder.
 *
 * @see Recorder
 * @see Cut
 * @see CommandRecordable
 */
public final class CutRecordable implements CommandRecordable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(CutRecordable.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/** The engine. */
	private EditionEngine engine;

	/**
	 * Instantiate a CutRecordable from the given parameters (all shall be
	 * not null).
	 *
	 * @param engine
	 *            the engine to ask to execute the command
	 * @param recorder
	 *            the command recorder
	 */
	public CutRecordable(EditionEngine engine, Recorder recorder) {
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
	 * Instantiate a CutRecordable from a memento and executes a cut
	 * command.
	 *
	 * @param memento
	 *            the memento from which we restore the state and execute the
	 *            select command
	 */
	public CutRecordable(MementoCommand memento) {
		restore(memento);

		LOGGER.trace("Executing command cut");

		new Cut(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public final void execute() {
		recorder.record(this);

		LOGGER.trace("Executing command cut");

		new Cut(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see recordables.CommandRecordable#getMemento()
	 */
	@Override
	public final MementoCommand getMemento() {
		return new MementoCut(engine, recorder);
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
		if (!(memento instanceof MementoCut))
			throw new IllegalArgumentException("memento not of type MementoCut");

		LOGGER.trace("CutRecordable from memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
	}
}
