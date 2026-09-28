package game;
public class Board {
	public int numPins = 0;

	public Board() {
		this.setUp(0);
	}
	
	public void setUp(int numPins) {
		this.numPins = numPins;
	}
	
	public void takePins(int nbr) {
		if((nbr != 1 && nbr != 2) || nbr > this.numPins) {
			throw new IllegalArgumentException();
		}
		this.numPins = this.numPins - nbr;
	}

	public int getNumPins() {
		return this.numPins;
	}	
}
