package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import commands.Coller;
import commands.Copier;
import commands.Couper;
import commands.InsererTexte;
import commands.Selectionner;
import commands.SupprimerTexte;
import engine.MoteurImplementation;
import engine.Selection;

@RunWith(PowerMockRunner.class)
public class TestsIntegrationv1 {

	private MoteurImplementation moteur;
	private IHMTest ihm;
	
	@Before
	public void setUp() throws Exception {
		
		moteur = new MoteurImplementation();
		ihm = new IHMTest();
		moteur.getBuffer().ajouterObservateur(ihm);
	}

	@Test
	public void insertionSuppression() {

		//Insertion du texte
		new InsererTexte(moteur, "Test").executer();
		assertEquals("Test", ihm.getDerniereInsert());
		
		//Suppression dees deux derniers caractères
		new SupprimerTexte(moteur).executer();
		new SupprimerTexte(moteur).executer();
		assertEquals("Te", ihm.getDerniereInsert());
		
		//S�lection des deux premier caractères
		new Selectionner(moteur, new Selection(0, 2)).executer();
		
		//suppression de la sélection
		new SupprimerTexte(moteur).executer();
		assertEquals("", ihm.getDerniereInsert());
	}

	@Test
	public void couperColler() {

		//Insertion du texte
		new InsererTexte(moteur, "Test").executer();

		//S�lection des deux premier caractères
		new Selectionner(moteur, new Selection(2, 4)).executer();

		//Coupe de la sélection
		new Couper(moteur).executer();
		assertEquals("Te", ihm.getDerniereInsert());
		
		//Collage
		new Selectionner(moteur, new Selection(0, 0)).executer();
		new Coller(moteur).executer();
		assertEquals("stTe", ihm.getDerniereInsert());
	}
	
	@Test
	public void copierColler() {

		//Insertion du texte
		new InsererTexte(moteur, "Test").executer();

		//S�lection dela chaine
		new Selectionner(moteur, new Selection(0, 4)).executer();

		//Coupe de la sélection
		new Copier(moteur).executer();
		assertEquals("Test", ihm.getDerniereInsert());
		
		//Collage de remplacement
		new Coller(moteur).executer();
		assertEquals("Test", ihm.getDerniereInsert());
		
		//Collage additionnel
		new Coller(moteur).executer();
		assertEquals("TestTest", ihm.getDerniereInsert());
	}
}