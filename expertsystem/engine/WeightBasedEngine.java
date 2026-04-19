package expertsystem.engine;

import expertsystem.model.Disease;
import expertsystem.model.DiagnosisResult;
import expertsystem.model.Symptom;

import java.util.*;

public class WeightBasedEngine {

    private final Map<Symptom, Map<Disease, Double>> weightMatrix;

    public WeightBasedEngine() {
        weightMatrix = new HashMap<>();
        initWeights();
    }

    private void initWeights() {

        // influenza
        setWeight(Symptom.DEMAM_RINGAN, Disease.INFLUENZA, 0.9, Disease.DEMAM_BERDARAH, 0.2);
        setWeight(Symptom.BATUK_KERING, Disease.INFLUENZA, 0.9);
        setWeight(Symptom.PILEK, Disease.INFLUENZA, 0.9);
        setWeight(Symptom.SAKIT_TENGGOROKAN, Disease.INFLUENZA, 0.8);

        // demam berdarah
        setWeight(Symptom.DEMAM_TINGGI, Disease.DEMAM_BERDARAH, 0.9, Disease.INFLUENZA, 0.1);
        setWeight(Symptom.NYERI_SENDI, Disease.DEMAM_BERDARAH, 0.85);
        setWeight(Symptom.RUAM_KULIT, Disease.DEMAM_BERDARAH, 0.95);
        setWeight(Symptom.MUAL_MUNTAH, Disease.DEMAM_BERDARAH, 0.75, Disease.DIABETES, 0.1);

        // diabetes
        setWeight(Symptom.SERING_HAUS, Disease.DIABETES, 0.9);
        setWeight(Symptom.SERING_BAK, Disease.DIABETES, 0.85);
        setWeight(Symptom.MUDAH_LELAH, Disease.DIABETES, 0.7, Disease.HIPERTENSI, 0.2);
        setWeight(Symptom.LUKA_SULIT_SEMBUH, Disease.DIABETES, 0.95);

        // hipertensi
        setWeight(Symptom.SAKIT_KEPALA, Disease.HIPERTENSI, 0.8, Disease.INFLUENZA, 0.2);
        setWeight(Symptom.PUSING, Disease.HIPERTENSI, 0.75, Disease.DIABETES, 0.1);
        setWeight(Symptom.PENGLIHATAN_KABUR, Disease.HIPERTENSI, 0.85);
        setWeight(Symptom.TEKANAN_DARAH_TINGGI, Disease.HIPERTENSI, 0.95);
        setWeight(Symptom.MIMISAN, Disease.HIPERTENSI, 0.7);
    }

    private void setWeight(Symptom symptom, Object... pairs) {
        Map<Disease, Double> map = weightMatrix.computeIfAbsent(symptom, k -> new HashMap<>());
        for (int i = 0; i < pairs.length; i += 2) {
            map.put((Disease) pairs[i], (Double) pairs[i + 1]);
        }
    }

    public DiagnosisResult diagnose(Set<Symptom> selectedSymptoms) {

        Map<Disease, Double> maxScores = new HashMap<>();
        Map<Disease, Double> achievedScores = new HashMap<>();

        for (Disease d : Disease.values()) {
            maxScores.put(d, 0.0);
            achievedScores.put(d, 0.0);
        }

        for (Map.Entry<Symptom, Map<Disease, Double>> entry : weightMatrix.entrySet()) {
            Symptom symptom = entry.getKey();

            for (Map.Entry<Disease, Double> dEntry : entry.getValue().entrySet()) {
                Disease disease = dEntry.getKey();
                double weight = dEntry.getValue();

                maxScores.merge(disease, weight, Double::sum);

                if (selectedSymptoms.contains(symptom)) {
                    achievedScores.merge(disease, weight, Double::sum);
                }
            }
        }

        StringBuilder explanation = new StringBuilder();
        explanation.append("=== HASIL BOBOT ===\n\n");

        Disease bestDisease = null;
        double bestScore = 0.15;

        for (Disease d : new Disease[]{
                Disease.INFLUENZA,
                Disease.DEMAM_BERDARAH,
                Disease.DIABETES,
                Disease.HIPERTENSI
        }) {

            double max = maxScores.getOrDefault(d, 1.0);
            double achieved = achievedScores.getOrDefault(d, 0.0);
            double normalized = max > 0 ? achieved / max : 0;

            explanation.append(String.format(
                    "%-20s : %.1f%%\n",
                    d.getName(), normalized * 100
            ));

            if (normalized > bestScore) {
                bestScore = normalized;
                bestDisease = d;
            }
        }

        explanation.append("\nKESIMPULAN:\n");

        if (bestDisease == null) {
            explanation.append("Tidak dapat mendiagnosa.");
            return new DiagnosisResult(Disease.TIDAK_TERDIAGNOSA, 0, "Weight", explanation.toString());
        }

        explanation.append(bestDisease.getName() + " (" + (bestScore * 100) + "%)");

        return new DiagnosisResult(bestDisease, bestScore, "Weight", explanation.toString());
    }
}
