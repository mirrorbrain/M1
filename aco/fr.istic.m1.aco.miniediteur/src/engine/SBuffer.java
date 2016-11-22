/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
package engine;

import java.util.Observable;

/**
 * The Class SBuffer.
 */
public class SBuffer extends Observable {

	/** The buffer. */
	private StringBuffer buffer = new StringBuffer();

	/**
	 * Instantiates a new s buffer.
	 */
	public SBuffer() {}

	/**
	 * Length.
	 *
	 * @return the int
	 */
	public int length() {
		return buffer.length();
	}

	/**
	 * Substring.
	 *
	 * @param selectionStart
	 *            the selection start
	 * @param selectionEnd
	 *            the selection end
	 * @return the string
	 */
	public String substring(int selectionStart, int selectionEnd) {
		return buffer.substring(selectionStart, selectionEnd);
	}

	/**
	 * Delete.
	 *
	 * @param selectionStart
	 *            the selection start
	 * @param selectionEnd
	 *            the selection end
	 */
	public void delete(int selectionStart, int selectionEnd) {
		buffer.delete(selectionStart, selectionEnd);
	}

	/**
	 * Insert.
	 *
	 * @param selectionStart
	 *            the selection start
	 * @param text
	 *            the text
	 */
	public void insert(int selectionStart, String text) {
		buffer.insert(selectionStart, text);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return buffer.toString();
	}

}