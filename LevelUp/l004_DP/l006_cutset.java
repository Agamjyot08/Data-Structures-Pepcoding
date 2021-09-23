import java.util.Arrays;
public class l006_cutset {
    // https://practice.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1#
    public static int mcm_memo(int[] arr,int si,int ei,int[][] dp){
        if(ei-si==1){
            return dp[si][ei] =0;
        }
        if(dp[si][ei]!=-1){
            return dp[si][ei];
        }
        int minres = (int)1e9;
        for(int cut = si+1;cut<ei;cut++){
        int left = mcm_memo(arr,si,cut,dp);
        int right = mcm_memo(arr,cut,ei,dp);
        minres = Math.min(minres,left+(arr[si]*arr[cut]*arr[ei])+right);
        }
        return dp[si][ei] = minres;
    }
    public static void display2D(int[][] dp){
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
   public static int matrixMultiplication(int N, int arr[])
    {
        // code here
        int[][] dp =new int[N][N];
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        int ans= mcm_memo(arr,0,N-1,dp); 
         display2D(dp); 
         return ans; 
    }
    public static void mcm(){
        int N=5;
        int[] arr ={40, 20, 30, 10, 30};
        matrixMultiplication(N, arr);
    }
    public static int mcm_tab(int[] arr,int Si,int Ei,int[][] dp){
        return dp[Si][Ei];
    }   
    // 312. Burst Balloons
    public int maxCoins(int[] nums, int si,int ei,int[][] dp) {
        if(dp[si][ei]!=0) return dp[si][ei];
        int lele = si==0?1:nums[si-1];
        int rele = ei==nums.length-1?1:nums[ei+1];
        int maxCoins=0;
        for(int cut = si;cut<=ei;cut++){
            int leftcost =  cut==si?0:maxCoins(nums,si,cut-1,dp);
            int rightcost = cut==ei?0:maxCoins(nums,cut+1,ei,dp);
            
            maxCoins = Math.max(maxCoins,leftcost + lele*nums[cut]*rele + rightcost);
        }
        return dp[si][ei] = maxCoins;
    }
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        return maxCoins(nums, 0,n-1,dp);
    }
    public static void main(String[] args){
        mcm();
    }
}
