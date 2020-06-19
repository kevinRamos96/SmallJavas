import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

class project5 {
    String unArr[];
    int arr[];
    int index =0;
    public project5(String input) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = reader.readLine();
        if(line !=null){
        String cut[]; 
        cut = line.split(" ",0);
        arr = new int[cut.length];
        unArr = new String[cut.length];
        for(int i=0;i<cut.length; i++){
           arr[i] = Integer.parseInt(cut[i]);
           unArr[i] = cut[i];
        }
        }
        System.out.println(Arrays.toString(aSort(unArr)));
        reader.close();
    }

    public int[] aSort(String [] input){
        Arrays.sort(input,new Comparator<String>(){
 
            @Override
            public int compare(String a, String b) {
                int x=0,y=0;
                if(Integer.parseInt(a)<0 || Integer.parseInt(b)<0){
                x = Math.abs(Integer.parseInt(a));
                y = Math.abs(Integer.parseInt(b));
                if(x==y){
                   return a.compareTo(b);
                }
                a=Integer.toString(x);
                b=Integer.toString(y);
                }
                //System.out.print(x);
                //System.out.print(y);
                //System.out.println(Math.min(x, y));
                //result[index++]=Math.min(x, y);
                //result[index++]=Math.max(x, y);
                //return Math.min(x, y);
                return a.compareTo(b);

            }});
        return Arrays.stream(input).mapToInt(mapper ->Integer.parseInt(mapper)).toArray();
    }

    public static void main(String args[]) throws Exception{
        project5 obj = new project5("input6.txt");
        /** 
         * int i =0;
        int[] result = obj.aSort(obj.unArr);
        System.out.println("");
        System.out.println(Arrays.toString(obj.unArr));
        System.out.println("sorted");
        System.out.println(Arrays.toString(result));
         * 
        */

    }

}