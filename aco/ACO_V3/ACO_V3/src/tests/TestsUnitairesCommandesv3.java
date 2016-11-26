package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import commands.Defaire;
import commands.Refaire;
import engine.EditionEngine;
import engine.MoteurImplementation;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MoteurImplementation.class)
public class TestsUnitairesCommandesv3 {
	
	private EditionEngine moteur;

	
	@Before
	public void setUp(){
		
		moteur = PowerMockito.mock(MoteurImplementation.class);
	}
	
	@Test
	public void testDefaire() {
		
		Defaire cmd = new Defaire(moteur);
		cmd.executer();
		Mockito.verify(moteur).defaire();
	}
	
	@Test
	public void testRefaire() {
		
		Refaire cmd = new Refaire(moteur);
		cmd.executer();
		Mockito.verify(moteur).refaire();
	}
}