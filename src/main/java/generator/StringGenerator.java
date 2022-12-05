package generator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class is used to aid in the generation of random strings of a given length and size with a degree of sorting.
 * @author <a href="https://www.linkedin.com/in/gage-carpenter-07750a174/">Gage Carpenter</a>
 */
public class StringGenerator {

    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final ObjectMapper _objectMapper = new ObjectMapper();

    /**
     * It generates a random string of a given length by generating random integers between 0 and the length of the
     * characters string, limiting the number of integers to the length of the string we want to generate, mapping each
     * integer to the character at that index in the characters string, collecting the characters into a StringBuilder, and
     * then converting the StringBuilder to a String
     *
     * @param length The length of the generated string.
     * @return A random string of characters of the length specified.
     */
    private String generateString(int length) {
        return new Random()
                .ints(0, characters.length())
                .limit(length)
                .mapToObj(characters::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * Generate a list of random strings of a given length and size.
     *
     * @param length The length of the string to be generated.
     * @param size The number of strings to generate
     * @return A list of strings of a given length and size.
     */
    public List<String> generateStringList(int length, int size) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(generateString(length));
        }
        return list;
    }

    /**
     * Write the given list to the given file path.
     *
     * @param list The list of strings to write to the file.
     * @param path The path to the file you want to write to.
     */
    public void writeToFile(List<String> list, String path) {
        try {
            _objectMapper.writeValue(new File(path), list.toArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read a file and return a list of strings.
     *
     * @param path The path to the file to read from.
     * @return A list of strings
     */
    public List<String> readFromFile(String path) {
        try {
            return Arrays.asList(_objectMapper.readValue(new File(path), String[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the file, sorts it, and then desorts it based on the degree of sorting
     *
     * @param path The path to the file you want to read from.
     * @param degreeSorted The degree of sorting you want to apply to the list.
     * @return A list of strings
     */
    public List<String> presort(String path, DegreeSorted degreeSorted) {
        List<String> list = readFromFile(path);
        switch (degreeSorted){
            case SORTED -> {
                Collections.sort(list);
                return list;
            }
            case PARTIALLY_SORTED -> {
                Collections.sort(list);
                String[] arr = list.toArray(new String[0]);
                for (int i = 3; i < arr.length-1; i += 4) { //Desorts every 4th element
                    String temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
                return Arrays.asList(arr);
            }
            case PARTIALLY_UNSORTED -> {
                Collections.sort(list);
                String[] arr = list.toArray(new String[0]);
                for (int i = 1; i < arr.length-1; i += 2) { //Desorts every 2nd element
                    String temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
                return Arrays.asList(arr);
            }
            case UNSORTED -> {
                return list;
            }
        }
        return null;
    }
}