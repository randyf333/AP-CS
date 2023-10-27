// Name: 
// Date: 

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BST implements BSTinterface
{
   /*  copy your BST code  here  */
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root,s);
      size++;
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null)
      {
         t = new TreeNode(s);
         return t;
      }
      if(s.compareTo((String)t.getValue()) < 0)
      {
         t.setLeft(add(t.getLeft(),s));
      }else if(s.compareTo((String)t.getValue()) > 0)
      {
         t.setRight(add(t.getRight(),s));
      }else{
         t.setLeft(add(t.getLeft(),s));
      }
      return t;
   }
   
   public String display()
   {
      return display(root,0);
   }
   
   private String display(TreeNode t, int level) //recursive helper method
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean contains( String obj)
   {
      return contains(root,obj);
   }
   
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null)
      {
         return false;
      }
      if(t.getValue().equals(x))
      {
         return true;
      }
      boolean left = contains(t.getLeft(),x);
      if(left)
         return true;
      boolean right = contains(t.getRight(),x);
      return right;
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      TreeNode node = t;   
      while(node.getLeft() != null)
      {
         node = node.getLeft();
      }
      return (String)node.getValue();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t.getRight() != null) {
         return max(t.getRight());
      }
      return t.getValue().toString();
   }
   
   public String toString()
   {
      return toString(root);
   }
   
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
      {
         return "";
      }
      toReturn += toString(t.getLeft());
      toReturn += t.getValue()+ " ";
      toReturn += toString(t.getRight());
      return toReturn;
   } 
   
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   
   private TreeNode remove(TreeNode current, String target)
   {
      if(current == null)
      {
         return current;
      }
      if(target.compareTo((String)current.getValue()) < 0)
      {
         current.setLeft(remove(current.getLeft(),target));
      }else if(target.compareTo((String)current.getValue()) > 0)
      {
         current.setRight(remove(current.getRight(),target));
      }else{
         if(current.getLeft() == null)
         {
            return current.getRight();
         }else if(current.getRight() == null)
         {
            return current.getLeft();
         }
         current.setValue(max(current.getLeft()));
         current.setLeft(remove(current.getLeft(),(String)current.getValue()));
      }
      return current;
   }
   
   /*  start the addBalanced methods */
   public void addBalanced(String value)  
   {
      size++;
      root = add(root, value);
      root = balanceTree(root);   // for an AVL tree.  You may change this line.
   }
   
   private TreeNode balanceTree(TreeNode t)//recursion is better
   {
      if(t == null)
      {
         return null;
      }
      int check = balance(t);
      /*if(check <= 1 && check >= -1 && balance(t.getRight()) <= 1 && balance(t.getRight()) >= -1 && balance(t.getLeft()) >= -1 && balance(t.getLeft()) <= 1)
      //if(check == 0)
      {
         return t;
      }*/
      t.setRight(balanceTree(t.getRight()));
      t.setLeft(balanceTree(t.getLeft()));
      check = balance(t);
      if(check < -1)
      {
         if(balance(t.getRight()) > 0)
         {
            return rightLeftRotate(t);
         }else{
            return leftRotate(t);
         }     
      }else if(check > 1){
         if(balance(t.getLeft()) < 0)
         {
            return leftRightRotate(t);
         }else{
            return rightRotate(t);
         }
      }
      return t;         
   }
   
   private int balance(TreeNode t)
   {
      if(t == null)
      {
         return 0;
      }
      return height(t.getLeft()) - height(t.getRight());
   }
   private int height(TreeNode t)
   {
      if(t == null){
         return -1;
      }
      int lHeight = height(t.getLeft());
      int rHeight = height(t.getRight());
      if(lHeight > rHeight)
      {
         return lHeight+1;
      }else{
         return rHeight+1;
      }   
   }
   private TreeNode rightRotate(TreeNode t)
   {
      TreeNode node = t.getLeft();
      TreeNode temp = node.getRight();
      node.setRight(t);
      t.setLeft(temp);
      return node;
   }
   private TreeNode leftRotate(TreeNode t)
   {
      TreeNode node = t.getRight();
      TreeNode temp = node.getLeft();
      node.setLeft(t);
      t.setRight(temp); 
      return node;
   }
   private TreeNode leftRightRotate(TreeNode t)
   {
      t.setLeft(leftRotate(t.getLeft()));
      return rightRotate(t);
   }
   private TreeNode rightLeftRotate(TreeNode t)
   {
      t.setRight(rightRotate(t.getRight()));
      return leftRotate(t);
   }
}