package game;

import java.util.Scanner;

public class TakePinsGame {
    public static void main(String[] args) {
        Board board = new Board();
        board.setUp(10);

        HumanPlayer humanPlayer = new HumanPlayer();

        ComputerPlayer computerPlayer = new ComputerPlayer();

        Scanner scanner = new Scanner(System.in);

        while(board.getNumPins() > 0) {
            System.out.println(board.getNumPins());
            humanPlayer.takePins((board), humanPlayer.getPinCount((null), scanner));
            if(board.getNumPins() > 0) {
                break;
            }
            computerPlayer.takePins((board), computerPlayer.getPinCount((null)));
        }
    }
    
}
