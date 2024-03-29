/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package recordables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.InsertText;
import editor.Recorder;
import engine.EditionEngine;
import mementos.MementoCommand;
import mementos.MementoInsText;

/**
 * InsTextRecordable executes a text insertion command will saving its state in
 * a recorder.
 *
 * @see Recorder
 * @see InsertText
 * @see CommandRecordable
 */
public final class InsTextRecordable implements CommandRecordable {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(InsTextRecordable.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/** The engine. */
	private EditionEngine engine;

	/** The string. */
	private String string;

	/**
	 * Instantiate a InsTextRecordable from the given parameters (all shall be
	 * not null).
	 *
	 * @param engine
	 *            the engine to ask to execute the command
	 * @param recorder
	 *            the command recorder
	 * @param s
	 *            the string to insert
	 */
	public InsTextRecordable(EditionEngine engine, Recorder recorder, String s) {
		/* Preconditions */
		if (engine == null)
			throw new IllegalArgumentException("engine is null");
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");
		if (s == null)
			throw new IllegalArgumentException("string is null");

		/* Treatment */
		this.engine = engine;
		this.recorder = recorder;
		string = s;
	}

	/**
	 * Instantiate a InsTextRecordable from a memento and executes a text
	 * insertion command.
	 *
	 * @param memento
	 *            the memento from which we restore the state and execute the
	 *            select command
	 */
	public InsTextRecordable(MementoCommand memento) {
		restore(memento);

		LOGGER.trace("Executing command insert text");

		new InsertText(engine, string).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public final void execute() {
		recorder.record(this);

		LOGGER.trace("Executing command insert text");

		new InsertText(engine, string).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see recordables.CommandRecordable#getMemento()
	 */
	@Override
	public final MementoCommand getMemento() {
		return new MementoInsText(engine, recorder, string);
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
		if (!(memento instanceof MementoInsText))
			throw new IllegalArgumentException("memento not of type MementoInsText");

		LOGGER.trace("InsTextRecordable from memento");

		/* Treatment */
		engine = memento.getEngine();
		recorder = memento.getRecorder();
		string = ((MementoInsText) memento).getText();
	}
}
