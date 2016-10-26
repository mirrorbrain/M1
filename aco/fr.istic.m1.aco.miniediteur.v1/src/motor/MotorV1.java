/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Ma�l Nogues mael.nogues@etudiant.univ-rennes.fr
 * @author Mathieu Grandmontagne mathieu.grandmontagne@etudiant.univ-rennes.fr
 */
package motor;

import event.EventDispatcher;
import event.EventManager;
import event.EventTypeEnum;
import event.Subject;

/**
 * The Class MotorV1.
 */
public class MotorV1 implements EventManager {

	/** The event dispatcher. */
	private static EventDispatcher	ed	= EventDispatcher.getInstance();
	/** The execution context. */
	private static Context			con	= Context.getInstance();

	/**
	 * Instantiates a new motor version 1.
	 */
	public MotorV1() {
		ed.enlist(EventTypeEnum.COPY, this);
		ed.enlist(EventTypeEnum.CUT, this);
		ed.enlist(EventTypeEnum.INSERT, this);
		ed.enlist(EventTypeEnum.PASTE, this);
		ed.enlist(EventTypeEnum.SAVE, this);
		ed.enlist(EventTypeEnum.SELECT, this);
	}

	/**
	 * Closes the motor.
	 */
	public void close() {
		ed.resign(EventTypeEnum.COPY, this);
		ed.resign(EventTypeEnum.CUT, this);
		ed.resign(EventTypeEnum.INSERT, this);
		ed.resign(EventTypeEnum.PASTE, this);
		ed.resign(EventTypeEnum.SAVE, this);
		ed.resign(EventTypeEnum.SELECT, this);
	}

	/**
	 * Copy the selected text in the Context.clipBoard.
	 */
	private void copy() {
		con.setClipBoard(con.getSelected().toString());
	}

	/**
	 * Cuts the selected text in the Context.clipBoard, deletes the selected
	 * text and sets the selected text to nothing.
	 *
	 * @param object
	 *            the object responsible of this event
	 */
	private void cut(Subject object) {
		con.setClipBoard(con.getSelected().toString());
		int start = con.getText().indexOf(con.getSelected().toString());
		con.getText().delete(start, start + con.getSelected().length());
		con.setSelected("");
	}

	/*
	 * (non-Javadoc)
	 * @see event.EventManager#executeEvent(event.EventTypeEnum, event.Subject)
	 */
	@Override
	public void executeEvent(EventTypeEnum event, Subject object) {
		if (object == null)
			return;
		switch (event) {
			case SELECT:
				select(object);
				break;
			case COPY:
				copy();
				break;
			case CUT:
				cut(object);
				break;
			case PASTE:
				paste(object);
				break;
			case INSERT:
				insert(object);
				break;
			case SAVE:
				save();
				break;
			default:
				System.out.println("Invalid event");
				return;
		}
	}

	/**
	 * Inserts the text from Context.clipBoard in the Context.text.
	 *
	 * @param object
	 *            the object responsible of this event
	 */
	private void insert(Subject object) {
		con.getText().insert(Subject.selection[0], con.getClipBoard().toString());
	}

	/**
	 * Pastes the text in Context.clipBoard.
	 *
	 * @param object
	 *            the object responsible of this event
	 */
	private void paste(Subject object) {
		con.getText().insert(Subject.selection[0], con.getClipBoard().toString());
	}

	/**
	 * Saves the text from Context.text on the disk.
	 */
	private void save() {
		// TODO Auto-generated method stub

	}

	/**
	 * Puts the selected text from the HMI in Context.selected.
	 *
	 * @param object
	 *            the object responsible of this event
	 */
	private void select(Subject object) {
		Context con = Context.getInstance();
		con.setSelected(con.getText().substring(Subject.selection[0], Subject.selection[1]));
	}

}