import java.util.Arrays;
import java.util.LinkedList;
public class l001{
    // 198. House Robber
//     time O(n) space O(n)
    public int rob1(int[] nums) {
        int n = nums.length,max=0;
        int[] dp = new int[n];
        if(n==0)
            return 0;
        else if(n==1)
            return nums[0];
        else if(n==2){
            return Math.max(nums[0],nums[1]);
        }
        else{
            dp[0] = nums[0];
            dp[1] = nums[1];
            dp[2] = nums[2] + nums[0];
            max = Math.max(dp[1],dp[2]);
            for(int i=3;i<n;i++){
                dp[i] = Math.max(dp[i-2],dp[i-3]) + nums[i];
                max = Math.max(dp[i],max);
            }
        }
        return max;
    }
      public int rob(int[] nums,int n,int[] dp) {
         if(n<=0) return 0;
         if(dp[n]!=-1) return dp[n];
         int robCurr = nums[n-1] +  rob(nums,n-2,dp);   
         int notRobCurr = rob(nums,n-1,dp);
        
         return dp[n] = Math.max(robCurr,notRobCurr);
         
      }
    
     public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return rob(nums,n,dp);
     }
//     time O(n) space O(1)
    public int rob2(int[] nums) {
        int n = nums.length,max=0,prev2,prev3,ans=0;
        if(n==1)
            return nums[0];
        else if(n==2){
            return Math.max(nums[0],nums[1]);
        }
        else{
            prev3 = nums[0];
            prev2 = nums[1];
            int prev1 = nums[2] + nums[0];
            max = Math.max(prev1,prev2);
            for(int i=3;i<n;i++){
                ans = Math.max(prev2,prev3) + nums[i];
                max = Math.max(ans,max);
                prev3 = prev2;
                prev2 = prev1;
                prev1 = ans;
            }
        }
        return max;
    }

    public class ListNode {
             int val;
             ListNode next;
             ListNode() {}
             ListNode(int val) { this.val = val; }
             ListNode(int val, ListNode next) { this.val = val; this.next = next; }
         }
    // 328. Odd Even Linked List
    // O(n) time , O(1) space 
    public ListNode oddEvenList1(ListNode head) {
        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);
        int count = 0;
        ListNode ptr = head, ep=even, op = odd;
        while(ep!=null){
            count++;
            if(count%2!=0){
                if(ptr==null)
                    break;
                op.next = ptr;
                op = ptr;
            }
            else{
                ep.next = ptr;
                ep = ptr;
            }
            if(ptr!=null)
            ptr = ptr.next;
        }
        op.next = even.next;
        return odd.next;
    }
    
    public ListNode oddEvenList(ListNode head) {
        if(head!=null){
            ListNode odd = head, even = head.next, evenhead = even;
            while(even!=null && even.next!=null){
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenhead;
        }
        return head;
    }

    // 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int n = nums.length, currmax,currmin,prevmax,prevmin, ans;
        currmax = currmin = prevmax = prevmin = ans = nums[0];
        for(int i=1;i<n;i++){
            currmax = Math.max(prevmax*nums[i],Math.max(nums[i],prevmin*nums[i]));
            currmin = Math.min(prevmax*nums[i],Math.min(nums[i],prevmin*nums[i]));
            prevmax = currmax;
            prevmin = currmin;
            ans = Math.max(ans,currmax);
        }
        return ans;
    }

    
    class StreamChecker {
        // 1032. Stream of Characters
        // for this question it is like suffix matching of words with the characters of query present
        // so we will use trie here then how to use it here
        // we will store the words in the reverse order in th trie then one by one when the
        // character will come we will out that into a stringbuilder and check for the matching by 
        // starting from the end of the stringbuilder to its start and here if at any point of time
        // curr reaches null this means no match so return false
        // and if it reaches the character in the trie whose isEnd = true then return true as 
        // the matching is done. If the whole stringbuilder is traversed then also there is no
        // matching of the word with the characters as something may be left in the word to be matched
        // so return false.
        //     time O( n*m + q^2) space O(n*m + q)
            private class TrieNode{
                TrieNode[] children;
                boolean isEnd;
                public TrieNode(){
                    children = new TrieNode[26];
                    isEnd = false;
                }
            }
            private TrieNode root;
            private StringBuilder sb;
            public StreamChecker(String[] words) {
                root = new TrieNode();
                sb = new StringBuilder();
                
                for(String word:words){
                    TrieNode curr = root;
                    for(int i=word.length()-1;i>=0;i--){
                        char ch = word.charAt(i);
                        if(curr.children[ch-'a']==null){
                            curr.children[ch-'a'] = new TrieNode();
                        }
                        curr = curr.children[ch-'a'];
                    }
                    curr.isEnd = true;
                }
            }
            
            public boolean query(char letter) {
                sb.append(letter);
                TrieNode curr = root;
                for(int i=sb.length()-1;i>=0;i--){
                    char ch = sb.charAt(i);
                    curr = curr.children[ch-'a'];
                    
                    if(curr==null){
                        return false;
                    }
                    if(curr.isEnd) return true;
                }
                return false;
            }
        }
        
        /**
         * Your StreamChecker object will be instantiated and called as such:
         * StreamChecker obj = new StreamChecker(words);
         * boolean param_1 = obj.query(letter);
         */
    
        //  codechef SnackDown 2021 - Elimination Parallel (Rated for Div 2)  
        // public static Scanner scn =new Scanner(System.in);
    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(
                new InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() { return Integer.parseInt(next()); }
 
        long nextLong() { return Long.parseLong(next()); }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    public static FastReader scn = new FastReader();
        // https://www.codechef.com/SDELP21B/problems/REMELEM
        // Remove Element 
        public static void solveRem(){
            int t=scn.nextInt();
            while(t-->0)
            {
                int n=scn.nextInt();
                int k = scn.nextInt();
                int[] arr = new int[n];
                for(int i=0;i<arr.length;i++){
                    arr[i] = scn.nextInt();
                }
                Arrays.sort(arr);
                int l =0, h =arr.length-1;
                while(l<h){
                if(arr[l]+arr[h]<=k){
                    h--;
                }
                else break;
                }
                if(l==h){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            
            }
        }


        // https://www.codechef.com/SDELP21B/problems/SUBPRB
        // Yet another subarray problem
        public static void solveSub(){
            int t=scn.nextInt();
            while(t-->0)
            {
             int n=scn.nextInt();
             int[] arr = new int[n];
             int p=1,q=2;
             if(n%2==0 || n==1){
                 for(int i=0;i<n;i++){
                     arr[i] = p;
                     p+=2;
                 }
             }
             else{
                 for(int i=0;i<n;i++){
                     arr[i] = q;
                     q+=2;
                 }
             }
             
                 for(int i=0;i<n;i++){
                     System.out.print(arr[i] + " ");
                 }
                 System.out.println();
            }
        }


        public class TreeNode {
                 int val;
                 TreeNode left;
                 TreeNode right;
                 TreeNode() {}
                 TreeNode(int val) { this.val = val; }
                 TreeNode(int val, TreeNode left, TreeNode right) {
                     this.val = val;
                     this.left = left;
                     this.right = right;
                 }
             }
    // 337. House Robber III
    //     time O(n) space  O(1) ignoring the recursive space

    // faith is that I will be asking from the left and right that what will be the profit if 
    // you will rob and will not rob then I will decide that what I have to do for profit by 
    // robbing and not robbing. Like we will be roobing current then we take 
    // l.q + r.q + root.val and when not then max(l.p,l.q) + max(r.p,r.q).  

//     interview related code
    private class housePair{
        int withRob = 0;
        int withoutRob = 0;
    }
    public housePair rob1_(TreeNode root){
        if(root == null) return new housePair();
        
        housePair left = rob1_(root.left);
        housePair right = rob1_(root.right);
        
        housePair myans = new housePair();
        myans.withRob = left.withoutRob + right.withoutRob + root.val;
        myans.withoutRob = Math.max(left.withRob,left.withoutRob) + Math.max(right.withRob,right.withoutRob);
        return myans;
    }
    public int rob1(TreeNode root) {
        housePair ans = rob1_(root);
        return Math.max(ans.withRob,ans.withoutRob);
    }
    
//     online test related code but do the upper one in interview.
//     {withRob,withoutRob} 1d array of size 2
    public int[] rob_(TreeNode root){
        if(root == null) return new int[2];
        
        int[] left = rob_(root.left);
        int[] right = rob_(root.right);
        
        int[] myans = new int[2];
        myans[0] = left[1] + right[1] + root.val;
        myans[1] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        return myans;
    }
    public int rob(TreeNode root) {
        int[] ans = rob_(root);
        return Math.max(ans[0],ans[1]);
    }


    
// 1217. Minimum Cost to Move Chips to The Same Position
//  even to even and odd to odd doesn't cost us anything i.e. cost is 0
//  but even to odd or vice versa is costing us 1 unit i.e. cost = 1
// so count even and odd occurences and return the minimum of them as we will switch the minimum 
// one into the maximum one.
    
    public int minCostToMoveChips(int[] position) {
        int evenCt = 0, oddCt = 0;
        for(int ele:position){
            if(ele%2==0){
                evenCt++;
            }
            else{
                oddCt++;
            }
        }
        return Math.min(evenCt,oddCt);
    }

    // 1290. Convert Binary Number in a Linked List to Integer
    //     time  O(n) space O(n)
    public int binToDec(StringBuilder sb){
        int dnum = 0, count=0;
        for(int i=sb.length()-1;i>=0;i--){
            if(sb.charAt(i) == '1')
            dnum = dnum + 1*(int)Math.pow(2,count);
            count++;
        }
        return dnum;
    }
    public int getDecimalValue1(ListNode head) {
        ListNode curr = head;
        int dnum = 0;
        StringBuilder sb = new StringBuilder();
//         get bnum from linkedlist
        while(curr!=null){
            sb.append(curr.val);
            curr = curr.next;
        }
//         convert the bnum to dnum
        dnum = binToDec(sb);
        return dnum;
    }
    
//     time O(n) space O(1)
    public int getDecimalValue2(ListNode head) {
        int num = 0;
        ListNode curr = head;
        while(curr!=null){
            num = num * 2 + curr.val;
            curr = curr.next;
        }
        return num;
    }
//     more faster through bits
    public int getDecimalValue(ListNode head) {
        int num = 0;
        ListNode curr = head;
        while(curr!=null){
            num = (num<<1) | curr.val;
            curr = curr.next;
        }
        return num;
    }

        // 563. Binary Tree Tilt
//     time O(n) space O(log n)
    private int tilt = 0;
//     return the sum and change the tilt
    private int findSum(TreeNode root){
         if(root == null){
            return 0;
        }
        
        int lsum = findSum(root.left);
        int rsum = findSum(root.right);
        
        int ctilt = Math.abs(lsum-rsum);
        tilt+=ctilt;
        
        int sum = lsum + rsum + root.val;
        return sum;
    }
    public int findTilt(TreeNode root) {
        findSum(root);
        return tilt;
    }

    
//     some kind of graph traversal is needed here
//     so we can use dfs or bfs but in dfs we will take one node and stretch it to 
//     complete its traversal and we may not find the answer but in bfs we do the 
//     things parrallely so less time will be taken to calculate the answer
    // 1306. Jump Game III
    public boolean canReach(int[] arr, int start) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = arr.length;
        boolean[] vis = new boolean[n];
        que.addLast(start);
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int rind = que.removeFirst();
                if(arr[rind] == 0){
                    return true;
                }
                int back = rind - arr[rind];
                int forw = rind + arr[rind];
                if(!vis[rind]){
                if(back>=0){
                    que.add(back);
                }
                if(forw<n){
                    que.add(forw);
                }
                vis[rind] = true;
                }
        }
        }
        return false;
    }



    
