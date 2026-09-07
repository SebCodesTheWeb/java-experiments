package view;
import fractal.Line;
import fractal.Point;
import fractal.Fractal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.List;

import javax.swing.*;

public class FractalView implements FractalListener {
    private Fractal actFractal;
    private FractalViewModel viewModel;
    private FractalPanel drawingPanel;
    private ValuePanel orderPanel;
    private ValuePanel delayPanel;


    public FractalView(Fractal[] fractals, String title, int width, int height) {
        SwingUtilities.invokeLater(() -> createWindow(fractals, title, width, height));
    }

    /**
     * Private helper method, to confine all Swing-related work to
     * Swing's Event Dispatch Thread (EDT).
     */
    private void createWindow(Fractal[] fractals, String title, int width, int height) {
        actFractal = fractals[0];
        this.viewModel = new FractalViewModel();
        this.viewModel.addListener(this);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new FractalPanel();
        drawingPanel.setPreferredSize(new Dimension(width, height));
        drawingPanel.setBorder(BorderFactory.createEtchedBorder());

        orderPanel = new ValuePanel(viewModel::increaseOrder, viewModel::decreaseOrder, "Order: ");
        delayPanel = new ValuePanel(viewModel::increaseDelay, viewModel::decreaseDelay, "Delay: ");
        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        commandPanel.add(orderPanel.getMainComponent());
        commandPanel.add(delayPanel.getMainComponent());
        JComboBox<Fractal> comboBox = new JComboBox<>(fractals);
        comboBox.addActionListener(e -> {
            actFractal = (Fractal) comboBox.getSelectedItem();
            orderPanel.clear();
            delayPanel.clear();
            this.viewModel.setFractal(actFractal);
        });
        commandPanel.add(comboBox);
        frame.add(commandPanel, BorderLayout.SOUTH);
        frame.add(drawingPanel, BorderLayout.CENTER);
        viewModel.setFractal(actFractal);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void fractalChanged() {
       drawingPanel.repaint();
    }

    private class FractalPanel extends JPanel {
        private static final long serialVersionUID = 1L;

		@Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            List<Line> lines = viewModel.getLines();
            if(lines!= null){
                for (Line s : lines) {
                	Point p1 = s.getStartPoint();
                	Point p2 = s.getEndPoint();
                    g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
                }
            }
        }
    }

}
