import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class project2 {

    static List<List<String>> table;
    int row;

    public project2(String input) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String numLiString = reader.readLine();
        table = new ArrayList<List<String>>();
        row = 0;
        // Creating of matrix with cols and rows
        while (numLiString != null) {
            String line = numLiString;
            String splits[] = line.split(" ", 0);
            table.add(new ArrayList<String>());
            for (String col : splits) {
                table.get(row).add(col);
            }
            row++;
            numLiString = reader.readLine();
        }
        printA(table); // Check the table
        System.out.println("Is there a Cycle?: " + cycles(table));
        System.out.println("levels " + levels(table));
        reader.close();
    }

    // Commom print method to see matrix
    void printA(List<List<String>> input) {
        for (List<String> row : input) {
            for (String col : row) {

                System.out.print("[" + col + "]");

            }
            System.out.println();
        }

    }

    public int levels(List<List<String>> graph) {
        int rowindex = 0;
        int colindex = 0;
        boolean visited[] = new boolean[row];
        int test[] = new int[row];
        // create a queue
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(rowindex);
        test[rowindex] = 0;
        visited[rowindex] = true;
        while (!queue.isEmpty()) {
            rowindex = queue.poll();
            // Going trough the matrix
            for (int i = 0; i < graph.get(rowindex).size(); i++) {
                // if there is a 1 between a col and row there exists an edge.
                if (Integer.parseInt(graph.get(rowindex).get(i)) == 1) {
                    colindex = i;
                    // found and edge now lets make sure we have not seen that edge before
                    if (!visited[colindex]) {
                        visited[colindex] = true;
                        test[colindex] = test[rowindex] + 1;
                        queue.add(colindex);

                    }
                }
            }
        }
        return test[test.length - 1];
    }

    public boolean cycles(List<List<String>> graph) {
        int prede[] = new int[row];
        Arrays.fill(prede, 0); // set it to empty
        boolean visited[] = new boolean[row];
        int rowindex = 0;
        int colindex = 0;

        // create a queue
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[rowindex] = true;
        queue.add(rowindex);
        while (!queue.isEmpty()) {
            rowindex = queue.poll();
            // Going trough the matrix
            for (int i = 0; i < graph.get(rowindex).size(); i++) {
                // if there is a 1 between a col and row there exists an edge.
                if (Integer.parseInt(graph.get(rowindex).get(i)) == 1) {
                    colindex = i;
                    // found and edge now lets make sure we have not seen that edge before
                    if (!visited[colindex]) {
                        visited[colindex] = true; // add the non visited edge to the visited array
                        queue.add(colindex); // add a new edge
                        prede[colindex] = rowindex; // add edge to predecesor

                    }
                    // cycle found where there is edges on prede that != rowindex and complete
                    // connect the nodes
                    else if (prede[rowindex] != colindex) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String args[]) throws Exception {
        project2 obj = new project2("input3.txt");
    }
}