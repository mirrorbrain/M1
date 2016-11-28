/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package gui;

import javax.swing.JButton;

import editor.Observable;
import editor.Observer;
import editor.Recorder;

/**
 * ReplayButton implements the behavior of the replayButton (enable/disable)
 *
 * @see Recorder
 */
public class ReplayButton extends JButton implements Observer {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6273371891402818469L;

	/**
	 * Instantiates a new replay button.
	 */
	// the button is disable at the launch
	public ReplayButton() {
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
			throw new IllegalArgumentException("Null o ");
		if (!(o instanceof Recorder))
			throw new IllegalArgumentException("o not type Recorder");

		/* Treatment */
		setEnabled(!((Recorder) o).getRecord());
	}
}