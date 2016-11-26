package editor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import engine.MoteurImplementation;
import gui.IHM;

/**
 * Cette classe est chargée d'instancier les classes nécessaires à l'éditeur, les lier si nécessaire et de lancer l'interface graphique
 */
public final class ClientEditeur
{
	private static final Logger LOGGER = LogManager.getLogger(ClientEditeur.class.getName());	
	
	public static void main(String[] args) {
		
		final Enregistreur enregistreur = new Enregistreur();
		final GestionnaireHisto gestionnaireHisto = new GestionnaireHisto();
		final MoteurImplementation moteur = new MoteurImplementation();
		
		final IHM ihm = new IHM(moteur, enregistreur, gestionnaireHisto);
		
		moteur.getBuffer().ajouterObservateur(ihm);
		moteur.setHistorique(gestionnaireHisto);
		
		LOGGER.trace("Mise en place des différents éléments de l'application effectué, prêt à l'utilisation !");
	}
}
