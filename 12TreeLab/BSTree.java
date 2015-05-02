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
    
    /*======== public String toString()) ==========
      Inputs:   
      Returns: A string representation of the tree

      ====================*/
    public String toString() {
        int height = getHeight();
        String[][] treeArr = new String[height][(int)Math.pow(2, height)-1];
        for (int i = 0; i < treeArr.length; i++) {
            Arrays.fill(treeArr[i], "");
        }

        fillTreeArr(treeArr, root, 0, treeArr[0].length-1, 0);

        String s = "";
        for (int i = 0; i < treeArr.length; i++) {
            for (int j = 0; j < treeArr[i].length; j++) {
                s += treeArr[i][j];
            }
            s += "\n\n";
        }
        return s;
    }

    private void fillTreeArr(String[][] arr, BSTreeNode<T> tn, int minx, int maxx, int y) {
        if (tn == null) {
            return;
        }
        int x = (minx + maxx)/2;
        arr[y][x] = String.valueOf(tn.getData());
        String spaces = String.format("%" + arr[y][x].length() + "s", "");

        // set spaces above to the same length of spaces
        for (int i = y-1; i >= 0; i--) {
            arr[i][x] = spaces;
        }
        // set spaces below to the same length of spaces
        for (int i = y+1; i < arr.length; i++) {
            arr[i][x] = spaces;
        }

        fillTreeArr(arr, tn.getLeft(), minx, x-1, y+1);
        fillTreeArr(arr, tn.getRight(), x+1, maxx, y+1);
    }

    public static void main( String[] args ) {
        BSTree bs = new BSTree<Integer>();
        bs.add(10);
        bs.add(50);
        bs.add(5);
        bs.add(3);
        bs.add(9);
        bs.add(50000);
        bs.add(0);
        bs.add(25);
        bs.add(8);
        System.out.println(bs);
    }

}
