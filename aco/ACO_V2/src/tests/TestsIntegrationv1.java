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

import commands.Copy;
import commands.Cut;
import commands.DeleteText;
import commands.InsertText;
import commands.Paste;
import commands.Select;
import engine.ImplementedEngine;
import engine.Selection;

/**
 * The Class TestsIntegrationv1.
 */
@RunWith(PowerMockRunner.class)
public class TestsIntegrationv1 {
	/** The engine. */
	private ImplementedEngine engine;

	/** The graphical user interface. */
	private GUITest gui;

	/**
	 * Copy paste.
	 */
	@Test
	public void copyPaste() {
		// Insert text
		new InsertText(engine, "Test").execute();

		// Select string
		new Select(engine, new Selection(0, 4)).execute();

		// Cut selection
		new Copy(engine).execute();
		assertEquals("Test", gui.getLastInsert());

		// Paste
		new Paste(engine).execute();
		assertEquals("Test", gui.getLastInsert());

		// Paste again
		new Paste(engine).execute();
		assertEquals("TestTest", gui.getLastInsert());
	}

	/**
	 * Cut paste.
	 */
	@Test
	public void cutPaste() {
		// Insert text
		new InsertText(engine, "Test").execute();

		// Select 2 first char
		new Select(engine, new Selection(2, 4)).execute();

		// Cut selection
		new Cut(engine).execute();
		assertEquals("Te", gui.getLastInsert());

		// Paste
		new Select(engine, new Selection(0, 0)).execute();
		new Paste(engine).execute();
		assertEquals("stTe", gui.getLastInsert());
	}

	/**
	 * Insert delete.
	 */
	@Test
	public void insertDelete() {
		// Insert text
		new InsertText(engine, "Test").execute();
		assertEquals("Test", gui.getLastInsert());

		// Delete 2 last char
		new DeleteText(engine).execute();
		new DeleteText(engine).execute();
		assertEquals("Te", gui.getLastInsert());

		// Select 2 first char
		new Select(engine, new Selection(0, 2)).execute();

		// Delete selection
		new DeleteText(engine).execute();
		assertEquals("", gui.getLastInsert());
	}

	/**
	 * Sets up the testing environment.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		engine = new ImplementedEngine();
		gui = new GUITest();
		engine.getBuffer().addObserver(gui);
	}
}
