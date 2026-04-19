package expertsystem.model;

public enum Disease {

    INFLUENZA("Influenza", "Infeksi", "Istirahat, minum obat flu"),
    DEMAM_BERDARAH("Demam Berdarah", "Infeksi", "Segera ke dokter"),
    DIABETES("Diabetes", "Non-Infeksi", "Kontrol gula darah"),
    HIPERTENSI("Hipertensi", "Non-Infeksi", "Kurangi garam & cek rutin"),
    TIDAK_TERDIAGNOSA("Tidak Terdiagnosa", "-", "Periksa lebih lanjut");

    private final String name;
    private final String category;
    private final String recommendation;

    Disease(String name, String category, String recommendation) {
        this.name = name;
        this.category = category;
        this.recommendation = recommendation;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getRecommendation() { return recommendation; }
}
