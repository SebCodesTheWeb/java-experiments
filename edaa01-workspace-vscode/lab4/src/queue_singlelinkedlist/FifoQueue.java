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
			QueueNode<E> newestNode = new QueueNode<E>(e);
			if(size == 0) {
				last.next = newestNode;
				last.next.next = newestNode;
			}
			if(size >= 1) {
				newestNode.next = last.next.next;
				last.next.next = newestNode;
				last.next = newestNode;
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
		return last.next.next.element;
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
			last.next.next = last.next.next.next;
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
      private QueueNode<E> pos = last.next;
	  private boolean hasStarted = false;

      private QueueIterator() {

	  }
      public boolean hasNext() {
		return size >= 1 && ((pos != last.next) || !hasStarted);
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
