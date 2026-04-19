package expertsystem.gui;

import javax.swing.*;
import expertsystem.model.Symptom;

public class SymptomCard extends JCheckBox {

    private Symptom symptom;

    public SymptomCard(String text, Symptom symptom) {
        super(text);
        this.symptom = symptom;
    }

    public Symptom getSymptom() {
        return symptom;
    }
}
