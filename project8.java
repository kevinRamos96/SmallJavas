import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class project8{
    protected int row;
    protected int col;
    public project8(String input) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = reader.readLine();
        String parts[] = line.split(" ",0);
        row = Integer.parseInt(parts[0]);
        col = Integer.parseInt(parts[1]);
        String [][]arr = new String[row][col];
        arr[0][0]="start";
        arr[row-1][col-1]="goal";
        //paths(arr);
        steps(row, col); // First Implementation to just print the number of paths
        System.out.println(uniquePaths(row, col));
    }

    public void paths(String [][]arr){
        String [][]another = new String[row+1][col+1];
        String path="";
       // paths(arr,0,0,row,col,another,path);
    }
    public void steps(int m , int n){
        int []dp = new int[n];
        dp[0]=1;
        for(int i=0; i<m; i++){
            for(int j =1; j<n;j++){
                dp[j]+=dp[j-1];
            }
        }
        System.out.println(dp[n-1]);
    }
    ///This implementation does not only shows you the number of paths but also the path that you can take
    public int uniquePaths(int m, int n) {
        int[][] mem = new int[m][n];
        String path="";
        int another[][]=new int[m][n];
        //init with -1 value
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                mem[i][j]=-1;
            }
        }
     
        return helper(mem, m-1, n-1,path);
    }
     
    private int helper(int[][] mem, int m, int n,String path){
        //edge has only one path
        if(m==0||n==0){
            if(m==0 && n>1){
                mem[m][n]=1;
            System.out.println(path+ "all the way right hit target ");
            return 1;

            }
            if(n==0 && m>0){
                mem[m][n]=1;
                System.out.println(path+ "all the way down hit target ");
                return 1;
            }
            else{
                mem[m][n]=1;
                System.out.println(path+ " hit target ");
                return 1;

            }
        }
        
        if(mem[m][n]!=-1){
            if(m!=-1){
            System.out.println(path+ "all the way right hit target ");}
            if(n!=-1){
                System.out.println(path+ "all the way down hit target ");}
            return mem[m][n];
        }
     
        mem[m][n] = helper(mem, m, n-1,(path+" go right"+ " then ")) + helper(mem, m-1, n,(path+"go down "+ " then "));
     
        return mem[m][n];
    }
    
    public static void main(String args[])throws Exception{
        project8 obj = new project8("input7.txt");
    }
}