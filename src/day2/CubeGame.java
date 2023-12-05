package day2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CubeGame {
    public static void main(String[] args) throws FileNotFoundException {
//        int res = findGame("src/day2/input.txt", 12, 13, 14);
        int res = findPower("src/day2/input.txt");
        System.out.println(res);
    }

    public static int findGame(String inputPath, int wantedRed, int wantedGreen, int wantedBlue) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(inputPath));
        int total = 0;
        ArrayList<Game> games = new ArrayList<>();

        while (sc.hasNextLine()) {
            games.add(new Game(sc.nextLine()));
        }

        for (Game game : games) {
            if (game.maxRed <= wantedRed && game.maxBlue <= wantedBlue && game.maxGreen <= wantedGreen) {
                total += game.gameNum;
            }
        }

        return total;
    }

    public static int findPower(String  inputPath) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(inputPath));
        int total = 0;
        ArrayList<Game> games = new ArrayList<>();

        while (sc.hasNextLine()) {
            games.add(new Game(sc.nextLine()));
        }

        for (Game game : games) {
            total += game.maxRed * game.maxGreen * game.maxBlue;
        }

        return total;
    }
}
