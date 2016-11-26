package gui;

import javax.swing.JButton;

import editor.Enregistreur;
import editor.Observable;
import editor.Observateur;

/**
 * Ce bouton est chargé de se mettre à jour (au niveau de son état cliquable ou non) à chaque modification de l'enregistreur
 * @see Enregistreur
 */
public class BoutonJouer extends JButton implements Observateur {

	private static final long serialVersionUID = -6273371891402818469L;

	public BoutonJouer(){
		
		setEnabled(false);
	}
	
	/**
	 * @see Observateur
	 */
	@Override
	public void miseAJour(Observable o) {
		
		if(o == null){
			
			throw new IllegalArgumentException("o est à null");
		}
		
		if(!(o instanceof Enregistreur)){
			
			throw new IllegalArgumentException("o n'est pas du type Enregistreur");
		}
		
		Enregistreur enregistreur = (Enregistreur) o;
		
		if(enregistreur.getEnregistrer()){
			
			setEnabled(false);
		}
		else{
			
			setEnabled(true);
		}
	}
}