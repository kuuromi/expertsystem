package expertsystem.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Sistem Pakar Diagnosa Penyakit");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ResultCard result = new ResultCard();
        DiagnosisPanel left = new DiagnosisPanel(result);

        setLayout(new GridLayout(1,2));

        add(left);
        add(result);
    }
}
