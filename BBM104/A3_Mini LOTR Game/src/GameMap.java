import java.io.PrintWriter;
import java.util.Arrays;

public class GameMap {
    public static String[][] map;
    public static String repeated;

    public static void createMap(int x) {
        GameMap.map = new String[x][x];
        for (String[] arrayLine : GameMap.map) {
            Arrays.fill(arrayLine, "  ");
        }
        GameMap.repeated = new String(new char[(2 * x) + 2]).replace("\0", "*");
    }

    public static void writeMap(PrintWriter outWriter) {
        outWriter.println(repeated);
        for (String[] line : map) {
            outWriter.println("*" + String.join("", line) + "*");
        }
        outWriter.println(repeated + "\n");
    }
}