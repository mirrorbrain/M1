/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;
import engine.EditionEngine;
import recordables.DelTextRecordable;
import recordables.InsTextRecordable;

/**
 * This class filters input in TextArea. First it transmits a command to the
 * engine (modify the Buffer).
 * Then it apply this modifications to the GUI via Observer pattern
 */
public final class ModificationFilter extends DocumentFilter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(ModificationFilter.class.getName());

	/** The active. */
	private boolean active;

	/** The engine. */
	private final EditionEngine engine;

	/** The recorder. */
	private final Recorder recorder;

	/**
	 * Constructor need to know the edition engine to perform commands.
	 *
	 * @param engine
	 *            Edition engine (not null)
	 * @param recorder
	 *            the recorder (not null)
	 */
	public ModificationFilter(EditionEngine engine, Recorder recorder) {
		super();
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
	 * @see javax.swing.text.DocumentFilter#insertString(javax.swing.text.
	 * DocumentFilter.FilterBypass, int, java.lang.String,
	 * javax.swing.text.AttributeSet)
	 */
	@Override
	/**
	 * Invoked when string is passed in TextArea
	 *
	 * @param offset
	 *            the position in Buffer
	 * @param string
	 *            the String to insert
	 */
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		LOGGER.trace("Enter in  insertString");
		LOGGER.debug("Insertion of string : " + string);

		if (active)
			new InsTextRecordable(engine, recorder, string).execute();
		else
			super.insertString(fb, offset, string, attr);

		LOGGER.trace("Exit insertString");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.text.DocumentFilter#remove(javax.swing.text.DocumentFilter.
	 * FilterBypass, int, int)
	 */
	@Override
	/**
	 * Invoked when text is deleted in TextArea
	 *
	 * @param offset
	 *            the position in Buffer
	 * @param length
	 *            length of deleted text
	 */
	public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
		LOGGER.trace("Enter remove");
		LOGGER.debug("Delete string from " + offset + " length " + length);

		if (active)
			new DelTextRecordable(engine, recorder).execute();
		else
			super.remove(fb, offset, length);

		LOGGER.trace("Exit remove");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.text.DocumentFilter#replace(javax.swing.text.DocumentFilter.
	 * FilterBypass, int, int, java.lang.String, javax.swing.text.AttributeSet)
	 */
	@Override
	/**
	 * Invoked when one char is inserted in TextArea via keyboard
	 *
	 * @param offset
	 *            the position in Buffer
	 * @param length
	 *            length of insertion
	 * @param string
	 *            the char to insert
	 */
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attrs)
			throws BadLocationException {
		LOGGER.trace("Enter replace");
		LOGGER.debug("replace the string in position : " + offset + "  length " + length + " with " + string);

		if (active)
			new InsTextRecordable(engine, recorder, string).execute();
		else
			super.replace(fb, offset, length, string, attrs);

		LOGGER.trace("Exit replace");
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
