/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Select;
import engine.EditionEngine;
import engine.Selection;

/**
 * This class monitors the selection in the TextArea and ensures the
 * synchronization with the engine selection.
 *
 * @see Selection
 */
public final class SelectionListener implements CaretListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(SelectionListener.class.getName());

	/** The active. */
	private boolean active;

	/** Engine to perform commands. */
	private final EditionEngine engine;

	/**
	 * Constructor needs to know the edition engine to perform commands.
	 *
	 * @param engine
	 *            the engine (not null)
	 */
	public SelectionListener(EditionEngine engine) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("Engine is null");

		/* Treatment */
		this.engine = engine;
		active = true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.event.CaretListener#caretUpdate(javax.swing.event.CaretEvent)
	 */
	@Override
	/**
	 * Invoked when the selection changes in TextArea.
	 *
	 * @param e
	 *            CaretEvent
	 */
	public final void caretUpdate(CaretEvent e) {
		LOGGER.trace("Detected new selection");

		final int min = Math.min(e.getDot(), e.getMark());
		final int max = Math.max(e.getDot(), e.getMark());

		LOGGER.debug("New selection is : [" + min + ", " + max + "]");

		if (active)
			new Select(engine, new Selection(min, max)).execute();
	}

	/**
	 * Tells Filter if it needs to ask the engine to execute a command or not.
	 *
	 * @param active
	 *            ask when true
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}