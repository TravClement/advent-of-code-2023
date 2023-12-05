package day2;

import java.util.StringTokenizer;

public class Game {
    int gameNum;
    int maxRed;
    int maxGreen;
    int maxBlue;
    public Game(String line) {
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        String[] sections = line.split(":");
        String[] metaTokens = sections[0].split(" ");
        gameNum = Integer.parseInt(metaTokens[1]);

        String[] game = sections[1].split(";");
        maxBlue = 0;
        maxGreen = 0;
        maxRed = 0;
        for (String pull : game) {
            StringTokenizer st = new StringTokenizer(pull, ", ");
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                String color = st.nextToken();
                if (color.equals("blue") && num > maxBlue) {
                    maxBlue = num;
                } else if (color.equals("green") && num > maxGreen) {
                    maxGreen = num;
                } else if (color.equals("red") && num > maxRed) {
                    maxRed = num;
                }
            }
        }
    }
}