//     790. Domino and Tromino Tiling
//     time O(n) space O(n)
//     just to come with this equation is tough 
//     so dry run it and then do code
// previous will be taken care of and first three cases are special 
// then after that only trominoes have to be taken that are 2 more in the existing answer which is already 
// taken care of. Rest checkout the dry run. 
    public int numTilings(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        int mod = (int)1e9 +7;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for(int i=4;i<=n;i++){
            dp[i] = ((2*dp[i-1]%mod) + (dp[i-3]%mod))%mod;
        }
        return dp[n];
    }


    // https://www.codechef.com/START19B/problems/INDIPERM
    //  Indivisible Permutation 
        public static void solveIndivisible(){
            int t=scn.nextInt();
            while(t-->0)
            {
            int n=scn.nextInt();
            if(n==2){
                System.out.println("2 1");
                continue;
            }
            System.out.print("1 ");
            for(int itr=3;itr<=n;itr++){
                System.out.print(itr+" ");
            }
            System.out.print("2 ");
            System.out.println();
            }
            
        }

        // https://www.codechef.com/START19B/problems/CHEFCONTEST
        // Chef and Contest  
        public static void solveContest(){
            int t=scn.nextInt();
            while(t-->0)
            {
                int x = scn.nextInt();
                int y = scn.nextInt();
                int p = scn.nextInt();
                int q = scn.nextInt();
                int chef,chefina;
                
                chef = x + (p*10);
                chefina = y + (q*10);
                
                if(chef == chefina){
                    System.out.println("Draw");
                }
                
                else if(chef<chefina){
                    System.out.println("Chef");
                }
                else{
                    System.out.println("Chefina");
                }
                
            }
        }



        // https://www.codechef.com/START19B/problems/DISTELE
        // Distinct Elements

        public static void solveDistinct(){
            int t=scn.nextInt();
            while(t-->0)
            {
                int n=scn.nextInt(), mod = ((int)1e9) + 7,value;
                long ans = 1;
                HashMap<Integer,Integer> map = new HashMap<>();
                for(int i=0;i<n;i++){
                    int ele = scn.nextInt();
                    map.put(ele,map.getOrDefault(ele,0)+1);
                }
                
                for(int key:map.keySet())
                {
                    value = map.get(key);
                    if(value == 1) ans *=2;
                    else ans *= (value+1);
                    ans %= mod;
                }
                
                System.out.println(ans-1);
            }
        }


        // https://www.codechef.com/START19B/problems/FILL01
        // Sleepy Chef

        public static void solveSleepy(){
            int t=scn.nextInt();
            while(t-->0)
            {
                int n=scn.nextInt(),ans = 0,count=0;
                int k=scn.nextInt();
                String s = scn.next();
                
                for(int i=0;i<n;i++){
                    if(s.charAt(i) == '0'){
                        count++;
                        if(count==k){
                            ans++;
                            count=0;
                        }
                    }
                    else 
                    count=0;
                }
                
                
                System.out.println(ans);
            }
        }




            
    //  878. Nth Magical Number
    //  tc O(log n) sc O(1) 
    //  we need to analyse the pattern here and first of all why binary search 
    //  because of large constraints and the range that we are having will be
    //  considered in sorted order. After that we will see the series of factors
    //  of a and b respectively then we can only consider n/a and n/ b
    //  where repetition can be there so to subtract that we will subtract 
    //  n/lcm(a,b).
    //  a=2-> 2,4,6,8,10....   
    //  b=3-> 3,6,9,12,15....  
    //  n = 4
    //  l = 2, h =8
    // 6/2 + 6/3 - 6/6 = 3 + 1 -0 = 4
    // if the factor other than 6 here is < 4 then go in upper half otherwise go in lower half.
    // to get this result we will be applying binary search, rest do it by dry run
        
    public int gcd(int a,int b){
        if(a==0) return b;
        return gcd(b%a,a);
    }
    public int nthMagicalNumber(int n, int a, int b) {
        long mod = ((int)1e9) + 7;
        long lcm = (a*b)/gcd(a,b);
        long low = Math.min(a,b);
        long high = low*n,factor=1;
        while(low<high){
            long mid = low + (high-low)/2;
            factor = (mid/a) + (mid/b) - (mid/lcm);
            if(factor<n){
                low = mid + 1;
            }
            else{
                high = mid;
            }
        }
        return (int)(low%mod);
    }


    
    
    // 416. Partition Equal Subset Sum
    //    tc O(nums.length*totSum)
