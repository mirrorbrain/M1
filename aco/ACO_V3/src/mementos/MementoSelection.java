package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.Selection;

/**
 * Ce memento est chargé de sauvegarder/restaurer l'état d'un objet Selection
 *
 * @see Selection
 */
public class MementoSelection {

	private static final Logger LOGGER = LogManager.getLogger(MementoSelection.class.getName());

	private int	start;
	private int	end;

	/**
	 * Crée le memento à partir de l'état de la selection
	 * Le début doit être inférieur ou égal à la end
	 *
	 * @param start
	 *            Le début de la sélection (positif)
	 * @param end
	 *            La end de la sélection (positif)
	 */
	public MementoSelection(int start, int end) {
		/* Precondition */
		if (start < 0)
			throw new IllegalArgumentException("start < 0");
		if (start > end)
			throw new IllegalArgumentException("start > end");

		/* Treatment */
		this.start = start;
		this.end = end;

		LOGGER.trace("Création d'un MementoSelection");
	}

	/**
	 * @return La end de la sélection précédemment sauvegardée
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @return Le début de la sélection précédemment sauvegardé
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Change l'attribut end de l'objet recevant le message.
	 * La nouvelle end doit être supérieur ou égale à l'attribut début actuel
	 */
	public void setEnd(int end) {
		/* Precondition */
		if (end < 0)
			throw new IllegalArgumentException("end < 0");
		if (start > end)
			throw new IllegalArgumentException("start > end");

		/* Treatment */
		this.end = end;
	}

	/**
	 * Change l'attribut start de l'objet recevant le message.
	 * Le nouveau start doit être inférieur ou égal à l'attribut end actuel
	 *
	 * @param start
	 *            Le start qui sera sauvegardé dans le memento (positif)
	 */
	public void setStart(int start) {
		/* Precondition */
		if (start < 0)
			throw new IllegalArgumentException("start < 0");
		if (start > end)
			throw new IllegalArgumentException("start > end");

		/* Treatment */
		this.start = start;
	}
}
