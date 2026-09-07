package view;

import javax.swing.*;
import java.awt.*;
import java.util.function.IntSupplier;

public class ValuePanel {

    private final JLabel label;
    private final JPanel mainComponent;
    private String valueText;

    public JPanel getMainComponent() {
        return mainComponent;
    }

    public ValuePanel(IntSupplier up, IntSupplier down, String valueText) {
        label = new JLabel(valueText + "0");
        this.valueText = valueText;
        JButton upButton = new JButton("▲");
        upButton.setFocusPainted(false);
        upButton.addActionListener(e -> {
            int value = up.getAsInt();
            this.label.setText(valueText + value);
        });

        JButton downButton = new JButton("▼");
        downButton.setFocusPainted(false);
        downButton.addActionListener(e -> {
           int value = down.getAsInt();
           this.label.setText(valueText + value);
        });
        this.mainComponent = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainComponent.setBorder(BorderFactory.createEtchedBorder());
        mainComponent.add(upButton);
        mainComponent.add(label);
        mainComponent.add(downButton);
    }
    void clear(){
        this.label.setText(valueText + "0");
    }
}
