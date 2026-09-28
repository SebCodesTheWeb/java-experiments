package game;

public class Player {
    private String userId = "";

    public Player() {

    }

    public String getUserId() {
        return this.userId;
    }

    public void takePins(Board board, int nbrPins) {
        board.takePins(nbrPins);
    }
}
