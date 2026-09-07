package view;

import fractal.Fractal;
import fractal.Line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import java.util.List;

public class FractalViewModel implements ActionListener {
    public static final int DELAY_STEP = 10;
    private Fractal fractal;
    private final List<FractalListener> listeners = new ArrayList<>();
    List<Line> sides;
    Timer timer;
    SwingWorker<List<Line>, Line> worker;

    public List<Line> getLines(){
        return this.sides;
    }

    public void addListener(FractalListener f){
        this.listeners.add(f);
    }
    public int decreaseOrder() {
        int order = fractal.getOrder();
        if(order > 0){
            fractal.setOrder(order-1);
            updateFractal();
        }
        return fractal.getOrder();
    }
    private void notifyListeners(){
        for(FractalListener f : this.listeners){
            f.fractalChanged();
        }

    }
    public int increaseOrder() {
        fractal.setOrder(fractal.getOrder()+1);
        updateFractal();
        return fractal.getOrder();
    }

    private void updateFractal() {
        timer = new Timer(Math.max(fractal.getDelay()/2,1), this);
        timer.setRepeats(true);
        timer.start();
        this.sides = new ArrayList<>();
        if(worker!= null && !worker.isDone()){
            worker.cancel(true);
        }
        worker = new CalculateFractalSwingWorker(this);
        worker.execute();
    }

    public void setFractal(Fractal actFractal) {
        this.fractal = actFractal;
        this.fractal.setOrder(0);
        this.fractal.setDelay(0);
        updateFractal();
    }

    Fractal getFractal(){
        return fractal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        notifyListeners();
    }

    public int decreaseDelay() {
        int prevDelay = this.fractal.getDelay();
        int newDelay = Math.max(0,  prevDelay- DELAY_STEP);
        this.fractal.setDelay(newDelay);
        return newDelay;
    }

    public int increaseDelay() {
        this.fractal.setDelay(this.fractal.getDelay()+DELAY_STEP);
        return this.fractal.getDelay();
    }
}
