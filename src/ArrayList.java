import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array list is a dynamic array with a flexible size or resizable-array
 * implementation of array. Standard Java arrays are of a fixed length. After
 * arrays are created, they cannot grow or shrink, which means you must know in
 * advance how many elements an array will hold. Array lists are created with an
 * initial size. When this size is exceeded, the collection is automatically
 * enlarged. When objects are removed, the array may be shrunk.
 */

public class ArrayList<T> implements List<T> {

	// instance variables
	/**
	 * Default array capacity.
	 */
	private static final int CAPACITY = 10; // default array capacity

	/**
	 * Generic array used for storage of list elements.
	 */
	private T[] data; // generic array used for storage

	/**
	 * Current number of elements in the list.
	 */
	private int size = 0; // current number of elements

	// constructors
	/**
	 * Creates an array list with default initial capacity.
	 */
	public ArrayList() {
		this(CAPACITY);
	} // constructs list with default capacity

	/**
	 * Creates an array list with given initial capacity.
	 */
	@SuppressWarnings({ "unchecked" })
	public ArrayList(int capacity) { // constructs list with given capacity
		data = (T[]) new Object[capacity]; // safe cast; compiler may give warning
	}

	// public methods
	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Tests whether the array list is empty.
	 * 
	 * @return true if the array list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns (but does not remove) the element at index i.
	 * 
	 * @param i the index of the element to return
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   size()-1
	 */
	public T get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}

	/**
	 * Replaces the element at the specified index, and returns the element
	 * previously stored.
	 * 
	 * @param i the index of the element to replace
	 * @param e the new element to be stored
	 * @return the previously stored element
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   size()-1
	 */
	public T set(int i, T e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		T answer = data[i];
		data[i] = e;
		return answer;
	}

	/**
	 * Inserts the given element at the specified index of the list, shifting all
	 * subsequent elements in the list one position further to make room.
	 * 
	 * @param i the index at which the new element should be stored
	 * @param e the new element to be stored
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   size()
	 */
	public void add(int i, T e) {
		checkIndex(i, size + 1);
		if (size == data.length) // not enough capacity
			resize(2 * data.length); // so double the current capacity
		for (int k = size - 1; k >= i; k--) { // start by shifting rightmost
			data[k + 1] = data[k];
		}
		data[i] = e; // ready to place the new element
		size++;
	}

	/**
	 * Removes and returns the element at the given index, shifting all subsequent
	 * elements in the list one position closer to the front.
	 * 
	 * @param i the index of the element to be removed
	 * @return the element that had be stored at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   size()
	 */
	public T remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		T answer = data[i];
		for (int k = i; k < size - 1; k++) { // shift elements to fill hole
			data[k] = data[k + 1];
		}
		data[size - 1] = null; // help garbage collection
		size--;
		return answer;
	}

	/**
	 * Produces a string representation of the contents of the array list.
	 * 
	 * @return textual representation of the array list
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		for (int j = 0; j < size; j++) {
			if (j > 0) {
				sb.append(", ");
			}
			sb.append(data[j]);
		}
		sb.append(")");
		return sb.toString();
	}

	/* utility methods */
	/**
	 * Resizes internal array to have given capacity >= size.
	 * 
	 * @param capacity the enlarged capacity
	 */
	@SuppressWarnings({ "unchecked" })
	protected void resize(int capacity) {
		T[] temp = (T[]) new Object[capacity]; // safe cast; compiler may give warning
		for (int k = 0; k < size; k++) {
			temp[k] = data[k];
		}
		data = temp; // start using the new array
	}

	/**
	 * Checks whether the given index is in the range [0, n-1].
	 * 
	 * @param i index to be checked
	 * @param n the upper bound of the range
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   size()
	 */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
	}

	/**
	 * Returns an iterator of the elements stored in the list.
	 * 
	 * @return iterator of the list's element
	 */
	public Iterator<T> iterator() {
		return new ArrayIterator(); // create a new instance of the inner class
	}

	// nested ArrayIterator class
	/**
	 * A nonstatic nested class. Note well that each instance contains an implicit
	 * reference to the containing list, allowing it to access the list's members.
	 */
	private class ArrayIterator implements Iterator<T> {

		// instance variables
		/**
		 * Index of the next element to report.
		 */
		private int j = 0; // index of the next element to report
		private boolean removable = false; // can remove be called at this time?

		/**
		 * Tests whether the iterator has a next object.
		 * 
		 * @return true if there are further objects, false otherwise
		 */
		public boolean hasNext() {
			return j < size;
		} // size is field of outer instance

		/**
		 * Returns the next object in the iterator.
		 * 
		 * @return next object
		 * @throws NoSuchElementException if there are no further elements
		 */
		public T next() throws NoSuchElementException {
			if (j == size) {
				throw new NoSuchElementException("No next element");
			}
			removable = true; // this element can subsequently be removed
			return data[j++]; // post-increment j, so it is ready for future call to next
		}

		/**
		 * Removes the element returned by most recent call to next.
		 * 
		 * @throws IllegalStateException if next has not yet been called
		 * @throws IllegalStateException if remove was already called since recent next
		 */
		public void remove() throws IllegalStateException {
			if (!removable) {
				throw new IllegalStateException("Nothing to remove");
			}
			ArrayList.this.remove(j - 1); // that was the last one returned
			j--; // next element has shifted one cell to the left
			removable = false; // do not allow remove again until next is called
		}
	}
}
