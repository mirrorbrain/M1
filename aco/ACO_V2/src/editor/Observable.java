/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package editor;

/**
 * The Observable interface describes objects that notifies its observers when
 * its content changes.
 *
 * @see Observer
 */
public interface Observable {
	/**
	 * Add an observer in the list of observers on this object. The newly added
	 * observer will be notified of any future changes on the content of this
	 * object.
	 *
	 * @param o
	 *            the observer to add
	 */
	public void addObserver(Observer o);

	/**
	 * Notifies all the observers in the list that there have been a change in
	 * the content of this object.
	 */
	public void notifyObservers();

	/**
	 * Remove an observer from the list of observers on this object. The removed
	 * observer will not be notified of any future changes on the content of
	 * this object.
	 *
	 * @param o
	 *            the observer to remove
	 */
	public void removeObserver(Observer o);
}
