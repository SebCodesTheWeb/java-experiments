package fractal;

import view.FractalView;
import koch.Koch;

public class FractalApplication {
	public static void main(String[] args) {

		Fractal[] fractals = new Fractal[1];
		int length = 300;
		int width = 700;
		int height = 600;
		Point start = new Point((width - length)/ 2, (int) (height / 2.0 + Math.sqrt(3.0) * length / 4.0));
		fractals[0] = new Koch(length, start);
		new FractalView(fractals, "Fraktaler", 700, 600);
	}
}
