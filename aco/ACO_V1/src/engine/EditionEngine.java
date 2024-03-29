/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package engine;

/**
 * An edition engine allows for a variety of actions to edit a text. These
 * actions are:
 * Copy, Cut, Paste, Select, Delete text, Insert text.
 * An edition engine is composed of :
 * - A buffer, allowing modification and storage of a text.
 * - A clipboard, allowing the copy/cut/paste actions.
 * - a selection, allowing the edition of parts of the text in the buffer
 *
 * @see Buffer
 * @see Clipboard
 * @see Selection
 */
public interface EditionEngine {
	/**
	 * Copy of the selection to the clipboard.
	 */
	public void copy();

	/**
	 * Cut the selection to the clipboard.
	 */
	public void cut();

	/**
	 * Delete the selection from the buffer.
	 */
	public void deleteText();

	/**
	 * Insert the given string in the buffer.
	 *
	 * @param s
	 *            the string to insert
	 */
	public void insertText(String s);

	/**
	 * Paste the clipboard to the buffer.
	 */
	public void paste();

	/**
	 * Set the selection to the given selection.
	 *
	 * @param s
	 *            the new selection
	 */
	public void select(Selection s);
}
