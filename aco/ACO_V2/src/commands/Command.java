/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package commands;

import engine.EditionEngine;

/**
 * Command transmit an order to an object implementing the EditionEngine
 * interface.
 *
 * @see EditionEngine
 */
public interface Command {
	/**
	 * Execute the command in the edition engine.
	 */
	public void execute();
}
