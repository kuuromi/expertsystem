package expertsystem.engine;

import java.util.Set;
import expertsystem.model.*;

public class RuleBasedEngine {

    public DiagnosisResult diagnose(Set<Symptom> s) {

        int influenza = 0;
        if(s.contains(Symptom.DEMAM_RINGAN)) influenza++;
        if(s.contains(Symptom.BATUK_KERING)) influenza++;
        if(s.contains(Symptom.PILEK)) influenza++;
        if(s.contains(Symptom.SAKIT_TENGGOROKAN)) influenza++;

        int dbd = 0;
        if(s.contains(Symptom.DEMAM_TINGGI)) dbd++;
        if(s.contains(Symptom.NYERI_SENDI)) dbd++;
        if(s.contains(Symptom.RUAM_KULIT)) dbd++;
        if(s.contains(Symptom.MUAL_MUNTAH)) dbd++;

        int diabetes = 0;
        if(s.contains(Symptom.SERING_HAUS)) diabetes++;
        if(s.contains(Symptom.SERING_BAK)) diabetes++;
        if(s.contains(Symptom.MUDAH_LELAH)) diabetes++;
        if(s.contains(Symptom.LUKA_SULIT_SEMBUH)) diabetes++;

        int hipertensi = 0;
        if(s.contains(Symptom.SAKIT_KEPALA)) hipertensi++;
        if(s.contains(Symptom.PUSING)) hipertensi++;
        if(s.contains(Symptom.PENGLIHATAN_KABUR)) hipertensi++;
        if(s.contains(Symptom.TEKANAN_DARAH_TINGGI)) hipertensi++;
        if(s.contains(Symptom.MIMISAN)) hipertensi++;

        StringBuilder exp = new StringBuilder();
        exp.append("=== HASIL RULE ===\n\n");
        exp.append("Influenza: " + influenza + "/4\n");
        exp.append("Demam Berdarah: " + dbd + "/4\n");
        exp.append("Diabetes: " + diabetes + "/4\n");
        exp.append("Hipertensi: " + hipertensi + "/5\n\n");

        Disease best = Disease.TIDAK_TERDIAGNOSA;
        int max = 1;

        if(influenza > max){ max = influenza; best = Disease.INFLUENZA; }
        if(dbd > max){ max = dbd; best = Disease.DEMAM_BERDARAH; }
        if(diabetes > max){ max = diabetes; best = Disease.DIABETES; }
        if(hipertensi > max){ max = hipertensi; best = Disease.HIPERTENSI; }

        if(best == Disease.TIDAK_TERDIAGNOSA){
            exp.append("Tidak memenuhi minimal gejala.");
            return new DiagnosisResult(best, 0, "Rule", exp.toString());
        }

        double conf = (double) max / (best == Disease.HIPERTENSI ? 5 : 4);

        exp.append("KESIMPULAN:\n");
        exp.append(best.getName() + " (" + (conf*100) + "%)");

        return new DiagnosisResult(best, conf, "Rule", exp.toString());
    }
}
