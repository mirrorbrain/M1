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
package event;

/**
 * The Interface Subject.
 */
public interface Subject {

	/** The event dispatcher. */
	EventDispatcher ed = EventDispatcher.getInstance();

	/** The selection positions (selection[0] = start, selection[1] = end). */
	int[] selection = { 0, 0 };
}