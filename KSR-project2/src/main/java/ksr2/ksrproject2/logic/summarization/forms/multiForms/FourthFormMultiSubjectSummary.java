package ksr2.ksrproject2.logic.summarization.forms.multiForms;

import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FourthFormMultiSubjectSummary implements MultiSubjectSummary {

    private final List<Label> summarizers;
    private final List<PowerliftingResult> subject1;
    private final List<PowerliftingResult> subject2;

    public FourthFormMultiSubjectSummary(List<Label> summarizers, List<PowerliftingResult> subject1, List<PowerliftingResult> subject2) {
        this.summarizers = summarizers;
        this.subject1 = subject1;
        this.subject2 = subject2;
    }

    @Override
    public double getDegreeOfTruthT1() {
        double implication = 0.0;

        List<PowerliftingResult> all = Stream.concat(subject1.stream(), subject2.stream()).collect(Collectors.toList());
        for (PowerliftingResult powerliftingResult : all) {
            double memberShip = and(summarizers, powerliftingResult);
            if (subject1.contains(powerliftingResult) && subject2.contains(powerliftingResult)) {
                implication += lukasiewiczImplication(memberShip, memberShip);
            } else if (subject1.contains(powerliftingResult) && !subject1.contains(powerliftingResult)) {
                implication += lukasiewiczImplication(memberShip, 0);
            } else if (!subject2.contains(powerliftingResult) && subject1.contains(powerliftingResult)) {
                implication += lukasiewiczImplication(0, memberShip);
            }

        }
        return 1 - (implication / all.size());

    }

    private double lukasiewiczImplication(double a, double b) {
        return Math.min(1, 1 - a + b);
    }
    @Override
    public String toString() {
        String subjectName1 = subject1.isEmpty() ? "Nieznany" : subject1.get(0).getWeightClass();
        String subjectName2 = subject2.isEmpty() ? "Nieznany" : subject2.get(0).getWeightClass();
        StringBuilder sb = new StringBuilder();
        sb.append("More ")
                .append("competitors in ")
                .append(subjectName1)
                .append(" than ")
                .append("competitors in ")
                .append(subjectName2)
                .append(" are/have ");
        for (int i = 0; i < summarizers.size(); i++) {
            Label summarizer = summarizers.get(i);
            sb.append(summarizer.getName().toUpperCase(Locale.ROOT)).append(" ")
                    .append(summarizer.getLinguisticVariableName().toLowerCase(Locale.ROOT));
            if (i + 1 < summarizers.size()) {
                sb.append(" and ");
            }
        }
        return sb.toString();
    }

}
