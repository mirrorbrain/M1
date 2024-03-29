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
import engine.Selection;

/**
 * The selection command, asks the engine to execute a selection.
 */
public final class Select implements Command {
	/** The Constant LOGGER. */
	private static final Logger	LOGGER	= LogManager.getLogger(Select.class.getName());
	/** Engine that will execute the command. */
	private final EditionEngine	engine;
	/** New selection. */
	private final Selection		sel;

	/**
	 * Instantiate a new Select that will execute the given selection in the
	 * given engine.
	 *
	 * @param engine
	 *            the engine
	 * @param sel
	 *            the new selection (not null)
	 */
	public Select(EditionEngine engine, Selection sel) {
		/* Preconditions */
		if (engine == null)
			throw new IllegalArgumentException("Engine is null");
		if (sel == null)
			throw new IllegalArgumentException("Selection is null");

		/* Treatment */
		this.engine = engine;
		this.sel = sel;
	}

	/**
	 * Execute the command.
	 */
	@Override
	public final void execute() {
		LOGGER.trace("Executing command select");
		engine.select(sel);
	}
}
