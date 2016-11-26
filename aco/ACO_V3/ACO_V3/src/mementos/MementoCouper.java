package mementos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import editor.Enregistreur;
import engine.EditionEngine;
import recordables.CouperEnregistrable;

/**
 * Cette classe est chargée de stocker l'état d'une commande CouperEnregistrable
 * @see CouperEnregistrable
 * @see MementoCommande
 */
public final class MementoCouper extends MementoCommande{

	private static final Logger LOGGER = LogManager.getLogger(MementoCouper.class.getName());	
	
	public MementoCouper(EditionEngine moteur, Enregistreur enregistreur){
		
		super(moteur, enregistreur);
		LOGGER.trace("Création d'un MementoCouper");
	}
}