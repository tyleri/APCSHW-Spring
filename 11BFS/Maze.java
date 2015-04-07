import java.util.Scanner;
import java.io.File;

public class Maze {

    private char[][]maze;
    private int maxx,maxy;
    private int startx,starty;
    private MyDeque<Node> frontier;
    private MyDeque<Integer> exitPath;
    
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

        public void setPrev(Node prev) {
            this.prev = prev;
        }

    }

    //Terminal keycodes to clear the terminal, or hide/show the cursor
    private String clear =  "\033[2J";
    private String hide =  "\033[?25l";
    private String show =  "\033[?25h";

    public Maze(String filename){
        // instantiate variables
        frontier = new MyDeque<Node>();
        exitPath = new MyDeque<Integer>();
        
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

    public boolean solveBFS(boolean animate) {
        int currx = startx, curry = starty;
        Node n = new Node(currx, curry, null);
        
        // copy maze over so I can modify this without worrying
        char[][] testMaze = new char[maxy][maxx];
        for (int i = 0; i < maxy; i++)
            for (int j = 0; j < maxx; j++)
                testMaze[i][j] = maze[i][j];

        do {
            // place a wall at the current space
            // prevents checking the same space twice
            testMaze[curry][currx] = '#';

            // check bottom space
            if ( testMaze[curry+1][currx] != '#' )
                frontier.addLast( new Node(currx, curry+1, n) );
            // check top space
            if ( testMaze[curry-1][currx] != '#' )
                frontier.addLast( new Node(currx, curry-1, n) );
            // check right space
            if ( testMaze[curry][currx+1] != '#' )
                frontier.addLast( new Node(currx+1, curry, n) );
            // check left space
            if ( testMaze[curry][currx-1] != '#' )
                frontier.addLast( new Node(currx-1, curry, n) );

            // if there's nowhere else to go, there's no exit path
            if ( frontier.size() == 0 )
                return false;

            // get the next space to check
            n = frontier.removeFirst();
            currx = n.getX();
            curry = n.getY();

        } while ( testMaze[curry][currx] != 'E' );

        // add exit to exitPath
        exitPath.addFirst( n.getY() );
        exitPath.addFirst( n.getX() );
        n = n.getPrev();
        // add each step to exitPath and place an '@' there
        while ( n.getPrev() != null ) {
            exitPath.addFirst( n.getY() );
            exitPath.addFirst( n.getX() );
            maze[n.getY()][n.getX()] = '@';
            n = n.getPrev();
        }
        // add start to exitPath
        exitPath.addFirst( n.getY() );
        exitPath.addFirst( n.getX() );

        return true;
    }

    public boolean solveDFS(boolean animate) {
        return false;
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
        System.out.println();
        m.solveBFS();
        System.out.println(m);
    }
}
