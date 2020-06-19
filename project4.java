import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class project4 {
    int capacity = 0;
    int[] values;
    int[] weight;

    public project4(String input) throws Exception {
        // Reader from txt file
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String numLiString = reader.readLine();
        capacity = Integer.parseInt(numLiString);
        numLiString = reader.readLine(); // move on to weights
        String secondLine = numLiString;
        String splits[] = secondLine.split(" ", 0);
        numLiString = reader.readLine();
        String thridLine = numLiString;
        String secondSplit[] = thridLine.split(" ", 0);
        values = convertData(splits); // convert string input tp int array
        weight = convertData(secondSplit);// convert string input to int array
        // callknapscak solver
        System.out.println(knapSack(capacity, weight, values, values.length));
        reader.close();
    }

    public int[] convertData(String[] data) {
        return Arrays.stream(data).mapToInt(input -> Integer.parseInt(input)).toArray();

    }

    public int knapSack(int cap, int weight[], int values[], int length) {
        // create a matrix
        int table[][] = new int[length + 1][cap + 1];
        // populate the matrix
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j <= cap; j++) {
                if (i == 0 || j == 0)
                    table[i][j] = 0;
                // Check for the max value that can be put in the bag without exceeding capacity
                // weight.
                // max checks for the nth item to be included vs not included.
                else if (weight[i - 1] <= j)
                    table[i][j] = Math.max(values[i - 1] + table[i - 1][j - weight[i - 1]], table[i - 1][j]);
                // weight > capacity
                else
                    table[i][j] = table[i - 1][j];
            }
        }

        return table[length][cap];
    }

    public static void main(String args[]) throws Exception {
        project4 obj = new project4("input5.txt");
    }
}