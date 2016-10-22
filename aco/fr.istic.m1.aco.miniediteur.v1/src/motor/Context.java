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

/**
 * The Context.
 */
public class Context {

	/** The instance. */
	private static Context INSTANCE = new Context();

	/**
	 * Gets the single instance of Context.
	 *
	 * @return single instance of Context
	 */
	public static Context getInstance() {
		return INSTANCE;
	}

	/** The clip board. */
	private StringBuffer clipBoard;

	/** The selected text. */
	private StringBuffer selected;

	/** The text. */
	private StringBuffer text;

	/**
	 * Instantiates a new context.
	 */
	private Context() {
		clipBoard = new StringBuffer();
		selected = new StringBuffer();
		text = new StringBuffer();
	}

	/**
	 * Gets the clip board.
	 *
	 * @return the clip board
	 */
	public StringBuffer getClipBoard() {
		return clipBoard;
	}

	/**
	 * Gets the selected text.
	 *
	 * @return the selected text
	 */
	public StringBuffer getSelected() {
		return selected;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public StringBuffer getText() {
		return text;
	}

	/**
	 * Sets the clip board.
	 *
	 * @param clipBoard
	 *            the new clip board
	 */
	public void setClipBoard(String clipBoard) {
		this.clipBoard.delete(0, this.clipBoard.length());
		this.clipBoard.append(clipBoard);
	}

	/**
	 * Sets the selected text.
	 *
	 * @param selected
	 *            the new selected text
	 */
	public void setSelected(String selected) {
		this.selected.delete(0, this.selected.length());
		this.selected.append(selected);
	}

	/**
	 * Sets the text.
	 *
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		this.text.delete(0, this.text.length());
		this.text.append(text);
	}
}