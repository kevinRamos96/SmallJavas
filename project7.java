import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class project7{
    
     public project7(String input)throws Exception{
         BufferedReader reader = new BufferedReader(new FileReader(input));
         String line = reader.readLine();
         String [] cuts = line.split(" ",0);
         int [] arr = new int [cuts.length];
         int i=0;
         for(String x:cuts){
            arr[i++]=Integer.parseInt(x);
         }
         canwe(arr);

     }
     public void canwe(int []arr){
         canwe(arr, "",0);
     }
     public void canwe(int []arr, String path, int index){
        if(arr[index]==0){
            System.out.println("No");
            System.out.println(path +" from index "+ index+" cannot move forward");
            return;
        }
        if(arr[index]>=(arr.length-(index+1))){
            if(arr[index]==arr.length-(index+1)){
            System.out.println("yes");
            System.out.println(path+" from index "+index+" we can move "+arr[index]+" steps and then we reached the end of array");
            }
            if(arr[index]>(arr.length-(index+1))){
                System.out.println("yes");
                System.out.println(path+" from index "+index+" we can move "+(arr[index]-arr.length-1)+" steps and then we reached the end of array insted of using the full "+ 
                arr[index]+ " steps and run out of bounds");    
            }
            return;
        } 
        canwe(arr, path+" from index "+index + " we can walk "+arr[index]+" indexes", index+1);
     }

     public static void main(String args[]) throws Exception{
         project7 obj = new project7("input9.txt");

     }
}