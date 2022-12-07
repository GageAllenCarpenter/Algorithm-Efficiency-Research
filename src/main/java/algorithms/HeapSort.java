package algorithms;

import java.util.List;

/**
 * A Java program for implementation of Heap sort
 * Referenced from: <a href="https://www.geeksforgeeks.org/heap-sort/">Geeks for Geeks</a>
 * And later modified to fit the requirements of the project
 * @author <a href="https://www.linkedin.com/in/gage-carpenter-07750a174/">Gage Carpenter</a>
 */
public class HeapSort extends SortAlgorithm {

    long complexityCount = 0;

    /**
     * Takes in a list of comparable objects and sorts them in ascending order
     * using the heap sort algorithm.
     *
     * @param arr The array to be sorted
     * @return The number of elementary operations performed
     */
    @Override
    public <T extends Comparable<T>> long sort(List<T> arr)
    {
        int N = arr.size();

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--) {
            complexityCount++;
            heapify(arr, N, i);
        }

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            complexityCount++;
            T temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
        return complexityCount;
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    <T extends Comparable<T>> void heapify(List<T> arr, int N, int i) // O(
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr.get(l).compareTo(arr.get(largest)) > 0) {
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < N && arr.get(r).compareTo(arr.get(largest)) > 0) {
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            T swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }

    // Driver method
    public static void main(String args[])
    {
        driver(new HeapSort());
    }
}