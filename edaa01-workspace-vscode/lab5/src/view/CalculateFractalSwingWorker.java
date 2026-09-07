package view;

import javax.swing.*;

import fractal.Fractal;
import fractal.Line;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;

public class CalculateFractalSwingWorker extends SwingWorker<List<Line>, Line> {
    private final FractalViewModel fractalViewModel;

    public CalculateFractalSwingWorker(FractalViewModel fractalViewModel) {
        this.fractalViewModel = fractalViewModel;
    }

    @Override
    public List<Line> doInBackground() {
        Fractal fractal = fractalViewModel.getFractal();
        fractal.setPartialResultConsumer(this::publish);
        return fractal.getLinesToDraw();
    }
    @Override
    protected void process(List<Line> chunks) {
        fractalViewModel.sides.addAll(chunks);
    }
    @Override
    public void done() {
        try {
            fractalViewModel.sides = get();
        } catch (InterruptedException | CancellationException ignore) {

        } catch (java.util.concurrent.ExecutionException e) {
            String why;
            Throwable cause = e.getCause();
            why = Objects.requireNonNullElse(cause, e).getMessage();
            System.err.println("Error calculating fractal: " + why);
        }
    }
}
