package storage;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Queue Abstract Data Type (ADT) implemented using a Linked List.
 * The linked list is managed using the java LinkedList class.
 * 
 * @author Willow Sapphire
 * @version 04/04/2024
 */
public class LinkedListQueue<T> implements Cloneable, Iterable<T>
{
    /**
     * Reference to the data in the queue.
     * The front of the queue is the first node in the list.
     */
    private LinkedList<T> data;

    /**
     * Creates a new empty queue.
     */
    public LinkedListQueue()
    {
        data = new LinkedList<>();
    }

    /**
     * Creates a new queue with provided data.
     * The data at the beginning of the list will be at the front of the queue.
     * 
     * @param input the data to add to the queue
     */
    public LinkedListQueue(List<T> input)
    {
        data = new LinkedList<>();
        for (T item : input)
        {
            data.addLast(item);
        }
    }

    /**
     * Retrieves the element at the front of the queue.
     * The element is NOT removed from the queue.
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if there are no elements on the queue
     */
    public T examine()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        return data.getFirst();
    }

    /**
     * Retrieves an element from the front of the queue.
     * The element retrieved is removed from the queue.
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if there are no elements on the queue
     */
    public T dequeue()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        return data.removeFirst();
    }

    /**
     * Adds an element to the front of the queue.
     * 
     * @param item the element to add
     */
    public void enqueue(T item)
    {
        data.addLast(item);
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return data.isEmpty();
    }

    /**
     * Getter for the number of elements in the queue.
     * 
     * @return the number of elements in the queue.
     */
    public int size()
    {
        return data.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public LinkedListQueue<T> clone()
    {
        try
        {
            LinkedListQueue<T> clone = (LinkedListQueue<T>) super.clone();
            clone.data = (LinkedList<T>) data.clone();
            return clone;
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException("Clone is not supported");
        }
    }

    @Override
    public String toString()
    {
        Iterator<T> it = data.descendingIterator();
        String res = "<";
        while(it.hasNext()) {
            res += it.next() + ", ";
        }
        return res.substring(0, res.length() - 2) + ">";
    }

    @Override
    public Iterator<T> iterator()
    {
        return data.iterator();
    }
}
