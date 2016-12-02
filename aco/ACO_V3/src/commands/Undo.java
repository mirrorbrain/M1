package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;

/**
 * Undo is to ask the engine to undo the last command (if possible).
 */
public class Undo implements Command {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Undo.class.getName());

	/** The engine. */
	private final EditionEngine engine;

	/**
	 * Instantiates a new undo.
	 *
	 * @param engine
	 *            the engine
	 */
	public Undo(EditionEngine engine) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("engine est à null");

		/* Treatment */
		this.engine = engine;
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Commande#executer()
	 */
	@Override
	public void execute() {
		LOGGER.trace("Exécution d'une commande Défaire");
		engine.undo();
	}
}