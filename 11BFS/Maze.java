// Attribution/Disclaimer:
//
// The constructor, test *.dat files,
// and code to print out the maze
// animatedly with the special
// characters were imported
// from Mr. K's MazeSolver files.

import java.util.Scanner;
import java.io.File;

public class Maze {

    private char[][]maze;
    private int maxx,maxy;
    private int startx,starty,endx,endy;
    private MyDeque<Node> frontier;
    private int[] solution;
    private int BFS = 0;
    private int DFS = 1;
    private int BEST = 2;

    private class Node {

        private int x;
        private int y;
        private Node prev;

        public Node(int xcor, int ycor, Node prevNode) {
            x = xcor;
            y = ycor;
            prev = prevNode;
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

    }

    //Terminal keycodes to clear the terminal, or hide/show the cursor
    private String clear =  "\033[2J";
    private String hide =  "\033[?25l";
    private String show =  "\033[?25h";

    public Maze(String filename){
        // instantiate variables
        frontier = new MyDeque<Node>();
        solution = new int[0];

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
                startx = i % maxx;
                starty = i / maxx;
            }
            if(c == 'E'){
                endx = i % maxx;
                endy = i / maxx;
            }
        }
    }

    //terminal specific character to move the cursor
    private String go(int x,int y){
        return ("\033[" + x + ";" + y + "H");
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

    public boolean solveBest() {
        return solveBest(false);
    }

    public boolean solveBFS(boolean animate) {
        return solve(BFS, animate);
    }

    public boolean solveDFS(boolean animate) {
        return solve(DFS, animate);
    }

    public boolean solveBest(boolean animate) {
        return solve(BEST, animate);
    }

    private boolean solve(int mode, boolean animate) {
        int currx = startx, curry = starty;
        Node n = new Node(currx, curry, null);

        // copy maze over so I can modify it without worrying
        char[][] mazeCopy = new char[maxy][maxx];
        for (int i = 0; i < maxy; i++)
            for (int j = 0; j < maxx; j++)
                mazeCopy[i][j] = maze[i][j];

        do {
            // mark the spaces already passed
            if (maze[curry][currx] != 'S')
                maze[curry][currx] = 'x';

            // animate the solve if animate == true
            if (animate) {
                System.out.println( toString(true) );
                wait(20);
            }

            // check bottom space
            if ( maze[curry+1][currx] != '#' && maze[curry+1][currx] != 'x' ) {
                if (mode == BFS)
                    frontier.addLast( new Node(currx, curry+1, n) );
                else if (mode == DFS)
                    frontier.addFirst( new Node(currx, curry+1, n) );
                else if (mode == BEST)
                    frontier.add(
                            new Node(currx, curry+1, n),
                            Math.abs(endx-currx) + Math.abs(endy-(curry+1)));
            }
            // check top space
            if ( maze[curry-1][currx] != '#' && maze[curry-1][currx] != 'x' ) {
                if (mode == BFS)
                    frontier.addLast( new Node(currx, curry-1, n) );
                else if (mode == DFS)
                    frontier.addFirst( new Node(currx, curry-1, n) );
                else if (mode == BEST)
                    frontier.add(
                            new Node(currx, curry-1, n),
                            Math.abs(endx-currx) + Math.abs(endy-(curry-1)));
            }
            // check right space
            if ( maze[curry][currx+1] != '#' && maze[curry][currx+1] != 'x' ) {
                if (mode == BFS)
                    frontier.addLast( new Node(currx+1, curry, n) );
                else if (mode == DFS)
                    frontier.addFirst( new Node(currx+1, curry, n) );
                else if (mode == BEST)
                    frontier.add(
                            new Node(currx+1, curry, n),
                            Math.abs(endx-(currx+1)) + Math.abs(endy-curry));
            }
            // check left space
            if ( maze[curry][currx-1] != '#' && maze[curry][currx-1] != 'x' ) {
                if (mode == BFS)
                    frontier.addLast( new Node(currx-1, curry, n) );
                else if (mode == DFS)
                    frontier.addLast( new Node(currx-1, curry, n) );
                else if (mode == BEST)
                    frontier.add(
                            new Node(currx, curry, n),
                            Math.abs(endx-(currx-1)) + Math.abs(endy-curry));
            }

            // if there's nowhere else to go, there's no exit path
            if ( frontier.size() == 0 ) {
                maze = mazeCopy;
                return false;
            }

            // get the next space to check
            if (mode == BEST)
                n = frontier.removeSmallest();
            else
                n = frontier.removeFirst();
            currx = n.getX();
            curry = n.getY();

        } while ( maze[curry][currx] != 'E' );

        MyDeque<Integer> exitPath = new MyDeque<Integer>();
        // add exit to exitPath
        exitPath.addFirst( n.getY() );
        exitPath.addFirst( n.getX() );
        n = n.getPrev();
        // add each step to exitPath and place an 'x' there
        while ( n.getPrev() != null ) {
            exitPath.addFirst( n.getY() );
            exitPath.addFirst( n.getX() );
            mazeCopy[n.getY()][n.getX()] = 'x';
            n = n.getPrev();
        }
        // add start to exitPath
        exitPath.addFirst( n.getY() );
        exitPath.addFirst( n.getX() );

        // copy coordinates to solution array
        solution = new int[ exitPath.size() ];
        int i = 0;
        while (exitPath.size() > 0) {
            solution[i] = exitPath.removeFirst();
            i++;
        }

        maze = mazeCopy;

        return true;
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
        if (!animate)
            return toString();
        return hide + go(0,0) + toString() + "\n" + show;
    }

    /**return an array [x1,y1,x2,y2,x3,y3...]
     *that contains the coordinates of the solution from start to end.
     *Precondition :  solveBFS() OR solveDFS() has already been called
     *(otherwise an empty array is returned)
     *Postcondition:  the correct solution is in the returned array
     */
    public int[] solutionCoordinates() {
        return solution;
    }    

    public static void main(String[] args) {
        if (args.length == 0) {
            Maze m = new Maze("data1.dat");
            System.out.println(m.solveBFS(true));
            System.out.println(m);
        } else {
            Maze m = new Maze(args[0]);
            System.out.println(m);
            if (args.length == 2) {
                int mode = Integer.parseInt(args[1]);
                System.out.println(
                        mode == 0 ? m.solveBFS(true) :
                        mode == 1 ? m.solveDFS(true) :
                        mode == 2 ? m.solveBest(true) :
                        "Invalid mode");
            }
            System.out.println(m);
        }
    }
}
