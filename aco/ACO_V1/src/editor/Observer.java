/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package editor;

/**
 * The Observer interface describes objects that want to listen to the changes
 * in the content of an observable that it subscribed to.
 *
 * @see Observable
 */
public interface Observer {
	/**
	 * This method is executed when the observer receives a notification from an
	 * observable it subscribed to.
	 *
	 * @param o
	 *            the observable that sent the notification
	 */
	public void update(Observable o);
}
