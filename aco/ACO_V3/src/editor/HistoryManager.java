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
 * Cette classe est chargée de gérer les actions défaire/redo lorsque
 * l'utilisateur les demandent au travers des commandes portant le même nom.
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
	 * Permet d'ajouter un état du système au gestionnaire d'historique.
	 *
	 * @param memSystem
	 *            Le MementoSystem contenant l'état du système
	 * @see MementoSystem
	 */
	public void addElement(MementoSystem memSystem) {
		if (!redo.empty())
			redo.clear();

		undo.push(memSystem);

		System.out.println("Taille de undo :" + undo.size());
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
	 * Can redo.
	 *
	 * @return True si on peut effectuer une action redo, False sinon
	 */
	public boolean canRedo() {

		return !redo.empty();
	}

	/**
	 * Can undo.
	 *
	 * @return True si on peut effectuer une action undo, False sinon
	 */
	public boolean canUndo() {

		return undo.size() > 1;
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
	 * Permet d'annuler une action undo.
	 *
	 * @return Un MementoSystem permettant de rétablir le système à l'état T+1
	 *         (précédemment T-1)
	 * @see MementoSystem
	 */
	public MementoSystem redo() {

		LOGGER.trace("On effectue un redo");

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
	 * Rétablit le système à l'état T-1 (T représentant le temps au moment de
	 * l'exécution de cette méthode).
	 *
	 * @return Un MementoSystem permettant de rétablir le système à l'état T-1
	 * @see MementoSystem
	 */
	public MementoSystem undo() {

		LOGGER.trace("On effectue un undo");

		if (undo.size() > 1) {

			MementoSystem memSysteme = undo.pop();
			redo.push(memSysteme);
		}

		notifyObservers();
		return undo.peek();
	}
}
