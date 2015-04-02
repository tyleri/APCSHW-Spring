import java.util.Scanner;
import java.io.File;

public class Maze {

    private char[][]maze;
    private int maxx,maxy;
    private int startx,starty;
    private MyDeque<Node> frontier;

    private int DFS_MODE = 0;
    private int BFS_MODE = 1;
    
    private class Node {

        private int x;
        private int y;
        private Node prev;

        public Node(int xcor, int ycor) {
            x = xcor;
            y = ycor;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

    }

    //Terminal keycodes to clear the terminal, or hide/show the cursor
    private String clear =  "\033[2J";
    private String hide =  "\033[?25l";
    private String show =  "\033[?25h";

    public Maze(String filename){
        startx = -1;
        starty = -1;
        //read the whole maze into a single string first
        String ans = "";
        try{
            Scanner in = new Scanner(new File(filename));

            //keep reading next line
            while(in.hasNext()){
                String line = in.nextLine();
                if(maxy == 0){
                    //calculate width of the maze
                    maxx = line.length();
                }
                //every new line add 1 to the height of the maze
                maxy++;
                ans += line;
            }
        }
        catch(Exception e){
            System.out.println("File: " + filename + " could not be opened.");
            e.printStackTrace();
            System.exit(0);
        }

        //copy from the single string to a 2D array
        maze = new char[maxy][maxx];
        for(int i = 0; i < ans.length(); i++){
            char c = ans.charAt(i);
            maze[i / maxx][i % maxx] = c;
            if(c == 'S'){
                startx = i / maxx;
                starty = i % maxx;
            }
        }
    }

    //terminal specific character to move the cursor
    private String go(int x,int y){
        return ("\033[" + x + ";" + y + "H");
    }


    private String color(int foreground,int background){
        return ("\033[0;" + foreground + ";" + background + "m");
    }

    public void clearTerminal(){
        System.out.println(clear);
    }

    public void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }

    public boolean solveBFS() {
        return solveBFS(false);
    }

    public boolean solveDFS() {
        return solveDFS(false);
    }

    private boolean solve(int mode, boolean animate) {
        frontier.addFirst( new Node(startx, starty) );
        return false;
    }

    public boolean solveBFS(boolean animate) {
        return solve(BFS_MODE, animate);
    }

    public boolean solveDFS(boolean animate) {
        return solve(DFS_MODE, animate);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++)
                s += maze[i][j];
            s += '\n';
        }
        return s;
    }

    public String toString(boolean animate) {
        return "";
    }

    /**return an array [x1,y1,x2,y2,x3,y3...]
      *that contains the coordinates of the solution from start to end.
      *Precondition :  solveBFS() OR solveDFS() has already been called
      *(otherwise an empty array is returned)
      *Postcondition:  the correct solution is in the returned array
      */
    public int[] solutionCoordinates() {
        return new int[0];
    }    

    public static void main(String[] args) {
        Maze m = new Maze("data1.dat");
        System.out.println(m);
    }
}
