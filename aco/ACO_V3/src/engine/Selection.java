/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package engine;

import mementos.MementoSelection;

/**
 * The class selection represents the selection that the user has done.
 */
public final class Selection {
	/** Selection start index. */
	private int start;

	/** Selection end index. */
	private int end;

	/**
	 * Instantiate a new selection from the start index to the end index.
	 *
	 * @param start
	 *            selection start
	 * @param end
	 *            selection end
	 */
	public Selection(int start, int end) {
		/* Preconditions */
		if (start < 0)
			throw new IllegalArgumentException("start < 0");
		if (end < start)
			throw new IllegalArgumentException("end < start");

		/* Treatment */
		this.start = start;
		this.end = end;
	}

	/**
	 * Flush the selection by making start and end equal.
	 */
	public final void flush() {
		end = start;
	}

	/**
	 * Gets the end of the selection.
	 *
	 * @return the end
	 */
	public final int getEnd() {
		return end;
	}

	/**
	 * Gets the length of the selection.
	 *
	 * @return the length
	 */
	public final int getLength() {
		return end - start;
	}

	/**
	 * Gets the memento.
	 *
	 * @return the memento
	 */
	public MementoSelection getMemento() {
		return new MementoSelection(start, end);
	}

	/**
	 * Gets the start of the selection.
	 *
	 * @return the start
	 */
	public final int getStart() {
		return start;
	}

	/**
	 * Checks if the selection is empty.
	 *
	 * @return true, if is empty
	 */
	public final boolean isEmpty() {
		return start == end;
	}

	/**
	 * Restore.
	 *
	 * @param memento
	 *            the memento
	 */
	public final void restore(MementoSelection memento) {
		/* Precondition */
		if (memento == null)
			throw new IllegalArgumentException("memento is null");

		/* Treatment */
		start = memento.getStart();
		end = memento.getEnd();
	}

	/**
	 * Sets the selection with start and end index.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 */
	public final void setSelection(int start, int end) {
		/* Preconditions */
		if (start < 0)
			throw new IllegalArgumentException("start < 0");
		if (end < start)
			throw new IllegalArgumentException("end < start");

		/* Treatment */
		this.start = start;
		this.end = end;
	}

	/**
	 * Sets the selection with given selection.
	 *
	 * @param selection
	 *            the new selection
	 */
	public final void setSelection(Selection selection) {
		/* Preconditions */
		if (selection == null)
			throw new IllegalArgumentException("selection is null");

		/* Treatment */
		start = selection.getStart();
		end = selection.getEnd();
	}
}