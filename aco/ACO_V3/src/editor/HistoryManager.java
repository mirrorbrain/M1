/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.Redo;
import commands.Undo;
import mementos.MementoSystem;

/**
 * HistoryManager manages the command history by saving in stacks the memento
 * systems created after each command altering the buffer or the selection.
 *
 * @see Undo
 * @see Redo
 */
public class HistoryManager implements Observable {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(HistoryManager.class.getName());

	/** The undo stack. */
	private Stack<MementoSystem> undo;

	/** The redo stack. */
	private Stack<MementoSystem> redo;

	/** The list of observers. */
	private List<Observer> listObservers;

	/**
	 * Instantiates a new history manager.
	 */
	public HistoryManager() {
		undo = new Stack<>();
		redo = new Stack<>();
		listObservers = new ArrayList<>();
	}

	/**
	 * Adds the element.
	 *
	 * @param memSystem
	 *            the memento system
	 * @see MementoSystem
	 */
	public void addElement(MementoSystem memSystem) {
		if (!redo.empty())
			redo.clear();

		undo.push(memSystem);
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#addObserver(editor.Observer)
	 */
	@Override
	public void addObserver(Observer o) {
		/* Precondition */
		if (o == null)
			throw new IllegalArgumentException("o is null");
		if (listObservers.contains(o))
			throw new IllegalArgumentException("o is already subscribed");

		/* Treatment */
		listObservers.add(o);
	}

	/**
	 * Can redo?
	 *
	 * @return true, if we can redo
	 */
	public boolean canRedo() {
		return !redo.empty();
	}

	/**
	 * Can undo?
	 *
	 * @return true, if we can undo
	 */
	public boolean canUndo() {
		return !undo.empty();
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : listObservers)
			o.update(this);
	}

	/**
	 * Redo the last undone command.
	 *
	 * @return the memento system
	 * @see MementoSystem
	 */
	public MementoSystem redo() {
		LOGGER.trace("Executing command redo");

		MementoSystem memSysteme = redo.pop();
		undo.push(memSysteme);
		notifyObservers();

		return memSysteme;
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observable#removeObserver(editor.Observer)
	 */
	@Override
	public void removeObserver(Observer o) {
		/* Precondition */
		if (o == null)
			throw new IllegalArgumentException("o is null");
		if (!listObservers.contains(o))
			throw new IllegalArgumentException("o is not subscribed");

		/* Treatment */
		listObservers.remove(o);
	}

	/**
	 * Undo the last executed command.
	 *
	 * @return the memento system
	 * @see MementoSystem
	 */
	public MementoSystem undo() {
		LOGGER.trace("Executing command undo");

		MementoSystem memSysteme = undo.pop();
		redo.push(memSysteme);

		notifyObservers();
		return undo.peek();
	}
}
