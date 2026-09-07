package fractal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Fractal {
	protected int order;
    private List<Line> linesToDraw;
    private Point currentPosition;
    
    private int delay;
    private Consumer<Line> partialResultConsumer;

	protected Fractal() {
        order = 0;
        linesToDraw = new ArrayList<>();
        currentPosition = new Point(0, 0);
        delay = 0;
    }

	/**
	 * Returns the title.
	 * @return the title
	 */
	public abstract String getTitle();
	
	/**
	 * Returns a string representation of this fractal
	 * @return a string representation of this fractal
	 */
	public String toString() {
		return getTitle();
	}

	/** Sets the order of the fractal to order.
	 * @param order the new order of the fractal
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/** 
	 * Returns the order of the fractal. 
	 * @return the order of the fractal*/
	public int getOrder(){
		return order;
	}
	
	/** 
	 * Sets the starting point to p.
	 * @param p the starting point
	 */
	protected void setStartingPoint(Point p) {
		currentPosition = p;
		linesToDraw = new ArrayList<>();		
	}

	/**
	 * Draws the fractal.
	 */
    public abstract void draw();
    
    
    /** 
     * Collects and return a list with the lines which will be drawn.
     *
     * @return a list with lines that will be drawn.
     */
    public List<Line> getLinesToDraw() {
    	linesToDraw = new ArrayList<>();
    	draw();
    	return linesToDraw;
    }

	/**
	 * Adds a line with length length and angle alpha o the list of lines which will
	 * be drawn. If it is the first line, it starts from the fractal's starting
	 * point. Otherwise, it starts from the last added line's ending point.
	 * 
	 * @param the length of the line
	 * @param the angle of the line
	 */  
    protected void drawLine(double length, int alpha) {
        Point current;
        if (linesToDraw.isEmpty()) {
            current = this.currentPosition;
        } else {
        	current = linesToDraw.get(linesToDraw.size() - 1).getEndPoint();
        }
        double x = current.getX() + length * Math.cos(alpha * Math.PI / 180);
        double y = current.getY() - length * Math.sin(alpha * Math.PI / 180);
        addLine(new Line(current, new Point(x, y)));
    }
    
	/**
	 * Adds a line with starting point p1 and ending point p2 to the list of lines
	 * which will be drawn
	 *
	 * @param p1 starting point of the line
	 * @param p2 ending point of the line
	 */
    protected void drawLine(Point p1, Point p2) {
        addLine(new Line(p1, p2));
    }
    
    
	/**
	 * Sets a new delay value in milliseconds. 
	 * 
	 * @param newDelay the new delay value
	 */
	public void setDelay(int newDelay) {
		this.delay = newDelay;
	}
	
	/**
	 *
	 * Returns the current delay value in milliseconds. 
	 * 
	 * @return the delay value
	 */
	public int getDelay() {
        return  delay;
    }

	/**
	 * Pauses the animation for the duration specified by the delay. 
	 */
	protected void animationPause() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds the line line to the list of lines which will be drawn
	 * If the fractal has a Consumer that takes partial results, it is also posted to it.
     *
     * @param line the line that is added the list of lines which will be drawn
     */
    private void addLine(Line line) {
        this.linesToDraw.add(line);
        if(this.partialResultConsumer!= null){
            this.partialResultConsumer.accept(line);
        }
        this.animationPause();
    }
    
    /**
     * Sets the Consumer that takes partial results.
     *
     * @param the Consumer that takes partial result.
     */
    public void setPartialResultConsumer(Consumer<Line> c){
        this.partialResultConsumer = c;
    }
}