//    sc O(nums.length*totSum)
//     In this question we will be searching for the totSum/2 target
//     if it can be acheived then we can return true otherwise false
//    in dp we are having two choices for each element for its inclusion or
//     exclusion but in inclusion only if we can accomadate that in our sum.

    
    //     perfect exaplanation of a coder
//     This problem is essentially let us to find whether there are several numbers in a set which are able to sum to a specific value (in this problem, the value is sum/2).

// Actually, this is a 0/1 knapsack problem, for each number, we can pick it or not. Let us assume dp[i][j] means whether the specific sum j can be gotten from the first i numbers. If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true, otherwise it is false.

// Base case: dp[0][0] is true; (zero number consists of sum 0 is true)

// Transition function: For each number, if we don't pick it, dp[i][j] = dp[i-1][j], which means if the first i-1 elements has made it to j, dp[i][j] would also make it to j (we can just ignore nums[i]). If we pick nums[i]. dp[i][j] = dp[i-1][j-nums[i]], which represents that j is composed of the current value nums[i] and the remaining composed of other previous numbers. Thus, the transition function is dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
    
    
    public boolean canPartition(int[] nums) {
        int totSum = 0;
        for(int ele:nums){
            totSum += ele;
        }
        if(totSum%2!=0){
            return false;
        }
        boolean[][] dp = new boolean[nums.length+1][totSum/2 + 1];
        for(int i=0;i<dp.length;i++){
            dp[i][0] = true;
        }
        
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
//                 for exclusion
                dp[i][j] = dp[i-1][j];
//                 for inclusion
                if(j>=nums[i-1]){
                    dp[i][j] = dp[i][j] || dp[i-1][j-nums[i-1]];  
                }
            }
        }
        return dp[nums.length][totSum/2];
    }


    
    // 1446. Consecutive Characters
    // set one character and check for the other character if it is equal or not and according update the answer
