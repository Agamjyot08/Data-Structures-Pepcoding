import java.util.*;
public class l002_BST{
    public static class Node{
        public static int size(Node node) {
            // write your code here
            return node==null?0:size(node.left)+size(node.right)+1;
          }
        
          public static int sum(Node node) {
            // write your code here
            return node==null?0:sum(node.left)+sum(node.right)+node.data;
          }
        
          public static int max(Node node) {
            // write your code here
            while(node.right!=null)
            {
                node=node.right;
            }
            return node.data;
          }
        
          public static int min(Node node) {
            // write your code here
            while(node.left!=null)
            {
                node=node.left;
            }
            return node.data;
          }
        
          public static boolean find(Node node, int data){
            // write your code here
            while(node!=null)
            {
                if(node.data==data)
                {
                    return true;
                }
                else if(node.data<data)
                {
                    node=node.right;
                }
                else
                {
                    node=node.left;
                }
            }
            return false;
          }  
       //   T O(n^2), S O(1)
        public static ArrayList<Node> nodeToRootPath(Node node,int data)
        {
            ArrayList<Node> path=new ArrayList<>();
            boolean flag=false;
            while(node!=null)
            {
                path.add(node);
                if(node.data==data)
                {
                    flag=true;
                    break;
                }
                else if(node.data<data)
                {
                    node=node.right;
                }
                else
                {
                    node=node.left;
                }
            }
            if(!flag) path.clear();
            Collections.reverse(path);
            return path;
        }
        //T: O(logn) S:O(1)
        //dono ek jagah hai d1 and d2 to aage chalo jis stage pai nhi honge vhi lca hoga
        public static int lca(Node node, int d1, int d2) {
            int lca=-1;
            while(node!=null)
            {
                if(node.data>d1 && node.data>d2)
                {
                    node=node.left;
                }
                else if(node.data<d1 && node.data<d2)
                {
                    node=node.right;
                }
                else
                {
                    lca=node.data;
                    break;
                }
            }
            return lca;
     }
   //print in range 
   //inorder traversal karo and beech mai range mai ho to print kardo 
   //time : O(n) time 
   public static void pir(Node node, int d1, int d2) {
    // write your code here
    if(node==null)
    return;
    if(node.data>=d1)
        pir(node.left,d1,d2);
    
    if(node.data>=d1 && node.data<=d2)
    {
        System.out.println(node.data);
    }
    if(node.data<=d2)
        pir(node.right,d1,d2);
    
  }
  //add node
  //change in the structure of the tree ho to always use recursion
  //jaha sai asli jagah mil jaye to backtrack karte vakt link bante hue chalenge and then return karte chalo
  public static Node add(Node node, int data) {
    // write your code here
    if(node==null)
    {
        return new Node(data,null,null);
    }
    if(node.data>data)
    {
        node.left=add(node.left,data);
    }
    else if(node.data<data)
    {
        node.right=add(node.right,data);
    }
    return node;
  }
  //remove node 
  //pehle dhund left mai ya right mai kaha pai data hai
  //jab mil jaye to teen cases 0 child, 1child,2 children
   //ko handle kardo ,aur return kara dena result to vo attach hojaega
   //2 child mai left max or right min nikal ke current mai replace kardo
   //and then usko bhi delete kara ke attach kara do 
  public static int maximum(Node node)
  {
      return node==null?-(int)1e9:Math.max(Math.max(maximum(node.left),maximum(node.right)),node.data);
  }
public static Node remove(Node node, int data) {
  // write your code here
  if(node==null) return null;
  if(node.data>data)
  node.left=remove(node.left,data);
  else if(node.data<data)
  node.right=remove(node.right,data);
  else
  {
      // 0 child and 1 child case is handled
      if(node.left==null || node.right==null)
      {
          return node.left!=null?node.left:node.right;
      }
      else
      {
          int leftmax=maximum(node.left);
          node.data=leftmax;
          node.left=remove(node.left,leftmax);
      }
  }
  return node;
}


    }
}