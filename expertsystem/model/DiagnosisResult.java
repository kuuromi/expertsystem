package expertsystem.model;

public class DiagnosisResult {

    private Disease disease;
    private double confidence;
    private String method;
    private String explanation;

    public DiagnosisResult(Disease disease, double confidence, String method, String explanation) {
        this.disease = disease;
        this.confidence = confidence;
        this.method = method;
        this.explanation = explanation;
    }

    public Disease getDisease() {
        return disease;
    }

    public double getConfidence() {
        return confidence;
    }

    public String getMethod() {
        return method;
    }

    public String getExplanation() {
        return explanation;
    }
}
