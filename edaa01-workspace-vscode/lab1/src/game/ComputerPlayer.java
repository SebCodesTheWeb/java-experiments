package game;

import java.util.Random;

public class ComputerPlayer extends Player {

    public int getPinCount(String str) {
        return new Random().nextInt(1) + 1;
    }
}
