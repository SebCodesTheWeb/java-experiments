package game;
import java.util.Scanner;

public class HumanPlayer extends Player {

    public int getPinCount(String str, Scanner scanner) {
        int pinsToTake = scanner.nextInt();

        return pinsToTake;

    }
    
}