//     tc O(n^2) sc O(1) => brute
    public int maxPower1(String s) {
        int ans = 0, count = 1,i=0,j=0, n =s.length();
        while(i<n){
            char ch = s.charAt(i);
            j=i+1;
            count=1;
            while(j<n){
                char ch1 = s.charAt(j);
                if(ch == ch1){
                    count++;
                    j++;
                }else{
                    i=j-1;
                    break;
                }
            }
            i++;
            ans = Math.max(ans,count);
        }
        return ans;
        
    }
//     tc O(n) sc O(1) => Optimised
// this is nice we can just compare the current with lasgt one if it is equal increase count and also check
// for the max ans so far and if not reset the counter to 1 and after traversing you will get the answer. 
     public int maxPower(String s) {
         int count=1,ans=1;
         for(int i=1;i<s.length();i++){
             if(s.charAt(i) == s.charAt(i-1)){
                 if(++count>ans){
                     ans = count;
                 }
             }
             else{
                 count=1;
             }
         }
         return ans;
     }


    //  938. Range Sum of BST
//     brute force
//     tc O(n) sc O(height)
//     if I am in the range add me in the answer and take the ans
//     from both sides along with it and return and if not return 0
//     this can also be done in binary trees bst property is not used here
    public int rangeSumBST1(TreeNode root,int low,int high){
        if(root == null) return 0;
        
        int sum = 0;
        sum+=rangeSumBST1(root.left,low,high);
        sum+=rangeSumBST1(root.right,low,high);
        
        if(root.val>=low && root.val<=high){
            sum += root.val;
        }
        
        return sum;
    }
//         optimised    
//     if I am in the range i will call both sides but if I am not 
//    then i will call in only one side depending upon the condition 
    // tc O(n) sc O(height) , height= log n  but for skewed tree height = n
    public int sum = 0;
    public void rangeSumBST2(TreeNode root,int low,int high){
        if(root==null){
            return;
        }
        if(root.val>=low && root.val<=high){
            sum += root.val;
            rangeSumBST2(root.left,low,high);
            rangeSumBST2(root.right,low,high);
        }
        else if(root.val<low){
            rangeSumBST2(root.right,low,high);
        }
        else if(root.val>high){
            rangeSumBST2(root.left,low,high);
        }
    }
    public int rangeSumBST(TreeNode root, int low, int high) {
        // return rangeSumBST1(root,low,high);

        rangeSumBST2(root,low,high);
        return sum;
    }


    

}