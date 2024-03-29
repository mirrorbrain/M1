/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import commands.Redo;
import commands.Undo;
import editor.HistoryManager;
import editor.Recorder;
import engine.ImplementedEngine;
import recordables.InsTextRecordable;

/**
 * The Class TestsIntegrationv3.
 */
@RunWith(PowerMockRunner.class)
public class TestsIntegrationv3 {

	/** The engine. */
	private ImplementedEngine engine;

	/** The gui. */
	private GUITest gui;

	/** The recorder. */
	private Recorder recorder;

	/** The manager. */
	private HistoryManager manager;

	/**
	 * Sets up the testing environment.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		engine = new ImplementedEngine();
		recorder = new Recorder();
		manager = new HistoryManager();
		engine.setHistory(manager);
		gui = new GUITest();
		engine.getBuffer().addObserver(gui);
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
		// Add text and undo
		new InsTextRecordable(engine, recorder, "Test").execute();
		new Undo(engine).execute();
		assertEquals("", gui.getLastInsert());

		// Redo insertion
		new Redo(engine).execute();
		assertEquals("Test", gui.getLastInsert());

		// Trying redo after insertion
		new Undo(engine).execute();
		new InsTextRecordable(engine, recorder, "a").execute();
		new Redo(engine).execute();
		assertEquals("a", gui.getLastInsert());
	}
}
