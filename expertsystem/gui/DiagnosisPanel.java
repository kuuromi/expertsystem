package expertsystem.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import expertsystem.model.*;
import expertsystem.engine.*;

public class DiagnosisPanel extends JPanel {

    private List<SymptomCard> symptoms = new ArrayList<>();
    private ResultCard resultCard;

    public DiagnosisPanel(ResultCard resultCard) {
        this.resultCard = resultCard;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // influenza
        panel.add(new JLabel("=== Influenza ==="));

        add(panel,"Demam ringan (suhu 37.5–39°C, suhu meningkat bertahap, menggigil ringan)", Symptom.DEMAM_RINGAN);
        add(panel,"Batuk kering (batuk kering, berdahak ringan, frekuensi >3x/jam)", Symptom.BATUK_KERING);
        add(panel,"Pilek (hidung tersumbat, hidung berair, cairan bening encer, bersin berulang)", Symptom.PILEK);
        add(panel,"Sakit tenggorokan (nyeri saat menelan, kemerahan ringan pada faring)", Symptom.SAKIT_TENGGOROKAN);

        // demam berdarah
        panel.add(new JLabel("=== Demam Berdarah ==="));

        add(panel,"Demam tinggi (suhu > 39°C mendadak, pola 2–7 hari suhu turun naik)", Symptom.DEMAM_TINGGI);
        add(panel,"Nyeri sendi (nyeri pada otot, nyeri pada tulang)", Symptom.NYERI_SENDI);
        add(panel,"Ruam kulit (bintik merah, bintik tidak hilang saat ditekan, uji torniket positif)", Symptom.RUAM_KULIT);
        add(panel,"Mual/muntah (muntah >2x/hari, nafsu makan turun)", Symptom.MUAL_MUNTAH);

        // diabetes
        panel.add(new JLabel("=== Diabetes ==="));

        add(panel,"Sering haus (minum >3 liter/hari)", Symptom.SERING_HAUS);
        add(panel,"Sering buang air kecil (buang air kecil 8x/hari, sering buang air kecil pada malam hari)", Symptom.SERING_BAK);
        add(panel,"Mudah lelah (energi cepat habis, kadar gula darah >200 mg/dL)", Symptom.MUDAH_LELAH);
        add(panel,"Luka sulit sembuh (infeksi berulang, penyembuhan >2 minggu)", Symptom.LUKA_SULIT_SEMBUH);

        // hipertensi
        panel.add(new JLabel("=== Hipertensi ==="));

        add(panel,"Sakit kepala (terjadi pagi hari, berdenyut, terutama di bagian belakang kepala)", Symptom.SAKIT_KEPALA);
        add(panel,"Pusing (terasa berputar dan ringan, terkait perubahan posisi)", Symptom.PUSING);
        add(panel,"Penglihatan kabur (gangguan visual sementara, retina mengalami penyempitan pembuluh)", Symptom.PENGLIHATAN_KABUR);
        add(panel,"Tekanan darah tinggi (konsisten tinggi ≥ 140/90 mmHg, detak jantung meningkat)", Symptom.TEKANAN_DARAH_TINGGI);
        add(panel,"Mimisan (perdarahan dari hidung)", Symptom.MIMISAN);

        JScrollPane scroll = new JScrollPane(panel);

        JButton btn = new JButton("Diagnosa Penyakit");
        btn.addActionListener(e -> proses());

        add(scroll, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
    }

    private void add(JPanel panel, String text, Symptom symptom){
        SymptomCard cb = new SymptomCard(text, symptom);
        symptoms.add(cb);
        panel.add(cb);
    }

    private void proses(){

        Set<Symptom> selected = new HashSet<>();

        for(SymptomCard s : symptoms){
            if(s.isSelected()){
                selected.add(s.getSymptom());
            }
        }

        RuleBasedEngine rule = new RuleBasedEngine();
        DiagnosisResult r1 = rule.diagnose(selected);

        WeightBasedEngine weight = new WeightBasedEngine();
        DiagnosisResult r2 = weight.diagnose(selected);

        StringBuilder hasil = new StringBuilder();

        hasil.append("=== METODE 1: RULE-BASED ===\n\n");
        hasil.append("Hasil: ")
            .append(r1.getDisease().getName())
            .append("\nConfidence: ")
            .append(String.format("%.1f%%", r1.getConfidence() * 100))
            .append("\n\n");
        hasil.append(r1.getExplanation());

        hasil.append("\n\n====================================\n\n");

        hasil.append("=== METODE 2: WEIGHT-BASED ===\n\n");
        hasil.append("Hasil: ")
            .append(r2.getDisease().getName())
            .append("\nConfidence: ")
            .append(String.format("%.1f%%", r2.getConfidence() * 100))
            .append("\n\n");
        hasil.append(r2.getExplanation());

        resultCard.setResult(hasil.toString());
    }
}
