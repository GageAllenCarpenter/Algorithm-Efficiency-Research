import algorithms.SortAlgorithm;
import generator.DegreeSorted;
import generator.Paths;
import generator.StringGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is used to generate a list of strings of a given length and size with a degree of sorting.
 * The list is then written to a file with the tests included in CSV format.
 * @author <a href="https://www.linkedin.com/in/gage-carpenter-07750a174/">Gage Carpenter</a>
 */
public class Application {

    private final int count = 1000;
    private final StringGenerator sg = new StringGenerator();
    private final SortAlgorithm[] myAlg = {
            //new algorithms.BubbleSort(),
            new algorithms.InsertionSort(),
            //new algorithms.MergeSort(),
            //new algorithms.QuickSort(),
            //new algorithms.HeapSort()
    };

    /**
     * Creates a list of all the paths to the input files, and then for each path, it creates the input
     * files, and then for each degree sorted, it creates a list of the input file, and then for each algorithm, it creates
     * an output object, and then it writes the output object to the output files
     */
    public void run(){
        createInputDirectory();
        createOutputDirectory();
        for(Paths path : Paths.values()){
            createInputFiles(path);
            for(DegreeSorted degreeSorted : DegreeSorted.values()){
                List<String> list = sg.presort(path.getPath(),degreeSorted);
                for(SortAlgorithm alg : myAlg){
                    Output output = new Output(alg.getName(),getComplexity(alg,list), isStable(alg), isSorted(alg,list), isPreSorted(list) ,path.getSize(), degreeSorted);
                    System.out.println(output);
                    writeToOutputFiles(output);
                }
            }
        }
    }

    /**
     * Creates a file if it doesn't exist, and if it does exist, it does nothing
     *
     * @param paths This is a list of Paths objects. Each Path's object contains a path to a file and the size of the file.
     */
    private void createInputFiles(Paths paths){
        File file = new File(paths.getPath());
        if(!file.exists()){
            try{
                file.createNewFile();
                sg.writeToFile(sg.generateStringList(10,paths.getSize()),paths.getPath());
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Writes the output of the algorithm to a file
     *
     * @param output The name of the algorithm
     */
    private void writeToOutputFiles(Output output){
        final String path = "src/main/resources/OutputData/" + output.name + output.degreeSorted + ".csv";
        if(createOutputFiles(path)){
            try{
                String header = "Algorithm, Stable, Sorted, Size, Complexity, DegreeSorted";
                FileWriter writer = new FileWriter(path, false);
                writer.write(header);
                writer.write("\n");
                writer.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        try{
            String body = output.name + "," + output.stable + "," + output.sorted + "," + output.size + "," + output.complexity + "," + output.degreeSorted;
            //add a new line to the end of the file
            FileWriter writer = new FileWriter(path, true);
            writer.write(body);
            writer.write("\n");
            writer.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a directory called InputData in the resources folder
     */
    private void createInputDirectory(){
        File file = new File("src/main/resources/InputData");
        if(file.exists()){
            file.delete();
        }
        file.mkdir();
    }

    /**
     * Creates a directory called "OutputData" in the resources folder
     */
    private void createOutputDirectory(){
        File file = new File("src/main/resources/OutputData");
        if(file.exists()){
            file.delete();
        }
        file.mkdir();
    }

    /**
     * Creates a new file at the specified path if the file does not already exist
     *
     * @param path The path to the file you want to create.
     * @return A boolean value.
     */
    private boolean createOutputFiles(String path){
        if(!new File(path).exists()){
            try{
                return new File(path).createNewFile();
            }catch (IOException e){
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if the algorithm is stable by sorting a list of strings that are all the same, and then checking if the
     * sorted list is the same as the original list
     *
     * @param myAlg The algorithm to test
     * @return The method is returning a boolean value.
     */
    @SuppressWarnings("all")
    private boolean isStable(SortAlgorithm myAlg) {
        List<String> original = new ArrayList<>();
        List<String> sorted = new ArrayList<>();
        generateValues(List.of(sorted, original));
        myAlg.sort(sorted);
        for (int i = 0; i < count; i++) {
            if (sorted.get(i * 3) != original.get(i)) {
                return false;
            }
            if (sorted.get(i * 3 + 1) != original.get(count + i)) {
                return false;
            }
            if (sorted.get(i * 3 + 2) != original.get(count * 2 + i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * If the list is sorted, then the sorted list should be equal to the sorted list.
     *
     * @param myAlg The algorithm to test
     * @param seed the list of strings to be sorted
     * @return The return value is a boolean.
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private boolean isSorted(SortAlgorithm myAlg, List<String> seed){
        List<String> expected = new ArrayList<>(seed);
        List<String> actual = new ArrayList<>(seed);
        myAlg.sort(actual);
        Collections.sort(expected);
        return actual.equals(expected);
    }

    /**
     * If the list is sorted, then the sorted list will be equal to the original list.
     *
     * @param seed the list of strings to be sorted
     * @return The method is returning a boolean value.
     */
    private boolean isPreSorted(List<String> seed){
        List<String> expected = new ArrayList<>(seed);
        Collections.sort(expected);
        return seed.equals(expected);
    }

    /**
     * "Generate a list of lists of strings, where each list contains the same strings."
     *
     * The function is a bit more complicated than that, but that's the gist of it
     *
     * @param lists The list of lists to add the values to.
     */
    @SuppressWarnings("all")
    private void generateValues(List<List<String>> lists) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < count; j++) {
                final String uniqueInstance = new String(String.valueOf(count + j));
                for (List<String> list : lists) {
                    list.add(uniqueInstance);
                }
            }
        }
    }

    /**
     * It takes a sorting algorithm and a list of strings, and returns the number of comparisons that the algorithm makes
     * when sorting the list
     *
     * @param myAlg The algorithm you want to test.
     * @param seed the list of strings to be sorted
     * @return The number of comparisons made by the algorithm.
     */
    private long getComplexity(SortAlgorithm myAlg, List<String> seed){
        List<String> temp = new ArrayList<>(seed);
        return myAlg.sort(temp);
    }

    // It's a record. It's a new feature in Java 14. It's a class that has a private constructor, getters, and a toString
    // method.
    private record Output(String name, long complexity, boolean stable, boolean sorted, boolean presorted, int size, DegreeSorted degreeSorted) { }
}
