package storage;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Queue Abstract Data Type (ADT) implemented using a Linked List.
 * The linked list is managed using an internal node class
 * rather than the java LinkedList class.
 * 
 * @author Willow Sapphire
 * @version 04/04/2024
 */
public class ManualLinkedListQueue<T> implements Cloneable, Iterable<T>
{
    /**
     * Reference to item at the front of the queue.
     * Each successive link is the next item down on the queue.
     */
    private Node front;

    /**
     * Reference to the item at the back of the queue.
     * Used to make pushing more efficient.
     * The link of back should always be null.
     */
    private Node back;

    /**
     * Tracker for the number of items in the queue.
     * The sole purpose is to make the size method constant time.
     */
    private int manyItems;

    /**
     * Creates a new empty queue.
     */
    public ManualLinkedListQueue()
    {
        front = back = null;
        manyItems = 0;
    }

    /**
     * Creates a new queue with provided data.
     * The data at the beginning of the list will be at the front of the queue.
     * 
     * @param input the data to add to the queue
     */
    public ManualLinkedListQueue(List<T> input)
    {
        front = back = null;
        manyItems = 0;
        if (!input.isEmpty())
        {
            front = back = new Node(input.get(0));
            for (int i = 1; i < input.size(); i++)
            {
                back.setLink(new Node(input.get(i)));
                back = back.getLink();
                manyItems++;
            }
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
        return front.getData();
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
        T dataToReturn = front.getData();
        front = front.getLink();
        manyItems--;
        return dataToReturn;
    }

    /**
     * Adds an element to the front of the queue.
     * 
     * @param data the element to add
     */
    public void enqueue(T data)
    {
        if (isEmpty())
        {
            front = back = new Node(data);
        }
        else
        {
            back.setLink(new Node(data));
            back = back.getLink();
        }
        manyItems++;
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return front == null;
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
    public Iterator<T> iterator()
    {
        return new MLLQIterator(front);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ManualLinkedListQueue<T> clone()
    {
        try
        {
            ManualLinkedListQueue<T> clone = (ManualLinkedListQueue<T>) super.clone();
            clone.front = front == null ? null : front.copyList();
            for (clone.back = clone.front; clone.back.getLink() != null; clone.back = clone.back.getLink());
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
        String res = ">";
        for (Node i = front; i != null; i = i.getLink())
        {
            res = i.getData() + (i == front ? res : ", " + res);
        }       
        return "<" + res;
    }
    
    /**
     * A simple, generic node class.
     * 
     * @author Willow Sapphire
     * @version 04/04/2024
     */
    private class Node
    {
        /**
         * The data stored in this node.
         */
        private T data;

        /**
         * The next node in a potential chain of nodes.
         */
        private Node link;

        /**
         * Creates a new node with the given data and no link.
         * 
         * @param data the data to store in this node
         */
        public Node(T data)
        {
            this(data, null);
        }

        /**
         * Creates a new node with the given data and link.
         * 
         * @param data the data stored in the node.
         * @param link the node to connect to this one.
         */
        public Node(T data, Node link)
        {
            setData(data);
            setLink(link);
        }

        /**
         * Sets the data of this node.
         * 
         * @param data the data to store in this node
         */
        public void setData(T data)
        {
            this.data = data;
        }

        /**
         * Sets the node connected to this one.
         * 
         * @param link the node to connect
         */
        public void setLink(Node link)
        {
            this.link = link;
        }

        /**
         * Gets the data stored in this node.
         * 
         * @return data
         */
        public T getData()
        {
            return this.data;
        }

        /**
         * Getter for the next node.
         * 
         * @return a reference to the node connected to this one
         */
        public Node getLink()
        {
            return this.link;
        }

        /**
         * Copies this node and the entire list it is connected to.
         * 
         * @return a copy of this node with every node in its chain copied
         */
        public Node copyList()
        {
            Node head = new Node(this.getData());
            for (Node t = this.getLink(), c = head; t.getLink() != null; t = t.getLink(), c = c.getLink())
            {
                c.setLink(new Node(t.getData()));
            }
            return head;
        }
    }

    /**
     * A simple iterator class to go through the queue.
     * 
     * @author Willow Sapphire
     * @version 04/05/2024
     */
    private class MLLQIterator implements Iterator<T>
    {
        /**
         * The node containing the next data to return.
         */
        private Node next;

        /**
         * Creates a new iterator beginning at the provided node.
         * @param start
         */
        public MLLQIterator(Node start)
        {
            this.next = start;
        }

        @Override
        public boolean hasNext()
        {
            return this.next != null;
        }

        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            T retValue = next.getData();
            next = next.getLink();
            return retValue;
        }
    }
}
