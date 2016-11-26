/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.JTextArea;

import commands.Copy;
import commands.Cut;
import commands.Paste;
import engine.EditionEngine;

/**
 * this class inherit of swing JtextArea to implement copy, paste and cut
 * actions
 */
public class TextAreaCustom extends JTextArea {

	static final long		serialVersionUID	= 8166387793964966707L;
	private EditionEngine	engine;

	/**
	 * Constructor for text area
	 *
	 * @param height
	 *            height of text area
	 * @param width
	 *            width of text area
	 * @param engine
	 *            engine to send commands
	 */
	public TextAreaCustom(int height, int width, EditionEngine engine) {
		super(height, width);
		if (engine == null)
			throw new IllegalAccessError("engine est à null");
		this.engine = engine;
	}

	/**
	 * launch a copy command
	 *
	 * @see Copy
	 */
	@Override
	public void copy() {
		new Copy(engine).execute();
	}

	/**
	 * launch a cut command
	 *
	 * @see Cut
	 */
	@Override
	public void cut() {
		new Cut(engine).execute();
	}

	/**
	 * launch a paste command
	 *
	 * @see Paste
	 */
	@Override
	public void paste() {
		new Paste(engine).execute();
	}
}