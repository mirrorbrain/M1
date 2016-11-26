/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import java.util.logging.LogManager;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import commands.DeleteText;
import commands.InsertText;
import engine.EditionEngine;

/**
 * This class filters input in TextArea. First it transmits a command to the
 * engine (modify the Buffer).
 * Then it apply this modifications to the GUI via Observer pattern
 */
public final class ModificationFilter extends DocumentFilter {
	private static final Logger	LOGGER	= LogManager.getLogger(ModificationFilter.class.getName());
	private final EditionEngine	engine;
	private boolean				active;

	/**
	 * Constructor need to know the edition engine to perform commands
	 *
	 * @param engine
	 *            Edition engine (not null)
	 */
	public ModificationFilter(EditionEngine engine) {
		super();
		if (engine == null)
			throw new IllegalArgumentException("Null engine");
		this.engine = engine;
		active = true;
	}

	@Override
	/**
	 * Invok when String is paste in TextArea
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
			new InsertText(engine, string).execute();
		else
			super.insertString(fb, offset, string, attr);
		LOGGER.trace("Exit insertString");
	}

	@Override
	/**
	 * Invok when text is delete in TextArea
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
			new DeleteText(engine).execute();
		else
			super.remove(fb, offset, length);
		LOGGER.trace("Exit remove");
	}

	/**
	 * Invok when one char is insert in TextArea via keyboard
	 *
	 * @param offset
	 *            the position in Buffer
	 * @param length
	 *            length of insertion
	 * @param string
	 *            the char to insert
	 */
	@Override
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attrs)
			throws BadLocationException {
		LOGGER.trace("Enter replace");
		LOGGER.debug("replace the string in position : " + offset + "  length " + length + " with " + string);
		if (active)
			new InsertText(engine, string).execute();
		else
			super.replace(fb, offset, length, string, attrs);
		LOGGER.trace("Exit replace");
	}

	/**
	 * Notice Filter if it must launch a command to the engine or not
	 *
	 * @param active
	 *            Boolean to notice
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}