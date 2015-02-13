import java.util.*;
import java.io.*;


public class KnightsTour{
    //constants for the class
    //terminal specific character to clear screen , or hide/show cursor
    final static String clear =  "\033[2J";
    final static String hide =  "\033[?25l";
    final static String show =  "\033[?25h";

    //instance variable
    private int[][]board;

    //terminal specific character to move the cursor
    private String go(int x,int y){
        return ("\033[" + x + ";" + y + "H");
    }

    public void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }

    public String toString(){
        String ans = "\n";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int num = board[i][j];
                ans += ( num < 10 ? " " + num : num);
                ans += " ";
            }
            ans += '\n';
        }
        return hide + clear + go(0,0) + ans + "\n" + show;
    }

    public KnightsTour(int size){
        board = new int[size][size];
    }

    public String name() {
        return "ishikawa.tyler";
    }

    public boolean solve(){
        return solve(0, 0, 1);
    }

    public boolean solve(int x, int y){
        return solve(x, y, 1);
    }

    public boolean solve(int x,int y,int currentMoveNumber){
        //System.out.println(this);
        //wait(20);
        if (x < 0 || y < 0 || x >= board.length || y >= board.length)
            return false;
        else if ( board[x][y] != 0 )
            return false;
        else {
            board[x][y] = currentMoveNumber;
            if (currentMoveNumber++ == board.length * board.length)
                return true;
            boolean pathFound = solve(x+2, y+1, currentMoveNumber)
                             || solve(x+2, y-1, currentMoveNumber)
                             || solve(x-2, y+1, currentMoveNumber)
                             || solve(x-2, y-1, currentMoveNumber)
                             || solve(x+1, y+2, currentMoveNumber)
                             || solve(x+1, y-2, currentMoveNumber)
                             || solve(x-1, y+2, currentMoveNumber)
                             || solve(x-1, y-2, currentMoveNumber);
            if (! pathFound)
                board[x][y] = 0;
            return pathFound;
        }
    }

    public static void main(String[] args) {
        KnightsTour kt = new KnightsTour(5);
        System.out.println(kt.name());

        System.out.println();

        if (kt.solve())
            System.out.println( kt );

        System.out.println();

        kt = new KnightsTour(5);
        if (kt.solve(3,3))
            System.out.println( kt );
    }

}
