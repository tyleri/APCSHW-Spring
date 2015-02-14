public class NQueens {

    private char[][] board;

    public NQueens() {}

    public NQueens(int size) {
        board = new char[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = '-';
    }

    public String toString() {
        String ans = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                ans += board[i][j] + " ";
            ans += '\n';
        }
        return ans;
    }

    public String name() {
        return "ishikawa.tyler";
    }

    public boolean solve() {
        return solve(0);
    }

    public boolean solve(int x) {
        board[0][x] = 'Q';
        for (int i = 0; i < board.length; i++)
            if ( solve(1, i) )
                return true;
        return false;
    }

    private boolean solve(int row, int col) {
        // check upward for any queens
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q')
                return false;

        // check diagonally for any queens
        int currR, currC;

        // Up-right check
        currR = row;
        currC = col;
        while (--currR >= 0 && ++currC < board.length)
            if (board[currR][currC] == 'Q')
                return false;
        // Up-left check
        currR = row;
        currC = col;
        while (--currR >= 0 && --currC >= 0)
            if (board[currR][currC] == 'Q')
                return false;

        // if not threatened by any queens
        board[row][col] = 'Q';
        if (row == board.length-1)
            return true;
        for (int i = 0; i < board.length; i++)
            if ( solve(row+1, i) )
                return true;
        board[row][col] = '-';
        return false;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        int size = 8; // board size for testing

        for (int i = 0; i < size; i++) {
            nq = new NQueens(size);
            if (nq.solve(i))
                System.out.println(nq);
        }
    }

}
