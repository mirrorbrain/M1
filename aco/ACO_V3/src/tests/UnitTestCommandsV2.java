/*
 * This is a scholar project for the ACO course of the M1 System & Network of
 * the ISTIC
 * @author Maël Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu GrandMontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import editor.Recorder;
import engine.EditionEngine;
import engine.ImplementedEngine;
import engine.Selection;
import recordables.CopyRecordable;
import recordables.CutRecordable;
import recordables.DelTextRecordable;
import recordables.InsTextRecordable;
import recordables.PasteRecordable;
import recordables.SelectRecordable;

/**
 * The Class UnitTestCommandsV2.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ImplementedEngine.class, Recorder.class })
public class UnitTestCommandsV2 {

	/** The engine. */
	private static EditionEngine engine;

	/** The recorder. */
	private static Recorder recorder;

	/**
	 * Instantiates a new unit test commands V 2.
	 */
	public UnitTestCommandsV2() {
		engine = PowerMockito.mock(ImplementedEngine.class);
		recorder = PowerMockito.mock(Recorder.class);
	}

	/**
	 * Test copy recordable.
	 */
	@Test
	public void testCopyRecordable() {
		CopyRecordable cmd = new CopyRecordable(engine, recorder);
		cmd.execute();
		Mockito.verify(recorder).record(Matchers.eq(cmd));
	}

	/**
	 * Test cut recordable.
	 */
	@Test
	public void testCutRecordable() {
		CutRecordable cmd = new CutRecordable(engine, recorder);
		cmd.execute();
		Mockito.verify(recorder).record(Matchers.eq(cmd));
	}

	/**
	 * Test text deletion recordable.
	 */
	@Test
	public void testDelTextRecordable() {
		DelTextRecordable cmd = new DelTextRecordable(engine, recorder);
		cmd.execute();
		Mockito.verify(recorder).record(Matchers.eq(cmd));
	}

	/**
	 * Test text insertion recordable.
	 */
	@Test
	public void testInsTextRecordable() {
		InsTextRecordable cmd = new InsTextRecordable(engine, recorder, "Test");
		cmd.execute();
		Mockito.verify(engine).insertText(Matchers.eq("Test"));
		Mockito.verify(recorder).record(Matchers.eq(cmd));
	}

	/**
	 * Test paste recordable.
	 */
	@Test
	public void testPasteRecordable() {
		PasteRecordable cmd = new PasteRecordable(engine, recorder);
		cmd.execute();
		Mockito.verify(recorder).record(Matchers.eq(cmd));
	}

	/**
	 * Test select recordable.
	 */
	@Test
	public void testSelectRecordable() {
		SelectRecordable cmd = new SelectRecordable(engine, recorder, new Selection(0, 4));
		cmd.execute();
		Mockito.verify(recorder).record(Matchers.eq(cmd));
	}

}
