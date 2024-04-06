package algorithms;

import java.util.List;

import storage.ManualLinkedListQueue;
import storage.ArrayQueue;

/**
 * Three variations of radix sort.
 * 
 * @author Willow Sapphire
 * @version 04/05/2024
 */
public class RadixSort
{
    /**
     * Don't make these!
     */
    private RadixSort() {}

    /**
     * The number of possible digits. Uses base 10.
     */
    public static final int NUM_DIGITS = 10;

    /**
     * Number of valid characters for alphabetical sort.
     * All letters (case doesn't matter) and a ` character.
     */
    public static final int NUM_CHARS = 27;

    /**
     * Sorts a list of integers using radix sort.
     * 
     * @param data the list to be sorted.
     */
    @SuppressWarnings("unchecked")
    public static void intRadixSort(List<Integer> data)
    {
        ManualLinkedListQueue<Integer> result = new ManualLinkedListQueue<>(data);
        ManualLinkedListQueue<Integer>[] buckets = new ManualLinkedListQueue[NUM_DIGITS];
        for (int i = 0; i < NUM_DIGITS; i++)
        {
            buckets[i] = new ManualLinkedListQueue<Integer>();
        }
        int mostDigits = getMostDigits(data);
        for (int i = 0, place = 1; i < mostDigits; i++, place *= 10)
        {
            while (!result.isEmpty())
            {
                int item = result.dequeue();
                int digit = item / place % 10;
                buckets[digit].enqueue(item);
            }
            for (ManualLinkedListQueue<Integer> bucket : buckets)
            {
                while(!bucket.isEmpty())
                {
                    result.enqueue(bucket.dequeue());
                }
            }
        }
        for (int i = 0; i < data.size(); i++)
        {
            data.set(i, result.dequeue());
        }
    }

    /**
     * Sorts a list of integers using radix sort.
     * 
     * @param data the list to be sorted.
     */
    @SuppressWarnings("unchecked")
    public static void alphabeticalRadixSort(List<String> data)
    {
        int mostChars = getMostCharacters(data);
        ArrayQueue<String> result = new ArrayQueue<>(data.size());
        for (String w : data)
        {
            result.enqueue(pad(w, mostChars));
        }
        ArrayQueue<String>[] buckets = new ArrayQueue[NUM_CHARS];
        for (int i = 0; i < NUM_CHARS; i++)
        {
            buckets[i] = new ArrayQueue<String>();
        }
        for (int i = 0; i < mostChars; i++)
        {
            while (!result.isEmpty())
            {
                String word = result.dequeue();
                int bucket = word.toLowerCase().charAt(word.length() - 1 - i) - 'a' + 1;
                buckets[bucket].enqueue(word);
            }
            for (ArrayQueue<String> bucket : buckets)
            {
                while(!bucket.isEmpty())
                {
                    result.enqueue(bucket.dequeue());
                }
            }
        }
        for (int i = 0; i < data.size(); i++)
        {
            data.set(i, unpad(result.dequeue()));
        }
    }

    /**
     * Helper method to get the number of digits
     * in the longest number in a list of ints.
     * 
     * @param nums the list to search through
     * @return the number of digits of the longest number in the list
     */
    private static int getMostDigits(List<Integer> nums)
    {
        int longestNumDigits = 0;
        for (int i : nums)
        {
            int numDigits = String.valueOf(i).length();
            if (numDigits > longestNumDigits)
            {
                longestNumDigits = numDigits;
            }
        }
        return longestNumDigits;
    }

    /**
     * Helper method to get the number of characters
     * in the longest string in a list of strings.
     * 
     * @param words the list to search through
     * @return the number of chgaracters in the longest string in the list
     */
    private static int getMostCharacters(List<String> words)
    {
        int longestNumChars = 0;
        for (String i : words)
        {
            if (i.length() > longestNumChars)
            {
                longestNumChars = i.length();
            }
        }
        return longestNumChars;
    }

    /**
     * Pads a string with backticks on the right up to a given length.
     * 
     * @param data the list of strings to pad
     * @param length the length to which to pad the strings
     */
    private static String pad(String data, int length)
    {
        String s = data;
        while (s.length() < length)
        {
            s += "`";
        }
        return s;
    }

    /**
     * Removes all trailing backticks from a string.
     * 
     * @param word the word to trim
     */
    private static String unpad(String word)
    {
        return word.replaceAll("`", "");
    }
}
