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

import commands.DeleteText;
import commands.InsertText;
import engine.EditionEngine;

/**
 * This class filters input in TextArea. First it transmits a command to the
 * engine (modify the Buffer).
 * Then it apply this modifications to the GUI via Observer pattern
 */
public final class ModificationFilter extends DocumentFilter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(ModificationFilter.class.getName());

	/** The engine. */
	private final EditionEngine engine;

	/** The active. */
	private boolean active;

	/**
	 * Constructor need to know the edition engine to perform commands.
	 *
	 * @param engine
	 *            Edition engine (not null)
	 */
	public ModificationFilter(EditionEngine engine) {
		super();
		/* Precondition */
		if (engine == null)
			throw new IllegalArgumentException("Engine is null");

		/* Treatment */
		this.engine = engine;
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
	 * Invoked when a string is passed in the TextArea
	 *
	 * @param offset
	 *            the position in the Buffer
	 * @param string
	 *            the string to insert
	 */
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		LOGGER.trace("insertString method");
		LOGGER.debug("Insertion of string : " + string);
		if (active)
			new InsertText(engine, string).execute();
		else
			super.insertString(fb, offset, string, attr);
		LOGGER.trace("insertString end");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.text.DocumentFilter#remove(javax.swing.text.DocumentFilter.
	 * FilterBypass, int, int)
	 */
	@Override
	/**
	 * Invoked when text is deleted from the TextArea
	 *
	 * @param offset
	 *            the position in the Buffer
	 * @param length
	 *            length of the deleted text
	 */
	public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
		LOGGER.trace("remove method");
		LOGGER.debug("Deleted string from " + offset + " length " + length);
		if (active)
			new DeleteText(engine).execute();
		else
			super.remove(fb, offset, length);
		LOGGER.trace("remove end");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.swing.text.DocumentFilter#replace(javax.swing.text.DocumentFilter.
	 * FilterBypass, int, int, java.lang.String, javax.swing.text.AttributeSet)
	 */
	@Override
	/**
	 * Invoked when one char is inserted in the TextArea via the keyboard.
	 *
	 * @param fb
	 *            the filter bypass
	 * @param offset
	 *            the position in the Buffer
	 * @param length
	 *            length of the insertion
	 * @param string
	 *            the char to insert
	 * @param attrs
	 *            the attributes
	 * @throws BadLocationException
	 *             the bad location exception
	 */
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attrs)
			throws BadLocationException {
		LOGGER.trace("replace method");
		LOGGER.debug("replace the string in position : " + offset + "  length " + length + " with " + string);
		if (active)
			new InsertText(engine, string).execute();
		else
			super.replace(fb, offset, length, string, attrs);
		LOGGER.trace("replace end");
	}

	/**
	 * Notify the Filter if it needs to ask the engine to execute a command or
	 * not.
	 *
	 * @param active
	 *            ask if true
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
