package storage;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Queue Abstract Data Type (ADT) implemented using an Array.
 * 
 * @author Willow Sapphire
 * @version 04/04/2024
 */
public class ArrayQueue<T> implements Cloneable, Iterable<T>
{
    /**
     * The capacity of the array to use when none is provided.
     */
    public static final int DEFAULT_CAPACITY = 10;

    /**
     * Items in the queue. The front of the queue is data[front].
     * The back of the queue is data[rear].
     */
    private Object[] data;

    /**
     * Tracks the number of items in the queue.
     */
    private int manyItems;

    /**
     * The index of the front of the queue in the array.
     */
    private int front;

    /**
     * The index of the rear of the queue in the array.
     */
    private int rear;

    /**
     * Creates a new empty queue with default capacity.
     */
    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates a new empty queue with a provided capacity.
     * Note that this constructor exposes to the user that an array is being used.
     * 
     * @param initialCapacity the capacity to set the array to.
     */
    public ArrayQueue(int initialCapacity)
    {
        data = new Object[initialCapacity];
        manyItems = 0;
        front = rear = -1;
    }

    /**
     * Creates a new queue with provided data.
     * The data at the beginning of the list will be at the front of the queue.
     * 
     * @param input the data to add to the queue
     */
    public ArrayQueue(List<T> input)
    {
        front = 0;
        manyItems = 0;
        data = new Object[input.size()];
        for (T item : input)
        {
            data[manyItems++] = item;
        }
        rear = manyItems - 1;
    }

    /**
     * Retrieves the element at the top of the queue.
     * The element is NOT removed from the queue.
     * 
     * @return the element at the top of the queue
     * @throws NoSuchElementException if there are no elements on the queue
     */
    @SuppressWarnings("unchecked")
    public T examine()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        return (T) data[front];
    }

    /**
     * Retrieves an element from the top of the queue.
     * The element retrieved is removed from the queue.
     * 
     * @return the element at the top of the queue
     * @throws NoSuchElementException if there are no elements on the queue
     */
    @SuppressWarnings("unchecked")
    public T dequeue()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        T ret = (T) data[front];
        front = (front + 1) % data.length;
        manyItems--;
        return ret;
    }

    /**
     * Adds an element to the top of the queue.
     * 
     * @param data the element to add
     */
    public void enqueue(T item)
    {
        if (size() == data.length)
        {
            ensureCapacity(size() * 2 + 1);
        }
        if (front == -1)
        {
            front = rear = 0;
        }
        else
        {
            this.rear = (rear + 1) % data.length;
        }
        data[rear] = item;
        manyItems++;
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return manyItems == 0;
    }

    /**
     * Getter for the number of elements in the queue.
     * 
     * @return the number of elements in the queue.
     */
    public int size()
    {
        return manyItems;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayQueue<T> clone()
    {
        try
        {
            ArrayQueue<T> clone = (ArrayQueue<T>) super.clone();
            System.arraycopy(data, 0, clone.data, 0, data.length);
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
        String res = "<";
        for (int i = data.length - 1; i >= 0; i--)
        {
            res += data[i] + ", ";
        }
        return res.substring(0, res.length() - 2) + ">";
    }

    @Override
    public Iterator<T> iterator()
    {
        return new AQIterator(front);
    }

    /*
     * The following three methods are specific to the array implementation.
     * One could argue that they should be private so that users do not know the
     * underlying implementation. However, making them public allows the users
     * to control the structure in more detail.
     */

    /**
     * Getter for the capacity of the data array.
     * This method should not be necessary, but users could
     * hypothetically use it to save memory.
     * 
     * @return data.length
     */
    public int getCapacity()
    {
        return data.length;
    }

    /**
     * Compare the capacity of data to the provided capacity.
     * If the capacity provided is greater, data is expanded to that size.
     * 
     * It would make more sense to make this method private, since users
     * shouldn't need to call it.
     * 
     * @param capacity the size desired for the data array.
     */
    public void ensureCapacity(int capacity)
    {
        if (capacity > getCapacity())
        {
            Object[] newData = new Object[capacity];
            for (int i = 0; i < data.length; i++)
            {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    /**
     * Reduces the size of the data array to manyItems.
     * Should not really be necessary, but users could use it
     * to save memory.
     */
    public void trimToSize()
    {
        Object[] newData = new Object[manyItems];
        for (int i = 0; i < manyItems; i++)
        {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Simple inner iterator class.
     * Does not implement remove.
     * 
     * @author Willow Sapphire
     * @version 04/05/2024
     */
    private class AQIterator implements Iterator<T>
    {
        /**
         * The index of the next item to return.
         */
        private int currIndex;

        /**
         * Creates a new iterator starting at the beginning of the queue.
         * 
         * @param front the index of the front of the queue in data
         */
        public AQIterator(int front)
        {
            currIndex = front;
        }

        @Override
        public boolean hasNext()
        {
            return currIndex != (rear + 1) % data.length;
        }

        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            @SuppressWarnings("unchecked")
            T retValue = (T) data[currIndex];
            currIndex = (currIndex + 1) % data.length;
            return retValue;
        }
        
    }
}