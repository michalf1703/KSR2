package ksr2.ksrproject2.logic.summarization.forms.singleForms;


import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.Label;
import ksr2.ksrproject2.logic.summarization.Quantifier;
import ksr2.ksrproject2.logic.summarization.AbsoluteQuantifier;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FirstFormSingleSubjectSummary implements SingleSubjectSummary {
    private final Map<String, Double> weights;
    private final Quantifier quantifier;
    private final List<Label> summarizers;
    private final List<PowerliftingResult> subject;

    public FirstFormSingleSubjectSummary(Map<String, Double> weights, Quantifier quantifier, List<Label> summarizers, List<PowerliftingResult> subject) {
        this.weights = weights;
        this.quantifier = quantifier;
        this.summarizers = summarizers;
        this.subject = subject;
    }


    @Override
    public double getMainSummaryMeasure() {
        Map<String, Double> measures = calculateMeasures();
        return measures.entrySet().stream().mapToDouble(e -> e.getValue() * weights.get(e.getKey())).sum();
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
        } else m = subject.size();
        return quantifier.getFuzzySet().getMembershipDegree(r / m);
    }


    @Override
    public double getDegreeOfImprecision_T2() {

        double productOfImprecision = 1.0;
        int numberOfSummarizers = summarizers.size();

        for (Label summarizer : summarizers) {
            double degreeOfFuzziness = summarizer.getFuzzySet().getDegreeOfFuzziness();
            productOfImprecision *= degreeOfFuzziness;
        }

        double geometricMean = Math.pow(productOfImprecision, 1.0 / numberOfSummarizers);

        return 1 - geometricMean;
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
        return Math.abs(multiply - getDegreeOfCovering_T3());
    }

    @Override
    public double getDegreeOfSummary_T5() {
        return 2 * Math.pow(0.5,summarizers.size());
    }


    @Override
    public double getDegreeOfQuantifierImprecision_T6() {
        double result = quantifier.getFuzzySet().getDegreeOfFuzziness();

        if (quantifier instanceof AbsoluteQuantifier) {
            result /= subject.size();
        }

        return result;
    }

    @Override
    public double getDegreeOfQuantifierCardinality_T7() {
        double cardinality = quantifier.getFuzzySet().getCardinality();
        double universeSize = 1;

        if (quantifier instanceof AbsoluteQuantifier) {
            universeSize = subject.size();
        }

        return 1 - (cardinality / universeSize);
    }


    @Override
    public double getDegreeOfSummarizerCardinality_T8() {
        double productOfCardinalities = 1.0;

        for (Label summarizer : summarizers) {
            double cardinality = summarizer.getFuzzySet().getCardinality();
            productOfCardinalities *= cardinality;
        }

        double geometricMean = Math.pow(productOfCardinalities, 1.0 / summarizers.size());

        return 1 - geometricMean;
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
        sb.append(quantifier.getName().toUpperCase(Locale.ROOT)).append(" powerligting results are/have got ");
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
