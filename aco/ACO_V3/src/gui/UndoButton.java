package gui;

import javax.swing.JButton;

import editor.GestionnaireHisto;
import editor.Observable;
import editor.Observateur;

/**
 * this class implement the behavior of the UndoButton (enable/disable)
 *
 * @see HistoryManager
 */
public class UndoButton extends JButton implements Observer {

	private static final long serialVersionUID = -6273371891402818469L;

	public UndoButton() {

		setEnabled(false);
	}

	/**
	 * @see Observateur
	 */
	@Override
	public void update(Observable o) {

		if(o == null){
			throw new IllegalArgumentException("Null o");
		if(!(o instanceof GestionnaireHisto))
			throw new IllegalArgumentException("o not type HistoryManager");

		GestionnaireHisto gestionnaire = (GestionnaireHisto) o;

		if(gestionnaire.peutDefaire())
			setEnabled(true);
		else
			setEnabled(false);
	}
}
