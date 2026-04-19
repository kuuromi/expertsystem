package expertsystem.model;

public enum Symptom {

    DEMAM_RINGAN("Demam ringan"),
    BATUK_KERING("Batuk kering"),
    PILEK("Pilek"),
    SAKIT_TENGGOROKAN("Sakit tenggorokan"),

    DEMAM_TINGGI("Demam tinggi"),
    NYERI_SENDI("Nyeri sendi"),
    RUAM_KULIT("Ruam kulit"),
    MUAL_MUNTAH("Mual muntah"),

    SERING_HAUS("Sering haus"),
    SERING_BAK("Sering buang air kecil"),
    MUDAH_LELAH("Mudah lelah"),
    LUKA_SULIT_SEMBUH("Luka sulit sembuh"),

    SAKIT_KEPALA("Sakit kepala"),
    PUSING("Pusing"),
    PENGLIHATAN_KABUR("Penglihatan kabur"),
    TEKANAN_DARAH_TINGGI("Tekanan darah tinggi"),
    MIMISAN("Mimisan");

    private final String displayName;

    Symptom(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
