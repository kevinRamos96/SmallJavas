import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class project1{

    static List<List<String>> table, maleT, femaleT, permutations;
    static String perm[];
    int cuts,index;

    public project1(String input) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String numLiString= reader.readLine() ;
        cuts = Integer.parseInt(numLiString) *2;
        index = 0;
        table= new ArrayList <List<String>>();
        permutations= new  ArrayList<List<String>>();
        maleT= new ArrayList <List<String>>();
        femaleT = new ArrayList<List<String>>();
        numLiString=reader.readLine();
        int size= 0; 
        while((numLiString!= null) && (size<cuts)){
            String line= numLiString;
            String splits [] = line.split(" ",0);
            perm=splits;
            table.add(new ArrayList<String>());
            for(String data: splits){ 
                table.get(size).add(data);
            }
            size++;
            numLiString=reader.readLine();
        }
        printA(table); //print original matrix
        System.out.println("Printing maleT");
        createP(table); //Creates Female and Male preference matrix
        printA(maleT); // print male matrix
        System.out.println("Printing femaleT");
        printA(femaleT); // print female matrix
        System.out.println();
        reader.close();
        permutations(perm, perm.length, perm.length); //Creates the permutations
        System.out.println("printing number of stables matches "+stableSolutions(permutations, maleT, femaleT));
        
    }
    //Create a preference matrix for male and females
    void createP(List<List<String>>input){
        int size = 0;
        int len = (int)(cuts/2);
        int findex=0;

        while(size<cuts){
            if(size<len)maleT.add(size,table.get(size));
            else {
                femaleT.add(findex,table.get(size));
                findex++;
            }
            size++;   
        }            
    }
//Commom print matrix
    void printA(List<List<String>> input){
       int size = 0;
       int len = (int)(cuts/2);
        for(List<String> row: input){
            for(String col: row){
                if(size <len){
                    
                }
                System.out.print("["+col+"]");

            }       
            System.out.println();
            size++;
        }
        
    }
    //Commom permutations algo
    public void permutations(String input[], int size , int oSize ){
        if(size ==1){
            permutations.add(new ArrayList<String>());
            for(int i=0; i<input.length;i++){
            permutations.get(index).add(input[i]);
            }
            index++;
         }
        for(int i=0; i<size; i++){
            permutations(input, size-1, oSize);

            if(size%2==1){
                String temp= input[0];
                input[0]=input[size-1];
                input[size-1]=temp;
            }

            else{
                String temp= input[i];
                input[i]=input[size-1];
                input[size-1]=temp;
            }
        }
    }
//checking for the stability of possible solutions
public static boolean isStable(List<String> permArray, List<List<String>> malePref, List<List<String>> femPref)
    {
      boolean femaleStable = true;
      boolean maleStable = true;
       int maleLocation = 0;
       int femaleLocation = 0;
       int maleMatch = 0;
       int femaleMatch = 0;
       int femaleMatchLoc = 0;
       int currentFemaleMatchLoc = 0;
       int currentMaleMatchLoc = 0;
       int maleMatchLoc = 0;
       int maleNumber = 0;
       int femaleNumber = 0;
       for(int i = 0; i < permArray.size(); i++) 
       {
          maleNumber = i+1; 
          femaleNumber = Integer.parseInt(permArray.get(i));
          //going through the female preference list
          for(int j = 0; j < femPref.size(); j++) 
          {
             if(Integer.parseInt(femPref.get(femaleNumber-1).get(j)) == maleNumber) 
             {
                maleLocation = j;
             }
          }
          //going through the male preference list
          for(int k = 0; k < malePref.size(); k++) 
          {
             if(Integer.parseInt(malePref.get(maleNumber-1).get(k)) == femaleNumber) 
             {
                femaleLocation = k;
             }
          }
          for(int a = 0 ; a < maleLocation; a++)
          {
            //there is someone that prefers someone else than the current match
            if(a < maleLocation) 
             {
                   maleMatch = Integer.parseInt(femPref.get(femaleNumber-1).get(a)); 
                   femaleMatch = Integer.parseInt(permArray.get(maleMatch-1)); 
                   for(int c = 0; c < malePref.size(); c++)
                   {
                      if(Integer.parseInt(malePref.get(maleMatch-1).get(c)) == femaleMatch)
                       { 
                        femaleMatchLoc = c;
                       }
                       if(Integer.parseInt(malePref.get(maleMatch-1).get(c)) == femaleNumber) 
                       {
                         currentFemaleMatchLoc = c; 
                       } 
                   } 
                   if(currentFemaleMatchLoc < femaleMatchLoc) 
                   {
                      maleStable = false; //females prefers another male
                   }          
             }
           }
          for(int d = 0; d < femaleLocation; d++)
           {
             if(d < femaleLocation) 
             {
                femaleMatch = Integer.parseInt(malePref.get(maleNumber-1).get(d)); 
                maleMatch = permArray.indexOf(femaleMatch) + 1; 
                for( int b = 0; b < femPref.size(); b++) 
                {
                   if(Integer.parseInt(femPref.get(femaleMatch-1).get(b))== maleMatch) 
                   {
                      maleMatchLoc = b; 
                   }
                   if(Integer.parseInt(femPref.get(femaleMatch-1).get(b)) == maleNumber) //finding current M's location within femaleMatch's preference
                   {
                      currentMaleMatchLoc = b; 
                   }
                }
                if(currentMaleMatchLoc < maleMatchLoc)
                {
                   femaleStable = false; //female prefers male
                }
             }
             if(maleStable == false || femaleStable == false) 
             {
                return false;
             }
          }
       }
       return true; // everyone is happy with their current matches 
    }

    public static int stableSolutions(List<List<String>> permuArray, List<List<String>> malePref, List<List<String>> femPref)
   {
      int matches = 0;
      if(malePref.size() == 0) 
      {
         return 0;
      }
      if(malePref.size() == 1)
      {
         return 1;
      }
      for(int i = 0; i < permuArray.size(); i++) //we have done permutaions from the orignal matrix table now lets go through em!!!
      {
         List<String> testArray = new ArrayList<String>();
         testArray = permuArray.get(i);
         //lets check if it is stable.
         if(isStable(testArray, malePref, femPref) == true) 
         {
            matches += 1;
         }
      }
               return matches;
    }
   
    
    public static void main (String args[]) throws Exception{
        project1 obj = new project1("input.txt");
       
    }
}