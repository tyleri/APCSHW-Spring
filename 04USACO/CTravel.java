import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.io.IOException;

public class CTravel {

    char[][] pasture;
    int startR, startC, finalR, finalC, time;

    public CTravel() {
        List<String> lines;
        try {
            lines = Files.readAllLines(
                    Paths.get("ctravel.in"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String[] arr = lines.get(0).split(" ");
        pasture = new char[Integer.parseInt(arr[0])][];
        time = Integer.parseInt(arr[2]);

        for (int i = 0; i < pasture.length; i++) {
            pasture[i] = lines.get(i+1).toCharArray();
        }

        arr = lines.get(lines.size()-1).split(" ");
        startR = Integer.parseInt( arr[0] );
        startC = Integer.parseInt( arr[1] );
        finalR = Integer.parseInt( arr[2] );
        finalC = Integer.parseInt( arr[3] );
    }

    public int getPaths() {
        return getPaths( startR, startC, time );
    }

    public int getPaths(int r, int c, int t) {
        if (r == finalR && c == finalC && t == 0)
            return 1;
        if (r > pasture.length || r < 1 || c > pasture[r-1].length || c < 1
                || pasture[r-1][c-1] == '*' || t == 0)
            return 0;
        return getPaths(r+1, c, t-1) + getPaths(r-1, c, t-1) +
            getPaths(r, c+1, t-1) + getPaths(r, c-1, t-1);
    }

    public static void main(String[] args) {
        CTravel ct = new CTravel();

        int result = ct.getPaths();
        System.out.println( result );

        try {
            Files.write( Paths.get("ctravel.out"),
                    String.valueOf(result).getBytes() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
