package ksr2.ksrproject2.logic.summarization.forms.singleForms;

import ksr2.ksrproject2.logic.calculation.sets.FuzzySet;
import ksr2.ksrproject2.logic.model.PowerliftingResult;
import ksr2.ksrproject2.logic.summarization.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SecondFormSingleSubjectSummary implements SingleSubjectSummary {
    private final MeasureWeights weights;
    private final RelativeQuantifier quantifier;
    private final List<Label> qualifiers;
    private final List<Label> summarizers;
    private final List<PowerliftingResult> subject;

    public SecondFormSingleSubjectSummary(MeasureWeights weights, RelativeQuantifier quantifier, List<Label> qualifiers, List<Label> summarizers, List<PowerliftingResult> subject) {
        this.weights = weights;
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.subject = subject;
    }


    @Override
    public double getMainSummaryMeasure() {
        Map<String, Double> measures = calculateMeasures();
        return measures.entrySet().stream().mapToDouble(e -> e.getValue() * weights.getWeights().size()).sum();
    }

  public double getDegreeOfTruth_T1() {
      double r = 0.0;
      double m = 0.0;

      for (PowerliftingResult result : subject) {
          double intersectedQualifiers = and(qualifiers, result);
          r += Math.min(and(summarizers, result), intersectedQualifiers);
          m += intersectedQualifiers;
      }

      if (quantifier.getClass().equals(AbsoluteQuantifier.class)) {
          m = 1;
      }
      return quantifier.getFuzzySet().getMembershipDegree(r / m);
    }

    public double getDegreeOfImprecision_T2() {
        double multiply = 1.0;
        for (Label summarizer : summarizers) {
            multiply = multiply * subject.stream()
                    .map(c -> fieldForLabel(summarizer, c))
                    .mapToDouble(summarizer.getFuzzySet()::getDegreeOfFuzziness)
                    .reduce(1, (a, b) -> a * b);
        }
        double res = Math.pow(multiply, 1.0 / summarizers.size());
        return 1.0 - res;
    }

    public double getDegreeOfCovering_T3() {
        int t = 0;
        int h = 0;
        for (PowerliftingResult result : subject) {
            double intersectedQualifier = and(qualifiers, result);
            double intersectedSummarizer = and(summarizers, result);
            if (intersectedQualifier > 0) {
                h++;
            }
            if (Math.min(intersectedQualifier, intersectedSummarizer) > 0) {
                t++;
            }
        }
        if (h == 0 && t == 0) {
            return 0;
        }
        return (double) t / h;
    }

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

    public double getDegreeOfSummary_T5() {

        return 2.0* Math.pow(0.5, summarizers.size());
    }

    public double getDegreeOfQuantifierImprecision_T6() {

        FuzzySet fs = quantifier.getFuzzySet();
        double measure = fs.getSupport().getSize();
        if (quantifier.getClass().equals(AbsoluteQuantifier.class)) {
            return 1.0 - (measure / subject.size());
        } else {
            return 1.0 - measure;
        }
    }


    public double getDegreeOfQuantifierCardinality_T7() {
        double measure = quantifier.getFuzzySet().getCardinality();
        if (quantifier.getClass().equals(AbsoluteQuantifier.class)) {
            return 1.0 - (measure / subject.size());
        } else {
            return 1.0 - measure;
        }
    }

    public double getDegreeOfSummarizerCardinality_T8() {
        double multiply = 1.0;
        for (Label summarizer : summarizers) {
            multiply = multiply * subject.stream()
                    .map(c -> fieldForLabel(summarizer, c))
                    .mapToDouble(summarizer.getFuzzySet()::getCardinality)
                    .average()
                    .orElse(0.0);
        }
        multiply = Math.pow(multiply, 1.0 / summarizers.size());
        return 1.0 - multiply;
    }

    public double getDegreeOfQualifierImprecision_T9() {
        double multiply = 1.0;
        for (Label qualifier : qualifiers) {
            multiply = multiply * subject.stream()
                    .map(c -> fieldForLabel(qualifier, c))
                    .mapToDouble(qualifier.getFuzzySet()::getDegreeOfFuzziness)
                    .average()
                    .orElse(0.0);
        }
        double res = Math.pow(multiply, 1.0 / qualifiers.size());
        return 1.0 - res;
    }

    public double getDegreeOfQualifierCardinality_T10() {
        double multiply = 1.0;
        for (Label qualifier : qualifiers) {
            multiply = multiply * subject.stream()
                    .map(c -> fieldForLabel(qualifier, c))
                    .mapToDouble(qualifier.getFuzzySet()::getCardinality)
                    .average()
                    .orElse(0.0);
        }
        multiply = Math.pow(multiply, 1.0 / qualifiers.size());
        return 1.0 - multiply;
    }
    public double getLengthOfQualifier_T11() {

        return 2.0 * Math.pow(0.5, qualifiers.size());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(quantifier.getName().toUpperCase(Locale.ROOT)).append(" powerlifting results being/having ");
        for (int i = 0; i < qualifiers.size(); i++) {
            Label qualifier = qualifiers.get(i);
            sb.append(qualifier.getName().toUpperCase(Locale.ROOT)).append(" ").append(qualifier.getLinguisticVariableName().toLowerCase(Locale.ROOT));
            if (i + 1 < qualifiers.size()) {
                sb.append(" and ");
            }
        }
        sb.append(" have got ");
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
