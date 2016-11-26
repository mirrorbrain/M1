package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.EditionEngine;

/**
 * Cette commande est chargée d'annuler une commande Defaire précédemment exécutée
 * @see Defaire
 */
public class Refaire implements Commande {

	private static final Logger LOGGER = LogManager.getLogger(Refaire.class.getName());	
	
	private final EditionEngine moteur;
	
	/**
	 * Crée la commande
	 * @param moteur Le moteur d'édition auquel adresser la commande (non-null)
	 */
	public Refaire(EditionEngine moteur){
		
		if(moteur == null){
			
			throw new IllegalArgumentException("moteur est à null");
		}
		
		this.moteur = moteur;
	}
	
	@Override
	public void executer() {
		
		LOGGER.trace("Exécution d'une commande Refaire");
		moteur.refaire();
	}
}