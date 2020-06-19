
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class project3 {
   int inversions = 0;
   long swaps = 0;

   public project3(String input) throws Exception {
      BufferedReader reader = new BufferedReader(new FileReader(input));
      String numLiString = reader.readLine();
      List<String> data = new ArrayList<String>();
      while (numLiString != null) {
         String line = numLiString;
         String splits[] = line.split(" ", 0);
         data = Arrays.asList(splits);
         numLiString = reader.readLine();
      }
      int[] arr = convertData(data);
      System.out.println(mergeSort(arr));
      // System.out.println(getInvCount(arr, arr.length));
      reader.close();
   }

   // For debbuging brute force n2
   int getInvCount(int arr[], int n) {
      int count = 0;
      int i, j;

      for (i = 0; i < n - 1; i++)
         for (j = i + 1; j < n; j++)
            if (arr[i] > arr[j])
               count++;

      return count;
   }

   public int[] convertData(List<String> data) {
      return data.stream().mapToInt(input -> Integer.parseInt(input)).toArray();

   }

   // A different approach to merger sort same output as the rest of
   // implementations so far...
   public int mergeSort(int[] arr) {
      if (arr.length == 1) {
         return 0;
      }

      else {
         int mid = arr.length / 2;
         int[] arrLeft = (Arrays.copyOfRange(arr, 0, mid));
         int[] arrRight = Arrays.copyOfRange(arr, mid, arr.length);
         inversions = mergeSort(arrLeft) + mergeSort(arrRight);
         int leftIndex = 0;
         int rightIndex = 0;
         int range = arr.length - mid;
         for (int i = 0; i < arr.length; i++) {
            // make sure left index < middle and right index less than range (end-mid second
            // half)
            // gotta make sure that left is < right as well
            if (leftIndex < mid && ((rightIndex >= range) || arrLeft[leftIndex] <= arrRight[rightIndex])) {
               arr[i] = arrLeft[leftIndex++];
               inversions += rightIndex; // add an inversion as left > right
               swaps += 1;
            } else if (rightIndex < range) {
               arr[i] = arrRight[rightIndex++];
            }
         }
         return inversions;
      }
   }

   public static void main(String args[]) throws Exception {
      project3 obj = new project3("input4.txt");
      int[] test = { 1, 12, 13, 24, 35, 46, 57, 58, 69 };
      // System.out.println("Inversions: " + obj.adder(test)
      // 21,59,98,231,5,97 //11
      // 92,47,21,18,3
      // 1,12,13,24,35,46,57,58,69
      // );
      // System.out.println("cehcker " +obj.getInvCount(test,test.length));
      // System.out.println(mergeSort(test, test.length));
      // System.out.println("total "+ obj.total);
      // System.out.println("Inversions: " + obj.numInvs);
      // System.out.println("tracker: " + obj.tracker);
      // System.out.println("swaps: " + obj.swaps);
   }

}