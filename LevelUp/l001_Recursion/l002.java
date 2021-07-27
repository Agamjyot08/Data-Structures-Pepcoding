import java.util.*;
public class l002 {
   
    public static int infiPermutation(int[] coins, int tar,String asf)
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count = 0;

        for(int  i = 0 ;i<coins.length;i++)
        {
            if(tar - coins[i] >= 0)
            {
                count+=infiPermutation(coins, tar - coins[i], asf+coins[i]);
            }
        }
        return count;
    }
    public static int singlePermutation(int[] coins, int tar,String asf,boolean[] vis)
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count = 0;

        for(int  i = 0 ;i<coins.length;i++)
        {
            if(tar - coins[i] >= 0 && vis[i]==false)
            {
                vis[i]=true;
                count+=singlePermutation(coins, tar - coins[i], asf+coins[i],vis);
                vis[i]=false;
            }
        }
        return count;
    }
    public static int singlePermutation1(int[] coins, int tar,String asf)
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        
        for(int  i = 0 ;i<coins.length;i++)
        {
            if(tar - coins[i] >= 0 && coins[i]>0)
            {
                int val = coins[i];
                coins[i] = -coins[i];
                count+=singlePermutation1(coins, tar - val, asf+val);
                coins[i] = -coins[i];
            }
        }
        return count;
    }

    public static int infiCombinations(int[] coins,int tar,int idx,String asf)
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count = 0;

        for(int  i = idx ;i<coins.length;i++)
        {
            if(tar - coins[i] >= 0)
            {
                count+=infiCombinations(coins, tar - coins[i],i, asf+coins[i]);
            }
        }
        return count;
    }

    public static int singleCombinations(int[] coins,int tar,int idx,String asf)
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count = 0;

        for(int  i = idx ;i<coins.length;i++)
        {
            if(tar - coins[i] >= 0)
            {
                count+=singleCombinations(coins, tar - coins[i],i+1, asf+coins[i]);
            }
        }
        return count;
    }


     //leetcode 39  Combination Sum 
    public void combinations(int[] candidates,int target, int idx, List<Integer> smallAns,List<List<Integer>> ans)
    {
        if(target == 0)
        {
            List<Integer> base = new ArrayList<>(smallAns); //deep copy  (values)
            ans.add(base); //shallow copy (address)
            return;
        }
        
        for(int i = idx;i<candidates.length;i++)
        {
            if(target - candidates[i] >= 0 )
            {
                smallAns.add(candidates[i]);
                combinations(candidates,target-candidates[i],i,smallAns,ans);
                smallAns.remove(smallAns.size()-1);
            }
        }
        
    }
    
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<Integer> smallAns = new ArrayList<>();
            List<List<Integer>> ans = new ArrayList<>();
            
            combinations(candidates,target,0,smallAns,ans);
        return ans;
    }
    //leetcode 40

    
    
    public void combinations2(int[] candidates,int target, int idx, List<Integer> smallAns,List<List<Integer>> ans)
    {
        int prev=-1;
        if(target == 0)
        {
            List<Integer> base = new ArrayList<>(smallAns); //deep copy  (values)
            ans.add(base); //shallow copy (address)
            return;
        }
        
        for(int i = idx;i<candidates.length;i++)
        {
            if(target - candidates[i] >= 0 )
            {
                if(prev!=candidates[i])
                {
                smallAns.add(candidates[i]);
                combinations2(candidates,target-candidates[i],i+1,smallAns,ans);
                smallAns.remove(smallAns.size()-1);                
                }
                prev=candidates[i];
            }
        }
        
    }
    
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
         List<Integer> smallAns = new ArrayList<>();
            List<List<Integer>> ans = new ArrayList<>();
            
            combinations2(candidates,target,0,smallAns,ans);
        return ans;
    }

    //https://www.interviewbit.com/problems/subset/
    public void getSubsets(ArrayList<Integer> A,ArrayList<Integer> smallAns,ArrayList<ArrayList<Integer>> ans,int idx)
    {      
            ans.add(new ArrayList<>(smallAns));
            for(int i =idx;i<A.size();i++)
            {
                smallAns.add(A.get(i));
                
                getSubsets( A, smallAns, ans, i+1);
                smallAns.remove(smallAns.size()-1);

            }
    }
    
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();
        Collections.sort(A);
        getSubsets(A,smallAns,ans,0);
        return ans;
    }

    public static int singleCombination_subseq(int[] coins,int tar,int idx,String asf)
    {
        if(tar==0 || idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count=0;
        if(tar-coins[idx]>=0)
        count+=singleCombination_subseq(coins,tar-coins[idx],idx+1,asf+coins[idx]);
        count+=singleCombination_subseq(coins,tar,idx+1,asf);

        return count;

    }

    public static int infiCombination_subseq(int[] coins,int tar,int idx,String asf)
    {
        if(tar==0 || idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count=0;
        if(tar-coins[idx]>=0)
        count+=infiCombination_subseq(coins,tar-coins[idx],idx,asf+coins[idx]);
        count+=infiCombination_subseq(coins,tar,idx+1,asf);

        return count;

    }
    public static int infiPermutation_subseq(int[] coins,int tar,int idx,String asf)
    {
        if(tar==0 || idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count=0;
        if(tar-coins[idx]>=0)
        count+= infiPermutation_subseq(coins,tar-coins[idx],0,asf+coins[idx]);
        count+= infiPermutation_subseq(coins,tar,idx+1,asf);

        return count;

    }

    
    public static int singlePermutation_subseq(int[] coins,int tar,int idx,String asf,boolean[] vis)
    {
        if(tar==0 || idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count=0;
        if(tar-coins[idx]>=0 && !vis[idx])
        {
            vis[idx]=true;
        count+= singlePermutation_subseq(coins,tar-coins[idx],0,asf+coins[idx],vis);
            vis[idx]=false;
    }
        count+= singlePermutation_subseq(coins,tar,idx+1,asf,vis);

        return count;

    }
    //Queen set =========
    //tnb= total no of bpxes, bno= box no,tnq : total no of queen, qpsf: queen placed
   // so far
   
   public static int queenCombination1D(int tnb,int bno, int tnq,int qpsf,String asf)
   {
        if(qpsf>tnq)
        {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for(int b = bno;b<=tnb;b++)
        {
            count+= queenCombination1D(tnb, b + 1, tnq, qpsf + 1, asf + "b"+b+"q"+qpsf+" "); 
        }
        return count;
   }

   public static int queenCombination1d_sub(int tnb,int bno,int tnq,int qpsf,String asf)
   {
        //idhar do case dekhne padenge box ka and queen ka
        if(qpsf>tnq || bno>tnb){
            if(qpsf>tnq)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }


        int count=0;
        
        count+=queenCombination1d_sub(tnb, bno+1, tnq, qpsf+1, asf+"b"+bno+"q"+qpsf+" ");
        count+=queenCombination1d_sub(tnb, bno+1, tnq, qpsf, asf);
        
        return count;
   }

   public static int queenPermutation1D(int tnb,int bno, int tnq,int qpsf,String asf,boolean[] vis)
   {
    if(qpsf>tnq)
    {
        System.out.println(asf);
        return 1;
    }
    int count = 0;
    for(int b = bno;b<=tnb;b++)
    {
        if(!vis[b])
        {
            vis[b] = true;
            count+= queenPermutation1D(tnb, 1, tnq, qpsf + 1, asf + "b"+b+"q"+qpsf+" ",vis); 
            vis[b]=false;
    }
    }
    return count;
   }

   public static int queenPermutations1d_sub(int tnb,int bno,int tnq,int qpsf,String asf,boolean[] vis)
   {
        //idhar do case dekhne padenge box ka and queen ka
        if(qpsf>tnq || bno>tnb){
            if(qpsf>tnq)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }


        int count=0;
        
        if(!vis[bno])
        {
            vis[bno]=true;
        count+=queenPermutations1d_sub(tnb, 1, tnq, qpsf+1, asf+"b"+bno+"q"+qpsf+" ",vis);
        vis[bno]=false;
    }
        count+=queenPermutations1d_sub(tnb, bno+1, tnq, qpsf, asf,vis);
        
        return count;
   }

   //2d reh gye hw vale
    
   public static int queenCombination2D(boolean[][] vis,int bno, int tnq,String asf)
   {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        int n=vis.length,m=vis[0].length;
        for(int b = bno;b<n*m;b++)
        {
            int r=b/m;
            int c=b%m;
            count+= queenCombination2D(vis, b + 1, tnq-1, asf + "("+r+","+c+") "); 
        }
        return count;
   }
   
   public static int queenPermutations2D(boolean[][] vis, int tnq,String asf)
   {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        int n=vis.length,m=vis[0].length;
        for(int b = 0;b<n*m;b++)
        {
            int r=b/m;
            int c=b%m;
            if(!vis[r][c])
            {
                vis[r][c]=true;
            count+= queenPermutations2D(vis, tnq-1, asf + "("+r+","+c+") "); 
                vis[r][c]=false;
        }
        }
        return count;
   }




   public static void queen2D(){
       int tnq=4;
       boolean[][] box = new boolean[4][4];
       System.out.println(queenCombination2D(box, 1, tnq, ""));
   }
   







public static void queen(){
    int tnb = 7,tnq=4;
    boolean[] vis = new boolean[tnb+1];
    System.out.println(queenCombination1D(tnb, 1, tnq, 1, ""));
    // System.out.println(queenCombination1d_sub(tnb, 1, tnq, 1, ""));
}


    public static void main(String[] args)
    {
        int[] coins={2,3,5,7};
        boolean[] vis = new boolean[coins.length];
    //   System.out.println(infiPermutation(coins,10,""));
    // System.out.println(infiCombinations(coins,10,0,""));
    // System.out.println(singleCombinations(coins,10,0,""));
    
    // System.out.println(singlePermutation(coins,10,"",vis));
    // System.out.println(singlePermutation1(coins,10,""));
    // System.out.println(singlePermutation_subseq(coins,10,0,"",vis));
        queen();
        // queen2D();
}
}
