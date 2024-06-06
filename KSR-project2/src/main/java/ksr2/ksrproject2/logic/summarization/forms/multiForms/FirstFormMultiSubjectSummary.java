package ksr2.ksrproject2.logic.summarization.forms.multiForms;

import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.Quantifier;

import java.util.List;
import java.util.Locale;

public class FirstFormMultiSubjectSummary implements MultiSubjectSummary {
    private final Quantifier quantifier;
    private final List<Label> summarizers;
    private final List<PowerliftingResult> subject1;
    private final List<PowerliftingResult> subject2;

    public FirstFormMultiSubjectSummary(Quantifier quantifier, List<Label> summarizers, List<PowerliftingResult> subject1, List<PowerliftingResult> subject2) {
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        this.subject1 = subject1;
        this.subject2 = subject2;
    }


    @Override
    public double getDegreeOfTruthT1() {

        double nfSigmaFirstSubjSummarizers = nfSigmaCount(subject1, summarizers);
        double nfSigmaSecondSubjSummarizers = nfSigmaCount(subject2, summarizers);
        double m1 = subject1.size();
        double m2 = subject2.size();

        return quantifier.getFuzzySet().getMembershipDegree((nfSigmaFirstSubjSummarizers / m1) / ((nfSigmaFirstSubjSummarizers / m1) + (nfSigmaSecondSubjSummarizers / m2)));
    }

    @Override
    public String toString() {
        String subjectName1 = subject1.isEmpty() ? "Nieznany" : subject1.get(0).getWeightClass();
        String subjectName2 = subject2.isEmpty() ? "Nieznany" : subject2.get(0).getWeightClass();
    StringBuilder sb = new StringBuilder();
        sb.append(quantifier.getName().toUpperCase(Locale.ROOT))
                .append(" competitiors in  ")
                .append(subjectName1)
                .append(" compared to ")
                .append(" competitiors in")
                .append(subjectName2)
                .append(" are/having ");
        for (int i = 0; i < summarizers.size(); i++) {
            Label summarizer = summarizers.get(i);
            sb.append(summarizer.getName().toUpperCase(Locale.ROOT)).append(" ")
                    .append(summarizer.getLinguisticVariableName().toLowerCase(Locale.ROOT));
            if (i + 1 < summarizers.size()) {
                sb.append(" i ");
            }
        }
        return sb.toString();

    }
}
