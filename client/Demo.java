package client;

import java.util.ArrayList;
import java.util.List;

import algorithms.RadixSort;
import storage.ArrayQueue;

/**
 * Demo for playing with various queue structures.
 * 
 * @author Willow Sapphire
 * @version 04/05/2024
 */
public class Demo
{
    /**
     * Main method to run tests.
     * 
     * @param args unused, unless...
     */
    public static void main(String[] args)
    {
        // Play with the queue classes or sorts here.
        // Some methods that use the sorts are provided for you.

        List<String> myList = new ArrayList<>();
        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("D");
        ArrayQueue<String> myQueue = new ArrayQueue<>(myList);
        System.out.println(myQueue);
    }

    /**
     * Tests the alphabeticalRadixSort method of the RadixSort class.
     * Prints the data before and after sorting for visual inspection.
     */
    public static void testAlphabeticalRadixSort()
    {
        List<String> words = randomStringList(10, 10);
        System.out.println("Before Sorted");
        for (String word : words)
        {
            System.out.println(word);
        }
        RadixSort.alphabeticalRadixSort(words);
        System.out.println("Sorted");
        for (String word : words)
        {
            System.out.println(word);
        }
    }

    /**
     * Tests the intRadixSort method of the RadixSort class.
     * Prints the data before and after sorting for visual inspection.
     */
    public static void testIntRadixSort()
    {
        List<Integer> myList = randomIntList(10, 200);
        System.out.println("Before Sorted");
        for (int i : myList)
        {
            System.out.println(i);
        }
        RadixSort.intRadixSort(myList);
        System.out.println("Sorted");
        for (int i : myList)
        {
            System.out.println(i);
        }
    }

    /**
     * Generates a list containg a number of random integers.
     * Generated numbers will be between 0 and maxNum.
     * 
     * @param length the number of integers to put in the list
     * @param maxNum the highest acceptable number
     * 
     * @return the list of random ints
     */
    public static List<Integer> randomIntList(int length, int maxNum)
    {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < length; i++)
        {
            a.add((int) (Math.random() * maxNum + 1));
        }
        return a;
    }

    /**
     * Generates a list containg a number of random strings.
     * Strings may contain only upper or lower case letters.
     * 
     * @param length the number of strings to put in the list
     * @param maxSize the maximum acceptable length for a single string
     * 
     * @return the list of random strings
     */
    public static List<String> randomStringList(int length, int maxSize)
    {
        List<String> a = new ArrayList<>();
        for (int i = 0; i < length; i++)
        {
            int sLength = (int) (Math.random() * maxSize + 1);
            String w = "";
            for (int j = 0; j < sLength; j++)
            {
                w += (char) (Math.random() > 0.5
                    ? (int) (Math.random() * 26 + 97)
                    : (int) (Math.random() * 26 + 65));
            }
            a.add(w);
        }
        return a;
    }
}