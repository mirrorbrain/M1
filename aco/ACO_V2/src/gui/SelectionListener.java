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

import editor.Recorder;
import engine.EditionEngine;
import engine.Selection;
import recordables.SelectRecordable;

/**
 * SelectionListener is to listen on changes on the selection in the JTextArea,
 * to ensure synchronization between selection in JTextArea and the selection
 * object in the edition engine.
 */
public final class SelectionListener implements CaretListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(SelectionListener.class.getName());

	/** The active. */
	private boolean active;

	/** The engine. */
	private final EditionEngine engine;

	/** The recorder. */
	private final Recorder recorder;

	/**
	 * Constructor needs to know the edition engine to perform commands.
	 *
	 * @param engine
	 *            the engine (not null)
	 * @param recorder
	 *            the recorder (not null)
	 */
	public SelectionListener(EditionEngine engine, Recorder recorder) {
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("engine is null");
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		/* Treatment */
		this.engine = engine;
		this.recorder = recorder;
		active = true;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.event.CaretListener#caretUpdate(javax.swing.event.CaretEvent)
	 */
	@Override
	/**
	 * Invoked when the selection changes in the TextArea.
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
			new SelectRecordable(engine, recorder, new Selection(min, max)).execute();
	}

	/**
	 * Tells Filter if it needs to ask the engine to execute a command or not.
	 *
	 * @param active
	 *            ask if true
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}