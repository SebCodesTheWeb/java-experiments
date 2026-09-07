package fractal;

public class Line {
    private Point start;
    private Point end;

    /** Constructs and initializes defined by two points.
     *     
     * @param start the starting point of the line     
     * @param end   the ending point of the line     
     */
    
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**     
     * 
     * Returns the start point of the line.    
     * 
     * @return the start point     
     */    
    public Point getStartPoint() {        
    	return start;    
    }
    
    /**     
     * Returns the end point of the line.    
     * 
     * @return the start point     
     */    
    public Point getEndPoint() {        
    	return end;    
    }
}
