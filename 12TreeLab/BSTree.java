//Your binary search tree skeleton file:



import java.io.*;
import java.util.*;

public class BSTree <T extends Comparable> {

    private BSTreeNode<T> root;

    public BSTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }
    public boolean isLeaf( BSTreeNode<T> t ) {
        return (t.getLeft() == null && t.getRight() == null);
    }

    /*======== public void add() ==========
      Inputs:   T c  
      Returns: 

      Wrapper for the recursive add method
      ====================*/
    public void add( T c ) {
        root = add( root, new BSTreeNode<T>(c) );
    }

    /*======== public BSTreeNode<T> add() ==========
      Inputs:  BSTreeNode<T> curr
               BSTreeNode<T> t 
      Returns: 

      Add t to the correct place in the tree rooted at curr.
      ====================*/
    private BSTreeNode<T> add(BSTreeNode<T> curr, BSTreeNode<T> t) {
        if (curr == null) {
            return t;
        }
        
        if (t.compareTo(curr) < 0) {
            curr.setLeft( add(curr.getLeft(), t) );
        } else {
            curr.setRight( add(curr.getRight(), t) );
        }
        return curr;
    }

    /*======== public void remove() ==========
      Inputs:   T c  
      Returns: 
      
      Wrapper for the recursive remove method
      ====================*/
    public void remove( T c ) {
        root = remove( root, c );
    }

    /*======== public BSTreeNode<T> remove() ==========
      Inputs:   BSTreeNode<T> curr
        T c
      Returns: 

      Should remove the value c from the tree rooted at
      curr, if it exists.
      ====================*/
    private BSTreeNode<T> remove( BSTreeNode<T> curr, T c ) {
        if (c.compareTo(curr) < 0) {
            curr.setLeft( remove(curr.getLeft(), c) );
        } else if (c.compareTo(curr) > 0) {
            curr.setRight( remove(curr.getRight(), c) );
        } else if (isLeaf(curr)) {
            return null;
        } else if (curr.getLeft() == null) {
            return curr.getLeft();
        } else if (curr.getRight() == null) {
            return curr.getRight();
        }
        return curr;
    }


    /*======== public void inOrder()) ==========
      Inputs:   
      Returns: 

      Wrapper for the recursive inOrder method
      ====================*/
    public void inOrder() {
        inOrderHelper( root );
        System.out.println();
    }

    /*======== public void inOrderHelper() ==========
      Inputs:   BSTreeNode<T> t  
      Returns: 
      
      Performs an in-order traversal for the tree with 
      root t.
      ====================*/
    public void inOrderHelper( BSTreeNode<T> t ) {
        if (t == null) 
            return;
        inOrderHelper( t.getLeft() );
        System.out.print( t.getData() + " ");
        inOrderHelper( t.getRight() );
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
      Inputs:   BSTreeNode<T> curr  
      Returns:  The height of the tree rooted at node curr

      ====================*/
    public int getHeight( BSTreeNode<T> curr ) {
        BSTreeNode<T> left = curr.getLeft(), right = curr.getRight();
        int leftHt, rightHt;

        if (left == null && right == null)
            return 1;

        leftHt = (left == null ? 0 : getHeight(left));
        rightHt = (right == null ? 0 : getHeight(right));
        return 1 + Math.max(leftHt, rightHt);
    }


    /*======== public String getLevel() ==========
      Inputs:   BSTreeNode<T> curr
                int level
                int currLevel  
      Returns: A string containing all the elements on the
               given level, ordered left -> right
      
      ====================*/
    private String getLevel( BSTreeNode<T> curr, int level, int currLevel ) {

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
        BSTree bs = new BSTree<Integer>();
        bs.add(5);
        bs.add(3);
        bs.add(9);
        bs.add(100);
        bs.inOrder();
        System.out.println(bs);
    }

}
