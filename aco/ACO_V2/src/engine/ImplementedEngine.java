/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package engine;

/**
 * This class is an Implementation of the edition engine.
 *
 * @see EditionEngine
 */
public final class ImplementedEngine implements EditionEngine {
	/** The selection. */
	private final Selection selection;

	/** The buffer. */
	private final Buffer buffer;

	/** The clipboard. */
	private final Clipboard clipboard;

	/**
	 * Instantiates a new implemented engine and initialize it.
	 */
	public ImplementedEngine() {
		selection = new Selection(0, 0);
		buffer = new Buffer();
		clipboard = new Clipboard();
	}

	/*
	 * (non-Javadoc)
	 * @see engine.EditionEngine#copy()
	 */
	@Override
	public final void copy() {
		if (!selection.isEmpty())
			clipboard.setContent(buffer.getContent(selection));
	}

	/*
	 * (non-Javadoc)
	 * @see engine.EditionEngine#cut()
	 */
	@Override
	public final void cut() {
		if (!selection.isEmpty()) {
			clipboard.setContent(buffer.getContent(selection));
			deleteText();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see engine.EditionEngine#deleteText()
	 */
	@Override
	public final void deleteText() {
		buffer.deleteText(selection);
	}

	/**
	 * Gets the buffer of this engine.
	 *
	 * @return the buffer
	 */
	public final Buffer getBuffer() {
		return buffer;
	}

	/*
	 * (non-Javadoc)
	 * @see engine.EditionEngine#insertText(java.lang.String)
	 */
	@Override
	public final void insertText(final String s) {
		/* Precondition */
		if (s == null)
			throw new IllegalArgumentException("s is null");

		/* Treatment */
		buffer.addText(s, selection);
	}

	/*
	 * (non-Javadoc)
	 * @see engine.EditionEngine#paste()
	 */
	@Override
	public final void paste() {
		if (!clipboard.isEmpty())
			buffer.addText(clipboard.getContent(), selection);
	}

	/*
	 * (non-Javadoc)
	 * @see engine.EditionEngine#select(engine.Selection)
	 */
	@Override
	public final void select(final Selection selection) {
		/* Precondition */
		if (selection == null)
			throw new IllegalArgumentException("selection is null");

		/* Treatment */
		int start = selection.getStart();
		int end = selection.getEnd();

		// Verify if the selection is correct and corrects it if it is not
		if (end > buffer.getMaxSelect())
			end = buffer.getMaxSelect();
		if (start > end)
			start = end;

		this.selection.setSelection(new Selection(start, end));
	}
}
