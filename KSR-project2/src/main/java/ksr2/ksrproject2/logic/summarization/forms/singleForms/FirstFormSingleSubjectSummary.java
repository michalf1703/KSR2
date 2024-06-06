package ksr2.ksrproject2.logic.summarization.forms.singleForms;


import ksr2.ksrproject2.logic.calculation.sets.FuzzySet;
import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.MeasureWeights;
import ksr2.ksrproject2.logic.summarization.Quantifier;
import ksr2.ksrproject2.logic.summarization.AbsoluteQuantifier;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstFormSingleSubjectSummary implements SingleSubjectSummary {
    private final MeasureWeights weights;
    private final Quantifier quantifier;
    private final List<Label> summarizers;
    private final List<PowerliftingResult> subject;

    public FirstFormSingleSubjectSummary(MeasureWeights weights, Quantifier quantifier, List<Label> summarizers, List<PowerliftingResult> subject) {
        this.weights = weights;
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        this.subject = subject;
    }


    @Override
    public double getMainSummaryMeasure() {
        Map<String, Double> measures = calculateMeasures();
        //System.out.println(measures);
        //System.out.println(weights.getWeights());
        System.out.println("main summary measure:" + measures.entrySet().stream().mapToDouble(e -> e.getValue() * weights.getWeights().size()).sum());
        return measures.entrySet().stream().mapToDouble(e -> e.getValue() * weights.getWeights().get(e.getKey())).sum();
    }
    @Override
    public double getDegreeOfTruth_T1() {
        double r = 0.0;
        double m;
        for (PowerliftingResult result : subject) {
            r += and(summarizers, result);
        }
        if (quantifier.getClass().equals(AbsoluteQuantifier.class)) {
            m = 1;
        }
        else m = subject.size();
        System.out.println("T1" + quantifier.getFuzzySet().getMembershipDegree(r / m));
        return quantifier.getFuzzySet().getMembershipDegree(r / m);
    }


    @Override
    public double getDegreeOfImprecision_T2() {
        double multiply = 1.0;
        for (Label summarizer : summarizers) {
            multiply = multiply * summarizer.getFuzzySet().getDegreeOfFuzziness(subject.stream().map(c -> fieldForLabel(summarizer, c)).collect(Collectors.toList()));
        }
        double res = Math.pow(multiply, 1.0 / summarizers.size());
        return 1.0 - res;
    }

    @Override
    public double getDegreeOfCovering_T3() {
        int t = 0;
        for (PowerliftingResult result : subject) {
            double intersectedSummarizer = and(summarizers, result);
            if (intersectedSummarizer > 0) {
                t++;
            }
        }
        if (t == 0) {
            return 0;
        }
        System.out.println("T3" + (double) t / subject.size());
        return (double) t / subject.size();
    }

    @Override
    public double getDegreeOfAppropriateness_T4() {
        double multiply = 1.0;
        for (Label summarizer : summarizers) {
            double r = 0.0;
            for (PowerliftingResult result : subject) {
                if (summarizer.getFuzzySet().getMembershipDegree(fieldForLabel(summarizer, result)) > 0) {
                    r++;
                }
            }
            multiply *= (r / subject.size());
        }
        System.out.println("T4" + Math.abs(multiply - getDegreeOfCovering_T3()));
        return Math.abs(multiply - getDegreeOfCovering_T3());
    }

    @Override
    public double getDegreeOfSummary_T5() {
        System.out.println("T5" + 2 * Math.pow(0.5, summarizers.size()));
    return 2 * Math.pow(0.5,summarizers.size());
    }


    @Override
    public double getDegreeOfQuantifierImprecision_T6() {
        FuzzySet fs = quantifier.getFuzzySet();
        double measure = fs.getSupport().getSize();
        if (quantifier.getClass().equals(AbsoluteQuantifier.class)) {
            return 1.0 - (measure / subject.size());
        } else {
            return 1.0 - measure;
        }
    }

    @Override
    public double getDegreeOfQuantifierCardinality_T7() {
        double measure = quantifier.getFuzzySet().getCardinality();
        if (quantifier.getClass().equals(AbsoluteQuantifier.class)) {
            return 1.0 - (measure / subject.size());
        } else {
            return 1.0 - measure;
        }
    }


    @Override
    public double getDegreeOfSummarizerCardinality_T8() {
        double multiply = 1.0;
        for (Label summarizer : summarizers) {
            multiply = multiply * (summarizer.getFuzzySet().getCardinality((List<Double>) subject.stream().map(c -> fieldForLabel(summarizer, c)).collect(Collectors.toList())) / subject.size());
        }
        multiply = Math.pow(multiply, 1.0 / summarizers.size());
        System.out.println("T8" + (1 - multiply));
        return 1.0 - multiply;
    }

    @Override
    public double getDegreeOfQualifierImprecision_T9() {
        return 0;
    }

    @Override
    public double getDegreeOfQualifierCardinality_T10() {
        return 0;
    }

    @Override
    public double getLengthOfQualifier_T11() {
        return 1.0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(quantifier.getName().toUpperCase(Locale.ROOT)).append(" competitors are/have got ");
        for (int i = 0; i < summarizers.size(); i++) {
            Label summarizer = summarizers.get(i);
            sb.append(summarizer.getName().toUpperCase(Locale.ROOT)).append(" ").append(summarizer.getLinguisticVariableName().toLowerCase(Locale.ROOT));
            if (i + 1 < summarizers.size()) {
                sb.append(" and ");
            }
        }
        return sb.toString();
    }

}
