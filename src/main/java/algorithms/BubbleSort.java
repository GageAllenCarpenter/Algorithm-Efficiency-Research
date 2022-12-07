package algorithms;

import java.util.List;

/**
 * A Java program for implementation of Bubble sort
 * Referenced from: <a href="https://www.geeksforgeeks.org/bubble-sort/">Geeks for Geeks</a>
 * And later modified to fit the requirements of the project
 * @author <a href="https://www.linkedin.com/in/gage-carpenter-07750a174/">Gage Carpenter</a>
 */
public class BubbleSort extends SortAlgorithm {

    /**
     * "For each element in the list, compare it to the next element and swap them if the first element is greater than the
     * second element."
     *
     * The function takes a list of type T, which is a generic type that extends the Comparable interface. This means that
     * the type T must implement the compareTo() method
     *
     * @param list The list to be sorted
     * @return The number of times the algorithm had to compare two elements.
     */
    @Override
    public <T extends Comparable<T>> long sort(List<T> list) {
        long complexityCount = 0;
        int n = list.size();
        T temp;

        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(list.get(j).compareTo(list.get(j + 1)) > 0){
                    complexityCount++;
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return complexityCount;
    }

    // Driver method
    public static void main(String args[])
    {
        driver(new BubbleSort());
    }
}

/**
 *
 */