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
 * this class implement the behavior of the replayButton (enable/disable)
 *
 * @see Recorder
 */
public class StopButton extends JButton implements Observer {

	private static final long serialVersionUID = -6273371891402818469L;

	/**
	 * @see Observer
	 */
	@Override
	public void update(Observable o) {

		if (o == null)
			throw new IllegalArgumentException("NULL o");

		if (!(o instanceof Recorder))
			throw new IllegalArgumentException("o not type Recorder");

		Recorder recorder = (Recorder) o;

		if (recorder.getRecord())
			setEnabled(true);

		else
			setEnabled(false);
	}
}