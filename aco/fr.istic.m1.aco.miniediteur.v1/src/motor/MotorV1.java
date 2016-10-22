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

import event.EventManager;
import event.EventTypeEnum;
import event.Subject;

/**
 * The Class MotorV1.
 */
public class MotorV1 implements EventManager {

	/**
	 * Copy command.
	 */
	private void copy() {
		Context con = Context.getInstance();
		con.setClipBoard(con.getSelected().toString());
	}

	/**
	 * Cuts command.
	 *
	 * @param object
	 *            the object responsible of this event
	 */
	private void cut(Subject object) {
		Context con = Context.getInstance();
		con.setClipBoard(con.getSelected().toString());
		int start = con.getText().indexOf(con.getSelected().toString());
		con.setText(con.getText().replace(start, start + con.getSelected().length(), "").toString());
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
	 * Inserts the text in Context.clipBoard.
	 *
	 * @param object
	 *            the object responsible of this event
	 */
	private void insert(Subject object) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pastes the text in Context.clipBoard.
	 *
	 * @param object
	 *            the object responsible of this event
	 */
	private void paste(Subject object) {
		// TODO Auto-generated method stub

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
