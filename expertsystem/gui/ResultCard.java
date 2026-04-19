package expertsystem.gui;

import javax.swing.*;
import java.awt.*;

public class ResultCard extends JPanel {

    private JTextArea area;

    public ResultCard() {
        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);

        add(new JScrollPane(area), BorderLayout.CENTER);
    }

    public void setResult(String text) {
        area.setText(text);
    }
}
