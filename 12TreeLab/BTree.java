/*========== BTree.java ==========  
  Lab: Complete
  1. TreeNode.java
  2. add()
  3. pre/post/in Order()
  4. getHeight
  5. getLevel
  6. toString

  Basic binary tree.
  Uses TreeNode
  Stolen from DW.
=========================*/

import java.io.*;
import java.util.*;

public class BTree<E> {

    public static final int PRE_ORDER = 0;
    public static final int IN_ORDER = 1;
    public static final int POST_ORDER = 2;

    private Random rand;

    private TreeNode<E> root;

    public BTree() {
        root = null;
        rand = new Random();
    }

    public BTree(long seed) {
        root = null;
        rand = new Random(seed);
    }

    /*======== public void add() ==========
      Inputs:   E d
      Returns: 
      
      Wrapper method for the recursive add()
      ====================*/     
    public void add( E d ) {
        TreeNode<E> tn = new TreeNode<E>(d);
        
        if (root == null)
            root = tn;
        else
            add(root, tn);
    }

    /*======== public void add() ==========
      Inputs:   TreeNode<E> curr, TreeNode<E> bn  
      Returns: 
      
      Adds bn to the tree rooted at curr. If curr has 
      an available child space, then attach bn there.

      Otherwise, try to add at the subtree rooted at
      one of curr's children. Choose the child to be
      added to randomly.
      ====================*/
    private void add( TreeNode<E> curr, TreeNode<E> bn ) {
        TreeNode<E> left = curr.getLeft(), right = curr.getRight();

        if (left == null && right == null) {
            if (rand.nextInt(2) == 0)
                curr.setLeft(bn);
            else
                curr.setRight(bn);
        } else if (left == null) {
            curr.setLeft(bn);
        } else if (right == null) {
            curr.setRight(bn);
        } else {
            add((rand.nextInt(2) == 0 ? left : right), bn);
        }

    }

    public void traverse( int mode) {
        if ( mode == PRE_ORDER )
            preOrder( root );
        else if ( mode == IN_ORDER )
            inOrder( root );
        else
            postOrder( root );
        System.out.println();
    }

    /*======== public void preOrder() ==========
      Inputs:   TreeNode<E> curr  
      Returns: 
      
      Prints out the elements in the tree by doing an
      pre-order Traversal
      ====================*/
    public void preOrder( TreeNode<E> curr ) {
        System.out.print(curr.getData() + " ");
        if (curr.getLeft() != null)
            preOrder(curr.getLeft());
        if (curr.getRight() != null)
            preOrder(curr.getRight());
    }

    /*======== public void inOrder() ==========
      Inputs:   TreeNode<E> curr  
      Returns: 
      
      Prints out the elements in the tree by doing an
      in-order Traversal
      ====================*/
    public void inOrder( TreeNode<E> curr ) {
        if (curr.getLeft() != null)
            inOrder(curr.getLeft());
        System.out.print(curr.getData() + " ");
        if (curr.getRight() != null)
            inOrder(curr.getRight());
    }

    /*======== public void postOrder() ==========
      Inputs:   TreeNode<E> curr  
      Returns: 
      
      Prints out the elements in the tree by doing a
      post-order Traversal    

      ====================*/
    public void postOrder( TreeNode<E> curr ) {
        if (curr.getLeft() != null)
            postOrder(curr.getLeft());
        if (curr.getRight() != null)
            postOrder(curr.getRight());
        System.out.print(curr.getData() + " ");
    }
    
    /*======== public int getHeight()) ==========
      Inputs:   
      Returns: The height of the tree

      Wrapper for the recursive getHeight method
      ====================*/
    public int getHeight() {
        return getHeight( root );
    }
    /*======== public int getHeight() ==========
      Inputs:   TreeNode<E> curr  
      Returns:  The height of the tree rooted at node curr
      
      ====================*/
    public int getHeight( TreeNode<E> curr ) {
        TreeNode<E> left = curr.getLeft(), right = curr.getRight();
        int leftHt, rightHt;

        if (left == null && right == null)
            return 1;

        leftHt = (left == null ? 0 : getHeight(left));
        rightHt = (right == null ? 0 : getHeight(right));
        return 1 + Math.max(leftHt, rightHt);
    }

    /*======== public String getLevel() ==========
      Inputs:   TreeNode<E> curr
                int level
                int currLevel  
      Returns: A string containing all the elements on the
               given level, ordered left -> right
      
      ====================*/
    private String getLevel( TreeNode<E> curr, int level, int currLevel ) {

        int betSize = ((int)Math.pow(2, getHeight() - level) * 2 - 1) * 2;

        // create spaces between values
        char[] arr = new char[betSize];
        Arrays.fill(arr, ' ');
        String bet = new String(arr);

        if (curr == null) {
            String s = "";
            for (int i = 0; i < Math.pow(2, level - currLevel); i++)
                s += "xx" + bet;
            return s;
        }
        if (level == currLevel)
            return String.format("%2s", curr.getData()).replace(' ', '0')
                   + (level == 1 ? "" : bet);

        return getLevel( curr.getLeft(), level, currLevel+1 ) +
               getLevel( curr.getRight(), level, currLevel+1 );
    }
    
    /*======== public String toString()) ==========
      Inputs:   
      Returns: A string representation of the tree
     
      This string should display each level as a separate line.
      A simple version might look something like this:

      0
      1 2
      3 4 5

      Note that you cannot tell exactly where 3, 4 and 5 lie.
      That is ok, but if you want a CHALLENGE, you can try to
      get the output to look nicer, something like this:
             0

          1      2

            3  4   5

      ====================*/
    public String toString() {
        String s = "";
        char[] arr;
        String begin;

        for (int i = 1; i <= getHeight(); i++) {
            arr = new char[((int)Math.pow(2, getHeight() - i) - 1) * 2];
            Arrays.fill(arr, ' ');
            begin = new String(arr);

            s += begin + getLevel(root, i, 1) + "\n";
        }
        return s;
    }
    
    public static void main( String[] args ) {

        BTree<Integer> t = new BTree<Integer>();

        for ( int i=0; i < 22; i++ ) 
            t.add( i );
        System.out.println( "Pre-order: ");
        t.traverse( PRE_ORDER );
        System.out.println( "In-order: ");
        t.traverse( IN_ORDER );
        System.out.println( "Post-order: ");
        t.traverse( POST_ORDER );
        System.out.println( "Height: " + t.getHeight() );

        System.out.println( t );
    }
}
