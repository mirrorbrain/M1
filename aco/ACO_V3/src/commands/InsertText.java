/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;

/**
 * The insert text command, asks the engine to execute the text insertion.
 */
public final class InsertText implements Command {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(InsertText.class.getName());

	/** Engine that will execute the command. */
	private final EditionEngine engine;

	/** String to insert. */
	private final String string;

	/**
	 * Instantiate a new InsertText that will execute a text insertion in the
	 * given engine of the given string.
	 *
	 * @param engine
	 *            the engine
	 * @param string
	 *            string to insert (not null)
	 */
	public InsertText(EditionEngine engine, String string) {
		/* Preconditions */
		if (engine == null)
			throw new IllegalArgumentException("Engine is null");
		if (string == null)
			throw new IllegalArgumentException("String is null");

		/* Treatment */
		this.engine = engine;
		this.string = string;
	}

	/**
	 * Execute the command.
	 */
	@Override
	public final void execute() {
		LOGGER.trace("Executing command insert text");
		engine.insertText(string);
	}
}