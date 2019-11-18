import java.util.Iterator;

/**
 * A list is an ordered collection (also known as a sequence). The user of this
 * interface has precise control over where in the List each element is
 * inserted. The user can access elements by their integer index (position) in
 * the List, and remove elements from the List. The List interface provides a
 * iterator. Although similar in purpose, this interface differs from
 * java.util.List.
 */

public interface List<T> extends Iterable<T> {

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return number of elements in the list
	 */
	int size();

	/**
	 * Tests whether the list is empty.
	 * 
	 * @return true if the list is empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Returns (but does not remove) the element at index i.
	 * 
	 * @param i the index of the element to return
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   size()-1
	 */
	T get(int i) throws IndexOutOfBoundsException;

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
	T set(int i, T e) throws IndexOutOfBoundsException;

	/**
	 * Inserts the given element at the specified index of the list, shifting all
	 * subsequent elements in the list one position further to make room.
	 * 
	 * @param i the index at which the new element should be stored
	 * @param e the new element to be stored
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   size()
	 */
	void add(int i, T e) throws IndexOutOfBoundsException;

	/**
	 * Removes and returns the element at the given index, shifting all subsequent
	 * elements in the list one position closer to the front.
	 * 
	 * @param i the index of the element to be removed
	 * @return the element that had be stored at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than
	 *                                   size()
	 */
	T remove(int i) throws IndexOutOfBoundsException;

	/**
	 * Returns an iterator of the elements stored in the list.
	 * 
	 * @return iterator of the list's elements
	 */
	Iterator<T> iterator();
}
