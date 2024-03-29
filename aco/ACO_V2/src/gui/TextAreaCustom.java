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
 * TextAreaCustom inherits of the swing JtextArea to implement the copy, paste
 * and cut actions.
 */
public class TextAreaCustom extends JTextArea {

	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 8166387793964966707L;

	/** The engine. */
	private EditionEngine engine;

	/**
	 * Instantiates a new custom text area.
	 *
	 * @param height
	 *            the height
	 * @param width
	 *            the width
	 * @param engine
	 *            the engine
	 */
	public TextAreaCustom(int height, int width, EditionEngine engine) {
		super(height, width);
		/* Precondition */
		if (engine == null)
			throw new IllegalAccessError("engine is null");

		/* Treatment */
		this.engine = engine;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#copy()
	 */
	@Override
	public void copy() {
		new Copy(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#cut()
	 */
	@Override
	public void cut() {
		new Cut(engine).execute();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.text.JTextComponent#paste()
	 */
	@Override
	public void paste() {
		new Paste(engine).execute();
	}
}
