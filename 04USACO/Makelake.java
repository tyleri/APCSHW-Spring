import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.io.IOException;

public class Makelake {
    int[][] ground;
    int finalLakeElevation;
    int rows;
    int columns;

    public static void main (String[] args) {
        Makelake ml = new Makelake();
        List<String> lines;

        try {
            lines = Files.readAllLines(
                    Paths.get("makelake.in"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String[] firstLineArray = lines.get(0).split(" ");
        ml.rows = Integer.parseInt(firstLineArray[0]);
        ml.columns = Integer.parseInt(firstLineArray[1]);
        ml.finalLakeElevation = Integer.parseInt(firstLineArray[2]);
        lines.remove(0);
        ml.ground = new int[ml.rows][ml.columns];
        for (int i = 0; i < ml.rows; i++) {
            for (int j = 0; j < ml.columns; j++) {
                ml.ground[i][j] = Integer.parseInt(
                        lines.get(0).split(" ")[j]);
            }
            lines.remove(0);
        }
        for (int i = 0; i < lines.size(); i++) {
            String[] currentLines = lines.get(i).split(" ");
            ml.stomp(
                    Integer.parseInt(currentLines[0]),
                    Integer.parseInt(currentLines[1]),
                    Integer.parseInt(currentLines[2]));
        }

        try {
            String s = String.valueOf( ml.calculateVolume() );
            Files.write( Paths.get("makelake.out"), s.getBytes() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stomp (int x, int y, int e) {
        x--; y--;
        int maxNumber = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ( i+x < rows &&
                        j+y < columns &&
                        ground[i + x][j + y] > maxNumber) {
                    maxNumber = ground[i + x][j + y];
                }
            }
        }
        for (int p = 0; p < e; p++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ( i+x < rows &&
                            j+y < columns &&
                            ground[i + x][j + y] == maxNumber) {
                        ground[i + x][j + y]--;
                    }
                }
            }
            maxNumber--;
        }
    }

    public int calculateVolume() {
        int sumDepth = 0;
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                if (ground[i][j] < finalLakeElevation)
                    sumDepth += finalLakeElevation - ground[i][j];
            }
        }
        return 6 * 12 * 6 * 12 * sumDepth;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++)
                s += ground[i][j] + " ";
            s += '\n';
        }
        return s;
    }

}
