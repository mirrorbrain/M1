/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Recorder;

/**
 * Cette commande est chargée d'ordonner à l'recorder de commande de commencer à
 * enregistrer les commandes qui lui sont destinées.
 *
 * @see Recorder
 */
public class Start implements Command {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Start.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/**
	 * Crée la commande.
	 *
	 * @param recorder
	 *            L'recorder à qui adresser la commande (non null)
	 */
	public Start(Recorder recorder) {

		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		this.recorder = recorder;
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public void execute() {

		LOGGER.trace("Executing command start");
		recorder.activate();
	}
}
