import java.util.Scanner;
import java.io.File;

public class Maze {

    private char[][]maze;
    private int maxx,maxy;
    private int startx,starty;
    
    private class Trail {
        int x;
        int y;
        MyDeque<Integer> path;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void push(int x, int y) {
            path.addLast(x);
            path.addLast(y);
        }

        public String pop() {
            return "(" + path.removeFirst() + "," + path.removeFirst() + ")";
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
        maze = new char[maxx][maxy];
        for(int i = 0; i < ans.length(); i++){
            char c = ans.charAt(i);
            maze[i % maxx][i / maxx] = c;
            if(c == 'S'){
                startx = i % maxx;
                starty = i / maxx;
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

    public void solveBFS(int mode) {
        
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++)
                s += maze[i][j] + " ";
            s += '\n';
        }
        return s;
    }

}
