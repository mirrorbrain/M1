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

@RunWith(PowerMockRunner.class)
public class TestsIntegrationv3 {

	private ImplementedEngine	engine;
	private GUITest				gui;
	private Recorder			recorder;
	private HistoryManager		manager;

	@Before
	public void setUp() throws Exception {
		engine = new ImplementedEngine();
		recorder = new Recorder();
		manager = new HistoryManager();
		engine.setHistory(manager);
		gui = new GUITest();
		engine.getBuffer().addObserver(gui);
	}

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