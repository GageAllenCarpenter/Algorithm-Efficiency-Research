package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for all sorting algorithms to extend. This class contains the
 * methods that are common to all sorting algorithms. This class also contains
 * the methods that are used to test the sorting algorithms.
 * @author <a href="https://www.linkedin.com/in/gage-carpenter-07750a174/">Gage Carpenter</a>
 */
public abstract class SortAlgorithm {

    private final String name;

    /**
     * Setting the name of the class to the name of the class.
     */
    public SortAlgorithm() {
        this.name = getClass().getSimpleName();
    }

    /**
     * Setting the name of the class to the name of the class.
     * @param name The name of the class.
     */
    public SortAlgorithm(String name) {
        this.name = name;
    }

    /**
     * "Sort the list and return the number of comparisons made."
     *
     * The first thing to notice is that the function is abstract. This means that it doesn't have a body. The body of the
     * function is defined in the subclasses
     *
     * @param list The list to be sorted.
     * @return The number of swaps that were made.
     */
    public abstract <T extends Comparable<T>> long sort(List<T> list);

    /**
     * returns the name of the class.
     *
     * @return The name of the class.
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a list of 100 random integers and then sorts them using the defined algorithm.
     */
    static void driver(SortAlgorithm alg) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            list.add((int)(Math.random() * 100));
        }
        alg.sort(list);
        int n = list.size();
        for (Integer integer : list) System.out.print(integer + " ");
    }

}
