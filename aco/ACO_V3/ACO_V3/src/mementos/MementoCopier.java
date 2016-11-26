package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Enregistreur;
import engine.EditionEngine;
import recordables.CopierEnregistrable;

/**
 * Cette classe est chargée de stocker l'état d'une commande CopierEnregistrable
 * @see CopierEnregistrable
 * @see MementoCommande
 */
public final class MementoCopier extends MementoCommande{

	private static final Logger LOGGER = LogManager.getLogger(MementoCopier.class.getName());	
	
	public MementoCopier(EditionEngine moteur, Enregistreur enregistreur){
		
		super(moteur, enregistreur);
		LOGGER.trace("Création d'un MementoCopier");
	}
}