package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = new QueueNode<E>(null);
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		try{
			QueueNode<E> NodeToAdd = new QueueNode<E>(e);
			if(size == 0) {
				//vid initialisering, appenda noden och loopa runt den till sig själv 
				last.next = NodeToAdd;
				NodeToAdd.next = NodeToAdd;
			}
			if(size >= 1) {
				QueueNode<E> FirstNode = last.next.next;
				QueueNode<E> LastNode = last.next;

				NodeToAdd.next = FirstNode;
				LastNode.next = NodeToAdd;
				last.next = NodeToAdd;
			}
			size += 1;
		} catch(Exception error) {
			return false;
		}
		return false;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;
		}

		QueueNode<E> FirstNode = last.next.next;
		return FirstNode.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		E head = peek();

		if (size == 0) {
			return null;
		}
		if (size == 1) {
			last.next = null;
			size -= 1;
		}
		if (size >= 2) {
			QueueNode<E> FirstNode = last.next.next;
			QueueNode<E> LastNode = last.next;

			LastNode.next = FirstNode.next;
			size -= 1;
		}
		return head;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
	  QueueNode<E> LastNode = last.next;
      private QueueNode<E> pos = LastNode;
	  private boolean hasStarted = false;

      private QueueIterator() {

	  }
      public boolean hasNext() {
		return size >= 1 && ((pos != LastNode) || !hasStarted); // when pos == LastNode then a loop has occured and no more elements to check exist
	  }

      public E next() {
		if(!hasNext()) {
			throw new NoSuchElementException("The iteration has no more elements");
		}
		hasStarted = true;
		E nextElement = pos.next.element;
		pos = pos.next;
		return nextElement;
	  }
   }
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
