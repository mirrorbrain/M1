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
 * Stop asks the recorder to stop recording commands.
 *
 * @see Recorder
 */
public class Stop implements Command {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Stop.class.getName());

	/** The recorder. */
	private Recorder recorder;

	/**
	 * Instantiates a new stop.
	 *
	 * @param recorder
	 *            the recorder
	 */
	public Stop(Recorder recorder) {
		/* Precondition */
		if (recorder == null)
			throw new IllegalArgumentException("recorder is null");

		/* Treatment */
		this.recorder = recorder;
	}

	/*
	 * (non-Javadoc)
	 * @see commands.Command#execute()
	 */
	@Override
	public void execute() {
		LOGGER.trace("Executing command stop");
		recorder.deactivate();
	}
}