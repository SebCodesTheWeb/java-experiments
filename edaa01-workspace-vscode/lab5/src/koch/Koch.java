package koch;

import fractal.Fractal;
import fractal.Point;

public class Koch extends Fractal {
    private final Point startPoint;
    private final int length;

    /**
     * Creates an object that handles Koch's fractal.
     *
     * @param length the length of the triangle side
     */
    public Koch(int length, Point startPoint) {
        super();
        this.length = length;
        this.startPoint = startPoint;
    }

    /**
     * Returns the title.
     *
     * @return the title
     */
    @Override
    public String getTitle() {
        return "Kochs triangel";
    }


    /**
     * Draws Koch's triangle
     */
    @Override
    public void draw() {
        setStartingPoint(startPoint);
        fractalLine(order, length, 0);
        fractalLine(order, length, 120);
        fractalLine(order, length, 240);
    }

    /**
     * Recursive method: Draws a recursive line of the triangle.
     */
    private void fractalLine(int order, double length, int alpha) {
    	// TODO
	}
       
}
