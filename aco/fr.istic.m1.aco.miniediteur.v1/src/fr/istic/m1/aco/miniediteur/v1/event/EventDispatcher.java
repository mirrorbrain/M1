/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Ma�l Nogues mael.nogues@etudiant.univ-rennes1.fr
 * @author Mathieu Grandmontagne mathieu.grandmontagne@etudiant.univ-rennes1.fr
 */
package fr.istic.m1.aco.miniediteur.v1.event;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class EventDispatcher {

	public ConcurrentMap<EventTypeEnum, CopyOnWriteArraySet<EventManager>> EventManagers = new ConcurrentHashMap<>();

	public EventDispatcher() {
		for (EventTypeEnum e : EventTypeEnum.values())
			EventManagers.put(e, new CopyOnWriteArraySet<>());
	}

	public void dispatch(EventTypeEnum event, Subject object) {
		if (event == null)
			return;
		for (EventManager manager : EventManagers.get(event))
			manager.executeEvent(event, object);
	}

	public void enlist(EventTypeEnum event, EventManager manager) {
		if (event == null)
			return;
		EventManagers.get(event).add(manager);
	}

	public void unlist(EventTypeEnum event, EventManager manager) {
		if (event == null)
			return;
		EventManagers.get(event).remove(manager);
	}
}
