package gui;

import javax.swing.JButton;

import editor.HistoryManager;
import editor.Observable;
import editor.Observer;

/**
 * RedoButton implements the behavior of the redoButton (enable/disable).
 *
 * @see HistoryManager
 */
public class RedoButton extends JButton implements Observer {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6273371891402818469L;

	/**
	 * Instantiates a new redo button.
	 */
	public RedoButton() {
		setEnabled(false);
	}

	/*
	 * (non-Javadoc)
	 * @see editor.Observer#update(editor.Observable)
	 */
	@Override
	public void update(Observable o) {
		/* Precondition */
		if (o == null)
			throw new IllegalArgumentException("o is null");
		if (!(o instanceof HistoryManager))
			throw new IllegalArgumentException("o is not an HistoryManager");

		/* Treatment */
		HistoryManager gestionnaire = (HistoryManager) o;
		setEnabled(gestionnaire.canRedo());
	}
}
