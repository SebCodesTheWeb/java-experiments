package game;

public class Board {
	public int numPins = 0;

	public Board() {

	}
	
	public void setUp(int numPins) {
		this.numPins = numPins;
	}
	
	public void takePins(int nbr) {
		this.numPins = this.numPins - nbr;
	}

	public int getNumPins() {
		return this.numPins;
	}	
}
