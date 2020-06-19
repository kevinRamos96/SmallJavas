import java.io.BufferedReader;
import java.io.FileReader;

class project6 {

    public project6(String input) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line= reader.readLine();
        int numb = Integer.parseInt(line);
        int result = climbStairs(numb);
        System.out.println(result);
        reader.close();
    }

    public int climbStairs(int n) {
        int memo[] = new int[n + 1]; // let this array handle n-2
        String path="";
        int another[]=new int[n+1]; //let this array handle n-1
        return climb_Stairs(0, n, memo,path);
    }
    public int climb_Stairs(int i, int n, int memo[],String path) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            System.out.println("Starting from step 0 go up "+path);
            return 1;
        }
        memo[i]=climb_Stairs(i+1, n, memo, path+"1+") +climb_Stairs(i + 2, n, memo,path+"2+"); //
        if (memo[i] > 0 ) { //make sure theyre still null;
            return memo[i];
        }
        return memo[i];
    }




    public static void main(String args[]) throws Exception{
        project6 obj = new project6("input8.txt");
       // System.out.println("check for: "+ obj.checker(3));
        //System.out.println("check for test: "+ obj.test(4));
       // obj.stairway(3);
      //  System.out.println("Test for: "+ obj.poosibleWaysDyna(4));
      //  System.out.println("Test for: "+ (obj.base1) + (obj.base2));
    }
}